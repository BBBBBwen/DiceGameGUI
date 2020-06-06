package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.toolbar.PlaceBetActionListener;
import controller.toolbar.ResetBetActionListener;
import controller.toolbar.RollDiceActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class ToolBar extends JPanel {
    private GameEngine gameEngine;
    private Player player;
    private GameEngineCallbackGUI frame;

    public ToolBar(GameEngineCallbackGUI frame, GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.frame = frame;
        this.setLayout(new GridLayout(1, 4, 2, 2));
        creatToolBar();
    }

    private void creatToolBar() {
        JButton placeBet = new JButton("place bet");
        JButton resetBet = new JButton("cancel bet");
        JButton roll = new JButton("roll");

        placeBet.addActionListener(
                new PlaceBetActionListener(gameEngine, frame));
        resetBet.addActionListener(
                new ResetBetActionListener(gameEngine, frame));
        roll.addActionListener(new RollDiceActionListener(gameEngine, frame));

        this.add(placeBet);
        this.add(resetBet);
        this.add(roll);
    }
}
