
package logica;

import datos.*;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.colas.Cola;
import gui.servicios.serviciosLogicos.DocumentosService;

import java.io.*;
import java.time.ZonedDateTime;
/**
 *Esta clase permite manejar los archivos para mantener la persistencia de la información.
 * @author Anderson
 */
public class Archivo{
    /**
     * Guarda todos los elementos necesarios para que la información persista
     */
    public static void guardarDatos(){
        ControlDocumentos.guardarDatos();
        ControlMovimientos.guardarDatos();
        ControlPendientes.guardarDatos();
    }

}
