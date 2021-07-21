package gui.servicios.serviciosLogicos;

import java.util.Calendar;

public class FechaService {
    private static FechaService servicio;
    private Calendar calendario;
    private String dia;
    private  String mes;
    private String annio;
    private String fecha;

    public FechaService(){
        calendario = Calendar.getInstance();
        dia = Integer.toString(calendario.get(Calendar.DATE));
        mes = Integer.toString(calendario.get(Calendar.MONTH)+1);
        annio = Integer.toString(calendario.get(Calendar.YEAR));
        fecha = dia.concat("/" + mes + "/" + annio);
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
        return fecha;
    }

    public String getFechaPlus(String fecha, int i){
        String[] fechaAux = fecha.split("/");
        String dia = fechaAux[0];
        String mes = fechaAux[1];
        String annio = fechaAux[2];
        return dia.concat("/" + mes + "/" + String.valueOf(Integer.parseInt(annio) + i));
    }
}
