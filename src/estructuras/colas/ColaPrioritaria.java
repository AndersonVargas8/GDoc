package estructuras.colas;
import estructuras.arreglos.ArregloDinamico;

public class ColaPrioritaria<T extends Comparable <? super T>>{
    ArregloDinamico<T> arreglo;

    public ColaPrioritaria(){
        this.arreglo = new ArregloDinamico<>();
    }

    public void insertar(T dato){
        arreglo.pushBack(dato);
        heap(dato,arreglo.getSize()-1);
    }

    private void heap(T dato, int posicion){
        int pos_padre = (posicion - 1)/2;
        T padre = arreglo.get(pos_padre);
        if(padre.compareTo(dato) > 0){//Si el dato que llega como parámetro es menor que su padre
            arreglo.replace(pos_padre,dato);
            arreglo.replace(posicion,padre);
            heap(dato,pos_padre);
        }
    }

    public T eliminarMinimo(){
        if(arreglo.getSize() == 0){
            return null;
        }
        T toReturn = arreglo.get(0);
        T ultimo_elemento = arreglo.get(arreglo.getSize()-1);
        arreglo.replace(0,ultimo_elemento);
        arreglo.remove(arreglo.getSize()-1);
        downheap(arreglo.get(0),0);
        return toReturn;
    }

    private void downheap(T dato, int posicion){
        int pos_hijo_iz = posicion * 2 + 1, pos_hijo_der = posicion * 2 + 2;
        if(pos_hijo_iz >= arreglo.getSize() && pos_hijo_der >= arreglo.getSize())
            return;
        int menor = pos_hijo_der;
        if(arreglo.get(pos_hijo_iz).compareTo(arreglo.get(pos_hijo_der)) < 0){//Si el hijo izquierdo es menor que el hijo derecho
            menor = pos_hijo_iz;
        }

        T hijo_menor = arreglo.get(menor);
        if(hijo_menor.compareTo(dato) < 0){//Si el hijo menor es menor que el dato que llega como parámetro
            arreglo.replace(menor,dato);
            arreglo.replace(posicion,hijo_menor);

            downheap(dato,menor);
        }
    }
    public void imprimir(){
        for(int i = 0; i < arreglo.getSize(); i++){
            System.out.println(arreglo.get(i));
        }
    }

    public boolean estaVacia(){
        if(arreglo.getSize() == 0)
            return true;

        return false;
    }

    public int cantidadElementos(){
        return this.arreglo.getSize();
    }

    public T minimo(){
        if(this.arreglo.getSize() == 0)
            return null;
        return this.arreglo.get(0);
    }

    public void vaciar(){
        this.arreglo.vaciar();
    }
}
