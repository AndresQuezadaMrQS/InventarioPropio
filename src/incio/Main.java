/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incio;

import controlador.ControladorVendedor;
import vista.FormLogin;

/**
 *
 * @author LATITUDE E6420
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormLogin login = new FormLogin();
        ControladorVendedor cven = new ControladorVendedor(login);
        
        login.setVisible(true);
    }

}
