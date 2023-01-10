package src.piece;

import java.util.HashSet;

import src.board.AttacksOnKing;
import src.board.Board;
import src.board.BoardChanges;


public class Pawn implements Piece, Cloneable{
    public int originalRank;
    public char pieceColor;
    public String PieceImage;
    public int locationNumber;
    public int numberOfMovesMade = 0;


    public Pawn(char pieceColor, int locationNumber){
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Pawn.png";
        this.locationNumber = locationNumber;
        if(pieceColor == 'W'){
            Board.whitePieces.add(this);
            originalRank = 1;
        }else{
            Board.blackPieces.add(this);
            originalRank = 6;
        }
    }

    public int pieceValue = 1;

    public char pieceAbbreviation = 'P';

    @Override
    public void move(Board board, int location) {
    //check if the move is en passant ->if the piece behind is an opposite color pawn (en passant); promotion(rank 8/1) or just a normal capture;
        // also check if that was a double move
        Piece pieceAtDesiredLocation = Board.board[location];
        if(pieceAtDesiredLocation.getClass().equals(EmptySpace.class)){
            int offset = (pieceColor == 'W') ? -8: 8;
            Board.board[location + offset] = new EmptySpace(location + offset);
        }
        pieceAtDesiredLocation.erase();
        Board.board[location] = this;
        Board.board[locationNumber] = new EmptySpace(locationNumber);
        locationNumber = location;
        numberOfMovesMade ++;
    }
    public boolean isPromotion(int destination){
        int rank = Board.getPieceRank(destination);
        if(rank == 0){
            return pieceColor == 'B';
        } else if (rank == 7) {
            return pieceColor == 'W';
        }
        return false;
    }

