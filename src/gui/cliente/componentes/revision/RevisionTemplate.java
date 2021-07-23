package gui.cliente.componentes.revision;

import gui.servicios.serviciosGraficos.GraficosAvanzadosService;
import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import java.awt.*;

public class  RevisionTemplate extends JPanel {
    //Servicios
    private RevisionComponent revisionComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    //Objetos gráficos
    JPanel pVencidos;


    public RevisionTemplate(RevisionComponent revisionComponent){
        this.revisionComponent = revisionComponent;
        sRecursos = RecursosService.getServicio();
        //Configuración del componente
        setSize(950,650);
        setBackground(sRecursos.getColorGrisClaro());
        setLayout(null);
        setVisible(true);
    }
}
