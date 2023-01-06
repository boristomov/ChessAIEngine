package src.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import src.piece.*;

public class AttacksOnKing {

    public static HashMap<Piece, HashSet<Integer>> pPiecesAndAllowedMoves = new HashMap<>();
    public static int WkingLocation;
    public static int BkingLocation;
    public static void checkForPins(Board board, int kingLocation){
        pinnedPieceN(board, kingLocation);
        pinnedPieceS(board, kingLocation);
        pinnedPieceW(board, kingLocation);
        pinnedPieceE(board, kingLocation);
        pinnedPieceNW(board, kingLocation);
        pinnedPieceNE(board, kingLocation);
        pinnedPieceSW(board, kingLocation);
        pinnedPieceSE(board, kingLocation);
    }
    public static HashSet<Integer> dangerScopeN(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while(moveLocation <= 63 && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            moveLocation += 8;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeS(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while(moveLocation >= 0 && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            moveLocation -= 8;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeE(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 1;
        while(moveLocation <= 63 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceRank = Board.getPieceRank(moveLocation);
            moveLocation += 1;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeW(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 1;
        while(moveLocation >= 0 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceRank = Board.getPieceRank(moveLocation);
            moveLocation -= 1;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeNW(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 7;
        while(moveLocation <= 63 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation += 7;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeNE(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 9;
        while(moveLocation <= 63 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation += 9;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeSW(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 9;
        while(moveLocation >= 0 && Board.getPieceFile(moveLocation) + 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation -= 9;
        }
        return possibleDestinations;
    }
    public static HashSet<Integer> dangerScopeSE(Board board, Piece piece){
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        possibleDestinations.add(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 7;
        while(moveLocation >= 0 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation -= 7;
        }
        return possibleDestinations;
    }
    public static Piece pinnedPieceN(Board board, int kingLocation){
        Piece adjacentPieceN = board.getAdjacentPieceN(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceN == null){
            return null;
        }
        while(adjacentPieceN.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceN.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceN);
            }
            adjacentPieceN = board.getAdjacentPieceN(adjacentPieceN.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceN == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceN.getClass().equals(Queen.class) || adjacentPieceN.getClass().equals(Rook.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeS(board, adjacentPieceN));
            return potentialPPieces.get(0);
        }
        return null;
    }
    public static Piece pinnedPieceS(Board board, int kingLocation){
        Piece adjacentPieceS = board.getAdjacentPieceS(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceS == null){
            return null;
        }
        while(adjacentPieceS.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceS.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceS);
            }
            adjacentPieceS = board.getAdjacentPieceS(adjacentPieceS.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceS == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceS.getClass().equals(Queen.class) || adjacentPieceS.getClass().equals(Rook.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeN(board, adjacentPieceS));
            return potentialPPieces.get(0);
        }
        return null;
    }
    public static Piece pinnedPieceW(Board board, int kingLocation){
        Piece adjacentPieceW = board.getAdjacentPieceW(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceW == null){
            return null;
        }
        while(adjacentPieceW.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceW.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceW);
            }
            adjacentPieceW = board.getAdjacentPieceW(adjacentPieceW.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceW == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceW.getClass().equals(Queen.class) || adjacentPieceW.getClass().equals(Rook.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeE(board, adjacentPieceW));
            return potentialPPieces.get(0);
        }
        return null;
    }
    public static Piece pinnedPieceE(Board board, int kingLocation){
        Piece adjacentPieceE = board.getAdjacentPieceE(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceE == null){
            return null;
        }
        while(adjacentPieceE.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceE.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceE);
            }
            adjacentPieceE = board.getAdjacentPieceE(adjacentPieceE.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceE == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceE.getClass().equals(Queen.class) || adjacentPieceE.getClass().equals(Rook.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeW(board, adjacentPieceE));
            return potentialPPieces.get(0);
        }
        return null;
    }
    public static Piece pinnedPieceNW(Board board, int kingLocation){
        Piece adjacentPieceNW = board.getAdjacentPieceNW(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceNW == null){
            return null;
        }
        while(adjacentPieceNW.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceNW.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceNW);
            }
            adjacentPieceNW = board.getAdjacentPieceNW(adjacentPieceNW.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceNW == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceNW.getClass().equals(Queen.class) || adjacentPieceNW.getClass().equals(Bishop.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeSE(board, adjacentPieceNW));
            return potentialPPieces.get(0);
        }
        return null;
    }
    public static Piece pinnedPieceNE(Board board, int kingLocation){
        Piece adjacentPieceNE = board.getAdjacentPieceNE(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceNE == null){
            return null;
        }
        while(adjacentPieceNE.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceNE.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceNE);
            }
            adjacentPieceNE = board.getAdjacentPieceNE(adjacentPieceNE.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceNE == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceNE.getClass().equals(Queen.class) || adjacentPieceNE.getClass().equals(Bishop.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeSW(board, adjacentPieceNE));
            return potentialPPieces.get(0);
        }
        return null;
    }
    public static Piece pinnedPieceSW(Board board, int kingLocation){
        Piece adjacentPieceSW = board.getAdjacentPieceSW(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceSW == null){
            return null;
        }
        while(adjacentPieceSW.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceSW.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceSW);
            }
            adjacentPieceSW = board.getAdjacentPieceSW(adjacentPieceSW.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceSW == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceSW.getClass().equals(Queen.class) || adjacentPieceSW.getClass().equals(Bishop.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeNE(board, adjacentPieceSW));
            return potentialPPieces.get(0);
        }
        return null;
    }
    public static Piece pinnedPieceSE(Board board, int kingLocation){
        Piece adjacentPieceSE = board.getAdjacentPieceSE(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if(adjacentPieceSE == null){
            return null;
        }
        while(adjacentPieceSE.pieceColor() != Board.getOppositeColor(king)){
            if(adjacentPieceSE.pieceColor() == king.pieceColor()){
                potentialPPieces.add(adjacentPieceSE);
            }
            adjacentPieceSE = board.getAdjacentPieceSE(adjacentPieceSE.locationNumber()); //looks for the adjacent square to the one just checked.
            if(adjacentPieceSE == null){
                return null;
            }
        }
        if(potentialPPieces.size() == 1 && (adjacentPieceSE.getClass().equals(Queen.class) || adjacentPieceSE.getClass().equals(Bishop.class))){
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeNW(board, adjacentPieceSE));
            return potentialPPieces.get(0);
        }
        return null;
    }
}