    public void promote(){
        //interacts with click or keyboard
    }
    public boolean hasMoved(){
        return originalRank != Board.getPieceRank(locationNumber);
    }
    public boolean hasMovedTwo(){
        if(pieceColor == 'W') {
            if (numberOfMovesMade == 1 && Board.getPieceRank(locationNumber) == 3){
                return true;
            }
        }else{
            if (numberOfMovesMade == 1 && Board.getPieceRank(locationNumber) == 4){
                return true;
            }
        }
        return false;
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
        optionsToMoveU1(board, possibleDestinations);
        optionsToMoveU2(board, possibleDestinations);
        optionsToMoveUW(board, possibleDestinations);
        optionsToMoveUE(board, possibleDestinations);
        if(pieceColor == 'W' && Board.getPieceRank(locationNumber) == 4){
            possibleDestinations.addAll(enPassantLocations(board));
        } else if (pieceColor == 'B' && Board.getPieceRank(locationNumber) == 3) {
            possibleDestinations.addAll(enPassantLocations(board));
        }
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
    public HashSet<Integer> generateCaptureMoves(Board board, HashSet<Integer> allowedMoves) {
        HashSet<Integer> possibleDestinations = new HashSet<>();

        guardingUW(board, possibleDestinations);
        guardingUE(board, possibleDestinations);
        if(pieceColor == 'W' && Board.getPieceRank(locationNumber) == 4){
            possibleDestinations.addAll(enPassantLocations(board));
        } else if (pieceColor == 'B' && Board.getPieceRank(locationNumber) == 3) {
            possibleDestinations.addAll(enPassantLocations(board));
        }
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
    public void optionsToMoveUW(Board board, HashSet<Integer> possibleDestinations){
        if(pieceColor == 'W') {
            Piece adjacentPieceW = board.getAdjacentPieceNW(locationNumber);
            if(adjacentPieceW != null && !adjacentPieceW.getClass().equals(EmptySpace.class) && adjacentPieceW.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        }else{
            Piece adjacentPieceW = board.getAdjacentPieceSW(locationNumber);
            if(adjacentPieceW != null && !adjacentPieceW.getClass().equals(EmptySpace.class) && adjacentPieceW.pieceColor() != 'B' && Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        }
    }
    public void guardingUW(Board board, HashSet<Integer> possibleDestinations){
        if(pieceColor == 'W') {
            Piece adjacentPieceW = board.getAdjacentPieceNW(locationNumber);
            if(adjacentPieceW != null && adjacentPieceW.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        }else{
            Piece adjacentPieceW = board.getAdjacentPieceSW(locationNumber);
            if(adjacentPieceW != null && adjacentPieceW.pieceColor() != 'B'&& Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        }
    }
    public void optionsToMoveUE(Board board, HashSet<Integer> possibleDestinations){
        if(pieceColor == 'W') {
            Piece adjacentPieceE = board.getAdjacentPieceNE(locationNumber);
            if(adjacentPieceE != null && !adjacentPieceE.getClass().equals(EmptySpace.class) && adjacentPieceE.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceE.locationNumber()) - 1 == Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        }else{
            Piece adjacentPieceE = board.getAdjacentPieceSE(locationNumber);
            if(adjacentPieceE != null && !adjacentPieceE.getClass().equals(EmptySpace.class) && adjacentPieceE.pieceColor() != 'B'&& Board.getPieceFile(adjacentPieceE.locationNumber()) - 1== Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        }
    }
    public void guardingUE(Board board, HashSet<Integer> possibleDestinations){
        if(pieceColor == 'W') {
            Piece adjacentPieceE = board.getAdjacentPieceNE(locationNumber);
            if(adjacentPieceE != null && adjacentPieceE.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceE.locationNumber()) - 1 == Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        }else{
            Piece adjacentPieceE = board.getAdjacentPieceSE(locationNumber);
            if(adjacentPieceE != null && adjacentPieceE.pieceColor() != 'B'&& Board.getPieceFile(adjacentPieceE.locationNumber()) - 1 == Board.getPieceFile(locationNumber)){
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        }
    }
    public void optionsToMoveU1(Board board, HashSet<Integer> possibleDestinations){
        if(pieceColor == 'W') {
            Piece adjacentPieceU1 = board.getAdjacentPieceN(locationNumber);
            if (adjacentPieceU1 != null && adjacentPieceU1.getClass().equals(EmptySpace.class)) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
            }
        }
        else{
            Piece adjacentPieceU1 = board.getAdjacentPieceS(locationNumber);
            if (adjacentPieceU1 != null &&adjacentPieceU1.getClass().equals(EmptySpace.class)) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
            }
        }
    }
    public void optionsToMoveU2(Board board, HashSet<Integer> possibleDestinations){
        if(pieceColor == 'W') {
            Piece adjacentPieceU1 = board.getAdjacentPieceN(locationNumber);
            if(adjacentPieceU1 == null){
                return;
            }
            Piece adjacentPieceU2 = board.getAdjacentPieceN(adjacentPieceU1.locationNumber());
            if (adjacentPieceU1.getClass().equals(EmptySpace.class) && adjacentPieceU2.getClass().equals(EmptySpace.class) && !hasMoved()) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
                possibleDestinations.add(adjacentPieceU2.locationNumber());
            }
        }
        else{
            Piece adjacentPieceU1 = board.getAdjacentPieceS(locationNumber);
            if(adjacentPieceU1 == null){
                return;
            }
            Piece adjacentPieceU2 = board.getAdjacentPieceS(adjacentPieceU1.locationNumber());
            if (adjacentPieceU1.getClass().equals(EmptySpace.class) && adjacentPieceU2.getClass().equals(EmptySpace.class) && !hasMoved()) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
                possibleDestinations.add(adjacentPieceU2.locationNumber());
            }
        }
    }
    public HashSet<Integer> enPassantLocations(Board board){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceE = board.getAdjacentPieceE(locationNumber);
        Piece adjacentPieceW = board.getAdjacentPieceW(locationNumber);
        //East
        if(adjacentPieceE != null && adjacentPieceE.getClass().equals(Pawn.class)){
            if(((Pawn) adjacentPieceE).hasMovedTwo() && BoardChanges.isLastEntryEqualTo(adjacentPieceE)){
                int captureLocationE = (this.pieceColor == 'W')? ((Pawn) adjacentPieceE).locationNumber + 8: ((Pawn) adjacentPieceE).locationNumber - 8;
                if(Board.board[captureLocationE].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(captureLocationE);
                }
            }
        }
        //West
        if(adjacentPieceW != null && adjacentPieceW.getClass().equals(Pawn.class)){
            if(((Pawn) adjacentPieceW).hasMovedTwo() && BoardChanges.isLastEntryEqualTo(adjacentPieceW)){
                int captureLocationW = (this.pieceColor == 'W')? ((Pawn) adjacentPieceW).locationNumber + 8: ((Pawn) adjacentPieceW).locationNumber - 8;
                if(Board.board[captureLocationW].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(captureLocationW);
                }
            }
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

    //en passant optimization: For white it can happen only on the 5th rank/ black 4th rank. Check only whenever we have
    //selected a pawn located on one of those ranks. Then check for a pawn of opposite color left and right.
    //if there is, check (peek func of stack) history to see if the last move was an opposite color pawn move.
    //if yes, then finally check whether it did a double jump or not.

}
