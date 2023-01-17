package src.piece;

import src.board.Board;

import java.util.HashSet;

public interface Piece {
    /**
     * Method for moving a piece.
     */
    void move(Board board, int location);

    /**
     * Method which removes a taken piece from the sets of white/black pieces.
     */
    void erase();

    /**
     * Method which returns the file location of the piece's visual representation of the board (PNG file).
     */
    String pieceImage();

    /**
     * Method which returns the piece's abbreviation. Used mainly when parsing FEN strings.
     */
    char pieceAbbreviation();

    /**
     * Method which returns the piece's location on the board.
     */
    int locationNumber();

    /**
     * Method which returns the piece's color.
     */
    char pieceColor();

    /**
     * Helper method used when cloning an enemy piece as a piece from the opposite color. After calling the replace() method
     * replacing the enemy piece with its friendly clone, we are able to determine whether that piece is
     * targeted by the enemy pieces. In other words, we are utilizing this cloning function to check whether a
     * given piece is guarded by the enemy pieces on the board. If not, we can allow the king to capture it and
     * avoid pseudo-illegal moves.
     */
    Piece cloneInOppositeColor() throws CloneNotSupportedException;

    /**
     * Helper function which changes the piece's color.
     */
    void changePieceColor();

    /**
     * Function which takes consideration of the current underlying move restrictions and generates
     * an integer set of possible allowed squares for that piece to move. This method is also utilized
     * to visualise the highlighting of squares on the board.
     */
    HashSet<Integer> generatePossibleMoves(Board board, HashSet<Integer> allowedMoves) throws CloneNotSupportedException;

    /**
     * Method which returns a set of square locations where the piece is able to reach. This is helpful when
     * generating the current set of attacked squares from a position and also prevents the kings from
     * coming less than 1 square apart, which is pseudo-illegal.
     */
    HashSet<Integer> attacksInAllDirections(Board board);

}
