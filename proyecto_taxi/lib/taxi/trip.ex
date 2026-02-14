defmodule Taxi.Trip do
  use GenServer
  alias Taxi.{UserManager, Location}
  alias UUID

  @filepath "data/results.log"

  defstruct id: nil, cliente: nil, conductor: nil, origen: "", destino: "", estado: :pendiente

@doc """
  Especificación del proceso hijo para el DynamicSupervisor.
  Define que el viaje no debe reiniciarse al terminar.
  """
  def child_spec(args) do
    %{
      id: UUID.uuid4(),
      start: {__MODULE__, :start_link, [args]},
      restart: :temporary,
      shutdown: 5000,
      type: :worker
    }
  end
@doc """
Función para iniciar un nuevo viaje
## Ejemplo:
    iex> Taxi.Trip.start_link(%{cliente: "Sebas", origen: "Centro", destino: "Aeropuerto"})
    {:ok, pid}
## Parámetro
  - cliente: nombre del cliente que solicita el viaje

  - origen: ubicación de origen
  - destino: ubicación de destino
"""
  def start_link(%{cliente: cliente, origen: origen, destino: destino, codigo: codigo}) do
    GenServer.start_link(__MODULE__, %{cliente: cliente, origen: origen, destino: destino, codigo: codigo})
  end

@doc """
Callback que inicializa el proceso de viaje cuando se crea con `start_link/1`.
## Parámetro
  - %{cliente: cliente, origen: origen, destino: destino}: mapa con los datos del viaje
"""
  @impl true
  def init(%{cliente: cliente, origen: origen, destino: destino, codigo: codigo}) do
    if Location.ubicacion_valida?(origen) and Location.ubicacion_valida?(destino) do
      id = UUID.uuid4()
      estado_inicial = %__MODULE__{
        id: id,
        cliente: cliente,
        origen: origen,
        destino: destino,
        estado: :pendiente
      }
      estado_inicial = Map.put(estado_inicial, :codigo, codigo)
      escribir_log("#{fecha()} | Viaje #{id} solicitado por #{cliente} de #{origen} a #{destino}\n")
      Process.send_after(self(), :verificar_conductor, 300000_000)
      {:ok, estado_inicial}
    else
      {:stop, :ubicacion_invalida}
    end
  end

@doc """
Callback que maneja la asignación de conductor a un viaje activo.
Cambia el estado a `:en_progreso` y programa la finalización automática
del viaje en 20 segundos.
## Parámetro
  - pid: PID del proceso del viaje
  - conductor: nombre del conductor que acepta el viaje
"""
  @impl true
  def handle_call({:asignar_conductor, conductor}, _from, state) do
    nuevo_estado = %{state | conductor: conductor, estado: :en_progreso}
    escribir_log("#{fecha()} | Viaje #{state.id} aceptado por #{conductor}\n")

    Process.send_after(self(), :terminar_viaje, 20_000)
    {:reply, :ok, nuevo_estado}
  end

#Callback que maneja la solicitud de información del viaje.
#Devuelve un mapa con los detalles del viaje.
  def handle_call(:get_info, _from, state) do
    {:reply, {:ok, %{id: state.id, codigo: state.codigo, cliente: state.cliente, origen: state.origen, destino: state.destino, estado: state.estado}}, state}
  end

@doc """
Callback que se ejecuta automáticamente cuando el viaje debe finalizar.
Actualiza el estado a `:completado`, escribe en el log y suma puntos
a cliente y conductor.
## Parámetro
  - state: estado actual del viaje
"""
  @impl true
  def handle_info(:terminar_viaje, state) do
    escribir_log(
      "#{fecha()} | cliente=#{state.cliente}; conductor=#{state.conductor}; origen=#{state.origen}; destino=#{state.destino}; status=Completado\n"
    )
    UserManager.actualizar_puntaje(state.cliente, 10)
    UserManager.actualizar_puntaje(state.conductor, 15)

    {:stop, :normal, %{state | estado: :completado}}
  end


#Callback que se ejecuta automáticamente si no se asigna un conductor
#en el tiempo límite (20 segundos). Actualiza el estado a `:cancelado`,
## Parámetro
#- %{conductor: nil} = state: estado actual del viaje
  def handle_info(:verificar_conductor, %{conductor: nil} = state) do
  escribir_log(
    "#{fecha()} | cliente=#{state.cliente}; status=Cancelado (sin conductor)\n"
  )
  UserManager.actualizar_puntaje(state.cliente, -5)

  {:stop, :normal, %{state | estado: :cancelado}}
end


#Callback que se ejecuta automáticamente si el viaje ya tiene conductor asignado.
#No realiza ninguna acción.
## Parámetro
# - state: estado actual del viaje
def handle_info(:verificar_conductor, state) do
  {:noreply, state}
end

#Función auxiliar para escribir en el archivo log
  defp escribir_log(linea) do
    File.write(@filepath, linea, [:append])
  end


#Función auxiliar para ontener la fecha y hora actual
  defp fecha do
    DateTime.utc_now() |> DateTime.to_string()
  end
end
