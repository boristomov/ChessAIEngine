package src.board;

import edu.princeton.cs.algs4.StdDraw;
import src.piece.Piece;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


/**
 * The BoardSquare object is used to represent a single square on the board.
 * A 2D array of tiles makes up a board, and can be drawn to the screen using the BoardRenderer class.
 */

public class BoardSquare implements Serializable {
    private final char pieceAbbreviation;
    private final Piece pieceAtSquare;
    private final Color backgroundColor;
    private final String filepath;


    /**
     * Full constructor for BoardSquare objects.
     *
     * @param pieceAtSquare   The piece positioned at the square.
     * @param backgroundColor The color of the square.
     * @param filepath        Full path to image to be used for this tile. Must be correct size (16x16)
     */
    public BoardSquare(Piece pieceAtSquare, Color backgroundColor, String filepath) {
        this.pieceAbbreviation = pieceAtSquare.pieceAbbreviation();
        this.pieceAtSquare = pieceAtSquare;
        this.backgroundColor = backgroundColor;
        if (pieceAbbreviation != 'E') {
            this.filepath = Utils.join(Main.CWD, pieceAtSquare.pieceImage()).getPath();
        } else {
            this.filepath = pieceAtSquare.pieceImage();
        }
    }

    /**
     * Constructor without filepath.
     *
     * @param pieceAtSquare   The piece positioned at the square.
     * @param backgroundColor The color of the square.
     */
    public BoardSquare(Piece pieceAtSquare, Color backgroundColor) {
        this.pieceAbbreviation = pieceAtSquare.pieceAbbreviation();
        this.pieceAtSquare = pieceAtSquare;
        this.backgroundColor = backgroundColor;
        this.filepath = null;
    }

    /**
     * Returns piece under the mouse when it is clicked.
     */
    public static Piece selectPiece(BoardSquare[][] BS) {
        return Mouse.scanMousePositionBS(BS);
    }

    /**
     * Converts the given 2D array to a String. Handy for debugging.
     * Note that since y = 0 is actually the bottom of the board when
     * drawn using the board rendering engine, this print method has to
     * print in what might seem like backwards order (so that the 0th
     * row gets printed last).
     *
     * @param world the 2D board to print
     * @return string representation of the board
     */
    public static String toString(BoardSquare[][] world) {
        int width = world.length;
        int height = world[0].length;
        StringBuilder sb = new StringBuilder();

        for (int y = height - 1; y >= 0; y -= 1) {
            for (int x = 0; x < width; x += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                sb.append(world[x][y].pieceAbbreviation());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Makes a copy of the given 2D board square array.
     *
     * @param BS the 2D array to copy
     **/
    public static BoardSquare[][] copyOf(BoardSquare[][] BS) {
        if (BS == null) {
            return null;
        }

        BoardSquare[][] copy = new BoardSquare[BS.length][];

        int i = 0;
        for (BoardSquare[] column : BS) {
            copy[i] = Arrays.copyOf(column, column.length);
            i += 1;
        }

        return copy;
    }

    /**
     * Draws the square to the screen at location x, y. If a valid filepath is provided,
     * we draw the image located at that filepath to the screen.
     * Note that the image provided must be of the right size (16x16). It will not be
     * automatically resized or truncated.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void draw(double x, double y) {
        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledSquare(x, y, 4);
        if (!Objects.equals(filepath, "")) {
            try {
                StdDraw.picture(x, y, filepath);
            } catch (IllegalArgumentException e) {
                // Exception happens because the file can't be found. In this case, fail silently
                // and just use the character and background color for the tile.
            }
        }
    }

    /**
     * Draws promotion menu.
     */
    public void drawPromotionMenu(double x, double y) {
        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledSquare(x, y, 16);
        if (!Objects.equals(filepath, "")) {
            try {
                StdDraw.picture(x, y, filepath);
            } catch (IllegalArgumentException e) {
                // Exception happens because the file can't be found. In this case, fail silently
                // and just use the character and background color for the tile.
            }
        }
    }

    /**
     * Returns the char referring to a piece on the board.
     */
    public char pieceAbbreviation() {
        return pieceAbbreviation;
    }

    public Piece pieceAtSquare() {
        return this.pieceAtSquare;
    }
}