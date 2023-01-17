package src.board;

import src.piece.*;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;

public class Board {
    /**
     * Size of the playing board.
     */
    public static int Size = 8;
    /**
     * Piece array holding the objects on the board.
     */

    public static Piece[] board = new Piece[64];
    /**
     * Current selected piece initialized to null.
     */

    public static Piece selectedPiece = null;
    /**
     * Sets of white pieces and black pieces.
     */

    public static HashSet<Piece> whitePieces = new HashSet<>();
    public static HashSet<Piece> blackPieces = new HashSet<>();

    public Board(int size) {
        Size = size;
        //Queens
        board[3] = new Queen('W', 3);
        board[59] = new Queen('B', 59);
        //Kings
        board[4] = new King('W', 4);
        board[60] = new King('B', 60);
        //Rooks
        board[0] = new Rook('W', 0);
        board[7] = new Rook('W', 7);


        board[56] = new Rook('B', 56);
        board[63] = new Rook('B', 63);
        //Knights
        board[1] = new Knight('W', 1);
        board[6] = new Knight('W', 6);

        board[57] = new Knight('B', 57);
        board[62] = new Knight('B', 62);
        //Bishops
        board[2] = new Bishop('W', 2);
        board[5] = new Bishop('W', 5);

        board[58] = new Bishop('B', 58);
        board[61] = new Bishop('B', 61);
        //Pawns
        for (int i = 8; i <= 15; i++) {
            board[i] = new Pawn('W', i);
            whitePieces.add(board[i]);
        }
        for (int i = 48; i <= 55; i++) {
            board[i] = new Pawn('B', i);
            blackPieces.add(board[i]);
        }
        whitePieces.addAll(Arrays.asList(board).subList(0, 8));
        blackPieces.addAll(Arrays.asList(board).subList(56, 64));

        //EmptySpaces
        for (int i = 16; i <= 47; i++) {
            board[i] = new EmptySpace(i);
        }

    }

    /**
     * Constructor for an empty board.
     */
    public Board() {
        for (int i = 0; i <= 63; i++) {
            board[i] = new EmptySpace(i);
        }
    }
    /**
     * Method which takes in an arrangement of chess figures on the board
     * and creates a Board instance by converting the given string input into a
     * playable position.
     *
     * @param inputPosition - FEN string.
     * @return a new Board initialized with the given string input.
     */
    public Board interactWithStringInput(String inputPosition) {
        return null;
    }

    /**
     * Activates when piece is clicked. Highlights the square underneath it and simulates the possible moves
     * by coloring their underlying squares in cyan.
     *
     * @return the clicked piece
     */
    public Piece clickOnPiece(char turnColor) throws CloneNotSupportedException {
        //if a piece able to move is clicked
        Piece king = (turnColor == 'W') ? board[AttacksOnKing.WkingLocation] : board[AttacksOnKing.BkingLocation];

        selectedPiece = Mouse.scanMousePosition(this);


        if (!selectedPiece.getClass().equals(EmptySpace.class) && selectedPiece.pieceColor() == Main.turnColor) {
            BoardSquare[][] BS = BoardRender.BoardToBSConverter(this);
            ProgramRunner.selectedPiece = selectedPiece;
            //changes the color of the selected piece to RED.
            Color selectedPieceSQColor = Color.RED;
            int selectedPieceFile = getPieceFile(selectedPiece.locationNumber());
            int selectedPieceRank = getPieceRank(selectedPiece.locationNumber());
            BS[selectedPieceFile][selectedPieceRank] = new BoardSquare(selectedPiece, selectedPieceSQColor, selectedPiece.pieceImage());

            //Checks for special move restrictions following a pin on the selected piece.
            int kingLocation = king.locationNumber();
            AttacksOnKing.checkForPins(this, kingLocation);
            HashSet<Integer> allowedMoves = (AttacksOnKing.pPiecesAndAllowedMoves.containsKey(selectedPiece)) ? AttacksOnKing.pPiecesAndAllowedMoves.get(selectedPiece) : new HashSet<>();
            if (AttacksOnKing.isPieceTargeted(this, turnColor, king.locationNumber())) {// if king is in check
                allowedMoves = AttacksOnKing.mergeAllowedMoves(allowedMoves, AttacksOnKing.applyDanSfunc(this, king));
            }
            HashSet<Integer> movesToPutShadowOn = selectedPiece.generatePossibleMoves(this, allowedMoves);
            ProgramRunner.movesToPutShadowOn = movesToPutShadowOn;// sets the holder variable
            if (movesToPutShadowOn != null) {
                for (int i : movesToPutShadowOn) {
                    Color squareColor = Color.CYAN;
                    int file = getPieceFile(i);
                    int rank = getPieceRank(i);
                    Piece pieceAtSquare = getPieceAtSquare(i);
                    BS[file][rank] = new BoardSquare(pieceAtSquare, squareColor, pieceAtSquare.pieceImage());
                }
            }
            ProgramRunner.visualizeBoardBS(BS);
            return selectedPiece;
        }
        return null;
    }

