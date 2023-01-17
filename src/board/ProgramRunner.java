package src.board;

import edu.princeton.cs.algs4.StdDraw;
import src.piece.*;

import java.util.HashSet;

public class ProgramRunner {
    /**
     * JFrame window dimensions.
     */
    public static int WIDTH = 64;
    public static int HEIGHT = 64;
    /**
     * Squares of the board where possible moves are projected once a piece is clicked.
     */
    public static HashSet<Integer> movesToPutShadowOn;
    /**
     * Current selected piece.
     */
    public static Piece selectedPiece = null;

    /**
     * Methods for visualizing the board given different inputs. Some of them
     * use different drawing functions depending on whether they are portraying the
     * board or the pop-up menu for promoting pawns.
     */
    public static void visualizeBoard(Board board) {
        BoardRender render = new BoardRender();
        render.initialize(WIDTH, HEIGHT, 4, 4);
        render.renderFrame(board);
    }
    public static void visualizeBoardBS(BoardSquare[][] BS) {
        BoardRender render = new BoardRender();
        render.initialize(WIDTH, HEIGHT, 4, 4);
        render.renderFrame(BS);
    }
    public static void visualizeBoardBSPromotion(BoardSquare[][] BS) {
        BoardRender render = new BoardRender();
        render.initialize(WIDTH, HEIGHT, 16, 16);
        render.renderFramePromotion(BS);
    }

    /**
     * This will be the main start menu after the HUV and user interface are implemented.
     */
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

    /**
     * Function which runs the test games for now.
     */

    public static void startTestGame(Board board) throws CloneNotSupportedException {
        while(true) {
            while(selectedPiece == null){
                if(StdDraw.isMousePressed()) {
                    StdDraw.pause(300);
                    StdDraw.enableDoubleBuffering();
                     board.clickOnPiece(Main.turnColor);
                    StdDraw.pause(300);
                }
            }
            StdDraw.show();
            boolean completed = false;
            while(!completed) {
                if (StdDraw.isMousePressed()) {
                    StdDraw.pause(300);
                    completed = board.confirmMove(selectedPiece, Main.turnColor, movesToPutShadowOn);
                    StdDraw.pause(300);
                }
            }
            selectedPiece = null;
        }
    }

}
