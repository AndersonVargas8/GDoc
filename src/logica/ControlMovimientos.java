package logica;

import datos.Documento;
import datos.Fecha;
import datos.Movimiento;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.listas.ListaEncadenadaSimple;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.MovimientosService;

import java.io.*;

public class ControlMovimientos {
    private ListaEncadenadaSimple<Movimiento> movimientos;

    public ControlMovimientos(){
        this.movimientos = new ListaEncadenadaSimple<>();
        cargarDatos();
    }

    public void cargarDatos(){
        File archivo;
        FileReader fr;
        BufferedReader br;

        try{
            archivo = new File("src/archivos/movimientos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;

            while((linea = br.readLine()) != null){
                String[] atributos = linea.split(",");

                Movimiento movimiento = new Movimiento();
                movimiento.setIdDocumento(Integer.parseInt(atributos[0]));
                movimiento.setTipoDocumento(atributos[1]);
                movimiento.setNombreDocumento(atributos[2]);
                movimiento.setUbicacionDocumento(atributos[3]);
                movimiento.setTipoMovimiento(atributos[4]);
                movimiento.setFecha(new Fecha(atributos[5].split("/")));
                movimiento.setUsuario(atributos[6]);

                movimientos.insertarAlFinal(movimiento);
            }
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void guardarDatos(){
        ListaEncadenadaSimple<Movimiento> movimientos = MovimientosService.getServicio().imprimirTodo();
        FileWriter fw = null;
        try{
            fw = new FileWriter("src/archivos/movimientos.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Movimiento mov: movimientos)
                bw.write(mov.getIdDocumento() + ","
                        + mov.getTipoDocumento() + ","
                        + mov.getNombreDocumento() + ","
                        + mov.getUbicacionDocumento() + ","
                        + mov.getTipoMovimiento() + ","
                        + mov.getFecha().toString() + "/" + mov.getFecha().getHoraCompleta() + ","
                        + mov.getUsuario() + "\n");

            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fw != null){
                try {
                    fw.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    public ListaEncadenadaSimple<Movimiento> getMovimientos(){
        return this.movimientos;
    }
}
