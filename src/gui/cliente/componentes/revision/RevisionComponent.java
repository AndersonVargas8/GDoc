package gui.cliente.componentes.revision;

import datos.Documento;
import datos.DocumentoPriorizado;
import datos.Fecha;
import datos.Movimiento;
import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RevisionComponent implements ActionListener, MouseListener{
    private RevisionTemplate revisionTemplate;
    private RevisionService sRevision;
    private DocumentosService sDocumentos;
    private VistaPrincipalComponent vistaPrincipalComponent;

    //Objetos de apoyo
    private Documento documento;

    public RevisionComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.revisionTemplate = new RevisionTemplate(this);
        this.sRevision = RevisionService.getServicio();
        this.sDocumentos = DocumentosService.getServicio();

        //Hace que el componente pendientes se muestre de inicio
        /*this.revisionComponent = new RevisionComponent(this);
        vistaPrincipalTemplate.getpPrincipal().add(
                revisionComponent.getRevisionTemplate()
        );*/
        revisionTemplate.getpInferior().setBackground(Color.blue);

        agregarRegistro();
    }

    public RevisionTemplate getRevisionTemplate() {
        return revisionTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == revisionTemplate.getbEliminar())
            actualizarPrioridad();
        if(e.getSource() == revisionTemplate.getbPendientes()) {
            revisionTemplate.getbPendientes().setBackground(RecursosService.getServicio().getColorGrisBotonOscuro1());
            revisionTemplate.getbProximos().setBackground(RecursosService.getServicio().getColorGrisBoton1());
            mostrarComponentes(e.getActionCommand());
        }
        if(e.getSource() == revisionTemplate.getbProximos()) {
            revisionTemplate.getbProximos().setBackground(RecursosService.getServicio().getColorGrisBotonOscuro1());
            revisionTemplate.getbPendientes().setBackground(RecursosService.getServicio().getColorGrisBoton1());
            mostrarComponentes(e.getActionCommand());
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
        if(e.getSource() == revisionTemplate.getbEliminar()){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(RecursosService.getServicio().getColorPrincipalOscuro());
        }
        else if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            if(boton.getBackground() == RecursosService.getServicio().getColorGrisBotonOscuro1())
                boton.setBackground(RecursosService.getServicio().getColorGrisBotonOscuro2());
            else if(boton.getBackground() == RecursosService.getServicio().getColorGrisBoton1()){
                boton.setBackground(RecursosService.getServicio().getColorGrisBoton2());
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == revisionTemplate.getbEliminar()){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(RecursosService.getServicio().getColorPrincipal());
        }
        else if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            if(boton.getBackground() == RecursosService.getServicio().getColorGrisBotonOscuro2())
                boton.setBackground(RecursosService.getServicio().getColorGrisBotonOscuro1());
            else if(boton.getBackground() == RecursosService.getServicio().getColorGrisBoton2()){
                boton.setBackground(RecursosService.getServicio().getColorGrisBoton1());
            }
        }
    }

    public void mostrarComponentes(String comando){
        revisionTemplate.getpInferior().removeAll();

        switch (comando){
            case "Pendientes":
                revisionTemplate.getpInferior().setBackground(Color.blue);
                break;

            case "Próximos a vencer":
                revisionTemplate.getpInferior().setBackground(Color.yellow);
                break;
        }
        revisionTemplate.repaint();
    }

    //METODOS PARA MANEJAR LA INFO DE LA TABLA
    public void agregarRegistro(){
        if(sRevision.cantidadVencidos() == 0){
            revisionTemplate.getbEliminar().setEnabled(false);
            return;
        }
        if(revisionTemplate.getModelo().getRowCount() > 0)
            revisionTemplate.getModelo().removeRow(0);
        revisionTemplate.getbEliminar().setEnabled(true);
        documento = sDocumentos.getDocumento(sRevision.getSiguienteVencido().getIdDocumento());
        revisionTemplate.getModelo().addRow(
                new Object[]{
                        documento.getId(),
                        documento.getTipo(),
                        documento.getNombre(),
                        documento.getEstante().concat("-"+documento.getCarpeta()),
                        documento.getExpiracion().toString(),
                }
        );

    }
    public void actualizarPrioridad(){
        registrarMovimiento(sRevision.getSiguienteVencido().getIdDocumento(),"Eliminación");
        if(revisionTemplate.getModelo().getRowCount() > 0)
            revisionTemplate.getModelo().removeRow(0);

        sRevision.eliminarVencido();
        actualizarValores();
        agregarRegistro();
    }

    public void actualizarValores(){
        vistaPrincipalComponent.actualizarValores();
        restarurarValores();
    }

    public void restarurarValores(){
        sRevision.actualizarDatos();
        agregarRegistro();
    }
    public void registrarMovimiento(int id, String tipo){
        documento = sDocumentos.getDocumento(id);
        Movimiento movimiento = new Movimiento();
        movimiento.setIdDocumento(id);
        movimiento.setTipoDocumento(documento.getTipo());
        movimiento.setNombreDocumento(documento.getNombre());
        movimiento.setUbicacionDocumento(documento.getEstante().concat("-"+documento.getCarpeta()));
        movimiento.setUsuario(UsuarioService.getServicio().getUsuarioConectado().getNombreUsuario());
        movimiento.setTipoMovimiento(tipo);
        movimiento.setFecha(new Fecha(FechaService.getServicio().getFechaCompleta().split("/")));
        MovimientosService.getServicio().agregarMovimiento(movimiento);
    }
}
