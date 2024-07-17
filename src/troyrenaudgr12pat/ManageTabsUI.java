/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author troyr
 */
public class ManageTabsUI extends javax.swing.JFrame {

    private User currentUser;
    private Customer currentCustomer;
    private DataHandler dh;
    private ArrayList<Tab> tabs;

    /**
     * Creates new form ManageTabsUI
     */
    public ManageTabsUI(User u, Customer c) {
        initComponents();
        currentUser = u;
        currentCustomer = c;
        dh = new DataHandler();
        this.updateTabsTable();
    }
    
    private void updateTabsTable() {
    DefaultTableModel dtm = (DefaultTableModel) tblTabs.getModel();
//        dtm.setRowCount(0); // Clear existing rows

        ArrayList<Tab> tabs = dh.getAllTabs();
        
        Object[] tabRow = new Object[5];
        
        for (Tab tab : tabs) {
            String customerName = tab.getCustomerFullname();
            double balance = tab.getBalance();
            boolean isPaid = tab.isPaid();

            for (Product product : tab.getProducts()) {
                tabRow[0] = tab.getTabID();
                tabRow[1] = customerName;
                tabRow[2] = product.getProductName();
                tabRow[3] = product.getPrice();
                tabRow[4] = isPaid;
                dtm.addRow(tabRow);
            }
        }

//        tblTabs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Set the selection to single selection mode.

        // Adding a checkbox for the "Paid" column
        TableColumnModel columnModel = tblTabs.getColumnModel();
        columnModel.getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Adding an action listener to save changes when the checkbox is toggled
        tblTabs.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 4) {
                boolean paid = (boolean) tblTabs.getValueAt(row, column);
                int tabID = (int) tblTabs.getValueAt(row, 0);
                dh.updateTabPaidStatus(tabID, paid);
            }
        });
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
        tblTabs = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblTabs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Customer Name", "Products", "Total Amount", "Paid"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTabsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTabs);
        if (tblTabs.getColumnModel().getColumnCount() > 0) {
            tblTabs.getColumnModel().getColumn(0).setResizable(false);
            tblTabs.getColumnModel().getColumn(1).setResizable(false);
            tblTabs.getColumnModel().getColumn(2).setResizable(false);
            tblTabs.getColumnModel().getColumn(3).setResizable(false);
            tblTabs.getColumnModel().getColumn(4).setResizable(false);
        }

        backBtn.setText("←");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backBtn)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 999, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(backBtn)
                .addGap(129, 129, 129)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTabsMouseClicked

    }//GEN-LAST:event_tblTabsMouseClicked

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
        // Handle the action when the "Back" button is clicked.
        // It opens the SelectProductsForTabUI and closes the current window.
    }//GEN-LAST:event_backBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ManageTabsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageTabsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageTabsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageTabsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ManageTabsUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTabs;
    // End of variables declaration//GEN-END:variables
}
