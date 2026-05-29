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
    
    public boolean isSquareAttacked(int targetRow, int targetCol, boolean defendingColor) {
        //scan the entire board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square currentSquare = board[i][j];

                // look for squares that have pieces belonging to opponent
                if (currentSquare.hasPiece() && currentSquare.getPiece().getColor() != defendingColor) {
                    Piece enemyPiece = currentSquare.getPiece();

                    // get all the possible moves of that piece
                    ArrayList<int[]> enemyMoves = enemyPiece.possibleMoves(currentSquare, board);

                    // check if those moves attack this square
                    for (int[] move : enemyMoves) {
                        if (move[0] == targetRow && move[1] == targetCol) {
                            return true; 
                        }
                    }
                }
            }
        }
        
        // checked all pieces and none hit this square
        return false; 
    }

    public boolean isKingInCheck(boolean kingColor) {
        int kingRow = -1;
        int kingCol = -1;
        //find the king's coordinates
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].hasPiece()) {
                    Piece p = board[i][j].getPiece();
                    if (p instanceof King && p.getColor() == kingColor) {
                        kingRow = i;
                        kingCol = j;
                        break;
                    }
                }
            }
        }
        
        //if the king's square is being attacked, it is in check
        return isSquareAttacked(kingRow, kingCol, kingColor);
    }

    
    public String toString(){
        for (int row = 7; row >= 0; i--){ // print in opposite direction because board is 0 based from the bottom left corner
            for (int col = 0; col < 8; col++){
                if (!board[row][col].hasPiece()){
                    System.out.println(". ");
                }
                else {
                    //WE NEED TO DIFFERENTIATE BETWEEN WHITE AND BLACK PIECE - maybe uppercase for white, lowercase for black?
                    Piece p = board[row][col].getPiece();
                    if (board[row][col] instanceOf King){
                        System.out.print("K ");
                    }
                    else if (board[row][col] instanceOf Bishop){
                        System.out.print("B ");
                    }
                    else if (board[row][col] instanceOf Pawn){
                        System.out.print("P ");
                    }
                    else if (board[row][col] instanceOf Queen){
                        System.out.print("Q ");
                    }
                    else if (board[row][col] instanceOf Rook){
                        System.out.print("R ");
                    }
                    else if (board[row][col] instanceOf Knight){
                        System.out.print("N ");
                    }
                }
                
            }
            System.out.println();
        }
    }
}
