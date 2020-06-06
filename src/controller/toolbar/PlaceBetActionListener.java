package controller.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class PlaceBetActionListener implements ActionListener {
    private GameEngine gameEngine;
    private GameEngineCallbackGUI frame;

    public PlaceBetActionListener(GameEngine gameEngine, GameEngineCallbackGUI frame) {
        this.frame = frame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBetInformation();
    }

    public void setBetInformation() {
        JPanel panel = new JPanel();
        JTextField playerBet = new JTextField(5);
        panel.add(new JLabel("Enter Bet"));
        panel.add(playerBet);

        int option = JOptionPane.showConfirmDialog(null, panel,
                "Please fill all the fields", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            int bet = 0;
            try {
                bet = Integer.parseInt(playerBet.getText());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            Player player = frame.getPlayer();
            gameEngine.placeBet(player, bet);
            
            String result = String.format("Name: %s\nPoint: %s\nBet: %s\n",
                    player.getPlayerName(), player.getPoints(), player.getBet());
            frame.setSummary(result);
        }
    }

}
