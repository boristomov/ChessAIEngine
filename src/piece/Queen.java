package src.piece;

import src.board.Board;

import java.util.ArrayList;

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
    }

    @Override
    public void move(Board board) {

    }

    @Override
    public void erase() {

    }

    @Override
    public ArrayList<Integer> generatePossibleMoves(Board board) {
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveN(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveS(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveW(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveE(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveNW(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveNE(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveSW(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
    public ArrayList<Integer> optionsToMoveSE(Board board){
        ArrayList<Integer> possibleDestinations = new ArrayList<>();
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
