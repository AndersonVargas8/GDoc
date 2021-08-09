package gui.cliente.componentes.solicitudes;

import datos.Documento;
import gui.cliente.componentes.movimientos.MovimientosTemplate;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.MovimientosService;
import gui.servicios.serviciosLogicos.SolicitudesService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SolicitudesComponent implements MouseListener, ActionListener {
    private SolicitudesTemplate solicitudesTemplate;
    private SolicitudesService sSolicitudes;
    private DocumentosService sDocumentos;

    //Objetos de apoyo
    Documento documento;
    public SolicitudesComponent(){
        this.solicitudesTemplate = new SolicitudesTemplate(this);
        this.sDocumentos = DocumentosService.getServicio();
        this.sSolicitudes = SolicitudesService.getServicio();
    }

    public void nuevaSolicitud(int id){
        documento = sDocumentos.getDocumento(id);
        String infoDoc = documento.getId() + " - " + documento.getTipo() + " - " + documento.getNombre();
        solicitudesTemplate.gettDocumento().setText(infoDoc);
        solicitudesTemplate.getbSolicitar().setEnabled(true);
        solicitudesTemplate.getCbDependencia().setEnabled(true);
    }

    public void solicitarDocumento(){
        String dependencia = (String) solicitudesTemplate.getCbDependencia().getSelectedItem();


        int tiempo = sSolicitudes.getTiempoSolicitud(documento,dependencia);

        JOptionPane.showMessageDialog(null,"El documento llegará en " + tiempo + " segundos");

    }

    public SolicitudesTemplate getSolicitudesTemplate() {
        return solicitudesTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == solicitudesTemplate.getbSolicitar())
            solicitarDocumento();
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
