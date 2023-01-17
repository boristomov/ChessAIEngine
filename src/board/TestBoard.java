package src.board;

import src.piece.*;

public class TestBoard {
    /** FEN string is a string representation on a given chess position.
     * The functions below take care of parsing the string and creating pieces on
     * the board.
     */
    public static Board FENStringBoardGenerator(String FEN) {
        Board board = new Board();
        String[] fields = FEN.split(" ");
        char[] FirstFelements = elementsFEN(fields[0]);
        char[] SecondFelements = elementsFEN(fields[1]);
        char[] ThirdFelements = elementsFEN(fields[2]);
        char[] FourthFelements = elementsFEN(fields[3]);
        char[] FifthFelements = elementsFEN(fields[4]);
        char[] SixthFelements = elementsFEN(fields[5]);

        board = FirstFieldParser(FirstFelements);
        SecondFieldParser(SecondFelements);
        ThirdFieldParser(board, ThirdFelements);
        return board;
    }

    /**
     * Helper functions which parse the FEN string into separate fields. Once the string
     * elements are passed, the functions load the necessary properties of the position like
     * ability for castling, en passant, turn color and number of moves made, that indicate whether
     * the game should end in a draw.
     */

    /**
     * Loads pieces on the board.
     */
    private static Board FirstFieldParser(char[] elements) {
        Board board = new Board();
        int currentRank = 7;
        int currentFile = 0;
        int currSquareNum;
        for (char elem : elements) {
            switch (elem) {
                case 'r':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Rook('B', currSquareNum);
                    currentFile += 1;
                    break;
                case 'R':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Rook('W', currSquareNum);
                    currentFile += 1;
                    break;
                case 'q':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Queen('B', currSquareNum);
                    currentFile += 1;
                    break;
                case 'Q':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Queen('W', currSquareNum);
                    currentFile += 1;
                    break;
                case 'n':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Knight('B', currSquareNum);
                    currentFile += 1;
                    break;
                case 'N':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Knight('W', currSquareNum);
                    currentFile += 1;
                    break;
                case 'k':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new King('B', currSquareNum);
                    currentFile += 1;
                    break;
                case 'K':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new King('W', currSquareNum);
                    currentFile += 1;
                    break;
                case 'p':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Pawn('B', currSquareNum);
                    currentFile += 1;
                    break;
                case 'P':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Pawn('W', currSquareNum);
                    currentFile += 1;
                    break;
                case 'b':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Bishop('B', currSquareNum);
                    currentFile += 1;
                    break;
                case 'B':
                    currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                    Board.board[currSquareNum] = new Bishop('W', currSquareNum);
                    currentFile += 1;
                    break;
                case '/':
                    currentRank -= 1;
                    currentFile = 0;
                    break;
                default:
                    if (Character.isDigit(elem)) {
                        int numEmptySpaces = Character.getNumericValue(elem);
                        for (int i = 1; i <= numEmptySpaces; i++) {
                            currSquareNum = Board.getBoardLocation(currentRank, currentFile);
                            Board.board[currSquareNum] = new EmptySpace(currSquareNum);
                            currentFile += 1;
                        }
                    } else {
                        return board;
                    }
                    break;
            }
        }
        return board;
    }

    /**
     * Assigns a current player turn.
     */

    private static void SecondFieldParser(char[] element) {
        Main.turnColor = Character.toUpperCase(element[0]);
    }
    /**
     * Gives information about which castling moves are still possible.
     */

    private static void ThirdFieldParser(Board board, char[] elements) {
        // have to set all isMoved variables to true where not indicated the right to castle;
        if (elements.length == 0) {
            return;
        }
        Piece king;
        Piece rook;
        for (char elem : elements) {
            switch (elem) {
                case 'q':
                    king = Board.board[60];
                    rook = Board.board[56];
                    ((King) king).isMoved = false;
                    ((Rook) rook).isMoved = false;
                    break;
                case 'Q':
                    king = Board.board[4];
                    rook = Board.board[0];
//                    ((King) king).isMoved = false;
//                    ((Rook) rook).isMoved = false;
                    break;
                case 'k':
                    king = Board.board[60];
                    rook = Board.board[63];
//                    ((King) king).isMoved = false;
//                    ((Rook) rook).isMoved = false;
                    break;
                case 'K':
                    king = Board.board[4];
                    rook = Board.board[7];
//                    ((King) king).isMoved = false;
//                    ((Rook) rook).isMoved = false;
                    break;
                default:
                    return;
            }
        }
    }
    /**
     * Gives information about pawns that could be captured by en passant on the current move.
     */

    private static void FourthFieldParser() {

    }
    /**
     * Sets the number of full moves made.
     */

    private static void FifthFieldParser() {

    }
    /**
     * Sets the number of half moves made.
     */
    private static void SixthFieldParser() {

    }

    /**
     * Converts a FEN string into char bits and stores them into an array.
     */
    private static char[] elementsFEN(String FEN) {
        return FEN.toCharArray();
    }

