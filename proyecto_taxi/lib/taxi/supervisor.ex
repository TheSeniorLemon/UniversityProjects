defmodule Taxi.Supervisor do
  use DynamicSupervisor
  alias Taxi.Trip

  @doc """
  Función para iniciar el supervisor de la aplicación Taxi.
  ## Parámetro
    - Ninguno
  """
  def start_link(_) do
    DynamicSupervisor.start_link(__MODULE__, :ok, name: __MODULE__)
  end

  @doc """
  Función para inicializar el supervisor con la estrategia `:one_for_one`.
  ## Parámetro
    - Ninguno
  """
  @impl true
  def init(:ok) do
    DynamicSupervisor.init(strategy: :one_for_one)
  end

  @doc """
  Función para crear un nuevo viaje mediante dynamic supervision.
  ## Parámetro
    - cliente: nombre del cliente que solicita el viaje
    - origen: ubicación de origen
    - destino: ubicación de destino
  """
  def crear_viaje(cliente, origen, destino, codigo) do
    # Buscar si ya existe un viaje con el mismo cliente o el mismo código
    viajes_activos = listar_viajes_activos()

    existe_viaje? =
      Enum.any?(viajes_activos, fn
        {_pid, %{cliente: c, codigo: cod, estado: estado}} ->
          (c == cliente or cod == codigo) and estado in [:pendiente, :en_progreso]

        _ ->
          false
      end)

    if existe_viaje? do
      {:error, :viaje_ya_existente}
    else
      spec = {Trip, %{cliente: cliente, origen: origen, destino: destino, codigo: codigo}}
      DynamicSupervisor.start_child(__MODULE__, spec)
    end
  end

  @doc """
  Función para listar los viajes activos.
  ## Parámetro
    - Ninguno
  """
  def listar_viajes_activos do
    DynamicSupervisor.which_children(__MODULE__)
    |> Enum.map(fn {_, pid, _, _} ->
      case GenServer.call(pid, :get_info) do
        {:ok, info} -> {pid, info}
        _ -> {pid, :error}
      end
    end)
  end

  @doc """
  Función para terminar un viaje activo.
  ## Parámetro
    - pid: PID del proceso del viaje a terminar
  """
  def terminar_viaje(pid) do
    DynamicSupervisor.terminate_child(__MODULE__, pid)
  end

  @doc """
  Busca el PID de un viaje según su código.
  """
  def obtener_pid_por_codigo(codigo) do
    DynamicSupervisor.which_children(__MODULE__)
    |> Enum.find_value(fn {_, pid, _, _} ->
      case GenServer.call(pid, :get_info) do
        {:ok, %{codigo: ^codigo}} -> {:ok, pid}
        _ -> nil
      end
    end)
    |> case do
      nil -> {:error, :not_found}
      result -> result
    end
  end
end
