package logica;
/**
 * Esta clase hereda la clase ListaEncadenada, encadena todos sus nodos con un solo enlace,
 * usando el atributo siguiente de la clase Nodo,
 * contiene una especificación al primer elemento.
 * @author Anderson Vargas - anvargasa
 * @param <T> Tipo de elementos que va a contener la lista
 */
public class ListaEncadenadaSimple<T> extends ListaEncadenada<T> {
    
    /**
     * Consruye una lista vacía.
     */
    public ListaEncadenadaSimple(){}
    
    /**
     * Construye una lista y se insertan todos los elementos que contenga el arreglo especificado.
     * @param arreglo arreglo de elementos que serán insertados en la nueva lista.
     */
    public ListaEncadenadaSimple(T[] arreglo){
        for(int i = 0; i < arreglo.length; i++){
            this.insertar(i, arreglo[i]);
        }
    }
    @Override
    public void insertarAlInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.setSiguiente(this.getPrimero());
        this.setPrimero(nuevoNodo);
        this.aumentarContador();
    }
    
    @Override
    public void insertarAlFinal(T dato){
        Integer cont = this.getContador();
        this.insertar(cont, dato);
    }
    
    @Override
    public void insertar(int k, T dato) {
        if (k < 0 || k > this.getContador()) {
            System.out.println("ERROR,No es posible hacer la inserción en este índice");
            return;
        }

        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        if (k == 0) {
            insertarAlInicio(dato);
            return;
        }
        
        Nodo atras = leer(k-1);
        nuevoNodo.setSiguiente(atras.getSiguiente()); //Se asigna la referencia "siguiente" del nuevo nodo hacia el "siguiente" del nodo en la posición k-1
        atras.setSiguiente(nuevoNodo);  //se cambia la referencia "siguiente" del nodo en la posición k-1 hacia nuevo nodo
        this.aumentarContador();
    }

    @Override
    public void eliminarAlInicio() {
        if (this.estaVacia()) {
            System.out.print("ERROR, no puede eliminar el elemento");
            return;
        }
        this.setPrimero(this.getPrimero().getSiguiente());
        this.decrementarContador();
    }
    
    @Override
    public void eliminarAlFinal(){
        Integer cont = this.getContador() -1;
        this.eliminar(cont);
    }
    
    @Override
    public void eliminar(int k) {
        if (k < 0 || k >= this.getContador()) {
            System.out.println("ERROR, No es posible realizar la eliminación de esa posición");
            return;
        }

        if (k == 0) {
            this.eliminarAlInicio();
            return;
        }
        
        Nodo antes = leer(k-1);
        antes.setSiguiente(antes.getSiguiente().getSiguiente()); //se asigna la referencia "siguiente" del nodo en la posición k-1 hacia el nodo "siguiente" del de la posición k
        this.decrementarContador();
        return;
    }
    
    @Override
    public String toString() {
        if(this.estaVacia()){
            return "La lista simple está vacía";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> aux = this.getPrimero();

        while (aux != null) {
            sb.append(aux.getDato());
            sb.append(", ");
            aux = aux.getSiguiente();
        }
        String toReturn = sb.substring(0, sb.length() - 2) + "]";

        return toReturn;
    }

    @Override
    public void buscar(T dato) {
        if (this.estaVacia()) {
            System.out.print("ERROR, la lista simple está vacía");
            return;
        }

        Nodo<T> aux = this.getPrimero();
        ListaEncadenadaSimple<Integer> indices = new ListaEncadenadaSimple<Integer>();
        Integer indice = 0;

        while (aux != null) {
            if (dato.equals(aux.getDato())) {
                Integer cont = indices.getContador();
                indices.insertar(cont,indice);
            }
            indice++;
            aux = aux.getSiguiente();
        }
        if (indices.estaVacia()) {
            System.out.println("No se encontró el elemento");
            return;
        }

        System.out.println("Se encontró el elemento en las siguientes posiciones de la lista simple: " + indices.toString());
    }
    
    @Override
    public T leerDato(int k){
        if(estaVacia() || k < 0 || k>=this.getContador())
            return null;
        T dato = (T) this.leer(k).getDato();
        return dato;
    }
    
    private Nodo leer(int k) {
        if (k < 0 || k > this.getContador()) {
            System.out.println("ERROR, No es posible realizar la búsqueda");
            return null;
        }
        Nodo<T> aux = this.getPrimero();

        for (int i = 0; i < k; i++) {
            aux = aux.getSiguiente();
        }

        return aux;
    }
    
    @Override
    public void vaciar() {
        this.setContador(0);
        this.setPrimero(null);
    }
    
    public Nodo eliminarDuplicados(Nodo primero) {
        Nodo aux = primero.getSiguiente();
        while (aux != null) {
            if (primero.getDato() == aux.getDato()) {
                primero = primero.getSiguiente();
                aux = primero.getSiguiente();
                continue;
            }
            aux = aux.getSiguiente();
        }
        if (primero.getSiguiente() != null) {
            primero.setSiguiente(eliminarDuplicados(primero.getSiguiente()));
        }
        return primero;
    }
        
    @Override
    public T primerElemento(){
        return this.leerDato(0);
    }
    
    @Override
    public T ultimoElemento(){
        return this.leerDato(this.getContador()-1);
    }
}
