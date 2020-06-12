package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.summarypanel.PlayerListActionListener;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * @author bowen
 *
 */
public class GameEngineCallbackGUI extends JFrame
        implements GameEngineCallback {
    private static final long serialVersionUID = 1L;
    private MenuBar menuBar;
    private GameEngine gameEngine;
    private SummaryPanel summaryPanel;
    private StatusBar statusBar;
    /* help automatically add player id */
    private int playerId = 1;
    private JComboBox<String> playerList;
    private JPanel currentView;
    private PlayerGUI currentPlayer;
    private HouseGUI houseGUI;
    private CardLayout card;
    /* help check if all player has rolled */
    private int houseResult;
    private ExecutorService executor;
    private boolean isRolling;

    public GameEngineCallbackGUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        executor = Executors.newSingleThreadExecutor();
        createGUI();
    }

    /* Create main frame */
    private void createGUI() {
        setTitle("DiceGame");
        this.getContentPane().setLayout(new BorderLayout(2, 2));

        /* initial card layout for hosting player screen */
        card = new CardLayout();

        /* initial panel in main frame */
        playerList = new JComboBox<>();
        menuBar = new MenuBar(this, gameEngine, playerList);
        currentView = new JPanel(card);
        statusBar = new StatusBar();

        /* add house GUI to card layout and list */
        houseGUI = new HouseGUI(this, gameEngine);
        playerList.addItem("House");
        currentView.add(houseGUI, "House");
        summaryPanel = new SummaryPanel(gameEngine);

        playerList.addActionListener(
                new PlayerListActionListener(this, gameEngine));

        /* set preferred location and size */
        this.getContentPane().add(playerList, BorderLayout.NORTH);
        this.getContentPane().add(currentView, BorderLayout.CENTER);
        this.getContentPane().add(summaryPanel, BorderLayout.EAST);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);

        this.setJMenuBar(menuBar);
        this.setVisible(true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        summaryPanel.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight()));
    }

    @Override
    public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
        if (die.getNumber() == 1)
            currentPlayer.getDicePanel().setDie1(die);
        else
            currentPlayer.getDicePanel().setDie2(die);
    }

    @Override
    public void houseDieUpdate(Die die, GameEngine gameEngine) {
        if (die.getNumber() == 1)
            houseGUI.getDicePanel().setDie1(die);
        else
            houseGUI.getDicePanel().setDie2(die);
    }

    @Override
    public void playerResult(Player player, DicePair result,
            GameEngine gameEngine) {
        currentPlayer.getDicePanel().setDie1(result.getDie1());
        currentPlayer.getDicePanel().setDie2(result.getDie2());
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        houseGUI.getDicePanel().setDie1(result.getDie1());
        houseGUI.getDicePanel().setDie2(result.getDie2());
        houseResult = result.getTotal();
    }

    /* helper functions */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }

    public void updateSummary(String text) {
        summaryPanel.setText(text);
    }

    public void setCurrentPlayer(PlayerGUI gui) {
        this.currentPlayer = gui;
    }

    public void setStatus(String text) {
        statusBar.setText(text);
    }

    public JPanel getCurrentView() {
        return currentView;
    }

    public HouseGUI getHouseGUI() {
        return houseGUI;
    }

    public MenuBar getMenu() {
        return menuBar;
    }

    public JComboBox<String> getPlayerList() {
        return playerList;
    }

    /* switch among screen */
    public void switchPanel(String id) {
        card.show(currentView, id);
        if (id.equals("House")) {
            summaryPanel.setText(houseGUI.getHouseInfo());
            playerList.setSelectedItem(houseGUI);
        } else {
            PlayerGUI item = getPlayerPanel(id);
            summaryPanel.setText(item.getPlayerInfo());
            playerList.setSelectedItem(item.getPlayer().getPlayerId());
        }
    }

    public PlayerGUI getPlayerPanel(String id) {
        for (Component comp : currentView.getComponents()) {
            if (comp instanceof PlayerGUI && comp.getName().equals(id)) {
                return ((PlayerGUI) comp);
            }
        }
        return null;
    }

    public int getResult() {
        return houseResult;
    }

    /* entirely remove a player from game */
    public void removeView(PlayerGUI gui) {
        gameEngine.removePlayer(gui.getPlayer());
        playerList.removeItem(gui.getPlayer().getPlayerId());
        currentView.remove(gui);
    }

    public boolean isRolling() {
        return isRolling;
    }

    public void setIsRolling(boolean isRolling) {
        this.isRolling = isRolling;
    }

    public ExecutorService getExecutor() {
        return executor;
    }
}
