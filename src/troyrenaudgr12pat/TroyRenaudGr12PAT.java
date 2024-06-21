/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package troyrenaudgr12pat;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author troyr
 */
public class TroyRenaudGr12PAT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginPageUI login = new LoginPageUI();
        login.setVisible(true);

        DataHandler dh = new DataHandler();
//        Product p1 = new Product("Kudu Burger", "Hot Meals", "6294701735294", 50, 20, LocalDate.now());
//        dh.insertNewProduct(p1);
//        Product p2 = new Product(4, "Kudu Burger", "Hot Meals", "6294701735294", 50.0, 18, LocalDate.now());
//        dh.updateProduct(p2);

        ArrayList<Product> products = dh.getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }

}
