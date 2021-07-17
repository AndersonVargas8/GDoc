package gui;

import gui.cliente.login.LoginTemplate;
import gui.vistaPrincipal.VistaPrincipalTemplate;
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
                LoginTemplate login = new LoginTemplate();
                login.getClass();
            }
        };
        SwingUtilities.invokeLater(iniciarAplicacion);
    }
}