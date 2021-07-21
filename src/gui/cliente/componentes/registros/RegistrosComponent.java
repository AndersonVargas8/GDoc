package gui.cliente.componentes.registros;

import datos.Documento;
import estructuras.arreglos.ArregloDinamico;
import gui.cliente.componentes.navegacionUsuario.NavegacionUsuarioComponent;
import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.FechaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RegistrosComponent implements ActionListener, MouseListener, FocusListener {
    private RegistrosTemplate registrosTemplate;
    private DocumentosService sDocumentos;
    private FechaService sFecha;
    private VistaPrincipalComponent vistaPrincipalComponent;

    //Objetos de apoyo
    private Documento documento;
    private String[] placeholders = {"Tipo", "Nombre", "Estante", "Carpeta", "Filtrar..."};

    public RegistrosComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.registrosTemplate = new RegistrosTemplate(this);
        this.sDocumentos = DocumentosService.getServicio();
        this.sFecha = FechaService.getServicio();

        this.mostrarRegistrosTabla();
    }

    public RegistrosTemplate getRegistrosTemplate() {
        return registrosTemplate;
    }

    //ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrosTemplate.getbMostrar())
            mostrarRegistrosTabla();
        if(e.getSource() == registrosTemplate.getbInsertar())
            insertarRegistroTabla();
        if(e.getSource() == registrosTemplate.getbModificar())
            modificarRegistroTabla();
        if(e.getSource() == registrosTemplate.getbEliminar())
            eliminarRegistroTabla();
        if(e.getSource() == registrosTemplate.getbFiltrar())
            filtrarRegistrosTabla();
        if(e.getSource() == registrosTemplate.getbLimpiar())
            restaurarValores();
        if(e.getSource() == registrosTemplate.getCbTipo()){
            registrosTemplate.getlExpiracionValor().setText(cambiarVencimiento());
        }
    }

    //FocusListener
    @Override
    public void focusGained(FocusEvent e) {
        JTextField textField = ((JTextField) e.getSource());
        textField.setBorder(RecursosService.getServicio().getBordeTextField());
        if(
                textField.getText().equals(placeholders[0]) || textField.getText().equals(placeholders[1]) ||
                        textField.getText().equals(placeholders[2]) || textField.getText().equals(placeholders[3]) ||
                        textField.getText().equals(placeholders[4])
        )
            textField.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField textField = ((JTextField) e.getSource());
        textField.setBorder(null);
    }

    //MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JTable){
            int fSeleccionada = registrosTemplate.getTabla().getSelectedRow();
            documento = sDocumentos.devolverDocumento(fSeleccionada);
            registrosTemplate.getlIdValor().setText(documento.getId()+"");
            registrosTemplate.getCbTipo().setSelectedItem(documento.getTipo());
            registrosTemplate.gettNombre().setText(documento.getNombre());
            registrosTemplate.gettEstante().setText(documento.getEstante());
            registrosTemplate.gettCarpeta().setText(documento.getCarpeta());
            registrosTemplate.getlIngresoValor().setText(documento.getIngreso());
            registrosTemplate.getlExpiracionValor().setText(documento.getExpiración());
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

    //MÉTODOS PARA EL CONTROL DE DOCUMENTOS EN LA TABLA
    public void restaurarValores(){
        int cantidadDocumentos = sDocumentos.devolverCantidadDocumentos();
        int idUltimoDocumento = sDocumentos.devolverDocumento(cantidadDocumentos-1).getId();
        registrosTemplate.getlIdValor().setText((idUltimoDocumento + 1)+"");
        registrosTemplate.getCbTipo().setSelectedIndex(0);
        registrosTemplate.gettNombre().setText(placeholders[1]);
        registrosTemplate.gettEstante().setText(placeholders[2]);
        registrosTemplate.gettCarpeta().setText(placeholders[3]);
        registrosTemplate.getlIngresoValor().setText(sFecha.getFecha());
        registrosTemplate.getlExpiracionValor().setText(cambiarVencimiento());
    }

    public void mostrarRegistrosTabla(){
        int cantidadDocumentos = sDocumentos.devolverCantidadDocumentos();
        for(int i = 0; i < cantidadDocumentos; i++){
            documento = sDocumentos.devolverDocumento(i);
            this.agregarRegistro(documento);
        }
        int idUltimoDocumento = sDocumentos.devolverDocumento(cantidadDocumentos-1).getId();
        registrosTemplate.getlIdValor().setText((idUltimoDocumento + 1)+"");
        registrosTemplate.getbMostrar().setEnabled(false);
    }

    public void insertarRegistroTabla(){
        documento = new Documento();
        int cantidadDocumentos = sDocumentos.devolverCantidadDocumentos();
        int idUltimoDoc = 0;
        if (cantidadDocumentos != 0)
            idUltimoDoc = sDocumentos.devolverDocumento(cantidadDocumentos-1).getId();
        documento.setId(idUltimoDoc+1);
        documento.setTipo((String)registrosTemplate.getCbTipo().getSelectedItem());
        documento.setNombre(registrosTemplate.gettNombre().getText());
        documento.setEstante(registrosTemplate.gettEstante().getText());
        documento.setCarpeta(registrosTemplate.gettCarpeta().getText());
        documento.setIngreso(registrosTemplate.getlIngresoValor().getText());
        documento.setExpiración(registrosTemplate.getlExpiracionValor().getText());

        this.agregarRegistro(documento);
        sDocumentos.agregarDocumento(documento);
        restaurarValores();
        actualizarValores();
    }

    public void modificarRegistroTabla(){
        int fSeleccionada = registrosTemplate.getTabla().getSelectedRow();
        if(fSeleccionada != -1){
            registrosTemplate.getModelo().setValueAt(
                    (String)registrosTemplate.getCbTipo().getSelectedItem(), fSeleccionada,1
            );
            registrosTemplate.getModelo().setValueAt(
                    registrosTemplate.gettNombre().getText(), fSeleccionada,2
            );
            registrosTemplate.getModelo().setValueAt(
                    registrosTemplate.gettEstante().getText().concat(
                            registrosTemplate.gettCarpeta().getText()
                    ), fSeleccionada,3
            );
            registrosTemplate.getModelo().setValueAt(
                    registrosTemplate.getlExpiracionValor().getText(),fSeleccionada,5
            );
            documento = sDocumentos.devolverDocumento(fSeleccionada);
            documento.setTipo((String)registrosTemplate.getCbTipo().getSelectedItem());
            documento.setNombre(registrosTemplate.gettNombre().getText());
            documento.setEstante(registrosTemplate.gettEstante().getText());
            documento.setCarpeta(registrosTemplate.gettCarpeta().getText());
            documento.setExpiración(registrosTemplate.getlExpiracionValor().getText());
            restaurarValores();
        }
        else{
            JOptionPane.showMessageDialog(null,"seleccione una fila", "Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarRegistroTabla(){
        int fSeleccionada = registrosTemplate.getTabla().getSelectedRow();
        if(fSeleccionada != -1){
            registrosTemplate.getModelo().removeRow(fSeleccionada);
            documento = sDocumentos.devolverDocumento(fSeleccionada);
            sDocumentos.eliminarDocumento(documento);
            actualizarValores();
        }else
            JOptionPane.showMessageDialog(null,"seleccione una fila","Error",JOptionPane.ERROR_MESSAGE);

    }

    public void filtrarRegistrosTabla(){
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(registrosTemplate.getModelo());
        registrosTemplate.getTabla().setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(registrosTemplate.gettConsulta().getText()));

    }

    public void agregarRegistro(Documento documento){
        registrosTemplate.getModelo().addRow(
                new Object[]{
                        documento.getId(),
                        documento.getTipo(),
                        documento.getNombre(),
                        documento.getEstante().concat("-" + documento.getCarpeta()),
                        documento.getIngreso(),
                        documento.getExpiración()
                }
        );

    }

    public void actualizarValores(){
        vistaPrincipalComponent.restaurarValores();
    }

    public String cambiarVencimiento(){
        int anniosVencimiento = (registrosTemplate.getCbTipo().getSelectedIndex()) +1;
        return sFecha.getFechaPlus(anniosVencimiento);
    }
}
