/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;


public class Square {
    
    private boolean hasPiece;
    private Piece p;
    private int row;
    private int col;
    
    //Creates a square with no pieces on it
    public Square(int x, int y){
        hasPiece = false;
        row = x;
        col = y;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
    
    public boolean hasPiece()
    {
        return hasPiece;
    }
    
    public Piece getPiece()
    {
        return p;
    }
    
    public void setEmpty(){
        p = null;
        hasPiece = false;
    }
    
    public void addPiece(Piece newPiece){
        hasPiece = true;
        p = newPiece;
    }
    
    public String toString()
    {
        return ""; 
    }
}
