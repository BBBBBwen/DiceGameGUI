package controller.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.PlayerGUI;

/**
 * @author bowen
 *
 */
/* Respond to Place Bet button under toolbox */
public class PlaceBetActionListener implements ActionListener {
    private GameEngine gameEngine;
    private PlayerGUI playerGUI;

    public PlaceBetActionListener(GameEngine gameEngine, PlayerGUI playerGUI) {
        this.playerGUI = playerGUI;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBetWindow();
    }

    /* pop up window */
    public void setBetWindow() {
        /* create new panel and setting for pop up dialog */
        JPanel panel = new JPanel();
        JTextField playerBet = new JTextField(5);
        panel.add(new JLabel("Enter Bet"));
        panel.add(playerBet);

        int option = JOptionPane.showConfirmDialog(null, panel,
                "Please Enter Amount Of Bet", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            /* create new bet based on confirmed information */
            int bet = 0;
            try {
                bet = Integer.parseInt(playerBet.getText());
                /* update summary to corresponded player */
                Player player = playerGUI.getPlayer();
                if (gameEngine.placeBet(player, bet)) {
                    playerGUI.getFrame()
                            .updateSummary(playerGUI.getPlayerInfo());
                    playerGUI.setBet(true);
                } else {
                    JOptionPane.showMessageDialog(playerGUI, "incorrect bet");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(playerGUI,
                        "please enter integer");
            }
        }
    }

}
