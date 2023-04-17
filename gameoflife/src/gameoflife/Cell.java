package gameoflife;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Cell class contains all of the information for each individual
 * cell and then paints a JPanel to indicate living status.
 */
public class Cell extends JPanel implements MouseListener {

    private static int cellID = 0;
    private Tuple location;
    private int subgrid;
    private boolean alive = false;
    private boolean seeding = false;
    Random random = new Random();


    /**
     * One argument constructor
     * This one generates a new cell with the "living"
     * status already determined (from Tick or from seeding)
     * @param life
     */
    public Cell(boolean life){
        cellID++;
        this.alive = life;
        this.seeding = false;
        addMouseListener(this);
        //System.out.println(cellID);
    }

    /**
     * Generates the graphics for the cell
     * Black = alive; Light_Gray = not alive
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(alive) {
            // paint the board
            this.setBackground(Color.BLACK);
        }
        else{
            this.setBackground(Color.LIGHT_GRAY);
        }
    }

    /**
     * Set method for living status
     * @param life true = alive
     */
    public void setLife(boolean life){
        alive = life;
        repaint();
    }

    /**
     * Get method for living status
     * @return living status (life)
     */
    public boolean isAlive(){
        //System.out.println("Hey I am Cell # " + cellID + " and my life status is " + alive);
        return alive;
    }

    /**
     * Set method for seeding
     * @param seeding true = user can seed
     */
    public void setSeeding(boolean seeding){
        this.seeding = seeding;
    }

    /**
     * Set the life status based on user clicks
     * @param e mouse click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(seeding){
            setLife(!isAlive());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
