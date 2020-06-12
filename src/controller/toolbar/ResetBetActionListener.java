package controller.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.PlayerGUI;

/**
 * @author bowen
 *
 */
/* gameEngine is not used but can be used for future extension */
@SuppressWarnings("unused")
/* Respond to Reset Bet button under toolbox */
public class ResetBetActionListener implements ActionListener {
    private GameEngine gameEngine;
    private PlayerGUI playerGUI;

    public ResetBetActionListener(GameEngine gameEngine, PlayerGUI playerGUI) {
        this.playerGUI = playerGUI;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* Reset bet and update summary */
        playerGUI.getPlayer().resetBet();
        playerGUI.setBet(false);
        playerGUI.getFrame().switchPanel(playerGUI.getPlayer().getPlayerId());
    }
}
