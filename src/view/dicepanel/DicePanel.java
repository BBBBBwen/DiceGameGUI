package view.dicepanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.DicePairImpl;
import model.interfaces.DicePair;
import model.interfaces.Die;

public class DicePanel extends JPanel {
    private DicePair dicePair;
    private DieView die1;
    private DieView die2;

    public DicePanel() {
        dicePair = new DicePairImpl();
        die1 = new DieView(dicePair.getDie1().getValue());
        die2 = new DieView(dicePair.getDie2().getValue());
        this.setLayout(new GridLayout(0, 2));
        drawDice();
    }

    public void drawDice() {
        this.add(die1);
        this.add(die2);
    }

    public void setDie1(Die die) {
        die1.setPoint(die.getValue());
        die1.repaint();
    }

    public void setDie2(Die die) {
        die2.setPoint(die.getValue());
        die2.repaint();
    }
}
