package estructuras.listas;
/**
 * Esta clase provee un nodo para ser utilizado en una estructura de datos.
 * @author Anderson Vargas - anvargasa
 * @param <T> Tipo de elemento del nodo.
 */
public class Nodo<T>{
    private T dato;
    private Nodo anterior;
    private Nodo siguiente;

    /**
     * Construye un nodo con un dato especificado.
     * @param dato dato asociado al nuevo nodo.
     */
    public Nodo(T dato){
        this.dato = dato;
        this.anterior = this.siguiente = null;
    }
    
    /**
     * Retorna el nodo anterior.
     * @return el nodo anterior.
     */
    public Nodo getAnterior(){
        return anterior;
    }
    
    /**
     * Establece el nodo anterior.
     * @param anterior nodo a ser ubicado como anterior.
     */
    public void setAnterior(Nodo anterior){
        this.anterior = anterior;
    }

    /**
     * Retorna el nodo siguiente.
     * @return el nodo siguiente.
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo.
     * @param siguiente nodo a ser ubicado como siguiente.
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    /**
     * Retorna el dato asociado al nodo.
     * @return dato asociado al nodo.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato asociado al nodo.
     * @param dato dato a ser asociado al nodo.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }
    
    /**
     * Retorna una cadena con el dato asociado al nodo.
     * @return una cadena con el dato asociado al nodo.
     */
    @Override
    public String toString() {
        return this.dato.toString();
    }  
}
