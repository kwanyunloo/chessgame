// used AI to fix small errors
import java.util.*;

public class Pawn extends Piece {
    private boolean justMovedTwo;
    
    public Pawn(boolean newColor) {
        super(newColor);
    }

    @Override
    public ArrayList<int[]> possibleMoves(Square current, Square[][] board)
    {
        ArrayList<int[]> moves = new ArrayList<>();
        int row = current.getRow();
        int col = current.getCol();

        int direction;
        if (current.getPiece().getColor()) {
            direction = 1;
        } 
        else {
            direction = -1;
        }

        int nextRow = row + direction;
        if (nextRow >= 0 && nextRow < 8){
            //forward 1 square
            Square forwardSquare = board[nextRow][col];
            if (!forwardSquare.hasPiece()){
                moves.add(new int[]{nextRow, col});
            }

            //forward 2 squares
            int doubleRow = row + (2 * direction);
                if (!hasMoved && doubleRow >= 0 && doubleRow < 8) {
                    Square doubleForwardSquare = board[doubleRow][col];
                    if (!doubleForwardSquare.hasPiece()) {
                        moves.add(new int[]{doubleRow, col});
                    }
                }
        }

        //diagonal capturing
        int[] cols = {col + 1, col - 1};
        if (nextRow >= 0 && nextRow < 8) {
            for (int c: cols){
                if (c >= 0 && c < 8){
                    Square targetSquare = board[nextRow][c];
                    if (targetSquare.hasPiece() && targetSquare.getPiece().getColor() != this.getColor()) {
                        moves.add(new int[]{nextRow, c});
                    }
                }
            }
        }
        //en passant
        if (nextRow >= 0 && nextRow < 8) {
            for (int c: cols){
                if (c >= 0 && c < 8){
                    Square targetSquare = board[nextRow][c];
                    Square adjSquare = board[row][c];
                    if (adjSquare.hasPiece()){
                        Piece adjPiece = adjSquare.getPiece();
                        if (!targetSquare.hasPiece() && adjPiece instanceof Pawn && adjPiece.getColor() != this.getColor()) {
                            if (((Pawn) adjPiece).justMovedTwo()){
                                moves.add(new int[]{nextRow, c});
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    public boolean getJustMovedTwo(){
        return justMovedTwo;
    }

    public void setJustMovedTwo(boolean b){
        justMovedTwo = b;
    }
}
