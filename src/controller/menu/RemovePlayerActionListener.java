package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

/**
 * @author bowen
 *
 */
/* Respond to each Player button under Remove Player menu */
public class RemovePlayerActionListener implements ActionListener {
    private GameEngineCallbackGUI frame;
    private GameEngine gameEngine;
    private Player player;

    public RemovePlayerActionListener(GameEngineCallbackGUI frame,
            GameEngine gameEngine, Player player) {
        this.frame = frame;
        this.gameEngine = gameEngine;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* remove player from model and player list */
        if (!frame.isRolling()) {
            gameEngine.removePlayer(player);
            frame.getPlayerList().removeItem(player.getPlayerId());
        } else {
            JOptionPane.showMessageDialog(frame, "player is rolling");
        }
    }

}
