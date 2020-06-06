package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import controller.summarypanel.PlayerListActionListener;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;
import view.menu.MenuBar;

public class GameEngineCallbackGUI extends JFrame
        implements GameEngineCallback {
    private MenuBar menuBar;
    private GameEngine gameEngine;
    private int playerId = 0;
    private ToolBar toolBar;
    private Player player;
    private StatusBar statusBar;
    private JComboBox<String> playerList;
    private HashMap<String, PlayerGUI> playerViewList;
    private PlayerGUI currentView;

    public GameEngineCallbackGUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        playerViewList = new HashMap<>();
        createGUI();
    }

    private void createGUI() {
        this.setVisible(true);
        setTitle("DiceGame");
        setLayout(new BorderLayout(2, 2));

        playerList = new JComboBox<>();
        toolBar = new ToolBar(this, gameEngine);
        statusBar = new StatusBar();
        menuBar = new MenuBar(this, gameEngine, playerList);
        currentView = new PlayerGUI(gameEngine, player);

        playerList.addActionListener(
                new PlayerListActionListener(this, gameEngine, playerList));

        setJMenuBar(menuBar);
        toolBar.setPreferredSize(new Dimension(this.getWidth(), 30));
        statusBar.setPreferredSize(new Dimension(toolBar.getWidth(), 30));

        toolBar.add(playerList);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(statusBar, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
        if (die.getNumber() == 1)
            currentView.getDicePanel().setDie1(die);
        else
            currentView.getDicePanel().setDie2(die);
    }

    @Override
    public void houseDieUpdate(Die die, GameEngine gameEngine) {
        if (die.getNumber() == 1)
            currentView.getDicePanel().setDie1(die);
        else
            currentView.getDicePanel().setDie2(die);
    }

    @Override
    public void playerResult(Player player, DicePair result,
            GameEngine gameEngine) {
        currentView.getDicePanel().setDie1(result.getDie1());
        currentView.getDicePanel().setDie2(result.getDie2());
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        currentView.getDicePanel().setDie1(result.getDie1());
        currentView.getDicePanel().setDie2(result.getDie2());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void addPlayerGUI(String playerId, PlayerGUI player) {
        playerViewList.put(playerId, player);
    }

    public PlayerGUI getGUI(String id) {
        return playerViewList.get(id);
    }

    public void setCurrentView(PlayerGUI view) {
        this.currentView = view;
    }

    public PlayerGUI getCurrentView() {
        return currentView;
    }

    public void setSummary(String text) {
        currentView.getSummaryPanel().setText(text);
    }

    public void setStatus(String text) {
        statusBar.setText(text);
    }
}
