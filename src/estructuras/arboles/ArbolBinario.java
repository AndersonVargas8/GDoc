package estructuras.arboles;
import estructuras.arreglos.ArregloDinamico;
import estructuras.colas.Queue;
import estructuras.listas.ListaEncadenada;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.listas.ListaEncadenadaSimple;

/**
 * Esta clase permite almacenar datos jerarquicamente.
 * @author Anderson
 */
public class ArbolBinario<T>{
    protected NodoArbolBinario<T> raiz;

    /**
     * Crea un arbol vacío.
     */
    public ArbolBinario(){
        this.raiz = null;
    }

    /**
     * Retorna la raíz del árbol.
     * @return el nodo raíz del árbol.
     */
    public NodoArbolBinario<T> getRaiz() {
        return raiz;
    }

    /**
     * Establece la raíz del árbol.
     * @param raiz nodo que pasará a ser la raíz del árbol.
     */
    public void setRaiz(NodoArbolBinario<T> raiz) {
        this.raiz = raiz;
    }

    /**
     * Realiza un recorrido por el árbol, visitando primero al nodo padre y luego a sus hijos
     * en orden de izquierda a derecha.
     * @return un String con el recorrido preorden.
     */
    public String preorden(){
        return this.preorden(this.raiz,"");
    }

    private String preorden(NodoArbolBinario raiz, String recorrido){
        if(raiz != null){
            recorrido = recorrido.concat(raiz.getDato() + " ");
            recorrido = preorden(raiz.getHijoIzquierdo(),recorrido);
            recorrido = preorden(raiz.getHijoDerecho(),recorrido);
        }
        return recorrido;
    }

    public ArregloDinamico<T> preOrden(){
        ArregloDinamico<T> elementos = new ArregloDinamico<>();
        return this.preOrden(this.raiz,elementos);
    }

    private ArregloDinamico<T> preOrden(NodoArbolBinario raiz, ArregloDinamico<T> elementos){
        if(raiz != null){
            elementos.pushBack((T) raiz.getDato());
            preOrden(raiz.getHijoIzquierdo(),elementos);
            preOrden(raiz.getHijoDerecho(),elementos);
        }
        return elementos;
    }

    /**
     * Realiza un recorrido por el árbol, visitando primero a los hijos de izquierda a derecha
     * y luego al padre.
     * @return un String con el recorrido postorden.
     */
    public String postorden(){
        return this.postorden(this.raiz,"");
    }

    private String postorden(NodoArbolBinario raiz, String recorrido){
        if(raiz == null)
            return recorrido;
        if(raiz.getHijoIzquierdo() != null)
            recorrido = this.postorden(raiz.getHijoIzquierdo(),recorrido);
        if(raiz.getHijoDerecho() != null)
            recorrido = this.postorden(raiz.getHijoDerecho(),recorrido);
        recorrido = recorrido.concat(raiz.getDato() + " ");
        return recorrido;
    }

    /**
     * Realiza un recorrido por el árbol, visitando primero al hijo izquierdo, luego al padre y
     * luego el hijo derecho.
     * @return un String con el recorrido inorden.
     */
    public String inorden(){
        return this.inorden(this.raiz, "");
    }

    private String inorden(NodoArbolBinario raiz, String recorrido){
        if(raiz == null)
            return recorrido;
        if(raiz.getHijoIzquierdo() != null)
            recorrido = this.inorden(raiz.getHijoIzquierdo(), recorrido);
        recorrido = recorrido.concat(raiz.getDato() + " ");
        if(raiz.getHijoDerecho() != null)
            recorrido = this.inorden(raiz.getHijoDerecho(), recorrido);
        return recorrido;

    }

    public ListaEncadenadaDoble<T> inOrden(){
        ListaEncadenadaDoble<T> elementos = new ListaEncadenadaDoble<>();
        return this.inOrden(this.raiz,elementos);
    }

    private ListaEncadenadaDoble<T> inOrden(NodoArbolBinario raiz, ListaEncadenadaDoble<T> elementos){
        if(raiz == null)
            return elementos;
        if(raiz.getHijoIzquierdo() != null)
            inOrden(raiz.getHijoIzquierdo(), elementos);
        elementos.insertarAlInicio((T) raiz.getDato());
        if(raiz.getHijoDerecho() != null)
            inOrden(raiz.getHijoDerecho(), elementos);
        return elementos;

    }

    /**
     * Realiza un recorrido por el árbol, visitando los nodos de izquierda a derecha en cada nivel.
     * @return un String con el recorrido por niveles.
     */
    public String niveles(){
        return this.niveles(this.raiz, "");
    }

    protected String niveles(NodoArbolBinario raiz, String recorrido){
        if(raiz == null)
            return recorrido;
        Queue<NodoArbolBinario> cola = new Queue<>();
        NodoArbolBinario aux;
        cola.enqueue(raiz);
        while(! cola.estaVacia()){
            aux = cola.dequeue();
            recorrido = recorrido.concat(aux.getDato().toString() + " ");

            if(aux.getHijoIzquierdo() != null)
                cola.enqueue(aux.getHijoIzquierdo());

            if(aux.getHijoDerecho() != null)
                cola.enqueue(aux.getHijoDerecho());
        }
        return recorrido;
    }

    /**
     * Indica si el árbol se encuentra vacío.
     * @return True si el árbol esta vacío, False en otro caso.
     */
    public boolean estaVacio(){
        return this.raiz == null;
    }


    /**
     * Elimina todos los elementos del árbol
     */
    public void vaciar(){
        this.setRaiz(null);
    }

    /**
     * Retorna la altura del árbol
     * @return la altura del árbol.
     */
    public int altura(){
        return altura(this.raiz);
    }

    protected int altura(NodoArbolBinario<T> raiz){
        if(raiz == null)
            return -1;
        return 1 + Math.max(altura(raiz.getHijoIzquierdo()),altura(raiz.getHijoDerecho()));
    }

    public int cantidadNodos(){
        return cantidadNodos(this.raiz);
    }

    private int cantidadNodos(NodoArbolBinario<T> nodo){
        if(nodo == null)
            return 0;

        return 1 + cantidadNodos(nodo.getHijoIzquierdo()) + cantidadNodos(nodo.getHijoDerecho());
    }
}
