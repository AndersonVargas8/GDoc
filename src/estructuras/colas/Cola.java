package estructuras.colas;
import estructuras.listas.ListaEncadenadaDoble;

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

    /**
     * Construye una cola e inserta el elemento indicado.
     * @param dato
     */
    public Cola(T dato){
        this.enqueue(dato);
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
        if (this.estaVacia()){
            System.out.println("La cola se encuentra vacía");
            return null;
        }
        T aux = (T)super.primerElemento();
        super.eliminarAlInicio();
        return aux;
    }

    /**
     * Retorna el primer elemento de la cola sin eliminarlo de la cola.
     * @return el primer elemento de la cola.
     */
    public T peek(){
        if(super.estaVacia()) {
            System.out.println("La Pila se encuentra vacía");
            return null;
        }
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
