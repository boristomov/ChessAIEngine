package src.piece;

import src.board.Board;
import src.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;

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
        if(pieceColor == 'W'){
            Board.whitePieces.add(this);
        }else{
            Board.blackPieces.add(this);
        }
    }
    @Override
    public void move(Board board, int location) {

    }

    @Override
    public void erase() {

    }

    @Override
    public HashSet<Integer> generatePossibleMoves(Board board, HashSet<Integer> allowedMoves) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.addAll(optionsToMoveN(board));
        possibleDestinations.addAll(optionsToMoveS(board));
        possibleDestinations.addAll(optionsToMoveW(board));
        possibleDestinations.addAll(optionsToMoveE(board));
        if(!allowedMoves.isEmpty()) {
            HashSet<Integer> clone = (HashSet<Integer>) possibleDestinations.clone();
            for (int i : clone) {
                if (!allowedMoves.contains(i)) {
                    possibleDestinations.remove(i);
                }
            }
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveN(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceN = board.getAdjacentPieceN(locationNumber);
        if(adjacentPieceN == null){
            return possibleDestinations;
        }
        while(adjacentPieceN.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceN.locationNumber());
            adjacentPieceN = board.getAdjacentPieceN(adjacentPieceN.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceN == null){
                return possibleDestinations;
            }
        }
        if(adjacentPieceN.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceN.locationNumber());
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveS(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceS = board.getAdjacentPieceS(locationNumber);
        if(adjacentPieceS == null){
            return possibleDestinations;
        }
        while(adjacentPieceS.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceS.locationNumber());
            adjacentPieceS = board.getAdjacentPieceS(adjacentPieceS.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceS == null){
                return possibleDestinations;
            }
        }
        if(adjacentPieceS.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceS.locationNumber());
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveW(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceW = board.getAdjacentPieceW(locationNumber);
        if(adjacentPieceW == null){
            return possibleDestinations;
        }
        int selectedPieceRank = Board.getPieceRank(adjacentPieceW.locationNumber());
        if(Board.getPieceRank(locationNumber) != selectedPieceRank) {
            return possibleDestinations;
        }
        while(adjacentPieceW.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceW.locationNumber());
            selectedPieceRank = Board.getPieceRank(locationNumber);
            adjacentPieceW = board.getAdjacentPieceW(adjacentPieceW.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceW == null || Board.getPieceRank(adjacentPieceW.locationNumber()) != selectedPieceRank){
                return possibleDestinations;
            }
        }
        if(adjacentPieceW.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceW.locationNumber());
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveE(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceE = board.getAdjacentPieceE(locationNumber);
        if(adjacentPieceE == null){
            return possibleDestinations;
        }
        int selectedPieceRank = Board.getPieceRank(adjacentPieceE.locationNumber());
        if(Board.getPieceRank(locationNumber) != selectedPieceRank) {
            return possibleDestinations;
        }
        while(adjacentPieceE.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceE.locationNumber());
            adjacentPieceE = board.getAdjacentPieceE(adjacentPieceE.locationNumber()); //looks for the adjacent square to the one just checked.
            selectedPieceRank = Board.getPieceRank(locationNumber);
            if(adjacentPieceE == null || Board.getPieceRank(adjacentPieceE.locationNumber()) != selectedPieceRank){
                return possibleDestinations;
            }
        }
        if(adjacentPieceE.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceE.locationNumber());
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

}
