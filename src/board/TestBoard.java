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
        Board.board[59] = new EmptySpace(59);
        Board.board[1] = new EmptySpace(1);
        Board.board[27] = new Pawn('W', 27);
        Board.board[36] = new Pawn('B', 36);
        Board.board[25] = new Bishop('B', 25);
        Board.board[18] = new Knight('W', 18);
        Board.board[31] = new Queen('B', 31);
        return board;
    }
    public static Board testBRQatTheCorners(){
        Board board = new Board();
        for(int i = 0; i <=63; i++){
            Board.board[i] = new EmptySpace(i);
        }
        Board.board[32] = new Bishop('W',32);
        Board.board[16] = new Queen('W',16);
        Board.board[63] = new Queen('W',63);
        Board.board[0] = new Queen('W',0);
        Board.board[55] = new Rook('W',55);
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
    public static Board testHorizontalPin(){
        Board emptyBoard = new Board();
        for(int i = 0; i <=63; i++){
            emptyBoard.board[i] = new EmptySpace(i);
        }
        emptyBoard.board[29] = new King('W',29);
        emptyBoard.board[26] = new Queen('B',26);
        return emptyBoard;
    }
    public static Board testCheckMate(){
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[52] = new EmptySpace(52);
        Board.board[13] = new EmptySpace(13);
        Board.board[30] = new Pawn('W', 30);
        Board.board[29] = new Pawn('B', 29);

        return board;
    }
    public static Board testQueenAndPawn(){
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[30] = new Pawn('W', 30);
        Board.board[29] = new Pawn('B', 29);
        Board.board[52] = new Queen('B', 52);
        Board.board[20] = new Pawn('B', 20);

        return board;
    }
    public static Board testQueenAndPawn2(){
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);
        Board.board[11] = new Pawn('B',11);
        Board.board[30] = new Pawn('W', 30);
        Board.board[29] = new Pawn('B', 29);
        Board.board[52] = new Queen('B', 52);

        return board;
    }
    public static Board testBishopNorhtSameFile(){
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);
        Board.board[11] = new Pawn('B',11);
        Board.board[30] = new Pawn('W', 30);
        Board.board[29] = new Pawn('B', 29);
        Board.board[52] = new Bishop('B', 52);

        return board;
    }
    public static Board testCheckBlock(){
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);

        Board.board[11] = new EmptySpace(11);
        Board.board[28] = new Knight('W', 28);
        Board.board[29] = new Pawn('B', 29);
        Board.board[18] = new Bishop('B', 18);
        Board.board[43] = new Queen('B', 43);

        return board;
    }
    public static Board testKnightCheckBlock(){
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);

        Board.board[10] = new Knight('W',10);
        Board.board[29] = new Pawn('W', 29);
        Board.board[18] = new Bishop('B', 18);
        Board.board[36] = new Queen('B', 36);

        return board;
    }
    public static Board testQueenAttack2ndRank(){
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);

        Board.board[10] = new Knight('W',10);
        Board.board[29] = new Pawn('W', 29);
        Board.board[18] = new Queen('B', 18);
        Board.board[36] = new Bishop('B', 36);

        return board;
    }
    public static Board testBishop25CheckNoCM(){
        Board board = new Board(8);
        Board.board[52] = new EmptySpace(52);
        Board.board[11] = new EmptySpace(11);
        Board.board[36] = new Pawn('W', 36);

        return board;
    }
    public static Board testWQueenCheckNoCM(){
        Board board = new Board(8);
        Board.board[52] = new EmptySpace(52);
        Board.board[59] = new EmptySpace(59);
        Board.board[3] = new EmptySpace(3);
        Board.board[51] = new Queen('W', 51);
        Board.board[31] = new Queen('B', 31);

        return board;
    }
    public static Board testWQueenCM(){
        Board board = new Board(8);
        Board.board[3] = new EmptySpace(3);
        Board.board[11] = new EmptySpace(11);
        Board.board[60] = new EmptySpace(60);
        Board.board[35] = new Queen('W', 35);
        Board.board[52] = new King('B', 52);


        return board;
    }
}
