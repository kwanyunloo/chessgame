

import java.util.ArrayList;

public class Knight extends Piece {
    
    public Knight(boolean newColor) {
        super(newColor);

    }
    @Override
    public ArrayList<int[]> possibleMoves(Square current, Square[][] board) {
        ArrayList<int[]> moves = new ArrayList<>();
        
        // 8 possible knight moves
        int[][] knightMoves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] m : knightMoves) {
            int newRow = current.getRow() + m[0];
            int newCol = current.getCol() + m[1];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square next = board[newRow][newCol];
                
                if (!next.hasPiece() || next.getPiece().getColor() != this.getColor()) {
                    moves.add(new int[]{newRow, newCol});
                }
            }
        }

        return moves;
    }
    
}
