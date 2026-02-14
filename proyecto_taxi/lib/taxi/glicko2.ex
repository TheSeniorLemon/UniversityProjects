defmodule Glicko2 do
    @moduledoc "Sistema de puntuación Glicko-2"
    @q :math.log(10) / 400
    @tau 0.5

    defstruct rating: 1500.0, rd: 350.0, volatility: 0.06

    def new(), do: %__MODULE__{}

    def update(player, opponents) do
        # opponents: [%{rating: r, rd: rd, score: s}]
        g = fn rd -> 1 / :math.sqrt(1 + 3 * @q * @q * rd * rd / (:math.pi * :math.pi)) end
        e = fn p, o -> 1 / (1 + :math.pow(10, -g.(o.rd) * (p.rating - o.rating) / 400)) end

        v_inv =
            opponents
            |> Enum.map(fn o -> (g.(o.rd) ** 2) * e.(player, o) * (1 - e.(player, o)) end)
            |> Enum.sum()

            v = 1 / (@q * @q * v_inv)
                    delta = v * @q * Enum.map(opponents, fn o -> g.(o.rd) * (o.score - e.(player, o)) end) |> Enum.sum()

        rd_star = :math.sqrt(player.rd * player.rd + player.volatility * player.volatility)
        rd_new = 1 / :math.sqrt(1 / (rd_star * rd_star) + 1 / v)
        rating_new = player.rating + @q / (1 / (rd_new * rd_new)) * delta

        %__MODULE__{player | rating: rating_new, rd: rd_new}
    end
end
