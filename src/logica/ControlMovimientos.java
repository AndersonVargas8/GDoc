package logica;

import datos.Fecha;
import datos.Movimiento;
import estructuras.listas.ListaEncadenadaSimple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
                movimiento.setUsuario(atributos[1]);
                movimiento.setTipoMovimiento(atributos[2]);
                movimiento.setFecha(new Fecha(atributos[3].split("/")));

                movimientos.insertarAlInicio(movimiento);
            }
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ListaEncadenadaSimple<Movimiento> getMovimientos(){
        return this.movimientos;
    }
}
