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
    private static final double BORDER = 0.00;
    public static boolean isMousePressed = false;
    private static double mouseX = 0;
    private static double mouseY = 0;
    private static double xmin, ymin, xmax, ymax;
    private static final int DEFAULT_SIZE = 512;
    private static int width  = DEFAULT_SIZE;
    private static int height = DEFAULT_SIZE;
    public static double   UserX(double x) { return xmin + x * (xmax - xmin) / width;    }
    public static double   UserY(double y) { return ymax - y * (ymax - ymin) / height;   }

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
    public static Piece scanMousePositionBS (BoardSquare[][] BS){
        int x = (int) Math.floor(StdDraw.mouseX()/32);
        int y = (int) Math.floor(StdDraw.mouseY()/32);

        if (x >= WIDTH || y >= HEIGHT) {
            return null;
        }
        return BS[x][y].pieceAtSquare();
    }
    public static boolean isMousePressed() {
        return isMousePressed;
    }

    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        synchronized (mouseLock){

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        synchronized (mouseLock) {
            mouseX = Mouse.UserX(e.getX());
            mouseY = Mouse.UserY(e.getY());
            isMousePressed = true;
        }
    }



    @Override
    public void mouseReleased(MouseEvent e) {
        synchronized (mouseLock){
            isMousePressed = false;
        }
    }
    public static void setXscale(double min, double max) {
        double size = max - min;
        if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
        synchronized (mouseLock) {
            xmin = min - BORDER * size;
            xmax = max + BORDER * size;
        }
    }

    /**
     * Sets the <em>y</em>-scale to the specified range.
     *
     * @param  min the minimum value of the <em>y</em>-scale
     * @param  max the maximum value of the <em>y</em>-scale
     * @throws IllegalArgumentException if {@code (max == min)}
     */
    public static void setYscale(double min, double max) {
        double size = max - min;
        if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
        synchronized (mouseLock) {
            ymin = min - BORDER * size;
            ymax = max + BORDER * size;
        }
    }

    /**
     * Sets both the <em>x</em>-scale and <em>y</em>-scale to the (same) specified range.
     *
     * @param  min the minimum value of the <em>x</em>- and <em>y</em>-scales
     * @param  max the maximum value of the <em>x</em>- and <em>y</em>-scales
     * @throws IllegalArgumentException if {@code (max == min)}
     */
    public static void setScale(double min, double max) {
        double size = max - min;
        if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
        synchronized (mouseLock) {
            xmin = min - BORDER * size;
            xmax = max + BORDER * size;
            ymin = min - BORDER * size;
            ymax = max + BORDER * size;
        }
    }
}

