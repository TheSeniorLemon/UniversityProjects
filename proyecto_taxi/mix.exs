defmodule ProyectoTaxi.MixProject do
  use Mix.Project

  def project do
    [
      app: :proyecto_taxi,
      version: "0.1.0",
      elixir: "~> 1.14",
      start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  def application do
    [
      extra_applications: [:logger],
      mod: {ProyectoTaxi.Application, []}
    ]
  end

  defp deps do
  [
    {:elixir_uuid, "~> 1.2"}
  ]
  end
end
