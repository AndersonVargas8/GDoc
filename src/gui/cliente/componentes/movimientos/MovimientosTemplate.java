package gui.cliente.componentes.movimientos;

import datos.Documento;
import gui.servicios.serviciosGraficos.GraficosAvanzadosService;
import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MovimientosTemplate extends JPanel {

    //Servicios
    private MovimientosComponent movimientosComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    // Objetos gráficos
    JPanel pOpciones;
    JLabel lTitulo,lUsuario,lFecha,lTipo;
    JTextField tFecha;
    JButton bFiltrar;
    JComboBox cbUsuario,cbTipo;

    //Objetos para JTable
    private JScrollPane pTabla;
    private JPanel pCorner;
    private JTable tabla;
    private JTableHeader header;
    private DefaultTableModel modelo;
    private String [] cabecera={"Id documento", "Tipo Documento", "Nombre documento", "Usuario", "Movimiento", "Fecha","Hora"};

    public MovimientosTemplate(MovimientosComponent movimientosComponent){
        this.movimientosComponent = movimientosComponent;
        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();
        sGraficosAvanzados = GraficosAvanzadosService.getServicio();

        //Creación de objetos gráficos
        crearJPanels();
        crearContenidopOpciones();
        crearJTable();

        //Configuración del componente
        setSize(950,650);
        setBackground(sRecursos.getColorGrisClaro());
        setLayout(null);
        setVisible(true);
    }

    private void crearJPanels(){
        pOpciones = sObjGraficos.construirJPanel(10,10, 930,150, Color.white, null);
        this.add(pOpciones);
    }

    public void crearContenidopOpciones(){
        // LABEL TITULO--------------------------------------------------------------------
        lTitulo = sObjGraficos.construirJLabel(
                "Historial de movimientos", 20, 10, 250, 30, null, null,
                sRecursos.getFuenteTitulo(), null,sRecursos.getColorGrisOscuro(),null , "c"
        );
        pOpciones.add(lTitulo);

        // LABEL USUARIO--------------------------------------------------------------------
        lUsuario = sObjGraficos.construirJLabel(
                "Usuario:", 60, 65, 80, 30, null, null,
                sRecursos.getFuenteMediana(), null,sRecursos.getColorGrisOscuro(),null , "l"
        );
        pOpciones.add(lUsuario);

        //COMBOBOX USUARIO ------------------------------------------------------------------
        cbUsuario = sObjGraficos.construirJComboBox(
                "Todos_Anderson_Andres_Daniel_Omar",
                130,60,100,40,
                sRecursos.getFuenteTextFields(),
                sRecursos.getColorGris(),sRecursos.getColorGrisOscuro(),"c"
        );
        cbUsuario.addActionListener(movimientosComponent);
        cbUsuario.transferFocus();
        pOpciones.add(cbUsuario);

        // LABEL FECHA--------------------------------------------------------------------
        lFecha = sObjGraficos.construirJLabel(
                "Fecha:", 260, 65, 80, 30, null, null,
                sRecursos.getFuenteMediana(), null,sRecursos.getColorGrisOscuro(),null , "l"
        );
        pOpciones.add(lFecha);

        // TEXTFIELD FECHA--------------------------------------------------------------------
        tFecha = sObjGraficos.construirJTextField(
                "Fecha...", 320, 60, 150, 40, sRecursos.getFuenteTextFields() , sRecursos.getColorGris(),
                sRecursos.getColorGrisOscuro(), sRecursos.getColorGrisOscuro(), null, "c"
        );
        tFecha.addFocusListener(movimientosComponent);
        pOpciones.add(tFecha);

        // LABEL TIPO--------------------------------------------------------------------
        lTipo = sObjGraficos.construirJLabel(
                "Tipo:", 500, 65, 70, 30, null, null,
                sRecursos.getFuenteMediana(), null,sRecursos.getColorGrisOscuro(),null , "l"
        );
        pOpciones.add(lTipo);

        //COMBOBOX TIPO ------------------------------------------------------------------
        cbTipo = sObjGraficos.construirJComboBox(
                "Todos_Eliminación_Inserción_Modificación",
                550,60,100,40,
                sRecursos.getFuenteTextFields(),
                sRecursos.getColorGris(),sRecursos.getColorGrisOscuro(),"c"
        );
        cbTipo.addActionListener(movimientosComponent);
        pOpciones.add(cbTipo);

        // BOTÓN FILTRAR--------------------------------------------------------------------
        bFiltrar = sObjGraficos.construirJButton(
                "Filtrar", 700, 62, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bFiltrar.addMouseListener(movimientosComponent);
        bFiltrar.addActionListener(movimientosComponent);
        pOpciones.add(bFiltrar);
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
        header.setPreferredSize(new Dimension(930,30));
        header.setDefaultRenderer(sGraficosAvanzados.devolverTablaPersonalizada(
                sRecursos.getColorPrincipal(), null, null, Color.white, sRecursos.getFuentePrincipal()
        ));
        tabla.setDefaultRenderer(Object.class,sGraficosAvanzados.devolverTablaPersonalizada(
                Color.white,sRecursos.getColorGrisClaro(), sRecursos.getColorPrincipalOscuro(),
                sRecursos.getColorGrisOscuro(),sRecursos.getFuentePrincipal()
        ));

        pTabla = sObjGraficos.construirPanelBarra(tabla, 10, 170, 930, 470, Color.WHITE, null);

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

    public MovimientosComponent getMovimientosComponent() {
        return movimientosComponent;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTabla() {
        return tabla;
    }

    public JButton getbFiltrar(){
        return bFiltrar;
    }

    public JTextField gettFecha() {
        return tFecha;
    }

    public ObjGraficosService getsObjGraficos() {
        return sObjGraficos;
    }

    public JScrollPane getpTabla() {
        return pTabla;
    }

    public JComboBox getCbUsuario() {
        return cbUsuario;
    }

    public JComboBox getCbTipo() {
        return cbTipo;
    }
}
