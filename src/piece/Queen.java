package src.piece;

import src.board.AttacksOnKing;
import src.board.Board;
import src.board.BoardChanges;

import java.util.HashSet;

public class Queen implements Piece, Cloneable {
    /**
     * Piece color.
     */
    public char pieceColor;
    /**
     * Piece PNG image filepath.
     */
    public String PieceImage;
    /**
     * Piece standardized value according to chess engines and current software for position analysis.
     * Needed for developing an evaluation feature.
     */
    public int pieceValue = 9;
    /**
     * Piece abbreviation.
     */
    public char pieceAbbreviation;
    /**
     * Location index of the board square containing the piece.
     */
    public int locationNumber;

    // Constructors. (Used when initializing the promoting menu options, so that the pieces Queen,
    // Rook, Bishop and Knight are not added to the set of existing pieces on the board.
    public Queen(char pieceColor, int locationNumber) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Queen.png";
        this.locationNumber = locationNumber;// if taken maybe -1
        if (pieceColor == 'W') {
            Board.whitePieces.add(this);
            this.pieceAbbreviation = 'Q';
        } else {
            Board.blackPieces.add(this);
            this.pieceAbbreviation = 'q';
        }
    }

    public Queen(char pieceColor, int locationNumber, boolean addToPieces) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Queen.png";
        this.locationNumber = locationNumber;// if taken maybe -1
        // no adding to piece lists
    }

    // Interface methods.
    @Override
    public void move(Board board, int newLocation) {
        Piece pieceAtDesiredLocation = Board.board[newLocation];
        pieceAtDesiredLocation.erase();
        Board.board[newLocation] = this;
        Board.board[locationNumber] = new EmptySpace(locationNumber);
        locationNumber = newLocation;
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

    public HashSet<Integer> optionsToMoveN(Board board) {
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

    public HashSet<Integer> optionsToMoveS(Board board) {
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

    public HashSet<Integer> optionsToMoveW(Board board) {
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

    public HashSet<Integer> optionsToMoveE(Board board) {
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

    public HashSet<Integer> optionsToMoveNW(Board board) {
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

    public HashSet<Integer> optionsToMoveNE(Board board) {
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

    public HashSet<Integer> optionsToMoveSW(Board board) {
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

    public HashSet<Integer> optionsToMoveSE(Board board) {
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
    public char pieceColor() {
        return pieceColor;
    }

    @Override
    public int locationNumber() {
        return locationNumber;
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
