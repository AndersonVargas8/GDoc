package ListasEncadenadas;
/**
 * Esta clase hereda la clase ListaEncadenada, encadena todos sus nodos con un doble enlace
 * usando los atributos anterior y siguiente de la clase Nodo,
 * contiene una especificación al primer y al útlimo elemento.
 * @author Anderson Vargas - anvargasa
 * @param <T> Tipo de elementos que va a contener la lista
 */
public class ListaEncadenadaDoble<T> extends ListaEncadenada<T>{
    private Nodo ultimo;
    
    /**
     * Construye una lista encadenada doble vacía.
     */
    public ListaEncadenadaDoble() {
        super();
        this.ultimo = null;
    }
    
    /** 
     * Construye una lista encadenada doble insertando el pirmer elemento especificado
     * @param dato elemento a insertar al crear la lista.
     */
    public ListaEncadenadaDoble(T dato){
        super();
        this.insertarAlInicio(dato);
    }
    
    /**
     * Construye una lista y se insertan todos los elementos que contenga el arreglo especificado.
     * @param arreglo arreglo de elementos que serán insertados en la nueva lista.
     */
    public ListaEncadenadaDoble(T[] arreglo){
        for(int i = 0; i < arreglo.length; i++){
            this.insertar(i, arreglo[i]);
        }
    }
    
    /**
     * Retorna el último nodo de la lista
     * @return el último nodo de la lista.
     */
    protected Nodo getUltimo() {
        return ultimo;
    }
    
    /**
     * Reemplaza el últmo nodo de la lista por el nodo especificado.
     * @param ultimo Nodo que reemplazará el últmo de la lista.
     */
    protected void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
        this.ultimo.setSiguiente(null);
    }

    @Override
    public void insertarAlInicio(T dato){
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        
        if(this.estaVacia()){
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
            this.aumentarContador();
            return;
        }
        
        this.primero.setAnterior(nuevoNodo);
        nuevoNodo.setSiguiente(this.primero);
        this.primero = nuevoNodo;
        this.aumentarContador();
    }
    
    @Override
    public void insertarAlFinal(T dato){
        if(this.estaVacia()){
            this.insertarAlInicio(dato);
            return;
        }
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        
        this.ultimo.setSiguiente(nuevoNodo);
        nuevoNodo.setAnterior(this.ultimo);
        this.ultimo = nuevoNodo;
        this.aumentarContador();
    }
    
    @Override
    public void insertar(int indice, T dato){
        if(indice < 0 || indice > this.contador){
            System.out.println("ERROR, No se puede insertar un elemento en esa posición");
            return;
        }
        
        if(indice == 0){
            this.insertarAlInicio(dato);
            return;
        }
        
        if(indice == this.contador){
            this.insertarAlFinal(dato);
            return;
        }
        
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        
        Nodo nodoAntes = leer(indice-1);
        Nodo nodoDespues = leer(indice);
        nuevoNodo.setSiguiente(nodoDespues);
        nuevoNodo.setAnterior(nodoAntes);
        nodoAntes.setSiguiente(nuevoNodo);
        nodoDespues.setAnterior(nuevoNodo);
        this.aumentarContador();
        
    }
    
    @Override
    public void eliminarAlInicio(){
        if(this.estaVacia()){
            System.out.print("ERROR, no puede eliminar el elemento");
            return;
        }
        this.primero = this.primero.getSiguiente();
        this.decrementarContador();
    }
    

    @Override
    public void eliminarAlFinal(){
        if(this.estaVacia()){
            System.out.println("ERROR, no puede eliminar el elemento");
            return;
        }
        this.ultimo = this.ultimo.getAnterior();
        this.decrementarContador();
    }
    
    @Override
    public void eliminar(int indice){
        if(indice < 0  || indice >= this.contador){
            System.out.println("ERROR, no se puede eliminar el elemento de esa posición");
            return;
        }
        
        if(indice == 0){
            this.eliminarAlInicio();
            return;
        }
        
        if(indice == this.contador -1){
            this.eliminarAlFinal();
            return;
        }
        
        Nodo nodoAntes = leer(indice-1);
        Nodo nodoDespues = leer(indice+1);
        nodoAntes.setSiguiente(nodoDespues);
        nodoDespues.setAnterior(nodoAntes);
        this.decrementarContador();
    }
    
    @Override
    public String toString(){
        if(this.estaVacia()){
            return "La lista doble está vacía";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> aux = this.primero;
                
        while(aux != null){
            sb.append(aux.getDato().toString());
            sb.append(", ");
            aux = aux.getSiguiente();
        }
        String toReturn = sb.substring(0, sb.length()-2) + "]";
        return toReturn;
    }
    
    @Override
    public int[] buscar(T dato){
        int[] toReturn = new int[this.contador];
        if(this.estaVacia()){
            System.out.println("ERROR, La lista doble está vacía");
            return toReturn;
        }
        
        ListaEncadenadaDoble<Integer> indices = new ListaEncadenadaDoble<Integer>();
        Integer indice = 0;
        
        Nodo<T> aux = this.primero;
        
        while(aux != null){
            if(aux.getDato().equals(dato)){
                indices.insertarAlFinal(indice);
            }
            indice++;
            aux = aux.getSiguiente();
        }
        if(indices.estaVacia()){
            System.out.println("No se encontró el elemento");
            return toReturn;
        }
        
        for(int i = 0; i < indices.getContador();i++){
            toReturn[i] = indices.leerDato(i);
        }
        
        return toReturn;
    }

    @Override
    public T leerDato(int indice){
        if(estaVacia() || indice < 0 || indice>=this.contador)
            return null;
        T dato = (T) this.leer(indice).getDato();
        
        return dato;
    }
    
    private Nodo leer(int indice){
        if(indice < 0 || indice>= this.getContador()){
            System.out.println("ERROR, No se puede leer un elemento en esta posición");
            return null;
        }
        
        if(indice <= this.getContador()/2){
            Nodo aux = this.getPrimero();
        
            for (int i = 0; i < indice; i++) {
                aux = aux.getSiguiente();
            }
            return aux;
        }
        Nodo aux = this.ultimo;
        Integer i = this.getContador() - 1;
        
        while(i > indice){
            aux = aux.getAnterior();
            i--;
        }
        
        return aux;
    }
    
    @Override
    public void vaciar(){
        this.primero = null;
        this.ultimo = null;
        this.contador = 0;
    }

    @Override
    public T primerElemento(){
        return this.leerDato(0);
    }

    @Override
    public T ultimoElemento(){
        return this.leerDato(this.contador-1);
    }
    
    
}
