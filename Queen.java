
import java.util.ArrayList;

public class Queen extends Piece {
    
    public Queen(boolean newColor) {
        super(newColor);
    }

    @Override
    public ArrayList<int[]> possibleMoves(Square current, Square[][] board) {
        ArrayList<int[]> moves = new ArrayList<>();
        int row = current.getRow();
        int col = current.getCol();
        
        // 8 directions: 4 straight (Rook) + 4 diagonal (Bishop)
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},       // Rook moves
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}      // Bishop moves
        };

        for (int[] d : directions) {
            int newRow = row + d[0];
            int newCol = col + d[1];
            //continues until hitting edge of the board
            while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square next = board[newRow][newCol];
                
                if (!next.hasPiece()) { 
                    moves.add(new int[]{newRow, newCol}); //move is possible if the square is empty
                } else {
                    //if enemy piece, add as a possible capture move
                    if (next.getPiece().getColor() != this.getColor()) {
                        moves.add(new int[]{newRow, newCol}); 
                    } 
                    //stop moving in this direction once any piece is hit
                    break; 
                }
                //move to next square in this direction
                newRow += d[0];
                newCol += d[1];
            }
        }
        
        return moves;
    }
}
