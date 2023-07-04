package source.view;

import javax.swing.*;
import java.awt.*;

public class RoundButton extends JButton {
    public RoundButton(Icon icon) {
        super(icon);
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
}
