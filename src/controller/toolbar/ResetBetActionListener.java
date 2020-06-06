package controller.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class ResetBetActionListener implements ActionListener {
    private GameEngine gameEngine;
    private GameEngineCallbackGUI frame;

    public ResetBetActionListener(GameEngine gameEngine, GameEngineCallbackGUI frame) {
        this.frame = frame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getPlayer().resetBet();
    }

}
