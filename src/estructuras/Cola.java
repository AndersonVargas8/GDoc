package estructuras;

/**
 * Esta lista implementa una lista encadenada simple para realizar las funciones FIFO.
 * @author Anderson
 */
public class Cola<T> extends ListaEncadenadaDoble{
    
    /**
     * Constuye una cola vacía.
     */
    public Cola(){
        super();
    }
    
    public Cola(T dato){
        super.insertarAlFinal(dato);
    }
    
    /**
     * Inserta el dato indicado en la última posición de la cola.
     * @param dato 
     */
    public void enqueue(T dato){
        super.insertarAlFinal(dato);
    }
    
    /**
     * Retorna el primer elemento de la cola y lo elimina de la cola.
     * @return el primer elemento de la cola.
     */
    public T dequeue(){
        Nodo aux = super.getPrimero();
        if (aux == null){
            return null;
        }
        T toReturn = (T) super.primerElemento();
        super.eliminarAlInicio();
        return toReturn;
    }
    
    
    /**
     * Retorna el primer elemento de la cola sin eliminarlo de la cola.
     * @return el primer elemento de la cola.
     */
    public T peek(){
        Nodo toReturn = super.getPrimero();
        if(toReturn == null)
            return null;
        return (T)super.primerElemento();
    }
    
    /**
     * Indica si la cola está vacía.
     * @return true si la cola está vacía, false en otro caso.
     */
    public boolean estaVacia(){
        return super.estaVacia();
    }
    
    /**
     * Elimina todos los elementos de la cola.
     */
    public void vaciar(){
        super.vaciar();
    }
}
