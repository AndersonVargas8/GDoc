package gui.servicios.serviciosLogicos;

import datos.Documento;
import datos.DocumentoPriorizado;
import estructuras.arboles.AVL;
import estructuras.colas.ColaPrioritaria;
import estructuras.listas.ListaEncadenada;
import estructuras.listas.ListaEncadenadaDoble;
import logica.ControlPendientes;

public class PendientesService {
    private static PendientesService servicio;
    private ControlPendientes cPendientes;
    private DocumentosService sDocumentos;
    private AVL<Integer> documentosPendientes;
    private ColaPrioritaria<DocumentoPriorizado> documentosAVencer;
    //Objeto de apoyo
    Documento documento;

    public PendientesService(){
        cPendientes = new ControlPendientes();
        sDocumentos = DocumentosService.getServicio();

        documentosPendientes = cPendientes.getPendientes();
        encontrarAVencer();
    }

    public static PendientesService getServicio(){
        if(servicio == null)
            servicio = new PendientesService();

        return servicio;
    }

    public void agregarPendiente(int id){
        documentosPendientes.insertar(id);
    }

    public boolean estaEnPendientes(int id){
        return documentosPendientes.contiene(id);
    }
    public ListaEncadenadaDoble<Integer> getPendientes(){
        return documentosPendientes.inOrden();
    }

    public void quitarPendiente(int id){
        documentosPendientes.eliminar(id);
    }

    public void eliminarPendiente(int id){
        documento = new Documento();
        documento.setId(id);
        sDocumentos.eliminarDocumento(documento);
        quitarPendiente(id);
    }

    public int cantidadPendientes(){
        return documentosPendientes.cantidadNodos();
    }


    //PRÓXIMOS A VENCER
    public void encontrarAVencer(){
        if(documentosAVencer == null)
            documentosAVencer = new ColaPrioritaria<>();
        else
            documentosAVencer.vaciar();

        ListaEncadenada<Documento> datos = sDocumentos.imprimirTodo();
        for(Documento doc: datos)
            if(!doc.estaVencido())
                documentosAVencer.insertar(new DocumentoPriorizado(doc.getId(),doc.getExpiracion()));
    }

    public ColaPrioritaria getAVencer(){
        encontrarAVencer();
        return documentosAVencer;
    }
}
