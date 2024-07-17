/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author troyr
 */
public class UserManagementUI extends javax.swing.JFrame {

    private ArrayList<User> users = new ArrayList(); //declare and make space in RAM for products
    private int selected; //Initialise 'selected'
    private User adminUser;
    private Customer currentCustomer;

    /**
     * Creates new form UserManagementUI
     */
    public UserManagementUI(User adminUser, Customer c) {
        initComponents();
        DataHandler dh = new DataHandler();  // Initialise and create an instance of the DataHandler class.
        this.addTable();//initialises the table
        users = dh.getAllUsers(); //initialise the ArrayList
        this.adminUser = adminUser;
        currentCustomer = c;
    }

    private void refreshTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblUsers.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < users.size(); i++) {
            Object[] userRow = new Object[]{
                users.get(i).getUserID(),
                users.get(i).getUsername(),
                users.get(i).getPassword(),
                users.get(i).getRole()
            };
            dtm.addRow(userRow);
        }
        //this code repopulated the previous data with updated data, by setting the old rows to the newly updated rows
    }

    private void tblProductsValueChanged() {
        selected = tblUsers.getSelectedRow();
        if (selected != -1) {
            usernameTF.setText(users.get(selected).getUsername());
            passwordTF.setText("" + users.get(selected).getPassword());
            roleTF.setText("" + users.get(selected).getRole());
        }
        //helps to enter the selected rows/variable names so that it can be editted or deleted as shown in the text fields
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameTF = new javax.swing.JTextField();
        passwordTF = new javax.swing.JTextField();
        AddNewUserBtn = new javax.swing.JButton();
        modifyUsername = new javax.swing.JLabel();
        modifyPassword = new javax.swing.JLabel();
        helpButton1 = new javax.swing.JButton();
        modifyRole = new javax.swing.JLabel();
        deleteButton2 = new javax.swing.JButton();
        editButton2 = new javax.swing.JButton();
        roleTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        usernameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTFActionPerformed(evt);
            }
        });

        AddNewUserBtn.setText("Add User");
        AddNewUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewUserBtnActionPerformed(evt);
            }
        });

        modifyUsername.setText("Username:");

        modifyPassword.setText("Password:");

        helpButton1.setBackground(new java.awt.Color(255, 153, 153));
        helpButton1.setText("Help");
        helpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButton1ActionPerformed(evt);
            }
        });

        modifyRole.setText("Role:");

        deleteButton2.setBackground(new java.awt.Color(153, 153, 255));
        deleteButton2.setText("Delete");
        deleteButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton2ActionPerformed(evt);
            }
        });

        editButton2.setBackground(new java.awt.Color(51, 153, 0));
        editButton2.setText("Edit");
        editButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton2ActionPerformed(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserID", "Username", "Password", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsers);
        if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(0).setResizable(false);
            tblUsers.getColumnModel().getColumn(1).setResizable(false);
            tblUsers.getColumnModel().getColumn(2).setResizable(false);
            tblUsers.getColumnModel().getColumn(3).setResizable(false);
        }

        btnBack.setBackground(new java.awt.Color(204, 204, 255));
        btnBack.setText("←");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(AddNewUserBtn)
                        .addGap(72, 72, 72)
                        .addComponent(deleteButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 818, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(modifyRole)
                                .addGap(9, 9, 9))
                            .addComponent(modifyUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(modifyPassword, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roleTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(helpButton1)
                    .addComponent(btnBack))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyUsername))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modifyPassword)
                            .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modifyRole)
                            .addComponent(roleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton2)
                    .addComponent(deleteButton2)
                    .addComponent(AddNewUserBtn)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTFActionPerformed
        // Handle the action when the "Title" text field is modified.
    }//GEN-LAST:event_usernameTFActionPerformed

    private void AddNewUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewUserBtnActionPerformed
        AdminNewUserUI anu = new AdminNewUserUI(adminUser, currentCustomer);
        anu.setVisible(true);
        this.dispose();
        // Handle the action when the "Add New Product" button is clicked.
        // It opens the AddNewProductUI and closes the current window.
    }//GEN-LAST:event_AddNewUserBtnActionPerformed

    private void helpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButton1ActionPerformed
        JOptionPane.showMessageDialog(null, "- Edit the values in the blocks or select a user you want to remove" + "\n" + "- Then click Edit / Delete to change or delete a product");
        //creates a message box that helps the user with what the page does
    }//GEN-LAST:event_helpButton1ActionPerformed

    private void deleteButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton2ActionPerformed
        if (selected != -1) {
            // Ask for confirmation before deleting the product
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                DataHandler dh = new DataHandler();

                // Delete the selected product from the ArrayList
                User userToDelete = users.get(selected);
                dh.deleteUser(userToDelete);
                users.remove(selected);

                // Delete the selected product from the table's data model
                DefaultTableModel dtm = (DefaultTableModel) tblUsers.getModel();
                dtm.removeRow(selected);

                // Clear the text fields
                usernameTF.setText("");
                passwordTF.setText("");
                roleTF.setText("");

                selected = -1; // Reset the selected index
            }
        } else {
            JOptionPane.showMessageDialog(null, "No user selected for deletion.");
        }
        // Handle the action when the "Delete" button is clicked.
        // It deletes the selected customer from the database and refreshes the UI.
    }//GEN-LAST:event_deleteButton2ActionPerformed

    private void editButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton2ActionPerformed
        int userID = users.get(selected).getUserID();
        String username = usernameTF.getText().trim();
        String password = passwordTF.getText().trim();
        String role = roleTF.getText().trim();
        // This code extracts information entered or selected by the user and assigns it to different variables

        checksForEdit(); // Perform validation checks first

        // If checksForEdit shows an error, it will return early and not execute the following code
        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            return;
        }

        DataHandler dh = new DataHandler();

        try {
            dh.updateUser(new User(userID, username, password, role));
            DefaultTableModel dtm = (DefaultTableModel) tblUsers.getModel();
            dtm.setValueAt(username, selected, 1);
            dtm.setValueAt(password, selected, 2);
            dtm.setValueAt(role, selected, 3);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price or quantity format. Please enter valid numbers.");
        }
        //this code sets/updates the older values so that they are updated immediately after clicking the edit button
        //makes sure price is is the right format otherwise displays message dialog
        //Reference: https://stackoverflow.com/questions/3179136/jtable-how-to-refresh-table-model-after-insert-delete-or-update-the-data (helped with refreshing table data)
    }//GEN-LAST:event_editButton2ActionPerformed

    private void checksForEdit() {
        String username = usernameTF.getText().trim();
        String password = passwordTF.getText().trim();
        String role = roleTF.getText().trim();

        // Check if any field is empty
        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled.");
            return;
        }

        // Validate role (example roles: Admin, User)
        if (!(role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("User"))) {
            JOptionPane.showMessageDialog(null, "Invalid role. Please enter 'Admin' or 'User'.");
            return;
        }
    }

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        selected = tblUsers.getSelectedRow();
        this.tblProductsValueChanged();
        //runs the code: when a row is clicked by the mouse, it will show up in text fields to then edit
    }//GEN-LAST:event_tblUsersMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        AdminDashboardUI adb = new AdminDashboardUI(adminUser, currentCustomer);
        adb.setVisible(true);
        this.dispose();
        // Handle the action when the "Back" button is clicked.
        // It opens the LandingPageUI and closes the current window.
    }//GEN-LAST:event_btnBackActionPerformed

    public void addTable() {
        DataHandler dh = new DataHandler();
        users = dh.getAllUsers();
        DefaultTableModel dtm = (DefaultTableModel) tblUsers.getModel();
        tblUsers.setModel(dtm);
        Object userRow[] = new Object[4];
        for (int i = 0; i < users.size(); i++) {
            userRow[0] = "" + users.get(i).getUserID();
            userRow[1] = "" + users.get(i).getUsername();
            userRow[2] = "" + users.get(i).getPassword();
            userRow[3] = "" + users.get(i).getRole();
            dtm.addRow(userRow);
        }
        tblUsers.setSelectionMode(0);  // Set the selection to the first user.
        selected = tblUsers.getSelectedRow();  // Update the selected index.
    }

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
            java.util.logging.Logger.getLogger(UserManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new UserManagementUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNewUserBtn;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton deleteButton2;
    private javax.swing.JButton editButton2;
    private javax.swing.JButton helpButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel modifyPassword;
    private javax.swing.JLabel modifyRole;
    private javax.swing.JLabel modifyUsername;
    private javax.swing.JTextField passwordTF;
    private javax.swing.JTextField roleTF;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
