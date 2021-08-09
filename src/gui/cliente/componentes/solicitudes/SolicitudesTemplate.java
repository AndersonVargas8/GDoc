package gui.cliente.componentes.solicitudes;

import gui.cliente.componentes.movimientos.MovimientosComponent;
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
    JPanel pSolicitud, pInformacion;
    JLabel lTitulo,lSolicitados,lOcupados,lDocumento,lDependencia;
    JTextField tDocumento;
    JButton bSolicitar,bDevolver;
    JComboBox cbDependencia;

    //Objetos para JTable
    private JScrollPane pTablaSol;
    private JPanel pCorner;
    private JTable tablaSol,tablaOcu;
    private JTableHeader headerSol,headerOcu;
    private DefaultTableModel modeloSol,modeloOcu;
    private String [] cabeceraSol={"Id documento", "Tipo Documento", "Nombre documento", "Ubicación documento",
            "Movimiento", "Fecha", "Hora", "Usuario"};
    private String[] cabeceraOcu = {};

    public SolicitudesTemplate(SolicitudesComponent solicitudesComponent){
        this.solicitudesComponent = solicitudesComponent;
        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();
        sGraficosAvanzados = GraficosAvanzadosService.getServicio();

        //Creación de objetos gráficos
        crearJPanels();
        crearContenidopSolicitud();
        //crearJTable();

        //Configuración del componente
        setSize(950,650);
        setBackground(sRecursos.getColorGrisClaro());
        setLayout(null);
        setVisible(true);
    }

    private void crearJPanels(){
        pSolicitud = sObjGraficos.construirJPanel(10,10, 930,150, Color.white, null);
        this.add(pSolicitud);
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

    public JTextField gettDocumento() {
        return tDocumento;
    }

    public JButton getbSolicitar() {
        return bSolicitar;
    }

    public JComboBox getCbDependencia() {
        return cbDependencia;
    }
}
