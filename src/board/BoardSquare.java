package src.board;
import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import src.piece.*;

import edu.princeton.cs.algs4.StdDraw;
import src.piece.*;


/**
 * The TETile object is used to represent a single tile in your world. A 2D array of tiles make up a
 * board, and can be drawn to the screen using the TERenderer class.
 *
 * All TETile objects must have a character, textcolor, and background color to be used to represent
 * the tile when drawn to the screen. You can also optionally provide a path to an image file of an
 * appropriate size (16x16) to be drawn in place of the unicode representation. If the image path
 * provided cannot be found, draw will fallback to using the provided character and color
 * representation, so you are free to use image tiles on your own computer.
 *
 * The provided TETile is immutable, i.e. none of its instance variables can change. You are welcome
 * to make your TETile class mutable, if you prefer.
 */

public class BoardSquare implements Serializable {
    private final char pieceAbbreviation;
    private final Piece pieceAtSquare;
    private final Color backgroundColor;
    private final String filepath;


    /**
     * Full constructor for TETile objects.
     * @param pieceAtSquare The piece positioned at the square.
     * @param backgroundColor The color of the square.
     * @param filepath Full path to image to be used for this tile. Must be correct size (16x16)
     */
    public BoardSquare(Piece pieceAtSquare, Color backgroundColor,String filepath) {
        this.pieceAbbreviation = pieceAtSquare.pieceAbbreviation();
        this.pieceAtSquare = pieceAtSquare;
        this.backgroundColor = backgroundColor;
        if(pieceAbbreviation != 'E'){
            this.filepath = Utils.join(Main.CWD, pieceAtSquare.pieceImage()).getPath();
        }
        else{
            this.filepath = pieceAtSquare.pieceImage();
        }
    }

    /**
     * Constructor without filepath. In this case, filepath will be null, so when drawing, we
     * will not even try to draw an image, and will instead use the provided character and colors.
     * @param pieceAtSquare The color of the character itself.
     * @param backgroundColor The color drawn behind the character.
     */
    public BoardSquare(Piece pieceAtSquare, Color backgroundColor) {
        this.pieceAbbreviation = pieceAtSquare.pieceAbbreviation();
        this.pieceAtSquare = pieceAtSquare;
        this.backgroundColor = backgroundColor;
        this.filepath = null;
    }

    /**
     * Creates a copy of TETile t, except with given textColor.
     * @param t tile to copy
     * @param pieceAtSquare foreground color for tile copy
     */
    public BoardSquare(BoardSquare t, Piece pieceAtSquare) {
        this(pieceAtSquare, t.backgroundColor, t.filepath);
    }


    /**
     * Draws the tile to the screen at location x, y. If a valid filepath is provided,
     * we draw the image located at that filepath to the screen. Otherwise, we fall
     * back to the character and color representation for the tile.
     *
     * Note that the image provided must be of the right size (16x16). It will not be
     * automatically resized or truncated.
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

    /** Character representation of the tile. Used for drawing in text mode.
     * @return character representation
     */
    public char pieceAbbreviation() {
        return pieceAbbreviation;
    }

    /**
     * Description of the tile. Useful for displaying mouseover text or
     * testing that two tiles represent the same type of thing.
     * @return description of the tile
     */


//    /**
//     * Creates a copy of the given tile with a slightly different text color. The new
//     * color will have a red value that is within dr of the current red value,
//     * and likewise with dg and db.
//     * @param t the tile to copy
//     * @param dr the maximum difference in red value
//     * @param dg the maximum difference in green value
//     * @param db the maximum difference in blue value
//     * @param r the random number generator to use
//     */
//    public static BoardSquare colorVariant(BoardSquare t, int dr, int dg, int db, Random r) {
//        Color oldColor = t.textColor;
//        int newRed = newColorValue(oldColor.getRed(), dr, r);
//        int newGreen = newColorValue(oldColor.getGreen(), dg, r);
//        int newBlue = newColorValue(oldColor.getBlue(), db, r);
//
//        Color c = new Color(newRed, newGreen, newBlue);
//
//        return new BoardSquare(t, c);
//    }

    private static int newColorValue(int v, int dv, Random r) {
        int rawNewValue = v + RandomUtils.uniform(r, -dv, dv + 1);

        // make sure value doesn't fall outside of the range 0 to 255.
        int newValue = Math.min(255, Math.max(0, rawNewValue));
        return newValue;
    }

    /**
     * Converts the given 2D array to a String. Handy for debugging.
     * Note that since y = 0 is actually the bottom of your world when
     * drawn using the tile rendering engine, this print method has to
     * print in what might seem like backwards order (so that the 0th
     * row gets printed last).
     * @param world the 2D world to print
     * @return string representation of the world
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
     * Makes a copy of the given 2D tile array.
     * @param tiles the 2D array to copy
     **/
    public static BoardSquare[][] copyOf(BoardSquare[][] tiles) {
        if (tiles == null) {
            return null;
        }

        BoardSquare[][] copy = new BoardSquare[tiles.length][];

        int i = 0;
        for (BoardSquare[] column : tiles) {
            copy[i] = Arrays.copyOf(column, column.length);
            i += 1;
        }

        return copy;
    }
    public static Piece selectPiece(BoardSquare[][] BS){
        return Mouse.scanMousePositionBS(BS);
    }

    public Piece pieceAtSquare() {
        return this.pieceAtSquare;
    }
}