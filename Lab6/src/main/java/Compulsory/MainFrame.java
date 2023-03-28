package Compulsory;

import javax.swing.*;

import static java.awt.BorderLayout.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel drawingPanel;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the components
        controlPanel=new ControlPanel(this);
        configPanel=new ConfigPanel(this);
        drawingPanel = new DrawingPanel(this);

        //arrange the components in the container (frame)
        //JFrame uses a BorderLayout by default
        add(configPanel, NORTH);
        add(controlPanel, SOUTH);
        add(drawingPanel, CENTER); //this is BorderLayout.CENTER

        //invoke the layout manager
        pack();
    }
}

