public class Player
{
    private char piece;

    /**
     * Sets the symbol for the player piece
     * @param symbol
     */
    public void setPiece(char symbol)
    {
        this.piece = symbol;
    }

    /**
     * Returns the player piece symbol
     * @return
     */
    public char getSymbol()
    {
        return this.piece;
    }
}