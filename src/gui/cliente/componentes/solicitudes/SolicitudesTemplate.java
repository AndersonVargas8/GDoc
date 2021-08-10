package gui.cliente.componentes.solicitudes;

import gui.servicios.serviciosGraficos.GraficosAvanzadosService;
import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class SolicitudesTemplate extends JPanel {

    //Servicios
    private SolicitudesComponent solicitudesComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    // Objetos gráficos
    JPanel pSolicitud, pInfoSol,pInfoOcu;
    JLabel lTitulo,lSolicitados,lOcupados,lDocumento,lDependencia;
    JTextField tDocumento;
    JButton bSolicitar,bDevolver;
    JComboBox cbDependencia;

    //Objetos para JTable
    private JScrollPane pTablaSol,pTablaOcu;
    private JPanel pCorner;
    private JTable tablaSol,tablaOcu;
    private JTableHeader headerSol,headerOcu;
    private DefaultTableModel modeloSol,modeloOcu;
    private String [] cabeceraSol ={"Documento", "Tiempo"};
    private String[] cabeceraOcu = {"Documento", "Dependencia", "Tiempo"};

    public SolicitudesTemplate(SolicitudesComponent solicitudesComponent){
        this.solicitudesComponent = solicitudesComponent;
        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();
        sGraficosAvanzados = GraficosAvanzadosService.getServicio();

        //Creación de objetos gráficos
        crearJPanels();
        crearContenidopSolicitud();
        crearJTables();

        //Configuración del componente
        setSize(950,650);
        setBackground(sRecursos.getColorGrisClaro());
        setLayout(null);
        setVisible(true);
    }

    private void crearJPanels(){
        pSolicitud = sObjGraficos.construirJPanel(10,10, 930,150, Color.white, null);
        this.add(pSolicitud);

        pInfoSol = sObjGraficos.construirJPanel(10,170, 450,50, Color.white, null);
        this.add(pInfoSol);

        pInfoOcu = sObjGraficos.construirJPanel(490,170, 450,50, Color.white, null);
        this.add(pInfoOcu);
    }

    private void crearContenidopSolicitud(){
        // LABEL TITULO--------------------------------------------------------------------
        lTitulo = sObjGraficos.construirJLabel(
                "Hacer nueva solicitud", 20, 10, 250, 30, null, null,
                sRecursos.getFuenteTitulo(), null,sRecursos.getColorGrisOscuro(),null , "c"
        );
        pSolicitud.add(lTitulo);

        // LABEL DOCUMENTO--------------------------------------------------------------------
        lDocumento = sObjGraficos.construirJLabel(
                "Documento a solicitar:", 60, 65, 180, 30, null, null,
                sRecursos.getFuenteMediana(), null,sRecursos.getColorGrisOscuro(),null , "l"
        );
        pSolicitud.add(lDocumento);

        // TEXTFIELD DOCUMENTO--------------------------------------------------------------------
        tDocumento = sObjGraficos.construirJTextField(
                "Documento", 230, 60, 250, 40, sRecursos.getFuenteTextFields() , sRecursos.getColorGris(),
                sRecursos.getColorGrisOscuro(), sRecursos.getColorGrisOscuro(), null, "c"
        );
        tDocumento.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        tDocumento.setFocusable(false);
        pSolicitud.add(tDocumento);

        // LABEL DEPENDENCIA--------------------------------------------------------------------
        lDependencia = sObjGraficos.construirJLabel(
                "Dependencia:", 500, 65, 180, 30, null, null,
                sRecursos.getFuenteMediana(), null,sRecursos.getColorGrisOscuro(),null , "l"
        );
        pSolicitud.add(lDependencia);

        //COMBOBOX DEPENDENCIA ------------------------------------------------------------------
        cbDependencia = sObjGraficos.construirJComboBox(
                "Cafetería_Cartera_Dirección General_Mercadeo_Recepción_Recursos humanos_Sistemas",
                600,60,120,40,
                sRecursos.getFuenteTextFields(),
                sRecursos.getColorGris(),sRecursos.getColorGrisOscuro(),"c"
        );
        cbDependencia.addActionListener(solicitudesComponent);
        cbDependencia.transferFocus();
        cbDependencia.setEnabled(false);
        pSolicitud.add(cbDependencia);

        // BOTÓN SOLICITAR--------------------------------------------------------------------
        bSolicitar = sObjGraficos.construirJButton(
                "Solicitar", 750, 62, 120, 35, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bSolicitar.addMouseListener(solicitudesComponent);
        bSolicitar.addActionListener(solicitudesComponent);
        bSolicitar.setEnabled(false);
        pSolicitud.add(bSolicitar);
    }

    public void crearJTables(){
        //TABLA DE SOLICITUDES
        // Label solicitados--------------------------------------------------------------------
        lSolicitados = sObjGraficos.construirJLabel(
                "Documentos solicitados", 10, 10, 250, 30, null, null,
                sRecursos.getFuenteTitulo(), null,sRecursos.getColorGrisOscuro(),null , "c"
        );
        pInfoSol.add(lSolicitados);

        modeloSol = new DefaultTableModel();
        modeloSol.setColumnIdentifiers(cabeceraSol);

        tablaSol = new JTable();
        tablaSol.setModel(modeloSol);
        headerSol = tablaSol.getTableHeader();

        //Diseño de la tabla
        tablaSol.setRowHeight(30);

        tablaSol.setShowHorizontalLines(false);
        tablaSol.setShowVerticalLines(false);
        headerSol.setPreferredSize(new Dimension(450,30));
        headerSol.setDefaultRenderer(sGraficosAvanzados.devolverTablaPersonalizada(
                sRecursos.getColorPrincipal(), null, null, Color.white, sRecursos.getFuentePrincipal()
        ));
        tablaSol.setDefaultRenderer(Object.class,sGraficosAvanzados.devolverTablaPersonalizada(
                Color.white,sRecursos.getColorGrisClaro(), sRecursos.getColorPrincipalOscuro(),
                sRecursos.getColorGrisOscuro(),sRecursos.getFuentePrincipal()
        ));
        pTablaSol = sObjGraficos.construirPanelBarra(tablaSol, 10, 220, 450, 420, Color.WHITE, null);

        pTablaSol.getVerticalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        7, 10, Color.WHITE, Color.GRAY, sRecursos.getColorGrisOscuro()
                )
        );

        tablaSol.getColumn("Tiempo").setMinWidth(80);
        tablaSol.getColumn("Tiempo").setMaxWidth(80);
        pCorner = new JPanel();
        pCorner.setBackground(sRecursos.getColorPrincipal());
        pTablaSol.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pCorner);
        this.add(pTablaSol);

        //TABLA DE OCUPADOS
        // Label solicitados--------------------------------------------------------------------
        lOcupados = sObjGraficos.construirJLabel(
                "Documentos ocupados", 10, 10, 250, 30, null, null,
                sRecursos.getFuenteTitulo(), null,sRecursos.getColorGrisOscuro(),null , "c"
        );
        pInfoOcu.add(lOcupados);

        // Botón devolver ----------------------------------------------------------------------
        bDevolver = sObjGraficos.construirJButton(
                "Devolver", 300, 15, 120, 25, sRecursos.getcMano(), null, sRecursos.getFuenteBotones(),
                sRecursos.getColorPrincipal(), Color.WHITE, null, "c", true
        );
        bDevolver.addMouseListener(solicitudesComponent);
        bDevolver.addActionListener(solicitudesComponent);
        pInfoOcu.add(bDevolver);


        modeloOcu = new DefaultTableModel();
        modeloOcu.setColumnIdentifiers(cabeceraOcu);

        tablaOcu = new JTable();
        tablaOcu.setModel(modeloOcu);
        headerOcu= tablaOcu.getTableHeader();

        //Diseño de la tabla
        tablaOcu.setRowHeight(30);

        tablaOcu.setShowHorizontalLines(false);
        tablaOcu.setShowVerticalLines(false);
        headerOcu.setPreferredSize(new Dimension(450,30));
        headerOcu.setDefaultRenderer(sGraficosAvanzados.devolverTablaPersonalizada(
                sRecursos.getColorPrincipal(), null, null, Color.white, sRecursos.getFuentePrincipal()
        ));
        tablaOcu.setDefaultRenderer(Object.class,sGraficosAvanzados.devolverTablaPersonalizada(
                Color.white,sRecursos.getColorGrisClaro(), sRecursos.getColorPrincipalOscuro(),
                sRecursos.getColorGrisOscuro(),sRecursos.getFuentePrincipal()
        ));
        pTablaOcu = sObjGraficos.construirPanelBarra(tablaOcu, 490, 220, 450, 420, Color.WHITE, null);

        pTablaOcu.getVerticalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        7, 10, Color.WHITE, Color.GRAY, sRecursos.getColorGrisOscuro()
                )
        );

        tablaOcu.getColumn("Tiempo").setMinWidth(80);
        tablaOcu.getColumn("Tiempo").setMaxWidth(80);
        pTablaOcu.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pCorner);
        this.add(pTablaOcu);
    }

    public JTextField gettDocumento() {
        return tDocumento;
    }

    public JButton getbSolicitar() {
        return bSolicitar;
    }

    public JComboBox getCbDependencia() {
        return cbDependencia;
    }

    public SolicitudesComponent getSolicitudesComponent() {
        return solicitudesComponent;
    }

    public JButton getbDevolver() {
        return bDevolver;
    }

    public JTable getTablaSol() {
        return tablaSol;
    }

    public JTable getTablaOcu() {
        return tablaOcu;
    }

    public ObjGraficosService getsObjGraficos() {
        return sObjGraficos;
    }

    public DefaultTableModel getModeloSol() {
        return modeloSol;
    }

    public DefaultTableModel getModeloOcu() {
        return modeloOcu;
    }
}
