package chess;

import java.util.Scanner;

// This acts as the driver class for our program
public class Chess{

    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        Stack<Board> boardPositions = new Stack<>();
        boardPositions.add(board);
        Stack<Board> storedMoves = new Stack<>();
        while (!board.isGameOver()){
            
            if (board.playerTurn()){
                System.out.println("White to move. Enter the starting square and ending square.");
            }else{
                System.out.println("Black to move. Enter the starting square and ending square.");
            }
            String start = in.next();
            while (start.equals("back") || start.equals("forward")){ // goes back or forward a move
                if (start.equals("back")){
                    if (boardPositions.isEmpty()){
                        System.out.println("You can't go back any more");
                    }else{
                        storedMoves.add(boardPositions.pop());
                    }
                }
                if (start.equals("forward")){
                    if (storedMoves.isEmpty()){
                        System.out.println("You can't go forward any more.");
                    }else{
                        boardPositions.add(storedMoves.pop());
                    }
                }
                board = boardPositions.peek();
                start = in.next();
                System.out.println(board);
            }
            String end = in.next();
            int startRow = start.charAt(0) - 'a';
            int startCol = start.charAt(0) - '0';
            int endRow = start.charAt(0) - 'a';
            int endCol = start.charAt(0) - '0';
            if (board.move(startingRow, startingCol, endingRow, endingCol)){
                // asked AI for advice on stack logic
                storedMoves.clear();
                board.changeMove(); // changes the turn from white to black or black to white
                boardPositions.add(new Board(board));
                
            }
            
            System.out.println(board); // make sure to make a toString method that prints it out correctly (rotate it)
            
        }
    }
}
