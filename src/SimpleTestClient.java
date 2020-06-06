import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class SimpleTestClient {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngineImpl();
        EventQueue.invokeLater(() -> {

            GameEngineCallbackGUI ex = new GameEngineCallbackGUI(gameEngine);
            gameEngine.addGameEngineCallback(ex);
            ex.setVisible(true);
        });
    }
}
