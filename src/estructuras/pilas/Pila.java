package estructuras.pilas;
import estructuras.listas.ListaEncadenadaSimple;

/**
 *La clase Pila hereda de la clase ListaEncadenadaSimple presentando el comportamiento LIFO.
 * @author Anderson
 * @param <T> Tipo de elementos que va a contener la Pila
 */
public class Pila<T> extends ListaEncadenadaSimple{
    /**
     * Construye una pila vacía.
     */
    public Pila(){
        super();
    }
    
    /**
     * Inserta el elemento en el inicio de la pila.
     * @param dato 
     */
    public void push(T dato){
        super.insertarAlInicio(dato);
    }
    
    /**
     * Retorna el primer elemento de la pila y lo elimina de la pila.
     * @return el primer elemento de la pila.
     */
    public T pop(){
        if(super.estaVacia()){
            System.out.println("La pila se encuentra vacía");
            return null;
        }
        T toReturn = (T) this.primerElemento();
        super.eliminarAlInicio();
        return toReturn;
    }
    
    /**
     * Retorna el primer elemento de la pila sin eliminarlo de la pila.
     * @return el primer elemento de la pila.
     */
    public T peek(){
        if(super.estaVacia()){
            System.out.println("La pila se encuentra vacía");
            return null;
        }
        return (T)super.primerElemento();
    }

    /**
     * Indica si la pila está vacía.
     * @return true si la cola está vacía, false en otro caso.
     */
    public boolean estaVacia(){
        return super.estaVacia();
    }

    /**
     * Elimina todos los elementos de la pila.
     */
    public void vaciar(){
        super.vaciar();
    }
}
