package gui.cliente.login;

import gui.cliente.componentes.revision.RevisionComponent;
import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosGraficos.RecursosService;
import logica.Archivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginComponent implements ActionListener, MouseListener, WindowListener {
    private LoginTemplate loginTemplate;
    private VistaPrincipalComponent vistaPrincipal;
    private IniciarSesionComponent iniciarSesionComponent;
    private RegistrarComponent registrarComponent;

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
        if(registrarComponent != null)
            registrarComponent.restaurarValores();
    }

    public void mostrarComponentes(String comando){
        loginTemplate.getpContenido().removeAll();

        switch (comando){
            case "Iniciar Sesión":
                loginTemplate.getpContenido().add(
                        iniciarSesionComponent.getIniciarSesionTemplate()
                );
                iniciarSesionComponent.getIniciarSesionTemplate().revalidate();
                break;

            case "NUEVO USUARIO":
                if(registrarComponent == null)
                    this.registrarComponent = new RegistrarComponent(this);
                loginTemplate.getpContenido().add(
                        registrarComponent.getRegistrarTemplate()
                );
                loginTemplate.getbAtras().setVisible(true);
                registrarComponent.getRegistrarTemplate().revalidate();
                break;
        }
        loginTemplate.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            windowClosing(new java.awt.event.WindowEvent(loginTemplate,WindowEvent.WINDOW_CLOSING));
            System.exit(0);
        }

        if(e.getSource() == loginTemplate.getbAtras()){
            loginTemplate.getbAtras().setVisible(false);
            mostrarComponentes("Iniciar Sesión");
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
        if(e.getSource() == loginTemplate.getbAtras()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.white);
            boton.setBackground(RecursosService.getServicio().getColorGris());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == loginTemplate.getbCerrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.black);
            boton.setBackground(null);
        }
        if(e.getSource() == loginTemplate.getbAtras()){
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
        if(vistaPrincipal != null || registrarComponent != null)
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
