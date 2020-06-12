package view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

/**
 * @author bowen
 *
 */
/* a summary panel that display user information */
public class SummaryPanel extends JPanel {
    private static final long serialVersionUID = -705389165572724025L;
    private JLabel name;
    private JLabel point;
    private JLabel bet;
    private JLabel result;

    public SummaryPanel(GameEngine gameEngine) {
        name = new JLabel("Name: House");
        point = new JLabel();
        bet = new JLabel();
        result = new JLabel();
        this.setLayout(new GridLayout(4, 0));
        this.add(name);
        this.add(point);
        this.add(bet);
        this.add(result);
    }

    /* set information */
    public void setText(String text) {
        String[] str = text.split("\n");

        name.setText(str[0]);
        point.setText(str.length == 1 ? "" : str[1]);
        bet.setText(str.length == 1 ? "" : str[2]);
        result.setText(str.length == 1 ? "" : str[3]);
    }
}