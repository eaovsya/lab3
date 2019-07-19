package sample.test;


import org.junit.Test;
import sample.renju.model.Model;
import sample.renju.model.PieceType;
import sample.renju.model.WinType;


import static org.junit.Assert.*;


public class ModelTest {

    @Test
    public void foursCheck() {
        Model model = new Model();
        model.foursCheck();
        assertEquals(Model.numberOfFours, 0);
        model.board[3][13] = PieceType.BLACK;
        model.board[5][13] = PieceType.BLACK;
        model.board[6][13] = PieceType.BLACK;
        model.board[7][13] = PieceType.BLACK;
        model.board[9][13] = PieceType.BLACK;
        model.foursCheck();
        assertEquals(Model.numberOfFours, 2);
    }

    @Test
    public void openThreesCheck() {
        Model model = new Model();
        model.foursCheck();
        assertEquals(Model.numberOfOpenThrees, 0);
        model.board[2][1] = PieceType.BLACK;
        model.board[3][1] = PieceType.BLACK;
        model.board[4][1] = PieceType.BLACK;
        model.board[3][2] = PieceType.BLACK;
        model.board[3][4] = PieceType.BLACK;
        model.openThreesCheck();
        assertEquals(Model.numberOfOpenThrees, 2);
    }

    @Test
    public void overlineCheck() {
        Model model = new Model();
        model.board[9][1] = PieceType.BLACK;
        model.board[10][1] = PieceType.BLACK;
        model.board[11][1] = PieceType.BLACK;
        model.board[12][1] = PieceType.BLACK;
        model.board[13][1] = PieceType.BLACK;
        model.board[14][1] = PieceType.BLACK;
        WinType result = model.winCheck(PieceType.BLACK);
        assertEquals(result, WinType.OVERLINE);
    }

    @Test
    public void winCheck() {
        Model model = new Model();
        model.board[0][0] = PieceType.BLACK;
        model.board[1][1] = PieceType.BLACK;
        model.board[2][2] = PieceType.BLACK;
        model.board[3][3] = PieceType.BLACK;
        model.board[4][4] = PieceType.BLACK;
        WinType result = model.winCheck(PieceType.BLACK);
        assertEquals(result, WinType.BLACKWIN);
    }
}
