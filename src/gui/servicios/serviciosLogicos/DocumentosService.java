package gui.servicios.serviciosLogicos;

import datos.Documento;
import estructuras.arboles.AVL;
import estructuras.arreglos.ArregloDinamico;
import logica.ControlDocumentos;

public class DocumentosService {
    private static DocumentosService servicio;
    private ControlDocumentos cDocumentos;
    private AVL<Documento> documentos;
    private ArregloDinamico<Documento> arreglo;
    public DocumentosService(){
        cDocumentos = new ControlDocumentos();
        documentos = cDocumentos.getDocumentos();
        arreglo = documentos.inOrden();
    }

    public static DocumentosService getServicio(){
        if(servicio == null)
            servicio = new DocumentosService();

        return servicio;
    }

    public Documento devolverDocumento(int id){
        return arreglo.get(id);
    }

    public void agregarDocumento(Documento documento){
        this.documentos.insertar(documento);
        this.arreglo = documentos.inOrden();
    }

    public int devolverCantidadDocumentos(){
        return this.documentos.cantidadNodos();
    }

    public void eliminarDocumento(Documento documento){
        this.documentos.eliminar(documento);
        this.arreglo = documentos.inOrden();
    }
}
