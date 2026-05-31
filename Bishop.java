package chess;

import java.util.ArrayList;
// used AI to fix small errors
public class Bishop extends Piece {
    
    public Bishop(boolean newColor) {
        super(newColor);
    }
    @Override
    public ArrayList<int[]> possibleMoves(Square current, Square[][] board) {
        ArrayList<int[]> moves = new ArrayList<>();
        boolean currentColor = current.getPiece().getColor();
        int[][] directions = { // used ai to figure out how to implement the directions
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
        };

        for (int[] d : directions) {

            int newRow = current.getRow() + d[0];
            int newCol = current.getCol() + d[1];

            while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square next = board[newRow][newCol];
                
                if (!next.hasPiece()) {
                    moves.add(new int[]{newRow, newCol});
                } else {
                    
                    if (next.getPiece().getColor() != this.getColor()) {
                        moves.add(new int[]{newRow, newCol}); 
                    }
                    break; 
                }
      
                newRow += d[0];
                newCol += d[1];
            }
        }
        return moves;
    }
}
