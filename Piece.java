package chess;

import java.util.ArrayList;

public abstract class Piece {
    // True for White, False for Black
    protected boolean color;

    public Piece(boolean newColor) {
        color = newColor;
    }

    public boolean getColor() {
        return this.isWhite;
    }

    public abstract ArrayList<int[]> possibleMoves(Square current, Square[][] board);
}
