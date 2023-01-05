package src.board;

import java.util.HashMap;
import java.util.HashSet;
import src.piece.*;

public class AttacksOnKing {

    public static HashMap<Piece, HashSet<Integer>> pPiecesAndAllowedMoves;

    public void checkForPins(Board board, int kingLocation){
//        Board.board[kingLocation]
    }
    public static HashSet<Integer> dangerScopeN(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while(moveLocation <= 63 && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            moveLocation += 8;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeS(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while(moveLocation >= 0 && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            moveLocation -= 8;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeE(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 1;
        while(moveLocation <= 63 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceRank = Board.getPieceRank(moveLocation);
            moveLocation += 1;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeW(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 1;
        while(moveLocation >= 0 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceRank = Board.getPieceRank(moveLocation);
            moveLocation -= 1;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeNW(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 7;
        while(moveLocation <= 63 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation += 7;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeNE(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 9;
        while(moveLocation <= 63 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation += 9;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeSW(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 9;
        while(moveLocation >= 0 && Board.getPieceFile(moveLocation) + 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation -= 9;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeSE(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        possibleDestinations.add(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 7;
        while(moveLocation >= 0 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation -= 7;
        }
        return possibleDestinations;
    }
}
