/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author 4004960
 */
import java.util.ArrayList;

public class Bishop extends Piece {
    
    public Bishop(Player name) {
        super(name);
    }

    public ArrayList<int[]> possibleMoves(Square current, Square[][] board) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        boolean currentColor = current.getPiece().getColor();
        int[][] directions = {
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
        };

        for (int[] d : directions) {

            int newRow = current.getRow() + d[0];
            int newCol = current.getCol() + d[1];

            while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                int[] toAdd = new int[2];
                toAdd[0] = newRow;
                toAdd[1] = newCol;
                Square next = board[newRow][newCol];
                if (next.hasPiece()){
                    if (next.getPiece().getColor() == currentColor){
                        possibleMoves.add(toAdd);
                        break;
                    }else{
                        break;
                    }
                }
                
                

                newRow += d[0];
                newCol += d[1];
            }
        }
        return possibleMoves;
    
}
