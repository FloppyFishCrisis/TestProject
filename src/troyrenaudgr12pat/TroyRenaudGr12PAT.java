/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package troyrenaudgr12pat;

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
        DataHandler dh = new DataHandler();
        ArrayList<Products> products = dh.getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }
    
}
