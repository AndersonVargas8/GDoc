package estructuras;
/**
 * Esta clase provee la estrucutura para los diferentes tipos de listas encadenadas.
 * @author Anderson Vargas - anvargasa
 * @param <T> Tipo de elementos que va a contener la lista
 */
public abstract class ListaEncadenada<T>{
    protected Nodo<T> primero;
    protected Integer contador;
    
    /**
     * Constructor para una lista vacía
     */
    public ListaEncadenada(){
        this.contador = 0;
        this.primero = null;
    }
    
    /**
     * Retorna el primer nodo de la lista.
     * @return El primer nodo de la lista.
     */
    public Nodo getPrimero(){
        return primero;
    }
    
    /**
     * Reemplaza el primer nodo de la lista con el elemento especificado.
     * @param primero Elemento que reemplaza al primer elemento de la lista.
     */
    protected void setPrimero(Nodo primero){
       this.primero = primero;
    }
    
    /**
     * Retorna el contador de la lista.
     * @return el contador de la lista.
     */
    protected int getContador() {
        return contador;
    }
    
    /**
     * Modifica el contador de la lista por el número especificado.
     * @param contador número que será el nuevo contador.
     */
    protected void setContador(int contador) {
        this.contador = contador;
    }
    
    /**
     * Aumenta en 1 el contador de la lista.
     */
    protected void aumentarContador(){
        this.contador++;
    }
    
    /**
     * Decrementa en 1 el contador de la lista.
     */
    protected void decrementarContador(){
        this.contador--;
    }
    
    /**
     *Inserta el elemento especificado al inicio de la lista,
     * desplazando el actual primer elemento a la siguiente posición.
     * @param dato elemento a ser insertado en el inicio de la lista.
     */
    public abstract void insertarAlInicio(T dato);
    
    /**
     * Inserta el elemento especificdo al final de la lista.
     * @param dato elemento a ser insertado en el final de la lista.
     */
    public abstract void insertarAlFinal(T dato);
    
    /**
     * Inserta un elemento especificado en la posición indicada,
     * desplazando el elemento que está en esa posición a la siguiente posición.
     * @param indice posición de la lista donde se inserta el nuevo elemento.
     * @param dato elemento a ser insertado.
     */
    public abstract void insertar(int indice, T dato);
    
    /**
     * Elimina el primer elemento de la lista, dejando como primer elemento el
     * que se encuentra en la siguiente posición.
     */
    public abstract void eliminarAlInicio();
    
    /**
     * Elimina el último elemento de la lista, dejando como último elemento el
     * elemento en la penúltima posición.
     */
    public abstract void eliminarAlFinal();
    
    /**
     * Elimina el elemento en la posición especificada.
     * @param indice posición de la lista donde se encuentra el elemento a ser eliminado.
     */
    public abstract void eliminar(int indice);
    
    /**
     * Retorna una cadena con los elementos de la lista.
     * @return una cadena con los elementos de la lista. Los elementos están entre [] y separados por ','.
     */
    @Override
    public abstract String toString();
    
    /**
     * Indica las posiciones donde se ha encontrado un elemento igual al especificado.
     * @param dato elemento a ser buscado.
     * @return arreglo con las posiciones donde se he encontrado un elemento igual al especificado.
     */
    public abstract int buscar(T dato);
    
    /**
     * Busca el elemento en la posición dada y retorna su dato asociado.
     * @param indice posición donde se encuentra el elemento a buscar.
     * @return dato del elemento en la posición indicada.
     */
    public abstract T leerDato(int indice);
    
    /**
     * Elimina todos los elementos de la lista, dejándola vacía.
     */
    public abstract void vaciar();
    
    /**
     * Retorna el dato asociado al primer elemento de la lista.
     * @return el dato asociado al primer elemento.
     */
    public abstract T primerElemento();
    
    /**
     * Retorna el dato asoiado al último elemento de la lista.
     * @return el dato asociado al último elemento de la lista.
     */
    public abstract T ultimoElemento();
    
    /**
     * Retorna la cantidad de elemento que contiene la lista.
     * @return la cantidad de elementos que contiene la lista.
     */
    public int cantidadDeElementos(){
        Nodo aux = this.primero;
        int cont = 0;
        while(aux != null){
            cont++;
            aux = aux.getSiguiente();
        }
        
        return cont;
    }
    
    /**
     * Indica si la lista está vacía.
     * @return true si la lista está vacía.
     */
    public boolean estaVacia(){
        return this.contador == 0;
    }
    
    /**
     *Elimina los elementos duplicados dentro de la lista. Cuando encuentra dos
     * elementos iguales, elimina la primera ocurrencia encontrada. Si hay otro 
     * elemento igual repite el proceso hasta que no hayan elementos repetidos.
     */
    public void eliminarDuplicados(){
        this.primero = this.eliminarDuplicados(this.primero);
    }
    
    private Nodo eliminarDuplicados(Nodo primero) {
        Nodo aux = primero.getSiguiente();
        while (aux != null) {
            if (primero.getDato().equals(aux.getDato())) {
                primero = primero.getSiguiente();
                aux = primero.getSiguiente();
                continue;
            }
            aux = aux.getSiguiente();
        }
        if (primero.getSiguiente() != null) {
            primero.setSiguiente(eliminarDuplicados(primero.getSiguiente()));
        }
        return primero;
    }
    
    /**
     * Imprime los elementos de la lista
     */
    public void imprimir(){
        System.out.println(this.toString());
    }
}