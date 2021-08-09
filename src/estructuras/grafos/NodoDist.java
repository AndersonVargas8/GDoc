package estructuras.grafos;

public class NodoDist implements Comparable<NodoDist>{
    private Vertice vertice;
    private int peso;
    private String intermedio;
    private boolean conocido;

    public NodoDist(Vertice vertice, int peso, String intermedio, boolean conocido) {
        this.vertice = vertice;
        this.peso = peso;
        this.intermedio = intermedio;
        this.conocido = conocido;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getIntermedio() {
        return intermedio;
    }

    public void setIntermedio(String intermedio) {
        this.intermedio = intermedio;
    }

    public boolean isConocido() {
        return conocido;
    }

    public void setConocido(boolean conocido) {
        this.conocido = conocido;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }

    @Override
    public int compareTo(NodoDist nodoDist) {
       return (int)(this.peso - nodoDist.peso);
    }

    @Override
    public String toString() {
        return this.vertice.getNombre();
    }
}