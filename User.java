public class User extends Players{

    //Instance Variables
    private char symbol;

    //Constructor
    public User(int userSymbol) {
        if (userSymbol == 1) {
            symbol = 'O';
        } else {
            symbol = 'X';
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public int unusedSymbol() {
        if (symbol == 'X') {
            return 1;
        } else {
            return 0;
        }
    }

}
