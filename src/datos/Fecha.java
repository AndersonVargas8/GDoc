package datos;

public class Fecha {
    private int dia,mes,annio,hora,minuto,segundo;


    public Fecha(int dia, int mes, int annio){
        this.dia = dia;
        this.mes = mes;
        this.annio = annio;
        this.hora = this.minuto = this.segundo = -1;
    }

    public Fecha(){
        this.dia = this. mes = this.annio = this.hora = this.minuto = this.segundo = -1;
    }

    public Fecha(String[] fecha){
        this.dia = Integer.parseInt(fecha[0]);
        this.mes = Integer.parseInt(fecha[1]);
        this.annio = Integer.parseInt(fecha[2]);
        if(fecha.length > 3){
            String[] datosHora = fecha[3].split(":");
            this.hora = Integer.parseInt(datosHora[0]);
            this.minuto = Integer.parseInt(datosHora[1]);
            this.segundo = Integer.parseInt(datosHora[2]);
        }else{
            this.hora = this.minuto = this.segundo = -1;
        }

    }
    private String completar(String dato){
        if(dato.length() == 1)
            dato = "0".concat(dato);

        return dato;
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

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    @Override
    public String toString(){
        String dia = completar(String.valueOf(this.dia));
        String mes = completar(String.valueOf(this.mes));
        String annio = String.valueOf(this.annio);

        String toReturn = dia.concat("/" + mes + "/" + annio);

        /*if(hora != -1){
            String hora = String.valueOf(this.hora);
            String minuto = String.valueOf(this.minuto);
            String segundo = String.valueOf(this.segundo);

            toReturn = toReturn.concat("/" + hora + ":" + minuto + segundo);
        }*/

        return toReturn;
    }

    public String getHoraCompleta(){
        String toReturn = "";
        if(hora != -1) {
            String hora = String.valueOf(this.hora);
            String minuto = completar(String.valueOf(this.minuto));
            String segundo = completar(String.valueOf(this.segundo));

            toReturn = hora + ":" + minuto + ":" + segundo;
        }
        return toReturn;
    }
}
