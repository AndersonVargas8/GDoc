package gui.cliente.login;

import gui.cliente.componentes.revision.RevisionComponent;
import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import logica.Archivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginComponent implements ActionListener, MouseListener, WindowListener {
    private LoginTemplate loginTemplate;
    private VistaPrincipalComponent vistaPrincipal;
    private IniciarSesionComponent iniciarSesionComponent;

    public LoginComponent(){
        loginTemplate = new LoginTemplate(this);

        //Hace que el componente iniciar sesión se muestre de inicio
        this.iniciarSesionComponent = new IniciarSesionComponent(this);
        loginTemplate.getpContenido().add(
                iniciarSesionComponent.getIniciarSesionTemplate()
        );
    }

    public LoginTemplate getLoginTemplate(){
        return this.loginTemplate;
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
        iniciarSesionComponent.restaurarValores();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            windowClosing(new java.awt.event.WindowEvent(loginTemplate,WindowEvent.WINDOW_CLOSING));
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.white);
            boton.setBackground(new Color(252, 34, 34));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.black);
            boton.setBackground(null);
        }

    }

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
