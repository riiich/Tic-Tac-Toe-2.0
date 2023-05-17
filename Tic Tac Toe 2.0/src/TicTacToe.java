import java.util.Scanner;

public class TicTacToe
{
    public static void main(String args[])
    {
        char choice = ' ';

        //will as if the player wants to keep playing, and will continue if they press 'y'
        do
        {
            boolean gameOver = false;
            int playerSize = 0;
            Scanner numOfPlayers = new Scanner(System.in);

            System.out.print("Enter the number of players (3-10 players): ");
            try
            {
                playerSize = numOfPlayers.nextInt();
            }
            catch(Exception e)
            {
                //Checks user input to see if they entered an integer or not.
                while(!numOfPlayers.hasNextInt())
                {
                    System.out.print("Invalid input. Enter the number of players (3-10 players): ");
                    numOfPlayers = new Scanner(System.in);
                }
            }

            //makes sure the user inputs a player size that's within the bounds
            while(playerSize < 3 || playerSize > 10)
            {
                System.out.print("Invalid amount. Please enter the number of players (3-10 players): ");
                numOfPlayers = new Scanner(System.in);

                //if user doesn't enter an integer, keep prompting the user
                if(!numOfPlayers.hasNextInt())
                {
                    System.out.print("Invalid input. Enter the number of players (3-10 players): ");
                    numOfPlayers = new Scanner(System.in);
                }

                playerSize = numOfPlayers.nextInt();
            }

            GameLogic game = new GameLogic();   //the game logic object
            game.setBoard(playerSize);  //initialize the board with player size
            char playerPiece = ' ';
            int playerIndex = 0;
            int playerCount = 0;

            System.out.println("Enter pieces for each player.");
            for(int i = 0; i < game.getPlayers().length; i++)
            {
                System.out.print("Player " + (i+1) + ": ");
                Scanner pieces1 = new Scanner(System.in);
                String tempPiece = pieces1.next();
                playerPiece = tempPiece.charAt(0);

                //check if the piece has already been taken
                for(int j = 0; j < playerCount; j++)
                {
                    while(playerPiece == game.getPlayers()[j].getSymbol())  //loop keeps running if a player puts their piece on an occupied spot
                    {
                        System.out.println(playerPiece + " has already been taken. Enter another piece.");
                        System.out.print("Player " + (playerCount+1) + ": ");
                        pieces1 = new Scanner(System.in);
                        tempPiece = pieces1.next();
                        playerPiece = tempPiece.charAt(0);
                    }
                }

                game.getPlayers()[i].setPiece(playerPiece);
                playerCount++;
            }
            System.out.println();

            System.out.println("These are the Player's Pieces.");
            for(int i = 0; i < game.getPlayers().length; i++)
            {
                System.out.println("Player " + (i+1) + "'s Piece: " + game.getPlayers()[i].getSymbol());
            }
            System.out.println();

            //Setting win condition
            int winInARow = 0;
            System.out.print("How many in a row would you like for a player to win? (from 3 - " + (playerSize+1) + "): ");
            Scanner userWinCon = new Scanner(System.in);

            //check if the user input is an integer or not
            try
            {
                winInARow = userWinCon.nextInt();
            }
            catch(Exception e)
            {
                //Checks user input to see if they entered an integer or not.
                while(!userWinCon.hasNextInt())
                {
                    System.out.print("Invalid input. Enter how many in a row would you like for a player to win? (from 3 - " + (playerSize+1) + "): ");
                    userWinCon = new Scanner(System.in);
                }
            }

            //check if the user input a winning condition that's within the bounds.
            while(winInARow < 3 || winInARow > playerSize+1)
            {
                System.out.print("Invalid number of pieces to win in a row. Please enter how many in a row you would like for a player to win (from 3 - " + (playerSize+1) + "): ");
                userWinCon = new Scanner(System.in);
                while(!userWinCon.hasNextInt())
                {
                    System.out.print("Invalid input. Enter how many in a row would you like for a player to win? (from 3 - " + (playerSize+1) + "): ");
                    userWinCon = new Scanner(System.in);
                }
                winInARow = userWinCon.nextInt();
            }
            game.setWinCondition(winInARow);    //setting the win condition

            Scanner userRow;
            Scanner userCol;
            int row = 0;
            int col = 0;
            int playerTurn = 0;

            while(!gameOver)
            {
                printBoard(game.getBoard().getBoard());

                System.out.print("Player " + (playerTurn+1) + "'s Turn (" + game.getPlayers()[playerTurn].getSymbol() + "). ");
                System.out.println("Place your piece on the board by entering a row and a column.");
                userRow = new Scanner(System.in);
                userCol = new Scanner(System.in);

                try
                {
                    System.out.print("Row: ");
                    row = userRow.nextInt();

                }
                catch(Exception e)
                {
                    while(!userRow.hasNextInt())
                    {
                        System.out.println("Invalid input. Player " + (playerTurn+1) + ", re-enter a row.");
                        userRow = new Scanner(System.in);
                        System.out.print("Row: ");
                    }
                    row = userRow.nextInt();
                }

                try
                {
                    System.out.print("Column: ");
                    col = userCol.nextInt();
                }
                catch(Exception e)
                {
                    while(!userCol.hasNextInt())
                    {
                        System.out.println("Invalid input. Player " + (playerTurn+1) + ", re-enter a column.");
                        userCol = new Scanner(System.in);
                        System.out.print("Column: ");
                    }
                    col = userCol.nextInt();
                }

                //makes sure the user enters a position that's within the borders of the board
                while(row < 0 || row >= game.getBoard().getBoardSize() || col < 0 || col >= game.getBoard().getBoardSize())
                {
                    System.out.println("Invalid input. Re-enter a row and column.");
                    System.out.print("Row: ");
                    row = userRow.nextInt();
                    System.out.print("Column: ");
                    col = userCol.nextInt();
                }

                //only checks if the spot in the board is taken if the row and column entered by the user is within the board borders
                if(row >= 0 && row < game.getBoard().getBoardSize() && col >= 0 && col < game.getBoard().getBoardSize())
                {
                    //Asks the user to re-enter position if the spot is occupied by another piece.
                    while(!game.getBoard().positionEmpty(row, col))
                    {
                        System.out.println("Spot is taken. Enter another position.");
                        System.out.print("Row: ");
                        row = userRow.nextInt();
                        System.out.print("Column: ");
                        col = userCol.nextInt();
                    }
                }

                game.getBoard().setPiece(row, col, game.getPlayers()[playerTurn].getSymbol());  //set the piece on the board
                playerTurn++;   //increment player turn

                //reset the player index in the player array to keep track of turns
                if(playerTurn >= playerSize)
                    playerTurn = 0;

                //check for the winning conditions
                if(gameOver = game.isTie())
                    break;
                else if(gameOver = game.winConditionRow())
                    break;
                else if(gameOver = game.winConditionColm())
                    break;
                else if(gameOver = game.winConditionDiagonal())
                    break;
                else if(gameOver = game.winConditionAntiDiagonal())
                    break;
            }

            printBoard(game.getBoard().getBoard()); //print board to show their last move

            if(gameOver = game.isTie())
            {
                System.out.println("It's a tie!");
            }

            //Ask the user if they'd like to play the game again.
            System.out.print("Would you like to play again (y/n)?: ");
            Scanner answer = new Scanner(System.in);
            String tempString = answer.next();
            choice = tempString.charAt(0);

            if(choice == 'y')
            {
                game.resetBoard();  //if they want to play again, the board gets cleared
                gameOver = game.getStatus();
            }

        }while(choice == 'y');

        System.out.println("Thanks for playing. Goodbye!");
    }

    //prints the board
    public static void printBoard(char[][] board)
    {
        System.out.println();
        for(int i = 0; i < board.length; i++)
        {
            System.out.print(" | " + i);
        }
        System.out.println(" | ");

        for(int i = 0; i < board.length; i++)
        {
            for(int k = 0; k < board.length*2.1; k++)
            {
                System.out.print(" -");
            }
            System.out.println();
            System.out.print(i + "| ");

            for(int j = 0; j < board.length; j++)
            {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
}