package gui.cliente.vistaPrincipal;

import gui.servicios.serviciosGraficos.ObjGraficosService;
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
    private VistaPrincipalComponent vistaPrincipalComponent;

    public VistaPrincipalTemplate(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;

        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();

        //Creación de objetos gráficos
        this.crearJPanels();

        //Configuración de la página
        ImageIcon iLogoP = new ImageIcon("recursos/imagenes/GDoc.png");
        ImageIcon iDimAux = new ImageIcon(iLogoP.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING));
        this.setIconImage(iDimAux.getImage());
        this.addWindowListener(vistaPrincipalComponent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1200,700);
        setLocationRelativeTo(this);
        setLayout(null);
        setVisible(true);
    }

    private void crearJPanels(){
        pNavegacion = sObjGraficos.construirJPanel(0,0, 250, 700,null,null);
        this.add(pNavegacion);

        pBarra = sObjGraficos.construirJPanel(250,0,950,50,null,null);
        this.add(pBarra);

        pPrincipal = sObjGraficos.construirJPanel(250,50,950,650, null,null);
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
