package estructuras.arboles;

/**
 *Esta clase proporciona nodos que tienen un hijo izquierdo y un hijo izquierdo.
 * @author Anderson
 */
public class NodoArbolBinario<T> {
    private T dato;
    private NodoArbolBinario hijoIzquierdo;
    private NodoArbolBinario hijoDerecho;

    /**
     * Dado un dato del tipo T, se crea un árbol, donde el dato pasado como parámetro será la raíz.
     * @param dato Primer elemento (raíz) del nuevo árbol.
     */
    public NodoArbolBinario(T dato){
        this.dato = dato;
        this.hijoIzquierdo = this.hijoDerecho = null;
    }

    /**
     * Retorna el dato el nodo.
     * @return dato de tipo T.
     */
    public T getDato(){
        return this.dato;
    }

    /**
     * Retona el hijo izquierdo del nodo.
     * @return el nodo hijo izquierdo.
     */
    public NodoArbolBinario getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }

    /**
     * Retorna el hijo derecho del nodo.
     * @return el nodo hijo derecho.
     */
    public NodoArbolBinario getHijoDerecho(){
        return this.hijoDerecho;
    }

    /**
     * Establece el dato del nodo.
     * @param dato dato que será asignado al nodo.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Establece el hijo izquierdo del nodo.
     * @param nodo dato que será el hijo izquierdo del nodo.
     */
    public void setHijoIzquierdo(NodoArbolBinario<T> nodo) {
        this.hijoIzquierdo = nodo;
    }

    /**
     * Establece el hijo derecho del nodo.
     * @param nodo dato que será el hijo derecho del nodo.
     */
    public void setHijoDerecho(NodoArbolBinario<T> nodo) {
        this.hijoDerecho = nodo;
    }
}
