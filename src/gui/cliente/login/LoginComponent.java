package gui.cliente.login;

import gui.cliente.vistaPrincipal.VistaPrincipalComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginComponent implements ActionListener {
    private LoginTemplate loginTemplate;
    private VistaPrincipalComponent vistaPrincipal;

    public LoginComponent(){
        this.loginTemplate = new LoginTemplate(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            System.exit(0);
        }

        if(e.getSource() == loginTemplate.getbEntrar()){
            this.mostrarDatos();
            this.entrar();
        }

    }

    public LoginTemplate getLoginTemplate(){
        return this.loginTemplate;
    }

    public void mostrarDatos(){
        String nombre = loginTemplate.gettNombreUsuario().getText();
        String clave = new String(loginTemplate.gettClaveUsuario().getPassword());

        JOptionPane.showMessageDialog(null,nombre + " " + clave);
    }
    public void entrar(){
        loginTemplate.setVisible(false);
        if(vistaPrincipal == null)
            this.vistaPrincipal = new VistaPrincipalComponent(this);
        else
            this.vistaPrincipal.getVistaPrincipalTemplate().setVisible(true);

    }
}
