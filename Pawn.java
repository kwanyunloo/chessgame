package chess;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean hasMoved;
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
            Square forwardSquare = board[nextRow][col]
            if (!forwardSquare.hasPiece()){
                moves.add(new int[]{nextRow, col});
            }

            //forward 2 squares
            int doubleRow = row + (2 * direction);
                if (!hasMoved && doubleRow >= 0 && doubleRow < 8) {
                    Square doubleForwardSquare = board[doubleRow][col];
                    if (!doubleForwardSquare.hasPiece()) {
                        moves.add(new int[]{doubleRow, col});
                        justMovedTwo = true;
                    }
                }
        }

        //diagonal capturing
        int[] cols = {col + 1, col - 1};
        if (nextRow >= 0 && nextRow < 8) {
            for (int c: cols){
                if (c >= 0 && c < 8){
                    Square targetSquare = board[nextRow][col1];
                    if (targetSquare.hasPiece() && targetSquare.getPiece().getColor() != this.getColor()) {
                        moves.add(new int[]{nextRow, nextCol});
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
                        adjPiece = adjSquare.getPiece();
                        if (!targetSquare.hasPiece() && adjPiece() == Pawn && adjPiece.getColor() != this.getColor() && adjPiece.justMovedTwo()) {
                            moves.add(new int[]{nextRow, c});
                        }
                    }
                }
            }
        }
    }

    public void setHasMoved(boolean b) {
        hasMoved = b;
    }
    public void setJustMovedTwo(boolean b){
        justMovedTwo = b;
    }
}
