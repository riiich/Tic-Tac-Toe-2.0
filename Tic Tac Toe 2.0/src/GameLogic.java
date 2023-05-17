public class GameLogic
{
    private int count = 0;
    private int inARowToWin = 0;
    private boolean status = false;
    private Player[] players;
    private Board board = new Board();

    /**
     * Initializes the board.
     * Also creates an array for the players
     * @param numOfPlayers
     */
    public void setBoard(int numOfPlayers)
    {
        this.board.initialize(numOfPlayers);
        this.players = new Player[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++)
        {
            this.players[i] = new Player();
        }
    }

    /**
     * Checks for a win in a row for the rows, according to win condition.
     * Returns true if there is a winner in a row.
     * @return
     */
    public boolean winConditionRow()
    {
        for (int i = 0; i < this.board.getBoardSize(); i++) //rows
        {
            int[] countWin = new int[this.players.length];     //creates an array for each row on the board
            char prevPiece = this.board.positionPiece(i, 0);     //holds the previous piece to compare to the current piece

            for (int j = 0; j < this.board.getBoardSize(); j++) //columns
            {
                char currPiece = this.board.positionPiece(i, j);    //get the current piece in this certain spot on the board

                if(currPiece != prevPiece)  //checks if the current piece is not the same as the previous piece
                {
                    for(int k = 0; k < this.players.length; k++)
                    {
                        if(countWin[k] < this.inARowToWin) //checks if the kth index in the countList array is less than the win condition
                            countWin[k] = 0;   //reset the count to 0 inside the countList at the kth index
                    }
                    prevPiece = currPiece;  //set the previous piece to the current piece as the index goes to the next piece
                }

                int count = 0;  //counter to increment to next index in countList array
                for(Player player : this.players)   //go through the player array
                {
                    if(player.getSymbol() == currPiece) //check if the current player's piece is the same as the current piece that's being held
                    {
                        countWin[count]++;     //if the player's piece at this spot is the same as the current piece, then increment at the countList index
                        break;  //break out of loop once the count index in countList increments
                    }
                    else
                        count++;    //increment the counter
                }
            }

            //goes through countWin array to see if there's a player that has won
            int playerIndex = 0;
            for (int win : countWin)
            {
                if (win == inARowToWin)
                {
                    System.out.println("Player " + (playerIndex+1) + " is the winner!");
                    return true;
                }
                else
                    playerIndex++;
            }
        }

        return false;
    }

    /**
     * Checks for a win in a row for the column, according to win condition.
     * Returns true if there is a winner in a column.
     * @return
     */
    public boolean winConditionColm()
    {
        for (int i = 0; i < this.board.getBoardSize(); i++)
        {
            int[] countWin = new int[this.players.length];
            char prevPiece = this.board.positionPiece(i, 0);

            for (int j = 0; j < this.board.getBoardSize(); j++)
            {
                char currPiece = this.board.positionPiece(j, i);

                if(currPiece != prevPiece)
                {
                    for(int k = 0; k < this.players.length; k++)
                    {
                        if(countWin[k] < this.inARowToWin)
                            countWin[k] = 0;
                    }
                    prevPiece = currPiece;
                }

                int count = 0;
                for(Player player : this.players)
                {
                    if(player.getSymbol() == currPiece)
                    {
                        countWin[count]++;
                        break;
                    }
                    else
                        count++;
                }

            }

            //goes through countWin array to see if there's a player that has won
            int playerIndex = 0;
            for (int win : countWin)
            {
                if (win == this.inARowToWin)
                {
                    System.out.println("Player " + (playerIndex+1) + " is the winner!");
                    return true;
                }
                else
                    playerIndex++;
            }
        }

        return false;
    }

    /**
     * Checks for a win in a row for the main diagonal, according to win condition.
     * Returns true if there is a winner in a row.
     * @return
     */
    public boolean winConditionDiagonal()
    {
        //lower diagonal checks
        for(int i = 0; i < this.board.getBoardSize(); i++)
        {
            int[] countWin = new int[this.players.length];  //create an array for each row, will keep track of how many pieces in a row there is
            char prevPiece = this.board.positionPiece(i, 0);    //hold the previous piece

            for(int j = i, k = 0; j < this.board.getBoardSize(); j++, k++)  //j and k is used to iterate over the lower diagonals
            {
                char currPiece = this.board.positionPiece(j, k);    //get piece on the board and hold in variable

                if(prevPiece != currPiece)  //checks if previous piece isn't the same as the current piece
                {
                    for(int l = 0; l < this.players.length; l++)    //goes through
                    {
                        if(countWin[l] < this.inARowToWin)  //checks if the lth index in the countWin is less than the win condition
                            countWin[l] = 0;    //reset the value in the lth index in the countWin array if there isn't a match from previous and current piece
                    }
                    prevPiece = currPiece;  //previous piece holds the current piece
                }

                int count = 0;
                for(Player player : this.players)   //go through the player array
                {
                    if(player.getSymbol() == currPiece) //checks if current player's piece is the same as the current piece on this spot
                    {
                        countWin[count]++;  //if it's the same, increment the count[th] index in the countWin array
                        break;  //break out the loop
                    }
                    else
                        count++;    //if it's not the same, then increment the count to go to the next index
                }
            }

            //goes through countWin array to see if there's a player that has won
            int playerIndex = 0;
            for (int win : countWin)
            {
                if (win == inARowToWin)     //go through the countWin int array to look for the index that equals the win condition
                {
                    System.out.println("Player " + (playerIndex+1) + " is the winner!");
                    return true;
                }
                else
                    playerIndex++;
            }
        }

        //upper diagonal checks
        for(int i = 0; i < this.board.getBoardSize(); i++)
        {
            int[] countWin = new int[this.players.length];  //create an array for each row, will keep track of how many pieces in a row there is
            char prevPiece = this.board.positionPiece(i, 0);    //hold previous piece of current piece to compare

            for(int j = i, k = 0; j < this.board.getBoardSize(); j++, k++)  //iterate over the upper diagonal
            {
                char currPiece = this.board.positionPiece(k, j);    //hold current piece on the board

                if(prevPiece != currPiece)  //checks if the previous piece is not equal to the current piece
                {
                    for(int l = 0; l < this.players.length; l++)    //goes through the countWin array
                    {
                        if(countWin[l] < this.inARowToWin)  //checks the lth index in countWin to see if it's less than the win condition
                            countWin[l] = 0;    //if it's less than the win condition, reset it since there's no match
                    }
                    prevPiece = currPiece;  //set the previous piece to the current piece
                }

                //go through the player array to check for matching pieces
                int count = 0;
                for(Player player : this.players)
                {
                    if(player.getSymbol() == currPiece) //checks if the current player's piece matches the current piece
                    {
                        countWin[count]++;  //if it matches, increment at count[th] index
                        break;  //break out of the loop
                    }
                    else
                        count++;    //if the piece doesn't match, increment to the next index in the countWin array to check for matching pieces
                }
            }

            //goes through countWin array to see if there's a player that has won
            int playerIndex = 0;
            for (int win : countWin)
            {
                if (win == inARowToWin) //go through the countWin int array to look for the index that equals the win condition
                {
                    System.out.println("Player " + (playerIndex+1) + " is the winner!");
                    return true;
                }
                else
                    playerIndex++;
            }
        }

        return false;
    }

    /**
     * Checks for a win in a row for the anti-diagonal, according to win condition.
     * Returns true if there is a winner in a row.
     * @return
     */
    public boolean winConditionAntiDiagonal()
    {
        //upper diagonal checks
        for(int i = 0; i < this.board.getBoardSize(); i++)
        {
            int[] countWin = new int[this.players.length];  //create an array for each row, will keep track of how many pieces in a row there is
            char prevPiece = this.board.positionPiece(i, 0);    //hold the previous piece, but at first, hold the first piece on the row

            for(int j = this.board.getBoardSize() - 1 - i, k = 0; j >= 0; j--, k++) //starts at the very top right piece on the board
            {
                char currPiece = this.board.positionPiece(k, j);    //hold the piece that's on the board at this certain spot

                if(prevPiece != currPiece)  //checks if the previous piece is not the same as the current piece
                {
                    for(int l = 0; l < this.players.length; l++)    //loop through countWin array
                    {
                        if(countWin[l] < this.inARowToWin)  //checks if the lth index in countWin is less than the win condition
                            countWin[l] = 0;    //if it is, reset the matching value back to 0
                    }
                    prevPiece = currPiece;  //set the previous piece to the current piece
                }

                //go through player array to check for matching pieces
                int count = 0;
                for(Player player : this.players)
                {
                    if(player.getSymbol() == currPiece) //check if current player's piece matches the current piece on the board
                    {
                        countWin[count]++;  //if it matches, increment the count[th] index in the countWin array
                        break;  //break out of the loop
                    }
                    else
                        count++;    //if it doesn't match, then increment count counter to go the next index in countWin array
                }
            }

            //goes through countWin array to see if there's a player that has won
            int playerIndex = 0;
            for (int win : countWin)
            {
                if (win == this.inARowToWin)    //go through the countWin int array to look for the index that equals the win condition
                {
                    System.out.println("Player " + (playerIndex+1) + " is the winner!");
                    return true;
                }
                else
                    playerIndex++;
            }
        }

        //lower diagonal checks
        for(int i = 0; i < this.board.getBoardSize(); i++)
        {
            int[] countWin = new int[this.players.length];  //create an array for each row, will keep track of how many pieces in a row there is
            char prevPiece = this.board.positionPiece(i, 0);

            for(int j = this.board.getBoardSize() - 1, k = i; k < this.board.getBoardSize(); j--, k++)  //starts at the top right
            {
                char currPiece = this.board.positionPiece(k, j);    //hold the piece that's on the board at this certain spot

                if(prevPiece != currPiece)  //checks if the current piece matches the previous piece
                {
                    for(int l = 0; l < this.players.length; l++)
                    {
                        if(countWin[l] < this.inARowToWin)  //goes through the countWin array to check if the lth index is less than the win condition
                            countWin[l] = 0;    //if it is, then reset the counter in the lth index of countWin back to 0
                    }
                    prevPiece = currPiece;  //set the previous piece to the current piece
                }

                int count = 0;
                for(Player player : this.players)   //goes through the player array
                {
                    if(player.getSymbol() == currPiece) //checks if the piece on the current spot on the board matches the current piece
                    {
                        countWin[count]++;  //if it matches, increment the counter in the count[th] index
                        break;  //break out of the loop
                    }
                    else
                        count++;    //if it doesn't match, increment the index to go to the next index
                }
            }

            //goes through countWin array to see if there's a player that has won
            int playerIndex = 0;
            for (int win : countWin)
            {
                if (win == this.inARowToWin)    //go through the countWin int array to look for the index that equals the win condition
                {
                    System.out.println("Player " + (playerIndex+1) + " is the winner!");
                    return true;
                }
                else
                    playerIndex++;
            }
        }

        return false;
    }

    /**
     * Since the board is filled with ' ' characters, this method will check if there are any ' ' characters left.
     * Count increments if there are any ' ' characters left, and if there is, there isn't a tie yet.
     * Returns true if the count is 0, which indicates that all the entire board is occupied by a player piece.
     * @return
     */
    public boolean isTie()
    {
        int count = 0;
        for(int i = 0; i < this.board.getBoardSize(); i++)
        {
            for(int j = 0; j < this.board.getBoardSize(); j++)
            {
                if(this.board.getBoard()[i][j] == ' ')  //if the piece on the board is empty then increment the counter
                    count++;
            }
        }
        this.status = true;
        return count == 0;  //returns true if the counter is still 0, which means that there was no empty spots on the board and the board was filled.
    }

    //////////////////ACCESSORS///////////////////
    /**
     * Returns the status of the game.
     * @return
     */
    public boolean getStatus()
    {
        return this.status;
    }

    /**
     * Returns the array for the players
     * @return
     */
    public Player[] getPlayers()
    {
        return this.players;
    }

    /**
     * Returns the board.
     * @return
     */
    public Board getBoard()
    {
        return this.board;
    }

    /**
     * Returns the win condition number.
     * @return
     */
    public int getWinCondition()
    {
        return this.inARowToWin;
    }

    //////////////////MUTATORS///////////////////
    /**
     * Places a piece on the board.
     * @param index
     * @param piece
     */
    public void setPlayerPiece(int index, char piece)
    {
        this.players[index].setPiece(piece);
    }

    /**
     * Sets the status of the game.
     * @param Status
     */
    public void setStatus(boolean Status)
    {
        this.status = Status;
    }

    /**
     * Sets the amount in a row to win (the win condition)
     * @param winCon
     */
    public void setWinCondition(int winCon)
    {
        this.inARowToWin = winCon;
    }

    public void resetBoard()
    {
        this.board.clearBoard();
        this.status = false;
    }
}