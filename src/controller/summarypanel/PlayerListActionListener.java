package controller.summarypanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

/**
 * @author bowen
 *
 */
/*gameEngine is not used but can be used for future extension*/
@SuppressWarnings("unused")
/* Respond to JComboBox */
public class PlayerListActionListener implements ActionListener {
    private GameEngine gameEngine;
    private GameEngineCallbackGUI frame;

    public PlayerListActionListener(GameEngineCallbackGUI frame,
            GameEngine gameEngine) {
        this.frame = frame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* get selected player id */
        String id = (String) frame.getPlayerList().getSelectedItem();
        
        /* switch to selected player panel */
        frame.switchPanel(id);
    }

}
