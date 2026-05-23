package chess;

public class Piece {
    
    Player p;
    
    public Piece(Player name) {
        p = name;
    } 
    //should we insert a move method here? I was thinkg we do it here and hten we override it in all of the classes  
    public void movePiece(Square s1, Square s2) {
        
    }
}
