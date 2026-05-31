package chess;

public class Board {
    
    private Square[][] board = new Square[8][8];
    private boolean currentTurn;
    public boolean move(int startRow, int startCol, int endRow, int endCol){
        if (piece[startRow][startCol].getColor() != currentTurn){ // someone trying to move an opposing player's piece
            System.out.println("Invalid Move: This is not your piece");
            return false;
        }
        Piece piece = board[startRow][startCol];
        ArrayList<int[]> possibleMoves = piece.possibleMoves(board[startRow][startCol], board);
        for (int[] possibleMove : possibleMoves){
            if (possibleMove[0] == endRow && possibleMove[1] == endCol){
                //save piece on end square in case of capture
                Piece capturedPiece = null;
                if (board[endRow][endCol].hasPiece()) {
                    capturedPiece = board[endRow][endCol].getPiece();
                }
                //temporarily execute move first
                board[endRow][endCol].addPiece(piece);
                board[startRow][startCol].setEmpty();

                if (isKingInCheck(currentTurn)){ //illegal move - undo move
                    board[startRow][startCol].addPiece(pieceToMove);
                    
                    if (capturedPiece != null) {
                        board[endRow][endCol].addPiece(capturedPiece); 
                    } else {
                        board[endRow][endCol].setEmpty(); 
                    }
                    
                    System.out.println("Invalid move: This move will leave your king in check");
                    return false;
                }

                if (piece instanceof Pawn || piece instanceof Rook || piece instanceof King){
                    piece.setHasMoved(true);
                }

                //check for en passant or pawn double move
                if (pieceToMove instanceof Pawn) {
                    Pawn movedPawn = (Pawn) pieceToMove;
                    
                    //check for en passant - pawn moves diagonal but capture square is empty
                    if (startCol != endCol && capturedPiece == null) {
                        board[startRow][endCol].setEmpty(); //deletes the captured pawn 
                    }
                    
                    //check if pawn double move
                    if (Math.abs(startRow - endRow) == 2) {
                        movedPawn.setJustMovedTwo(true);
                    }
                }
                
                return true;
            }
        }
        System.out.println("This is not a valid move.");
        return false;
    }
    public void changeMove(){
        currentTurn = !currentTurn;
        //reset en passant
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].hasPiece()) {
                    Piece p = board[i][j].getPiece();
                    if (p instanceof Pawn && p.getColor() == currentTurn) {
                        ((Pawn) p).setJustMovedTwo(false);
                    }
                }
            }
        }
    }

    
    //castle method
    //color=true for white, color=false for black
    //kingSide =true for kingside castle, kingSide=false for queenside castle
    public boolean castle(boolean color, boolean kingSide){
        int row;
        
        //white pieces start on row 0 and black pieces on row 7
        if(color){
            row=0;
        } else{
            row=7;
        }

        Piece kingPiece = board[row][4].getPiece();

        //make sure king exists
        if (!(kingPiece instanceof King)) {
            return false;
        }

        //king can't have moved previously
        if (kingPiece.hasMoved()) {
            return false;
        }

        //king can't castle while in check
        if (isKingInCheck(color)) {
            return false;
        }

        if (kingSide) {
        //Kingside castle
            //King: e -> g
            //Rook: h -> f

            Piece rookPiece = board[row][7].getPiece();

            //verify rook exists
            if (!(rookPiece instanceof Rook)) {
                return false;
            }

            //rook can't have moved
            if (rookPiece.hasMoved()) {
                return false;
            }

            //squares between rook and king must be empty
            if(board[row][5].hasPiece() || board[row][6].hasPiece()) {
                return false;
            }

            //king can't move through check
            if (isSquareAttacked(row, 5. color) || isSquareAttacked(row, 6, color)) {
                return false;
            }

            //move king
            board[row][6].addPiece(kingPiece);
            board[row][4].setEmpty();

            //move rook
            board[row][5].addPiece(rookPiece);
            board[row][7].setEmpty();
            
            kingPiece.setHasMoved(true);
            rookPiece.setHasMoved(true);

            changeMove();
            return true;    
        }

        else {

            //Queenside castle
            //King: e -> c
            //Rook: a -> d

            piece rookPiece = board[row][0].getPiece();

            //verify rook exists
            if (!(rookPiece instanceof Rook)) {
                return false;
            }

            //rook can't have moved
            if (rookPiece.hasMoved()) {
                return false;
            }

            //squares between rook and king must be empty

            if (board[row][1].hasPiece() || board[row][2].hasPiece() board[row][3].hasPiece()) {
            
                return false;
            }

            //king can't move through check
            if(isSquareAttacked(row, 3, color) || isSquareAttacked(row, 2, color)) {
                return false;
            }

            //move king
            board[row][2].addPiece(kingPiece);
            board[row][4].setEmpty();

            //move rook
            board[row][3].addPiece(rookPiece);
            board[row][0].setEmpty();

            kingPiece.setHasMoved(true);
            rookPiece.setHasMoved(true);

            changeMove();

            return true;
            
        }
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
