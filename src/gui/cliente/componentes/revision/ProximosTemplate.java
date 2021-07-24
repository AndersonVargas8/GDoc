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
    private JButton bEnviar;
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

        //Creación de objetos
        crearObjetosAdicionales();
        crearJTable();

        //Configuración del componente
        setSize(930,440);
        setBackground(Color.white);
        setBorder(sRecursos.getBordePanels());
        setLayout(null);
        setVisible(true);
    }

    public void crearObjetosAdicionales(){
        // LABEL TITULO--------------------------------------------------------------------
        lTitulo = sObjGraficos.construirJLabel(
                "Próximos documentos a vencer: ", 20, 10, 300, 30, null, null,
                sRecursos.getFuenteTitulo(), null, sRecursos.getColorGrisOscuro(), null, "c"
        );
        this.add(lTitulo);

        // BOTÓN QUITAR--------------------------------------------------------------------
        bEnviar = sObjGraficos.construirJButton(
                "Enviar a pendientes", 730, 62, 160, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bEnviar.addMouseListener(proximosComponent);
        bEnviar.addActionListener(proximosComponent);
        this.add(bEnviar);
    }
    public void crearJTable(){
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(cabecera);

        tabla = new JTable();
        tabla.setModel(modelo);
        header = tabla.getTableHeader();

        //Diseño de la tabla
        tabla.setRowHeight(30);

        tabla.setShowHorizontalLines(false);
        tabla.setShowVerticalLines(false);
        header.setPreferredSize(new Dimension(600,30));
        header.setDefaultRenderer(sGraficosAvanzados.devolverTablaPersonalizada(
                sRecursos.getColorPrincipal(), null, null, Color.white, sRecursos.getFuentePrincipal()
        ));
        tabla.setDefaultRenderer(Object.class,sGraficosAvanzados.devolverTablaPersonalizada(
                Color.white,sRecursos.getColorGrisClaro(), sRecursos.getColorPrincipalOscuro(),
                sRecursos.getColorGrisOscuro(),sRecursos.getFuentePrincipal()
        ));
        pTabla = sObjGraficos.construirPanelBarra(tabla, 80, 50, 620, 380, Color.WHITE, null);

        pTabla.getVerticalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        7, 10, Color.WHITE, Color.GRAY, sRecursos.getColorGrisOscuro()
                )
        );
        pCorner = new JPanel();
        pCorner.setBackground(sRecursos.getColorPrincipal());
        pTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pCorner);
        this.add(pTabla);
    }

    //GETTERS
    public JButton getbEnviar() {
        return bEnviar;
    }

    public JComboBox getCbCantidad() {
        return cbCantidad;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}


