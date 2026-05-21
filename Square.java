/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;


public class Square {
    
    private boolean hasPiece;
    private Piece p;
    
    //Creates a square with no pieces on it
    public Square(){
        hasPiece = false;
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
