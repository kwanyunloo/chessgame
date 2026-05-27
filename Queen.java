/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author 3017317
 */
public class Queen extends Piece {
    
    public Queen(boolean newColor) {
        super(newColor);
    }
    public ArrayList<int[]> possibleMoves(Square square, Square[][] board){
        ArrayList<int[]> moves = new ArrayList<>();
        int originalRow = square.getRow();
        int originalCol = square.getCol();
        // left moving 
        for (int col = originalCol; i >= 0; i--){
            if (board[originalRow][col].hasPiece()){
                break;
            }else{
                int[] toAdd = new int[2];
                toAdd[0] = originalRow;
                toAdd[1] = col;
                moves.add(toAdd);
            }
        }
        // right moving
        for (int col = originalCol; i < 8; i++){
            if (board[originalRow][col].hasPiece()){
                break;
            }else{
                int[] toAdd = new int[2];
                toAdd[0] = originalRow;
                toAdd[1] = col;
                moves.add(toAdd);
            }
        }

        // above
        for (int row = originalRow; i >= 0; i--){
            if (board[row][col].hasPiece()){
                break;
            }else{
                int[] toAdd = new int[2];
                toAdd[0] = row;
                toAdd[1] = originalCol;
                moves.add(toAdd);
            }
        }
        // below
        for (int row = originalRow; i < 8; i++){
            if (board[row][col].hasPiece()){
                break;
            }else{
                int[] toAdd = new int[2];
                toAdd[0] = row;
                toAdd[1] = originalCol;
                moves.add(toAdd);
            }
        }
        // upper right diagnal
        for (int row = originalRow; i < 8; i++){
            
        }
        // upper left diagnal

        // lower right diagnal

        // lower left diagnal
    }
    
}
