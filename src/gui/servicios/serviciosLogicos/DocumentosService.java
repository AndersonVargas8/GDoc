package gui.servicios.serviciosLogicos;

import datos.Documento;
import estructuras.arboles.AVL;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.tablasHash.TablaHash;
import logica.ControlDocumentos;

public class DocumentosService {
    private static DocumentosService servicio;
    private ControlDocumentos cDocumentos;
    private AVL<Documento> documentos;
    private ListaEncadenadaDoble<Documento> elementos;
    private ListaEncadenadaDoble<Documento> impresion;
    private TablaHash<String,Integer> bodegas;
    private Documento documento;
    private int numeroRegistro;

    public DocumentosService(){
        cDocumentos = new ControlDocumentos();
        crearBodegas();
        documentos = cDocumentos.getDocumentos();
        elementos = documentos.inOrden();
        numeroRegistro = cDocumentos.getNumeroRegistro();
    }

    public static DocumentosService getServicio(){
        if(servicio == null)
            servicio = new DocumentosService();

        return servicio;
    }

    public Documento devolverDocumento(int id){
        return (Documento)elementos.leerDato(id);
    }

    public Documento getDocumento(int id){
        if(documento == null)
            this.documento = new Documento();
        documento.setId(id);
        return documentos.buscar(documento);
    }

    public Documento devolverMayorDocumento(){
        return documentos.buscarMax();
    }

    public void agregarDocumento(Documento documento){
        this.documentos.insertar(documento);
        this.elementos = documentos.inOrden();
        this.numeroRegistro++;
    }

    public int devolverCantidadDocumentos(){
        return this.documentos.cantidadNodos();
    }

    public void eliminarDocumento(Documento documento){
        this.documentos.eliminar(documento);
    }

    public ListaEncadenadaDoble imprimirTodo(){
        impresion = documentos.inOrden();
        return impresion;
    }

    public void nuevoFiltro(){
        if(this.impresion == null)
            this.impresion = new ListaEncadenadaDoble<>();
        elementos = documentos.inOrden();
        impresion.vaciar();
    }

    public void agregarAImpresion(Documento documento){
        this.impresion.insertarAlFinal(documento);
    }

    public ListaEncadenadaDoble<Documento> getImpresion(){
        return this.impresion;
    }

    public int getSiguienteId(){
        return numeroRegistro;
    }

    private void crearBodegas(){
        this.bodegas = new TablaHash<>();
        bodegas.insertar("Hoja de vida",1);
        bodegas.insertar("Incapacidad",1);
        bodegas.insertar("Liquidación",1);
        bodegas.insertar("Afiliación Pensión",2);
        bodegas.insertar("Afiliación Salud",2);
        bodegas.insertar("Memorando",2);
        bodegas.insertar("Contrato",3);
        bodegas.insertar("Otro si",3);
    }

    public int retornarBodega(String tipoDoc){
        return bodegas.leer(tipoDoc);
    }
}
