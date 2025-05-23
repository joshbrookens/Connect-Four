//adjusted from tic tac toe game from my teacher, I made it Connect 4
public class Constants
{
    // Valid board size
    public static final int BOARD_WIDTH = 7; //width of board, 0 is at the left
    public static final int BOARD_HEIGHT = 6; //height of board, 0 is at the top
    // Valid board values
    public static final int R = -1;    // Indicates an "X"
    public static final int B = 1;     // Indicates an "O"
    public static final int BLANK = 0; // Indicates a blank square

    // Game states
    public static final int STANDBY = 0; // beginning 
    public static final int GET_R_NAME = 1; // get first persons name
    public static final int GET_B_NAME = 2; // get second persons name
    public static final int GET_R_MOVE = 3; // get first persons move
    public static final int GET_B_MOVE = 4; // get second persons move
    public static final int MAKE_MOVE = 5; 
    public static final int CHECK_IF_WINNER = 6;
    public static final int CHECK_IF_TIE = 7; 
    public static final int R_WINS = 8; //red wins
    public static final int B_WINS = 9; //black wins
    public static final int GAME_OVER = 10;
    public static final int QUIT_PROGRAM = 11; // ends game
    public static final int GET_C_MOVE = 12; // this replaces player b if their name is 'computer' or 'c', player b plays automatically as computer
   
    // Strings are referenced in UI
    public static final String DIVIDER_STRING = "|---|---|---|---|---|---|---|"; // 28 - 30 these will be used to make the gameboard
    public static final String BOARD_STRING = "| %s | %s | %s | %s | %s | %s | %s |";
    public static final String NUMBER_STRING = "  1   2   3   4   5   6   7";
    public static final String GET_PLAYER_NAME = "Player %s: What is your name?--> ";
    public static final String TITLE = "Thanks for playing Connect Four! --> ";
    public static final String GET_COL_MOVE = "Player %s (%s): Enter the column for your next move (1,2,3,4,5,6,7) --> ";
    public static final String INVALID_ROW_OR_COLUMN = "Your column must be an integer 1, 2, 3, 4, 5, 6, or 7";
    public static final String INVALID_MOVE_ERROR = "Row %d column %d is not a valid move. Please try again";
    public static final String PRINT_MOVE = "Player %s (%s) move to row %d, column %d";
    public static final String WINNER = "%s - %s is the winner!";
    public static final String TIE_GAME = "It's a tie game! Game over. nobody wins:(";
    public static final String START_NEW_GAME = "Start a new game? --> ";
    
    
}
