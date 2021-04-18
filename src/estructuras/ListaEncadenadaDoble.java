package logica;
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
     * Construye una clase vacía.
     */
    public ListaEncadenadaDoble() {
        super();
        this.ultimo = null;
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
    
    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
        this.ultimo.setSiguiente(null);
    }

    @Override
    public void insertarAlInicio(T dato){
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        
        if(this.estaVacia()){
            this.setPrimero(nuevoNodo);
            this.ultimo = nuevoNodo;
            this.aumentarContador();
            return;
        }
        
        this.getPrimero().setAnterior(nuevoNodo);
        nuevoNodo.setSiguiente(this.getPrimero());
        this.setPrimero(nuevoNodo);
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
    public void insertar(int k, T dato){
        if(k < 0 || k > this.getContador()){
            System.out.println("ERROR, No se puede insertar un elemento en esa posición");
            return;
        }
        
        if(k == 0){
            this.insertarAlInicio(dato);
            return;
        }
        
        if(k == this.getContador()){
            this.insertarAlFinal(dato);
            return;
        }
        
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        
        Nodo nodoAntes = leer(k-1);
        Nodo nodoDespues = leer(k);
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
        this.setPrimero(this.getPrimero().getSiguiente());
        this.decrementarContador();
    }
    

    @Override
    public void eliminarAlFinal(){
        if(this.estaVacia()){
            System.out.println("ERROR, no puede eliminar el elemento");
            return;
        }
        this.setUltimo(this.ultimo.getAnterior());
        this.decrementarContador();
    }
    
    @Override
    public void eliminar(int k){
        if(k < 0  || k >= this.getContador()){
            System.out.println("ERROR, no se puede eliminar el elemento de esa posición");
            return;
        }
        
        if(k == 0){
            this.eliminarAlInicio();
            return;
        }
        
        if(k == this.getContador() -1){
            this.eliminarAlFinal();
            return;
        }
        
        Nodo nodoAntes = leer(k-1);
        Nodo nodoDespues = leer(k+1);
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
        Nodo<T> aux = this.getPrimero();
                
        while(aux != null){
            sb.append(aux.getDato());
            sb.append(", ");
            aux = aux.getSiguiente();
        }
        String toReturn = sb.substring(0, sb.length()-2) + "]";
        return toReturn;
    }
    
    @Override
    public void buscar(T dato){
        if(this.estaVacia()){
            System.out.println("ERROR, La lista doble está vacía");
            return;
        }
        
        ListaEncadenadaDoble<Integer> indices = new ListaEncadenadaDoble<Integer>();
        Integer indice = 0;
        
        Nodo<T> aux = this.getPrimero();
        
        while(aux != null){
            if(aux.getDato().equals(dato)){
                indices.insertarAlFinal(indice);
            }
            indice++;
            aux = aux.getSiguiente();
        }
        if(indices.estaVacia()){
            System.out.println("No se encontró el elemento");
            return;
        }
            
        System.out.println("Se encontró el elemento en las siguientes posiciones de la lista doble: " + indices.toString());
    }

    @Override
    public T leerDato(int k){
        if(estaVacia() || k < 0 || k>=this.getContador())
            return null;
        T dato = (T) this.leer(k).getDato();
        
        return dato;
    }
    
    private Nodo leer(int k){
        if(k < 0 || k>= this.getContador()){
            System.out.println("ERROR, No se puede leer un elemento en esta posición");
            return null;
        }
        
        if(k <= this.getContador()/2){
            Nodo aux = this.getPrimero();
        
            for (int i = 0; i < k; i++) {
                aux = aux.getSiguiente();
            }
            return aux;
        }
        Nodo aux = this.ultimo;
        Integer i = this.getContador() - 1;
        
        while(i > k){
            aux = aux.getAnterior();
            i--;
        }
        
        return aux;
    }
    
    @Override
    public void vaciar(){
        this.setPrimero(null);
        this.ultimo = null;
        this.setContador(0);
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
