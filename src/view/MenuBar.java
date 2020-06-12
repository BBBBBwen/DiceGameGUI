package view;

import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.menu.*;
import model.interfaces.GameEngine;

/**
 * @author bowen
 *
 */
/* the menu bar at the top */
public class MenuBar extends JMenuBar {
    private static final long serialVersionUID = -4857599308100632662L;
    private JMenu optionMenu;
    private GameEngineCallbackGUI frame;
    private GameEngine gameEngine;

    public MenuBar(GameEngineCallbackGUI frame, GameEngine gameEngine,
            JComboBox<String> playerList) {
        this.frame = frame;
        this.gameEngine = gameEngine;
        createMenuBar();
    }

    private void createMenuBar() {
        optionMenu = new JMenu("Option");
        optionMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem addPlayerMenuItem = new JMenuItem("Add Player");
        addPlayerMenuItem.setMnemonic(KeyEvent.VK_A);
        addPlayerMenuItem.setToolTipText("Add Player");
        addPlayerMenuItem.addActionListener(
                new AddPlayerActionListener(frame, gameEngine));

        JMenu removePlayerMenuItem = new JMenu("Remove Player");
        removePlayerMenuItem.setMnemonic(KeyEvent.VK_R);
        removePlayerMenuItem.setToolTipText("Remove Player");
        removePlayerMenuItem.addMouseListener(
                new RemoveMenuMouseListener(frame, gameEngine, removePlayerMenuItem));

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit");
        exitMenuItem.addActionListener(new ExitActionListener());

        optionMenu.add(addPlayerMenuItem);
        optionMenu.add(removePlayerMenuItem);
        optionMenu.add(exitMenuItem);
        this.add(optionMenu);
    }
    
    public JMenu getOptionMenu() {
        return optionMenu;
    }
}
