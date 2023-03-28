package Compulsory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox<Double> linesCombo;
    JButton createButton;
    protected Integer numberOfDots;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));
        dotsSpinner.addChangeListener(this::updateSpinner);

        linesCombo = new JComboBox<Double>();
        linesLabel = new JLabel("probability:");
        linesCombo.addItem(1.0);
        linesCombo.addItem(0.5);
        dotsSpinner.addChangeListener(this::updatelinesCombo);

        //create the rest of the components
        add(dotsLabel); //JPanel uses FlowLayout by default
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);

    }

    private void updatelinesCombo(ChangeEvent changeEvent) {
        frame.drawingPanel.createBoard();
    }

    private void updateSpinner(ChangeEvent changeEvent) {
        frame.drawingPanel.createBoard();
    }


}
