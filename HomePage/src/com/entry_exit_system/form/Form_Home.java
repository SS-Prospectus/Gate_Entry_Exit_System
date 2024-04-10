package com.entry_exit_system.form;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Form_Home extends javax.swing.JPanel {
    private JTextField idTextField;
    private JButton logoutButton;

    public Form_Home() {
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
        JLabel TitleLabel = new JLabel("Local leave");
        TitleLabel.setFont(new Font("sansserif", Font.BOLD, 40));
        TitleLabel.setForeground(new Color(250, 250, 250));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setBounds(300, 10, 350, 120);
        this.add(TitleLabel);

        // Display current time as simple text
        JLabel currentTimeLabel = new JLabel();
        currentTimeLabel.setFont(new Font("sansserif", Font.BOLD, 100));
        currentTimeLabel.setForeground(Color.cyan);
        currentTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentTimeLabel.setBounds(250, 150, 500, 120);
        this.add(currentTimeLabel);
        updateTime(currentTimeLabel);

        // Style ID text field
        idTextField = new JTextField();
        idTextField.setFont(new Font("sansserif", Font.PLAIN, 40));
        idTextField.setBounds(110, 330, 500, 55);
        idTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
        idTextField.setBackground(new Color(250, 250, 250));
        idTextField.setOpaque(false);
        idTextField.setForeground(new Color(20, 50, 110));
        idTextField.setSelectionColor(new Color(220, 204, 182));
//        PromptSupport.setPrompt("ID", idTextField);
        this.add(idTextField);

        // Style Log Out button
        logoutButton = new JButton("ENTER");
        logoutButton.setFont(new Font("sansserif", Font.PLAIN, 30));
        logoutButton.setBounds(630, 330, 250, 55);
        logoutButton.setForeground(new Color(20, 50, 110));
        logoutButton.setBackground(new Color(10, 215, 255));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add logout functionality here
            }
        });
        this.add(logoutButton);
    }


    private void updateTime(JLabel label) {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                label.setText(sdf.format(new Date()));
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {


        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(125, 125, 125))
        );
    }// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
}
