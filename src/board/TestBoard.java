package src.board;

import src.piece.*;

public class TestBoard {
    public static Board testQueenInMiddleBoard(){
        Board emptyBoard = new Board();
        emptyBoard.board[28] = new Queen('W',28);
        return emptyBoard;
    }
    public static Board testQueenAndEnemies(){
        Board emptyBoard = new Board();
        emptyBoard.board[27] = new Queen('W',27);
        for(int i = 48; i <=55; i++){
            emptyBoard.board[i] = new Pawn('B',i);
        }
        return emptyBoard;
    }
    public static Board testBishopAndEnemies(){
        Board emptyBoard = new Board();
        emptyBoard.board[28] = new Bishop('W',28);
        for(int i = 48; i <=55; i++){
            emptyBoard.board[i] = new Pawn('B',i);
        }
        return emptyBoard;
    }
    public static Board testKnightAndEnemies(){
        Board emptyBoard = new Board();
        emptyBoard.board[46] = new Knight('W',46);
        for(int i = 48; i <=55; i++){
            emptyBoard.board[i] = new Pawn('B',i);
        }
        return emptyBoard;
    }
    public static Board testPawnEnPassant(){
        Board newBoard = new Board(8);
        Board.board[35] = new Pawn('W',35);
        Board.board[11] = new EmptySpace(11);

        Board.board[36] = new Pawn('B',36);
        EmptySpace emptySpace = new EmptySpace(36);
        BoardChanges.lastEntry = new BoardChanges(Board.board[36],52,36,emptySpace);
        Board.board[52] = new EmptySpace(52);
        return newBoard;
    }
    public static Board testRookAndEnemies(){
        Board emptyBoard = new Board();
        emptyBoard.board[46] = new Rook('W',46);
        for(int i = 48; i <=55; i++){
            emptyBoard.board[i] = new Pawn('B',i);
        }
        return emptyBoard;
    }
    public static Board testPinnedPieceDiagonally(){
        Board board = new Board(8);
        Board.board[11] = new EmptySpace(11);
        Board.board[52] = new EmptySpace(52);
        Board.board[61] = new EmptySpace(61);
        Board.board[1] = new EmptySpace(1);
        Board.board[27] = new Pawn('W', 27);
        Board.board[36] = new Pawn('B', 36);
        Board.board[25] = new Queen('B', 25);
        Board.board[18] = new Knight('W', 18);
        return board;
    }
    public static Board testVerticalPin(){
        Board emptyBoard = new Board();
        for(int i = 0; i <=63; i++){
            emptyBoard.board[i] = new EmptySpace(i);
        }
        emptyBoard.board[4] = new King('W',4);
        emptyBoard.board[36] = new Queen('B',36);
        return emptyBoard;
    }
}
