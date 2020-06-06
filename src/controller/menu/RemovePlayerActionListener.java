package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class RemovePlayerActionListener implements ActionListener {
    private Player player;
    private GameEngine gameEngine;
    private JComboBox<String> playerList;

    public RemovePlayerActionListener(GameEngine gameEngine,
            JComboBox<String> playerList, Player player) {
        this.gameEngine = gameEngine;
        this.playerList = playerList;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameEngine.removePlayer(player);
        playerList.removeItem("Player " + player.getPlayerId());
    }

}
