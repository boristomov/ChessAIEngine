package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;
import src.board.BoardChanges;

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
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class) && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber()) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
        }
    }
    private void optionMoveS(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceS(locationNumber);

        if(adjacentPiece == null){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class) && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber()) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
        }
    }
    private void optionMoveW(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceW(locationNumber);

        if(adjacentPiece == null || Board.getPieceRank(adjacentPiece.locationNumber()) != Board.getPieceRank(locationNumber)){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class) && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber()) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
        }
    }
    private void optionMoveE(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceE(locationNumber);

        if(adjacentPiece == null || Board.getPieceRank(adjacentPiece.locationNumber()) != Board.getPieceRank(locationNumber)){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class) && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber()) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
        }
    }
    private void optionMoveNW(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceNW(locationNumber);

        if(adjacentPiece == null || Board.getPieceFile(adjacentPiece.locationNumber()) + 1 != Board.getPieceFile(locationNumber)){
            return;
        }
        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class)
                && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber())
                && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
        }
    }
    private void optionMoveNE(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceNE(locationNumber);

        if(adjacentPiece == null || Board.getPieceFile(adjacentPiece.locationNumber()) - 1 != Board.getPieceFile(locationNumber)){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class) && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber()) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
        }
    }
    private void optionMoveSW(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceSW(locationNumber);

        if(adjacentPiece == null || Board.getPieceFile(adjacentPiece.locationNumber()) + 1 != Board.getPieceFile(locationNumber)){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class) && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber()) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
        }
    }
    private void optionMoveSE(Board board, HashSet<Integer> possibleDestinations) throws CloneNotSupportedException {
        Piece adjacentPiece = board.getAdjacentPieceSE(locationNumber);

        if(adjacentPiece == null || Board.getPieceFile(adjacentPiece.locationNumber()) - 1 != Board.getPieceFile(locationNumber)){
            return;
        }

        char oppositeColor = Board.getOppositeColorChar(pieceColor);
        // and is not guarded by anything
        if(adjacentPiece.getClass().equals(EmptySpace.class) && !threatenedSquares(board, this).contains(adjacentPiece.locationNumber()) && !AttacksOnKing.isPieceTargeted(board, pieceColor, adjacentPiece.locationNumber())) {
            possibleDestinations.add(adjacentPiece.locationNumber());
        }
        else if(adjacentPiece.pieceColor() == oppositeColor){
            Piece clone = adjacentPiece.cloneInOppositeColor();
            boolean isPieceGuarded = AttacksOnKing.isPieceTargeted(board.replace(adjacentPiece, clone), pieceColor, adjacentPiece.locationNumber());
            if(isPieceGuarded == false) {
                possibleDestinations.add(adjacentPiece.locationNumber());
            }
            board.replace(clone, adjacentPiece);
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
    public static HashSet<Integer> threatenedSquares(Board board, Piece king){
        HashSet<Integer> interceptionScope = new HashSet<>();

        for(Piece piece: AttacksOnKing.checkingPieces){
            int pieceFile = Board.getPieceFile(piece.locationNumber());
            int pieceRank = Board.getPieceRank(piece.locationNumber());
            int kingFile = Board.getPieceFile(king.locationNumber());
            int kingRank = Board.getPieceRank(king.locationNumber());

            if(pieceFile == Board.getPieceFile(king.locationNumber())){
                if(pieceRank > kingRank){
                    interceptionScope.addAll(AttacksOnKing.arrayScopeS(board, piece));
                }
                else{
                    interceptionScope.addAll(AttacksOnKing.arrayScopeN(board, piece));
                }
            }
            else if(pieceRank == Board.getPieceRank(king.locationNumber())){
                if(pieceFile > kingFile){
                    interceptionScope.addAll(AttacksOnKing.arrayScopeW(board, piece));
                }
                else{
                    interceptionScope.addAll(AttacksOnKing.arrayScopeE(board, piece));
                }
            }
            if(pieceRank > kingRank && !piece.getClass().equals(Knight.class)){
                if(pieceFile > kingFile){
                    interceptionScope.addAll(AttacksOnKing.arrayScopeSW(board, piece));
                }
                else if(pieceFile < kingFile){
                    interceptionScope.addAll(AttacksOnKing.arrayScopeSE(board, piece));
                }
            } else if(pieceRank < kingRank && !piece.getClass().equals(Knight.class)) {
                if(pieceFile > kingFile){
                    interceptionScope.addAll(AttacksOnKing.arrayScopeNW(board, piece));
                }
                else if(pieceFile < kingFile){
                    interceptionScope.addAll(AttacksOnKing.arrayScopeNE(board, piece));
                }
            }
            if(piece.getClass().equals(Knight.class)){
                interceptionScope.add(piece.locationNumber());
            }
        }
        return interceptionScope;
    }
}
