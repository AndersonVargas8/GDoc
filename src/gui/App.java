package gui;

import gui.cliente.login.IniciarSesionComponent;
import gui.cliente.login.LoginComponent;

import javax.swing.SwingUtilities;

/**
 * 
 * @author Anderson Vargas
 */
public class App{
    public static void main(String[] args){
        Runnable iniciarAplicacion = new Runnable() {
            @Override
            public void run() {
                LoginComponent login = new LoginComponent();
                login.getClass();
            }
        };
        SwingUtilities.invokeLater(iniciarAplicacion);
    }
}