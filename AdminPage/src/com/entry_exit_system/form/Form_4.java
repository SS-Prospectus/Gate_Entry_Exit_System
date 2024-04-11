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
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Reset the text fields to their original values
//                txtInTime.setText("In Time");
//                txtOutTime.setText("Out Time");
//
//                // Repopulate the time fields with the first value from the SQL database
//                populateTimeFields();
//            }
//        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the new values from the text fields
                String newInTime = txtInTime.getText();
                String newOutTime = txtOutTime.getText();

                try {
                    // Update the SQL database with the new values
                    JDBC.updateTimeLimits(newInTime, newOutTime);

                    // Update the text fields with the new values from the database
                    populateTimeFields();
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

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18));
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
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtInTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtOutTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton) // Add the button here
                                .addGap(20, 20, 20))
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


    // Variables declaration - do not modify
    private javax.swing.JLayeredPane panel;
    private com.entry_exit_system.swing.PanelBorder panelBorder1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}