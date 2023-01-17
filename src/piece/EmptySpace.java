package src.piece;

import src.board.Board;

import java.util.HashSet;

/**
 * Mostly unimplemented class representing an empty square on the board.
 */

public class EmptySpace implements Piece, Cloneable {
    /**
     * Piece color.
     */

    public char pieceColor = 'e';
    /**
     * Piece standardized value according to chess engines and current software for position analysis.
     * Needed for developing an evaluation feature.
     */

    public int pieceValue = 0;
    /**
     * Piece abbreviation.
     */
    public char pieceAbbreviation = 'E';
    /**
     * Location index of the board square containing the piece.
     */
    public int locationNumber;
    public EmptySpace(int locationNumber) {
        this.locationNumber = locationNumber;
    }

    // Interface methods.
    @Override
    public void move(Board board, int location) {

    }

    @Override
    public void erase() {

    }

    @Override
    public HashSet<Integer> generatePossibleMoves(Board board, HashSet<Integer> allowedMoves) {
        return new HashSet<>();
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

    @Override
    public void changePieceColor() {

    }

    @Override
    public Piece cloneInOppositeColor() throws CloneNotSupportedException {
        return new EmptySpace(locationNumber);
    }

    @Override
    public HashSet<Integer> attacksInAllDirections(Board board) {
        return null;
    }
}
