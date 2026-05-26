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
    
    public King(Player name) {
        super(name);
    }
    public ArrayList<int[]> possibleMoves(Square current, Square[][] board){
        ArrayList<int[]> toReturn = new ArrayList<>();
        if (current.x + 1 < 8){
            int[] toAdd = new int[2];
            toAdd[0] = current.x+1;
            toAdd[1] = current.y;
            toReturn.add(toAdd);
        }
        if (current.x - 1 >= 0){
            int[] toAdd = new int[2];
            toAdd[0] = current.x+1;
            toAdd[1] = current.y;
            toReturn.add(toAdd);
        }
        if (current.y - 1 >= 0){
            int[] toAdd = new int[2];
            toAdd[0] = current.x;
            toAdd[1] = current.y-1;
            toReturn.add(toAdd);
        }
        if (current.y + 1 < 8){
            int[] toAdd = new int[2];
            toAdd[0] = current.x;
            toAdd[1] = current.y+1;
            toReturn.add(toAdd);
        }
        return toReturn;
    }
    
}
