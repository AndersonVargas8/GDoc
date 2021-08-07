package gui.cliente.componentes.navegacionUsuario;

import gui.servicios.serviciosGraficos.GraficosAvanzadosService;
import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;
import gui.servicios.serviciosLogicos.DocumentosService;
import gui.servicios.serviciosLogicos.PendientesService;
import gui.servicios.serviciosLogicos.RevisionService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class NavegacionUsuarioTemplate extends JPanel {
    //Objetos gráficos
    private JPanel pSuperior, pMedio, pInferior;
    private JLabel lIconoUsuario,lUsuario,lCantidad, lNumCantidad, lVencidos, lResumen,
            lNumVencidos, lPendientes, lNumPendientes;
    private JButton bRevision, bRegistros, bMovimientos, bSolicitudes, bCerrarSesion;

    //Objetos decoradores
    private ImageIcon iUsuario, iRevision, iRegistros, iMovimientos, iSolicitudes, iCerrarSesion, iDimAux;
    private Border bVacio, bDifuminado;

    //Servicios
    ObjGraficosService sObjGraficos;
    RecursosService sRecursos;
    DocumentosService sDocumentos;

    private NavegacionUsuarioComponent navegacionUsuarioComponent;

    public NavegacionUsuarioTemplate(NavegacionUsuarioComponent navegacionUsuarioComponent){
        this.navegacionUsuarioComponent = navegacionUsuarioComponent;

        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();
        sDocumentos = DocumentosService.getServicio();

        //Creación de objetos graficos
        this.crearObjetosDecoradores();
        this.crearJPanels();
        this.crearJLabels();
        this.crearJButtons();

        //Configuración de la navegación
        setSize(250,700);
        setLayout(null);
        setVisible(true);
    }

    private void crearJPanels(){
        pSuperior = sObjGraficos.construirJPanel(
                0,0,250,300,
                sRecursos.getColorPrincipal(),
                null
        );
        this.add(pSuperior);

        pMedio = sObjGraficos.construirJPanel(
                5,100,240,160,
                sRecursos.getColorPrincipalOscuro(),
                bDifuminado
        );
        pSuperior.add(pMedio);

        pInferior = sObjGraficos.construirJPanel(
                0,300,250, 400,
                sRecursos.getColorPrincipal(),
                null
        );
        this.add(pInferior);
    }

    public void crearObjetosDecoradores(){
        iUsuario = new ImageIcon("recursos/imagenes/usuario.png");
        iRevision = new ImageIcon("recursos/imagenes/revision.png");
        iRegistros = new ImageIcon("recursos/imagenes/registros.png");
        iMovimientos = new ImageIcon("recursos/imagenes/movimientos.png");
        iSolicitudes = new ImageIcon("recursos/imagenes/solicitudes.png");
        iCerrarSesion = new ImageIcon("recursos/imagenes/salir.png");
        bVacio = new EmptyBorder(2,20,2,2);
        bDifuminado = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
                new Color(4, 28, 62),
                new Color(28, 86, 139));
        bDifuminado = GraficosAvanzadosService.getServicio().devolverBordeDifuminado(
                new Color(4, 28, 62),1
        );
    }

    public void crearJLabels(){
        iDimAux = new ImageIcon(
                iUsuario.getImage()
                        .getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING)
        );
        lIconoUsuario = sObjGraficos.construirJLabel(
                null,
                10, 20, 40, 40,
                null,
                iDimAux,
                null, null, null, null,
                "c"
        );
        pSuperior.add(lIconoUsuario);

        lUsuario = sObjGraficos.construirJLabel(
                navegacionUsuarioComponent.getUsuarioConectado().getNombreUsuario(),
                ((this.pSuperior.getWidth() - 200) / 2) + 10, 20, 200, 40,
                null, null,
                sRecursos.getFuenteTitulo(),
                null,
                Color.WHITE,
                null,
                "c"
        );
        pSuperior.add(lUsuario);

        lResumen= sObjGraficos.construirJLabel(
                "RESUMEN",
                85,5,200,30,
                null,null,
                sRecursos.getFuenteBotones(),
                null,
                Color.WHITE,
                null,
                "l"
        );
        pMedio.add(lResumen);

        lCantidad = sObjGraficos.construirJLabel(
            "Cantidad de documentos: ",
            5,40,200,30,
            null,null,
            sRecursos.getFuenteMediana(),
            null,
            Color.WHITE,
            null,
            "l"
        );
        pMedio.add(lCantidad);

        lNumCantidad = sObjGraficos.construirJLabel(
            String.valueOf(sDocumentos.devolverCantidadDocumentos()),
                190,40,50,30,
                null,null,
                sRecursos.getFuenteMediana(),
                null,
                Color.WHITE,
                null,
                "l"
        );
        pMedio.add(lNumCantidad);

        lVencidos = sObjGraficos.construirJLabel(
                "Documentos vencidos: ",
                5,80,200,30,
                null,null,
                sRecursos.getFuenteMediana(),
                null,
                Color.WHITE,
                null,
                "l"
        );
        pMedio.add(lVencidos);

        lNumVencidos = sObjGraficos.construirJLabel(
                String.valueOf(RevisionService.getServicio().cantidadVencidos()),
                190,80,50,30,
                null,null,
                sRecursos.getFuenteMediana(),
                null,
                Color.WHITE,
                null,
                "l"
        );
        pMedio.add(lNumVencidos);

        lPendientes = sObjGraficos.construirJLabel(
                "Documentos pendientes: ",
                5,120,200,30,
                null,null,
                sRecursos.getFuenteMediana(),
                null,
                Color.WHITE,
                null,
                "l"
        );
        pMedio.add(lPendientes);

        lNumPendientes = sObjGraficos.construirJLabel(
                String.valueOf(PendientesService.getServicio().cantidadPendientes()),
                190,120,50,30,
                null,null,
                sRecursos.getFuenteMediana(),
                null,
                Color.WHITE,
                null,
                "l"
        );
        pMedio.add(lNumPendientes);
    }

    public void crearJButtons(){
        //BOTÓN REVISIÓN-----------------------
        iDimAux = new ImageIcon(
                iRevision.getImage()
                        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        bRevision = sObjGraficos.construirJButton(
                "      Revisión",
                30, 20, 200, 40,
                sRecursos.getcMano(),
                iDimAux,
                sRecursos.getFuenteBotones(),
                null,
                Color.WHITE,
                bVacio,
                "l",
                true
        );
        this.bRevision.addActionListener(navegacionUsuarioComponent);
        this.bRevision.addMouseListener(navegacionUsuarioComponent);
        this.pInferior.add(bRevision);


        //BOTÓN REGISTROS-----------------------------
        iDimAux = new ImageIcon(
                iRegistros.getImage()
                        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        bRegistros = sObjGraficos.construirJButton(
                "      Registros",
                30, 70, 200, 40,
                sRecursos.getcMano(),
                iDimAux,
                sRecursos.getFuenteBotones(),
                null,
                Color.WHITE,
                bVacio,
                "l",
                true
        );
        this.bRegistros.addActionListener(navegacionUsuarioComponent);
        bRegistros.addMouseListener(navegacionUsuarioComponent);
        this.pInferior.add(bRegistros);

        //BOTÓN MOVIMIENTOS ------------------------------
        iDimAux = new ImageIcon(
                iMovimientos.getImage()
                        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        bMovimientos = sObjGraficos.construirJButton(
                "      Movimientos",
                30, 120, 200, 40,
                sRecursos.getcMano(),
                iDimAux,
                sRecursos.getFuenteBotones(),
                null,
                Color.WHITE,
                bVacio,
                "l",
                true
        );
        this.bMovimientos.addActionListener(navegacionUsuarioComponent);
        this.bMovimientos.addMouseListener(navegacionUsuarioComponent);
        this.pInferior.add(bMovimientos);

        //BOTÓN SOLICITUDES ------------------------------
        iDimAux = new ImageIcon(
                iSolicitudes.getImage()
                        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        bSolicitudes = sObjGraficos.construirJButton(
                "      Solicitudes",
                30, 170, 200, 40,
                sRecursos.getcMano(),
                iDimAux,
                sRecursos.getFuenteBotones(),
                null,
                Color.WHITE,
                bVacio,
                "l",
                true
        );
        this.bSolicitudes.addActionListener(navegacionUsuarioComponent);
        this.bSolicitudes.addMouseListener(navegacionUsuarioComponent);
        this.pInferior.add(bSolicitudes);


        //BOTÓN CERRAR SESIÓN---------------------------
        iDimAux = new ImageIcon(
                iCerrarSesion.getImage()
                        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        bCerrarSesion = sObjGraficos.construirJButton(
                "      Cerrar Sesión",
                30, 220, 200, 40,
                sRecursos.getcMano(),
                iDimAux,
                sRecursos.getFuenteBotones(),
                null,
                Color.WHITE,
                bVacio,
                "l",
                true
        );
        this.bCerrarSesion.addActionListener(navegacionUsuarioComponent);
        this.bCerrarSesion.addMouseListener(navegacionUsuarioComponent);
        this.pInferior.add(bCerrarSesion);
    }

    public JPanel getpSuperior() {
        return pSuperior;
    }

    public JPanel getpMedio() {
        return pMedio;
    }
}
