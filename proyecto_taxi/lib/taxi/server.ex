defmodule Taxi.Server do
  alias Taxi.{UserManager, Supervisor}

@doc """
Función para manejar la conexión de un usuario.
Si el usuario no existe, lo registra.
## Parámetro
  - nombre: nombre del usuario
  - rol: rol del usuario ("cliente" o "conductor")
  - contrasena: contrasena del usuario
"""
  def connect(nombre, rol, contrasena) do
    case UserManager.log_in(nombre, contrasena) do
      {:ok, usuario} -> {:ok, usuario}
      {:error, _} -> UserManager.sign_in(nombre, rol, contrasena)
    end
  end

@doc """
Función para manejar la desconexión de un usuario.
## Parámetro
  - usuario: usuario que se desconecta
"""
  def disconnect(_usuario) do
    :ok
  end


@doc """
Función para que un cliente solicite un nuevo viaje.
## Parámetro
  - cliente: nombre del cliente que solicita el viaje
  - origen: ubicación de origen
  - destino: ubicación de destino
"""
  def request_trip(cliente, origen, destino) do
    codigo = generate_trip_code()
    case Supervisor.crear_viaje(cliente, origen, destino, codigo) do
      {:ok, _pid} -> {:ok, codigo}
      {:error, razon} -> {:error, razon}
    end
  end

@doc """
Función para que un conductor vea los viajes activos.
## Parámetro
  - ninguno
"""
  def list_trips do
    Supervisor.listar_viajes_activos()
  end

@doc """
Función para que un conductor acepte un viaje.
## Parámetro
  - pid: PID del proceso del viaje
  - conductor: nombre del conductor que acepta el viaje
"""
  def accept_trip(codigo, conductor) do
    case Supervisor.obtener_pid_por_codigo(codigo) do
      {:ok, pid} ->
        GenServer.call(pid, {:asignar_conductor, conductor})
      {:error, :not_found} ->
        {:error, :codigo_invalido}
    end
  end


@doc """
Función para que un usuario consulte su puntaje.
## Parámetro
  - nombre: nombre del usuario
"""
  def get_score(nombre) do
    UserManager.obtener_puntaje(nombre)
  end

@doc """
Función para que un usuario consulte el ranking global.
## Parámetro
  - ninguno
"""
  def ranking_global do
    UserManager.ranking_global()
  end

@doc """
Función para que un usuario consulte el ranking por rol.
## Parámetro
  - rol: rol de los usuarios ("cliente" o "conductor")
"""
  def ranking_por_rol(rol) do
    UserManager.ranking_por_rol(rol)
  end

  # Función auxiliar para generar un código alfanumérico aleatorio (5 caracteres)
  defp generate_trip_code() do
    :crypto.strong_rand_bytes(4)
    |> Base.encode64()
    |> binary_part(0, 5)
    |> String.replace("/", "A")
    |> String.replace("+", "B")
    |> String.upcase()
  end
end
