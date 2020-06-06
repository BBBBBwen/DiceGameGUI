package controller;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Observer implements PropertyChangeListener
{
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      System.out.printf("\nReceived Response %s from %s\n", evt.getNewValue(), evt.getSource());
   }
}
