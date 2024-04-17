package com.entry_exit_system.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text, SwingConstants.CENTER); // Set horizontal alignment to left
        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(new Font("sansserif", Font.BOLD, 12)); // Use Font.BOLD for better visibility
        setForeground(new Color(102, 102, 102));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(230, 230, 230));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = calculatePreferredWidth(); // Calculate preferred width for each column
        return size;
    }

    private int calculatePreferredWidth() {
        FontMetrics metrics = getFontMetrics(getFont());
        int width = metrics.stringWidth(getText()) + 20; // Add padding
        return width;
    }
}
