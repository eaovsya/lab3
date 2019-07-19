package sample.renju.model;

import sample.renju.view.View;

public class Model {
    public PieceType[][] board;
    private PieceType currentPlayer;
    private static int moveCounter;
    public static int numberOfFours;
    public static int numberOfOpenThrees;
    private static int prevNumberOfFours;
    private static int prevNumberOfOpenThrees;
    private int[][] directions;


    public Model() {
        numberOfOpenThrees = 0;
        numberOfFours = 0;
        prevNumberOfFours = 0;
        prevNumberOfOpenThrees = 0;
        moveCounter = 0;
        this.board = new PieceType[View.HEIGHT][View.WIDTH];
        currentPlayer = PieceType.BLACK;
        directions = new int[][]{{1,0}, {0,1}, {1,1}, {1, -1}};
    }

    public boolean checkOccupied(PieceType type, int x, int y) {
        if(moveCounter == 0 && (x != 7 || y != 7)) return false;
        if(board[x][y] != null) return false;
        board[x][y] = type;
        return true;
    }

    public PieceType getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchCurrentPlayer() {
        this.currentPlayer = (this.currentPlayer == PieceType.WHITE) ? PieceType.BLACK : PieceType.WHITE;
        moveCounter++;
    }

    public void foursCheck() {
        prevNumberOfFours = numberOfFours;
        numberOfFours = 0;
        for(int x = 0; x < View.WIDTH; x++) {
            for (int y = 0; y < View.HEIGHT; y++) {
                for(int i = 0; i < 4; i++) {
                    if (board[x][y] == PieceType.BLACK && x + 2 * directions[i][0] < View.WIDTH && y + 2 * directions[i][1] < View.HEIGHT && y + 2 * directions[i][1] >= 0) {
                        if (board[x + directions[i][0]][y + directions[i][1]] == PieceType.BLACK && board[x + 2 * directions[i][0]][y + 2 * directions[i][1]] == PieceType.BLACK) {
                            if(x + 3 * directions[i][0] < View.WIDTH && y + 3 * directions[i][1] < View.HEIGHT && y + 3 * directions[i][1] >= 0) {
                                if (board[x + 3 * directions[i][0]][y + 3 * directions[i][1]] == PieceType.BLACK)
                                    numberOfFours++;
                            }
                            if(x + 4 * directions[i][0] < View.WIDTH && y + 4 * directions[i][1] < View.HEIGHT && y + 4 * directions[i][1] >= 0) {
                                if (board[x + 3 * directions[i][0]][y + 3 * directions[i][1]] == null && board[x + 4 * directions[i][0]][y + 4 * directions[i][1]] == PieceType.BLACK)
                                    numberOfFours++;
                            }
                        }

                        if(x + 4 * directions[i][0] < View.WIDTH && y + 4 * directions[i][1] < View.HEIGHT && y + 4 * directions[i][1] >= 0) {
                            if (((board[x + directions[i][0]][y + directions[i][1]] == null && board[x + 2 * directions[i][0]][y + 2 * directions[i][1]] == PieceType.BLACK) || (board[x + directions[i][0]][y + directions[i][1]] == PieceType.BLACK && board[x + 2 * directions[i][0]][y + 2 * directions[i][1]] == null)) && board[x + 3 * directions[i][0]][y + 3 * directions[i][1]] == PieceType.BLACK && board[x + 4 * directions[i][0]][y + 4 * directions[i][1]] == PieceType.BLACK) {
                                numberOfFours++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void openThreesCheck() {
        prevNumberOfOpenThrees = numberOfOpenThrees;
        numberOfOpenThrees = 0;
        for(int x = 0; x < View.WIDTH; x++) {
            for (int y = 0; y < View.HEIGHT; y++) {
                for(int i = 0; i < 4; i++) {
                    if(board[x][y] == null) {
                        if(x + 4 * directions[i][0] < View.WIDTH && y + 4 * directions[i][1] < View.HEIGHT && y + 4 * directions[i][1] >= 0) {
                            if (board[x + directions[i][0]][y + directions[i][1]] == PieceType.BLACK && board[x + 2 * directions[i][0]][y + 2 * directions[i][1]] == PieceType.BLACK && board[x + 3 * directions[i][0]][y + 3 * directions[i][1]] == PieceType.BLACK && board[x + 4 * directions[i][0]][y + 4 * directions[i][1]] == null) {
                                numberOfOpenThrees++;
                            }
                        }
                        if(x + 5 * directions[i][0] < View.WIDTH && y + 5 * directions[i][1] < View.HEIGHT && y + 5 * directions[i][1] >= 0) {
                            if (board[x + directions[i][0]][y + directions[i][1]] == PieceType.BLACK && ((board[x + 2 * directions[i][0]][y + 2 * directions[i][1]] == null && board[x + 3 * directions[i][0]][y + 3 * directions[i][1]] == PieceType.BLACK) || (board[x + 2 * directions[i][0]][y + 2 * directions[i][1]] == PieceType.BLACK && board[x + 3 * directions[i][0]][y + 3 * directions[i][1]] == null)) && board[x + 4 * directions[i][0]][y + 4 * directions[i][1]] == PieceType.BLACK && board[x + 5 * directions[i][0]][y + 5 * directions[i][1]] == null) {
                                numberOfOpenThrees++;
                            }
                        }
                    }
                }
            }
        }
    }

    public WinType winCheck(PieceType type) {
        for(int x = 0; x < View.WIDTH; x++) {
            for (int y = 0; y < View.HEIGHT; y++) {
                for(int i = 0; i < 4; i++) {
                    if (x + 5 * directions[i][0] < View.WIDTH && y + 5 * directions[i][1] < View.HEIGHT && y + 5 * directions[i][1] >= 0) {
                        if (board[x][y] == type && board[x + directions[i][0]][y + directions[i][1]] == type && board[x + 2 * directions[i][0]][y + 2 * directions[i][1]] == type && board[x + 3 * directions[i][0]][y + 3 * directions[i][1]] == type && board[x + 4 * directions[i][0]][y + 4 * directions[i][1]] == type) {
                            if (type == PieceType.BLACK) {
                                return overlineCheck(x + 5 * directions[i][0], y + 5 * directions[i][1]);
                            }
                            return WinType.WHITEWIN;
                        }
                    }
                }
            }
        }
        if(type == PieceType.BLACK) {
            foursCheck();
            openThreesCheck();
            return forksCheck();
        }
        return WinType.NONE;
    }

    private WinType overlineCheck(int x, int y) {
        if(board[x][y] == PieceType.BLACK) return WinType.OVERLINE;
        return WinType.BLACKWIN;
    }

    private WinType forksCheck() {
        if(numberOfFours - prevNumberOfFours > 1) return WinType.FOURFORK;
        if(numberOfOpenThrees - prevNumberOfOpenThrees > 1)return WinType.THREEFORK;
        return WinType.NONE;
    }
}
