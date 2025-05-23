//STATE CLASS
//Some of this code was taken over from tic tac toe template provided by my proffessor
public class State
{
    private int gameState = Constants.STANDBY;
    private int whoseMove = Constants.R; // whoseMove keeps track of whose move it is
    private String rName = "";
    private String bName = "";
    public int[][] board = new int[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH+1];

    public void clearBoard(){ //at the end of the game all cells become blank
        for (int row=0; row<Constants.BOARD_HEIGHT; row++) {
            for (int col=0; col<Constants.BOARD_WIDTH; col++) {
                board[row][col] = Constants.BLANK;
            }
        }
    }

    public boolean isTie() { //if all cells are full, and no winner has been declared, tie
        for (int row=0; row<Constants.BOARD_HEIGHT; row++) {
            for (int col=0; col<Constants.BOARD_WIDTH; col++) {
                if (getBoardCell(row,col) == Constants.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    
    
    public boolean isWinner(){//If there are 4 bs or 4 rs adjacent, boolean returns true
        int total;
        for (int row=Constants.BOARD_HEIGHT-1; row>=0; row--){
            for(int col=0; col<=Constants.BOARD_WIDTH-4; col++){
                total = getBoardCell(row,col)+getBoardCell(row,col+1)+getBoardCell(row,col+2)+getBoardCell(row,col+3);
                if(total == -4||total == 4) return true;
            }
        }
        for (int col=0; col<=Constants.BOARD_WIDTH-1; col++){
            for(int row=Constants.BOARD_HEIGHT-1; row>=Constants.BOARD_HEIGHT-3; row--){
                total = getBoardCell(row,col)+getBoardCell(row-1,col)+getBoardCell(row-2,col)+getBoardCell(row-3,col);
                if(total == -4||total == 4) return true;
            }
        }
        for (int col = 0; col<=Constants.BOARD_WIDTH-4; col++){
            for (int row=Constants.BOARD_HEIGHT-1; row>=Constants.BOARD_HEIGHT-3; row--){
                total = getBoardCell(row,col)+getBoardCell(row-1,col+1)+getBoardCell(row-2,col+2)+getBoardCell(row-3,col+3);
                if(total == -4||total == 4) return true;
            }
        }
        for (int col = 0; col<=Constants.BOARD_WIDTH-4; col++){
            for (int row=Constants.BOARD_HEIGHT-4; row>=0; row--){
                total = getBoardCell(row,col)+getBoardCell(row+1,col+1)+getBoardCell(row+2,col+2)+getBoardCell(row+3,col+3);
                if(total == -4||total == 4) return true;
            }
        }
        return false;
    }
    

    public int[] cMoveRandom() {
        int[] returnValues = new int[2];
        int col = (int)(Math.random()*7)+1;
        int row = 0;

        // transpose 1 based to 0 based becasue board is 0 based but UI is 1 based
        col = col-1;
        // Find the lowest empty row in that column
        for(int i = Constants.BOARD_HEIGHT-1;i>=0;i--){
            if(board[i][col] == Constants.BLANK){
                // row is already 0 based
                row = i;
                break;
            }
        }
        returnValues[0] = row;
        returnValues[1] = col;
        return returnValues;
    }
 //lines 81-612 are code for the computer to play, I worked with a partner on this logic
    public int[] cMakeTwoInARow() {
        int[] returnValues = new int[2];
        int row=0;
        int col = 0;
        boolean foundIt = false;

        for(col=0; col<=Constants.BOARD_WIDTH-1; col++){
            for (row=Constants.BOARD_HEIGHT-1; row>=0; row--){
                //checks to make 2 in a row right 
                if (col<= Constants.BOARD_WIDTH-2){
                    int total = getBoardCell(row,col);
                    int neighbor = getBoardCell(row,col+1);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == 1 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col+1)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col+1;
                        System.out.println("right = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 2 in a row left
                if (col>=1){
                    int total = getBoardCell(row,col);
                    int neighbor = getBoardCell(row,col-1);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == 1 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col-1)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col-1;
                        System.out.println("left = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 2 in a row up
                if (row>=1){
                    int total = getBoardCell(row,col);
                    int neighbor = getBoardCell(row-1,col);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if (total == 1 && neighbor==Constants.BLANK){
                        foundIt = true;
                        col=col;
                        row=row-1;
                        System.out.println("up = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 2 in a row up right diagonal
                if (row>=1){
                    if(col<=Constants.BOARD_WIDTH-2){
                        int total = getBoardCell(row,col);
                        int neighbor = getBoardCell(row-1,col+1);
                        //black is defined as 1, blank spot is 0, and red is -1, 
                        //so if the spot is filled with black and the spot to the right is empty, found move
                        if ((total == 1 && neighbor==Constants.BLANK) &&
                        ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row,col+1)!=Constants.BLANK))) {
                            foundIt = true;
                            col=col+1;
                            row=row-1;
                            System.out.println("diaright = c="+col+ " r=" +row);
                            break;  

                        }
                    }
                }

                //checks to make 2 in a row up left diagonal

                if ( row>=1){
                    if(col>=1){
                        int total = getBoardCell(row,col);
                        int neighbor = getBoardCell(row-1,col-1);
                        //black is defined as 1, blank spot is 0, and red is -1, 
                        //so if the spot is filled with black and the spot to the right is empty, found move
                        if ((total == 1 && neighbor==Constants.BLANK) &&
                        ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row,col-1)!=Constants.BLANK))) {
                            foundIt = true;
                            col=col-1;
                            row=row-1;
                             System.out.println("dialeft = c="+col+ " r=" +row);
                            break;  

                        }
                    }
                }
            }
            if(foundIt){
                break;
            }
        }

        if(foundIt == false){
            row = 8;
            col = 8;
            System.out.println("nope");
        }
        returnValues[0] = row;
        returnValues[1] = col;
        return returnValues;
    }

    public int[] cBlockTwoInARow() {
        int[] returnValues = new int[2];
        int row=0;
        int col = 0;
        boolean foundIt = false;

        for(col=0; col<=Constants.BOARD_WIDTH-1; col++){
            for (row=Constants.BOARD_HEIGHT-1; row>=0; row--){
                int total = 0;
                int neighbor = 0;
                //check to the right
                if(col<=Constants.BOARD_WIDTH-3){
                    total = getBoardCell(row,col)+getBoardCell(row,col+1);
                    neighbor = getBoardCell(row,col+2);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -2 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col+2)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col+2;
                        break;  

                    }
                }
                //checks to the left
                if(col<=Constants.BOARD_WIDTH-3){
                    total = getBoardCell(row,col+1)+getBoardCell(row,col+2);
                    neighbor = getBoardCell(row,col);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -2 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col;
                        break;  

                    }
                }
                //check two in a row up
                if(row >= Constants.BOARD_HEIGHT-4){

                    total = getBoardCell(row,col)+getBoardCell(row-1,col);
                    neighbor = getBoardCell(row-2,col);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -2 && neighbor==Constants.BLANK)) {
                        foundIt = true;
                        col=col;
                        row = row-2;
                        break;  

                    }
                }
                //check to the up-right
                if(row>=2 && col<=Constants.BOARD_WIDTH-3){
                    total = getBoardCell(row,col)+getBoardCell(row-1,col+1);
                    neighbor = getBoardCell(row-2,col+2);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -2 && neighbor==Constants.BLANK) &&
                    (getBoardCell(row-1,col+2)!=Constants.BLANK)) {
                        foundIt = true;
                        col=col+2;
                        row=row-2;
                        break;  

                    }
                }
                //check to the up-left
                if(row>=2 && col>=2){
                    total = getBoardCell(row,col)+getBoardCell(row-1,col-1);
                    neighbor = getBoardCell(row-2,col-2);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -2 && neighbor==Constants.BLANK) &&
                    (getBoardCell(row-1,col-2)!=Constants.BLANK)) {
                        foundIt = true;
                        col=col-2;
                        row=row-2;
                        break;  

                    }
                }
            }

            if(foundIt){
                break;
            }
        }
        if(foundIt == false){
            row = 8;
            col = 8;
        }
        returnValues[0] = row;
        returnValues[1] = col;
        return returnValues;
    }

    public int[] cMakeThreeInARow() {
        int[] returnValues = new int[2];
        int row=0;
        int col = 0;
        boolean foundIt = false;

        for(col=0; col<=Constants.BOARD_WIDTH-1; col++){
            for (row=Constants.BOARD_HEIGHT-1; row>=0; row--){
                //checks to make 3 in a row right 
                if (col<= Constants.BOARD_WIDTH-3){
                    int total = getBoardCell(row,col)+getBoardCell(row,col+1);
                    int neighbor = getBoardCell(row,col+2);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == 2 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col+2)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col+2;
                        System.out.println("right3 = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 3 in a row left
                if (col>=2){
                    int total = getBoardCell(row,col)+getBoardCell(row,col-1);
                    int neighbor = getBoardCell(row,col-2);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == 2 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col-2)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col-2;
                        System.out.println("left3 = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 3 in a row up
                if (row>=2){
                    int total = getBoardCell(row,col)+getBoardCell(row-1,col);
                    int neighbor = getBoardCell(row-2,col);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if (total == 2 && neighbor==Constants.BLANK){
                        foundIt = true;
                        col=col;
                        row=row-2;
                        System.out.println("up3 = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 3 in a row up right diagonal
                if (row>=2){
                    if(col<=Constants.BOARD_WIDTH-3){
                        int total = getBoardCell(row,col)+getBoardCell(row-1,col+1);
                        int neighbor = getBoardCell(row-2,col+2);
                        //black is defined as 1, blank spot is 0, and red is -1, 
                        //so if the spot is filled with black and the spot to the right is empty, found move
                        if ((total == 2 && neighbor==Constants.BLANK) &&
                        ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row-1,col+2)!=Constants.BLANK))) {
                            foundIt = true;
                            col=col+2;
                            row=row-2;
                            System.out.println("diaright3 = c="+col+ " r=" +row);
                            break;  

                        }
                    }
                }

                //checks to make 3 in a row up left diagonal

                if ( row>=2){
                    if(col>=2){
                        int total = getBoardCell(row,col)+getBoardCell(row-1,col-1);
                        int neighbor = getBoardCell(row-2,col-2);
                        //black is defined as 1, blank spot is 0, and red is -1, 
                        //so if the spot is filled with black and the spot to the right is empty, found move
                        if ((total == 2 && neighbor==Constants.BLANK) &&
                        ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row-1,col-2)!=Constants.BLANK))) {
                            foundIt = true;
                            col=col-2;
                            row=row-2;
                             System.out.println("dialeft3 = c="+col+ " r=" +row);
                            break;  

                        }
                    }
                }
            }
            if(foundIt){
                break;
            }
        }
        if(foundIt == false){
            row = 8;
            col = 8;
        }
        returnValues[0] = row;
        returnValues[1] = col;
        return returnValues;
    }
    public int[] cMakeFourInARow() {
        int[] returnValues = new int[2];
        int row=0;
        int col = 0;
        boolean foundIt = false;

        for(col=0; col<=Constants.BOARD_WIDTH-1; col++){
            for (row=Constants.BOARD_HEIGHT-1; row>=0; row--){
                //checks to make 4 in a row right 
                if (col<= Constants.BOARD_WIDTH-4){
                    int total = getBoardCell(row,col)+getBoardCell(row,col+1)+getBoardCell(row,col+2);
                    int neighbor = getBoardCell(row,col+3);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == 3 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col+3)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col+3;
                        System.out.println("right4 = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 4 in a row left
                if (col>=3){
                    int total = getBoardCell(row,col)+getBoardCell(row,col-1)+getBoardCell(row,col-2);
                    int neighbor = getBoardCell(row,col-3);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == 3 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col-3)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col-3;
                        System.out.println("left4 = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 4 in a row up
                if (row>=3){
                    int total = getBoardCell(row,col)+getBoardCell(row-1,col)+getBoardCell(row-2,col);
                    int neighbor = getBoardCell(row-3,col);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if (total == 3 && neighbor==Constants.BLANK){
                        foundIt = true;
                        col=col;
                        row=row-3;
                        System.out.println("up4 = c="+col+ " r=" +row);
                        break;  

                    }
                }

                //checks to make 4 in a row up right diagonal
                if (row>=3){
                    if(col<=Constants.BOARD_WIDTH-4){
                        int total = getBoardCell(row,col)+getBoardCell(row-1,col+1)+getBoardCell(row-2,col+2);
                        int neighbor = getBoardCell(row-3,col+3);
                        //black is defined as 1, blank spot is 0, and red is -1, 
                        //so if the spot is filled with black and the spot to the right is empty, found move
                        if ((total == 3 && neighbor==Constants.BLANK) &&
                        ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row-2,col+3)!=Constants.BLANK))) {
                            foundIt = true;
                            col=col+3;
                            row=row-3;
                            System.out.println("diaright4 = c="+col+ " r=" +row);
                            break;  

                        }
                    }
                }

                //checks to make 4 in a row up left diagonal

                if ( row>=3){
                    if(col>=3){
                        int total = getBoardCell(row,col)+getBoardCell(row-1,col-1)+getBoardCell(row-1,col-2);
                        int neighbor = getBoardCell(row-3,col-3);
                        //black is defined as 1, blank spot is 0, and red is -1, 
                        //so if the spot is filled with black and the spot to the right is empty, found move
                        if ((total == 3 && neighbor==Constants.BLANK) &&
                        ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row-2,col-3)!=Constants.BLANK))) {
                            foundIt = true;
                            col=col-3;
                            row=row-3;
                             System.out.println("dialeft4 = c="+col+ " r=" +row);
                            break;  

                        }
                    }
                }
            }
            if(foundIt){
                break;
            }
        }
        if(foundIt == false){
            row = 8;
            col = 8;
        }
        returnValues[0] = row;
        returnValues[1] = col;
        return returnValues;
    }

    public int[] cBlockThreeInARow() {
        int[] returnValues = new int[2];
        int row=0;
        int col = 0;
        boolean foundIt = false;

        for(col=0; col<=Constants.BOARD_WIDTH-2; col++){
            for (row=Constants.BOARD_HEIGHT-1; row>=0; row--){
                int total = 0;
                int neighbor = 0;
                //check to the right
                if(col<=Constants.BOARD_WIDTH-4){
                    total = getBoardCell(row,col)+getBoardCell(row,col+1)+getBoardCell(row, col+2);
                    neighbor = getBoardCell(row,col+3);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -3 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col+3)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col+3;
                        break;  

                    }
                }
                //checks to the left
                if(col<=Constants.BOARD_WIDTH-4){
                    total = getBoardCell(row,col+1)+getBoardCell(row,col+2)+getBoardCell(row,col+3);
                    neighbor = getBoardCell(row,col);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -3 && neighbor==Constants.BLANK) &&
                    ((row<(Constants.BOARD_HEIGHT-1) && getBoardCell(row+1,col)!=Constants.BLANK) ||
                        (row== (Constants.BOARD_HEIGHT-1)))) {
                        foundIt = true;
                        col=col;
                        break;  

                    }
                }
                //check three in a row up
                if(row >= Constants.BOARD_HEIGHT-3){

                    total = getBoardCell(row,col)+getBoardCell(row-1,col)+getBoardCell(row-2,col);
                    neighbor = getBoardCell(row-3,col);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -3 && neighbor==Constants.BLANK)) {
                        foundIt = true;
                        col=col;
                        row = row-3;
                        break;  

                    }
                }
                //check to the up-right
                if(row>=3 && col<=Constants.BOARD_WIDTH-4){
                    total = getBoardCell(row,col)+getBoardCell(row-1,col+1)+getBoardCell(row-2,col+2);
                    neighbor = getBoardCell(row-3,col+3);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -3 && neighbor==Constants.BLANK) &&
                    (getBoardCell(row-2,col+3)!=Constants.BLANK)) {
                        foundIt = true;
                        col=col+3;
                        row=row-3;
                        break;  

                    }
                }
                //check to the up-left
                if(row>=3 && col>=3){
                    total = getBoardCell(row,col)+getBoardCell(row-1,col-1)+getBoardCell(row-2,col-2);
                    neighbor = getBoardCell(row-3,col-3);
                    //black is defined as 1, blank spot is 0, and red is -1, 
                    //so if the spot is filled with black and the spot to the right is empty, found move
                    if ((total == -3 && neighbor==Constants.BLANK) &&
                    (getBoardCell(row-2,col-3)!=Constants.BLANK)) {
                        foundIt = true;
                        col=col-3;
                        row=row-3;
                        break;  

                    }
                }
            }

            if(foundIt){
                break;
            }
        }
        if(foundIt == false){
            row = 8;
            col = 8;
        }
        returnValues[0] = row;
        returnValues[1] = col;
        return returnValues;
    }

    public int getGameState() {
        return this.gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getWhoseMove() {
        return this.whoseMove;
    }

    public void setWhoseMove(int whoseMove) {
        this.whoseMove = whoseMove;
    }    

    public String getRName() {
        return this.rName;
    }

    public void setRName(String rName) {
        this.rName = rName;
    }

    public String getBName() {
        return this.bName;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public int getBoardCell(int row, int col) {
        //return 0;
        return this.board [row][col];
    }

    public void setBoardCell(int row, int col, int value) {
        this.board[row][col] = value;
    }

}
