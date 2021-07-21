package datos;

public class Fecha {
    private int dia;
    private int mes;
    private int annio;

    public Fecha(){

    }

    public Fecha(int dia, int mes, int annio){
        this.dia = dia;
        this.mes = mes;
        this.annio = annio;
    }

    public Fecha(String[] fecha){
        this.dia = Integer.parseInt(fecha[0]);
        this.mes = Integer.parseInt(fecha[1]);
        this.annio = Integer.parseInt(fecha[2]);
    }
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnnio() {
        return annio;
    }

    public void setAnnio(int annio) {
        this.annio = annio;
    }

    @Override
    public String toString(){
        String dia = String.valueOf(this.dia);
        String mes = String.valueOf(this.mes);
        String annio = String.valueOf(this.annio);

        return dia.concat("/" + mes + "/" + annio);
    }
}
