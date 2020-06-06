package view.summarypanel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SummaryPanel extends JPanel {
    private JLabel name;
    private JLabel point;
    private JLabel bet;
    private JLabel result;

    public SummaryPanel(GameEngine gameEngine, Player player) {
        name = new JLabel("Name: ");
        point = new JLabel("Point: ");
        bet = new JLabel("Bet: ");
        result = new JLabel("Result: ");
        this.setLayout(new GridLayout(4,0));
        this.add(name);
        this.add(point);
        this.add(bet);
        this.add(result);
    }

    public void setText(String text) {
        String[] str = text.split("\n");
        name.setText(str[0]);
        point.setText(str[1]);
        bet.setText(str[2]);
        if(str.length == 4) result.setText(str[3]);
    }
}