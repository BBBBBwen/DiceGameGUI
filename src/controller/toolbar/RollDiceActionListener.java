package controller.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;

public class RollDiceActionListener implements ActionListener {
    private GameEngine gameEngine;
    private GameEngineCallbackGUI frame;

    public RollDiceActionListener(GameEngine gameEngine, GameEngineCallbackGUI frame) {
        this.frame = frame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread(new Thread() {
            public void run() {
                gameEngine.rollPlayer(frame.getPlayer(), 100,1000,100,50,500,50);
            }
        });
        thread.start();
        frame.setStatus("Player " + frame.getPlayer().getPlayerId() + " is rolling......");
    }

}
