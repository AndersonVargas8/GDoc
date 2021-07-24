package gui.servicios.serviciosLogicos;

import datos.Documento;
import datos.Fecha;
import datos.Movimiento;
import estructuras.listas.ListaEncadenadaDoble;
import estructuras.listas.ListaEncadenadaSimple;
import logica.ControlMovimientos;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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

    public void agregarMovimiento(Movimiento movimiento){
        this.movimientos.insertarAlInicio(movimiento);
    }

    public void registrarMovimiento(int id, String tipo){
        Documento documento = DocumentosService.getServicio().getDocumento(id);
        Movimiento movimiento = new Movimiento();
        movimiento.setIdDocumento(id);
        movimiento.setTipoDocumento(documento.getTipo());
        movimiento.setNombreDocumento(documento.getNombre());
        movimiento.setUbicacionDocumento(documento.getEstante().concat("-"+documento.getCarpeta()));
        movimiento.setUsuario(UsuarioService.getServicio().getUsuarioConectado().getNombreUsuario());
        movimiento.setTipoMovimiento(tipo);
        movimiento.setFecha(new Fecha(FechaService.getServicio().getFechaCompleta().split("/")));
        agregarMovimiento(movimiento);
    }
}
