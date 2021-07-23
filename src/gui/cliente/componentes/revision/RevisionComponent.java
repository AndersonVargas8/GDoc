package gui.cliente.componentes.revision;

import datos.Documento;
import datos.DocumentoPriorizado;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.RevisionService;

import java.awt.event.*;

public class RevisionComponent implements ActionListener, MouseListener, FocusListener {
    private RevisionTemplate revisionTemplate;
    private RevisionService sRevision;
    private DocumentosService sDocumentos;

    //Objetos de apoyo
    private Documento documento;

    public RevisionComponent(){
        this.revisionTemplate = new RevisionTemplate(this);
        this.sRevision = RevisionService.getServicio();
        this.sDocumentos = DocumentosService.getServicio();

        agregarRegistro();
    }

    public RevisionTemplate getRevisionTemplate() {
        return revisionTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

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
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    //METODOS PARA MANEJAR LA INFO DE LA TABLA
    public void agregarRegistro(){
        documento = sDocumentos.getDocumento(sRevision.getSiguienteVencido().getIdDocumento());
        if(documento == null)
            return;

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
}
