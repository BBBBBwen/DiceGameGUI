package controller.summarypanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.PlayerGUI;

public class PlayerListActionListener implements ActionListener {
    private JComboBox playerList;
    private GameEngine gameEngine;
    private GameEngineCallbackGUI frame;

    public PlayerListActionListener(GameEngineCallbackGUI frame,
            GameEngine gameEngine, JComboBox playerList) {
        this.frame = frame;
        this.playerList = playerList;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = ((String) playerList.getSelectedItem()).substring(7);
        frame.remove(frame.getCurrentView());
        PlayerGUI gui = frame.getGUI(id);
        Player player = gameEngine.getPlayer(id);
        String result = String.format("Name: %s\nPoint: %s\nBet: %s\n",
                player.getPlayerName(), player.getPoints(), player.getBet());
        frame.setPlayer(player);
        frame.setCurrentView(gui);
        frame.add(frame.getCurrentView(), BorderLayout.CENTER);
        frame.setSummary(result);
    }

}
