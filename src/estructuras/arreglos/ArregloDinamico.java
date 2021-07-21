package estructuras.arreglos;

/**
 * Esta clase proporciona un arreglo dinámico el cual modifica su tamaño al doble
 * cuando está lleno de elementos y se desea insertar uno nuevo.
 * @author Anderson
 * @param <T> Tipo de datos de los elementos a almacenar en el arreglo.
 */
public class ArregloDinamico<T>{

    protected T[] arreglo;
    private int capacity= 0;
    private int size = 0;
    private final int FACTOR_AUMENTO = 2;

    /**
     * Constuye un arreglo dinámico vacío con capacidad 2.
     */
    public ArregloDinamico(){
        this.arreglo = (T[]) new Object[2];
        this.capacity = 2;
        this.size = 0;
    }

    /**
     * Construye un arreglo dinámico con capacidad 2 e inserta el dato en la posición 0.
     * @param dato
     */
    public ArregloDinamico(T dato){
        arreglo = (T[]) new Object[2];
        arreglo[0] = dato;

        this.capacity = 2;
        this.size = 1;
    }

    /**
     * Inserta el dato en el índice indicado y desplaza los elementos hacia adelante.
     * @param indice
     * @param dato
     */
    public void set(int indice, T dato){
        if(this.size == this.capacity){
            this.aumentar();
        }
        for(int i = this.size; i > indice; i--){
            this.arreglo[i] = this.arreglo[i-1];
        }
        this.arreglo[indice] = dato;
        this.size++;
    }

    /**
     * Reemplaza el elemento en el índice indicado por el dato indicado;
     * @param indice
     * @param dato
     */
    public void replace(int indice, T dato){
        this.arreglo[indice] = dato;
    }

    /**
     * Retorna el elemento ubicado en el índice indicado.
     * @param indice
     * @return el elemento ubicado en el índice indicado.
     */
    public T get(int indice){
        T toReturn = (T) this.arreglo[indice];
        return toReturn;
    }

    /**
     * Inserta el dato en la siguiente posición libre del arreglo;
     * @param dato
     */
    public void pushBack(T dato){
        if(this.size == this.capacity){
            this.aumentar();
        }
        this.arreglo[this.size++] = dato;
    }

    public void remove(int indice){
        for(int i = indice; i < this.size-1; i++){
            this.arreglo[i] = this.arreglo[i+1];
        }
        this.size--;
    }

    /**
     * Retorna la capacidad de almacenamiento actual del arreglo.
     * @return la capacidad de almecenamiento actual del arreglo.
     */
    public int getCapacity(){
        return this.capacity;
    }


    /**
     * Retorna la cantidad de elementos del arreglo.
     * @return la cantidad de elementos del arreglo.
     */
    public int getSize(){return this.size;}

    /**
     * Retorna un String con el contenido del arreglo.
     * @return un String con el contenido del arreglo.
     */
    @Override
    public String toString(){
        String toReturn = "[";

        for(int i = 0; i < this.size; i++){
            toReturn = toReturn.concat(String.valueOf(this.arreglo[i]));
            if(i < this.size-1){
                toReturn = toReturn.concat(", ");
            }
        }
        toReturn = toReturn.concat("]");
        return toReturn;
    }

    private void aumentar(){
        T[] array = (T[]) new Object[this.capacity*FACTOR_AUMENTO];
        for(int i = 0; i < this.arreglo.length; i++){
            array[i] = this.arreglo[i];
        }
        this.arreglo = array;
        this.capacity *= FACTOR_AUMENTO;
    }
}