    /**
     * Random test boards created while debugging.
     */
    public static Board testQueenInMiddleBoard() {
        Board emptyBoard = new Board();
        Board.board[28] = new Queen('W', 28);
        return emptyBoard;
    }

    public static Board testQueenAndEnemies() {
        Board emptyBoard = new Board();
        Board.board[27] = new Queen('W', 27);
        for (int i = 48; i <= 55; i++) {
            Board.board[i] = new Pawn('B', i);
        }
        return emptyBoard;
    }

    public static Board testBishopAndEnemies() {
        Board emptyBoard = new Board();
        Board.board[28] = new Bishop('W', 28);
        for (int i = 48; i <= 55; i++) {
            Board.board[i] = new Pawn('B', i);
        }
        return emptyBoard;
    }

    public static Board testKnightAndEnemies() {
        Board emptyBoard = new Board();
        Board.board[46] = new Knight('W', 46);
        for (int i = 48; i <= 55; i++) {
            Board.board[i] = new Pawn('B', i);
        }
        return emptyBoard;
    }

    public static Board testPawnEnPassant() {
        Board newBoard = new Board(8);
        Board.board[35] = new Pawn('W', 35);
        Board.board[11] = new EmptySpace(11);

        Board.board[36] = new Pawn('B', 36);
        EmptySpace emptySpace = new EmptySpace(36);
        BoardChanges.lastEntry = new BoardChanges(Board.board[36], 52, 36, emptySpace);
        Board.board[52] = new EmptySpace(52);
        return newBoard;
    }

    public static Board testRookAndEnemies() {
        Board emptyBoard = new Board();
        Board.board[46] = new Rook('W', 46);
        for (int i = 48; i <= 55; i++) {
            Board.board[i] = new Pawn('B', i);
        }
        return emptyBoard;
    }

    public static Board testPinnedPieceDiagonally() {
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

    public static Board testBRQatTheCorners() {
        Board board = new Board();
        for (int i = 0; i <= 63; i++) {
            Board.board[i] = new EmptySpace(i);
        }
        Board.board[32] = new Bishop('W', 32);
        Board.board[16] = new Queen('W', 16);
        Board.board[63] = new Queen('W', 63);
        Board.board[0] = new Queen('W', 0);
        Board.board[55] = new Rook('W', 55);
        return board;
    }

    public static Board testVerticalPin() {
        Board emptyBoard = new Board();
        for (int i = 0; i <= 63; i++) {
            Board.board[i] = new EmptySpace(i);
        }
        Board.board[4] = new King('W', 4);
        Board.board[36] = new Queen('B', 36);
        return emptyBoard;
    }

    public static Board testHorizontalPin() {
        Board emptyBoard = new Board();
        for (int i = 0; i <= 63; i++) {
            Board.board[i] = new EmptySpace(i);
        }
        Board.board[29] = new King('W', 29);
        Board.board[26] = new Queen('B', 26);
        return emptyBoard;
    }

    public static Board testCheckMate() {
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[52] = new EmptySpace(52);
        Board.board[13] = new EmptySpace(13);
        Board.board[30] = new Pawn('W', 30);
        Board.board[29] = new Pawn('B', 29);

        return board;
    }

    public static Board testQueenAndPawn() {
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

    public static Board testQueenAndPawn2() {
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);
        Board.board[11] = new Pawn('B', 11);
        Board.board[30] = new Pawn('W', 30);
        Board.board[29] = new Pawn('B', 29);
        Board.board[52] = new Queen('B', 52);

        return board;
    }

    public static Board testBishopNorhtSameFile() {
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);
        Board.board[11] = new Pawn('B', 11);
        Board.board[30] = new Pawn('W', 30);
        Board.board[29] = new Pawn('B', 29);
        Board.board[52] = new Bishop('B', 52);

        return board;
    }

    public static Board testCheckBlock() {
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

    public static Board testKnightCheckBlock() {
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);

        Board.board[10] = new Knight('W', 10);
        Board.board[29] = new Pawn('W', 29);
        Board.board[18] = new Bishop('B', 18);
        Board.board[36] = new Queen('B', 36);

        return board;
    }

    public static Board testQueenAttack2ndRank() {
        Board board = new Board(8);
        Board.board[14] = new EmptySpace(14);
        Board.board[13] = new EmptySpace(13);
        Board.board[12] = new EmptySpace(12);
        Board.board[59] = new EmptySpace(59);

        Board.board[10] = new Knight('W', 10);
        Board.board[29] = new Pawn('W', 29);
        Board.board[18] = new Queen('B', 18);
        Board.board[36] = new Bishop('B', 36);

        return board;
    }

    public static Board testBishop25CheckNoCM() {
        Board board = new Board(8);
        Board.board[52] = new EmptySpace(52);
        Board.board[11] = new EmptySpace(11);
        Board.board[36] = new Pawn('W', 36);

        return board;
    }

    public static Board testWQueenCheckNoCM() {
        Board board = new Board(8);
        Board.board[52] = new EmptySpace(52);
        Board.board[59] = new EmptySpace(59);
        Board.board[3] = new EmptySpace(3);
        Board.board[51] = new Queen('W', 51);
        Board.board[31] = new Queen('B', 31);

        return board;
    }

