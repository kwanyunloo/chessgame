// used ai to fix small errors

import java.util.Scanner;
import java.util.Stack;

// This acts as the driver class for our program
public class Chess{

    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        System.out.println(board);
        Stack<Board> boardPositions = new Stack<>();
        boardPositions.add(new Board(board));
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
                    if (boardPositions.size() <= 1){
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
                board = new Board(boardPositions.peek());
                System.out.println(board);
                start = in.next();
            }
            String end = in.next();
            int startRow = start.charAt(1) - '1';
            int startCol = start.charAt(0) - 'a';
            int endRow = end.charAt(1) - '1';
            int endCol = end.charAt(0) - 'a';
            if (board.move(startRow, startCol, endRow, endCol)){
                // asked AI for advice on stack logic
                storedMoves.clear();
                board.changeMove(); // changes the turn from white to black or black to white
                boardPositions.add(new Board(board));
                
            }
            
            System.out.println(board); // make sure to make a toString method that prints it out correctly (rotate it)
            
        }
        if (board.isCheckmate(board.playerTurn())) {
            System.out.println((board.playerTurn() ? "Black" : "White") + " wins by checkmate!");
        } else {
            System.out.println("Stalemate! The game is a draw.");
        }
    }
}
