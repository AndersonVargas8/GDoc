package gui.servicios.serviciosLogicos;

import datos.Documento;
import datos.DocumentoPriorizado;
import estructuras.colas.Cola;
import estructuras.colas.ColaPrioritaria;
import estructuras.listas.ListaEncadenada;

public class RevisionService {
    private static RevisionService servicio;
    private DocumentosService sDocumentos;
    private ColaPrioritaria<DocumentoPriorizado> documentosVencidos;

    public RevisionService(){
        sDocumentos = DocumentosService.getServicio();
        encontrarVencidos();
    }

    public static RevisionService getServicio(){
        if(servicio == null)
            servicio = new RevisionService();
        return servicio;
    }

    public void encontrarVencidos(){
        if(documentosVencidos == null)
            documentosVencidos = new ColaPrioritaria<>();
        else
            documentosVencidos.vaciar();
        ListaEncadenada<Documento> documentos = sDocumentos.imprimirTodo();

        for(Documento doc: documentos){
            if(doc.estaVencido())
                documentosVencidos.insertar(new DocumentoPriorizado(doc.getId(),doc.getExpiracion()));
        }
    }

    public DocumentoPriorizado getSiguienteVencido(){
        return documentosVencidos.minimo();
    }

    public int cantidadVencidos(){
        return documentosVencidos.cantidadElementos();
    }
}
