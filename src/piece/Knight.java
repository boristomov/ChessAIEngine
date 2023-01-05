package src.piece;

import src.board.Board;
import src.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;

public class Knight implements Piece{
    public char pieceColor;

    public String PieceImage;
    public char pieceAbbreviation = 'N';
    public int pieceValue = 3;
    public int locationNumber;
    public Knight(char pieceColor, int locationNumber){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Knight.png";
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
        optionsToMoveNW1(board, possibleDestinations);
        optionsToMoveNW2(board, possibleDestinations);
        optionsToMoveNE1(board, possibleDestinations);
        optionsToMoveNE2(board, possibleDestinations);
        optionsToMoveSW1(board, possibleDestinations);
        optionsToMoveSW2(board, possibleDestinations);
        optionsToMoveSE1(board, possibleDestinations);
        optionsToMoveSE2(board, possibleDestinations);
        if(!allowedMoves.isEmpty()) {
            for (int i : possibleDestinations) {
                if (!allowedMoves.contains(i)) {
                    possibleDestinations.remove(i);
                }
            }
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
}
