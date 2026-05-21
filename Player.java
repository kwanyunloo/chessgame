package chess;

public class Player {
    
    private String name;
    private char color;
    
    public Player(String playerName, char playerColor)
    {
        name = playerName;
        color = playerColor;
    }
    
    public void movePiece(Square s1, Square s2){
        Piece toMove = s1.getPiece();
    }

    
}
