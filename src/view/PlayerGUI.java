package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author bowen
 *
 */
public class PlayerGUI extends JPanel {
    private static final long serialVersionUID = 6280688219518105963L;
    private DicePanel dicePanel;
    private GameEngine gameEngine;
    private Player player;
    private ToolBar toolBar;
    private GameEngineCallbackGUI frame;
    private boolean isBet;
    private boolean isRolled;
    private int lastPoint;
    private String result;

    /* GUI for players */
    public PlayerGUI(GameEngineCallbackGUI frame, GameEngine gameEngine,
            Player player) {
        this.frame = frame;
        this.gameEngine = gameEngine;
        this.player = player;
        this.isBet = false;
        this.isRolled = false;
        this.result = "";
        lastPoint = 0;
        createGUI();
    }

    private void createGUI() {
        setLayout(new BorderLayout(2, 2));
        toolBar = new ToolBar(this, gameEngine);
        dicePanel = new DicePanel();

        this.add(dicePanel, BorderLayout.CENTER);
        this.add(toolBar, BorderLayout.NORTH);
    }

    /* helper functions */
    public DicePanel getDicePanel() {
        return dicePanel;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isBet() {
        return isBet;
    }

    public void setBet(boolean isBet) {
        this.isBet = isBet;
    }

    public boolean isRolled() {
        return isRolled;
    }

    public void setRolled(boolean isRolled) {
        this.isRolled = isRolled;
    }

    public GameEngineCallbackGUI getFrame() {
        return frame;
    }

    public void setLastPoint(int point) {
        this.lastPoint = point;
    }

    public int getLastPoint() {
        return lastPoint;
    }

    public String getPlayerInfo() {
        return String.format("Name: %s\nPoint: %s\nBet: %s\nResult: %s",
                player.getPlayerName(), player.getPoints(), player.getBet(),
                result);
    }

    public void setResult(String result) {
        this.result = result;
    }
}
