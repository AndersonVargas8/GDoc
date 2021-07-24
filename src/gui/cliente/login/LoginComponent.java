package gui.cliente.login;

import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.UsuarioService;
import logica.Archivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginComponent implements ActionListener, MouseListener,WindowListener{
    private LoginTemplate loginTemplate;
    private VistaPrincipalComponent vistaPrincipal;
    private UsuarioService sUsuario;
    private String[] placeholders = { "Ingrese su nombre de usuario", "Contraseña" };

    public LoginComponent(){
        sUsuario = UsuarioService.getServicio();
        this.loginTemplate = new LoginTemplate(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            windowClosing(new java.awt.event.WindowEvent(loginTemplate,WindowEvent.WINDOW_CLOSING));
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

        //----------------------------------------
       //nombreUsuario = "Anderson";
        //claveUsuario = "1234";
        // ----------------------
        if(!nombreUsuario.isEmpty() && !claveUsuario.isEmpty()){
            if(sUsuario.verificarDatosUsuario(nombreUsuario,claveUsuario))
                entrar();
            else
                JOptionPane.showMessageDialog(null,"Usuario o contraseña inválidos", "Error", 2);
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese todos los datos", "Error", 2);
        }
    }
    public void entrar(){
        loginTemplate.setVisible(false);
        if(vistaPrincipal == null)
            this.vistaPrincipal = new VistaPrincipalComponent(this);
        else
            this.vistaPrincipal.restaurarValores();
            this.vistaPrincipal.getVistaPrincipalTemplate().setVisible(true);

    }

    public void restaurarValores(){
        this.getLoginTemplate().gettNombreUsuario().setText("Ingrese su nombre de usuario");
        this.getLoginTemplate().gettClaveUsuario().setText("Contraseña");
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
        if(e.getSource() == loginTemplate.getbEntrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(new Color(6, 120, 172));
        }
        if(e.getSource() == loginTemplate.getbCerrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.white);
            boton.setBackground(new Color(252, 34, 34));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == loginTemplate.getbEntrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(new Color(0,134,190));
        }
        if(e.getSource() == loginTemplate.getbCerrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.black);
            boton.setBackground(null);
        }
    }



    //MÉTODOS DE WINDOWSlISTENER
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        if(vistaPrincipal != null)
            Archivo.guardarDatos();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
