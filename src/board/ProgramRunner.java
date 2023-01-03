package src.board;

import edu.princeton.cs.algs4.StdDraw;
import src.board.*;

public class ProgramRunner {
    public static int WIDTH = 64;
    public static int HEIGHT = 64;

    public static Board visualizeBoard(Board board) {
        BoardRender render = new BoardRender();
        render.initialize(WIDTH, HEIGHT, 4, 4);
        render.renderFrame(board);
        return board;
    }

    public static void startGame() {
        //this is structured like this because of a future user interface which will be
        //interacting with the keyboard to select menu options.
        while (true) {
            String typed = "";
            while (typed.length() < 1) {
                if (StdDraw.hasNextKeyTyped()) {
                    typed += StdDraw.nextKeyTyped();
                }
            }
            switch (typed.toUpperCase()) {
                //Quit control. :Q
                case ":":
                    String waitingForQ = "";
                    while (waitingForQ.length() < 1) {
                        if (StdDraw.hasNextKeyTyped()) {
                            waitingForQ += StdDraw.nextKeyTyped();
                        }
                    }
                    if (waitingForQ.equalsIgnoreCase("Q")) {
                        System.exit(0);
                    } else {
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void startTestGame(Board board) {
        while(true){
            if(StdDraw.isMousePressed()){
                board.clickOnPiece();
                break;
            }
        }
    }
}
