/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * This class represents the Collections User Interface. It shows tabs that can
 * be collected and allows users to interact with them.
 */
public class CollectionsUI extends javax.swing.JFrame {

    private ArrayList<Tab> tabs;
    private int selected = -1;
    private User currentUser;
    private Customer currentCustomer;

    /**
     * Creates new CollectionsUI with a user and customer.
     *
     * @param u the current user
     * @param c the current customer
     */
    public CollectionsUI(User u, Customer c) {
        initComponents();
        DataHandler dh = new DataHandler();
        tabs = dh.getAllTabs();
        this.addTable();
        currentUser = u;
        currentCustomer = c;
    }

    /**
     * Adds data to the table from the data handler.
     */
    public void addTable() {
        DataHandler dh = new DataHandler();
        tabs = dh.getAllUncollectedTabs();
        DefaultTableModel dtm = (DefaultTableModel) tblCollections.getModel();
        dtm.setRowCount(0); // Clear existing rows
        tblCollections.setModel(dtm);
        Object[] collectionRow = new Object[5];
        for (int i = 0; i < tabs.size(); i++) {
            collectionRow[0] = tabs.get(i).getTabID();
            collectionRow[1] = tabs.get(i).getCustomer().getFullname();
            collectionRow[2] = tabs.get(i).getBalance();
            collectionRow[3] = tabs.get(i).isPaid();
            collectionRow[4] = tabs.get(i).isCollected();
            dtm.addRow(collectionRow);
        }

        // Set custom checkbox renderer and editor for the table
        TableColumnModel columnModel = tblCollections.getColumnModel();
        columnModel.getColumn(3).setCellRenderer(new CheckboxRenderer()); // Paid checkbox renderer
        columnModel.getColumn(3).setCellEditor(new CheckboxEditor(tblCollections)); // Paid checkbox editor

        columnModel.getColumn(4).setCellRenderer(new CheckboxRenderer()); // Collected checkbox renderer
        columnModel.getColumn(4).setCellEditor(new CheckboxEditor(tblCollections)); // Collected checkbox editor

        tblCollections.setSelectionMode(0); // Set the selection mode to single selection
        selected = tblCollections.getSelectedRow(); // Update selected index
    }

    // Checkbox Renderer class
    public class CheckboxRenderer extends JCheckBox implements TableCellRenderer {

        public CheckboxRenderer() {
            setHorizontalAlignment(JLabel.CENTER); // Center align checkbox
        }

        /**
         * Renders the checkbox in the table cell.
         *
         * @param table the table
         * @param value the value in the cell
         * @param isSelected whether the cell is selected
         * @param hasFocus whether the cell has focus
         * @param row the row index
         * @param column the column index
         * @return the component to render
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Boolean) {
                setSelected((Boolean) value); // Set checkbox state
            }
            return this;
        }
    }

    public class CheckboxEditor extends DefaultCellEditor {

        private JCheckBox checkBox;
        private JTable table;

        /**
         * Creates a new CheckboxEditor for a table.
         *
         * @param table the table that contains the checkboxes
         */
        public CheckboxEditor(JTable table) {
            super(new JCheckBox()); // Call parent constructor
            this.table = table;
            checkBox = (JCheckBox) getComponent(); // Get the checkbox component
            checkBox.setHorizontalAlignment(JLabel.CENTER); // Center align checkbox
        }

        /**
         * Gets the component for editing the checkbox.
         *
         * @param table the table
         * @param value the value in the cell
         * @param isSelected whether the cell is selected
         * @param row the row index
         * @param column the column index
         * @return the component for editing
         */
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            boolean isPaid = (boolean) table.getValueAt(row, 3); // Paid column
            boolean isCollected = (boolean) table.getValueAt(row, 4); // Collected column

            // Check if we are editing the 'Collected' column
            if (column == 4) {
                // Only enable the 'Collected' checkbox if 'Paid' is true
                checkBox.setEnabled(isPaid);
                if (!isPaid) {
                    checkBox.setSelected(false); // Force unchecked
                }
            }

