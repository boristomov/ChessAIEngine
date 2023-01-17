package src.piece;

import edu.princeton.cs.algs4.StdDraw;
import src.board.*;

import java.awt.*;
import java.util.HashSet;


public class Pawn implements Piece, Cloneable {
    /**
     * Piece original rank assigned with board initialization. If the piece is white = 1, else = 6.
     */
    public int originalRank;
    /**
     * Piece color.
     */
    public char pieceColor;
    /**
     * Piece PNG image filepath.
     */
    public String PieceImage;
    /**
     * Location index of the board square containing the piece.
     */
    public int locationNumber;
    /**
     * Piece abbreviation.
     */
    public char pieceAbbreviation;
    /**
     * Piece standardized value according to chess engines and current software for position analysis.
     * Needed for developing an evaluation feature.
     */
    public int pieceValue = 1;
    /**
     * Variable utilized as an indicator whether a pawn has reached the 3rd or 4th rank by a double move
     * or two single moves forward.
     */
    public int numberOfMovesMade = 0;

    // Constructor.
    public Pawn(char pieceColor, int locationNumber) {
        this.pieceColor = pieceColor;
        this.PieceImage = "PieceImages/" + pieceColor + "Pawn.png";
        this.locationNumber = locationNumber;
        if (pieceColor == 'W') {
            Board.whitePieces.add(this);
            originalRank = 1;
            this.pieceAbbreviation = 'P';
        } else {
            Board.blackPieces.add(this);
            originalRank = 6;
            this.pieceAbbreviation = 'p';
        }
    }


    // Interface methods.
    @Override
    public void move(Board board, int location) {
        //check if the move is en passant ->if the piece behind is an opposite color pawn (en passant); promotion(rank 8/1) or just a normal capture;
        // also check if that was a double move

        Piece pieceAtDesiredLocation = Board.board[location];
        if (pieceAtDesiredLocation.getClass().equals(EmptySpace.class)) {
            int offset = (pieceColor == 'W') ? -8 : 8;
            Board.board[location + offset] = new EmptySpace(location + offset);
        }
        pieceAtDesiredLocation.erase();
        Board.board[location] = this;
        Board.board[locationNumber] = new EmptySpace(locationNumber);
        locationNumber = location;
        numberOfMovesMade++;
        if (isPromotion(location)) {
            promote(location);
            return;
        }
    }

    /**
     * Function used to determine if a move has resulted the pawn reaching the last rank. If true,
     * a promotion menu is invoked.
     */
    public boolean isPromotion(int destination) {
        int rank = Board.getPieceRank(destination);
        if (rank == 0) {
            return pieceColor == 'B';
        } else if (rank == 7) {
            return pieceColor == 'W';
        }
        return false;
    }

    /**
     * Calls the method which visualizes the promotion menu. Replaces the pawn with the chosen piece.
     */
    public void promote(int moveLocation) {
        //interacts with click or keyboard
        int lastRank = (pieceColor == 'W') ? 7 : 0;
        if (Board.getPieceRank(moveLocation) == lastRank) {
            BoardSquare[][] menu = invokePromoteMenu(moveLocation);

            Piece selectedPiece = null;
            while (selectedPiece == null) {
                if (StdDraw.isMousePressed()) {
                    StdDraw.pause(300);
                    selectedPiece = BoardSquare.selectPiece(menu);
                    StdDraw.pause(300);
                }
            }
            // replacing piece on the board
            this.erase();
            Board.board[locationNumber] = selectedPiece;
            if (pieceColor == 'W') {
                Board.whitePieces.add(selectedPiece);
            } else {
                Board.blackPieces.add(selectedPiece);
            }
        }
    }

    /**
     * Invokes the promotion menu JFrame.
     */
    private BoardSquare[][] invokePromoteMenu(int moveLocation) {
        BoardSquare[][] menu = new BoardSquare[2][2];
        Color background = StdDraw.WHITE;
        Piece queen = new Queen(pieceColor, moveLocation, false);
        Piece rook = new Rook(pieceColor, moveLocation, false);
        Piece knight = new Knight(pieceColor, moveLocation, false);
        Piece bishop = new Bishop(pieceColor, moveLocation, false);

        menu[0][0] = new BoardSquare(queen, background, queen.pieceImage());
        menu[0][1] = new BoardSquare(rook, background, rook.pieceImage());
        menu[1][0] = new BoardSquare(knight, background, knight.pieceImage());
        menu[1][1] = new BoardSquare(bishop, background, bishop.pieceImage());
        ProgramRunner.visualizeBoardBSPromotion(menu);
        return menu;
    }

