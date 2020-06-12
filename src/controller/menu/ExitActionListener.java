package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author bowen
 *
 */
/*Respond to Exit button under option menu*/
public class ExitActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        /*exit program*/
        System.exit(0);
    }
}
