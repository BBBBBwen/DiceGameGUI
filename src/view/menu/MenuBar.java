package view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.menu.*;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;

public class MenuBar extends JMenuBar {
    private JMenu OptionMenu;
    private GameEngineCallbackGUI frame;
    private GameEngine gameEngine;
    private JComboBox<String> playerList;

    public MenuBar(GameEngineCallbackGUI frame, GameEngine gameEngine,
            JComboBox<String> playerList) {
        this.frame = frame;
        this.gameEngine = gameEngine;
        this.playerList = playerList;
        createMenuBar();
    }

    private void createMenuBar() {
        OptionMenu = new JMenu("Option");
        OptionMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem addPlayerMenuItem = new JMenuItem("Add Player");
        addPlayerMenuItem.setMnemonic(KeyEvent.VK_A);
        addPlayerMenuItem.setToolTipText("Add Player");
        addPlayerMenuItem.addActionListener(
                new AddPlayerActionListener(frame, gameEngine, playerList));

        JMenu removePlayerMenuItem = new JMenu("Remove Player");
        removePlayerMenuItem.setMnemonic(KeyEvent.VK_R);
        removePlayerMenuItem.setToolTipText("Remove Player");
        removePlayerMenuItem.addMouseListener(new RemoveMenuMouseListener(
                gameEngine, playerList, removePlayerMenuItem));

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit");
        exitMenuItem.addActionListener(new ExitActionListener());

        OptionMenu.add(addPlayerMenuItem);
        OptionMenu.add(removePlayerMenuItem);
        OptionMenu.add(exitMenuItem);
        this.add(OptionMenu);
    }
}
