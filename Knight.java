/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.util.ArrayList;

/**
 *
 * @author 4004960
 */
public class Knight extends Piece {
    
    public Knight(Player name) {
        super(name);

    }
    public ArrayList<int[]> possibleMoves(Square current){
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        int row = current.getRow();
        int col = current.getCol();
        if (row + 2 < 8 && col + 1 < 8){
            int[] toAdd = new int[2];
            toAdd[0] = row+2;
            toAdd[1] = col+1;
            possibleMoves.add(toAdd);
        }
        if (row + 1 < 8 && col + 2 < 8){
            int[] toAdd = new int[2];
            toAdd[0] = row+1;
            toAdd[1] = col+2;
            possibleMoves.add(toAdd);
        }
        if (row - 2 < 8 && col - 1 < 8){
            int[] toAdd = new int[2];
            toAdd[0] = row-2;
            toAdd[1] = col-1;
            possibleMoves.add(toAdd);
        }
        if (row - 1 < 8 && col - 2 < 8){
            int[] toAdd = new int[2];
            toAdd[0] = row-1;
            toAdd[1] = col-2;
            possibleMoves.add(toAdd);
        }
        if (row - 2 < 8 && col + 1 < 8){
            int[] toAdd = new int[2];
            toAdd[0] = row-2;
            toAdd[1] = col+1;
            possibleMoves.add(toAdd);
        }
        if (row + 1 < 8 && col - 2 < 8){
           int[] toAdd = new int[2];
            toAdd[0] = row+1;
            toAdd[1] = col-2;
            possibleMoves.add(toAdd); 
        }


        return possibleMoves;
    }
    
}