    public static Board testWQueenCM() {
        Board board = new Board(8);
        Board.board[3].erase();
        Board.board[11].erase();
        Board.board[60].erase();
        Board.board[52].erase();
        Board.board[3] = new EmptySpace(3);
        Board.board[11] = new EmptySpace(11);
        Board.board[60] = new EmptySpace(60);
        Board.board[35] = new Queen('W', 35);
        Board.board[52] = new King('B', 52);


        return board;
    }

    public static Board testPawnInFrontOfBlackPos() {
        Board board = new Board(8);
        Board.board[14].erase();
        Board.board[14] = new EmptySpace(14);
        Board.board[38] = new Pawn('W', 38);

        return board;
    }

    public static Board testBQueenHorizontalCheck() {
        Board board = new Board(8);
        Board.board[4].erase();
        Board.board[59].erase();
        Board.board[4] = new EmptySpace(4);
        Board.board[59] = new EmptySpace(59);
        Board.board[29] = new Queen('B', 29);
        Board.board[31] = new King('W', 31);


        return board;
    }

    public static Board testBQueenHorizontalBlocked() {
        Board board = new Board(8);
        Board.board[4].erase();
        Board.board[59].erase();
        Board.board[4] = new EmptySpace(4);
        Board.board[59] = new EmptySpace(59);
        Board.board[54] = new EmptySpace(54);
        Board.board[29] = new Queen('B', 29);
        Board.board[30] = new Pawn('W', 30);
        Board.board[31] = new King('W', 31);
        Board.board[38] = new Pawn('B', 38);


        return board;
    }

    public static Board testBQueenHorizontal2() {
        Board board = new Board(8);
        Board.board[4].erase();
        Board.board[59].erase();
        Board.board[55].erase();
        Board.board[4] = new EmptySpace(4);
        Board.board[55] = new EmptySpace(55);
        Board.board[59] = new EmptySpace(59);
        Board.board[54] = new EmptySpace(54);
        Board.board[29] = new Queen('B', 29);
        Board.board[30] = new Pawn('W', 30);
        Board.board[47] = new Pawn('B', 47);
        Board.board[39] = new King('W', 39);
        Board.board[38] = new Pawn('B', 38);


        return board;
    }

    public static Board testMinusOneError() {
        Board board = new Board(8);
        Board.board[4].erase();
        Board.board[59].erase();
        Board.board[54].erase();
        Board.board[14].erase();
        Board.board[4] = new EmptySpace(4);
        Board.board[54] = new EmptySpace(54);
        Board.board[59] = new EmptySpace(59);
        Board.board[14] = new EmptySpace(14);
        Board.board[30] = new Pawn('W', 30);
        Board.board[44] = new Queen('B', 44);
        Board.board[23] = new King('W', 23);
        Board.board[38] = new Pawn('B', 38);


        return board;
    }

    public static Board testPawnPromotion() {
        Board board = new Board();
        Board.board[55] = new Pawn('W', 55);
        Board.board[11] = new Pawn('W', 11);
        Board.board[60] = new King('B', 60);
        Board.board[4] = new King('W', 4);
        return board;
    }

    public static Board testPawnPromotionRook() {
        Board board = new Board();
        Board.board[60] = new King('B', 60);
        Board.board[4] = new King('W', 4);
        Board.board[63] = new Rook('W', 63);
        return board;
    }

    public static Board testPawnPromotionWithoutCheck() {
        Board board = new Board();
        Board.board[60] = new King('B', 60);
        Board.board[4] = new King('W', 4);
        Board.board[63] = new Rook('W', 63);
        return board;
    }

    public static Board testKingAndRookCM() {
        Board board = new Board();
        Board.board[31] = new King('B', 31);
        Board.board[29] = new King('W', 29);
        Board.board[62] = new Rook('W', 62);
        return board;
    }

    public static Board testQueenTakingOlnPawnBug() {
        return FENStringBoardGenerator("rnb1k2r/1p2pp1p/2pq1n1B/pQ1p4/3P4/5BP1/PPP1PP1P/RN4KR w LL - 4 5");
    }

    public static Board testFENString() {
        return FENStringBoardGenerator("rnbq1rk1/pppp1ppp/4pn2/8/1bPP4/2N5/PPQ1PPPP/R1B1KBNR w KQ - 4 5");
    }

    public static Board testKingMoveBug() {
        return FENStringBoardGenerator("2r2bn1/ppp2kpr/3NpP2/3p1QBp/8/2P1P3/PP1K1PPP/R4BNR b KQ - 4 5");
    }

    public static Board testKingBehindSquareBug() {
        return FENStringBoardGenerator("2R3n1/pp5Q/1k1pp3/3p3p/7B/2P1P3/PP1K1PPP/R4BNR w KQ - 4 5");
    }
    public static Board testFEN() {
        return FENStringBoardGenerator("rn1q1rk1/pbpp1ppp/1p2pn2/6B1/2PP4/P1Q1P3/1P3PPP/R3KBNR b KQ - 0 8");
    }

}
