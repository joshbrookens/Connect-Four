//adjusted from tic tac toe game from my teacher, I made it Connect 4
import java.util.Scanner; // imports extention that allows scanner function to operate

public class UI
{
    int gameState = Constants.STANDBY;
    Scanner scanner;
    private ProcessBuilder.Redirect.Type getGameState;

    public UI() {
        scanner = new Scanner(System.in);        
    }

    public String getXOrO(int whoseMove) {
        if (whoseMove == -1){//-1 represents red player
            return "r"; // r is placed on board to represent where red player plays
        } else if (whoseMove == 1){ //1 represents black player
            return "b"; //b is placed on board to represent where black player plays
        } else{
            return " ";
        }
    }

    public String getPlayerName(int whoseMove, String xName, String oName) {
        return (whoseMove == -1) ? xName : oName;
    }

    public boolean isLegalMove(State state, int row, int col) {
        return 0 <= row && row <= Constants.BOARD_HEIGHT-1 &&
        0 <= col && col <= Constants.BOARD_WIDTH-1 &&
        state.getBoardCell(row, col) == Constants.BLANK;
    }

    public boolean isBlank(int row, int col){
        if ((row == Constants.BLANK)&&(col == Constants.BLANK)){
            return true;
        }
        return false;
    }

    // Prompt for input methods
    public String promptForName(String player) {
        System.out.printf(Constants.GET_PLAYER_NAME, player);
        return scanner.nextLine();
    }

    

    public int getMoveCol(int whoseMove, String xName, String oName) {
        int col = 0;
        while (col <= 0 || col >= Constants.BOARD_WIDTH+1) {
            try {
                System.out.printf(Constants.GET_COL_MOVE, getXOrO(whoseMove), getPlayerName(whoseMove, xName, oName));
                String answer = scanner.nextLine();
                col = Integer.parseInt(answer);
            } catch (Exception e) {
                System.out.println(Constants.INVALID_ROW_OR_COLUMN);
            }

        }
        return col;

    }

    public boolean startNewGame() {
        System.out.println();
        System.out.println(Constants.START_NEW_GAME);
        String yesOrNo = scanner.next();
        return yesOrNo.equals("Y") || yesOrNo.equals("y") || yesOrNo.equals("Yes") || yesOrNo.equals("yes") || yesOrNo.equals("YES")|| yesOrNo.equals("YEs") || yesOrNo.equals("YeS") || yesOrNo.equals("yEs") || yesOrNo.equals("yeS") || yesOrNo.equals("yES") || yesOrNo.equals("I would like to play again, please.");
    }

    // Printing text methods
    public void printWelcome() {
        System.out.println(Constants.TITLE);
    }

    public void printBoard(State state) {
        for(int i=0;i<40;i++){ // adds blank space at the top so previous plays can only be seen if scrolled up
        System.out.println("");
    }
        System.out.println(Constants.DIVIDER_STRING);
        for (int row = 0; row < Constants.BOARD_HEIGHT; row++) {
            System.out.printf(Constants.BOARD_STRING, getXOrO(state.getBoardCell(row, 0)), getXOrO(state.getBoardCell(row, 1)),getXOrO(state.getBoardCell(row, 2)),getXOrO(state.getBoardCell(row, 3)),getXOrO(state.getBoardCell(row, 4)),getXOrO(state.getBoardCell(row, 5)),getXOrO(state.getBoardCell(row, 6)) );
            System.out.println();
            System.out.println(Constants.DIVIDER_STRING);
        }
         System.out.println(Constants.NUMBER_STRING);
    }

    public void printInvalidRowOrColumn() {
        System.out.printf(Constants.INVALID_ROW_OR_COLUMN);
    }

    public void printInvalidMove(int row, int col) {
        System.out.printf(Constants.INVALID_MOVE_ERROR, row, col);
    }

    public void printMove(State state, int row, int col) {
        System.out.printf(
            Constants.PRINT_MOVE,
            getXOrO(state.getWhoseMove()),
            getPlayerName(state.getWhoseMove(), state.getRName(), state.getBName()),
            row+1,
            col+1
        );
        System.out.println();
    }

    public void printWinner(State state) {
        System.out.printf(Constants.WINNER, getXOrO(state.getWhoseMove()),
            getPlayerName(state.getWhoseMove(), state.getRName(), state.getBName())
        );
    }

    public void printTieGame() {
        System.out.println(Constants.TIE_GAME);
    }
}
