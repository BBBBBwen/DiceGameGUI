package controller.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

/**
 * @author bowen
 *
 */
/* Respond to Remove Player button under option menu */
public class RemoveMenuMouseListener implements MouseListener {
    private GameEngineCallbackGUI frame;
    private GameEngine gameEngine;
    private JMenu removePlayerMenu;

    public RemoveMenuMouseListener(GameEngineCallbackGUI frame,
            GameEngine gameEngine, JMenu removePlayerMenu) {
        this.frame = frame;
        this.gameEngine = gameEngine;
        this.removePlayerMenu = removePlayerMenu;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /* clear all player added */
        removePlayerMenu.removeAll();

        /* get all player added or display no player */
        if (gameEngine.getAllPlayers().size() != 0) {
            for (Player player : gameEngine.getAllPlayers()) {
                JMenuItem item = new JMenuItem(player.getPlayerName());
                removePlayerMenu.add(item);
                item.addActionListener(new RemovePlayerActionListener(frame,
                        gameEngine, player));
            }
        } else {
            JMenuItem item = new JMenuItem("No Player Added");
            item.setEnabled(false);
            removePlayerMenu.add(item);
        }
    }

    /* redundant method */

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
