package gui.cliente.componentes.movimientos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovimientosComponent{
    private MovimientosTemplate movimientosTemplate;

    public MovimientosComponent(){
        this.movimientosTemplate = new MovimientosTemplate(this);

    }

    public MovimientosTemplate getMovimientosTemplate() {
        return movimientosTemplate;
    }

}
