defmodule Match do
    use GenServer

    def start_link(state), do: GenServer.start_link(__MODULE__, state)

    def init(state), do: {:ok, %{state | estado: :en_juego}}

    def handle_cast({:finalizar, resultado}, state) do
        # resultado: %{jugador1: 1, jugador2: 0}
        [j1, j2] = state.jugadores

        nuevo_j1 = Glicko2.update(j1, [%{rating: j2.rating, rd: j2.rd, score: resultado.jugador1}])
        nuevo_j2 = Glicko2.update(j2, [%{rating: j1.rating, rd: j1.rd, score: resultado.jugador2}])

        RatingRepo.guardar(nuevo_j1)
        RatingRepo.guardar(nuevo_j2)

        {:stop, :normal, %{state | estado: :finalizado}}
    end
end
