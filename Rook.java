package chess;

import java.util.ArrayList;
// I used ai in this code for formatting to format it because all the formatting was off.
public class Rook extends Piece {
    boolean hasMoved() = false;
    
    public Rook(boolean newColor) {
        super(newColor);
    }
      public ArrayList<int[]> possibleMoves(Square current, Square[][] board){
        ArrayList<int[]> moves = new ArrayList<>();
        int row = current.getRow();
        int col = current.getCol();
  //4 directions in which a rook can move: up, down, left, and right
        int[][] directions={
            {-1,0},   //up
            {1,0},    //down
            {0,-1},   //left
            {0,1},    //right
        };
        for(int [] d: directions){
                 int newRow = row + d[0];
                int newCol = col + d[1];
  //the purpose of the while statement is to keep moving until the edge or you are blocked
                while(newRow >= 0 && newRow < 8 && newCol >= 0 && newCol< 8){
                    Square next=board[newRow][newCol]; //gets square on the chessboard at a specific position and stores it 
                    if(!next.hasPiece()){
                        moves.add(new int[]{newRow,newCol});
                    }
                    else{
                        // if enemy piece, you can capture
                        
                        if (next.getPiece().getColor() != this.getColor()){
                            moves.add(new int[]{newRow, newCol}); // record coordinate as legal move for this piece. 
                            //I used ai to store it because i did not know how to store the captures as possible moves. 
                            //As I did not know if they should be in the same ArrayLiist or not.
                        }
                        //stop scanning in this direction
                        break;
                
            }    //used ai for the below two lines because I did not know how to actually move the rook to other positions
                    newRow += d[0];
                    newCol += d[1];
      }
    }
    return moves;

      }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean b) {
        hasMoved = b;
    }
}    
          




          
