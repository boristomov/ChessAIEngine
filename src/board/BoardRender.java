package src.board;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * Utility class for rendering tiles.
 */
public class BoardRender {
    private static final int BoardSize = Board.Size;
    public static String mouse;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    /**
     * Helper method which converts a 1d array Board to a 2d array of BoardSquares.
     */
    public static BoardSquare[][] BoardToBSConverter(Board board) {
        BoardSquare[][] BS = new BoardSquare[Board.Size][Board.Size];
        for (int i = 0; i <= Board.board.length - 1; i++) {
            int file = i % 8;
            int rank = (int) Math.floor(i / 8);
            Color squareColor = Color.LIGHT_GRAY;
            if (file % 2 == rank % 2) {
                squareColor = Color.DARK_GRAY;
            }
            BS[file][rank] = new BoardSquare(Board.board[i], squareColor, Board.board[i].pieceImage());
        }
        return BS;
    }

    /**
     * Same functionality as the other initialization method. The only difference is that the xOff
     * and yOff parameters will change where the renderFrame method starts drawing.
     *
     * @param w width of the window in tiles
     * @param h height of the window in tiles.
     */
    public void initialize(int w, int h, int xOff, int yOff) {
        this.width = w;
        this.height = h;
        this.xOffset = xOff;
        this.yOffset = yOff;
        StdDraw.setCanvasSize(width * BoardSize, height * BoardSize);
        Font font = new Font("Monaco", Font.BOLD, BoardSize - 2);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    /**
     * Initializes StdDraw parameters and launches the StdDraw window. w and h are the
     * width and height of the world in number of tiles. If the BoardSquares[][] array that
     * is passed to renderFrame is smaller than this, then extra blank space will be left
     * on the right and top edges of the frame.
     *
     * @param w width of the window in tiles
     * @param h height of the window in tiles.
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0);
    }

    /**
     * Takes in a 1d array of BoardSquare objects and renders the board on the screen, starting from
     * xOffset and yOffset.
     */
    public void renderFrame(Board board) {
        BoardSquare[][] BS = BoardToBSConverter(board);
        int numXTiles = BS.length;
        int numYTiles = BS[0].length;
        StdDraw.clear(new Color(0, 0, 0));
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (BS[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                BS[x][y].draw(x * 8 + xOffset, y * 8 + yOffset);
            }
        }
        StdDraw.show();
    }

    /**
     * Takes in a 2d array of BoardSquare objects and renders the 2d array to the screen, starting from
     * xOffset and yOffset.
     */

    public void renderFrame(BoardSquare[][] BS) {
        int numXTiles = BS.length;
        int numYTiles = BS[0].length;
        StdDraw.clear(new Color(0, 0, 0));
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (BS[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                BS[x][y].draw(x * 8 + xOffset, y * 8 + yOffset);
            }
        }
        StdDraw.show();
    }

    /**
     * Render which specifically takes care of the promotion window that pops up.
     *
     * @param BS 2d array of BoardSquare tiles.
     */
    public void renderFramePromotion(BoardSquare[][] BS) {
        int numXTiles = BS.length;
        int numYTiles = BS[0].length;
        StdDraw.clear(new Color(0, 0, 0));
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (BS[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                BS[x][y].drawPromotionMenu(x * 32 + xOffset, y * 32 + yOffset);
            }
        }

        StdDraw.show();
    }

}