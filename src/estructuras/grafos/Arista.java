package estructuras.grafos;

public class Arista implements Comparable<Arista>{
    private Vertice inicial;
    private Vertice terminal;
    private int peso;

    public Arista(Vertice inicial, Vertice terminal, int peso){
        this.inicial = inicial;
        this.terminal = terminal;
        this.peso = peso;
    }

    public int getPeso(){
        return peso;
    }

    public void setPeso(int peso){
        this.peso = peso;
    }

    public Vertice getInicial(){
        return inicial;
    }

    public void setInicial(Vertice inicial){
        this.inicial = inicial;
    }

    public Vertice getTerminal(){
        return terminal;
    }

    public void setTerminal(Vertice terminal){
        this.terminal = terminal;
    }

    @Override
    public String toString(){
        return "(" + inicial + "," + terminal + "," + peso + ")";
    }

    @Override
    public int compareTo(Arista arista) {
        return (int)(this.peso - arista.peso);
    }
}
