package chess;

public class Board {
    
    private Square[][] board = new Square[8][8];
    
    // Initializes the starting board position
    public Board(Player p1, Player p2){
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                board[i][j] = new Square();
            }
        }
        
        for (int i = 0; i < 8; i++){
            
            board[1][i].addPiece((Piece) (new Pawn(p1)));
            board[6][i].addPiece((Piece) (new Pawn(p2)));
        }
    }
    public boolean checkIfKingInCheck(){
        for (int i = 0; i < board.length; i++){
            for (int j  0)
        }
    }
}
