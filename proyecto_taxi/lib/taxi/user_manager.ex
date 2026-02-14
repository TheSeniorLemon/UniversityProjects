defmodule Taxi.UserManager do
  @filepath "data/users.dat"

  defstruct nombre_usuario: "", rol: "", contrasena: "", puntaje: 0

@doc """
Función para crear un nuevo usuario
## Parámetro
  - nombre: nombre del usuario
  - rol: rol del usuario ("cliente" o "conductor")
  - contrasena: contraseña del usuario
"""
  def crear_usuario(nombre, rol, contrasena) when rol in ["cliente", "conductor"] do
    %Taxi.UserManager{nombre_usuario: nombre, rol: rol, contrasena: contrasena, puntaje: 0}
  end

@doc """
Función registrar un nuevo usuario
## Parámetro
  - nombre: nombre del usuario
  - rol: rol del usuario ("cliente" o "conductor")
  - contrasena: contraseña del usuario
"""
  def sign_in(nombre, rol, contrasena) do
    usuarios = cargar_usuarios()
    if Enum.any?(usuarios, fn u -> u.nombre_usuario == nombre end) do
      {:error, "El nombre de usuario ya existe"}
    else
      nuevo_usuario = crear_usuario(nombre, rol, contrasena)
      usuarios_actualizados = [nuevo_usuario | usuarios]
      guardar_usuarios(usuarios_actualizados)
      {:ok, nuevo_usuario}
    end
  end

@doc """
Función para iniciar sesión
## Parámetro
  - nombre: nombre del usuario
  - contrasena: contraseña del usuario
"""
  def log_in(nombre, contrasena) do
    usuarios = cargar_usuarios()
    case Enum.find(usuarios, fn u -> u.nombre_usuario == nombre and u.contrasena == contrasena end) do
      nil -> {:error, "Credenciales inválidas"}
      usuario -> {:ok, usuario}
    end
  end

@doc """
Función para obtener el puntaje de un usuario
## Parámetro
  - nombre: nombre del usuario
"""
  def obtener_puntaje(nombre) do
    usuarios = cargar_usuarios()
    case Enum.find(usuarios, fn u -> u.nombre_usuario == nombre end) do
      nil -> {:error, "Usuario no encontrado"}
      usuario -> {:ok, usuario.puntaje}
    end
  end

@doc """
Función para obtener el ranking general
## Parámetro
  - Ninguno
"""
  def ranking_global do
    cargar_usuarios()
    |> Enum.sort_by(& &1.puntaje, :desc)
  end

@doc """
Función para obtener el ranking de usuarios por rol
## Parámetro
  - rol: rol de los usuarios ("cliente" o "conductor")
"""
  def ranking_por_rol(rol) when rol in ["cliente", "conductor"] do
    cargar_usuarios()
    |> Enum.filter(&(&1.rol == rol))
    |> Enum.sort_by(& &1.puntaje, :desc)
  end

@doc """
Función para actualizar el puntaje de un usuario
## Parámetro
  - nombre: nombre del usuario
  - nuevo_puntaje: nuevo puntaje a asignar
"""
  def actualizar_puntaje(nombre_usuario, delta) do
  usuarios = cargar_usuarios()
  nuevos_usuarios = Enum.map(usuarios, fn u ->
    if u.nombre_usuario == nombre_usuario do
      %{u | puntaje: u.puntaje + delta}
    else
      u
    end
  end)
  guardar_usuarios(nuevos_usuarios)
  {:ok, Enum.find(nuevos_usuarios, &(&1.nombre_usuario == nombre_usuario))}
end

@doc """
Función para guardar los usuarios en un archivo
## Parámetro
  - usuarios: lista de usuarios a guardar
  - filepath: ruta del archivo donde se guardarán los usuarios
"""
  def guardar_usuarios(usuarios, filepath \\ @filepath) do
    File.write(filepath, :erlang.term_to_binary(usuarios))
  end

@doc """
Función para cargar los usuarios desde un archivo
## Parámetro
  - filepath: ruta del archivo desde donde se cargarán los usuarios
"""
  def cargar_usuarios(filepath \\ @filepath) do
    if File.exists?(filepath) do
      case File.read(filepath) do
        {:ok, ""} -> []
        {:ok, bin} -> :erlang.binary_to_term(bin)
        {:error, _} -> []
    end
     else
      []
    end
  end
end
