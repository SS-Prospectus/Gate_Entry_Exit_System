/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.PenaltyBanModel;
import com.entry_exit_system.swing.ScrollBar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Form_2 extends javax.swing.JPanel {

    public ArrayList<PenaltyBanModel> penalizedLeaveList;
    private Timer autoUpdateTimer;
    public Form_2() {
        initComponents();
        autoUpdateTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Refresh the data and table
                penalizedLeaveList = PenalizedStudentsHandler.getPenalizedStudents();
                refreshTable();
            }
        });
        // Start the timer
        autoUpdateTimer.start();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        penalizedLeaveList.forEach((pendingLeave)->{table.addRow(new Object[]{pendingLeave.name, pendingLeave.id, pendingLeave.date, pendingLeave.reason,pendingLeave.penalty_amount} );});
    }
    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (PenaltyBanModel leave : penalizedLeaveList) {
            model.addRow(new Object[]{leave.name, leave.id, leave.date, leave.reason, leave.penalty_amount});
        }
    }
    // Add JTextField declarations
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtReason;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtPenaltyAmount;
    @SuppressWarnings("unchecked")
    public void initComponents() {
        penalizedLeaveList = PenalizedStudentsHandler.getPenalizedStudents();
        txtID = new javax.swing.JTextField("Student ID");
        txtReason = new javax.swing.JTextField("Reason");
        txtDate = new javax.swing.JTextField();
        LocalDate currentDate = LocalDate.now();
        String dateString = currentDate.toString();
        txtDate.setText(dateString);
        txtPenaltyAmount = new javax.swing.JTextField("Penalty Amount");

        JButton addButton = new JButton("Add Data");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve values from text fields
                String id = txtID.getText();
                String date = txtDate.getText();
                String reason = txtReason.getText();
                String penalty_amount=txtPenaltyAmount.getText();
                // Establish connection to SQL database
                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    String sql = "INSERT INTO Penalties (student_id, date_penalized, reason,total_penalty_amount) VALUES ( ?, ?, ?,?)";
                    pstmt = JDBC.connection.prepareStatement(sql);
                    pstmt.setString(1, id);
                    pstmt.setString(2, date);
                    pstmt.setString(3, reason);
                    pstmt.setString(4, penalty_amount);

                    pstmt.executeUpdate();

                    txtID.setText("Student ID");
                    LocalDate currentDate = LocalDate.now();
                    String dateString = currentDate.toString();
                    txtDate.setText(dateString);
                    txtReason.setText("Reason");
                    txtPenaltyAmount.setText("Penalty Amount");

                    penalizedLeaveList = PenalizedStudentsHandler.getPenalizedStudents();

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    for (PenaltyBanModel leave : penalizedLeaveList) {
                        model.addRow(new Object[]{leave.name, leave.id, leave.date, leave.reason,leave.penalty_amount});
                    }

                    JOptionPane.showMessageDialog(null, "Data added successfully!");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Unable to add data to the database. This may be due to Invalid Input.");

                } finally {
                    try {
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.entry_exit_system.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.entry_exit_system.swing.Table();

        setBackground(new java.awt.Color(242, 242, 242));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("List of Penalitized Students");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Penalty ID", "Student ID", "Date", "Reason","Penalty Amount"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false,false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(spTable))
                                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(spTable)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPenaltyAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPenaltyAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(spTable)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPenaltyAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(addButton)) // Add the button here
                                .addGap(20, 20, 20))
        );

        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPenaltyAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton) // Add the button here
                                .addGap(20, 20, 20))
        );


        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtID.getText().equals("Student ID")) {
                    txtID.setText("");
                }
                if (txtPenaltyAmount.getText().equals("")) {
                    txtPenaltyAmount.setText("Penalty Amount");
                }
                if (txtDate.getText().equals("")) {
                    LocalDate currentDate = LocalDate.now();
                    // Convert LocalDate to String in the format "yyyy-MM-dd"
                    String dateString = currentDate.toString();
                    // Set the date string to the text field
                    txtDate.setText(dateString);
                }
                if (txtReason.getText().equals("")) {
                    txtReason.setText("Reason");
                }

            }
        });

        txtReason.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtReason.getText().equals("Reason")) {
                    txtReason.setText("");
                }
                if (txtDate.getText().equals("")) {
                    LocalDate currentDate = LocalDate.now();
                    // Convert LocalDate to String in the format "yyyy-MM-dd"
                    String dateString = currentDate.toString();
                    // Set the date string to the text field
                    txtDate.setText(dateString);
                }
                if (txtID.getText().equals("")) {
                    txtID.setText("Student ID");
                }
                if (txtPenaltyAmount.getText().equals("")) {
                    txtPenaltyAmount.setText("Penalty Amount");
                }
            }
        });

        txtDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtDate.getText().equals("Date")) {
                    txtDate.setText("");
                }
                if (txtPenaltyAmount.getText().equals("")) {
                    txtPenaltyAmount.setText("Penalty Amount");
                }
                if (txtReason.getText().equals("")) {
                    txtReason.setText("Reason");
                }
                if (txtID.getText().equals("")) {
                    txtID.setText("Student ID");
                }
            }
        });
        txtPenaltyAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtPenaltyAmount.getText().equals("Penalty Amount")) {
                    txtPenaltyAmount.setText("");
                }
                if (txtReason.getText().equals("")) {
                    txtReason.setText("Reason");
                }
                if (txtDate.getText().equals("")) {
                    LocalDate currentDate = LocalDate.now();
                    // Convert LocalDate to String in the format "yyyy-MM-dd"
                    String dateString = currentDate.toString();
                    // Set the date string to the text field
                    txtDate.setText(dateString);
                }
                if (txtID.getText().equals("")) {
                    txtID.setText("Student ID");
                }
            }
        });
        // Inside initComponents() method, after initializing other components:
        // Inside initComponents() method, after initializing other components:
        final JTextField searchField = new JTextField("Search by Student ID");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search by Student ID")) {
                    searchField.setText("");
                }
                if (txtReason.getText().equals("")) {
                    txtReason.setText("Reason");
                }
                if(txtPenaltyAmount.getText().equals("")){
                    txtPenaltyAmount.setText("Penalty Amount");
                }
                if (txtDate.getText().equals("")) {
                    LocalDate currentDate = LocalDate.now();
                    // Convert LocalDate to String in the format "yyyy-MM-dd"
                    String dateString = currentDate.toString();
                    // Set the date string to the text field
                    txtDate.setText(dateString);
                }
                if (txtID.getText().equals("")) {
                    txtID.setText("Student ID");
                }
            }
        });
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used for plain text documents
            }

            private void updateTable() {
                String searchText = searchField.getText();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear existing rows

                // If the search text is empty or equals default text, show full table
                if (searchText.isEmpty() || searchText.equals("Search by Student ID")) {
                    for (PenaltyBanModel leave : penalizedLeaveList) {
                        model.addRow(new Object[]{leave.name, leave.id, leave.date, leave.reason,leave.penalty_amount});
                    }
                } else {
                    // Search for matching Student_ID
                    for (PenaltyBanModel leave : penalizedLeaveList) {
                        if (leave.id.toLowerCase().contains(searchText.toLowerCase())) {
                            model.addRow(new Object[]{leave.name, leave.id, leave.date, leave.reason,leave.penalty_amount});
                        }
                    }
                }
            }
        });

// Add the search component to the panel
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8; // 80% of the width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(searchField, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.2; // 20% of the width


// Create clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("Search by Student ID");
                if (txtReason.getText().equals("")) {
                    txtReason.setText("Reason");
                }
                if(txtPenaltyAmount.getText().equals("")){
                    txtPenaltyAmount.setText("Penalty Amount");
                }
                if (txtDate.getText().equals("")) {
                    LocalDate currentDate = LocalDate.now();
                    // Convert LocalDate to String in the format "yyyy-MM-dd"
                    String dateString = currentDate.toString();
                    // Set the date string to the text field
                    txtDate.setText(dateString);
                }
                if (txtID.getText().equals("")) {
                    txtID.setText("Student ID");
                }
            }
        });

// Add the clear button to the panel
        panel.add(clearButton,gbc);

    }// </editor-fold>//GEN-END:initComponents

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.entry_exit_system.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.entry_exit_system.swing.Table table;
    // End of variables declaration//GEN-END:variables
}