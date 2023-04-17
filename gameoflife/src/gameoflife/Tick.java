package gameoflife;

import javax.swing.*;

/**
 * Tick class extends SwingWorker.
 * It checks the cells in the panel window (and the ones outside the border.)
 * Then it returns an array of new cells to replace the old.
 */

public class Tick extends SwingWorker<Cell[][], Integer> {

    private int beginRow;
    private int endRow;
    private int beginCol;
    private int endCol;
    private Grid grid;

    /**
     * Constructor
     * @param grid the grid
     * @param beginRow the beginning row index of the panel
     * @param endRow the ending row index of the panel
     * @param beginCol the beginning column index of the panel
     * @param endCol the ending column index of the panel
     */
    public Tick(Grid grid, int beginRow, int endRow, int beginCol, int endCol){
        this.beginCol = beginCol;
        this.beginRow = beginRow;
        this.endCol = endCol;
        this.endRow = endRow;
        this.grid = grid;
    }

    /**
     * Checks each cell against its neighbors, then returns an array of
     * new cells that are alive or not alive depending on the rules of the game.
     * @return an array of new cells to replace the old
     * @throws Exception
     */
    @Override
    protected Cell[][] doInBackground() throws Exception {

            while(!isCancelled()) {
                int rowDistance = grid.getRows();
                int colDistance = grid.getCols();

                int shortRowDistance = Math.abs(endRow - beginRow);
                int shortColDistance = Math.abs(endCol - beginCol);

                Cell[][] newCells = new Cell[shortRowDistance][shortColDistance];

                int rowCounter = 0;
                int colCounter = 0;
                for (int i = beginRow; i < endRow; i++) {
                    for (int j = beginCol; j < endCol; j++) {
                        if (isCancelled()) {
                            return null;
                        }
                        int counter = 0;
                        if (grid.getCell(((i - 1) % rowDistance + rowDistance) % rowDistance, ((j - 1) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;
                        if (grid.getCell(((i - 1) % rowDistance + rowDistance) % rowDistance, ((j) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;
                        if (grid.getCell(((i - 1) % rowDistance + rowDistance) % rowDistance, ((j + 1) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;
                        if (grid.getCell(((i) % rowDistance + rowDistance) % rowDistance, ((j - 1) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;
                        if (grid.getCell(((i) % rowDistance + rowDistance) % rowDistance, ((j + 1) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;
                        if (grid.getCell(((i + 1) % rowDistance + rowDistance) % rowDistance, ((j - 1) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;
                        if (grid.getCell(((i + 1) % rowDistance + rowDistance) % rowDistance, ((j) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;
                        if (grid.getCell(((i + 1) % rowDistance + rowDistance) % rowDistance, ((j + 1) % colDistance + colDistance) % colDistance).isAlive())
                            counter++;

                        if ((counter == 2 && grid.getCell(i, j).isAlive()) || counter == 3) {
                            newCells[rowCounter][colCounter] = new Cell(true);
                        } else {
                            newCells[rowCounter][colCounter] = new Cell(false);
                        }
                        colCounter++;
                    }
                    rowCounter++;
                    colCounter = 0;
                }
                grid.replaceCells(beginRow, endRow, beginCol, endCol, newCells);
                Thread.sleep(180);
            }
        return null;
    }

}