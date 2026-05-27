/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author 4004960
 */
public class King extends Piece {
    
    public King(boolean newColor) {
        super(newColor);
    }
    public ArrayList<int[]> move(Square current, Square[][] board) {

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

            int newX = current.x + d[0];
            int newY = current.y + d[1];

            if (newX >= 0 && newX < 8 &&
                newY >= 0 && newY < 8) {

                if (board[newX][newY].piece == null) {

                    moves.add(new int[]{newX, newY});
                }

                else if (board[newX][newY].getPiece.getColor != this.getColor) {

                    moves.add(new int[]{newX, newY});
                }
            }
        }

        return moves;
    }
    
}
