package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;
import src.board.BoardChanges;

import java.util.HashSet;

public class Rook implements Piece, Cloneable {
    /**
     * Piece color.
     */
    public char pieceColor;
    /**
     * Indicator of whether the piece has been moved.
     */
    public boolean isMoved = false;
    /**
     * Piece PNG image filepath.
     */
    public String PieceImage;
    /**
     * Piece abbreviation.
     */
    public char pieceAbbreviation;
    /**
     * Piece standardized value according to chess engines and current software for position analysis.
     * Needed for developing an evaluation feature.
     */
    public int pieceValue = 5;
    /**
     * Location index of the board square containing the piece.
     */
    public int locationNumber;

    // Constructors.
    public Rook(char pieceColor, int locationNumber) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Rook.png";
        this.locationNumber = locationNumber;
        if (pieceColor == 'W') {
            Board.whitePieces.add(this);
            this.pieceAbbreviation = 'R';
        } else {
            Board.blackPieces.add(this);
            this.pieceAbbreviation = 'r';
        }
    }

    public Rook(char pieceColor, int locationNumber, boolean addToPieces) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Rook.png";
        this.locationNumber = locationNumber;
        // no adding to piece lists
    }

    // Interface methods.
    @Override
    public void move(Board board, int location) {
        Piece pieceAtDesiredLocation = Board.board[location];
        pieceAtDesiredLocation.erase();
        Board.board[location] = this;
        Board.board[locationNumber] = new EmptySpace(locationNumber);
        locationNumber = location;
        this.isMoved = true;
    }

    @Override
    public void erase() {
        HashSet<Piece> setOfSameColorPieces = (pieceColor == 'W') ? Board.whitePieces : Board.blackPieces;
        setOfSameColorPieces.remove(this);
        if (pieceColor == 'W') {
            BoardChanges.erasedPiecesW.add(this);
        } else {
            BoardChanges.erasedPiecesB.add(this);
        }
    }

    @Override
    public HashSet<Integer> generatePossibleMoves(Board board, HashSet<Integer> allowedMoves) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.addAll(optionsToMoveN(board));
        possibleDestinations.addAll(optionsToMoveS(board));
        possibleDestinations.addAll(optionsToMoveW(board));
        possibleDestinations.addAll(optionsToMoveE(board));
        if (!allowedMoves.isEmpty()) {
            HashSet<Integer> clone = (HashSet<Integer>) possibleDestinations.clone();
            for (int i : clone) {
                if (!allowedMoves.contains(i)) {
                    possibleDestinations.remove(i);
                }
            }
        } else if (AttacksOnKing.pPiecesAndAllowedMoves.containsKey(this)) {
            return allowedMoves;
        }
        return possibleDestinations;
    }

    private HashSet<Integer> optionsToMoveN(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceN = board.getAdjacentPieceN(locationNumber);
        if (adjacentPieceN == null) {
            return possibleDestinations;
        }
        while (adjacentPieceN.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceN.locationNumber());
            adjacentPieceN = board.getAdjacentPieceN(adjacentPieceN.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceN == null || adjacentPieceN.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
        }
        if (adjacentPieceN.pieceColor() != pieceColor) {
            possibleDestinations.add(adjacentPieceN.locationNumber());
        }
        return possibleDestinations;
    }

    private HashSet<Integer> optionsToMoveS(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceS = board.getAdjacentPieceS(locationNumber);
        if (adjacentPieceS == null) {
            return possibleDestinations;
        }
        while (adjacentPieceS.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceS.locationNumber());
            adjacentPieceS = board.getAdjacentPieceS(adjacentPieceS.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceS == null || adjacentPieceS.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
        }
        if (adjacentPieceS.pieceColor() != pieceColor) {
            possibleDestinations.add(adjacentPieceS.locationNumber());
        }
        return possibleDestinations;
    }

    private HashSet<Integer> optionsToMoveW(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceW = board.getAdjacentPieceW(locationNumber);
        if (adjacentPieceW == null) {
            return possibleDestinations;
        }
        int selectedPieceRank = Board.getPieceRank(adjacentPieceW.locationNumber());
        if (Board.getPieceRank(locationNumber) != selectedPieceRank) {
            return possibleDestinations;
        }
        while (adjacentPieceW.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceW.locationNumber());
            selectedPieceRank = Board.getPieceRank(locationNumber);
            adjacentPieceW = board.getAdjacentPieceW(adjacentPieceW.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceW == null || Board.getPieceRank(adjacentPieceW.locationNumber()) != selectedPieceRank || adjacentPieceW.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
        }
        if (adjacentPieceW.pieceColor() != pieceColor) {
            possibleDestinations.add(adjacentPieceW.locationNumber());
        }
        return possibleDestinations;
    }

    private HashSet<Integer> optionsToMoveE(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceE = board.getAdjacentPieceE(locationNumber);
        if (adjacentPieceE == null) {
            return possibleDestinations;
        }
        int selectedPieceRank = Board.getPieceRank(adjacentPieceE.locationNumber());
        if (Board.getPieceRank(locationNumber) != selectedPieceRank) {
            return possibleDestinations;
        }
        while (adjacentPieceE.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceE.locationNumber());
            adjacentPieceE = board.getAdjacentPieceE(adjacentPieceE.locationNumber()); //looks for the adjacent square to the one just checked.
            selectedPieceRank = Board.getPieceRank(locationNumber);
            if (adjacentPieceE == null || Board.getPieceRank(adjacentPieceE.locationNumber()) != selectedPieceRank || adjacentPieceE.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
        }
        if (adjacentPieceE.pieceColor() != pieceColor) {
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
