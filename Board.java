// used AI to fix small errors

import java.util.ArrayList;

public class Board {
    
    private Square[][] board = new Square[8][8];
    private boolean currentTurn;
    public boolean move(int startRow, int startCol, int endRow, int endCol){
        Piece piece = board[startRow][startCol].getPiece();
        if (piece == null || piece.getColor() != currentTurn){ // check if someone trying to move an opposing player's piece
            System.out.println("Invalid Move: This is not your piece");
            return false;
        }
        
        ArrayList<int[]> possibleMoves = piece.possibleMoves(board[startRow][startCol], board);  //get all possible moves for selected piece.
       //check if the desired move is in the legal move list
        for (int[] possibleMove : possibleMoves){
            if (possibleMove[0] == endRow && possibleMove[1] == endCol){
                //castling: king can't be in check or pass through an attacked square
                if (piece instanceof King && Math.abs(startCol - endCol) == 2) {
                    int midCol = (startCol + endCol) / 2;
                    if (isKingInCheck(currentTurn) || isSquareAttacked(startRow, midCol, currentTurn)) {
                        System.out.println("Invalid move: cannot castle out of or through check");
                        return false;
                    }
                }
                //save piece on end square in case of capture
                Piece capturedPiece = null;
                if (board[endRow][endCol].hasPiece()) {
                    capturedPiece = board[endRow][endCol].getPiece();
                }
                //temporarily execute move first
                board[endRow][endCol].addPiece(piece);
                board[startRow][startCol].setEmpty();

                if (isKingInCheck(currentTurn)){ //illegal move - undo move
                    board[startRow][startCol].addPiece(piece);
                    
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

                //if the king moved two squares it castled - move the rook too
                if (piece instanceof King && Math.abs(startCol - endCol) == 2) {
                    if (endCol == 6) {
                        board[startRow][5].addPiece(board[startRow][7].getPiece());
                        board[startRow][7].setEmpty();
                    } else {
                        board[startRow][3].addPiece(board[startRow][0].getPiece());
                        board[startRow][0].setEmpty();
                    }
                }

                //check for en passant or pawn double move
                if (piece instanceof Pawn) {
                    Pawn movedPawn = (Pawn) piece;
                    
                    //check for en passant - pawn moves diagonal but capture square is empty
                    if (startCol != endCol && capturedPiece == null) {
                        board[startRow][endCol].setEmpty(); //deletes the captured pawn 
                    }
                    
                    //check if pawn double move
                    if (Math.abs(startRow - endRow) == 2) {
                        movedPawn.setJustMovedTwo(true);
                    }
                }

                //pawn promotion: reached the far rank -> promote to queen
                if (piece instanceof Pawn && (endRow == 0 || endRow == 7)) {
                    board[endRow][endCol].addPiece(new Queen(piece.getColor()));
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
            if (isSquareAttacked(row, 5, color) || isSquareAttacked(row, 6, color)) {
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

            Piece rookPiece = board[row][0].getPiece();

            //verify rook exists
            if (!(rookPiece instanceof Rook)) {
                return false;
            }

            //rook can't have moved
            if (rookPiece.hasMoved()) {
                return false;
            }

            //squares between rook and king must be empty

            if (board[row][1].hasPiece() || board[row][2].hasPiece() || board[row][3].hasPiece()) {
            
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

    public Board(Board other){
        this.currentTurn = other.currentTurn;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
                if (other.board[i][j].hasPiece()) {
                    board[i][j].addPiece(copyPiece(other.board[i][j].getPiece()));
                }
            }
        }
    }

    private Piece copyPiece(Piece p){
        Piece n;
        if (p instanceof Pawn) n = new Pawn(p.getColor());
        else if (p instanceof Rook) n = new Rook(p.getColor());
        else if (p instanceof Knight) n = new Knight(p.getColor());
        else if (p instanceof Bishop) n = new Bishop(p.getColor());
        else if (p instanceof Queen) n = new Queen(p.getColor());
        else n = new King(p.getColor());
        n.setHasMoved(p.hasMoved());
        if (p instanceof Pawn) ((Pawn) n).setJustMovedTwo(((Pawn) p).justMovedTwo());
        return n;
    }

    public boolean playerTurn(){
        return currentTurn;
    }

    public boolean isGameOver(){ return !hasAnyLegalMove(currentTurn); }
    public boolean isCheckmate(boolean color){ return isKingInCheck(color) && !hasAnyLegalMove(color); }
    public boolean isStalemate(boolean color){ return !isKingInCheck(color) && !hasAnyLegalMove(color); }

    //true if color has a move that doesn't leave its own king in check
    public boolean hasAnyLegalMove(boolean color){
        for (int i = 0; i < 8; i++) for (int j = 0; j < 8; j++) {
            Square from = board[i][j];
            if (!from.hasPiece() || from.getPiece().getColor() != color) continue;
            Piece piece = from.getPiece();
            for (int[] m : piece.possibleMoves(from, board)) {
                if (piece instanceof King && Math.abs(m[1] - j) == 2) continue; //castling can't be the only escape
                Square to = board[m[0]][m[1]];
                Piece captured = to.getPiece();   //null if the square is empty
                to.addPiece(piece); from.setEmpty();
                boolean safe = !isKingInCheck(color);
                from.addPiece(piece);
                if (captured != null) to.addPiece(captured); else to.setEmpty();
                if (safe) return true;
            }
        }
        return false;
    }
    
    public boolean isSquareAttacked(int targetRow, int targetCol, boolean defendingColor) {
        //scan the entire board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square currentSquare = board[i][j];

                // look for squares that have pieces belonging to opponent
                if (currentSquare.hasPiece() && currentSquare.getPiece().getColor() != defendingColor) {
                    Piece enemyPiece = currentSquare.getPiece();

                    // a pawn attacks only its two forward diagonals, not the squares it moves to
                    if (enemyPiece instanceof Pawn) {
                        int dir = enemyPiece.getColor() ? 1 : -1;
                        if (i + dir == targetRow && (j + 1 == targetCol || j - 1 == targetCol)) {
                            return true;
                        }
                        continue;
                    }

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
        for (int row = 7; row >= 0; row--){ // print in opposite direction because board is 0 based from the bottom left corner
            for (int col = 0; col < 8; col++){
                if (!board[row][col].hasPiece()){
                    System.out.print(". ");
                }
                else {
                    //uppercase for white, lowercase for black
                    Piece p = board[row][col].getPiece();
                    String s = "";
                    if (p instanceof King) s = "K";
                    else if (p instanceof Bishop) s = "B";
                    else if (p instanceof Pawn) s = "P";
                    else if (p instanceof Queen) s = "Q";
                    else if (p instanceof Rook) s = "R";
                    else if (p instanceof Knight) s = "N";
                    System.out.print((p.getColor() ? s : s.toLowerCase()) + " ");
                }
                
            }
            System.out.println();
        }
        return "";
    }
}
