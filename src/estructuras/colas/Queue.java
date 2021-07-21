package estructuras.colas;
/**
 *Esta clase utiliza arreglos circulares para hacer el manejo de los datos.
 * @author Anderson Vargas - anvargasa
 */
public class Queue<T>{
    private T[] arreglo;
    private int back;
    private int front;
    private int cantidad;

    /**
     * Construye una cola vacía.
     */
    public Queue(){
        this.arreglo = (T[]) new Object[2];
        this.back = this.front = this.cantidad = 0;
    }

    /**
     * Insertar el dato indicado al final de la cola.
     * @param dato dato a ser insertado en la cola.
     */
    public void enqueue(T dato){
        if(back % arreglo.length == front % arreglo.length && cantidad != 0){
            this.aumentar();
        }

        if(this.cantidad == 0){
            this.front = this.back = 0;
            this.arreglo[back++] = dato;
        }else{
            this.arreglo[back++ % arreglo.length] = dato;
        }
        this.cantidad++;
    }

    /**
     * Retorna el primer elemento de la cola, eliminando el elemento de la cola.
     * @return el primer elemento de la cola.
     */
    public T dequeue(){
        if(this.cantidad == 0){
            System.out.println("La cola está vacía");

            return null;
        }
        T toReturn = this.arreglo[front++ % this.arreglo.length];
        this.cantidad--;
        return toReturn;
    }

    /**
     * Retorna el primer elemento de la cola sin eliminarlo de la cola.
     * @return el primer elemento de la cola.
     */
    public T peek(){
        return this.arreglo[front];
    }

    private void aumentar(){
        T[] aux = (T[]) new Object[this.cantidad*2];

        for (int i = 0; i < this.cantidad; i++) {
            aux[i] = this.arreglo[(front+i) % cantidad];
        }
        this.arreglo = aux;
        this.front = 0;
        this.back = this.cantidad;
    }

    /**
     * Retorna un string con los elementos de la cola.
     * @return un string con los elementos de la cola.
     */
    @Override
    public String toString(){
        if(this.estaVacia())
            return "La cola se encuentra vacía";
        String toReturn = "[";

        for(int i = 0; i < this.cantidad; i++){
            toReturn = toReturn.concat(String.valueOf(this.arreglo[(front + i) % this.arreglo.length]));
            if(i < this.cantidad - 1){
                toReturn = toReturn.concat(", ");
            }
        }
        toReturn = toReturn.concat("]");
        return toReturn;
    }

    /**
     * Informa si la cola está vacía.
     * @return true si la cola está vacía y false en caso contrario.
     */
    public boolean estaVacia(){
        if(this.cantidad == 0){
            return true;
        }

        return false;
    }

    /**
     * Elimina todos los elementos de la cola
     */
    public void vaciar(){
        this.front = this.back = this.cantidad = 0;
        this.arreglo = (T[]) new Object[2];
    }

    public int cantidadDeElementos(){
        return this.cantidad;
    }
}
