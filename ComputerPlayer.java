import java.awt.*;
import java.util.ArrayList;

public class ComputerPlayer extends Players {

    //Instance Variable
    private char cpuSymbol;
    private char playerSymbol;
    private final int SIZE = 3;
    private int bestX;
    private int bestY;


    public ComputerPlayer(int value) {
        if (value == 1) {
            cpuSymbol = 'O';
            playerSymbol = 'X';
        } else {
            cpuSymbol = 'X';
            playerSymbol = 'O';
        }
    }

    public char getSymbol() {
        return cpuSymbol;
    }

    public void makeMove(Board board) {
        //reset the best coordinates
        bestX = 0;
        bestY = 0;
        minimax(board.deepcopy(), cpuSymbol);//this will give bestX and bestY new values
        board.placeMove(bestX, bestY, cpuSymbol);
    }

    private int minimax(Board board, char player) {
        ArrayList<Integer> scores = new ArrayList<>(SIZE*SIZE);//need to keep track of the scores
        ArrayList<Point> moves = new ArrayList<>(SIZE*SIZE);

        //base case
        if (board.gameOver(cpuSymbol)) {
            return 10 ;//player win
        } else if (board.gameOver(playerSymbol)) {
            return  - 10;//player lose
        } else if (board.full()) {
            return 0;
        }
        boolean[][] possibleMoves = remainingMoves(board);
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (possibleMoves[row][col]) {//only place moves that are possible
                    board.placeMove(row, col, player);
                    //recursively take turns and record how well this choice does. 10 if it will result in a win
                    // -10 if it will result in a loss. 0 if it will result in a draw
                    if (player == cpuSymbol) {
                        scores.add(minimax(board, playerSymbol));
                    } else {
                        scores.add(minimax(board, cpuSymbol));
                    }
                    moves.add(new Point(row, col));
                    board.placeMove(row, col, ' ');//remove placed move
                }
            }
        }
        int currScore;
        int bestMoveIndex;
        if (player == cpuSymbol) {
            currScore = -1000000;

            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) > currScore) {//if maximizing
                    currScore = scores.get(i);
                    bestMoveIndex = i;
                    bestX = moves.get(bestMoveIndex).x;
                    bestY = moves.get(bestMoveIndex).y;
                }
            }

        } else {
            currScore = 1000000;//if minimizing
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) < currScore) {
                    currScore = scores.get(i);
                    bestMoveIndex = i;
                    bestX = moves.get(bestMoveIndex).x;
                    bestY = moves.get(bestMoveIndex).y;
                }
            }
        }
       return currScore;//return the best score from the score list
    }


    private boolean[][] remainingMoves(Board board) {
        boolean[][] possibleMoves = new boolean[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board.getBoard()[row][col] == ' ') {
                    possibleMoves[row][col] = true;//Get all the array cells that have not been filled up
                } else {
                    possibleMoves[row][col] = false;
                }
            }
        }
        return possibleMoves;
    }


}
