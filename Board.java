package chess;

public class Board {
    
    private Square[][] board = new Square[8][8];
    
    // Initializes the starting board position
    public Board(Player p1, Player p2){
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                board[i][j] = new Square(i, j);
            }
        }
        
        for (int i = 0; i < 8; i++){
            
            board[1][i].addPiece((Piece) (new Pawn(p1)));
            board[6][i].addPiece((Piece) (new Pawn(p2)));
        }
    }
    public boolean checkIfKingInCheck(boolean color){
        int kingRowOfColor = -1;
        int kingColOfColor = -1;
        ArrayList<int[]> placesOpposingPlayerCovers = new ArrayList<>();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (!board[i][j].empty){
                    int[] toAdd = new int[2];
                    toAdd[0] = i;
                    toAdd[1] = j;
                    if (board[i][j].getPiece().getColor()){
                        kingRowOfColor = i;
                        kingRowOfColor = j;
                    }
                    placesOpposingPlayerCovers.add(board[i][j].possibleMoves());
                }
            }
        }
        for (int[] arr : placesOpposingPlayerCovers){
            if (arr[0] == kingRowOfColor && arr[1] == kingColOfColor){
                return true;
            }
        }
        return false;
        
    }
}
