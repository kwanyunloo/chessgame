/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author 4004960
 */
public class Bishop extends Piece {
    
    public Bishop(Player name) {
        super(name);
    }

    public ArrayList<int[]> move(Square current, Square[][] board) {

        ArrayList<int[]> moves = new ArrayList<>();

        int[][] directions = {
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
        };

        for (int[] d : directions) {

            int newX = current.x + d[0];
            int newY = current.y + d[1];

            while (newX >= 0 && newX < 8 &&
                   newY >= 0 && newY < 8) {

                if (board[newX][newY].getPiece == null) {

                    moves.add(new int[]{newX, newY});
                }

                else if (board[newX][newY].getPiece.getPlayer != this.getPlayer) {

                    moves.add(new int[]{newX, newY});
                    break;
                }

                else {
                    break;
                }

                newX += d[0];
                newY += d[1];
            }
        }

        return moves;
    }
    
}
