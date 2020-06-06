package controller.menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.PlayerGUI;

public class AddPlayerActionListener implements ActionListener {
    private GameEngine gameEngine;
    private JComboBox<String> playerList;
    private GameEngineCallbackGUI frame;

    public AddPlayerActionListener(GameEngineCallbackGUI frame,
            GameEngine gameEngine, JComboBox<String> playerList) {
        this.frame = frame;
        this.gameEngine = gameEngine;
        this.playerList = playerList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = frame.getPlayerId();
        Player player = setPlayerInformation(String.valueOf(id));
        gameEngine.addPlayer(player);
        frame.addPlayerGUI(player.getPlayerId(),
                new PlayerGUI(gameEngine, player));
        playerList.addItem("Player " + id);
        frame.setPlayerId(++id);
    }

    public Player setPlayerInformation(String playerId) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 2, 2));

        JTextField playerName = new JTextField(5);
        JTextField playerPoint = new JTextField(5);

        panel.add(new JLabel("Enter Name"));
        panel.add(playerName);

        panel.add(new JLabel("Enter Point"));
        panel.add(playerPoint);

        int option = JOptionPane.showConfirmDialog(null, panel,
                "Please fill all the fields", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            String name = playerName.getText();
            int point = 0;
            try {
                point = Integer.parseInt(playerPoint.getText());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }

            return new SimplePlayer(playerId, name, point);
        }

        return null;
    }

}
