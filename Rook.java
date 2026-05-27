/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author 4004960
 */
public class Rook extends Piece {
    
    public Rook(Player name) {
        super(name);
    }
      public ArrayList<int[]> possibleMoves(Square current, Square[][] board){
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        int row = current.getRow();
        int col = current.getCol();
    
}
