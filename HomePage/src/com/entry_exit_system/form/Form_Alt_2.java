package com.entry_exit_system.form;

import com.entry_exit_system.swing.HintTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Form_Alt_2 extends JPanel {

    private String id;
    private JTextField idTextField;
    private JButton EnterButton;

    public Form_Alt_2(String id) {
        this.id = id;
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
        JLabel TitleLabel = new JLabel("Outstation leave");
        TitleLabel.setFont(new Font("sansserif", Font.BOLD, 40));
        TitleLabel.setForeground(new Color(250, 250, 250));
        TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TitleLabel.setBounds(300, 10, 350, 120);
        this.add(TitleLabel);

        JLabel currentTimeLabel = new JLabel();
        currentTimeLabel.setFont(new Font("sansserif", Font.BOLD, 70));
        currentTimeLabel.setForeground(Color.cyan);
        currentTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentTimeLabel.setBounds(235, 150, 500, 120);
        this.add(currentTimeLabel);
        updateTime(currentTimeLabel);

        idTextField = new HintTextField("Reason for leave");
//        idTextField.setFont(new Font("sansserif", Font.PLAIN, 40));
        idTextField.setBounds(110, 330, 500, 55);
        idTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
        idTextField.setBackground(new Color(250, 250, 250));
        idTextField.setOpaque(false);
//        idTextField.setForeground(new Color(20, 50, 110));
        idTextField.setSelectionColor(new Color(220, 204, 182));
//        PromptSupport.setPrompt("ID", idTextField);
        if(StudentHandler.studentInCampus(id)){
            this.add(idTextField);
        }

        // Style Log Out button
        EnterButton = new JButton("Confirm Entry");
        EnterButton.setBounds(320, 330, 350, 75);
        if(StudentHandler.studentInCampus(id)){
            EnterButton.setText("Confirm Exit");
            EnterButton.setBounds(630, 330, 300, 55);
        }
        EnterButton.setFont(new Font("sansserif", Font.PLAIN, 30));
        EnterButton.setForeground(new Color(20, 50, 110));
        EnterButton.setBackground(new Color(10, 215, 255));
        EnterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(EnterButton);

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
