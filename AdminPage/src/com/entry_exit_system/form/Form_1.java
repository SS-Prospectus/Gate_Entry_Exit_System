/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entry_exit_system.form;

import com.entry_exit_system.model.OutstationRecordModel;
import com.entry_exit_system.swing.ScrollBar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class  Form_1 extends javax.swing.JPanel {
    ArrayList<OutstationRecordModel> outstationRecords;

    public Form_1() {

        initComponents();
//        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/entry_exit_system/icon/stock.png")), "Stock Total", "$200000", "Increased by 60%"));
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/entry_exit_system/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/entry_exit_system/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        //  add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        outstationRecords.forEach((outstationRecord)->{table.addRow(new Object[]{outstationRecord.id, outstationRecord.name, outstationRecord.reason, outstationRecord.outDate, outstationRecord.inDate, outstationRecord.destination} );});
        final JTextField searchField = new JTextField("Search by Student_ID");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search by Student_ID")) {
                    searchField.setText("");
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
                if (searchText.isEmpty() || searchText.equals("Search by Student_ID")) {
                    for (OutstationRecordModel leave : outstationRecords) {
                        model.addRow(new Object[]{leave.id, leave.name,leave.reason, leave.outDate, leave.inDate,leave.destination});
                    }
                } else {
                    // Search for matching Student_ID
                    for (OutstationRecordModel leave : outstationRecords) {
                        if (leave.id.toLowerCase().contains(searchText.toLowerCase())) {
                            model.addRow(new Object[]{leave.id, leave.name,leave.reason, leave.outDate, leave.inDate,leave.destination});
                        }
                    }
                }
            }
        });

// Add the search component to the panel
        panel.add(searchField);

// Create clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("Search by Student_ID"); // Clear the search field
            }
        });

// Add the clear button to the panel
        panel.add(clearButton);

    }

    // Add JTextField declarations
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtReason;
    private javax.swing.JTextField txtOutDate;
    private javax.swing.JTextField txtInDate;
    private javax.swing.JTextField txtDestination;



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void initComponents() {
        outstationRecords = OutstationRecordsHandler.getOutstationRecords();

        // Inside initComponents() method, after spTable.setViewportView(table):
        txtName = new javax.swing.JTextField("Name");
        txtID = new javax.swing.JTextField("ID");
        txtReason = new javax.swing.JTextField("Reason");
        txtOutDate = new javax.swing.JTextField("Out Date");
        txtInDate = new javax.swing.JTextField("In Date");
        txtDestination = new javax.swing.JTextField("Destination");

//        JButton addButton = new JButton("Add Data");
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Reset the text fields to their original values
//                txtName.setText("Name");
//                txtID.setText("ID");
//                txtReason.setText("Reason");
//                txtOutDate.setText("Out Date");
//                txtInDate.setText("In Date");
//
//            }
//        });

        panel = new javax.swing.JLayeredPane();
        card1 = new com.entry_exit_system.component.Card();
        card2 = new com.entry_exit_system.component.Card();
        card3 = new com.entry_exit_system.component.Card();
        panelBorder1 = new com.entry_exit_system.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.entry_exit_system.swing.Table();

        setBackground(new java.awt.Color(242, 242, 242));

        panel.setLayout(new java.awt.GridLayout(1, 0, 0, 0));

//        card1.setColor1(new java.awt.Color(142, 142, 250));
//        card1.setColor2(new java.awt.Color(123, 123, 245));
//        panel.add(card1);
//
//        card2.setColor1(new java.awt.Color(186, 123, 247));
//        card2.setColor2(new java.awt.Color(167, 94, 236));
//        panel.add(card2);
//
//        card3.setColor1(new java.awt.Color(241, 208, 62));
//        card3.setColor2(new java.awt.Color(211, 184, 61));
//        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("List of Outstation Records");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Name", "Reason", "Out Date", "In Date", "Destination"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
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

    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.entry_exit_system.component.Card card1;
    private com.entry_exit_system.component.Card card2;
    private com.entry_exit_system.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.entry_exit_system.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.entry_exit_system.swing.Table table;
}
