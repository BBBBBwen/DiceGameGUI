import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class ClientGUI {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngineImpl();
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
        SwingUtilities.invokeLater(new Runnable() {
            GameEngineCallback callbackGUI = new GameEngineCallbackGUI(gameEngine);
            @Override
            public void run() {
                gameEngine.addGameEngineCallback(callbackGUI);
            }
        });
    }
}