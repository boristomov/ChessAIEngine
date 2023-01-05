package src.board;

import edu.princeton.cs.algs4.*;
import src.piece.*;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.plaf.basic.BasicButtonListener;
import static src.board.ProgramRunner.*;

public class Mouse implements MouseListener {

    public static Object mouseLock = new Object();

    public static boolean isMousePressed = false;

        /**
         * Overrides StdDraw's method.
         * Returns the name of the object under the mouse pointer.
         */
        public static Piece scanMousePosition (Board board){
            int x = (int) Math.floor(StdDraw.mouseX()/Board.Size);
            int y = (int) Math.floor(StdDraw.mouseY()/Board.Size);

            if (x >= WIDTH || y >= HEIGHT) {
                return null;
            }
            BoardSquare[][] BS = BoardRender.BoardToBSConverter(board);
            return BS[x][y].pieceAtSquare();
        }
    public static boolean isMousePressed() {
        synchronized (mouseLock) {
            return isMousePressed;
        }
    }

    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }
}

