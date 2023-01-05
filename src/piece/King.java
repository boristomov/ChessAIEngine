package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;
import src.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;

public class King implements Piece{

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
        return null;
    }
    @Override
    public String pieceImage() {
        return PieceImage;
    }
    //KING PINNING.
//    public HashSet<Piece> pinningPieces(){
//        HashSet<Piece> pinningPieces = new HashSet<>();
//
//    }
    // returns the piece who is pinning;
    // after discovering an opposing color piece of a threading class (having encountered only 1 own color piece), goes back and calls a function which adds
    // the pinned piece to AttacksOnKing hashmap.
    public Piece pinningPieceN(Board board){
        Piece adjacentPieceN = board.getAdjacentPieceN(locationNumber);
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceN == null){
            return null;
        }
        while(adjacentPieceN.pieceColor() != Board.getOppositeColor(this)){
            if(adjacentPieceN.pieceColor() == pieceColor){
                potentialPPieces.add(adjacentPieceN);
            }
            adjacentPieceN = board.getAdjacentPieceN(adjacentPieceN.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceN == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceN.getClass().equals(Queen.class) || adjacentPieceN.getClass().equals(Rook.class))){

            return adjacentPieceN;
        }
        return null;
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
