//used AI to fix small errors
import java.util.ArrayList;

public abstract class Piece {
    
    // True for White, False for Black
    protected boolean color;

    //used for castling and pawn moves
    protected boolean hasMoved;

    public Piece(boolean newColor) {
        color = newColor;
    }
    
    //returns the color of the piece
    public boolean getColor() {
        return this.color;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean b) {
        hasMoved = b;
    }

    public abstract ArrayList<int[]> possibleMoves(Square current, Square[][] board);
}
