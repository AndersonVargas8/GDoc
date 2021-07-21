package gui.cliente.componentes.registros;

import gui.servicios.serviciosGraficos.GraficosAvanzadosService;
import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.FechaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class RegistrosTemplate extends JPanel {

    private RegistrosComponent registrosComponent;

    //Componentes gráficos
    private JPanel pOpciones, pDatos;
    private JButton bMostrar, bInsertar, bFiltrar, bModificar, bEliminar;
    private JTextField tConsulta;
    private JLabel lTitulo, lDatos, lId,lIdValor, lTipo, lNombre, lEstante, lCarpeta,
            lIngreso, lIngresoValor, lExpiracion, lExpiracionValor;
    private JTextField tTipo, tNombre, tEstante, tCarpeta, tIngreso, tExpiracion;
    //servicios graficos
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    //Objetos para JTable
    private JScrollPane pTabla;
    private JPanel pCorner;
    private JTable tabla;
    private JTableHeader header;
    private DefaultTableModel modelo;
    private String [] cabecera={"Id", "Tipo", "Nombre", "Ubicación", "Ingreso", "Vencimiento"};

    public RegistrosTemplate(RegistrosComponent registrosComponent){
        this.registrosComponent = registrosComponent;
        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();
        sGraficosAvanzados = GraficosAvanzadosService.getServicio();

        //Creación objetos gráficos
        this.crearJPanels();
        this.crearContenidoPOpciones();
        this.crearContenidoPDatos();
        this.crearJTable();
        //Configuración del componente
        setSize(950,650);
        setBackground(sRecursos.getColorGrisClaro());
        setLayout(null);
        setVisible(true);
    }

    public void crearJPanels(){
        pOpciones = sObjGraficos.construirJPanel(10,10, 680,200, Color.white, null);
        this.add(pOpciones);
        pDatos = sObjGraficos.construirJPanel(700,10, 240, 630, Color.white, null);
        this.add(pDatos);
    }

    public void crearContenidoPOpciones(){
        // LABEL TITULO--------------------------------------------------------------------
        lTitulo = sObjGraficos.construirJLabel(
                "Gestión de documentos", 20, 10, 200, 30, null, null,
                sRecursos.getFuenteTitulo(), null,sRecursos.getColorGrisOscuro(),null , "c"
        );
        pOpciones.add(lTitulo);

        // TEXTFIELD CONSULTA--------------------------------------------------------------------
        tConsulta = sObjGraficos.construirJTextField(
                "Filtrar...", 30, 60, 420, 40, sRecursos.getFuenteTextFields() , sRecursos.getColorGris(),
                sRecursos.getColorGrisOscuro(), sRecursos.getColorGrisOscuro(), null, "c"
        );
        tConsulta.addFocusListener(registrosComponent);
        pOpciones.add(tConsulta);

        // BOTÓN FILTRAR--------------------------------------------------------------------
        bFiltrar = sObjGraficos.construirJButton(
                "Filtrar", 470, 65, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bFiltrar.addMouseListener(registrosComponent);
        bFiltrar.addActionListener(registrosComponent);
        pOpciones.add(bFiltrar);

        // BOTÓN MOSTRAR--------------------------------------------------------------------
        bMostrar = sObjGraficos.construirJButton(
                "Mostrar", 50, 145, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bMostrar.addMouseListener(registrosComponent);
        bMostrar.addActionListener(registrosComponent);
        pOpciones.add(bMostrar);

        // BOTÓN INSERTAR--------------------------------------------------------------------
        bInsertar = sObjGraficos.construirJButton(
                "Insertar", 200, 145, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bInsertar.addMouseListener(registrosComponent);
        bInsertar.addActionListener(registrosComponent);
        pOpciones.add(bInsertar);

        // BOTÓN MODIFICAR--------------------------------------------------------------------
        bModificar = sObjGraficos.construirJButton(
                "Modificar", 350, 145, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bModificar.addMouseListener(registrosComponent);
        bModificar.addActionListener(registrosComponent);
        pOpciones.add(bModificar);

        // BOTÓN ELIMINAR--------------------------------------------------------------------
        bEliminar= sObjGraficos.construirJButton(
                "Eliminar", 500, 145, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bEliminar.addMouseListener(registrosComponent);
        bEliminar.addActionListener(registrosComponent);
        pOpciones.add(bEliminar);
    }

    public void crearContenidoPDatos(){
        //LABEL DATOS----------------------------------
        lDatos = sObjGraficos.construirJLabel(
                "Datos del documento", 20,10,180,50,null,null,
                sRecursos.getFuenteTitulo(),null, sRecursos.getColorGrisOscuro(),null,"l"
        );

        pDatos.add(lDatos);

        // LABEL ID ----------------------------------------------------------------
        lId = sObjGraficos.construirJLabel(
                "Id Documento:", 20, 70, 160, 30, null, null,
                sRecursos.getFuentePrincipal(),null,sRecursos.getColorPrincipalOscuro(), null , "l"
        );
        // LABEL ID CONTENIDO ----------------------------------------------------------
        lIdValor = sObjGraficos.construirJLabel(
                "0", 120, 70, 160, 30, null, null,
                sRecursos.getFuentePrincipal(),null, sRecursos.getColorPrincipalOscuro(), null,"l"
        );
        pDatos.add(lIdValor);
        pDatos.add(lId);

        // LABEL TIPO ----------------------------------------------------------------
        lTipo = sObjGraficos.construirJLabel(
                "Tipo documento:", 20, 115, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorPrincipalOscuro() ,null, "l"
        );
        pDatos.add(lTipo);

        // TEXTFIELD TIPO ----------------------------------------------------------------
        tTipo = sObjGraficos.construirJTextField(
                "Tipo", 30, 155, 190, 30,sRecursos.getFuenteTextFields(), sRecursos.getColorGris(),
                sRecursos.getColorGrisOscuro(),  sRecursos.getColorGrisOscuro() , null, "c"
        );
        tTipo.addFocusListener(registrosComponent);
        pDatos.add(tTipo);

        // LABEL NOMBRE ----------------------------------------------------------------
        lNombre = sObjGraficos.construirJLabel(
                "Nombre documento:", 20, 195, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorPrincipalOscuro() ,null, "l"
        );
        pDatos.add(lNombre);

        // TEXTFIELD NOMBRE ----------------------------------------------------------------
        tNombre = sObjGraficos.construirJTextField(
                "Nombre", 30, 235, 190, 30,sRecursos.getFuenteTextFields(), sRecursos.getColorGris(),
                sRecursos.getColorGrisOscuro(),  sRecursos.getColorGrisOscuro(), null, "c"
        );
        tNombre.addFocusListener(registrosComponent);
        pDatos.add(tNombre);

        // LABEL ESTANTE ----------------------------------------------------------------
        lEstante = sObjGraficos.construirJLabel(
                "Estante:", 20, 275, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorPrincipalOscuro() ,null, "l"
        );
        pDatos.add(lEstante);

        // TEXTFIELD ESTANTE ----------------------------------------------------------------
        tEstante = sObjGraficos.construirJTextField(
                "Estante", 30, 315, 190, 30,sRecursos.getFuenteTextFields(), sRecursos.getColorGris(),
                sRecursos.getColorGrisOscuro(),  sRecursos.getColorGrisOscuro(), null, "c"
        );
        tEstante.addFocusListener(registrosComponent);
        pDatos.add(tEstante);

        // LABEL CARPETA ----------------------------------------------------------------
        lCarpeta = sObjGraficos.construirJLabel(
                "Carpeta:", 20, 355, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorPrincipalOscuro() ,null, "l"
        );
        pDatos.add(lCarpeta);

        // TEXTFIELD CARPETA ----------------------------------------------------------------
        tCarpeta = sObjGraficos.construirJTextField(
                "Carpeta", 30, 395, 190, 30,sRecursos.getFuenteTextFields(), sRecursos.getColorGris(),
                sRecursos.getColorGrisOscuro(),  sRecursos.getColorGrisOscuro(), null, "c"
        );
        tCarpeta.addFocusListener(registrosComponent);
        pDatos.add(tCarpeta);

        // LABEL INGRESO ----------------------------------------------------------------
        lIngreso = sObjGraficos.construirJLabel(
                "Fecha de ingreso:", 20, 435, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorPrincipalOscuro() ,null, "l"
        );
        pDatos.add(lIngreso);

        // LABEL VALOR INGRESO----------------------------------------------------------------
        lIngresoValor = sObjGraficos.construirJLabel(
                FechaService.getServicio().getFecha(), 30, 475, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorGrisOscuro() ,null, "l"
        );
        pDatos.add(lIngresoValor);

        // LABEL EXPIRACION ----------------------------------------------------------------
        lExpiracion = sObjGraficos.construirJLabel(
                "Fecha de vencimiento:", 20, 515, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorPrincipalOscuro() ,null, "l"
        );
        pDatos.add(lExpiracion);

        // LABEL VALOR EXPIRACION----------------------------------------------------------------
        lExpiracionValor = sObjGraficos.construirJLabel(
                FechaService.getServicio().getFechaPlus(1), 30, 555, 160, 30, null, null,
                sRecursos.getFuentePrincipal(), null,sRecursos.getColorGrisOscuro() ,null, "l"
        );
        pDatos.add(lExpiracionValor);
    }

    public void crearJTable(){
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(cabecera);

        tabla = new JTable();
        tabla.setModel(modelo);
        tabla.addMouseListener(registrosComponent);
        header = tabla.getTableHeader();

        //Diseño de la tabla
        tabla.setRowHeight(30);
        tabla.setShowHorizontalLines(false);
        tabla.setShowVerticalLines(false);
        header.setPreferredSize(new Dimension(680,30));
        header.setDefaultRenderer(sGraficosAvanzados.devolverTablaPersonalizada(
                sRecursos.getColorPrincipal(), null, null, Color.white, sRecursos.getFuentePrincipal()
        ));
        tabla.setDefaultRenderer(Object.class,sGraficosAvanzados.devolverTablaPersonalizada(
                Color.white,sRecursos.getColorGrisClaro(), sRecursos.getColorPrincipalOscuro(),
                sRecursos.getColorGrisOscuro(),sRecursos.getFuentePrincipal()
        ));

        pTabla = sObjGraficos.construirPanelBarra(tabla, 10, 220, 680, 420, Color.WHITE, null);

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
    public RegistrosComponent getRegistrosComponent() {
        return registrosComponent;
    }

    public JButton getbMostrar() {
        return bMostrar;
    }

    public JButton getbInsertar() {
        return bInsertar;
    }

    public JButton getbFiltrar() {
        return bFiltrar;
    }

    public JButton getbModificar() {
        return bModificar;
    }

    public JButton getbEliminar() {
        return bEliminar;
    }

    public JTextField gettConsulta() {
        return tConsulta;
    }

    public JTextField gettTipo() {
        return tTipo;
    }

    public JTextField gettNombre() {
        return tNombre;
    }

    public JTextField gettEstante() {
        return tEstante;
    }

    public JTextField gettCarpeta() {
        return tCarpeta;
    }

    public JTextField gettIngreso() {
        return tIngreso;
    }

    public JTextField gettExpiracion() {
        return tExpiracion;
    }

    public JLabel getlIdValor() {
        return lIdValor;
    }

    public JLabel getlIngresoValor() {
        return lIngresoValor;
    }

    public JLabel getlExpiracionValor() {
        return lExpiracionValor;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}
