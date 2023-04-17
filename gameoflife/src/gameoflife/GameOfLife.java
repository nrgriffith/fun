package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

/**
 * Main class to play the game.
 * @author Nichole Griffith
 */

public class GameOfLife extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int rows = 16;
    private final int cols = 16;
    private final int panels = 4;

    private final JPanel buttonJPanel;
    private final JButton[] buttons;
    private JPanel gridPanel;
    private boolean seeding = false;

    private Grid grid;

    private ArrayList<Tick> ticks;


    /**
     * Constructor for the GUI
     */
    public GameOfLife() {
        // construct some things
        super("Game of Life");
        //Tick[] tock;
        grid = new Grid(rows*2, cols*2);
        //grid = new Grid(rows, cols, panels);
        //tock = new Tick[4];

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        buttonJPanel = new JPanel();
        buttons = new JButton[5];
        buttonJPanel.setLayout(new GridLayout(1, 3, 1, 1));
        buttonJPanel.setPreferredSize(new Dimension(800, 25));
        buttonJPanel.setMaximumSize(buttonJPanel.getPreferredSize());
        // -------------------- Button 0: Start
        buttons[0] = new JButton("Start");
        buttons[0].addActionListener(this);
        buttons[0].setEnabled(false);
        buttonJPanel.add(buttons[0]);
        // -------------------- Button 1: Stop
        buttons[1] = new JButton("Stop");
        buttons[1].addActionListener(this);
        buttons[1].setEnabled(false);
        buttonJPanel.add(buttons[1]);
        // -------------------- Button 2: New Random
        buttons[2] = new JButton("New Random");
        buttons[2].addActionListener(this);
        buttonJPanel.add(buttons[2]);
        // -------------------- Button 3: New Seeded
        buttons[3] = new JButton("New Seeded");
        buttons[3].addActionListener(this);
        buttonJPanel.add(buttons[3]);
        // -------------------- Button 4: Quit
        buttons[4] = new JButton("Exit Program");
        buttons[4].addActionListener(this);
        buttonJPanel.add(buttons[4]);

        add(buttonJPanel); // add the button panel to the main panel

        add(grid);

        pack();

        setBackground(Color.DARK_GRAY);
        setSize(800, 745);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Launches the GUI
     */
    public static void main(String[] args){
        // load gui
        GameOfLife board = new GameOfLife();
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Responds to the action performed by the user.
     * On "start", new threads (Tick) are created then executed.
     * On "stop", the threads are cancelled, pausing the game.
     * On "new random", cells are randomly generated
     * On "new seeded", the user can specify seeds
     * @param e ActionEvent (a mouseclick)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttons[0]) {
            buttons[0].setEnabled(false);
            buttons[2].setEnabled(false);
            buttons[3].setEnabled(false);
            buttons[1].setEnabled(true);
            ticks = new ArrayList<>();
            ticks.add(new Tick(grid, 0, rows * panels / 4, 0, cols * panels / 4));
            ticks.add(new Tick(grid, 0, rows * panels / 4, cols * panels / 4, cols * panels / 2));
            ticks.add(new Tick(grid, rows * panels / 4, rows * panels / 2, 0, cols * panels / 4));
            ticks.add(new Tick(grid, rows * panels / 4, rows * panels / 2, cols * panels / 4, cols * panels / 2));
            for(Tick tick : ticks){
                tick.execute();
            }

        }
        else if(e.getSource() == buttons[1]) {
            buttons[0].setText("Continue");
            buttons[0].setEnabled(true);
            buttons[1].setEnabled(false);
            buttons[2].setEnabled(true);
            buttons[3].setEnabled(true);
            for(Tick tick : ticks){
                tick.cancel(true);
            }
        }

        else if(e.getSource() == buttons[2]){
            buttons[0].setText("Start");
            buttons[0].setEnabled(true);
            grid.newRandom(0,rows*panels/2, 0, cols*panels/2);
        }

        else if(e.getSource() == buttons[3] && !seeding){
            seeding = true;
            buttons[0].setText("Start");
            buttons[0].setEnabled(false);
            buttons[1].setEnabled(false);
            buttons[2].setEnabled(false);
            grid.setBlank();
            buttons[3].setText("Click here when done");
        }
        else if(e.getSource() == buttons[3] && seeding){
            seeding = false;
            grid.stopSeeding();
            buttons[3].setText("New Seeded");
            buttons[0].setText("Start");
            buttons[0].setEnabled(true);
            buttons[2].setEnabled(true);
        }

        else if(e.getSource() == buttons[4]){
            System.exit(0);
        }

    }

}