package estructuras.arboles;

public class AVL <T extends Comparable<? super T>> extends ArbolBinarioBusqueda<T>{

    public void insertar(T dato){
        super.insertar(dato);

        this.raiz = balancear(this.raiz);
    }

    public void eliminar(T dato){
        super.eliminar(dato);

        this.raiz = balancear(this.raiz);
    }

    private int factorEquilibrio(NodoArbolBinario<T> raiz) {
        return altura(raiz.getHijoIzquierdo()) - altura(raiz.getHijoDerecho());
    }
    private boolean estaBalanceado(NodoArbolBinario<T> nodo){
        if (Math.abs(factorEquilibrio(nodo)) < 2)
            return true;

        return false;
    }

    private boolean hijoEstaBalanceado(NodoArbolBinario<T> nodo){
        int difAltura = factorEquilibrio(nodo);
        if(difAltura == 0)
            return true;
        if(Math.abs(difAltura) == 2)
            return false;
        if(difAltura == 1)
            return hijoEstaBalanceado(nodo.getHijoIzquierdo());
        if(difAltura == -1)
            return hijoEstaBalanceado(nodo.getHijoDerecho());

        return true;
    }
    private NodoArbolBinario balancear(NodoArbolBinario<T> raiz){
        int difAltura = factorEquilibrio(raiz);
        if(!estaBalanceado(raiz)){
            boolean hijosBalanceados;
            if (difAltura > 0){
                hijosBalanceados =  hijoEstaBalanceado(raiz.getHijoIzquierdo());

                if(!hijosBalanceados){
                    raiz.setHijoIzquierdo(balancear(raiz.getHijoIzquierdo()));
                    return raiz;
                }
            }
            if(difAltura < 0) {
                hijosBalanceados = hijoEstaBalanceado(raiz.getHijoDerecho());
                if(!hijosBalanceados){
                    raiz.setHijoDerecho(balancear(raiz.getHijoDerecho()));
                    return raiz;
                }
            }

            if(difAltura == 2){
                int difAlturaIz = factorEquilibrio(raiz.getHijoIzquierdo());

                if(difAlturaIz == 1 || difAlturaIz == 0){
                    return rotacionSimpleDerecha(raiz);
                }

                if(difAlturaIz == -1){
                    return rotacionDobleDerecha(raiz);
                }
            }
            if(difAltura == -2){
                int difAlturaDer = factorEquilibrio(raiz.getHijoDerecho());

                if(difAlturaDer == -1 || difAlturaDer == 0){
                    return rotacionSimpleIzquierda(raiz);
                }
                if(difAlturaDer == 1){
                    return rotacionDobleIzquierda(raiz);
                }
            }
        }

        if(difAltura > 0){
            raiz.setHijoIzquierdo(balancear(raiz.getHijoIzquierdo()));
        }
        if(difAltura < 0){
            raiz.setHijoDerecho(balancear(raiz.getHijoDerecho()));
        }
        return raiz;
    }

    private NodoArbolBinario rotacionSimpleDerecha(NodoArbolBinario<T> nodo1){
        NodoArbolBinario nodo2 = nodo1.getHijoIzquierdo();
        nodo1.setHijoIzquierdo(nodo2.getHijoDerecho());
        nodo2.setHijoDerecho(nodo1);
        return nodo2;
    }

    private NodoArbolBinario rotacionSimpleIzquierda(NodoArbolBinario<T> nodo1){
        NodoArbolBinario nodo2 = nodo1.getHijoDerecho();
        nodo1.setHijoDerecho(nodo2.getHijoIzquierdo());
        nodo2.setHijoIzquierdo(nodo1);
        return nodo2;
    }

    private NodoArbolBinario rotacionDobleDerecha(NodoArbolBinario<T> nodo1){
        nodo1.setHijoIzquierdo(rotacionSimpleIzquierda(nodo1.getHijoIzquierdo()));
        return rotacionSimpleDerecha(nodo1);
    }

    private NodoArbolBinario rotacionDobleIzquierda(NodoArbolBinario<T> nodo1){
        nodo1.setHijoDerecho(rotacionSimpleDerecha(nodo1.getHijoDerecho()));
        return rotacionSimpleIzquierda(nodo1);
    }

}
