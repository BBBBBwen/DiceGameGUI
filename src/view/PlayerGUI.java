package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.dicepanel.DicePanel;
import view.summarypanel.SummaryPanel;

public class PlayerGUI extends JPanel {
    private SummaryPanel summaryPanel;
    private DicePanel dicePanel;
    private GameEngine gameEngine;
    private Player player;

    public PlayerGUI(GameEngine gameEngine, Player player) {
        this.gameEngine = gameEngine;
        this.player = player;
        createGUI();
    }

    private void createGUI() {
        setLayout(new BorderLayout(2, 2));
        summaryPanel = new SummaryPanel(gameEngine, player);

        dicePanel = new DicePanel();
        summaryPanel.setPreferredSize(new Dimension(150, 400));

        this.add(dicePanel, BorderLayout.CENTER);
        this.add(summaryPanel, BorderLayout.EAST);
    }

    public DicePanel getDicePanel() {
        return dicePanel;
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }
    
    public String getPlayer() {
        return player.getPlayerId();
    }
}
