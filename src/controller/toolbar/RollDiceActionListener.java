package controller.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.PlayerGUI;
import view.ToolBar;

/**
 * @author bowen
 *
 */
/* Respond to Roll Dice button under toolbox */
public class RollDiceActionListener implements ActionListener {
    private GameEngine gameEngine;
    private PlayerGUI playerGUI;
    private ToolBar toolBar;

    public RollDiceActionListener(GameEngine gameEngine, PlayerGUI playerGUI,
            ToolBar toolBar) {
        this.playerGUI = playerGUI;
        this.gameEngine = gameEngine;
        this.toolBar = toolBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (conditionCheck()) {
            playerGUI.getFrame().getExecutor().submit(() -> {
                /* Pre-setting before the roll begin */
                toolBar.setEnabled(false);
                playerGUI.getFrame().setIsRolling(true);
                playerGUI.getFrame().setCurrentPlayer(playerGUI);
                playerGUI.getFrame().setStatus(
                        playerGUI.getPlayer().getPlayerId() + " is rolling");

                /* actual rolling */
                gameEngine.rollPlayer(playerGUI.getPlayer(), 100, 1000, 100, 50,
                        500, 50);

                /* after rolled, update lastPoint after all lock down */
                for (Component comp : playerGUI.getFrame().getCurrentView()
                        .getComponents()) {
                    if (comp instanceof PlayerGUI) {
                        PlayerGUI card = (PlayerGUI) comp;
                        card.setLastPoint(card.getPlayer().getPoints());
                    }
                }

                /* update status after rolling */
                playerGUI.setRolled(true);
                playerGUI.getFrame().setIsRolling(false);
                playerGUI.getFrame().setStatus(
                        playerGUI.getPlayer().getPlayerId() + " is done");

                /* if all player has rolled, house roll */
                if (isAllRolled()) {
                    houseRoll();
                }
            });
        }

    }

    private void houseRoll() {
        new Thread() {
            public void run() {
                /* switch to house panel before rolling and update status */
                playerGUI.getFrame().switchPanel("House");
                playerGUI.getFrame().setStatus("House Rolling");

                /* actual rolling */
                gameEngine.rollHouse(100, 1000, 100, 50, 500, 50);
                StringBuilder removedPlayer = new StringBuilder();

                /* reset all player to start condition and updated result */
                for (Component comp : playerGUI.getFrame().getCurrentView()
                        .getComponents()) {
                    if (comp instanceof PlayerGUI) {
                        PlayerGUI card = (PlayerGUI) comp;
                        card.getToolBar().setEnabled(true);
                        card.setRolled(false);
                        card.setBet(false);

                        /* update summary */
                        updateInfo(comp);
                        if (card.getPlayer().getPoints() == 0) {
                            removedPlayer.append(card.getPlayer().getPlayerId());
                            playerGUI.getFrame().removeView(card);
                        }
                    } else {
                        /* update summary */
                        updateInfo(comp);
                    }
                }

                /* Refresh display */
                playerGUI.getFrame().switchPanel("House");
                if (removedPlayer.length() != 0) {
                    playerGUI.getFrame().setStatus(
                            "Game is Done, " + removedPlayer + " are out");
                } else {
                    playerGUI.getFrame().setStatus("Game is Done");
                }
            }
        }.start();
    }

    private String getResult(int lastPoint, int currentPoint) {
        String winLose;
        if (lastPoint > currentPoint)
            winLose = "You Lost " + (lastPoint - currentPoint) + " point";
        else if (lastPoint < currentPoint)
            winLose = "You Won " + (currentPoint - lastPoint) + " point";
        else
            winLose = "it was a tie ";
        return winLose;
    }

    /* helper function for checking if condition match */
    private boolean conditionCheck() {
        if (playerGUI.isRolled()) {
            JOptionPane.showMessageDialog(playerGUI,
                    "you have rolled, please wait other player roll");
            return false;
        } else if (!playerGUI.isBet()) {
            JOptionPane.showMessageDialog(playerGUI, "you need to bet first");
            return false;
        } else if (playerGUI.getFrame().isRolling()) {
            JOptionPane.showMessageDialog(playerGUI,
                    "please wait until other player finished their rolling");
            return false;
        }
        return true;
    }

    /* helper function for update summary */
    private void updateInfo(Component comp) {
        if (comp instanceof PlayerGUI) {
            PlayerGUI player = (PlayerGUI) comp;
            player.setResult(getResult(playerGUI.getLastPoint(),
                    playerGUI.getPlayer().getPoints()));
        }
    }
    
    private boolean isAllRolled() {
        for (Component comp : playerGUI.getFrame().getCurrentView()
                .getComponents()) {
            if (comp instanceof PlayerGUI) {
                PlayerGUI card = (PlayerGUI) comp;
                if(!card.isRolled()) return false;
            }
        }
        return true;
    }
}
