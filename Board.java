package chess;

public class Board {
    
    private Square[][] board = new Square[8][8];
    private boolean currentTurn;
    public boolean move(int startRow, int startCol, int endRow, int endCol){
        if (piece[startRow][startCol].getColor() != currentTurn){ // someone trying to move an opposing player's piece
            System.out.println("This is not a valid move.");
            return false;
        }
        Piece piece = board[startRow][startCol];
        ArrayList<int[]> possibleMoves = piece.possibleMoves(board[startRow][startCol], board);
        for (int[] possibleMove : possibleMoves){
            if (possibleMove[0] == endRow && possibleMove[1] == endCol){
                //execute move
                board[endRow][endCol].addPiece(piece);
                board[startRow][startCol].setEmpty();

                if (piece instanceof Rook || piece instanceof King){
                    piece.setHasMoved(true);
                }
                
                return true;
            }
        }
        System.out.println("This is not a valid move.");
        return false;
    }
    public void changeMove(){
        currentTurn = !currentTurn;
    }
    public Board(){
        currentTurn = true;
         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
            }
        }
        // Used AI to load original position
        board[0][0].addPiece(new Rook(true));
        board[0][1].addPiece(new Knight(true));
        board[0][2].addPiece(new Bishop(true));
        board[0][3].addPiece(new Queen(true));
        board[0][4].addPiece(new King(true));
        board[0][5].addPiece(new Bishop(true));
        board[0][6].addPiece(new Knight(true));
        board[0][7].addPiece(new Rook(true));

        for (int i = 0; i < 8; i++) {
            board[1][i].addPiece(new Pawn(true));
        }

        board[7][0].addPiece(new Rook(false));
        board[7][1].addPiece(new Knight(false));
        board[7][2].addPiece(new Bishop(false));
        board[7][3].addPiece(new Queen(false));
        board[7][4].addPiece(new King(false));
        board[7][5].addPiece(new Bishop(false));
        board[7][6].addPiece(new Knight(false));
        board[7][7].addPiece(new Rook(false));

        for (int i = 0; i < 8; i++) {
            board[6][i].addPiece(new Pawn(false));
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
    public String toString(){
        for (int row = 7; row >= 0; i--){ // print in opposite direction because board is 0 based from the bottom left corner
            for (int col = 0; col < 8; col++){
                if (board[row][col] instanceOf King){
                    System.out.print("K ");
                }else if (board[row][col] instanceOf Bishop){
                    System.out.print("B ");
                }else if (board[row][col] instanceOf Pawn){
                    System.out.print("P ");
                }else if (board[row][col] instanceOf Queen){
                    System.out.print("Q ");
                }else if (board[row][col] instanceOf Rook){
                    System.out.print("R ");
                }else if (board[row][col] instanceOf Knight){
                    System.out.print("N ");
                }
            }
            System.out.println();
        }
    }
}
