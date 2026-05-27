public class driver{

    public static void main(String[] args){
        System.out.println("Hello");
           board[3][3].addPiece(rook);

        // get possible moves
        ArrayList<int[]> moves =
                rook.possibleMoves(board[3][3], board);

        // print moves
        for (int[] move : moves) {
            System.out.println("(" + move[0] + ", " + move[1] + ")");
        }
    }
}
