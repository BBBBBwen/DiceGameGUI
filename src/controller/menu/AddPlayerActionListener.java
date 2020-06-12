package controller.menu;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.PlayerGUI;

/**
 * @author bowen
 *
 */
/* Respond to Add player button under option menu */
public class AddPlayerActionListener implements ActionListener {
    private GameEngine gameEngine;
    private GameEngineCallbackGUI frame;

    public AddPlayerActionListener(GameEngineCallbackGUI frame,
            GameEngine gameEngine) {
        this.frame = frame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* get id for new player */
        int id = frame.getPlayerId();

        /* get player information from popup window */
        Player player = setPlayerInformation("Player " + id);
        if (player != null) {

            /* add player to model */
            gameEngine.addPlayer(player);

            /* create gui for new player and set name to the panel */
            PlayerGUI gui = new PlayerGUI(frame, gameEngine, player);
            gui.setName("Player " + id);

            /* update summary */
            frame.updateSummary(gui.getPlayerInfo());

            /* add new gui to display area under main frame */
            frame.getCurrentView().add(gui, "Player " + id);

            /* add new player to player list */
            frame.getPlayerList().addItem("Player " + id);

            frame.switchPanel(player.getPlayerId());

            /* current id is used, plus one */
            frame.setPlayerId(++id);
        }
    }

    /* pop up window */
    public Player setPlayerInformation(String playerId) {
        /* create new panel and setting for pop up dialog */
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
            /* create new player based on confirmed information */
            String name = playerName.getText();
            int point = 0;
            boolean flag = true;
            try {
                point = Integer.parseInt(playerPoint.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "please enter integer");
                flag = false;
            }
            if (!name.isEmpty() && flag)
                return new SimplePlayer(playerId, name, point);
        }
        return null;
    }

}
