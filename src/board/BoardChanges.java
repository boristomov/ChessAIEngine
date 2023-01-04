package src.board;

import java.util.ArrayList;
import java.util.LinkedList;
import src.piece.*;

public class BoardChanges {

    public int originLocation;
    public Piece movedPiece;
    public int destinationLocation;
    public static BoardChanges lastEntry;
    public Piece takenPiece;

    public BoardChanges(Piece movedPiece, int originLocation, int destinationLocation, Piece takenPiece){
        this.movedPiece = movedPiece;
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
        this.takenPiece = takenPiece;
//        erasedPieces.add(takenPiece);
        lastEntry = this;
    }

    public LinkedList<BoardChanges> moveHistory;
    public static ArrayList<Piece> erasedPieces;
    //when confirming a move
    public static boolean isLastEntryEqualTo(Piece lastPieceMoved){
        return lastEntry.movedPiece.equals(lastPieceMoved);
    }

}

