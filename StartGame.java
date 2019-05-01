//----------------------------------------
// CLASS: StartGame
//
// Author: Brett Adamson
// Last Updated: March 28,2019
// REMARKS: Create a unbeatable tic tac toe game based on Robert Heaton's found here
//          https://robertheaton.com/2018/10/09/programming-projects-for-advanced-beginners-3-b/
//
//
//
//-----------------------------------------
import java.util.Scanner;

public class StartGame {

    public static void main(String []args){


        Scanner input = new Scanner(System.in);
        System.out.println("New Game Started");
        Game game = new Game();
        while(game.playGame(input)){
            //play until the user no longer wants too
        }   System.out.println("Goodbye!");
            System.out.println("Program has Terminated");

        System.exit(1);


    }
}
