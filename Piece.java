//used AI to fix small errors
import java.util.ArrayList;

public abstract class Piece {
    // True for White, False for Black
    protected boolean color;

    //used for castling and pawn moves
    protected boolean hasMoved;

    //returns the color of the piece
    public Piece(boolean newColor) {
        color = newColor;
    }

    public boolean getColor() {
        return this.color;
    }

    public abstract ArrayList<int[]> possibleMoves(Square current, Square[][] board);
}
