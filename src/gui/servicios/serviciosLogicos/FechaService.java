package gui.servicios.serviciosLogicos;

import java.util.Calendar;

public class FechaService {
    private static FechaService servicio;
    private Calendar calendario;
    private String dia,mes,annio,hora,minuto,segundo,fecha,fechaCompleta;


    public FechaService(){
        obtenerFecha();
    }

    private void obtenerFecha(){
        calendario = Calendar.getInstance();
        dia = completar(Integer.toString(calendario.get(Calendar.DATE)));
        mes = completar(Integer.toString(calendario.get(Calendar.MONTH)+1));
        annio = Integer.toString(calendario.get(Calendar.YEAR));
        hora = Integer.toString(calendario.get(Calendar.HOUR));
        minuto = completar(Integer.toString(calendario.get(Calendar.MINUTE)));
        segundo = completar(Integer.toString(calendario.get(Calendar.SECOND)));
        fecha = dia.concat("/" + mes + "/" + annio);
        fechaCompleta = fecha.concat("/" + hora + ":" + minuto + ":" + segundo);
    }
    private String completar(String dato){
        if(dato.length() == 1)
            dato = "0".concat(dato);

        return dato;
    }
    public static FechaService getServicio() {
        if(servicio == null)
            servicio = new FechaService();
        return servicio;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAnnio() {
        return annio;
    }

    public String getFecha() {
        obtenerFecha();
        return fecha;
    }

    public String getFechaCompleta(){
        obtenerFecha();
        return fechaCompleta;
    }

    public String getFechaPlus(String fecha, int i){
        String[] fechaAux = fecha.split("/");
        String dia = fechaAux[0];
        String mes = fechaAux[1];
        String annio = fechaAux[2];
        return dia.concat("/" + mes + "/" + String.valueOf(Integer.parseInt(annio) + i));
    }
}
