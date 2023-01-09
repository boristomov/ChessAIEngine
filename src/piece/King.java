package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;

import java.util.HashSet;

public class King implements Piece, Cloneable{

    public char pieceColor;
    public static boolean isMoved;
    public String PieceImage;
    public char pieceAbbreviation = 'K';
    public int pieceValue = 1000;
    public int locationNumber;
    public King(char pieceColor, int locationNumber){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "King.png";
        this.locationNumber = locationNumber;
        if(pieceColor == 'W'){
            Board.whitePieces.add(this);
            AttacksOnKing.WkingLocation = locationNumber;
        }else{
            Board.blackPieces.add(this);
            AttacksOnKing.BkingLocation = locationNumber;
        }
    }
    @Override
    public void move(Board board, int location) {
    //update king location in attacksonking
        Piece pieceAtDesiredLocation = Board.board[location];
        pieceAtDesiredLocation.erase();
        Board.board[location] = this;
        Board.board[locationNumber] = new EmptySpace(locationNumber);
        locationNumber = location;
        if(pieceColor == 'W'){
            AttacksOnKing.WkingLocation = locationNumber;
        }
        else{
            AttacksOnKing.BkingLocation = locationNumber;
        }
//        AttacksOnKing.checkingPieces.clear();
    }

    @Override
    public void erase() {

    }

    @Override
    public HashSet<Integer> generatePossibleMoves(Board board, HashSet<Integer> restrictedMoves) throws CloneNotSupportedException {
        HashSet<Integer> possibleDestinations = new HashSet<>();

        optionMoveN(board, possibleDestinations);
        optionMoveS(board, possibleDestinations);
        optionMoveW(board, possibleDestinations);
        optionMoveE(board, possibleDestinations);
        optionMoveNW(board, possibleDestinations);
        optionMoveNE(board, possibleDestinations);
        optionMoveSW(board, possibleDestinations);
        optionMoveSE(board, possibleDestinations);
        //those allowed moves are different from the ones at the other pieces. These are as a result of the check.
//        if(!restrictedMoves.isEmpty()) {
//            HashSet<Integer> clone = (HashSet<Integer>) restrictedMoves.clone();
//            for (int i : clone) {
//                if (possibleDestinations.contains(i)) {
//                    possibleDestinations.remove(i);
//                }
//            }
//        }
//        else{
//            return possibleDestinations;
//        }

        return possibleDestinations;
    }
    private void optionMoveN(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceN(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionMoveS(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceS(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionMoveW(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceW(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionMoveE(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceE(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionMoveNW(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceNW(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionMoveNE(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceNE(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionMoveSW(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceSW(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionMoveSE(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceSE(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        Piece clone = adjacentPiece.cloneInOppositeColor();
        // and is not guarded by anything
        if((adjacentPiece.getClass().equals(EmptySpace.class) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber()))) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor
                && !AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber())){
            board.replace(clone, adjacentPiece);
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
    }
    private void optionsToCastleQueenSide(Board board, HashSet<Integer> possibleDestinations){
        //king is not in check and there are no pieces intercepting its move when castling;
    }
    @Override
    public String pieceImage() {
        return PieceImage;
    }
    //KING PINNING.

    // returns the piece who is pinning;
    // after discovering an opposing color piece of a threading class (having encountered only 1 own color piece), goes back and calls a function which adds
    // the pinned piece to AttacksOnKing hashmap.


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
