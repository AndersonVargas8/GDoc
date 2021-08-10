package gui.cliente.login;

import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosLogicos.UsuarioService;
import logica.Archivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IniciarSesionComponent implements ActionListener, MouseListener{
    private IniciarSesionTemplate iniciarSesionTemplate;
    private LoginComponent loginComponent;
    private UsuarioService sUsuario;
    private String[] placeholders = { "Ingrese su nombre de usuario", "Contraseña" };

    public IniciarSesionComponent(LoginComponent loginComponent){
        sUsuario = UsuarioService.getServicio();
        this.loginComponent = loginComponent;
        this.iniciarSesionTemplate = new IniciarSesionTemplate(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == iniciarSesionTemplate.getbEntrar()){
            this.enviarDatos();
        }
        if(e.getSource() == iniciarSesionTemplate.getbRegistrar()){
            loginComponent.mostrarComponentes(e.getActionCommand());
        }
    }

    public IniciarSesionTemplate getIniciarSesionTemplate(){
        return this.iniciarSesionTemplate;
    }

    public void enviarDatos(){
        String nombreUsuario = iniciarSesionTemplate.gettNombreUsuario().getText();
        String claveUsuario = new String(iniciarSesionTemplate.gettClaveUsuario().getPassword());

        //----------------------------------------
       //nombreUsuario = "Anderson";
        //claveUsuario = "1234";
        // ----------------------
        if(!nombreUsuario.isEmpty() && !claveUsuario.isEmpty() && !nombreUsuario.equals(placeholders[0]) && !claveUsuario.equals(placeholders[1])){
            if(sUsuario.verificarDatosUsuario(nombreUsuario,claveUsuario))
                entrar();
            else
                JOptionPane.showMessageDialog(null,"Usuario o contraseña inválidos", "Error", 2);
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese todos los datos", "Error", 2);
        }
    }
    public void entrar(){
        loginComponent.entrar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField textField = ((JTextField) e.getSource());

            if (textField.getText().equals(placeholders[0]) || textField.getText().equals(placeholders[1]))
                textField.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(new Color(6, 120, 172));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(new Color(0,134,190));
        }
    }

    public void restaurarValores(){
        this.getIniciarSesionTemplate().gettNombreUsuario().setText("Ingrese su nombre de usuario");
        this.getIniciarSesionTemplate().gettClaveUsuario().setText("Contraseña");
    }
}
