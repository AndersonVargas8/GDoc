package gui.cliente.login;

import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosLogicos.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginComponent implements ActionListener {
    private LoginTemplate loginTemplate;
    private VistaPrincipalComponent vistaPrincipal;
    private UsuarioService sUsuario;

    public LoginComponent(){
        sUsuario = UsuarioService.getServicio();
        this.loginTemplate = new LoginTemplate(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            System.exit(0);
        }

        if(e.getSource() == loginTemplate.getbEntrar()){
            this.enviarDatos();
        }

    }

    public LoginTemplate getLoginTemplate(){
        return this.loginTemplate;
    }

    public void enviarDatos(){
        String nombreUsuario = loginTemplate.gettNombreUsuario().getText();
        String claveUsuario = new String(loginTemplate.gettClaveUsuario().getPassword());

        if(!nombreUsuario.isEmpty() && !claveUsuario.isEmpty()){
            if(sUsuario.verificarDatosUsuario(nombreUsuario,claveUsuario))
                entrar();
            else
                JOptionPane.showMessageDialog(null,"Usuario o contraseña iválidos", "Error", 2);
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese todos los datos", "Error", 2);
        }
    }
    public void entrar(){
        loginTemplate.setVisible(false);
        if(vistaPrincipal == null)
            this.vistaPrincipal = new VistaPrincipalComponent(this);
        else
            this.vistaPrincipal.getVistaPrincipalTemplate().setVisible(true);

    }
}
