package src.piece;

import src.board.Board;

import java.util.ArrayList;

public interface Piece {

    public void move(Board board);
    public void erase();//if taken -> location -1 (-2 for black) (or find a way not to store in memory) or maybe -1 will direct it to the HUV bar for taken pieces.

    public String pieceImage();

    public char pieceAbbreviation();
    public int locationNumber();
    public char pieceColor();
    public ArrayList<Integer> generatePossibleMoves(Board board);

}
