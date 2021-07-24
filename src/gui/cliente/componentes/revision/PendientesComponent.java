package gui.cliente.componentes.revision;

import datos.Documento;
import datos.Movimiento;
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

public class PendientesComponent implements ActionListener, MouseListener {
    private PendientesTemplete pendientesTemplete;
    private RevisionComponent revisionComponent;
    private DocumentosService sDocumentos;
    private PendientesService sPendientes;

    public PendientesComponent(RevisionComponent revisionComponent){
        this.revisionComponent = revisionComponent;
        this.pendientesTemplete = new PendientesTemplete(this);
        this.sDocumentos = DocumentosService.getServicio();
        this.sPendientes = PendientesService.getServicio();

        mostrarRegistros(sPendientes.getPendientes());
    }

    public PendientesTemplete getPendientesTemplete() {
        return pendientesTemplete;
    }

    public void mostrarRegistros(Iterable<Integer> datos){
        eliminarRegistros();
        for(Integer pendiente: datos)
            agregarRegistro(pendiente);
    }

    public void eliminarRegistros(){
        DefaultTableModel dm = pendientesTemplete.getModelo();
        int c = dm.getRowCount();

        for (int i = c -1 ; i >= 0; i--)
            dm.removeRow(i);
    }

    public void agregarRegistro(Integer id){
        Documento documento = sDocumentos.getDocumento(id);
        if(documento == null){
            sPendientes.quitarPendiente(id);
            return;
        }
        pendientesTemplete.getModelo().addRow(
                new Object[]{
                        documento.getId(),
                        documento.getTipo(),
                        documento.getNombre(),
                        documento.getEstante().concat("-" + documento.getCarpeta()),
                        documento.getIngreso().toString(),
                        documento.getExpiracion().toString()
                }
        );

    }

    public void eliminarRegistro(){
        int fSeleccionada = pendientesTemplete.getTabla().getSelectedRow();
        if(fSeleccionada != -1){
            int id = (Integer) pendientesTemplete.getModelo().getValueAt(fSeleccionada,0);
            MovimientosService.getServicio().registrarMovimiento(id,"Eliminación");

            pendientesTemplete.getModelo().removeRow(fSeleccionada);
            sPendientes.eliminarPendiente(id);
            revisionComponent.actualizarValores();

        }else
            JOptionPane.showMessageDialog(null,"seleccione una fila","Error",JOptionPane.ERROR_MESSAGE);

    }

    public void quitarRegistro(){
        int fSeleccionada = pendientesTemplete.getTabla().getSelectedRow();
        if(fSeleccionada != -1){
            int id = (Integer) pendientesTemplete.getModelo().getValueAt(fSeleccionada,0);
            pendientesTemplete.getModelo().removeRow(fSeleccionada);
            sPendientes.quitarPendiente(id);
            revisionComponent.actualizarValores();

        }else
            JOptionPane.showMessageDialog(null,"seleccione una fila","Error",JOptionPane.ERROR_MESSAGE);

    }
    public void restarurarValores(){
        mostrarRegistros(sPendientes.getPendientes());
    }
    //ACCIONES DEL MOUSE
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pendientesTemplete.getbEliminar())
            eliminarRegistro();
        if(e.getSource() == pendientesTemplete.getbQuitar())
            quitarRegistro();
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
