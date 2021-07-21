package estructuras.colas;
import estructuras.listas.ListaEncadenadaSimple;

public class PriorityQueue<T extends Comparable <? super T>>{
    ListaEncadenadaSimple<T> lista;

    public PriorityQueue(){
        this.lista  = new ListaEncadenadaSimple<>();
    }

    public void insertar(T dato){
        lista.insertarAlFinal(dato);
        heap(dato,lista.cantidadDeElementos()-1);
    }

    private void heap(T dato, int posicion){
        int pos_padre = (posicion - 1)/2;
        T padre = lista.leerDato(pos_padre);
        if(padre.compareTo(dato) > 0){//Si el dato que llega como parámetro es menor que su padre
            lista.reemplazar(dato,pos_padre);
            lista.reemplazar(padre,posicion);
            heap(dato,pos_padre);
        }
    }

    public T eliminarMinimo(){
        T toReturn = lista.leerDato(0);
        T ultimo_elemento = lista.leerDato(lista.cantidadDeElementos()-1);
        lista.reemplazar(ultimo_elemento,0);
        lista.eliminarAlFinal();
        downheap(lista.leerDato(0),0);
        return toReturn;
    }

    private void downheap(T dato, int posicion){
        int pos_hijo_iz = posicion * 2 + 1, pos_hijo_der = posicion * 2 + 2;
        if(pos_hijo_iz >= lista.cantidadDeElementos() && pos_hijo_der >= lista.cantidadDeElementos())
            return;
        int mayor = pos_hijo_der;
        if(lista.leerDato(pos_hijo_iz).compareTo(lista.leerDato(pos_hijo_der)) == 1){//Si el hijo izquierdo es mayor que el hijo derecho
            mayor = pos_hijo_iz;
        }

        T hijo_mayor = lista.leerDato(mayor);
        if(hijo_mayor.compareTo(dato) == 1){//Si el hijo mayor es mayor que el dato que llega como parámetro
            lista.reemplazar(dato,mayor);
            lista.reemplazar(hijo_mayor,posicion);

            downheap(dato,mayor);
        }
    }
    public void imprimir(){
        for(int i = 0; i < lista.cantidadDeElementos(); i++){
            System.out.println(lista.leerDato(i));
        }
    }

    public boolean estaVacia(){
       return lista.estaVacia();
    }

    public T minimo(){
        return this.lista.primerElemento();
    }
}
