package src.board;

import edu.princeton.cs.algs4.StdDraw;
import src.piece.Piece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static src.board.ProgramRunner.HEIGHT;
import static src.board.ProgramRunner.WIDTH;

public class Mouse implements MouseListener {

    private static final int DEFAULT_SIZE = 512;
    public static Object mouseLock = new Object();
    public static boolean isMousePressed = false;
    private static final int width = DEFAULT_SIZE;
    private static final int height = DEFAULT_SIZE;


    /**
     * Overrides StdDraw's method.
     * Returns the name of the object under the mouse pointer.
     */
    public static Piece scanMousePosition(Board board) {
        int x = (int) Math.floor(StdDraw.mouseX() / Board.Size);
        int y = (int) Math.floor(StdDraw.mouseY() / Board.Size);

        if (x >= WIDTH || y >= HEIGHT) {
            return null;
        }
        BoardSquare[][] BS = BoardRender.BoardToBSConverter(board);
        return BS[x][y].pieceAtSquare();
    }
    /**
     * Overrides StdDraw's method.
     * Returns the name of the object under the mouse pointer.
     * Uses a 2d array of board squares as an intput.
     */
    public static Piece scanMousePositionBS(BoardSquare[][] BS) {
        int x = (int) Math.floor(StdDraw.mouseX() / 32);
        int y = (int) Math.floor(StdDraw.mouseY() / 32);

        if (x >= WIDTH || y >= HEIGHT) {
            return null;
        }
        return BS[x][y].pieceAtSquare();
    }

    /**
     * Those methods may be implemented in case the current standard mouse interaction library
     * I am using does not work or does not perform well enough to support drag and drops in the
     * future.
     * @param e the event to be processed
     */
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        synchronized (mouseLock) {
            isMousePressed = false;
        }
    }
}

