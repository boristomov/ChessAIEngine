package src.piece;

import java.util.ArrayList;
import src.board.Board;


public class Pawn implements Piece{
    public char pieceColor;

    public String PieceImage;
    public int locationNumber;


    public Pawn(char pieceColor, int locationNumber){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Pawn.png";
        this.locationNumber = locationNumber;
    }

    public int pieceValue = 1;

    public char pieceAbbreviation = 'P';

    @Override
    public void move(Board board) {

    }

    @Override
    public void erase() {

    }

    @Override
    public ArrayList<Integer> generatePossibleMoves(Board board) {
        return null;
    }
    @Override
    public String pieceImage() {
        return PieceImage;
    }

    @Override
    public char pieceAbbreviation() {
        return pieceAbbreviation;
    }
    @Override
    public int locationNumber() {
        return locationNumber;
    }
    @Override
    public char pieceColor() {
        return pieceColor;
    }

}
