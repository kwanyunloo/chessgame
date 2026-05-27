package chess;

public class Piece {
    
    Player p;
    boolean isWhite;

    abstract public void movePiece(Square s1, Square s2);
    
    public boolean getColor(){
        return color;
    }
    
}