            // Check if we are editing the 'Paid' column
            if (column == 3) {
                // Disable the 'Paid' checkbox if 'Collected' is true
                if (isCollected) {
                    checkBox.setEnabled(false);
                } else {
                    checkBox.setEnabled(true);
                }
            }

            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value); // Set checkbox state
            }

            return checkBox;
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected(); // Return selected state
        }

        @Override
        public boolean stopCellEditing() {
            // Always allow stopEditing to proceed
            return super.stopCellEditing();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblCollections = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        confirmBtn = new javax.swing.JButton();
        readyForCollectionLbl = new javax.swing.JLabel();
        cHelpBtn = new javax.swing.JButton();
        cImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1315, 835));
        setResizable(false);
        getContentPane().setLayout(null);

        tblCollections.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCollections.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OrderID", "Customer Name", "Total Balance", "Paid", "Collected"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCollections.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCollectionsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCollections);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(160, 170, 984, 464);

        backBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn);
        backBtn.setBounds(20, 20, 70, 30);

        confirmBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirmBtn.setText("Confirm");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });
        getContentPane().add(confirmBtn);
        confirmBtn.setBounds(1169, 733, 110, 50);

        readyForCollectionLbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        readyForCollectionLbl.setText("Ready For Collection");
        getContentPane().add(readyForCollectionLbl);
        readyForCollectionLbl.setBounds(514, 64, 256, 29);

        cHelpBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cHelpBtn.setText("Help");
        cHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cHelpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(cHelpBtn);
        cHelpBtn.setBounds(1220, 20, 60, 30);

        cImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\troyr\\Dropbox\\School Work\\IT\\TroyRenaudGr12PAT\\Troy_Gr_12_PAT_Images\\alexander-grey-62vi3TG5EDg-unsplash.jpg")); // NOI18N
        cImage.setMaximumSize(new java.awt.Dimension(1300, 800));
        cImage.setMinimumSize(new java.awt.Dimension(1315, 835));
        cImage.setPreferredSize(new java.awt.Dimension(1300, 800));
        getContentPane().add(cImage);
        cImage.setBounds(-1300, -340, 2650, 1650);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles mouse clicks on the table in order to select a specific row.
     *
     * @param evt the mouse event
     */
    private void tblCollectionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCollectionsMouseClicked
        selected = tblCollections.getSelectedRow();
    }//GEN-LAST:event_tblCollectionsMouseClicked

    /**
     * Handles the back button action.
     *
     * @param evt the action event
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        ArrayList<Tab> collectedTabs = new ArrayList<>();

        DataHandler dataHandler = new DataHandler();

        for (int i = 0; i < tblCollections.getRowCount(); i++) {
            int tabID = Integer.parseInt(tblCollections.getValueAt(i, 0).toString()); // TabID column
            boolean paid = (boolean) tblCollections.getValueAt(i, 3);  // Paid column
            boolean collected = (boolean) tblCollections.getValueAt(i, 4);  // Collected column

            // Update the tab in the database
            dataHandler.updateTabPaidStatus(tabID, paid); // Update the paid status in the database
            dataHandler.updateTabCollectedStatus(tabID, collected);  // Update the collected status in the database

            if (collected) {
                collectedTabs.add(tabs.get(i)); // Add to collected tabs
            }
        }

        tabs.removeAll(collectedTabs);

        // Navigate back to CustomerSelectionUI after saving all changes
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * Handles the confirm button action.
     *
     * @param evt the action event
     */
    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        // Loop through all rows and save changes
        DataHandler dataHandler = new DataHandler();

        for (int i = 0; i < tblCollections.getRowCount(); i++) {
            int tabID = Integer.parseInt(tblCollections.getValueAt(i, 0).toString()); // TabID column
            boolean paid = (boolean) tblCollections.getValueAt(i, 3); // Paid column
            boolean collected = (boolean) tblCollections.getValueAt(i, 4);  // Collected column

            // Update the tab in the database
            dataHandler.updateTabPaidStatus(tabID, paid); // Update the paid status in the database
            dataHandler.updateTabCollectedStatus(tabID, collected);  // Update the collected status in the database
        }

        // Navigate back to CustomerSelectionUI after saving all changes
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_confirmBtnActionPerformed

    /**
     * Displays a help message dialog to guide users on how to use the
     * Collections UI.
     *
     * @param evt action event triggered by clicking the help button
     */
    private void cHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cHelpBtnActionPerformed
        String helpMessage = "1. This interface allows users to manage collections of customer tabs.\n\n"
                + "2. The table displays the following information:\n"
                + "   - OrderID: The unique identifier for each order.\n"
                + "   - Customer Name: The name of the customer associated with the order.\n"
                + "   - Total Balance: The remaining balance for the order.\n"
                + "   - Paid: Indicates whether the order has been fully paid (checkbox).\n"
                + "   - Collected: Indicates whether the order has been collected by the customer (checkbox).\n\n"
                + "3. Editing Tabs:\n"
                + "   - You can mark an order as 'Paid' or 'Collected' by clicking on the respective checkbox.\n"
                + "   - The 'Collected' checkbox can only be selected if the order is fully paid.\n"
                + "   - Changes will be saved when you click the 'Confirm' button or navigate back.\n\n"
                + "4. Navigation:\n"
                + "   - Use the 'Back' button to return to the previous screen.\n"
                + "   - Make sure all necessary changes are confirmed before navigating away.\n\n"
                + "If you need further assistance, please contact support.";

        JOptionPane.showMessageDialog(null, helpMessage, "Collections UI Help", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_cHelpBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CollectionsUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CollectionsUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CollectionsUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CollectionsUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new CollectionsUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cHelpBtn;
    private javax.swing.JLabel cImage;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel readyForCollectionLbl;
    private javax.swing.JTable tblCollections;
    // End of variables declaration//GEN-END:variables
}
