package src.piece;

import src.board.Board;

import java.util.ArrayList;
import java.util.HashSet;

public class EmptySpace implements Piece{

    public char pieceColor = 'e';

    public String PieceImage = "";


    public EmptySpace(int locationNumber){
        this.locationNumber = locationNumber;
    }

    public int pieceValue = 0;

    public char pieceAbbreviation = 'E';
    public int locationNumber; //switches location number with the piece who goes on top of it.


    @Override
    public void move(Board board, int location) {

    }

    @Override
    public void erase() {

    }

    @Override
    public HashSet<Integer> generatePossibleMoves(Board board, HashSet<Integer> allowedMoves) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        return possibleDestinations;
    }
    @Override
    public String pieceImage() {
        return null;
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
