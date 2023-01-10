package src.board;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.awt.Font;

//import static src.board.Board.board;
import src.board.*;

/**
 * Utility class for rendering tiles. You do not need to modify this file. You're welcome
 * to, but be careful. We strongly recommend getting everything else working before
 * messing with this renderer, unless you're trying to do something fancy like
 * allowing scrolling of the screen or tracking the avatar or something similar.
 */
public class BoardRender {
    private static final int BoardSize = Board.Size;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    public static String mouse;

    /**
     * Same functionality as the other initialization method. The only difference is that the xOff
     * and yOff parameters will change where the renderFrame method starts drawing. For example,
     * if you select w = 60, h = 30, xOff = 3, yOff = 4 and then call renderFrame with a
     * TETile[50][25] array, the renderer will leave 3 tiles blank on the left, 7 tiles blank
     * on the right, 4 tiles blank on the bottom, and 1 tile blank on the top.
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
     * width and height of the world in number of tiles. If the TETile[][] array that you
     * pass to renderFrame is smaller than this, then extra blank space will be left
     * on the right and top edges of the frame. For example, if you select w = 60 and
     * h = 30, this method will create a 60 tile wide by 30 tile tall window. If
     * you then subsequently call renderFrame with a TETile[50][25] array, it will
     * leave 10 tiles blank on the right side and 5 tiles blank on the top side. If
     * you want to leave extra space on the left or bottom instead, use the other
     * initializatiom method.
     * @param w width of the window in tiles
     * @param h height of the window in tiles.
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0);
    }

    /**
     * Takes in a 2d array of TETile objects and renders the 2d array to the screen, starting from
     * xOffset and yOffset.
     *
     * If the array is an NxM array, then the element displayed at positions would be as follows,
     * given in units of tiles.
     *
     *              positions   xOffset |xOffset+1|xOffset+2| .... |xOffset+world.length
     *
     * startY+world[0].length   [0][M-1] | [1][M-1] | [2][M-1] | .... | [N-1][M-1]
     *                    ...    ......  |  ......  |  ......  | .... | ......
     *               startY+2    [0][2]  |  [1][2]  |  [2][2]  | .... | [N-1][2]
     *               startY+1    [0][1]  |  [1][1]  |  [2][1]  | .... | [N-1][1]
     *                 startY    [0][0]  |  [1][0]  |  [2][0]  | .... | [N-1][0]
     *
     * By varying xOffset, yOffset, and the size of the screen when initialized, you can leave
     * empty space in different places to leave room for other information, such as a GUI.
     * This method assumes that the xScale and yScale have been set such that the max x
     * value is the width of the screen in tiles, and the max y value is the height of
     * the screen in tiles.
     * @param board the 2D TETile[][] array to render
     */
    //In order to make this able to work: Write a function which converts the Board instance into a 2d array of BoardSquares;
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

        /**
         * User information bar: Evaluations. pieces taken, moves, etc.
         if(HUD){
         StdDraw.setPenColor(Color.WHITE);
         Font font = new Font("Monaco", Font.BOLD, 15);
         StdDraw.setFont(font);
         StdDraw.text(Engine.WIDTH - 5 , Engine.HEIGHT - 2, "Object: " + Engine.scanMousePosition(world).toUpperCase());
         StdDraw.text(Engine.WIDTH - 45 , Engine.HEIGHT - 2, "Name: " + Engine.getAvatarName().toUpperCase());
         StdDraw.text(Engine.WIDTH - 30 , Engine.HEIGHT - 2, "HEALTH: " + Engine.heartHUD());
         StdDraw.text(Engine.WIDTH - 20 , Engine.HEIGHT - 2, "POINTS: " + GamePlay.getCounter());
         StdDraw.line(0,Engine.HEIGHT - 3, Engine.WIDTH, Engine.HEIGHT - 3);
         }
         */
        StdDraw.show();
    }
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
    public static BoardSquare[][] BoardToBSConverter(Board board){
        BoardSquare[][] BS = new BoardSquare[Board.Size][Board.Size];
        for(int i = 0; i <= Board.board.length - 1; i++){
            int file = i % 8;
            int rank = (int) Math.floor(i / 8);
            Color squareColor = Color.LIGHT_GRAY;
            if( file % 2 == rank % 2){
                squareColor = Color.DARK_GRAY;
            }
            BS[file][rank] = new BoardSquare(Board.board[i], squareColor, Board.board[i].pieceImage());
        }
        return BS;
    }

}