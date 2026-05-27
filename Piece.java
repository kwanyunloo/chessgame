package chess;

public class Piece {
    
    Player p;
    boolean color;
    public Piece(Player name) {
        p = name;
    } 
    //should we insert a move method here? I was thinkg we do it here and hten we override it in all of the classes  
    abstract public void movePiece(Square s1, Square s2);
    //see if i did it right
    public boolean getColor(){
        return color;
    }
    private boolean checkPin(int diagonalsChecked, int rowsChecked)
    {   
        int diagonals = diagonalsChecked;
        int rows = rowsChecked;
        if (diagonalsChecked == 2 && rowsChecked == 2)
        {
            return false;
        } else {
            if (diagonals < 2)
            {
                if (diagonals == 0)
                {
                    while (Square s1
                    
            }
            checkPin(diagonals, rows);
        }
    }
}
