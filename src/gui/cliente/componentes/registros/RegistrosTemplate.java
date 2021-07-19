package gui.cliente.componentes.registros;

import javax.swing.*;
import java.awt.*;

public class RegistrosTemplate extends JPanel {
    private RegistrosComponent registrosComponent;

    public RegistrosTemplate(RegistrosComponent registrosComponent){
        this.registrosComponent = registrosComponent;

        //Configuración del componente
        setSize(950,650);
        setBackground(Color.yellow);
        setLayout(null);
        setVisible(true);
    }
}
