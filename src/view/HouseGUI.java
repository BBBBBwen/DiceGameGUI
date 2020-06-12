package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

/**
 * @author bowen
 *
 */
/* gameEngine is not used but can be used for future extension */
@SuppressWarnings("unused")
/* GUI for house dice */
public class HouseGUI extends JPanel {
    private static final long serialVersionUID = 6045090394930154705L;
    private DicePanel dicePanel;
    private GameEngine gameEngine;
    private GameEngineCallbackGUI frame;
    private String result;

    public HouseGUI(GameEngineCallbackGUI frame, GameEngine gameEngine) {
        this.frame = frame;
        this.gameEngine = gameEngine;
        this.result = "";
        createGUI();
    }

    private void createGUI() {
        setLayout(new BorderLayout(2, 2));

        dicePanel = new DicePanel();

        this.add(dicePanel, BorderLayout.CENTER);
    }

    public DicePanel getDicePanel() {
        return dicePanel;
    }

    public GameEngineCallbackGUI getFrame() {
        return frame;
    }

    public String getHouseInfo() {
        return String.format("Name: %s", "House");
    }

    public void setResult(String result) {
        this.result = result;
    }
}
