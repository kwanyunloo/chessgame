/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

/**
 *
 * @author 4004960
 */
// This acts as the driver class for our program
public class Chess{

    public static void main(String[] args){
        System.out.println("Hello");
           board[3][3].addPiece(rook);

        // get possible moves
        ArrayList<int[]> moves =
                rook.possibleMoves(board[3][3], board);

        // print moves
        for (int[] move : moves) {
            System.out.println("(" + move[0] + ", " + move[1] + ")");
        }
    }
}
