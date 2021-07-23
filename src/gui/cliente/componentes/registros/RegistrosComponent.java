package gui.cliente.componentes.registros;

import datos.Documento;
import datos.Fecha;
import datos.Movimiento;
import gui.cliente.vistaPrincipal.VistaPrincipalComponent;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.FechaService;
import gui.servicios.serviciosLogicos.MovimientosService;
import gui.servicios.serviciosLogicos.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class RegistrosComponent implements ActionListener, MouseListener, FocusListener {
    private RegistrosTemplate registrosTemplate;
    private DocumentosService sDocumentos;
    private MovimientosService sMovimientos;
    private FechaService sFecha;
    private VistaPrincipalComponent vistaPrincipalComponent;

    //Objetos de apoyo
    private Documento documento;
    private Movimiento movimiento;
    private String[] placeholders = {"Tipo", "Nombre", "Estante", "Carpeta", "Filtrar..."};

    public RegistrosComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.registrosTemplate = new RegistrosTemplate(this);
        this.sDocumentos = DocumentosService.getServicio();
        this.sMovimientos = MovimientosService.getServicio();
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
            int id = (Integer)registrosTemplate.getModelo().getValueAt(fSeleccionada,0);
            documento = sDocumentos.getDocumento(id);
            registrosTemplate.getlIdValor().setText(documento.getId()+"");
            registrosTemplate.getCbTipo().setSelectedItem(documento.getTipo());
            registrosTemplate.gettNombre().setText(documento.getNombre());
            registrosTemplate.gettEstante().setText(documento.getEstante());
            registrosTemplate.gettCarpeta().setText(documento.getCarpeta());
            registrosTemplate.getlIngresoValor().setText(documento.getIngreso().toString());
            registrosTemplate.getlExpiracionValor().setText(documento.getExpiracion().toString());
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
        int idUltimoDocumento = sDocumentos.getSiguienteId();
        registrosTemplate.getlIdValor().setText(idUltimoDocumento +"");
        registrosTemplate.getCbTipo().setSelectedIndex(0);
        registrosTemplate.gettNombre().setText(placeholders[1]);
        registrosTemplate.gettEstante().setText(placeholders[2]);
        registrosTemplate.gettCarpeta().setText(placeholders[3]);
        registrosTemplate.getlIngresoValor().setText(sFecha.getFecha());
        registrosTemplate.getlExpiracionValor().setText(cambiarVencimiento());
    }

    public void mostrarRegistrosTabla(){
        eliminarRegistros();
        agregarRegistros(sDocumentos.imprimirTodo());
        restaurarValores();
        registrosTemplate.getbMostrar().setEnabled(false);
    }

    public void insertarRegistroTabla(){
        int idUltimoDoc = sDocumentos.getSiguienteId();
        documento = new Documento();
        documento.setId(idUltimoDoc);
        documento.setTipo((String)registrosTemplate.getCbTipo().getSelectedItem());
        documento.setNombre(registrosTemplate.gettNombre().getText());
        documento.setEstante(registrosTemplate.gettEstante().getText());
        documento.setCarpeta(registrosTemplate.gettCarpeta().getText());
        documento.setIngreso(new Fecha(registrosTemplate.getlIngresoValor().getText().split("/")));
        documento.setExpiracion(new Fecha(registrosTemplate.getlExpiracionValor().getText().split("/")));
        sDocumentos.agregarDocumento(documento);

        registrarMovimiento((idUltimoDoc), "Inserción");
        actualizarValores();
        mostrarRegistrosTabla();
        restaurarValores();
    }

    public void modificarRegistroTabla(){
        int fSeleccionada = registrosTemplate.getTabla().getSelectedRow();
        if(fSeleccionada != -1) {
            int id = (Integer) registrosTemplate.getModelo().getValueAt(fSeleccionada,0);
            documento = sDocumentos.getDocumento(id);
            documento.setTipo((String) registrosTemplate.getCbTipo().getSelectedItem());
            documento.setNombre(registrosTemplate.gettNombre().getText());
            documento.setEstante(registrosTemplate.gettEstante().getText());
            documento.setCarpeta(registrosTemplate.gettCarpeta().getText());
            int annioIngreso = documento.getIngreso().getAnnio();
            int annioVencimiento = annioIngreso + registrosTemplate.getCbTipo().getSelectedIndex() + 1;
            documento.getExpiracion().setAnnio(annioVencimiento);

            registrarMovimiento(id,"Modificación");
            eliminarRegistros();
            agregarRegistros(sDocumentos.getImpresion());
            actualizarValores();
            restaurarValores();
        }
        else{
            JOptionPane.showMessageDialog(null,"seleccione una fila", "Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarRegistroTabla(){
        int fSeleccionada = registrosTemplate.getTabla().getSelectedRow();
        if(fSeleccionada != -1){
            int id = (Integer) registrosTemplate.getModelo().getValueAt(fSeleccionada,0);
            registrarMovimiento(id,"Eliminación");
            documento = new Documento();
            documento.setId(id);
            sDocumentos.eliminarDocumento(documento);
            sDocumentos.getImpresion().eliminar(fSeleccionada);
            actualizarValores();
            restaurarValores();
            eliminarRegistros();
            agregarRegistros(sDocumentos.getImpresion());
        }else
            JOptionPane.showMessageDialog(null,"seleccione una fila","Error",JOptionPane.ERROR_MESSAGE);

    }

    public void filtrarRegistrosTabla(){
        if(registrosTemplate.gettConsulta().getText().isEmpty() ||
                (!getRegistrosTemplate().getCheckId().isSelected() &&
                        !getRegistrosTemplate().getCheckTipo().isSelected() &&
                        !getRegistrosTemplate().getCheckNombre().isSelected() &&
                        !getRegistrosTemplate().getCheckUbicacion().isSelected() &&
                        !getRegistrosTemplate().getCheckIngreso().isSelected() &&
                        !getRegistrosTemplate().getCheckExpiracion().isSelected()
                )
        ) {
            if(registrosTemplate.getModelo().getRowCount() == 0)
                mostrarRegistrosTabla();
            return;
        }
        //Habilita el boton mostrar
        registrosTemplate.getbMostrar().setEnabled(true);

        //limpia la tabla
        eliminarRegistros();

        sDocumentos.nuevoFiltro();

        int cantDocs = sDocumentos.devolverCantidadDocumentos();

        for(int i = 0; i < cantDocs; i++){
            Documento doc = sDocumentos.devolverDocumento(i);
            String valor;
            if(registrosTemplate.getCheckId().isSelected()) {
                valor = String.valueOf(doc.getId());
                if(valor.contains(registrosTemplate.gettConsulta().getText())) {
                    sDocumentos.agregarAImpresion(doc);
                    continue;
                }
            }

            if(registrosTemplate.getCheckTipo().isSelected()) {
                valor = doc.getTipo();
                if(valor.contains(registrosTemplate.gettConsulta().getText())) {
                    sDocumentos.agregarAImpresion(doc);
                    continue;
                }
            }

            if(registrosTemplate.getCheckNombre().isSelected()) {
                valor = doc.getNombre();
                if(valor.contains(registrosTemplate.gettConsulta().getText())) {
                    sDocumentos.agregarAImpresion(doc);
                    continue;
                }
            }

            if(registrosTemplate.getCheckUbicacion().isSelected()) {
                valor = doc.getCarpeta().concat(doc.getEstante());
                if(valor.contains(registrosTemplate.gettConsulta().getText())) {
                    sDocumentos.agregarAImpresion(doc);
                    continue;
                }
            }

            if(registrosTemplate.getCheckIngreso().isSelected()) {
                valor = doc.getIngreso().toString();
                if(valor.contains(registrosTemplate.gettConsulta().getText())) {
                    sDocumentos.agregarAImpresion(doc);
                    continue;
                }
            }

            if(registrosTemplate.getCheckExpiracion().isSelected()) {
                valor = doc.getExpiracion().toString();
                if(valor.contains(registrosTemplate.gettConsulta().getText())) {
                    sDocumentos.agregarAImpresion(doc);
                    continue;
                }
            }
        }
        agregarRegistros(sDocumentos.getImpresion());
    }

    public void agregarRegistros(Iterable<Documento> datos){
        for(Documento doc: datos)
            agregarRegistro(doc);
    }
    public void eliminarRegistros(){
        DefaultTableModel dm = registrosTemplate.getModelo();
        int c = dm.getRowCount();

        for (int i = c -1 ; i >= 0; i--)
            dm.removeRow(i);
    }
    public void agregarRegistro(Documento documento){
        registrosTemplate.getModelo().addRow(
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

    public void actualizarValores(){
        vistaPrincipalComponent.restaurarValores();
    }

    public String cambiarVencimiento(){
        int anniosVencimiento = (registrosTemplate.getCbTipo().getSelectedIndex()) +1;
        return sFecha.getFechaPlus(registrosTemplate.getlIngresoValor().getText(),anniosVencimiento);
    }

    public void registrarMovimiento(int id, String tipo){
        documento = sDocumentos.getDocumento(id);
        movimiento = new Movimiento();
        movimiento.setIdDocumento(id);
        movimiento.setTipoDocumento(documento.getTipo());
        movimiento.setNombreDocumento(documento.getNombre());
        movimiento.setUbicacionDocumento(documento.getEstante().concat("-"+documento.getCarpeta()));
        movimiento.setUsuario(UsuarioService.getServicio().getUsuarioConectado().getNombreUsuario());
        movimiento.setTipoMovimiento(tipo);
        movimiento.setFecha(new Fecha(sFecha.getFechaCompleta().split("/")));
        sMovimientos.agregarMovimiento(movimiento);
    }
}
