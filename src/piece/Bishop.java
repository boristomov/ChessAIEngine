package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;
import src.board.BoardChanges;

import java.util.HashSet;

public class Bishop implements Piece, Cloneable {
    /**
     * Piece color.
     */
    public char pieceColor;
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
    public int pieceValue = 3;
    /**
     * Location index of the board square containing the piece.
     */
    public int locationNumber;

    // Constructors.
    public Bishop(char pieceColor, int locationNumber) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Bishop.png";
        this.locationNumber = locationNumber;
        if (pieceColor == 'W') {
            Board.whitePieces.add(this);
            this.pieceAbbreviation = 'B';
        } else {
            Board.blackPieces.add(this);
            this.pieceAbbreviation = 'b';
        }
    }

    /**
     * Constructor which is utilized when generating test board. It does not initially add any piece
     * into the set of white/black pieces.
     */
    public Bishop(char pieceColor, int locationNumber, boolean addToPieces) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Bishop.png";
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
        possibleDestinations.addAll(optionsToMoveNW(board));
        possibleDestinations.addAll(optionsToMoveNE(board));
        possibleDestinations.addAll(optionsToMoveSW(board));
        possibleDestinations.addAll(optionsToMoveSE(board));
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

    private HashSet<Integer> optionsToMoveNW(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceNW = board.getAdjacentPieceNW(locationNumber);
        if (adjacentPieceNW == null) {
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceNW.locationNumber());
        if (Board.getPieceFile(locationNumber) - 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while (adjacentPieceNW.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceNW.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceNW.locationNumber());
            adjacentPieceNW = board.getAdjacentPieceNW(adjacentPieceNW.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceNW == null || Board.getPieceFile(adjacentPieceNW.locationNumber()) + 1 != currentAdjPieceFile || adjacentPieceNW.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
        }
        if (adjacentPieceNW.pieceColor() != pieceColor) {
            possibleDestinations.add(adjacentPieceNW.locationNumber());
        }
        return possibleDestinations;
    }

    private HashSet<Integer> optionsToMoveNE(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceNE = board.getAdjacentPieceNE(locationNumber);
        if (adjacentPieceNE == null) {
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceNE.locationNumber());
        if (Board.getPieceFile(locationNumber) + 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while (adjacentPieceNE.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceNE.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceNE.locationNumber());
            adjacentPieceNE = board.getAdjacentPieceNE(adjacentPieceNE.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceNE == null || Board.getPieceFile(adjacentPieceNE.locationNumber()) - 1 != currentAdjPieceFile || adjacentPieceNE.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
        }
        if (adjacentPieceNE.pieceColor() != pieceColor) {
            possibleDestinations.add(adjacentPieceNE.locationNumber());
        }
        return possibleDestinations;
    }

    private HashSet<Integer> optionsToMoveSW(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceSW = board.getAdjacentPieceSW(locationNumber);
        if (adjacentPieceSW == null) {
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceSW.locationNumber());
        if (Board.getPieceFile(locationNumber) - 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while (adjacentPieceSW.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceSW.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceSW.locationNumber());
            adjacentPieceSW = board.getAdjacentPieceSW(adjacentPieceSW.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceSW == null || Board.getPieceFile(adjacentPieceSW.locationNumber()) + 1 != currentAdjPieceFile || adjacentPieceSW.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
        }
        if (adjacentPieceSW.pieceColor() != pieceColor) {
            possibleDestinations.add(adjacentPieceSW.locationNumber());
        }
        return possibleDestinations;
    }

    private HashSet<Integer> optionsToMoveSE(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceSE = board.getAdjacentPieceSE(locationNumber);
        if (adjacentPieceSE == null) {
            return possibleDestinations;
        }
        int currentAdjPieceFile = Board.getPieceFile(adjacentPieceSE.locationNumber());
        if (Board.getPieceFile(locationNumber) + 1 != currentAdjPieceFile) {
            return possibleDestinations;
        }
        while (adjacentPieceSE.getClass().equals(EmptySpace.class)) {
            possibleDestinations.add(adjacentPieceSE.locationNumber());
            currentAdjPieceFile = Board.getPieceFile(adjacentPieceSE.locationNumber());
            adjacentPieceSE = board.getAdjacentPieceSE(adjacentPieceSE.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceSE == null || Board.getPieceFile(adjacentPieceSE.locationNumber()) - 1 != currentAdjPieceFile || adjacentPieceSE.pieceColor() == pieceColor) {
                return possibleDestinations;
            }
            possibleDestinations.add(adjacentPieceSE.locationNumber());
        }
        if (adjacentPieceSE.pieceColor() != pieceColor) {
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
