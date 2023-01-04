package src.board;
import java.io.File;
import src.piece.*;


public class Main {
    public static final File CWD = new File(System.getProperty("user.dir"));

    public static void main(String[] args) {
        Board board = TestBoard.testPawnEnPassant();
        System.out.println(board.toString());
        ProgramRunner.visualizeBoard(board);
        ProgramRunner.startTestGame(board);
    }
}




