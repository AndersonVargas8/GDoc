package gui.cliente.componentes.revision;

import gui.servicios.serviciosGraficos.GraficosAvanzadosService;
import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class  RevisionTemplate extends JPanel {
    //Servicios
    private RevisionComponent revisionComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    //Objetos gráficos
    private JPanel pVencidos;
    private JLabel lTitulo;
    private JButton bEliminar;

    //Objetos para JTable
    private JScrollPane pTabla;
    private JPanel pCorner;
    private JTable tabla;
    private JTableHeader header;
    private DefaultTableModel modelo;
    private String [] cabecera={"Id", "Tipo", "Nombre", "Ubicación", "Vencimiento"};

    public RevisionTemplate(RevisionComponent revisionComponent){
        this.revisionComponent = revisionComponent;
        //Obtención de servicios
        sRecursos = RecursosService.getServicio();
        sObjGraficos = ObjGraficosService.getServicio();
        sGraficosAvanzados = GraficosAvanzadosService.getServicio();

        //Creación de objetos gráficos
        crearJPanels();
        crearContenidopVencidos();
        crearJTable();
        //Configuración del componente
        setSize(950,650);
        setBackground(sRecursos.getColorGrisClaro());
        setLayout(null);
        setVisible(true);
    }

    private void crearJPanels(){
        pVencidos = sObjGraficos.construirJPanel(10,10, 930,150, Color.white,
                BorderFactory.createLineBorder(new Color(255, 136, 136),2));
        this.add(pVencidos);
    }

    public void crearContenidopVencidos() {
        // LABEL TITULO--------------------------------------------------------------------
        lTitulo = sObjGraficos.construirJLabel(
                "Siguiente documento vencido: ", 20, 10, 300, 30, null, null,
                sRecursos.getFuenteTitulo(), null, sRecursos.getColorGrisOscuro(), null, "c"
        );
        pVencidos.add(lTitulo);

        // BOTÓN FILTRAR--------------------------------------------------------------------
        bEliminar = sObjGraficos.construirJButton(
                "Eliminar", 750, 62, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bEliminar.addMouseListener(revisionComponent);
        bEliminar.addActionListener(revisionComponent);
        pVencidos.add(bEliminar);
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
        pTabla = sObjGraficos.construirPanelBarra(tabla, 100, 50, 600, 63, Color.WHITE, null);

        pTabla.getVerticalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        7, 10, Color.WHITE, Color.GRAY, sRecursos.getColorGrisOscuro()
                )
        );

        pCorner = new JPanel();
        pCorner.setBackground(sRecursos.getColorPrincipal());
        pTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pCorner);
        pVencidos.add(pTabla);
    }

    public JButton getbEliminar() {
        return bEliminar;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}
