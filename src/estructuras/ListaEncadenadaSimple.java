package estructuras;

/**
 * Esta clase hereda la clase ListaEncadenada, encadena todos sus nodos con un
 * solo enlace, usando el atributo siguiente de la clase Nodo, contiene una
 * especificación al primer elemento.
 *
 * @author Anderson Vargas - anvargasa
 * @param <T> Tipo de elementos que va a contener la lista
 */
public class ListaEncadenadaSimple<T> extends ListaEncadenada<T> {
 
    /**
     *Construye una lista encadenada simple vacía.
     */
    public ListaEncadenadaSimple() {
       super();
    }
    
    /** 
     * Construye una lista encadenada simple insertando el pirmer elemento especificado
     * @param dato elemento a insertar al crear la lista.
     */
    public ListaEncadenadaSimple(T dato){
        super();
        this.insertarAlInicio(dato);
    }
    /**
     * Construye una lista y se insertan todos los elementos que contenga el
     * arreglo especificado.
     *
     * @param arreglo arreglo de elementos que serán insertados en la nueva
     * lista.
     */
    public ListaEncadenadaSimple(T[] arreglo) {
        this.primero = super.primero;
        for (int i = 0; i < arreglo.length; i++) {
            this.insertar(i, arreglo[i]);
        }
    }

    @Override
    public void insertarAlInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.setSiguiente(this.primero);
        this.primero = nuevoNodo;
        this.aumentarContador();
    }

    @Override
    public void insertarAlFinal(T dato) {
        Integer cont = this.contador;
        this.insertar(cont, dato);
    }

    @Override
    public void insertar(int indice, T dato) {
        if (indice < 0 || indice > this.contador) {
            System.out.println("ERROR,No es posible hacer la inserción en este índice");
            return;
        }

        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        if (indice == 0) {
            insertarAlInicio(dato);
            return;
        }

        Nodo atras = leer(indice - 1);
        nuevoNodo.setSiguiente(atras.getSiguiente()); //Se asigna la referencia "siguiente" del nuevo nodo hacia el "siguiente" del nodo en la posición indice-1
        atras.setSiguiente(nuevoNodo);  //se cambia la referencia "siguiente" del nodo en la posición indice-1 hacia nuevo nodo
        this.aumentarContador();
    }

    @Override
    public void eliminarAlInicio() {
        if (this.estaVacia()) {
            System.out.print("ERROR, no puede eliminar el elemento");
            return;
        }
        this.primero = this.getPrimero().getSiguiente();
        this.decrementarContador();
    }

    @Override
    public void eliminarAlFinal() {
        Integer cont = this.contador - 1;
        this.eliminar(cont);
    }

    @Override
    public void eliminar(int indice) {
        if (indice < 0 || indice >= this.contador) {
            System.out.println("ERROR, No es posible realizar la eliminación de esa posición");
            return;
        }

        if (indice == 0) {
            this.eliminarAlInicio();
            return;
        }

        Nodo antes = leer(indice - 1);
        antes.setSiguiente(antes.getSiguiente().getSiguiente()); //se asigna la referencia "siguiente" del nodo en la posición indice-1 hacia el nodo "siguiente" del de la posición indice
        this.decrementarContador();
        return;
    }

    @Override
    public String toString() {
        if (this.estaVacia()) {
            return "La lista simple está vacía";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> aux = this.primero;

        while (aux != null) {
            sb.append(aux.getDato());
            sb.append(", ");
            aux = aux.getSiguiente();
        }
        String toReturn = sb.substring(0, sb.length() - 2) + "]";

        return toReturn;
    }

    @Override
    public int buscar(T dato) {
        int toReturn = -1;
        if (this.estaVacia()) {
            System.out.print("ERROR, la lista simple está vacía");
            return toReturn;
        }

        Nodo<T> aux = this.primero;
        ListaEncadenadaSimple<Integer> indices = new ListaEncadenadaSimple<Integer>();
        Integer indice = 0;

        while (aux != null) {
            if (dato.equals(aux.getDato())) {
                Integer cont = indices.contador;
                indices.insertar(cont, indice);
                toReturn = indice;
            }
            indice++;
            aux = aux.getSiguiente();
        }
        if (indices.estaVacia()) {
            System.out.println("No se encontró el elemento");
            return toReturn;
        }
        
        
        return toReturn;
    }

    @Override
    public T leerDato(int indice) {
        if (estaVacia() || indice < 0 || indice >= this.contador) {
            return null;
        }
        T dato = (T) this.leer(indice).getDato();
        return dato;
    }

    private Nodo leer(int indice) {
        if (indice < 0 || indice > this.contador) {
            System.out.println("ERROR, No es posible realizar la búsqueda");
            return null;
        }
        Nodo<T> aux = this.primero;

        for (int i = 0; i < indice; i++) {
            aux = aux.getSiguiente();
        }

        return aux;
    }

    @Override
    public void vaciar() {
        this.contador = 0;
        this.primero = null;
    }
    

    @Override
    public T primerElemento() {
        return this.leerDato(0);
    }
    
    @Override
    public T ultimoElemento() {
        return this.leerDato(this.contador - 1);
    }
    
    
}
