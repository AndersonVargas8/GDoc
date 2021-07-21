package estructuras.arboles;


/**
 *
 * @author Anderson Vargas
 */
public class ArbolBinarioBusqueda<T extends Comparable<? super T>> extends ArbolBinario<T>{
    public ArbolBinarioBusqueda(){
        super();
    }


    public void insertar(T dato){
        this.raiz = this.insertar(dato, super.getRaiz());
    }

    private NodoArbolBinario<T> insertar(T dato, NodoArbolBinario nodo){
        if(nodo == null)
            return new NodoArbolBinario(dato);
        int c = dato.compareTo((T)nodo.getDato());
        // -1: dato < nodo.dato
        // 0: dato == nodo.dato
        // 1: dato > nodo.dato
        if(c < 0)
            nodo.setHijoIzquierdo(insertar(dato, nodo.getHijoIzquierdo()));
        else if(c > 0)
            nodo.setHijoDerecho(insertar(dato,nodo.getHijoDerecho()));
        else
            System.out.println("El dato ya existe en el árbol");
        return nodo;
    }

    public void eliminar(T dato){
        this.raiz = eliminar(this.raiz, dato);
    }

    private NodoArbolBinario eliminar(NodoArbolBinario<T> raiz, T dato){
        if(raiz == null)
            return null;

        int comparacion = raiz.getDato().compareTo(dato);
        if(comparacion > 0)
            raiz.setHijoIzquierdo(this.eliminar(raiz.getHijoIzquierdo(),dato));
        else if(comparacion < 0)
            raiz.setHijoDerecho(this.eliminar(raiz.getHijoDerecho(),dato));
        else if(raiz.getHijoIzquierdo() != null && raiz.getHijoDerecho() != null){
            T sucesor = (T) this.buscarMin(raiz.getHijoDerecho());
            raiz.setDato(sucesor);
            raiz.setHijoDerecho(this.eliminar(raiz.getHijoDerecho(),sucesor));
        }
        else
            raiz = (raiz.getHijoIzquierdo() != null) ? raiz.getHijoIzquierdo(): raiz.getHijoDerecho();

        return raiz;
    }
    public boolean contiene(T busqueda){
        return contiene(this.raiz, busqueda);
    }

    private boolean contiene(NodoArbolBinario<T> raiz, T busqueda){
        if(raiz != null){
            int comparacion = raiz.getDato().compareTo(busqueda);
            if(comparacion == 0)
                return true;
            if(comparacion > 0)
                return contiene(raiz.getHijoIzquierdo(),busqueda);
            if(comparacion < 0)
                return contiene(raiz.getHijoDerecho(),busqueda);

        }
        return false;
    }

    public T buscarMin(){
        return buscarMin(this.raiz);
    }

    private T buscarMin(NodoArbolBinario<T> raiz){
        if(raiz == null)
            return null;
        if(raiz.getHijoIzquierdo() != null)
            return (T) buscarMin(raiz.getHijoIzquierdo());

        return raiz.getDato();
    }

    public T buscarMax(){
        return buscarMax(this.raiz);
    }

    private T buscarMax(NodoArbolBinario<T> raiz){
        if (raiz == null)
            return null;
        if(raiz.getHijoDerecho() != null)
            return (T) buscarMax(raiz.getHijoDerecho());

        return raiz.getDato();
    }

    public T buscar(T busqueda){
        return buscar(this.raiz, busqueda);
    }

    private T buscar(NodoArbolBinario<T> raiz, T busqueda){
        if(raiz == null)
            return null;
        if(busqueda.compareTo(raiz.getDato()) == 0)
            return raiz.getDato();
        if(busqueda.compareTo(raiz.getDato()) < 0){
            return (T) buscar(raiz.getHijoIzquierdo(), busqueda);
        }
        if(busqueda.compareTo(raiz.getDato()) > 0){
            return (T) buscar(raiz.getHijoDerecho(),busqueda);
        }
        return null;
    }

}
