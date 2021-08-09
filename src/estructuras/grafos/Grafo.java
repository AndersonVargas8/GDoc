package estructuras.grafos;

import estructuras.colas.ColaPrioritaria;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.pilas.Pila;
import estructuras.tablasHash.TablaHash;

public class Grafo {
    private ListaEncadenadaDoble<Vertice> vertices;
    private ListaEncadenadaDoble<Arista> aristas;

    public Grafo(){
        aristas = new ListaEncadenadaDoble<>();
        vertices = new ListaEncadenadaDoble<>();
    }

    public void agregarVertice(String nombre){
        vertices.insertarAlFinal(new Vertice(nombre));
    }

    public void eliminarVertice(String nombre){
        Vertice vertice = null;

        for(Vertice v: vertices){
            if(v.getNombre().equals(nombre)){
                vertice = v;
                break;
            }
        }

        if(vertice == null)
            return;

        for(int i = 0; i < aristas.cantidadDeElementos(); i++){
            Arista aux = aristas.leerDato(i);
            if(aux.getInicial().getNombre().equals(nombre) ||
                aux.getTerminal().getNombre().equals(nombre)){

                eliminarArista(aux.getInicial().getNombre(),aux.getTerminal().getNombre());
                vertices.eliminar(vertices.buscar(vertice)[0]);
            }
        }
    }

    public void adicionarArista(String inicial, String terminal, int peso){

        if(buscarArista(inicial,terminal) != null)
            return;

        Vertice vInicial = null;
        Vertice vTerminal = null;

        for(Vertice v: vertices){
            if(vInicial == null && v.getNombre().equals(inicial)){
                vInicial = v;
            }
            if(vTerminal == null && v.getNombre().equals(terminal)){
                vTerminal = v;
            }
            if(vInicial != null && vTerminal != null)
                break;
        }

        if(vInicial == null || vTerminal == null) {
            System.out.println("No existe algún vertice");
            return;
        }

        aristas.insertarAlFinal(new Arista(vInicial,vTerminal,peso));

        vInicial.agregarAdyacente(vTerminal);
    }

    public void adicionarAristaDoble(String v1, String v2, int peso){
        adicionarArista(v1,v2,peso);
        adicionarArista(v2,v1,peso);
    }

    public void eliminarArista(String inicial, String terminal){
        Arista aux = buscarArista(inicial,terminal);

        if(aux != null){
            aux.getInicial().eliminarAdyacente(aux.getTerminal());
            aux.getTerminal().eliminarAdyacente(aux.getInicial());
            aristas.eliminar(aristas.buscar(aux)[0]);
            return;
        }

    }

    public void modificarPesoArista(String inicial, String terminal, int nuevoPeso){
        Arista aux = buscarArista(inicial,terminal);

        if(buscarArista(inicial,terminal)!= null)
            aux.setPeso(nuevoPeso);

    }

    public Arista buscarArista(String inicial, String terminal){
        for(int i = 0; i < aristas.cantidadDeElementos(); i++){
            Arista aux = aristas.leerDato(i);
            if(aux.getInicial().getNombre().equals(inicial) &&
                    aux.getTerminal().getNombre().equals(terminal)) {
                return aux;
            }
        }
        return null;
    }

    public ListaEncadenadaDoble<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ListaEncadenadaDoble<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ListaEncadenadaDoble<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(ListaEncadenadaDoble<Arista> aristas) {
        this.aristas = aristas;
    }

    public Vertice buscarVertice(String nombre){
        for(Vertice v: vertices){
            if (v.getNombre().equals(nombre))
                return v;
        }
        return null;
    }
    public TablaHash dijkstra(String sOrigen){
        Vertice origen = buscarVertice(sOrigen);

        ColaPrioritaria<NodoDist> desconocidos = new ColaPrioritaria<>();
        TablaHash<String,NodoDist> tabla = new TablaHash<>();

        for(Vertice vertice: vertices){
            NodoDist nodo = new NodoDist(vertice,Integer.MAX_VALUE,null,false);
            tabla.insertar(vertice.getNombre(),nodo);
            desconocidos.insertar(tabla.leer(vertice.getNombre()));
        }

        tabla.leer(origen.getNombre()).setPeso(0);
        desconocidos.actualizar();
        while(!desconocidos.estaVacia()){
            NodoDist n = desconocidos.eliminarMinimo();
            n.setConocido(true);

            for(Vertice v: n.getVertice().getListaAdyacencia()){
                if(!tabla.leer(v.getNombre()).isConocido()){
                    int nPeso = buscarArista(n.getVertice().getNombre(),v.getNombre()).getPeso();

                    if(n.getPeso() + nPeso < tabla.leer(v.getNombre()).getPeso()){
                        tabla.leer(v.getNombre()).setPeso(n.getPeso() + nPeso);
                        desconocidos.actualizar();
                        tabla.leer(v.getNombre()).setIntermedio(n.getVertice().getNombre());
                    }
                }
            }

        }


        return tabla;

    }

    public Pila getCaminoMasCorto(String sOrigen, String sDestino){
        TablaHash<String,NodoDist> caminos = this.dijkstra(sOrigen);
        Pila<String> camino = new Pila<>();

        String nodo = sDestino;

        do{
            camino.push(nodo);
            nodo = caminos.leer(nodo).getIntermedio();
        }while(nodo != null);


        return camino;
    }

    public int getPesoMinimo(String sOrigen, String sDestino){
        TablaHash<String,NodoDist> caminos = this.dijkstra(sOrigen);
        return caminos.leer(sDestino).getPeso();
    }


}



