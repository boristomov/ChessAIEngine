package src.board;

import src.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AttacksOnKing {
    /**
     * Stored pinned pieces as keys (i.e. pieces who lay on the line of attack from on opponent piece.)
     * The values represent sequences containing unique square indexes. Those indexes are then used to
     * outline the array of allowed moves pinned piece can make.
     */
    public static HashMap<Piece, HashSet<Integer>> pPiecesAndAllowedMoves = new HashMap<>();
    /**
     * Current list of enemy pieces having a direct attack on the king.
     */
    public static ArrayList<Piece> checkingPieces = new ArrayList<>();
    /**
     * Current locations of the white and the black king.
     */
    public static int WkingLocation;
    public static int BkingLocation;

    /**
     * Function which checks for pinned pieces in a given position on the board.
     * When it finds those it appends them to pPiecesAndAllowedMoves and checkingPieces.
     *
     * @param board Board.
     * @param kingLocation location of the king whose color matches the current move color.
     */
    public static void checkForPins(Board board, int kingLocation) {
        pinnedPieceN(board, kingLocation);
        pinnedPieceS(board, kingLocation);
        pinnedPieceW(board, kingLocation);
        pinnedPieceE(board, kingLocation);
        pinnedPieceNW(board, kingLocation);
        pinnedPieceNE(board, kingLocation);
        pinnedPieceSW(board, kingLocation);
        pinnedPieceSE(board, kingLocation);
    }

    /**
     * Functions which creates lines connecting the king to any attacking opponent piece who lies on the
     * same direction horizontally, vertically and diagonally. If this piece belongs to a class which is
     * capable of attacking the king along the given direction, the program counts the number of friendly pieces
     * staying in between the king and the enemy piece. If there is only 1, then the friendly piece is
     * added to the pinned pieces set and map.
     *
     * @param board Board.
     * @param kingLocation location of the king whose color matches the current move color.
     */
    private static void pinnedPieceN(Board board, int kingLocation) {
        Piece adjacentPieceN = board.getAdjacentPieceN(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceN == null) {
            return;
        }
        while (adjacentPieceN.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceN.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceN);
            }
            adjacentPieceN = board.getAdjacentPieceN(adjacentPieceN.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceN == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceN.getClass().equals(Queen.class) || adjacentPieceN.getClass().equals(Rook.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeS(adjacentPieceN));
        }
    }

    private static void pinnedPieceS(Board board, int kingLocation) {
        Piece adjacentPieceS = board.getAdjacentPieceS(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceS == null) {
            return;
        }
        while (adjacentPieceS.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceS.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceS);
            }
            adjacentPieceS = board.getAdjacentPieceS(adjacentPieceS.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceS == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceS.getClass().equals(Queen.class) || adjacentPieceS.getClass().equals(Rook.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeN(adjacentPieceS));
        }
    }

    private static void pinnedPieceW(Board board, int kingLocation) {
        Piece adjacentPieceW = board.getAdjacentPieceW(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceW == null) {
            return;
        }
        while (adjacentPieceW.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceW.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceW);
            }
            adjacentPieceW = board.getAdjacentPieceW(adjacentPieceW.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceW == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceW.getClass().equals(Queen.class) || adjacentPieceW.getClass().equals(Rook.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeE(adjacentPieceW));
        }
    }

    private static void pinnedPieceE(Board board, int kingLocation) {
        Piece adjacentPieceE = board.getAdjacentPieceE(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceE == null) {
            return;
        }
        while (adjacentPieceE.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceE.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceE);
            }
            adjacentPieceE = board.getAdjacentPieceE(adjacentPieceE.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceE == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceE.getClass().equals(Queen.class) || adjacentPieceE.getClass().equals(Rook.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeW(adjacentPieceE));
        }

    }

    private static void pinnedPieceNW(Board board, int kingLocation) {
        Piece adjacentPieceNW = board.getAdjacentPieceNW(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceNW == null) {
            return;
        }
        while (adjacentPieceNW.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceNW.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceNW);
            }
            adjacentPieceNW = board.getAdjacentPieceNW(adjacentPieceNW.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceNW == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceNW.getClass().equals(Queen.class) || adjacentPieceNW.getClass().equals(Bishop.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeSE(adjacentPieceNW));
        }
    }

    private static void pinnedPieceNE(Board board, int kingLocation) {
        Piece adjacentPieceNE = board.getAdjacentPieceNE(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceNE == null) {
            return;
        }
        while (adjacentPieceNE.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceNE.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceNE);
            }
            adjacentPieceNE = board.getAdjacentPieceNE(adjacentPieceNE.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceNE == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceNE.getClass().equals(Queen.class) || adjacentPieceNE.getClass().equals(Bishop.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeSW(adjacentPieceNE));
        }
    }

    private static void pinnedPieceSW(Board board, int kingLocation) {
        Piece adjacentPieceSW = board.getAdjacentPieceSW(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceSW == null) {
            return;
        }
        while (adjacentPieceSW.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceSW.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceSW);
            }
            adjacentPieceSW = board.getAdjacentPieceSW(adjacentPieceSW.locationNumber()); //looks for the adjacent square to the one just checked.
            if (adjacentPieceSW == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceSW.getClass().equals(Queen.class) || adjacentPieceSW.getClass().equals(Bishop.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeNE(adjacentPieceSW));
        }
    }

    private static void pinnedPieceSE(Board board, int kingLocation) {
        Piece adjacentPieceSE = board.getAdjacentPieceSE(kingLocation);
        Piece king = Board.board[kingLocation];
        ArrayList<Piece> potentialPPieces = new ArrayList<>();
        if (adjacentPieceSE == null) {
            return;
        }
        while (adjacentPieceSE.pieceColor() != Board.getOppositeColor(king)) {
            if (adjacentPieceSE.pieceColor() == king.pieceColor()) {
                potentialPPieces.add(adjacentPieceSE);
            }
            adjacentPieceSE = board.getAdjacentPieceSE(adjacentPieceSE.locationNumber());
            if (adjacentPieceSE == null) {
                return;
            }
        }
        if (potentialPPieces.size() == 1 && (adjacentPieceSE.getClass().equals(Queen.class) || adjacentPieceSE.getClass().equals(Bishop.class))) {
            pPiecesAndAllowedMoves.put(potentialPPieces.get(0), dangerScopeNW(adjacentPieceSE));
        }
    }

    //Methods restricting the movement of the pinned pieces.

    /**
     * Function which gives a set of square indexes representing the scope of attack of the
     * discovered above (within the pinnedPiece methods) enemy piece. This function is utilized
     * when pushing the hashset values of allowed moves in the pPiecesAndAllowedMoves hashmap.
     */
    public static HashSet<Integer> dangerScopeN(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while (moveLocation <= 63 && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            moveLocation += 8;
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> dangerScopeS(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while (moveLocation >= 0 && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            moveLocation -= 8;
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> dangerScopeE(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 1;
        while (moveLocation <= 63 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceRank = Board.getPieceRank(moveLocation);
            moveLocation += 1;
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> dangerScopeW(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 1;
        while (moveLocation >= 0 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceRank = Board.getPieceRank(moveLocation);
            moveLocation -= 1;
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> dangerScopeNW(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 7;
        while (moveLocation <= 63 && Board.getPieceFile(moveLocation) + 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation += 7;
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> dangerScopeNE(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 9;
        while (moveLocation <= 63 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation += 9;
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> dangerScopeSW(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 9;
        while (moveLocation >= 0 && Board.getPieceFile(moveLocation) + 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation -= 9;
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> dangerScopeSE(Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        possibleDestinations.add(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 7;
        while (moveLocation >= 0 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && !Board.board[moveLocation].getClass().equals(King.class)) {
            possibleDestinations.add(moveLocation);
            currentAdjPieceFile = Board.getPieceFile(moveLocation);
            moveLocation -= 7;
        }
        return possibleDestinations;
    }

    //Methods restricting the movement of the king.

    /**
     * Since dangerScope is used only for finding path across pinned pieces to the king, arrayScope(Direction)
     * includes the location of the square past the kind in order to restrict its (the king rather
     * than the movement of the pinned pieces) movement when checked,
     * so that the king cannot move further in the same direction of attack.
     */
    public static HashSet<Integer> arrayScopeN(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while (moveLocation <= 63) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                moveLocation += 8;
            } else {
                moveLocation += 8;
                if (moveLocation <= 63 && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> arrayScopeS(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        int moveLocation = piece.locationNumber();
        while (moveLocation >= 0) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                moveLocation -= 8;
            } else {
                moveLocation -= 8;
                if (moveLocation >= 0 && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> arrayScopeE(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 1;
        while (moveLocation <= 63 && Board.getPieceRank(moveLocation) == currentAdjPieceRank) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                currentAdjPieceRank = Board.getPieceRank(moveLocation);
                moveLocation += 1;
            } else {
                moveLocation += 1;
                if (moveLocation <= 63 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> arrayScopeW(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceRank = Board.getPieceRank(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 1;
        while (moveLocation >= 0 && Board.getPieceRank(moveLocation) == currentAdjPieceRank) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                currentAdjPieceRank = Board.getPieceRank(moveLocation);
                moveLocation -= 1;
            } else {
                moveLocation -= 1;
                if (moveLocation >= 0 && Board.getPieceRank(moveLocation) == currentAdjPieceRank && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> arrayScopeNW(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 7;
        while (moveLocation <= 63 && Board.getPieceFile(moveLocation) + 1 == currentAdjPieceFile) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation += 7;
            } else {
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation += 7;
                if (moveLocation <= 63 && Board.getPieceRank(moveLocation) + 1 == currentAdjPieceFile && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> arrayScopeNE(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() + 9;
        while (moveLocation <= 63 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation += 9;
            } else {
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation += 9;
                if (moveLocation <= 63 && Board.getPieceRank(moveLocation) - 1 == currentAdjPieceFile && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> arrayScopeSW(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 9;
        while (moveLocation >= 0 && Board.getPieceFile(moveLocation) + 1 == currentAdjPieceFile) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation -= 9;
            } else {
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation -= 9;
                if (moveLocation >= 0 && Board.getPieceFile(moveLocation) + 1 == currentAdjPieceFile
                        && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    public static HashSet<Integer> arrayScopeSE(Board board, Piece piece) {
        HashSet<Integer> possibleDestinations = new HashSet<>();
        possibleDestinations.add(piece.locationNumber());
        int currentAdjPieceFile = Board.getPieceFile(piece.locationNumber());
        possibleDestinations.add(piece.locationNumber());
        int moveLocation = piece.locationNumber() - 7;
        while (moveLocation >= 0 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile) {
            if (!Board.board[moveLocation].getClass().equals(King.class)) {
                possibleDestinations.add(moveLocation);
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation -= 7;
            } else {
                currentAdjPieceFile = Board.getPieceFile(moveLocation);
                moveLocation -= 7;
                if (moveLocation >= 0 && Board.getPieceFile(moveLocation) - 1 == currentAdjPieceFile && Board.board[moveLocation].getClass().equals(EmptySpace.class)) {
                    possibleDestinations.add(moveLocation);
                    break;
                }
            }
        }
        return possibleDestinations;
    }

    /**
     * Function which returns whether the just completed move has resulted into a CheckMate
     * on the opponent. If true, the game has to stop.
     */
    public static boolean isCheckMate(Board board, char turnColor, Piece king) throws CloneNotSupportedException {
        // use the function for tracked by...
        if (isPieceTargeted(board, Board.getOppositeColorChar(turnColor), king.locationNumber())) {
            HashSet<Integer> allowedMoves = applyDanSfunc(board, king);
            if (king.generatePossibleMoves(board, allowedMoves).isEmpty()) {
                // I think this king.loc arg is wrong. Use istracked func
                return checkingPieces.size() > 0 && !canCheckBeBlocked(board, allowedMoves, king, turnColor);
            }
        }
        return false;
    }

    /**
     * Function which returns a boolean value stating whether a friendly piece could block the
     * attacked towards the King when it is in check. It is useful when determining if a move has
     * resulted in a CheckMate.
     *
     * @param dangerScope path of the enemy piece attack.
     * @param turnColor   current player to make a move.
     */
    public static boolean canCheckBeBlocked(Board board, HashSet<Integer> dangerScope, Piece king, char turnColor) throws CloneNotSupportedException {
        HashSet<Integer> defendedSquares = defendedSquares(board, turnColor, king.locationNumber());
        for (int i : dangerScope) {
            if (defendedSquares.contains(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function which applies the correct DangerScope function depending on the type of piece,
     * which is currently checking the king. Returns a set of integers which indicate the locations
     * where an attack could be blocked.
     */
    public static HashSet<Integer> applyDanSfunc(Board board, Piece king) {
        HashSet<Integer> interceptionScope = new HashSet<>();

        for (Piece piece : checkingPieces) {
            int pieceFile = Board.getPieceFile(piece.locationNumber());
            int pieceRank = Board.getPieceRank(piece.locationNumber());
            int kingFile = Board.getPieceFile(king.locationNumber());
            int kingRank = Board.getPieceRank(king.locationNumber());

            if (pieceFile == Board.getPieceFile(king.locationNumber())) {
                if (pieceRank > kingRank) {
                    interceptionScope.addAll(dangerScopeS(piece));
                } else {
                    interceptionScope.addAll(dangerScopeN(piece));
                }
            } else if (pieceRank == Board.getPieceRank(king.locationNumber())) {
                if (pieceFile > kingFile) {
                    interceptionScope.addAll(dangerScopeW(piece));
                } else {
                    interceptionScope.addAll(dangerScopeE(piece));
                }
            }
            if (pieceRank > kingRank && !piece.getClass().equals(Knight.class)) {
                if (pieceFile > kingFile) {
                    interceptionScope.addAll(dangerScopeSW(piece));
                } else if (pieceFile < kingFile) {
                    interceptionScope.addAll(dangerScopeSE(piece));
                }
            } else if (pieceRank < kingRank && !piece.getClass().equals(Knight.class)) {
                if (pieceFile > kingFile) {
                    interceptionScope.addAll(dangerScopeNW(piece));
                } else if (pieceFile < kingFile) {
                    interceptionScope.addAll(dangerScopeNE(piece));
                }
            }
            if (piece.getClass().equals(Knight.class)) {
                interceptionScope.add(piece.locationNumber());
            }
        }
        return interceptionScope;
    }

    /**
     * Function which merges movement restrictions for the cases when a piece is pinned, but there is
     * also an ongoing attack on the king.
     *
     * @param pins               set of locations allowed when the piece is pinned.
     * @param interceptionOfMate set of locations where mate could be intercepted.
     */
    public static HashSet<Integer> mergeAllowedMoves(HashSet<Integer> pins, HashSet<Integer> interceptionOfMate) {
        if (pins.isEmpty()) {
            return interceptionOfMate;
        }
        if (!interceptionOfMate.isEmpty()) {
            HashSet<Integer> clone = (HashSet<Integer>) pins.clone();
            for (int i : clone) {
                if (!interceptionOfMate.contains(i)) {
                    pins.remove(i);
                }
            }
            return pins;
        } else {
            return pins;
        }
    }

    /**
     * Function which returns whether a piece at a given location is targeted by any of the enemy pieces
     * currently on the board.
     *
     * @param turnColor current player to move.
     * @param location  given square index to check.
     */
    public static boolean isPieceTargeted(Board board, char turnColor, int location) throws CloneNotSupportedException {
        return (attackedSquares(board, turnColor, location).contains(location));
    }

    /**
     * Function which returns a set of all squares attacked by enemy pieces.
     */
    public static HashSet<Integer> attackedSquares(Board board, char turnColor, int location) throws CloneNotSupportedException {
        AttacksOnKing.checkForPins(board, location);// wonder if there should be two locations added. Here this is the king location
        HashSet<Integer> attackedSquares = new HashSet<>();

        if (turnColor == 'W') {
            for (Piece enemyPiece : Board.blackPieces) {
                if (enemyPiece.getClass().equals(King.class)) {
                    attackedSquares.addAll(enemyPiece.attacksInAllDirections(board));
                    continue;
                }
                HashSet<Integer> allowedMoves = (AttacksOnKing.pPiecesAndAllowedMoves.containsKey(enemyPiece)) ? AttacksOnKing.pPiecesAndAllowedMoves.get(enemyPiece) : new HashSet<>();
                HashSet<Integer> attackingMoves;
                if (!enemyPiece.getClass().equals(Pawn.class)) {
                    attackingMoves = enemyPiece.generatePossibleMoves(board, allowedMoves);
                } else {
                    attackingMoves = ((Pawn) enemyPiece).generateCaptureMoves(board, allowedMoves);
                }
                if (attackingMoves.contains(location) && location == AttacksOnKing.WkingLocation) {
                    checkingPieces.add(enemyPiece);
                }
                attackedSquares.addAll(attackingMoves);
            }
        } else {
            for (Piece enemyPiece : Board.whitePieces) {
                if (enemyPiece.getClass().equals(King.class)) {
                    attackedSquares.addAll(enemyPiece.attacksInAllDirections(board));
                    continue;
                }
                HashSet<Integer> allowedMoves = (AttacksOnKing.pPiecesAndAllowedMoves.containsKey(enemyPiece)) ? AttacksOnKing.pPiecesAndAllowedMoves.get(enemyPiece) : new HashSet<>();
                HashSet<Integer> attackingMoves;
                if (!enemyPiece.getClass().equals(Pawn.class)) {
                    attackingMoves = enemyPiece.generatePossibleMoves(board, allowedMoves);
                } else {
                    attackingMoves = ((Pawn) enemyPiece).generateCaptureMoves(board, allowedMoves);
                }
                if (attackingMoves.contains(location) && location == AttacksOnKing.BkingLocation) {
                    checkingPieces.add(enemyPiece);
                }
                attackedSquares.addAll(attackingMoves);
            }
        }
        return attackedSquares;
    }

    /**
     * Function similar to attackedSquares which returns a set of all locations where the king is safe
     * to go. It is useful when determining the king's moves. A king can only take an enemy piece
     * if it is not defended by any enemy piece.
     */
    public static HashSet<Integer> defendedSquares(Board board, char turnColor, int location) throws CloneNotSupportedException {
        AttacksOnKing.checkForPins(board, location);// wonder if there should be two locations added. Here this is the king location
        HashSet<Integer> attackedSquares = new HashSet<>();

        if (turnColor == 'W') {
            for (Piece enemyPiece : Board.blackPieces) {
                if (enemyPiece.getClass().equals(King.class)) {
                    continue;
                }
                HashSet<Integer> allowedMoves = (AttacksOnKing.pPiecesAndAllowedMoves.containsKey(enemyPiece)) ? AttacksOnKing.pPiecesAndAllowedMoves.get(enemyPiece) : new HashSet<>();
                HashSet<Integer> attackingMoves = enemyPiece.generatePossibleMoves(board, allowedMoves);
                if (attackingMoves.contains(location)) {
                    checkingPieces.add(enemyPiece);
                }
                attackedSquares.addAll(attackingMoves);
            }
        } else {
            for (Piece enemyPiece : Board.whitePieces) {
                if (enemyPiece.getClass().equals(King.class)) {
                    continue;
                }
                HashSet<Integer> allowedMoves = (AttacksOnKing.pPiecesAndAllowedMoves.containsKey(enemyPiece)) ? AttacksOnKing.pPiecesAndAllowedMoves.get(enemyPiece) : new HashSet<>();
                HashSet<Integer> attackingMoves = enemyPiece.generatePossibleMoves(board, allowedMoves);
                if (attackingMoves.contains(location)) {
                    checkingPieces.add(enemyPiece);
                }
                attackedSquares.addAll(attackingMoves);
            }
        }
        return attackedSquares;
    }
}
