package src.testing;

import org.junit.Test;
import src.board.*;
import src.piece.*;
import static org.junit.Assert.*;

import java.util.HashSet;


public class Testing {
    @Test
    public void test_moveScope(){
        Board board = TestBoard.testPinnedPieceDiagonally();
        HashSet<Integer> set = AttacksOnKing.dangerScopeSE(Board.board[25]);
        HashSet<Integer> set1 = new HashSet<>();
        set1.add(25);
        set1.add(18);
        set1.add(11);
        assertEquals(set, set1);
    }
    @Test
    public void test_verticalPin(){
        Board board = TestBoard.testVerticalPin();
        HashSet<Integer> set = AttacksOnKing.dangerScopeS(Board.board[36]);
        HashSet<Integer> set1 = new HashSet<>();
        set1.add(36);
        set1.add(28);
        set1.add(12);
        set1.add(20);
        assertEquals(set, set1);
    }
    @Test
    public void test_horizontalPin(){
        Board board = TestBoard.testHorizontalPin();
        HashSet<Integer> set = AttacksOnKing.dangerScopeE(Board.board[26]);
        HashSet<Integer> set1 = new HashSet<>();
        set1.add(26);
        set1.add(27);
        set1.add(28);
        assertEquals(set, set1);
    }
}
