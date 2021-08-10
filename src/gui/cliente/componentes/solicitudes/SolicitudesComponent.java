package gui.cliente.componentes.solicitudes;

import datos.Documento;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.SolicitudesService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

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

        this.mostrarRegistrosTabla();
    }

    public void nuevaSolicitud(int id){
        documento = sDocumentos.getDocumento(id);
        String infoDoc = documento.getId() + " - " + documento.getTipo() + " - " + documento.getNombre();
        solicitudesTemplate.gettDocumento().setText(infoDoc);
        solicitudesTemplate.getbSolicitar().setEnabled(true);
        solicitudesTemplate.getCbDependencia().setEnabled(true);
    }

    public void solicitarDocumento(){
        int id = documento.getId();
        String infoDoc = solicitudesTemplate.gettDocumento().getText();
        String dependencia = (String) solicitudesTemplate.getCbDependencia().getSelectedItem();
        documento.setDisponible(false);
        documento.setDependencia(dependencia);

        int tiempo = sSolicitudes.getTiempoSolicitud(documento,dependencia);
        agregarRegistro(infoDoc);
        restaurarValores();
        crearTiempo(tiempo,infoDoc,dependencia);
        JOptionPane.showMessageDialog(null,"El documento llegará en " + tiempo + " segundos");

    }

    public void devolverDocumento(){
        int fSeleccionada = solicitudesTemplate.getTablaOcu().getSelectedRow();

        if(fSeleccionada != -1){
            if(!solicitudesTemplate.getModeloOcu().getValueAt(fSeleccionada,2).equals("-"))
                return;
            String sId = (String) solicitudesTemplate.getModeloOcu().getValueAt(fSeleccionada,0);
            int id = Integer.valueOf(sId.split("-")[0].trim());
            Documento doc = sDocumentos.getDocumento(id);
            int tiempo = sSolicitudes.getTiempoSolicitud(doc.getDependencia(),doc);
            crearTiempo(sId,tiempo,doc);
          }else
            JOptionPane.showMessageDialog(null,"Seleccione una fila","Error",JOptionPane.ERROR_MESSAGE);

    }
    public void restaurarValores(){
        solicitudesTemplate.gettDocumento().setText("Documento");
        solicitudesTemplate.getCbDependencia().setSelectedIndex(0);
        solicitudesTemplate.getCbDependencia().setEnabled(false);
        solicitudesTemplate.getbSolicitar().setEnabled(false);
    }

    //MÉTODOS PARA EL CONTROL DE INFORMACIÓN EN LA TABLA
    public void mostrarRegistrosTabla(){
        agregarRegistros(sDocumentos.imprimirTodo());
    }

    public void agregarRegistros(Iterable<Documento> datos){
        for(Documento doc: datos)
            if(!doc.isDisponible())
                agregarRegistro(doc);
    }

    public void agregarRegistro(Documento doc){
        String infoDoc = doc.getId() + " - " + doc.getTipo() + " - " + doc.getNombre();
        solicitudesTemplate.getModeloOcu().addRow(
                new Object[]{
                        infoDoc,
                        doc.getDependencia(),
                        "-"
                }
        );
    }

    public void agregarRegistro(String infoDoc){
        solicitudesTemplate.getModeloSol().addRow(
                new Object[]{
                        infoDoc,
                }
        );
    }

    public void agregarRegistro(String infoDoc, String dependecia){
        solicitudesTemplate.getModeloOcu().addRow(
                new Object[]{
                        infoDoc,
                        dependecia,
                        "-"
                }
        );
    }

    public void crearTiempo(int tiempo,String infoDoc, String dependencia){
        final int[] ultimaFila = {solicitudesTemplate.getTablaSol().getRowCount() - 1};

        java.util.Timer timer = new Timer();
        final int[] i = {tiempo};
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                ultimaFila[0] = buscarFilsSol(infoDoc);
                solicitudesTemplate.getModeloSol().setValueAt(
                        i[0]--, ultimaFila[0],1
                );
                solicitudesTemplate.getTablaSol().repaint();
                if(i[0] == -1) {
                    timer.cancel();
                    timer.purge();
                    solicitudesTemplate.getModeloSol().removeRow(ultimaFila[0]);
                    agregarRegistro(infoDoc,dependencia);
                }
            }
        };
        timer.schedule(tarea,0,1000);

    }

    public void crearTiempo(String infoDoc, int tiempo, Documento doc){
        int[] fila = {solicitudesTemplate.getTablaOcu().getSelectedRow()};
        java.util.Timer timer = new Timer();
        final int[] i = {tiempo};
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                fila[0] = buscarFilsOcu(infoDoc);
                solicitudesTemplate.getModeloOcu().setValueAt(
                        i[0]--, fila[0],2
                );
                solicitudesTemplate.getTablaOcu().repaint();
                if(i[0] == -1) {
                    timer.cancel();
                    timer.purge();
                    solicitudesTemplate.getModeloOcu().removeRow(fila[0]);
                    doc.setDisponible(true);
                    doc.setDependencia(null);
                }
            }
        };
        timer.schedule(tarea,0,1000);

    }

    public int buscarFilsSol(String infoDoc){
        int n = solicitudesTemplate.getModeloSol().getRowCount();

        for(int i = 0; i < n; i++)
            if(solicitudesTemplate.getModeloSol().getValueAt(i,0).equals(infoDoc))
                return i;

        return 0;
    }

    public int buscarFilsOcu(String infoDoc){
        int n = solicitudesTemplate.getModeloOcu().getRowCount();

        for(int i = 0; i < n; i++)
            if(solicitudesTemplate.getModeloOcu().getValueAt(i,0).equals(infoDoc))
                return i;

        return 0;
    }
    public SolicitudesTemplate getSolicitudesTemplate() {
        return solicitudesTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == solicitudesTemplate.getbSolicitar())
            solicitarDocumento();
        if(e.getSource() == solicitudesTemplate.getbDevolver())
            devolverDocumento();
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
