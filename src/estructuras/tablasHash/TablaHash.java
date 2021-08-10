package estructuras.tablasHash;

import java.io.Serializable;

public class TablaHash<T, V> implements Serializable {
    private int capacidad;
    private int contador;
    private NodoHash<T,V>[] tabla;

    public TablaHash(){
        this.capacidad = 2;
        this.contador = 0;
        this.tabla = new NodoHash[capacidad];
    }

    public TablaHash(int capacidad){
        this.capacidad = capacidad;
        this.contador = 0;
        this.tabla = new NodoHash[capacidad];
    }
    private int hash(T clave){
        return Math.abs(clave.hashCode()) % capacidad;
    }
    
    public void insertar(T clave, V valor){
        if(contador == capacidad / 2)
            redimensionar(this.capacidad * 2);
        int indice = buscarIndice(clave);

        if(tabla[indice] == null){
            tabla[indice] = new NodoHash<>(clave,valor);
            contador++;
            return;
        }

        tabla[indice].setValor(valor);
    }

    public V leer(T clave){
        int indice = buscarIndice(clave);

        if(tabla[indice] == null) {
            System.out.println("El dato no existe");
            return null;
        }
        return tabla[indice].getValor();
    }

    public void eliminar(T clave){
        int indice = buscarIndice(clave);

        if(tabla[indice] == null)
            return;

        tabla[indice] = null;
        contador--;
    }

    private int buscarIndice(T clave){
        int indice = hash(clave);
        int i = 1;
        while(tabla[indice] != null && !clave.equals(tabla[indice].getClave())){
            indice = (hash(clave) + i*i) % capacidad;
            i++;
            if(i == this.capacidad)
                return -1;
        }
        return indice;
    }
    
    private void redimensionar(int nuevaCapacidad){
        TablaHash<T,V> nuevaHash = new TablaHash<>(nuevaCapacidad);

        for(int i = 0; i < capacidad; i++){
            if(this.tabla[i] != null)
                nuevaHash.insertar(this.tabla[i].getClave(),this.tabla[i].getValor());
        }

        this.tabla = nuevaHash.tabla;
        this.capacidad = nuevaCapacidad;
    }

    public int getCapacidad(){
        return this.capacidad;
    }

    public int cantidadElementos(){
        return this.contador;
    }

    public boolean contiene(T clave){
        int indice = buscarIndice(clave);

        if(indice != -1 && tabla[indice] != null){
            if(tabla[indice].getClave().equals(clave))
                return true;
        }

        return false;
    }
}
