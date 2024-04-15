package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.swing.ScrollBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.entry_exit_system.jdbc.JDBC.connection;

/**
 * This class represents Form 4 of the entry-exit system.
 */
public class Form_4 extends javax.swing.JPanel {

    // Add JTextField declarations for In Time and Out Time
    private javax.swing.JTextField txtInTime;
    private javax.swing.JTextField txtOutTime;

    /**
     * Creates new form Form_4
     */
    public Form_4() {
        initComponents();
        populateTimeFields();
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

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Initialize JTextField for In Time
        txtInTime = new javax.swing.JTextField("In Time");
        txtInTime.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtInTime.getText().equals("In Time")) {
                    txtInTime.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtInTime.getText().isEmpty()) {
                    txtInTime.setText("In Time");
                }
            }
        });

        // Initialize JTextField for Out Time
        txtOutTime = new javax.swing.JTextField("Out Time");
        txtOutTime.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtOutTime.getText().equals("Out Time")) {
                    txtOutTime.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtOutTime.getText().isEmpty()) {
                    txtOutTime.setText("Out Time");
                }
            }
        });

        JButton addButton = new JButton("Set Time");
        addButton.setFont(new Font("sansserif", Font.PLAIN, 30));
        addButton.setForeground(new Color(20, 50, 110));
        addButton.setBackground(new Color(10, 215, 255));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the new values from the text fields
                String newInTime = txtInTime.getText();
                String newOutTime = txtOutTime.getText();

                try {

                    boolean isValidInput = validateInput(newInTime, newOutTime);

                    if (isValidInput) {
                        JDBC.updateTimeLimits(newInTime, newOutTime);

                        populateTimeFields();

                        showMessageDialog(Form_4.this, "The Time is Set", "Bingo", JOptionPane.INFORMATION_MESSAGE, Color.GREEN);
                    } else {
                        showMessageDialog(Form_4.this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE, Color.RED);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle any SQLException, such as displaying an error message
                }
            }
        });





        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.entry_exit_system.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 45));
        jLabel1.setBounds(300, 10, 350, 120);
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Entry and Exit Time");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(txtInTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtOutTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(addButton)) // Add the button here
                                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(200, 250, 300)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtInTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtOutTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton) // Add the button here
                                .addGap(200, 250, 300))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
        );
    }

    private void populateTimeFields() {
        try {
            // Call the method from JDBC class to retrieve time values
            List<TimeLimits> timeLimitsList = JDBC.runTest(connection);

            // Assuming there's only one record in the result set, get the first item
            if (!timeLimitsList.isEmpty()) {
                TimeLimits timeLimits = timeLimitsList.get(0);

                // Set the retrieved values to the text fields
                txtInTime.setText(timeLimits.getInTime());
                txtOutTime.setText(timeLimits.getOutTime());
            } else {
                // If no records are retrieved, set default values
                txtInTime.setText("In Time");
                txtOutTime.setText("Out Time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showMessageDialog(Component parentComponent, String message, String title, int messageType, Color backgroundColor) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.add(label);

        // Create a custom option pane with the specified background color
        JOptionPane optionPane = new JOptionPane(panel, messageType);
        optionPane.setBackground(backgroundColor);

        // Set background color recursively
        setBackgroundRecursively(optionPane, backgroundColor);

        // Create a custom dialog with the modified option pane
        JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setVisible(true);
    }

    private void setBackgroundRecursively(Container container, Color color) {
        container.setBackground(color);
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                setBackgroundRecursively((Container) component, color);
            }
            component.setBackground(color);
        }
    }

    private boolean validateInput(String inTime, String outTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        try {
            // Attempt to parse the input strings into Date objects
            Date inTimeDate = dateFormat.parse(inTime);
            Date outTimeDate = dateFormat.parse(outTime);

            // If parsing succeeds, the input strings are valid dates
            return true;
        } catch (ParseException e) {
            // If parsing fails, the input strings are not valid dates
            return false;
        } // Change this to your validation result
    }


    // Variables declaration - do not modify
    private javax.swing.JLayeredPane panel;
    private com.entry_exit_system.swing.PanelBorder panelBorder1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}