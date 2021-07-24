package gui.cliente.componentes.revision;

import gui.servicios.serviciosGraficos.GraficosAvanzadosService;
import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ProximosTemplate extends JPanel {
    private ProximosComponent proximosComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    //Objetos gráficos
    private JPanel pPendientes;
    private JButton bQuitar, bEliminar;
    private JComboBox cbCantidad;
    private JLabel lTitulo, lMostrar;

    //Objetos para JTable
    private JScrollPane pTabla;
    private JPanel pCorner;
    private JTable tabla;
    private JTableHeader header;
    private DefaultTableModel modelo;
    private String [] cabecera={"Id", "Tipo", "Nombre", "Ubicación", "Vencimiento"};

    public ProximosTemplate(ProximosComponent proximosComponent){
        this.proximosComponent = proximosComponent;

        //Obtención de servicios
        sRecursos = RecursosService.getServicio();
        sObjGraficos = ObjGraficosService.getServicio();
        sGraficosAvanzados = GraficosAvanzadosService.getServicio();

        //Configuración del componente
        setSize(950,440);
        setBackground(Color.cyan);
        setLayout(null);
        setVisible(true);
    }
}
