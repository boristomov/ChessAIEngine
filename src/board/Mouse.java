package src.board;

import edu.princeton.cs.algs4.*;
import src.piece.*;


import static src.board.ProgramRunner.*;

public class Mouse {



        /**
         * Overrides StdDraw's method.
         * Returns the name of the object under the mouse pointer.
         */
        public static Piece scanMousePosition (Board board){
            int x = (int) Math.floor(StdDraw.mouseX()/Board.Size);
            int y = (int) Math.floor(StdDraw.mouseY()/Board.Size);

            if (x >= WIDTH || y >= HEIGHT - 3) {
                return null;
            }
            BoardSquare[][] BS = BoardRender.BoardToBSConverter(board);
            return BS[x][y].pieceAtSquare();
        }

}

