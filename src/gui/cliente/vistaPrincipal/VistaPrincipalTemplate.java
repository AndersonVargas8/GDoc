package gui.cliente.vistaPrincipal;

import gui.servicios.ObjGraficosService;
import gui.servicios.RecursosService;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Anderson Vargas
 */
public class VistaPrincipalTemplate extends JFrame{
    //Objetos gráficos
    private JPanel pNavegacion, pBarra, pPrincipal;
    //Servicios
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private VistaPrincipalComponent vistaPrincipalComponent;

    public VistaPrincipalTemplate(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;

        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();

        //Creación de objetos gráficos
        this.crearJPanels();

        //Configuración de la página
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1200,700);
        setLocationRelativeTo(this);
        setLayout(null);
        setVisible(true);
    }

    private void crearJPanels(){
        pNavegacion = sObjGraficos.construirJPanel(0,0, 250, 700, sRecursos.getColorPrincipal(),null);
        this.add(pNavegacion);

        pBarra = sObjGraficos.construirJPanel(250,0,950,50,sRecursos.getColorSecundario(),null);
        this.add(pBarra);

        pPrincipal = sObjGraficos.construirJPanel(250,50,950,700, Color.WHITE,null);
        this.add(pPrincipal);

    }

    public JPanel getpNavegacion() {
        return pNavegacion;
    }

    public JPanel getpBarra() {
        return pBarra;
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }
}
