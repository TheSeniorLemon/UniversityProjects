defmodule RatingRepo do
    def start_link do
        :ets.new(:ratings, [:named_table, :set, :public])
        {:ok, %{}}
    end

    def obtener(id) do
        case :ets.lookup(:ratings, id) do
        [{^id, rating}] -> rating
        [] -> %Glicko2{}
        end
    end

    def guardar(%{id: id} = player) do
        :ets.insert(:ratings, {id, player})
    end
end
