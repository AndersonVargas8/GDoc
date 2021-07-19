package gui.cliente.componentes.revision;

import javax.swing.*;
import java.awt.*;

public class RevisionTemplate extends JPanel {
    private RevisionComponent revisionComponent;

    public RevisionTemplate(RevisionComponent revisionComponent){
        this.revisionComponent = revisionComponent;

        //Configuración del componente
        setSize(950,650);
        setBackground(Color.red);
        setLayout(null);
        setVisible(true);
    }
}
