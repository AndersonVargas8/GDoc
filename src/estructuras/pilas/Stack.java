package estructuras.pilas;
import estructuras.arreglos.ArregloDinamico;
/**
 *Esta clase utiliza arreglos dinámicos para el manejo de los datos.
 * @author Anderson Vargas - anvargasa
 */
public class Stack<T>{
    private ArregloDinamico<T> arreglo;
    private int indice = 0;

    /**
     * Construye una pila vacía.
     */
    public Stack(){
        this.arreglo = new ArregloDinamico<T>();
    }

    /**
     * Constuye un pila e inserta el elemento indicado.
     * @param dato elemento con el que se construye la pila.
     */
    public Stack(T dato){
        this.arreglo = new ArregloDinamico<>();
        this.push(dato);
    }

    /**
     * Inserta el elemento indicado en la pila.
     * @param dato elemento a insertar. Este elemento será al que se tenga acceso.
     */
    public void push(T dato){
        this.arreglo.set(indice++, dato);
    }

    /**
     * Retorna y elimina el elemento de la pila que haya sido insertado más recientemente.
     * @return el elemento insertado más recientemente.
     */
    public T pop(){
        if(this.indice == 0){
            System.out.println("La pila está vacía");
            return null;
        }
        return this.arreglo.get(--indice);
    }

    /**
     * Retorna el elemento insertado más recientemente en la pila sin eliminarlo de la pila.
     * @return el elemento insertado más recientemente en la pila.
     */
    public T peek(){
        if(this.indice == 0){
            System.out.println("La pila está vacía");
            return null;
        }
        return this.arreglo.get(indice-1);
    }

    /**
     * Indica si la pila está vacía.
     * @return true si la pila no tiene elementos, false en otro caso.
     */
    public boolean estaVacia(){
        if(this.indice == 0){
            return true;
        }
        return false;
    }

    /**
     * Elimina todos los elementos de la pila.
     */
    public void vaciar(){
        this.indice = 0;
    }

    /**
     * Retorna un string con los elementos de la pila.
     * @return un string con los elementos de la pila.
     */
    @Override
    public String toString(){
        if(this.estaVacia()){
            return "La pila se encuentra vacía.";
        }
        String toReturn = "[";
        
        for(int i = this.indice-1; i >= 0; i--){
            toReturn = toReturn.concat(String.valueOf(this.arreglo.get(i)));
            if(i > 0){
                toReturn = toReturn.concat(", ");
            }
        }
        toReturn = toReturn.concat("]");
        return toReturn;
    }

    public int cantidadDeElementos(){
        return this.arreglo.getSize();
    }
}
