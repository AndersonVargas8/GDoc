package gui.servicios.serviciosLogicos;

import datos.Movimiento;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.listas.ListaEncadenadaSimple;
import logica.ControlMovimientos;

public class MovimientosService {
    private static MovimientosService servicio;
    private ControlMovimientos cMovimientos;
    private ListaEncadenadaSimple<Movimiento> movimientos;

    public MovimientosService(){
        cMovimientos = new ControlMovimientos();
        movimientos = cMovimientos.getMovimientos();
    }

    public static MovimientosService getServicio(){
        if(servicio == null){
            servicio = new MovimientosService();
        }
        return servicio;
    }

    public ListaEncadenadaSimple imprimirTodo(){
        return movimientos;
    }
}
