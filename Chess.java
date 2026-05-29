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
        
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        
        while (!board.isGameOver()){
            if (board.playerTurn()){
                System.out.println("White to move. Enter the starting square and ending square.");
            }else{
                System.out.println("Black to move. Enter the starting square and ending square.");
            }
            String start = in.next();
            String end = in.next();
            int startRow = start.charAt(0) - 'a';
            int startCol = start.charAt(0) - '0';
            int endRow = start.charAt(0) - 'a';
            int endCol = start.charAt(0) - '0';
            if (board.move(startinRow, startingCol, endingRow, endingCol)){
                board.changeMove();
            }
            System.out.println(board); // make sure to make a toString method that prints it out correctly (rotate it)
        }
    }
}
