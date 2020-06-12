package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author bowen
 *
 */
/*Status bar panel at bottom*/
public class StatusBar extends JPanel {
    private static final long serialVersionUID = 4267381055865535188L;
    JLabel label;

    public StatusBar() {
        label = new JLabel("Statues Bar");
        this.add(label);
    }

    public void setText(String text) {
        label.setText(text);
    }
}
