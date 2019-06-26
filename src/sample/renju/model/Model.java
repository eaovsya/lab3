package sample.renju.model;

import sample.renju.view.View;

public class Model {
    private PieceType[][] board;
    private PieceType currentPlayer;
    private static int moveCounter;
    private static int numberOfFours;
    private static int numberOfOpenThrees;
    private static int prevNumberOfFours;
    private static int prevNumberOfOpenThrees;

    public Model() {
        numberOfOpenThrees = 0;
        numberOfFours = 0;
        prevNumberOfFours = 0;
        prevNumberOfOpenThrees = 0;
        moveCounter = 0;
        this.board = new PieceType[View.HEIGHT][View.WIDTH];
        currentPlayer = PieceType.BLACK;
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

    private void foursCheck() {
        prevNumberOfFours = numberOfFours;
        numberOfFours = 0;
        for(int x = 0; x < View.WIDTH; x++) {
            for (int y = 0; y < View.HEIGHT - 3; y++) {
                //VERTICAL
                if (board[x][y] == PieceType.BLACK) {
                    if (board[x][y + 1] == PieceType.BLACK && board[x][y + 2] == PieceType.BLACK) {
                        if (board[x][y + 3] == PieceType.BLACK) numberOfFours++;
                        if (y < 11 && board[x][y + 3] == null && board[x][y + 4] == PieceType.BLACK) numberOfFours++;
                    }
                    if (board[x][y + 1] == null && board[x][y + 2] == PieceType.BLACK && board[x][y + 3] == PieceType.BLACK && board[x][y + 4] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                    if (y < 11 && board[x][y + 1] == PieceType.BLACK && board[x][y + 2] == null && board[x][y + 3] == PieceType.BLACK && board[x][y + 4] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                }
                //HORIZONTAL
                if (board[y][x] == PieceType.BLACK) {
                    if (board[y + 1][x] == PieceType.BLACK && board[y + 2][x] == PieceType.BLACK) {
                        if (board[y + 3][x] == PieceType.BLACK) numberOfFours++;
                        if (y < 11 && board[y + 3][x] == null && board[y + 4][x] == PieceType.BLACK) numberOfFours++;
                    }
                    if ( y < 11 &&board[y + 1][x] == null && board[y + 2][x] == PieceType.BLACK && board[y + 3][x] == PieceType.BLACK && board[y + 4][x] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                    if ( y < 11 && board[y + 1][x] == PieceType.BLACK && board[y + 2][x] == null && board[y + 3][x] == PieceType.BLACK && board[y + 4][x] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                }
                //DIAGONAL
                if (x > 2 && board[x - 3][y] == PieceType.BLACK) {
                    if (board[x - 2][y + 1] == PieceType.BLACK && board[x - 1][y + 2] == PieceType.BLACK) {
                        if (board[x][y + 3] == PieceType.BLACK) numberOfFours++;
                        if (y < 11 && board[x][y + 3] == null && board[x + 1][y + 4] == PieceType.BLACK) numberOfFours++;
                    }
                    if (y < 11 && board[x - 2][y + 1] == null && board[x - 1][y + 2] == PieceType.BLACK && board[x][y + 3] == PieceType.BLACK && board[x + 1][y + 4] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                    if (y < 11 && board[x - 2][y + 1] == PieceType.BLACK && board[x - 1][y + 2] == null && board[x][y + 3] == PieceType.BLACK && board[x + 1][y + 4] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                }
                if (x < 12 && board[(View.WIDTH - 1) - x][y] == PieceType.BLACK) {
                    if (board[(View.WIDTH - 1) - x - 1][y + 1] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 2][y + 2] == PieceType.BLACK) {
                        if (board[(View.WIDTH - 1) - x - 3][y + 3] == PieceType.BLACK) {
                            numberOfFours++;
                        }
                        if (y < 11 && board[(View.WIDTH - 1) - x - 3][y + 3] == null && board[(View.WIDTH - 1) - x - 4][y + 4] == PieceType.BLACK) {
                            numberOfFours++;
                        }
                    }
                    if (y < 11 && board[(View.WIDTH - 1) - x - 1][y + 1] == null && board[(View.WIDTH - 1) - x - 2][y + 2] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 3][y + 3] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 4][y + 4] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                    if (y < 11 && board[(View.WIDTH - 1) - x - 1][y + 1] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 2][y + 2] == null && board[(View.WIDTH - 1) - x - 3][y + 3] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 4][y + 4] == PieceType.BLACK) {
                        numberOfFours++;
                    }
                }
            }
        }
    }

    private void openThreesCheck() {
        prevNumberOfOpenThrees = numberOfOpenThrees;
        numberOfOpenThrees = 0;
        for(int x = 0; x < View.WIDTH; x++) {
            for (int y = 0; y < View.HEIGHT - 4; y++) {
                //VERTICAL
                if(board[x][y] == null) {
                    if(board[x][y + 1] == PieceType.BLACK && board[x][y + 2] == PieceType.BLACK) {
                        if(board[x][y + 3] == PieceType.BLACK && board[x][y + 4] == null) {
                            numberOfOpenThrees++;
                        }
                        if(y < 10 && board[x][y + 3] == null && board[x][y + 4] == PieceType.BLACK && board[x][y + 5] == null) {
                            numberOfOpenThrees++;
                        }
                    }
                    if(y < 10 && board[x][y + 1] == PieceType.BLACK && board[x][y + 2] == null && board[x][y + 3] == PieceType.BLACK && board[x][y + 4] == PieceType.BLACK && board[x][y + 5] == null) {
                        numberOfOpenThrees++;
                    }
                }
                //HORIZONTAL
                if (board[y][x] == null) {
                    if(board[y + 1][x] == PieceType.BLACK && board[y + 2][x] == PieceType.BLACK) {
                        if(board[y + 3][x] == PieceType.BLACK && board[y + 4][x] == null) {
                            numberOfOpenThrees++;
                        }
                        if(y < 10 && board[y + 3][x] == null && board[y + 4][x] == PieceType.BLACK && board[y + 5][x] == null) {
                            numberOfOpenThrees++;
                        }
                    }
                    if(y < 10 && board[y + 1][x] == PieceType.BLACK && board[y + 2][x] == null && board[y + 3][x] == PieceType.BLACK && board[y + 4][x] == PieceType.BLACK && board[y + 5][x] == null) {
                        numberOfOpenThrees++;
                    }
                }
                //DIAGONAL
                if (x < 11 && board[(View.WIDTH - 1) - x][y] == null) {
                    if (board[(View.WIDTH - 1) - x - 1][y + 1] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 2][y + 2] == PieceType.BLACK) {
                        if (board[(View.WIDTH - 1) - x - 3][y + 3] == PieceType.BLACK && board[(View.WIDTH - 1) - x -  4][y + 4] == null) numberOfOpenThrees++;
                        if (y < 10 && board[(View.WIDTH - 1) - x - 3][y + 3] == null && board[(View.WIDTH - 1) - x - 4][y + 4] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 5][y + 5] == null) numberOfOpenThrees++;
                    }
                    if (y < 10 && board[(View.WIDTH - 1) - x - 1][y + 1] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 2][y + 2] == null && board[(View.WIDTH - 1) - x - 3][y + 3] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 4][y + 4] == PieceType.BLACK && board[(View.WIDTH - 1) - x - 5][y + 5] == null) {
                        numberOfOpenThrees++;
                    }
                }
                if (x > 3 && board[x - 4][y] == null) {
                    if (board[x - 2][y + 1] == PieceType.BLACK && board[x - 1][y + 2] == PieceType.BLACK) {
                        if (board[x][y + 3] == PieceType.BLACK && board[x + 1][y + 4] == null) numberOfOpenThrees++;
                        if (y < 10 && board[x][y + 3] == null && board[x + 1][y + 4] == PieceType.BLACK && board[x + 2][y + 5] == null) numberOfOpenThrees++;
                    }
                    if (y < 10 && board[x - 2][y + 1] == PieceType.BLACK && board[x - 1][y + 2] == null && board[x][y + 3] == PieceType.BLACK && board[x + 1][y + 4] == PieceType.BLACK && board[x + 2][y + 5] == null) {
                        numberOfOpenThrees++;
                    }
                }
            }
        }
    }

    public WinType winCheck(PieceType type) {
        for(int x = 0; x < View.WIDTH; x++) {
            for(int y = 0; y < View.HEIGHT - 4; y++) {
                //VERTICAL
                if(board[x][y] == type && board[x][y + 1] == type && board[x][y + 2] == type && board[x][y + 3] == type && board[x][y + 4] == type) {
                    if(type == PieceType.BLACK && y < 9) {
                        return overlineCheck(x, y + 5);
                    }
                    return WinType.WHITEWIN;
                }
                //HORIZONTAL
                if(board[y][x] == type && board[y + 1][x] == type && board[y + 2][x] == type && board[y + 3][x] == type && board[y + 4][x] == type) {
                    if(type == PieceType.BLACK && y < 9) {
                        //noinspection SuspiciousNameCombination
                        return overlineCheck(y + 5, x);
                    }
                    return WinType.WHITEWIN;
                }
                //DIAGONAL
                if(x > 3 && board[x - 4][y] == type && board[x - 3][y + 1] == type && board[x - 2][y + 2] == type && board[x - 1][y + 3] == type && board[x][y + 4] == type) {
                    if(type == PieceType.BLACK && y < 9) {
                        return overlineCheck(x + 1, y + 5);
                    }
                    return WinType.WHITEWIN;
                }
                if(x < 11 && board[(View.WIDTH - 1) - x][y] == type && board[(View.WIDTH - 1) - x - 1][y + 1] == type && board[(View.WIDTH - 1) - x - 2][y + 2] == type && board[(View.WIDTH - 1) - x - 3][y + 3] == type && board[(View.WIDTH - 1) - x - 4][y + 4] == type) {
                    if(type == PieceType.BLACK && y < 9) {
                        return overlineCheck((View.WIDTH - 1) - x - 5, y + 5);
                    }
                    return WinType.WHITEWIN;
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
