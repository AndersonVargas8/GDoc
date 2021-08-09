package estructuras.grafos;

import estructuras.listas.ListaEncadenadaDoble;

public class Vertice implements Comparable<Vertice>{
    private String nombre;
    private ListaEncadenadaDoble<Vertice> listaAdyacencia;
    private int numeroAdyacentes;


    public Vertice(String nombre){
        this.nombre = nombre;
        listaAdyacencia = new ListaEncadenadaDoble<>();
        numeroAdyacentes = 0;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void agregarAdyacente(Vertice adyacente){
        listaAdyacencia.insertarAlFinal(adyacente);
        numeroAdyacentes++;
    }

    public void eliminarAdyacente(Vertice adyacente){
        for(int i = 0; i < listaAdyacencia.cantidadDeElementos(); i++){
            if (listaAdyacencia.leerDato(i).equals(adyacente)) {
                listaAdyacencia.eliminar(i);
                numeroAdyacentes--;
                return;
            }
        }
    }

    @Override
    public int compareTo(Vertice v) {
        return this.nombre.compareTo(v.nombre);
    }

    public ListaEncadenadaDoble<Vertice> getListaAdyacencia() {
        return listaAdyacencia;
    }

    public int getNumeroAdyacentes() {
        return numeroAdyacentes;
    }
}
