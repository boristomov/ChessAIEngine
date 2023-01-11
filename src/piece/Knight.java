package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;
import src.board.BoardChanges;
import src.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;

public class Knight implements Piece, Cloneable{
    public char pieceColor;

    public String PieceImage;
    public char pieceAbbreviation;
    public int pieceValue = 3;
    public int locationNumber;
    public Knight(char pieceColor, int locationNumber){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Knight.png";
        this.locationNumber = locationNumber;
        if(pieceColor == 'W'){
            Board.whitePieces.add(this);
            this.pieceAbbreviation = 'N';
        }else{
            Board.blackPieces.add(this);
            this.pieceAbbreviation = 'n';
        }
    }
    public Knight(char pieceColor, int locationNumber, boolean AddToPieces){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Knight.png";
        this.locationNumber = locationNumber;
        // no adding to piece lists
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
        optionsToMoveNW1(board, possibleDestinations);
        optionsToMoveNW2(board, possibleDestinations);
        optionsToMoveNE1(board, possibleDestinations);
        optionsToMoveNE2(board, possibleDestinations);
        optionsToMoveSW1(board, possibleDestinations);
        optionsToMoveSW2(board, possibleDestinations);
        optionsToMoveSE1(board, possibleDestinations);
        optionsToMoveSE2(board, possibleDestinations);
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
    public void optionsToMoveNW1(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber + Board.Size - 2;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation > 63 || selectedPieceRank + 1 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }

    }
    public void optionsToMoveNW2(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber + 2 * Board.Size -1;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation > 63 || selectedPieceRank + 2 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }
    }
    public void optionsToMoveNE1(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber + Board.Size + 2;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation > 63 || selectedPieceRank + 1 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }
    }
    public void optionsToMoveNE2(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber + 2 * Board.Size + 1;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation > 63 || selectedPieceRank + 2 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }
    }
    public void optionsToMoveSW1(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber - Board.Size - 2;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation < 0 || selectedPieceRank - 1 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }
    }
    public void optionsToMoveSW2(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber - 2 * Board.Size - 1;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation < 0 || selectedPieceRank - 2 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }
    }
    public void optionsToMoveSE1(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber - Board.Size + 2;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation < 0 || selectedPieceRank - 1 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }
    }
    public void optionsToMoveSE2(Board board, HashSet<Integer> possibleDestinations){
        int newLocation = locationNumber - 2 * Board.Size + 1;
        int selectedPieceRank = Board.getPieceRank(locationNumber);
        if(newLocation < 0 || selectedPieceRank - 2 != Board.getPieceRank(newLocation) || Board.board[newLocation].pieceColor() == pieceColor){
            return;
        }
        else{
            possibleDestinations.add(newLocation);
        }
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

    @Override
    public HashSet<Integer> attacksInAllDirections(Board board) {
        return null;
    }
}
