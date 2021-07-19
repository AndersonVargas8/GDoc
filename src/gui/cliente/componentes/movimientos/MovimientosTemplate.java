package gui.cliente.componentes.movimientos;

import javax.swing.*;
import java.awt.*;

public class MovimientosTemplate extends JPanel {

    //Servicios
    private MovimientosComponent movimientosComponent;

    public MovimientosTemplate(MovimientosComponent movimientosComponent){
        this.movimientosComponent = movimientosComponent;

        //Configuración del componente
        setSize(950,650);
        setBackground(Color.blue);
        setLayout(null);
        setVisible(true);
    }
}
