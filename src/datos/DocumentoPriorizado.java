package datos;

public class DocumentoPriorizado implements Comparable<DocumentoPriorizado>{
    private int idDocumento;
    private Fecha fechaDocumento;

    public DocumentoPriorizado(){}

    public DocumentoPriorizado(int id, Fecha fecha){
        this.idDocumento = id;
        this.fechaDocumento = fecha;
    }

    @Override
    public int compareTo(DocumentoPriorizado documento){

        if(this.fechaDocumento.getAnnio() - documento.fechaDocumento.getAnnio() != 0)
            return this.fechaDocumento.getAnnio() - documento.fechaDocumento.getAnnio();

        if(this.fechaDocumento.getMes() - documento.fechaDocumento.getMes() != 0)
            return this.fechaDocumento.getMes() - documento.fechaDocumento.getMes();

        if(this.fechaDocumento.getDia() - documento.fechaDocumento.getDia() != 0)
            return this.fechaDocumento.getDia() - documento.fechaDocumento.getDia();

        return 0;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Fecha getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Fecha fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }
}
