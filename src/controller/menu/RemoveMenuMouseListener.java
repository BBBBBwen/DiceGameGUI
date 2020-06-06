package controller.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class RemoveMenuMouseListener implements MouseListener {
    private GameEngine gameEngine;
    private JMenu menu;
    private JComboBox<String> playerList;

    public RemoveMenuMouseListener(GameEngine gameEngine,
            JComboBox<String> playerList, JMenu menu) {
        this.gameEngine = gameEngine;
        this.playerList = playerList;
        this.menu = menu;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        menu.removeAll();
        if (gameEngine.getAllPlayers().size() != 0) {
            for (Player player : gameEngine.getAllPlayers()) {
                JMenuItem item = new JMenuItem(player.getPlayerName());
                menu.add(item);
                item.addActionListener(new RemovePlayerActionListener(
                        gameEngine, playerList, player));
            }
        } else {
            JMenuItem item = new JMenuItem("No Player Added");
            item.setEnabled(false);
            menu.add(item);
        }
    }

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
