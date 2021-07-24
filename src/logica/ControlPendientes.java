package logica;

import datos.Documento;
import datos.Fecha;
import datos.Movimiento;
import estructuras.arboles.AVL;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.listas.ListaEncadenadaSimple;
import gui.servicios.serviciosLogicos.MovimientosService;
import gui.servicios.serviciosLogicos.PendientesService;

import java.io.*;

public class ControlPendientes {
    private AVL<Integer> pendientes;

    public ControlPendientes(){
        this.pendientes = new AVL<>();
        cargarDatos();
    }

    public void cargarDatos() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("src/archivos/pendientes.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                pendientes.insertar(Integer.parseInt(linea));
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void guardarDatos(){
        ListaEncadenadaDoble<Integer> pendientes = PendientesService.getServicio().getPendientes();
        FileWriter fw = null;
        try{
            fw = new FileWriter("src/archivos/pendientes.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Integer pendiente: pendientes)
                bw.write(pendiente + "\n");

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

    public AVL<Integer> getPendientes(){
        return this.pendientes;
    }
}