    /**
     * Returns true if the pawn has moved, false otherwise.
     */
    public boolean hasMoved() {
        return originalRank != Board.getPieceRank(locationNumber);
    }

    /**
     * Returns true if the pawn has already utilized its double move.
     */
    public boolean hasMovedTwo() {
        if (pieceColor == 'W') {
            return numberOfMovesMade == 1 && Board.getPieceRank(locationNumber) == 3;
        } else {
            return numberOfMovesMade == 1 && Board.getPieceRank(locationNumber) == 4;
        }
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
        optionsToMoveU1(board, possibleDestinations);
        optionsToMoveU2(board, possibleDestinations);
        optionsToMoveUW(board, possibleDestinations);
        optionsToMoveUE(board, possibleDestinations);
        if (pieceColor == 'W' && Board.getPieceRank(locationNumber) == 4) {
            possibleDestinations.addAll(enPassantLocations(board));
        } else if (pieceColor == 'B' && Board.getPieceRank(locationNumber) == 3) {
            possibleDestinations.addAll(enPassantLocations(board));
        }
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

    /**
     * Helper function utilized to determine the scope of threatened squares on the board. Utilized in
     * restricting the king's movement onto an attacked by the pawn square. Here optionsToMove and guarding
     * differ from each other in the scenario which we are examining. While the pawn can practically move
     * one square diagonally only if there is an enemy piece sitting there, this same square is still in scope
     * of the pawn, so the king would not be able to go there.
     */
    public HashSet<Integer> generateCaptureMoves(Board board, HashSet<Integer> allowedMoves) {
        HashSet<Integer> possibleDestinations = new HashSet<>();

        guardingUW(board, possibleDestinations);
        guardingUE(board, possibleDestinations);
        if (pieceColor == 'W' && Board.getPieceRank(locationNumber) == 4) {
            possibleDestinations.addAll(enPassantLocations(board));
        } else if (pieceColor == 'B' && Board.getPieceRank(locationNumber) == 3) {
            possibleDestinations.addAll(enPassantLocations(board));
        }
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

    private void optionsToMoveUW(Board board, HashSet<Integer> possibleDestinations) {
        if (pieceColor == 'W') {
            Piece adjacentPieceW = board.getAdjacentPieceNW(locationNumber);
            if (adjacentPieceW != null && !adjacentPieceW.getClass().equals(EmptySpace.class) && adjacentPieceW.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        } else {
            Piece adjacentPieceW = board.getAdjacentPieceSW(locationNumber);
            if (adjacentPieceW != null && !adjacentPieceW.getClass().equals(EmptySpace.class) && adjacentPieceW.pieceColor() != 'B' && Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        }
    }

    private void guardingUW(Board board, HashSet<Integer> possibleDestinations) {
        if (pieceColor == 'W') {
            Piece adjacentPieceW = board.getAdjacentPieceNW(locationNumber);
            if (adjacentPieceW != null && adjacentPieceW.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        } else {
            Piece adjacentPieceW = board.getAdjacentPieceSW(locationNumber);
            if (adjacentPieceW != null && adjacentPieceW.pieceColor() != 'B' && Board.getPieceFile(adjacentPieceW.locationNumber()) + 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceW.locationNumber());
            }
        }
    }

    private void optionsToMoveUE(Board board, HashSet<Integer> possibleDestinations) {
        if (pieceColor == 'W') {
            Piece adjacentPieceE = board.getAdjacentPieceNE(locationNumber);
            if (adjacentPieceE != null && !adjacentPieceE.getClass().equals(EmptySpace.class) && adjacentPieceE.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceE.locationNumber()) - 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        } else {
            Piece adjacentPieceE = board.getAdjacentPieceSE(locationNumber);
            if (adjacentPieceE != null && !adjacentPieceE.getClass().equals(EmptySpace.class) && adjacentPieceE.pieceColor() != 'B' && Board.getPieceFile(adjacentPieceE.locationNumber()) - 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        }
    }

    public void guardingUE(Board board, HashSet<Integer> possibleDestinations) {
        if (pieceColor == 'W') {
            Piece adjacentPieceE = board.getAdjacentPieceNE(locationNumber);
            if (adjacentPieceE != null && adjacentPieceE.pieceColor() != 'W' && Board.getPieceFile(adjacentPieceE.locationNumber()) - 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        } else {
            Piece adjacentPieceE = board.getAdjacentPieceSE(locationNumber);
            if (adjacentPieceE != null && adjacentPieceE.pieceColor() != 'B' && Board.getPieceFile(adjacentPieceE.locationNumber()) - 1 == Board.getPieceFile(locationNumber)) {
                possibleDestinations.add(adjacentPieceE.locationNumber());
            }
        }
    }

    // single move.
    public void optionsToMoveU1(Board board, HashSet<Integer> possibleDestinations) {
        if (pieceColor == 'W') {
            Piece adjacentPieceU1 = board.getAdjacentPieceN(locationNumber);
            if (adjacentPieceU1 != null && adjacentPieceU1.getClass().equals(EmptySpace.class)) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
            }
        } else {
            Piece adjacentPieceU1 = board.getAdjacentPieceS(locationNumber);
            if (adjacentPieceU1 != null && adjacentPieceU1.getClass().equals(EmptySpace.class)) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
            }
        }
    }

    // double move.
    public void optionsToMoveU2(Board board, HashSet<Integer> possibleDestinations) {
        if (pieceColor == 'W') {
            Piece adjacentPieceU1 = board.getAdjacentPieceN(locationNumber);
            if (adjacentPieceU1 == null) {
                return;
            }
            Piece adjacentPieceU2 = board.getAdjacentPieceN(adjacentPieceU1.locationNumber());
            if (adjacentPieceU2 == null) {
                return;
            }
            if (adjacentPieceU1.getClass().equals(EmptySpace.class) && adjacentPieceU2.getClass().equals(EmptySpace.class) && !hasMoved()) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
                possibleDestinations.add(adjacentPieceU2.locationNumber());
            }
        } else {
            Piece adjacentPieceU1 = board.getAdjacentPieceS(locationNumber);
            if (adjacentPieceU1 == null) {
                return;
            }
            Piece adjacentPieceU2 = board.getAdjacentPieceS(adjacentPieceU1.locationNumber());
            if (adjacentPieceU2 == null) {
                return;
            }
            if (adjacentPieceU1.getClass().equals(EmptySpace.class) && adjacentPieceU2.getClass().equals(EmptySpace.class) && !hasMoved()) {
                possibleDestinations.add(adjacentPieceU1.locationNumber());
                possibleDestinations.add(adjacentPieceU2.locationNumber());
            }
        }
    }

    /**
     * Checks for en passant. En passant can happen only at two ranks on the board depending on the pawn color.
     * 3rd if it is black and 4th if it is white. This move is possible only if previous move, made my the opponent
     * has been a double pawn move. If this is the case, then the method determines if the selected pawn is
     * adjacent horizontally to the enemy pawn, and only if this is true it can capture it diagonally from the square
     * behind it.
     */
    public HashSet<Integer> enPassantLocations(Board board) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        Piece adjacentPieceE = board.getAdjacentPieceE(locationNumber);
        Piece adjacentPieceW = board.getAdjacentPieceW(locationNumber);
        //East
        if (adjacentPieceE != null && adjacentPieceE.getClass().equals(Pawn.class)) {
            if (((Pawn) adjacentPieceE).hasMovedTwo() && BoardChanges.isLastEntryEqualTo(adjacentPieceE)) {
                int captureLocationE = (this.pieceColor == 'W') ? ((Pawn) adjacentPieceE).locationNumber + 8 : ((Pawn) adjacentPieceE).locationNumber - 8;
                if (Board.board[captureLocationE].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(captureLocationE);
                }
            }
        }
        //West
        if (adjacentPieceW != null && adjacentPieceW.getClass().equals(Pawn.class)) {
            if (((Pawn) adjacentPieceW).hasMovedTwo() && BoardChanges.isLastEntryEqualTo(adjacentPieceW)) {
                int captureLocationW = (this.pieceColor == 'W') ? ((Pawn) adjacentPieceW).locationNumber + 8 : ((Pawn) adjacentPieceW).locationNumber - 8;
                if (Board.board[captureLocationW].getClass().equals(EmptySpace.class)) {
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

    @Override
    public HashSet<Integer> attacksInAllDirections(Board board) {
        return null;
    }


}
