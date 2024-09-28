/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.PrintStream;

/**
 * This class displays kitchen orders in a user interface. It allows the user to
 * view orders and send notifications.
 */
public class KitchenDisplayUI extends javax.swing.JFrame {

    private ArrayList<Tab> tabs;
    private int selected = -1;
    private User currentUser;
    private Customer currentCustomer;
    private static final String ACCOUNT_SID = "AC62c79daf73c41eb63442a5de6ebcd6b6";//change this to your account SID supplied by Twilio
    private static final String AUTH_TOKEN = "1816c4be579701efdc8b4d9e5667d6c1";//change this to your auth token supplied by Twilio
    private static final String SENDER_NUMBER = "+14155238886";//change this to your twilio whatsapp number

    /**
     * Creates new form KitchenDisplayUI. Initializes the UI components and
     * loads the tabs.
     *
     * @param u the current user
     * @param c the current customer
     */
    public KitchenDisplayUI(User u, Customer c) {
        initComponents();
        DataHandler dh = new DataHandler();
        tabs = dh.getAllTabs();
        this.addTable();
        currentUser = u;
        currentCustomer = c;

        // Add key listener for Enter key
        tblOrderDisplay.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int response = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure the order is completed?\nMake sure all the products in the selected tab are complete.",
                            "Confirm Order Completion",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        // Get selected row and tabID
                        int selectedRow = tblOrderDisplay.getSelectedRow();
                        if (selectedRow != -1) {
                            int tabID = (Integer) tblOrderDisplay.getValueAt(selectedRow, 0);
                            ArrayList<Product> products = getProductsForTab(tabID);

                            send(tabID, products);
                            updateReadyField();
                        }
                    }
                }
            }
        });
    }

    /**
     * Gets the list of products for a specific tab.
     *
     * @param tabID the ID of the tab to retrieve products from
     * @return a list of products for the specified tab
     */
    public ArrayList<Product> getProductsForTab(int tabID) {
        for (int i = 0; i < tabs.size(); i++) {
            Tab tab = tabs.get(i);
            if (tab.getTabID() == tabID) {
                return tab.getProducts();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Sends a notification message to the customer about their order.
     *
     * @param tabID the ID of the tab
     * @param products the list of products in the order
     */
    public static void send(int tabID, ArrayList<Product> products) {
        DataHandler dh = new DataHandler();

        // Retrieve the customer's phone number using the tabID
        String recipientNumber = dh.getCustomerPhoneNumberByTabID(tabID);
        if (recipientNumber == null || recipientNumber.isEmpty()) {
            System.out.println("No phone number found for TabID: " + tabID);
            return;  // Exit if the phone number is not found
        }

        // Format the phone number
        if (recipientNumber.startsWith("0")) {
            recipientNumber = "+27" + recipientNumber.substring(1);
        } else if (!recipientNumber.startsWith("+")) {
            // Handle cases where the number doesn't start with "+" (assuming it's not an international number)
            recipientNumber = "+27" + recipientNumber;
        }

        // Retrieve the customer's name using the tabID
        String customerName = dh.getCustomerNameByTabID(tabID);

        String msgText = "Hi " + customerName + ", your order is ready to be collected!\n\n"
                + "Order Number: " + tabID + "\n\n"
                + "Products: \n";

        for (int i = 0; i < products.size(); i++) { // Loop through product
            Product product = products.get(i);
            msgText += "- " + product.getProductName();
            if (product instanceof ProductWithNote) { // Check for notes
                msgText += " (Note: " + ((ProductWithNote) product).getNotes() + ")";
            }
            msgText += "\n";
        }

        //this accounts for the error message that pops up when ever sending to a new number, but it will continue because it works with this error so no need to terminate method
        // Suppress SLF4J warning by temporarily redirecting error output
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(new java.io.OutputStream() {
            public void write(int b) {
                // Do nothing, suppress SLF4J errors
            }
        }));

        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN); // Initialize Twilio
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:" + recipientNumber),
                    new com.twilio.type.PhoneNumber("whatsapp:" + SENDER_NUMBER),
                    msgText
            ).create();
            System.out.println("Message sent to: " + recipientNumber);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            // Restore original error output stream (have to do this because of the PrintStream code that redirects the output)
            System.setErr(originalErr);
        }
    }

    /**
     * Adds all uncollected tabs to the table display. This method retrieves the
     * tabs and updates the table model.
     */
    public void addTable() {
        DataHandler dh = new DataHandler();
        tabs = dh.getAllUncollectedTabsForKitchenDisplay();
        DefaultTableModel dtm = (DefaultTableModel) tblOrderDisplay.getModel();
        dtm.setRowCount(0);
        tblOrderDisplay.setModel(dtm);

        Object[] orderRow = new Object[4];
        for (int i = 0; i < tabs.size(); i++) { // Loop through tabs
            ArrayList<Product> products = tabs.get(i).getProducts();
            for (int j = 0; j < products.size(); j++) {
                orderRow[0] = tabs.get(i).getTabID();
                orderRow[1] = products.get(j).getProductName();
                if (products.get(j) instanceof ProductWithNote) { // Polymorphism // Checks if product has a note
                    orderRow[2] = ((ProductWithNote) products.get(j)).getNotes();
                } else {
                    //if there is no note assigned
                    orderRow[2] = ""; // No notes
                }
                dtm.addRow(orderRow);
            }
        }

        /**
         * Set the selection mode and select the first row
         *
         * this allows the table to have the first row selected at all times
         * therefore the kitchen in charge of making the products and submitting
         * the tabs can easily navigate through the program with the up / down
         * arrow keys.
         */
        tblOrderDisplay.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblOrderDisplay.getRowCount() > 0) {
            tblOrderDisplay.setRowSelectionInterval(0, 0); // Select the first row
            tblOrderDisplay.scrollRectToVisible(tblOrderDisplay.getCellRect(0, 0, true)); // Scroll to make the first row visible
            tblOrderDisplay.requestFocusInWindow(); // Set focus to the table 
        }

        selected = tblOrderDisplay.getSelectedRow(); //update the selected row
    }

    /**
     * Updates the ready status for the selected tab. This method marks the tab
     * as ready and refreshes the display.
     */
    private void updateReadyField() {
        DataHandler dh = new DataHandler();
        int row = tblOrderDisplay.getSelectedRow();

        if (row != -1) {
            int tabID = (Integer) tblOrderDisplay.getValueAt(row, 0);

            dh.updateTabReadyStatus(tabID, true);

            // Refresh the table after updating
            tabs = dh.getAllUncollectedTabsForKitchenDisplay(); // Reload uncollected tabs
            addTable();
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
        tblOrderDisplay = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        kdHelpBtn = new javax.swing.JButton();
        kdImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1315, 835));
        setResizable(false);
        getContentPane().setLayout(null);

        tblOrderDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblOrderDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Products", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblOrderDisplay);
        if (tblOrderDisplay.getColumnModel().getColumnCount() > 0) {
            tblOrderDisplay.getColumnModel().getColumn(0).setResizable(false);
            tblOrderDisplay.getColumnModel().getColumn(1).setResizable(false);
            tblOrderDisplay.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(140, 130, 1000, 550);

        backBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn);
        backBtn.setBounds(20, 20, 70, 30);

        kdHelpBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        kdHelpBtn.setText("Help");
        kdHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdHelpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(kdHelpBtn);
        kdHelpBtn.setBounds(1220, 20, 60, 30);

        kdImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\troyr\\Dropbox\\School Work\\IT\\TroyRenaudGr12PAT\\Troy_Gr_12_PAT_Images\\alexander-grey-62vi3TG5EDg-unsplash.jpg")); // NOI18N
        kdImage.setMaximumSize(new java.awt.Dimension(1300, 800));
        kdImage.setMinimumSize(new java.awt.Dimension(1315, 835));
        kdImage.setPreferredSize(new java.awt.Dimension(1300, 800));
        getContentPane().add(kdImage);
        kdImage.setBounds(-1300, -340, 2610, 1650);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action when the "Back" button is clicked. This method opens
     * the CustomerSelectionUI and closes the current window.
     *
     * @param evt the event triggered by the button click
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * Displays a help message dialog to guide users on how to use the Kitchen
     * Display UI.
     *
     * Additionally, when the kdHelpBtn is clicked the program will
     * automatically select the first row once again.
     *
     * @param evt action event triggered by clicking the help button
     */
    private void kdHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdHelpBtnActionPerformed
        String helpMessage = "1. This interface displays kitchen orders for preparation.\n\n"
                + "2. The table displays the following information:\n"
                + "   - Order Number: The unique identifier for each customer order.\n"
                + "   - Products: The products that are part of the order.\n"
                + "   - Notes: Any special instructions associated with the products.\n\n"
                + "3. Marking Orders as Ready:\n"
                + "   - Select an order from the table using your mouse or arrow keys.\n"
                + "   - Press the 'Enter' key or double-click to mark the order as complete.\n"
                + "   - A confirmation dialog will appear. Click 'Yes' to confirm.\n\n"
                + "4. Sending Notifications:\n"
                + "   - Once an order is marked as complete, a WhatsApp message will be sent "
                + "to the customer informing them that their order is ready for collection.\n\n"
                + "5. Navigation:\n"
                + "   - Use the 'Back' button to return to the main Customer Selection screen.\n\n"
                + "If you need further assistance, please contact support.";

        JOptionPane.showMessageDialog(null, helpMessage, "Kitchen Display Help", JOptionPane.INFORMATION_MESSAGE);

        /**
         * Set the selection mode and select the first row
         *
         * this allows the table to have the first row selected at all times
         * therefore the kitchen in charge of making the products and submitting
         * the tabs can easily navigate through the program with the up / down
         * arrow keys.
         */
        tblOrderDisplay.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblOrderDisplay.getRowCount() > 0) {
            tblOrderDisplay.setRowSelectionInterval(0, 0); // Select the first row
            tblOrderDisplay.scrollRectToVisible(tblOrderDisplay.getCellRect(0, 0, true)); // Scroll to make the first row visible
            tblOrderDisplay.requestFocusInWindow(); // Set focus to the table 
        }
    }//GEN-LAST:event_kdHelpBtnActionPerformed

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
            java.util.logging.Logger.getLogger(KitchenDisplayUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KitchenDisplayUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KitchenDisplayUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KitchenDisplayUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new KitchenDisplayUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kdHelpBtn;
    private javax.swing.JLabel kdImage;
    private javax.swing.JTable tblOrderDisplay;
    // End of variables declaration//GEN-END:variables
}
