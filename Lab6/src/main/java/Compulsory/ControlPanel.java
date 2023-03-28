package Compulsory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {

        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //configure listeners for all buttons
        add(exitBtn);
        exitBtn.addActionListener(this::exitGame);
        add(saveButton);
        add(loadButton);


    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
    private void saveGame(ActionEvent e){
    }
    /*

    private void saveGame(ActionEvent e){
        ImageIO.write()
    }
*/

}
