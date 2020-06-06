package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {
    JLabel label;
    public StatusBar() {
        label = new JLabel("Statues Bar");
        this.add(label);
    }

    public void setText(String text) {
        label.setText(text);
    }
}
