package src.board;
import java.io.File;

import edu.princeton.cs.algs4.StdDraw;
import src.piece.*;


public class Main {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static char turnColor = 'W';
    public static void main(String[] args) throws CloneNotSupportedException {
        Board board = TestBoard.testBQueenHorizontalCheck();
        System.out.println(board.toString());
        ProgramRunner.visualizeBoard(board);
        ProgramRunner.startTestGame(board);

    }
}




