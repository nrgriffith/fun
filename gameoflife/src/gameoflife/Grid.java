package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * The game of life grid which consists of an array of cells.
 */

public class Grid extends JPanel {

    private Cell[][] cells;
    private int rows;
    private int cols;
    private int panels;
    private Random random = new Random();

    /**
     * Constructor
     * @param rows number of rows
     * @param cols number of columns
     */
    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        setLayout(new GridLayout(rows, cols, 1, 1));
        for(int i=0; i<cols; i++){
            for(int j=0; j<rows; j++){
                cells[i][j] = new Cell(false);
                add(cells[i][j]);
            }
        }
    }


    /**
     * Get method for individual cells
     * @param row row index of the cell
     * @param col column index of the cell
     * @return
     */
    public Cell getCell(int row, int col) {
        //System.out.println("cells[" + row%rows + "][" + col%cols + "]");
        return cells[row%(rows)][col%(cols)];
    }

    /**
     * Get method for number of rows
     * @return rows
     */
    public int getRows(){ return rows; }

    /**
     * Get method for number of columns
     * @return columns
     */
    public int getCols(){ return cols; }

    /**
     * Synchronized method to replace the cells from the Tick (SwingWorker) class.
     * @param startRow starting row index
     * @param endRow ending row index
     * @param startCol starting column index
     * @param endCol ending column index
     * @param newCells Cell array generated by Tick
     */
    public synchronized void replaceCells(int startRow, int endRow, int startCol, int endCol, Cell[][] newCells) {
        int rowCounter = 0;
        int colCounter = 0;
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                cells[i][j].setLife(newCells[rowCounter][colCounter].isAlive());
                cells[i][j].repaint();
                colCounter++;
            }
            rowCounter++;
            colCounter = 0;
        }
    }

    /**
     * Creates new random grid
     * @param startRow starting row
     * @param endRow ending row
     * @param startCol  starting column
     * @param endCol ending column
     */
    public void newRandom(int startRow, int endRow, int startCol, int endCol) {
        boolean alive;
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                alive = false;
                if(random.nextInt(2) == 1){
                    alive = true;
                }
                cells[i][j].setLife(alive);
                cells[i][j].repaint();
            }
        }
    }

    /**
     * Set all of the cells to blank and enable seeding
     */
    public void setBlank(){
        for(int i=0; i<cols; i++){
            for(int j=0; j<rows; j++){
                cells[i][j].setLife(false);
                cells[i][j].setSeeding(true);
            }
        }
    }

    /**
     * Stop seeding cells
     */
    public void stopSeeding(){
        for(int i=0; i<cols; i++){
            for(int j=0; j<rows; j++){
                cells[i][j].setSeeding(false);
            }
        }
    }
}
