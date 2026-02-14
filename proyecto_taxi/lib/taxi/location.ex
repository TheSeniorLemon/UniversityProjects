defmodule Taxi.Location do
  @filepath "data/locations.dat"

@doc """
Función para guardar las ubicaciones en un archivo
## Parámetro
  - filepath: ruta del archivo donde se guardarán las ubicaciones
"""
  def cargar_ubicaciones(filepath \\ @filepath) do
    case File.read(filepath) do
      {:ok, contenido} ->
        contenido
        |> String.split("\n", trim: true)
        |> Enum.reject(&(&1 == ""))
      {:error, _} ->
        []
    end
  end

@doc """
Función para verificar si una ubicación es válida
## Parámetro
  - ubicacion: ubicación a verificar
"""
  def ubicacion_valida?(ubicacion) do
    ubicaciones = cargar_ubicaciones()
    Enum.member?(ubicaciones, ubicacion)
  end

@doc """
Función para añadir una nueva ubicación
## Parámetro
  - ubicacion: ubicación a añadir
  - filepath: ruta del archivo donde se guardarán las ubicaciones
"""
  def agregar_ubicacion(ubicacion, filepath \\ @filepath) do
    ubicaciones = cargar_ubicaciones(filepath)

    if Enum.member?(ubicaciones, ubicacion) do
      {:error, "La ubicación ya existe"}
    else
      nuevas_ubicaciones = [ubicacion | ubicaciones]
      File.write(filepath, Enum.join(nuevas_ubicaciones, "\n"))
      {:ok, "Ubicación agregada exitosamente"}
    end
  end

@doc """
Función para listar las ubicaciones
## Parámetro
  - Ninguno
"""
  def listar_ubicaciones do
    cargar_ubicaciones()
  end
end
