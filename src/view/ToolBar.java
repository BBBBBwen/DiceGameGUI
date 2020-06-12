package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.toolbar.PlaceBetActionListener;
import controller.toolbar.ResetBetActionListener;
import controller.toolbar.RollDiceActionListener;
import model.interfaces.GameEngine;

/**
 * @author bowen
 *
 */
/*ToolBar panel which contains buttons*/
public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 8746224707654638218L;
    private GameEngine gameEngine;
    private PlayerGUI playerGUI;
    private JPanel panel;
    private JButton placeBet;
    private JButton resetBet;
    private JButton roll;

    public ToolBar(PlayerGUI playerGUI, GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.playerGUI = playerGUI;
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 2, 2));
        creatToolBar();
    }

    private void creatToolBar() {
        placeBet = new JButton("place bet");
        resetBet = new JButton("cancel bet");
        roll = new JButton("roll");

        placeBet.addActionListener(
                new PlaceBetActionListener(gameEngine, playerGUI));
        resetBet.addActionListener(
                new ResetBetActionListener(gameEngine, playerGUI));
        roll.addActionListener(
                new RollDiceActionListener(gameEngine, playerGUI, this));

        panel.add(placeBet);
        panel.add(resetBet);
        panel.add(roll);
        this.add(panel);
    }

    /* set all button enable or disables*/
    public void setEnabled(boolean enable) {
        placeBet.setEnabled(enable);
        resetBet.setEnabled(enable);
        roll.setEnabled(enable);
    }
}
