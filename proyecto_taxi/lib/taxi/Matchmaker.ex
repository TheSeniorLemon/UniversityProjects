defmodule Matchmaker do
    use GenServer

    # API pública
    def start_link(_), do: GenServer.start_link(__MODULE__, %{}, name: __MODULE__)

    def buscar_partida(jugador) do
        GenServer.call(__MODULE__, {:buscar, jugador})
    end

    # Estado: %{jugadores_en_espera: [jugador1, jugador2, ...]}
    def init(_) do
        {:ok, %{jugadores_en_espera: []}}
    end

    def handle_call({:buscar, jugador}, _from, state) do
        case encontrar_oponente(jugador, state.jugadores_en_espera) do
        {:ok, oponente, restantes} ->
            crear_partida(jugador, oponente)
            {:reply, {:match, oponente}, %{state | jugadores_en_espera: restantes}}

        :no_encontrado ->
            {:reply, :esperando, %{state | jugadores_en_espera: [jugador | state.jugadores_en_espera]}}
        end
    end

    # Emparejamiento simple por distancia de rating
    defp encontrar_oponente(jugador, jugadores) do
        rango = 100 # tolerancia de diferencia en rating
        case Enum.split_with(jugadores, fn j -> abs(j.rating - jugador.rating) <= rango end) do
        {[oponente | restantes], otros} -> {:ok, oponente, restantes ++ otros}
        _ -> :no_encontrado
        end
    end

    defp crear_partida(j1, j2) do
        spec = {Match, %{jugadores: [j1, j2]}}
        DynamicSupervisor.start_child(MatchSupervisor, spec)
    end
end
