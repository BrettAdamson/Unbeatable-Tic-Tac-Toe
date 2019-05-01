import java.util.Scanner;

public class Game {
    private int cpuWins = 0;
    private int playerWin = 0;
    private int draws = 0;


    public boolean playGame(Scanner input) {

        Board board = new Board();

        System.out.println("Please Pick a Symbol to Play As: 0 for X. 1 For O.");

        User user = new User(oneOrZero(input));//create player

        ComputerPlayer cpu = new ComputerPlayer(user.unusedSymbol());


        System.out.println("User Will Go First.\n");


        while (!board.full()) {
            if(board.full()){
                System.out.println("It is a draw.");
                break;
            }

            board.printBoard();
            System.out.println("\nIt is your turn.");
            boolean invalidMove = true;

            while(invalidMove){//The user can only choose spots that have not already been taken
                System.out.print("Please choose a X coordinate: ");
                int xCord = validInput(input);

                System.out.print("Please choose a Y coordinate: ");
                int yCord = validInput(input);

                if(board.placeMove(xCord,yCord,user.getSymbol())){
                   invalidMove = false;
                }
            }
            board.printBoard();
            if(board.gameOver(user.getSymbol())){
                System.out.println("You Win!!!");
                playerWin++;
                board.printBoard();
                break;
            }


            System.out.println("\nComputer will now make a move.");
            cpu.makeMove(board);
            if(board.gameOver(cpu.getSymbol())){
                System.out.println("\nYou Lose!!!");
                cpuWins++;
                board.printBoard();
                break;
            }

            if(board.full()){
                System.out.println("\nIts a draw!!!");
                draws++;
                board.printBoard();
            }

        }
        System.out.println("Current Record: Player: "+playerWin+" | Computer: "+cpuWins+" | Draws: "+draws);
        System.out.println("Would You Like to Play Again? Type 1 for yes. Type 0 for no.");
            return validInput(input) == 1;
    }

    private int oneOrZero(Scanner scan) {
        boolean invalidInput = true;
        int answer = -1;
        while (invalidInput) {
            answer = validInput(scan);
            if(answer == 2){
                System.out.println("That is not a valid choice.");
            }else{
                invalidInput = false;
            }
        }
        return answer;
    }



    private int validInput(Scanner scan){
        boolean invalidInput = true;
        int input = -1;
        while(invalidInput){
        if(scan.hasNextInt()) {
            int num = scan.nextInt();
            if(num == 2){
                input = 2;
                invalidInput = false;
            }
            else if(num == 1){
                input = 1;
                invalidInput = false;

            }else if(num == 0){
                input = 0;
                invalidInput = false;

            }else{
                System.out.println("That is not a valid choice.");
            }

        }else {//if int
            System.out.println("That is not a valid choice.");
            scan.next();
        }
        }//while invalid
        return input;
    }



}
