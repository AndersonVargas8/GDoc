package gui.cliente.componentes.revision;

import datos.Documento;
import datos.DocumentoPriorizado;
import estructuras.colas.ColaPrioritaria;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.MovimientosService;
import gui.servicios.serviciosLogicos.PendientesService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProximosComponent implements ActionListener, MouseListener {
    ProximosTemplate proximosTemplate;
    RevisionComponent revisionComponent;
    private DocumentosService sDocumentos;
    private PendientesService sPendientes;

    public ProximosComponent(RevisionComponent revisionComponent){
        proximosTemplate = new ProximosTemplate(this);
        this.revisionComponent = revisionComponent;
        this.sDocumentos = DocumentosService.getServicio();
        this.sPendientes = PendientesService.getServicio();

        mostrarRegistros(sPendientes.getAVencer());
    }

    public ProximosTemplate getProximosTemplate(){
        return proximosTemplate;
    }

    public void mostrarRegistros(ColaPrioritaria<DocumentoPriorizado> datos){
        eliminarRegistros();
        while(!datos.estaVacia())
            agregarRegistro(datos.eliminarMinimo());
    }

    public void eliminarRegistros(){
        DefaultTableModel dm = proximosTemplate.getModelo();
        int c = dm.getRowCount();

        for (int i = c -1 ; i >= 0; i--)
            dm.removeRow(i);
    }

    public void agregarRegistro(DocumentoPriorizado doc){
        Documento documento = sDocumentos.getDocumento(doc.getIdDocumento());
        proximosTemplate.getModelo().addRow(
                new Object[]{
                        documento.getId(),
                        documento.getTipo(),
                        documento.getNombre(),
                        documento.getEstante().concat("-" + documento.getCarpeta()),
                        documento.getExpiracion().toString()
                }
        );

    }

    public void enviarAPendientes(){
        int fSeleccionada = proximosTemplate.getTabla().getSelectedRow();
        if(fSeleccionada != -1){
            int id = (Integer) proximosTemplate.getModelo().getValueAt(fSeleccionada,0);
            if(sPendientes.estaEnPendientes(id)){
                JOptionPane.showMessageDialog(null,"Ya está en pendientes","Advertencia",JOptionPane.WARNING_MESSAGE);
                return;
            }

            sPendientes.agregarPendiente(id);
            revisionComponent.actualizarValores();
            JOptionPane.showMessageDialog(null,"Agregado a pendientes","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }else
            JOptionPane.showMessageDialog(null,"seleccione una fila","Error",JOptionPane.ERROR_MESSAGE);

    }
    public void restaurarValores(){
        mostrarRegistros(sPendientes.getAVencer());
    }
    //ACCIONES DEL MOUSE
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == proximosTemplate.getbEnviar())
            enviarAPendientes();
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