package chess;

import java.util.ArrayList;

public class King extends Piece {
    boolean hasMoved = false;
    
    public King(boolean newColor) {
        super(newColor);
    }
    public ArrayList<int[]> possibleMoves(Square current, Square[][] board) {

        ArrayList<int[]> moves = new ArrayList<>();

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

        return moves;
    }
    
}
