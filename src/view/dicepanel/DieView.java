package view.dicepanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DieView extends JPanel {
    int point;

    public DieView(int point) {
        this.point = point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        int size = Math.min(getWidth(), getHeight()) / 2;
        int dotSize = size / 10;
        int marginLeft = size / 5;
        int marginTop = getHeight() / 3;
        Graphics2D g2d = (Graphics2D) graphic;

        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(marginLeft, marginTop, size, size, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(marginLeft, marginTop, size, size, 20, 20);

        g2d.setColor(Color.RED);

        int centerX = marginLeft + size / 2 - dotSize / 2;
        int centerY = marginTop + size / 2 - dotSize / 2;
        int left = marginLeft + size * 1 / 4 - dotSize / 2;
        int right = marginLeft + size * 3 / 4 - dotSize / 2;
        int up = marginTop + size * 1 / 4 - dotSize / 2;
        int down = marginTop + size * 3 / 4 - dotSize / 2;

        if(point % 2 != 0) {
            g2d.fillOval(centerX, centerY, dotSize, dotSize);
        }
        if(point != 1) {
            g2d.fillOval(left, down, dotSize, dotSize);
            g2d.fillOval(right, up, dotSize, dotSize);
        }
        if(point > 3) {
            g2d.fillOval(left, up, dotSize, dotSize);
            g2d.fillOval(right, down, dotSize, dotSize);
        }
        if(point == 6) {
            g2d.fillOval(left, centerY, dotSize, dotSize);
            g2d.fillOval(right, centerY, dotSize, dotSize);
        }
    }
}
