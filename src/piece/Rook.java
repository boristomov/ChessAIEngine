package src.piece;

import src.board.Board;
import src.piece.Piece;

import java.util.ArrayList;

public class Rook implements Piece{
    public char pieceColor;
    public String PieceImage;
    public char pieceAbbreviation = 'R';
    public int pieceValue = 5;
    public int locationNumber;
    public Rook(char pieceColor, int locationNumber){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Rook.png";
        this.locationNumber = locationNumber;
    }
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
