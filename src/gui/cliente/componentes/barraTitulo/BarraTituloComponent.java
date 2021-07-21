package gui.cliente.componentes.barraTitulo;

import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BarraTituloComponent implements ActionListener, MouseListener {

    private BarraTituloTemplate barraTituloTemplate;
    private VistaPrincipalComponent vistaPrincipalComponent;

    public BarraTituloComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.barraTituloTemplate = new BarraTituloTemplate(this);
        this.vistaPrincipalComponent = vistaPrincipalComponent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == barraTituloTemplate.getbMinimizar())
            vistaPrincipalComponent.minimizar();
        if(e.getSource() == barraTituloTemplate.getbCerrar())
            vistaPrincipalComponent.cerrar();
    }

    public BarraTituloTemplate getBarraTituloTemplate() {
        return barraTituloTemplate;
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
        if(e.getSource() == barraTituloTemplate.getbCerrar()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.white);
            boton.setBackground(new Color(252, 34, 34));
        }
        if(e.getSource() == barraTituloTemplate.getbMinimizar()){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.white);
            boton.setBackground(RecursosService.getServicio().getColorGris());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            boton.setForeground(Color.black);
            boton.setBackground(null);
        }

    }
}
