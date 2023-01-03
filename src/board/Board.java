package src.board;

import edu.princeton.cs.algs4.StdDraw;
import src.piece.*;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    public static int Size = 8;

    public static Piece[] board = new Piece[64];

    public static Piece selectedPiece = null;

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
        }
        for (int i = 48; i <= 55; i++) {
            board[i] = new Pawn('B', i);
        }
        //EmptySpaces
        for (int i = 16; i <= 47; i++) {
            board[i] = new EmptySpace(i);
        }

    }
    public Board(){
        //EmptySpaces
        for (int i = 0; i <= 63; i++) {
            board[i] = new EmptySpace(i);
        }
    }

    /**
     * Method which takes in an arrangement of chess figures on the board
     * and creates a Board instance by converting the given string input into a
     * playable position.
     * Example: /R/K/e/e/Q/e/e/.../N/B/K/R/
     *
     * @param inputPosition - string of the form shown above, where the characters in
     *                      between the slashes indicate different pieces.
     * @return a new Board initialized with the given string input.
     */
    public Board interactWithStringInput(String inputPosition) {
        return null;
    }

    /**
     * Activates when piece is clicked. Highlights the square underneath it and simulates possible moves.
     * @return the clicked piece
     */
    public void clickOnPiece(){
        //if a piece able to move is clicked
        selectedPiece = Mouse.scanMousePosition(this);
        if(!selectedPiece.getClass().equals(EmptySpace.class)){
            BoardSquare[][] BS = BoardRender.BoardToBSConverter(this);
            //changes the color of the selected piece to RED.
            Color selectedPieceSQColor = Color.RED;
            int selectedPieceFile = getPieceFile(selectedPiece.locationNumber());
            int selectedPieceRank = getPieceRank(selectedPiece.locationNumber());
            BS[selectedPieceFile][selectedPieceRank] = new BoardSquare(selectedPiece, selectedPieceSQColor, selectedPiece.pieceImage());
            //changes the color of the possible squares to CYAN.
            ArrayList<Integer> movesToPutShadowOn = selectedPiece.generatePossibleMoves(this);
            if(!movesToPutShadowOn.isEmpty()) {
                for (int i = 0; i <= movesToPutShadowOn.size()-1; i++) {
                    Color squareColor = Color.CYAN;
                    int file = getPieceFile(movesToPutShadowOn.get(i));
                    int rank = getPieceRank(movesToPutShadowOn.get(i));
                    Piece pieceAtSquare = getPieceAtSquare(movesToPutShadowOn.get(i));
                    BS[file][rank] = new BoardSquare(pieceAtSquare, squareColor, pieceAtSquare.pieceImage());
                }
            }
            BoardRender render = new BoardRender();
            render.initialize(ProgramRunner.WIDTH, ProgramRunner.HEIGHT, 4, 4);
            render.renderFrame(BS);
        }
    }

    /**
     * Activates when user clicks on one of the projected possible moves.
     * Calls Piece.move() and executes the board changes.
     * Shifts turns to the other player.
     * @param selectedPiece - piece which was clicked on earlier during the move
     *
     */
    public void confirmMove(Piece selectedPiece){

    }

    public String toString() {
        String output = "/";
        for (int i = 0; i <= board.length - 1; i++) {
            output += board[i].pieceAbbreviation() + "/";
        }
        return output;
    }

    //Helper functions//
    public Piece getPieceAtSquare(int squareNumber) {
        return board[squareNumber];
    }

    public static int getPieceFile(int locationNumber) {
        return locationNumber % Board.Size;
    }

    public static int getPieceRank(int locationNumber) {
        return (int) Math.floor(locationNumber / Board.Size);
    }

    public int getSquareNum(int file, int rank) {
        return rank * Board.Size + file;
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
        if (!(adjacentSquareNum < 0)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceE(int locationNumber) {
        int adjacentSquareNum = locationNumber + 1;
        if (!(adjacentSquareNum > 63)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceNW(int locationNumber) {
        int adjacentSquareNum = locationNumber + Board.Size - 1;
        if (!(adjacentSquareNum > 63)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceNE(int locationNumber) {
        int adjacentSquareNum = locationNumber + Board.Size + 1;
        if (!(adjacentSquareNum > 63)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceSW(int locationNumber) {
        int adjacentSquareNum = locationNumber - Board.Size - 1;
        if (!(adjacentSquareNum < 0)) {
            return board[adjacentSquareNum];
        }
        return null;
    }

    public Piece getAdjacentPieceSE(int locationNumber) {
        int adjacentSquareNum = locationNumber - Board.Size + 1;
        if (!(adjacentSquareNum < 0)) {
            return board[adjacentSquareNum];
        }
        return null;
    }
}
