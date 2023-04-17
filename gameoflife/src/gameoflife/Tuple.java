package gameoflife;

/**
 * A 'tuple' class to keep track of linked (x,y) coordinates.
 */

public class Tuple {
    private int rowIndex;
    private int colIndex;
    private boolean active = true;

    /**
     * Constructor
     * @param row x coordinate
     * @param col y coordinate
     */

    public Tuple(int row, int col){
        this.rowIndex = row;
        this.colIndex = col;
    }

    /**
     * Get method for y coordinate
     * @return y coordinate
     */
    public int getColIndex() {
        return colIndex;
    }

    /**
     * Get method for x coordinate
     * @return x coordinate
     */

    public int getRowIndex() { return rowIndex; }

    /**
     * Set method for y coordinate
     * @param colIndex y coordinate
     */

    public void setColIndex(int colIndex) {
        if(colIndex < 0){
            this.colIndex = 0;
        }
        else {
            this.colIndex = colIndex;
        }
    }

    /**
     * Set method for x coordinate
     * @param rowIndex x coordinate
     */

    public void setRowIndex(int rowIndex) {
        if(rowIndex < 0) {
            this.rowIndex = 0;
        }
        else{
            this.rowIndex = rowIndex;
        }
    }

    /** Evaluates whether this tuple is equal to another one
     *
     * @param row x coordinate
     * @param col y coordinate
     * @return true or false
     */
    public boolean isEqual(int row, int col){
        return row == rowIndex && col == colIndex;
    }

    /**
     * Deactivates this tuple
     */
    public void deactivate() { this.active = false; }

    /**
     * Get method for active variable
     * @return true or false
     */
    public boolean isActive() { return active; }
}

