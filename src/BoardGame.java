//mshaikh52 student number 250959996

public class BoardGame {

    private char[][] gameBoard;
    private int boardSize;
    private int emptyPostions;
    private int maxLevels;

    //constructor
    public BoardGame(int board_size, int empty_positions, int max_levels) {
        this.boardSize = board_size;
        this.emptyPostions = empty_positions;
        this.maxLevels = max_levels;

        gameBoard = new char[board_size][board_size];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                gameBoard[i][j] = 'g';
            }
        }

    }

    //create hashDictionary of size 9887
    public HashDictionary makeDictionary() {
        HashDictionary newDic = new HashDictionary(9887);
        return newDic;
    }

    //function checks if current config is in the dictionary
    public int isRepeatedConfig(HashDictionary dict) {
        String config = "";

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                char position = gameBoard[j][i];
                config = config + position;
            }

        }
        if (dict.getScore(config) != -1) {
            return dict.getScore(config);
        } else {
            return -1;
        }

    }

    //put the config in the hash dictionary
    public void putConfig(HashDictionary dict, int score) {
        String config = "";
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                char position = gameBoard[j][i];
                config = config + position;
            }
        }
        Configuration newData = new Configuration(config, score);
        try {
            dict.put(newData);
        } catch (DictionaryException e) {
            System.out.println("Collison Detected");

        }
    }

    //method to save symbol that is on game board
    public void savePlay(int row, int col, char symbol) {
        gameBoard[row][col] = symbol;

    }

    //checks if position is empty
    public boolean positionIsEmpty(int row, int col) {
        char symbol;
        symbol = gameBoard[row][col];
        if (symbol == 'g') {
            return true;
        } else {
            return false;
        }
    }

    //if the tile is of a computer
    public boolean tileOfComputer(int row, int col) {
        char symbol;
        symbol = gameBoard[row][col];
        if (symbol == 'o') {
            return true;
        } else {
            return false;
        }

    }

    //check which tile is of human
    public boolean tileOfHuman(int row, int col) {
        char symbol;
        symbol = gameBoard[row][col];
        if (symbol == 'b') {
            return true;
        } else {
            return false;
        }

    }

    //determine if anyone has won
    public boolean wins(char symbol) {

        //first check every row for horizontal win
        for (int i = 0; i < boardSize; i++) {
            if (rowChecker(i, boardSize, symbol) == true) {
                return true;
            }
        }

        //next check every collumn for a vertical win
        for (int i = 0; i < boardSize; i++) {
            if (colChecker(i, boardSize, symbol) == true) {
                return true;
            }
        }

        //diagonal checker
        if (diagonalChecker(boardSize, symbol) == true) {
            return true;
        }

        //check diagonal backwards
        if (reverseDiagonalChecker(boardSize, symbol) == true) {
            return true;
        }


        return false;

    }

    //determines if draw or not
    public boolean isDraw(char symbol, int empty_positions) {
        //flag to determine if any empty tile has the symbol next to it
        boolean flag = false;
        int emptyPos = 0;
        int matches = 0;
        //find number of empty postions
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (gameBoard[i][j] == 'g');
                {
                    emptyPos++;
                }
            }
        }

        loop:{
        for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (gameBoard[i][j] == 'g') {
                        //check if the empty tile has any tile next to it with symbol
                        if (i < boardSize - 1) {
                            if (gameBoard[i + 1][j] == symbol) {
                                matches++;
                            }
                        } else if (j < boardSize - 1) {
                            if (gameBoard[i][j + 1] == symbol && j < boardSize) {
                                matches++;
                            }
                        } else if (i > 0) {
                            if (gameBoard[i][i - 1] == symbol && j > 0) {
                                matches++;
                            }
                        } else if (j > 0) {
                            if (gameBoard[i][j - 1] == symbol && i > 0) {
                                matches++;
                            }
                        } else if (j < boardSize - 1 && i < boardSize - 1) {
                            if (gameBoard[i + 1][j + 1] == symbol && j < boardSize && i < boardSize) {
                                matches++;
                            }
                        } else if (i > 0 && j > 0) {
                            if (gameBoard[i - 1][j - 1] == symbol && i > 0 && j > 0) {
                                matches++;
                            }
                        }
                        else if (i< boardSize && j<0) {
                            if (gameBoard[i + 1][j - 1] == symbol) {
                                matches++;
                            }
                        }
                        else if (i>0 &&j<boardSize){
                            if (gameBoard[i-1][j+1]==symbol) {
                                matches++;
                            }
                        }
                    }
                }
            }
        }

        //if no one has won and there are no empty positions left
        if (wins(symbol) == false && emptyPos==0) {
            return true;
        }
        //if no one has won and the number of empty positions is greater than 0 but there are no tile next to the empty space matching the symbol
        if (wins(symbol) == false && emptyPos==empty_positions&&matches==0) {
            return true;
        }
        return false;

    }

    //evaluate the board
    public int evalBoard(char symbol, int empty_positions) {
        //if the computer has won
        if (wins('o') == true) {
            return 3;
        }
        //if the player has won
        else if (wins('b') == true) {
            return 0;
        }
        //if no one has won
        else if (isDraw(symbol, empty_positions) == true) {
            return 2;
        }
        //if none of the above condtions are true
        else {
            return 1;
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////
    //private methods

    //checks for a diagonal win
    private boolean diagonalChecker(int boardSize, char symbol) {
        boolean flag = true;
        for (int i = 0; i < boardSize; i++) {
            if (gameBoard[i][i] != symbol) {
                return false;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    //check for reverse diagonal win
    private boolean reverseDiagonalChecker(int boardSize, char symbol) {
        boolean flag = true;
        for (int i = boardSize - 1, j = 0; j < boardSize; i--, j++) {
            if (gameBoard[i][j] != symbol) {
                return false;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    //method to check a specific row for a win
    private boolean rowChecker(int rowNum, int boardSize, char symbol) {

        int row = rowNum;
        int n = boardSize;
        String config = "";
        boolean flag = false;

        for (int i = 0; i < boardSize; i++) {
            if (gameBoard[row][i] != symbol) {
                return false;
            } else {
                flag = true;
            }
        }
        return flag;


    }

    //method to check a specific collumn for a win
    private boolean colChecker(int colNum, int boardSize, char symbol) {

        int col = colNum;
        int n = boardSize;
        String config = "";
        boolean flag = false;

        for (int i = 0; i < boardSize; i++) {
            if (gameBoard[i][col] != symbol) {
                return false;
            } else {
                flag = true;
            }
        }
        return flag;

    }
}