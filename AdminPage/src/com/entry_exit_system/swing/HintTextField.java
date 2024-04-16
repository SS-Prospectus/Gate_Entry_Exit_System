package com.entry_exit_system.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

public class HintTextField extends JTextField implements FocusListener {
    private final String hint;
    private boolean showingHint;
    private final int cornerRadius = 15;

    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
        this.setForeground(Color.GRAY);
        this.setFont(new Font("sansserif", Font.PLAIN, 30));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (showingHint && getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getForeground());
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.drawString(hint, getInsets().left, g.getFontMetrics().getAscent() + getInsets().top);
            g2.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getForeground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
        g2d.dispose();
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            this.setForeground(new Color(20, 50, 110));
            this.setFont(new Font("sansserif", Font.PLAIN, 40));
            this.showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            this.setForeground(Color.GRAY);
            this.setFont(new Font("sansserif", Font.PLAIN, 30));
            this.showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}
