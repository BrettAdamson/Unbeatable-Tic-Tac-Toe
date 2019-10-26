public class Board {


    //Instance Variables
    private char[][] board;
    private final int SIZE = 3;


    //Constructor
    public Board() {
        board = new char[SIZE][SIZE];//Create the board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';//Fill the board with empty spaces
            }
        }
    }

    //Instance Methods

    public void printBoard() {
        System.out.println("   0   1   2 ");
        System.out.println("  --- --- ---");
        System.out.println(String.format("0| %s | %s | %s |", board[0][0], board[0][1], board[0][2]));
        System.out.println(String.format("1| %s | %s | %s |", board[1][0], board[1][1], board[1][2]));
        System.out.println(String.format("2| %s | %s | %s |", board[2][0], board[2][1], board[2][2]));
        System.out.println("  --- --- ---");
    }

    public boolean full() {
        boolean isFull = false;
        int counter = 0; //keep track of the number of occupied spaces
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 'X' || board[i][j] == 'O') {
                    counter++;
                }

            }
        }
        if (counter == 9) {
            isFull = true;
        }
        return isFull;
    }

    private boolean badMove(int x, int y) {
        boolean badMove = false;
        if (board[x][y] == 'X' || board[x][y] == 'O') {
            badMove = true;//If there is already a spot taken on the board
            System.out.println("That spot has already been taken.");
        }
        return badMove;
    }

    public boolean placeMove(int x, int y, char symbol) {
        boolean madeMove;
        if(symbol == ' '){
            //the ai is searching through all possible moves
            board[x][y] = symbol;
            madeMove = true;
        }else if (badMove(x, y)) {//check for bad move
            madeMove = false;
        } else {
            madeMove = true;
            board[x][y] = symbol;
        }
        return madeMove;
    }

    public boolean gameOver(char player) {
        boolean gameOver = false;
        if (horizontalWinner(player) || verticalWinner(player) || diagonalWinner(player)) {
            gameOver = true;//try all win conditions
        }
        return gameOver;
    }

    private boolean horizontalWinner(char player) {
        boolean winnerFound = false;
        for (int row = 0; row < SIZE; row++) {
            if (( board[row][0] == player) && (board[row][0] == board[row][1] && board[row][0] == board[row][2])) {
                winnerFound = true;
            }
        }

        return winnerFound;
    }

    private boolean verticalWinner(char player) {
        boolean winnerFound = false;
        for (int col = 0; col < SIZE; col++) {
            if (board[0][col] == player && (board[0][col] == board[1][col] && board[0][col] == board[2][col])){
                winnerFound = true;
            }
        }
        return winnerFound;
    }


    private boolean diagonalWinner(char player) {
        boolean winnerFound = false;
        //For diagonals you can just check the two possible combinations
        if ((board[1][1] == player) && (board[0][0] == board[1][1] && board[0][0] == board[2][2]
                || board[0][2] == board[1][1] && board[0][2] == board[2][0])) {
            winnerFound = true;
        }

        return winnerFound;
    }

    public char[][] getBoard() {
        return board;
    }

    public Board deepcopy() {
        Board copy = new Board();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                copy.board[row][col] = board[row][col];
            }
        }
            return copy;
    }


}
