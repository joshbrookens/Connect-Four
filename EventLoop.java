//Some of this code was taken over from tic tac toe template provided by my proffessor
public class EventLoop {
    // Instance variables for the UI and State classes
    
    State state = new State();
    UI ui = new UI();
    int row = Constants.BOARD_HEIGHT-1, col = Constants.BOARD_WIDTH-1;
    

    public static void main(String[] args){
        EventLoop eventLoop = new EventLoop();
        eventLoop.run();
    }

    public void run() {
        System.out.println("Welcome to Enginneering Open House");
        while (state.getGameState() != Constants.QUIT_PROGRAM) {
            int gameState = state.getGameState();
            if (gameState == Constants.STANDBY) {
                state.setGameState(Constants.GET_R_NAME);

            } else if (gameState == Constants.GET_R_NAME) {
                state.setRName(ui.promptForName("Red"));
                state.setGameState(Constants.GET_B_NAME);

            } else if (gameState == Constants.GET_B_NAME) {
                state.setBName(ui.promptForName("Black"));

                state.setGameState(Constants.GET_R_MOVE);

            } else if (gameState == Constants.GET_R_MOVE) {
                ui.printBoard(state);
                //row = ui.getMoveRow(state.getWhoseMove(),state.getRName(),state.getBName());

                // Ask the user for the column
                col = ui.getMoveCol(state.getWhoseMove(),state.getRName(),state.getBName());
                // transpose 1 based to 0 based becasue board is 0 based but UI is 1 based
                col = col-1;
                // Find the lowest empty row in that column
                for(int i = Constants.BOARD_HEIGHT-1;i>=0;i--){
                    if(state.board[i][col] == Constants.BLANK){
                        // row is already 0 based
                        row = i;
                        break;
                    }
                }
                if (ui.isLegalMove(state, row, col)) {
                    state.setGameState(Constants.MAKE_MOVE);
                    //state.row = state.row;

                }else{

                }

            } else if (gameState == Constants.GET_B_MOVE) {

                ui.printBoard(state);
                // row = ui.getMoveRow(state.getWhoseMove(),state.getXName(),state.getOName());
                col = ui.getMoveCol(state.getWhoseMove(),state.getRName(),state.getBName());
                // transpose 1 based to 0 based becasue board is 0 based but UI is 1 based
                col = col-1;
                // Find the lowest empty row in that column
                for(int i = Constants.BOARD_HEIGHT-1;i>=0;i--){
                    if(state.board[i][col] == Constants.BLANK){
                        // row is already 0 based
                        row = i;
                        break;
                    }
                }
                if (ui.isLegalMove(state, row, col)) {
                    state.setGameState(Constants.MAKE_MOVE);
                    //state.row = state.row;
                }
                // if the player types in 'c' or 'computer' for person b's name, the computer logic runs
                //This is the order of priorities the computer runs through when deciding where to play
                //It starts at blocking another
            }  else if (gameState == Constants.GET_C_MOVE) {
                ui.printBoard(state);
                int[] computerMoves = state.cMakeFourInARow();
                if((computerMoves[0] == 8)&&(computerMoves[1] == 8)){
                    computerMoves = state.cBlockThreeInARow();
                    if((computerMoves[0] == 8)&&(computerMoves[1] == 8)){
                        computerMoves = state.cMakeThreeInARow();
                        if((computerMoves[0] == 8)&&(computerMoves[1] == 8)){
                            computerMoves = state.cBlockTwoInARow();
                            if((computerMoves[0] == 8)&&(computerMoves[1] == 8)){
                                computerMoves = state.cMakeTwoInARow();
                                if((computerMoves[0] == 8)&&(computerMoves[1] == 8)){
                                    computerMoves = state.cMoveRandom();
                                }
                            }

                        }
                    }
                }

                row=computerMoves[0];
                col=computerMoves[1];
                // row = ui.getMoveRow(state.getWhoseMove(),state.getXName(),state.getOName());
                /*col = (int)(Math.random()*7)+1;

                // transpose 1 based to 0 based becasue board is 0 based but UI is 1 based
                col = col-1;
                // Find the lowest empty row in that column
                for(int i = Constants.BOARD_HEIGHT-1;i>=0;i--){
                if(state.board[i][col] == Constants.BLANK){
                // row is already 0 based
                row = i;
                break;
                }
                }/*/
                if (ui.isLegalMove(state, row, col)) {
                    state.setGameState(Constants.MAKE_MOVE);
                    //state.row = state.row;
                }

            } else if (gameState == Constants.MAKE_MOVE) {
                ui.printMove(state, row, col);
                /*if (ui.isBlank(state, row, col)){
                state.setBoardCell(row-2, col-1, state.getWhoseMove());
                }
                else{*/
                state.setBoardCell(row,col, state.getWhoseMove());
                //}
                /*
                int count = 0;
                count ++;
                if (count >=2){*/
                state.setGameState(Constants.CHECK_IF_WINNER);
            } /*else {
            state.setWhoseMove(state.getWhoseMove() * -1);
            if (state.getWhoseMove() == Constants.R) {
            state.setGameState(Constants.GET_R_MOVE);
            } else {
            state.setGameState(Constants.GET_B_MOVE);
            }
            }*/

            else if (gameState == Constants.CHECK_IF_WINNER) {
                if (state.isWinner()) { //if boolean returns true, returns message for r win or b win as seen in constants
                    if (state.getWhoseMove() == Constants.R) {
                        state.setGameState(Constants.R_WINS);
                    } else {
                        state.setGameState(Constants.B_WINS);
                    }
                } else {
                    state.setGameState(Constants.CHECK_IF_TIE);
                }

            } else if (gameState == Constants.CHECK_IF_TIE) {
                if (state.isTie()) {
                    ui.printTieGame();
                    state.setGameState(Constants.GAME_OVER);
                } else {
                    state.setWhoseMove(state.getWhoseMove() * -1);
                    if (state.getWhoseMove() == Constants.R) {
                        state.setGameState(Constants.GET_R_MOVE);
                    } else {
                        state.setGameState(Constants.GET_B_MOVE);
                        if (state.getBName().equals("computer")  ||state.getBName().equals("c")||state.getBName().equals("C") ){
                            state.setGameState(Constants.GET_C_MOVE);
                        }
                    }
                }

            } else if (gameState == Constants.R_WINS) {
                ui.printBoard(state);
                System.out.println("");
                ui.printWinner(state);
                state.setGameState(Constants.GAME_OVER);

            } else if (gameState == Constants.B_WINS) {
                ui.printBoard(state);
                System.out.println("");
                ui.printWinner(state);
                state.setGameState(Constants.GAME_OVER);

            } else if (gameState == Constants.GAME_OVER) {
                if (ui.startNewGame()) {
                    state.clearBoard();
                    state.setGameState(Constants.STANDBY);
                } else {
                    state.setGameState(Constants.QUIT_PROGRAM);

                }
            }
        }
    }
}
