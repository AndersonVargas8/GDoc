package gui.servicios.serviciosLogicos;

import datos.Documento;
import datos.DocumentoPriorizado;
import estructuras.arboles.AVL;
import estructuras.colas.ColaPrioritaria;
import estructuras.listas.ListaEncadenadaDoble;

public class PendientesService {
    private static PendientesService servicio;
    private DocumentosService sDocumentos;
    private AVL<Integer> documentosPendientes;
    //Objeto de apoyo
    Documento documento;

    public PendientesService(){
        sDocumentos = DocumentosService.getServicio();
        documentosPendientes = new AVL<>();

        documentosPendientes.insertar(2);
        documentosPendientes.insertar(4);
        documentosPendientes.insertar(6);
    }

    public static PendientesService getServicio(){
        if(servicio == null)
            servicio = new PendientesService();

        return servicio;
    }

    public void agregarPendiente(int id){
        documentosPendientes.insertar(id);
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
}
