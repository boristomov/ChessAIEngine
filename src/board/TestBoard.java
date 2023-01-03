package src.board;

import src.piece.Queen;

public class TestBoard {
    public static Board testQueenInMiddleBoard(){
        Board emptyBoard = new Board();
        emptyBoard.board[27] = new Queen('W',27);
        return emptyBoard;
    }
}
