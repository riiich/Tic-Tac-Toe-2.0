/**
 * This class initializes the board.
 * This should probably create a multidimensional array of chars or strings so that the user can input their symbol.
 *
 */
public class Board
{
    private char[][] board;
    private int numOfPlayers;
    private int boardSize;

    /**
     * Initialize the 2D array for the board according to the number of players with blank characters
     * @param numOfPlayers
     */
    public void initialize(int numOfPlayers)
    {
        this.numOfPlayers = numOfPlayers;
        this.boardSize = this.numOfPlayers + 1;
        this.board = new char[this.boardSize][this.boardSize];

        for(int i = 0; i < this.boardSize; i++)
        {
            for(int j = 0; j < this.boardSize; j++)
            {
                this.board[i][j] = ' ';
            }
        }
    }

    /**
     * Gets the board (2D-array)
     * @return
     */
    public char[][] getBoard()
    {
        return this.board;
    }

    //ACCESSORS
    /**
     * Return the status of the position of the specified index in the 2D array.
     * Returns true if spot is occupied. Returns false is spot is empty.
     * @param row
     * @param col
     * @return
     */
    public char positionPiece(int row, int col)
    {
        return board[row][col];
    }

    /**
     * Returns the amount of players in the game
     * @return
     */
    public int getNumOfPlayers()
    {
        return this.numOfPlayers;
    }

    public int getBoardSize()
    {
        return this.boardSize;
    }

    public boolean positionEmpty(int row, int col)
    {
        return this.board[row][col] == ' ';
    }

    //MUTATORS
    /**
     * Change the status of the position of the specified index in the 2D array.
     * @param row
     * @param col
     */
    public void setPiece(int row, int col, char piece)
    {
        this.board[row][col] = piece;
    }

    /**
     * Clears the entire board to be blank again
     */
    public void clearBoard()
    {
        for(int i = 0; i < this.boardSize; i++)
        {
            for(int j = 0; j < this.boardSize; j++)
            {
                this.board[i][j] = ' ';
            }
        }
    }
}