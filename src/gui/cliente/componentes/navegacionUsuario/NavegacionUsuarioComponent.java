package gui.cliente.componentes.navegacionUsuario;

import datos.Usuario;
import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NavegacionUsuarioComponent implements ActionListener, MouseListener {
    private NavegacionUsuarioTemplate navegacionUsuarioTemplate;
    private VistaPrincipalComponent vistaPrincipalComponent;

    //Servicios
    private UsuarioService sUsuario;

    private Usuario usuarioConectado;

    public NavegacionUsuarioComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.sUsuario = UsuarioService.getServicio();
        this.usuarioConectado = sUsuario.getUsuarioConectado();

        this.navegacionUsuarioTemplate = new NavegacionUsuarioTemplate(this);
        this.vistaPrincipalComponent = vistaPrincipalComponent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand().trim());
    }

    public NavegacionUsuarioTemplate getNavegacionUsuarioTemplate() {
        return navegacionUsuarioTemplate;
    }

    public Usuario getUsuarioConectado() {
        return usuarioConectado;
    }

    public void actualizarValores(){
        this.usuarioConectado = sUsuario.getUsuarioConectado();
        this.navegacionUsuarioTemplate.getpSuperior().removeAll();
        this.navegacionUsuarioTemplate.getpMedio().removeAll();
        this.navegacionUsuarioTemplate.getpSuperior().add(navegacionUsuarioTemplate.getpMedio());
        this.navegacionUsuarioTemplate.crearJLabels();
        this.navegacionUsuarioTemplate.repaint();
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
        if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(RecursosService.getServicio().getColorPrincipalOscuro());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(RecursosService.getServicio().getColorPrincipal());
        }
    }
}
