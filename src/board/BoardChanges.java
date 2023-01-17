package src.board;

import src.piece.Piece;

import java.util.ArrayList;
import java.util.LinkedList;

public class BoardChanges {
    /**
     * Boards last change. Used when determining if en passant is possible.
     */
    public static BoardChanges lastEntry;
    /**
     * Lists of taken pieces. Will be useful when implementing the HUV presenting taken pieces.
     */
    public static ArrayList<Piece> erasedPiecesW = new ArrayList<>();
    public static ArrayList<Piece> erasedPiecesB = new ArrayList<>();
    /**
     * Previous location of moved piece.
     */
    public int originLocation;
    /**
     * Piece who has changed its location.
     */
    public Piece movedPiece;
    /**
     * Location after move.
     */
    public int destinationLocation;
    /**
     * Piece who was previously occupying the square.
     */
    public Piece takenPiece;
    /**
     * Linked list which will contain Board Changes objects. It will be needed when creating the HUV
     * feature to go back and forth between moves that happened on the board. Since it is a doubly linked list
     * the runtime will be best for implementing that feature.
     */
    public LinkedList<BoardChanges> moveHistory;

    public BoardChanges(Piece movedPiece, int originLocation, int destinationLocation, Piece takenPiece) {
        this.movedPiece = movedPiece;
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
        this.takenPiece = takenPiece;
        lastEntry = this;
    }

    /**
     * Method returns if the last moved piece was a given piece.
     */
    public static boolean isLastEntryEqualTo(Piece lastPieceMoved) {
        return lastEntry.movedPiece.equals(lastPieceMoved);
    }

}

