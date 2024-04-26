package com.entry_exit_system.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class Form_dev extends JPanel {
    private JTextField idTextField;
    private JButton EnterButton;

    public Form_dev() {
        addComponents();
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Define the gradient colors
        Color color1 = new Color(28, 181, 224); // Color.decode("#1CB5E0") as RGB
        Color color2 = new Color(0, 0, 70); // Light blue

        // Create the gradient
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);

        // Apply the gradient
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void addComponents() {
        // Display title as simple text
        JLabel TitleLabel = new JLabel("Developers");
        TitleLabel.setFont(new Font("sansserif", Font.BOLD, 40));
        TitleLabel.setForeground(new Color(250, 250, 250));
        TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TitleLabel.setBounds(300, 10, 350, 120);
        this.add(TitleLabel);

        // Display current time as simple text

        // Add hyperlinks
        addHyperlink("Suryash", "https://github.com/SS-Prospectus", "/com/entry_exit_system/icon/profile1.png", 300, 100);
        addHyperlink("Shubham", "https://github.com/f20220056", "/com/entry_exit_system/icon/profile2.png", 300, 400);
        addHyperlink("Saket", "https://github.com/saketja", "/com/entry_exit_system/icon/profile3.png", 300, 700);
        addHyperlink("Samarth", "https://github.com/SmrthBits", "/com/entry_exit_system/icon/profile4.png", 550, 275);
        addHyperlink("Nishant", "https://github.com/bitsbyter", "/com/entry_exit_system/icon/profile5.png", 550, 550);

    }

    private void addHyperlink(String text, String url, String imagePath, int y, int x) {
        URL imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(x, y - 160, 150, 150);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(imageLabel);
        } else {
            System.out.println("Image not found: " + imagePath);
        }

        JLabel label = new JLabel(text);
        label.setFont(new Font("sansserif", Font.BOLD, 30));
        label.setForeground(new Color(250, 250, 250));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setBounds(x, y, 150, 35);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Runtime.getRuntime().exec(new String[] {
                            "open","-a","/Applications/Google Chrome.app/Contents/MacOS/Google Chrome",
                            url
                    });
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(label);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();

        setBackground(new Color(242, 242, 242));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(125, 125, 125))
        );
    }// Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
