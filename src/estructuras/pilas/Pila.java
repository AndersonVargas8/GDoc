package estructuras.pilas;

import estructuras.listas.ListaEncadenadaSimple;
import estructuras.listas.Nodo;

/**
 *La clase Pila hereda de la clase ListaEncadenadaSimple e implementa la funcionalidad LIFO. 
 * @author Anderson
 * @param <T> Tipo de elementos que va a contener la Pila
 */
public class Pila<T> extends ListaEncadenadaSimple {
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
        Nodo aux = super.getPrimero();
        if(aux == null)
            return null;
        T toReturn = (T) this.primerElemento();
        super.eliminarAlInicio();
        return toReturn;
    }
    
    /**
     * Retorna el primer elemento de la pila si eliminarlo de la pila.
     * @return el primer elemento de la pila.
     */
    public T peek(){
        if (this.getPrimero() == null)
            return null;

        return (T)this.getPrimero().getDato();
    }
    
}
