package src.board;
import java.io.File;

import edu.princeton.cs.algs4.StdDraw;
import src.piece.*;


public class Main {
    /**
     * Project directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * Current turn to play.
     */
    public static char turnColor = 'W';

    /**
     * Calls the necessary functions to start the game. This method will be further adjusted when the
     * user interface is complete, so that users can navigate using menu and keyboard/ mouse commands.
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Board board = TestBoard.testPawnPromotion();
        System.out.println(board.toString());
        ProgramRunner.visualizeBoard(board);
        ProgramRunner.startTestGame(board);

    }
}




