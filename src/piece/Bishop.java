package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;
import src.board.BoardChanges;
import src.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;

public class Bishop implements Piece , Cloneable {
    public char pieceColor;

    public String PieceImage;
    public char pieceAbbreviation = 'B';
    public int pieceValue = 3;
    public int locationNumber;
    public Bishop(char pieceColor, int locationNumber){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Bishop.png";
        this.locationNumber = locationNumber;
        if(pieceColor == 'W'){
            Board.whitePieces.add(this);
        }else{
            Board.blackPieces.add(this);
        }
    }
    @Override
    public void move(Board board, int location) {
        Piece pieceAtDesiredLocation = Board.board[location];
        pieceAtDesiredLocation.erase();
        Board.board[location] = this;
        Board.board[locationNumber] = new EmptySpace(locationNumber);
        locationNumber = location;
    }

    @Override
    public void erase() {
        HashSet<Piece> setOfSameColorPieces = (pieceColor == 'W')? Board.whitePieces: Board.blackPieces;
        if(setOfSameColorPieces.contains(this)){
            setOfSameColorPieces.remove(this);

        }
        if(pieceColor == 'W') {
            BoardChanges.erasedPiecesW.add(this);
        }else{
            BoardChanges.erasedPiecesB.add(this);
        }
    }

    @Override
    public HashSet<Integer> generatePossibleMoves(Board board, HashSet<Integer> allowedMoves) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.addAll(optionsToMoveNW(board));
        possibleDestinations.addAll(optionsToMoveNE(board));
        possibleDestinations.addAll(optionsToMoveSW(board));
        possibleDestinations.addAll(optionsToMoveSE(board));
        if(!allowedMoves.isEmpty()) {
            HashSet<Integer> clone = (HashSet<Integer>) possibleDestinations.clone();
            for (int i : clone) {
                if (!allowedMoves.contains(i)) {
                    possibleDestinations.remove(i);
                }
            }
        }
        else if(AttacksOnKing.pPiecesAndAllowedMoves.containsKey(this)){
            return allowedMoves;
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveNW(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceNW = board.getAdjacentPieceNW(locationNumber);
        if(adjacentPieceNW == null){
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceNW.locationNumber());
        if(Board.getPieceFile(locationNumber) - 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while(adjacentPieceNW.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceNW.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceNW.locationNumber());
            adjacentPieceNW = board.getAdjacentPieceNW(adjacentPieceNW.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceNW == null || Board.getPieceFile(adjacentPieceNW.locationNumber()) + 1 != currentAdjPieceFile){
                return possibleDestinations;
            }
        }
        if(adjacentPieceNW.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceNW.locationNumber());
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveNE(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceNE = board.getAdjacentPieceNE(locationNumber);
        if(adjacentPieceNE == null){
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceNE.locationNumber());
        if(Board.getPieceFile(locationNumber) + 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while(adjacentPieceNE.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceNE.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceNE.locationNumber());
            adjacentPieceNE = board.getAdjacentPieceNE(adjacentPieceNE.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceNE == null || Board.getPieceFile(adjacentPieceNE.locationNumber()) - 1 != currentAdjPieceFile){
                return possibleDestinations;
            }
        }
        if(adjacentPieceNE.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceNE.locationNumber());
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveSW(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceSW = board.getAdjacentPieceSW(locationNumber);
        if(adjacentPieceSW == null){
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceSW.locationNumber());
        if(Board.getPieceFile(locationNumber) - 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while(adjacentPieceSW.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceSW.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceSW.locationNumber());
            adjacentPieceSW = board.getAdjacentPieceSW(adjacentPieceSW.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceSW == null || Board.getPieceFile(adjacentPieceSW.locationNumber()) + 1 != currentAdjPieceFile){
                return possibleDestinations;
            }
        }
        if(adjacentPieceSW.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceSW.locationNumber());
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveSE(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceSE = board.getAdjacentPieceSE(locationNumber);
        if(adjacentPieceSE == null){
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceSE.locationNumber());
        if(Board.getPieceFile(locationNumber) + 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while(adjacentPieceSE.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceSE.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceSE.locationNumber());
            adjacentPieceSE = board.getAdjacentPieceSE(adjacentPieceSE.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceSE == null || Board.getPieceFile(adjacentPieceSE.locationNumber()) - 1 != currentAdjPieceFile){
                return possibleDestinations;
            }
            possibleDestinations.add(adjacentPieceSE.locationNumber());
        }
        if(adjacentPieceSE.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceSE.locationNumber());
        }
        return possibleDestinations;
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
    @Override
    public Piece cloneInOppositeColor() throws CloneNotSupportedException {
        Piece clone = (Piece) this.clone();
        clone.changePieceColor();
        return clone;
    }

    @Override
    public void changePieceColor() {
        pieceColor = Board.getOppositeColorChar(pieceColor);
    }

}
