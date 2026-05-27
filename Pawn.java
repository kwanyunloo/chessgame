package chess;

public class Pawn extends Piece {

    private boolean hasMoved;
    
    public Pawn(boolean newColor) {
        super(newColor);
    }

    public ArrayList<int[]> possibleMoves(Square current, Square[][] board)
    {
        int[][] directions;

        if (current.getPiece().getColor()) {
            directions = {
                {0, 1},
                {0, 2},
                {1, 1},
                {-1, 1}
            };
        } else {
            directions = {
                {0, -1},
                {0, -2},
                {1, -1},
                {-1, -1}
            };
        }
    }
}