    /**
     * Activates when user clicks on one of the projected possible moves.
     * Calls Piece.move() and executes the board changes.
     * Shifts turn to the other player.
     *
     * @param selectedPiece - piece which was clicked on earlier during the move
     */
    public boolean confirmMove(Piece selectedPiece, char turnColor, HashSet<Integer> movesToPutShadowOn) throws CloneNotSupportedException {
        Piece selectedDestinationPiece = Mouse.scanMousePosition(this);


        assert selectedDestinationPiece != null;
        if (movesToPutShadowOn.contains(selectedDestinationPiece.locationNumber())) {
            BoardChanges newChange = new BoardChanges(selectedPiece, selectedPiece.locationNumber(), selectedDestinationPiece.locationNumber(), selectedDestinationPiece);
            BoardChanges.lastEntry = newChange;
            selectedPiece.move(this, selectedDestinationPiece.locationNumber());
            BoardSquare[][] BS = BoardRender.BoardToBSConverter(this);
            ProgramRunner.visualizeBoardBS(BS);
            Piece king = (turnColor == 'W') ? board[AttacksOnKing.BkingLocation] : board[AttacksOnKing.WkingLocation];
            if (AttacksOnKing.isCheckMate(this, turnColor, king)) {
                System.out.println("game over");
                System.exit(0);
            }
            Main.turnColor = Board.getOppositeColorChar(Main.turnColor);
            AttacksOnKing.pPiecesAndAllowedMoves.clear();
            AttacksOnKing.checkingPieces.clear();
            return true;
        } else if (selectedDestinationPiece.pieceColor() == selectedPiece.pieceColor()) {
            AttacksOnKing.pPiecesAndAllowedMoves.clear();
            AttacksOnKing.checkingPieces.clear();
            clickOnPiece(turnColor);// does not have anywhere to store selected piece
            return false;
        }
        if (!movesToPutShadowOn.contains(selectedDestinationPiece.locationNumber()) || selectedDestinationPiece.pieceColor() != selectedPiece.pieceColor()) {
            AttacksOnKing.pPiecesAndAllowedMoves.clear();
            AttacksOnKing.checkingPieces.clear();
            ProgramRunner.visualizeBoard(this);
            return true;
        }
        return false;
    }

    /**
     * Method returns a string representation of the board using notation:
     * R/N/B/...E/E/../R
     */
    public String toString() {
        String output = "/";
        for (int i = 0; i <= board.length - 1; i++) {
            output += board[i].pieceAbbreviation() + "/";
        }
        return output;
    }
    //Helper functions//

    /**
     * Returns a location index from 0 to 63 given rank and file.
     */

    public static int getBoardLocation(int rank, int file) {
        return rank * 8 + file;
    }

    /**
     * Returns a file given location index.
     */

    public static int getPieceFile(int locationNumber) {
        return locationNumber % Board.Size;
    }

    /**
     * Returns a rank given location index.
     */
    public static int getPieceRank(int locationNumber) {
        return (int) Math.floor(locationNumber / Board.Size);
    }

    /**
     * Returns the opposite color to the color of a given piece.
     */
    public static char getOppositeColor(Piece selectedPiece) {
        return (selectedPiece.pieceColor() == 'W') ? 'B' : 'W';
    }


    /**
     * Returns the opposite color of a char input.
     */
    public static char getOppositeColorChar(char currentColor) {
        return (currentColor == 'W') ? 'B' : 'W';
    }

    /**
     * Returns the piece placed at a given index on the board.
     */
    public Piece getPieceAtSquare(int squareNumber) {
        return board[squareNumber];
    }

    /**
     * Replaces two given pieces and places the new on the board. Used when creating clones.
     */
    public Board replace(Piece replacedPiece, Piece newPiece) {
        board[replacedPiece.locationNumber()] = newPiece;
        return this;
    }

    /**
     * Functions getting an adjacent piece in all possible directions: N, S, W, E, NW, NE, SW, SE;
     *
     * @param locationNumber reference location.
     * @return the piece located at the adjacent square.
     */
    public Piece getAdjacentPieceN(int locationNumber) {
        int adjacentSquareNum = locationNumber + Board.Size;
        if (!(adjacentSquareNum > 63)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceS(int locationNumber) {
        int adjacentSquareNum = locationNumber - Board.Size;
        if (!(adjacentSquareNum < 0)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceW(int locationNumber) {
        int adjacentSquareNum = locationNumber - 1;
        if (!(adjacentSquareNum < 0) && getPieceRank(adjacentSquareNum) == getPieceRank(locationNumber)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceE(int locationNumber) {
        int adjacentSquareNum = locationNumber + 1;
        if (!(adjacentSquareNum > 63) && getPieceRank(adjacentSquareNum) == getPieceRank(locationNumber)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceNW(int locationNumber) {
        int adjacentSquareNum = locationNumber + Board.Size - 1;
        if (!(adjacentSquareNum > 63) && getPieceFile(adjacentSquareNum) + 1 == getPieceFile(locationNumber)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceNE(int locationNumber) {
        int adjacentSquareNum = locationNumber + Board.Size + 1;
        if (!(adjacentSquareNum > 63) && getPieceFile(adjacentSquareNum) - 1 == getPieceFile(locationNumber)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceSW(int locationNumber) {
        int adjacentSquareNum = locationNumber - Board.Size - 1;
        if (!(adjacentSquareNum < 0) && getPieceFile(adjacentSquareNum) + 1 == getPieceFile(locationNumber)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceSE(int locationNumber) {
        int adjacentSquareNum = locationNumber - Board.Size + 1;
        if (!(adjacentSquareNum < 0) && getPieceFile(adjacentSquareNum) - 1 == getPieceFile(locationNumber)) {
            return board[adjacentSquareNum];
        }
        return null;
    }
}
