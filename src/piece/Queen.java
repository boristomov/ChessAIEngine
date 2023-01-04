package src.piece;

import src.board.Board;
import src.board.BoardChanges;

import java.util.ArrayList;
import java.util.HashSet;

public class Queen implements Piece {
    public char pieceColor;
    public String PieceImage;
    public int pieceValue = 9;
    public char pieceAbbreviation = 'Q';
    public int locationNumber;

    public Queen(char pieceColor, int locationNumber) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Queen.png";
        this.locationNumber = locationNumber;// if taken maybe -1
        if(pieceColor == 'W'){
            Board.whitePieces.add(this);
        }else{
            Board.blackPieces.add(this);
        }
    }

    @Override
    public void move(Board board, int newLocation) {
        Piece pieceAtDesiredLocation = Board.board[newLocation];
        pieceAtDesiredLocation.erase();
        Board.board[newLocation] = this;
        Board.board[locationNumber] = new EmptySpace(locationNumber);
        locationNumber = newLocation;
    }

    @Override
    public void erase() {
        locationNumber = (pieceColor == 'W') ? -1: -2;
        BoardChanges.erasedPieces.add(this);
    }

    @Override
    public HashSet<Integer> generatePossibleMoves(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.addAll(optionsToMoveN(board));
        possibleDestinations.addAll(optionsToMoveS(board));
        possibleDestinations.addAll(optionsToMoveW(board));
        possibleDestinations.addAll(optionsToMoveE(board));
        possibleDestinations.addAll(optionsToMoveNW(board));
        possibleDestinations.addAll(optionsToMoveNE(board));
        possibleDestinations.addAll(optionsToMoveSW(board));
        possibleDestinations.addAll(optionsToMoveSE(board));
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
        while(adjacentPieceW.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceW.locationNumber());
            int selectedPieceRank = Board.getPieceRank(locationNumber);
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
        while(adjacentPieceE.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceE.locationNumber());
            adjacentPieceE = board.getAdjacentPieceE(adjacentPieceE.locationNumber()); //looks for the adjacent square to the one just checked.
            int selectedPieceRank = Board.getPieceRank(locationNumber);
            if(adjacentPieceE == null || Board.getPieceRank(adjacentPieceE.locationNumber()) != selectedPieceRank){
                return possibleDestinations;
            }
        }
        if(adjacentPieceE.pieceColor() != pieceColor){
            possibleDestinations.add(adjacentPieceE.locationNumber());
        }
        return possibleDestinations;
    }
    public HashSet<Integer> optionsToMoveNW(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceNW = board.getAdjacentPieceNW(locationNumber);
        if(adjacentPieceNW == null){
            return possibleDestinations;
        }
        while(adjacentPieceNW.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceNW.locationNumber());
            int currentAdjPieceFile = Board.getPieceFile(adjacentPieceNW.locationNumber());
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
        while(adjacentPieceNE.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceNE.locationNumber());
            int currentAdjPieceFile = Board.getPieceFile(adjacentPieceNE.locationNumber());
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
        while(adjacentPieceSW.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceSW.locationNumber());
            int currentAdjPieceFile = Board.getPieceFile(adjacentPieceSW.locationNumber());
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
        while(adjacentPieceSE.getClass().equals(EmptySpace.class)){
            possibleDestinations.add(adjacentPieceSE.locationNumber());
            int currentAdjPieceFile = Board.getPieceFile(adjacentPieceSE.locationNumber());
            adjacentPieceSE = board.getAdjacentPieceSE(adjacentPieceSE.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceSE == null || Board.getPieceFile(adjacentPieceSE.locationNumber()) - 1 != currentAdjPieceFile){
                return possibleDestinations;
            }
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
    public char pieceColor() {
        return pieceColor;
    }

    @Override
    public int locationNumber() {
        return locationNumber;
    }
}
