defmodule ProyectoTaxi.Application do
  use Application

  def start(_type, _args) do
    children = [
      {Taxi.Supervisor, []},
    ]

    opts = [strategy: :one_for_one, name: ProyectoTaxi.Supervisor]
    Supervisor.start_link(children, opts)
  end
end
