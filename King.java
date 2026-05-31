package chess;
// used AI to fix small errors
import java.util.ArrayList;

public class King extends Piece {
    
    public King(boolean newColor) {
        super(newColor);
    }
    @Override
    public ArrayList<int[]> possibleMoves(Square current, Square[][] board) {

        ArrayList<int[]> moves = new ArrayList<>();
        int row = current.getRow();

        int[][] directions = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
        };

        for (int[] d : directions) {

            int newX = current.getRow() + d[0];
            int newY = current.getCol() + d[1];

            if (newX >= 0 && newX < 8 &&
                newY >= 0 && newY < 8) {

                if (board[newX][newY].getPiece() == null) {

                    moves.add(new int[]{newX, newY});
                }

                else if (board[newX][newY].getPiece().getColor() != this.getColor()) {

                    moves.add(new int[]{newX, newY});
                }
            }
        }


        //castling
        if (!hasMoved) {
            // kingside castle - move to column 6
            if (!board[row][5].hasPiece() && !board[row][6].hasPiece()) {
                if (board[row][7].hasPiece()) {
                    Piece p = board[row][7].getPiece();
                    if (p instanceof Rook && !((Rook) p).getHasMoved()) {
                        moves.add(new int[]{row, 6});
                    }
                }
            }
            
            // queenside castle - move to column 2
            if (!board[row][1].hasPiece() && !board[row][2].hasPiece() && !board[row][3].hasPiece()) {
                if (board[row][0].hasPiece()) {
                    Piece p = board[row][0].getPiece();
                    if (p instanceof Rook && !((Rook) p).getHasMoved()) {
                        moves.add(new int[]{row, 2});
                    }
                }
            }
        }

        return moves;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean b) {
        hasMoved = b;
    }
    
}
