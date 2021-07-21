package gui.cliente.componentes.barraTitulo;

import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import java.awt.*;

public class BarraTituloTemplate extends JPanel {
    //Objetos gráficos
    private JLabel lFavicon, lNombre;
    private JButton bMinimizar, bCerrar;

    //Objetos decoradores
    private ImageIcon iDimAux;
    //Servicios
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private BarraTituloComponent barraTituloComponent;

    public BarraTituloTemplate(BarraTituloComponent barraTituloComponent){

        //Obtención de servicios
        this.barraTituloComponent = barraTituloComponent;
        this.sObjGraficos = ObjGraficosService.getServicio();
        this.sRecursos = RecursosService.getServicio();

        //Creación de elementos gráficos
        this.crearJButtons();
        this.crearJLabels();

        this.addMouseListener(barraTituloComponent);
        this.addMouseMotionListener(barraTituloComponent);
        //Configuración de la barra
        this.setSize(950, 50);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void crearJButtons(){
        bMinimizar = sObjGraficos.construirJButton(
                "_",
                860, 0, 45, 50,
                sRecursos.getcMano(),
                null,
                sRecursos.getFuenteCerrar(), null, null, null,
                "c",
                true
        );

        bMinimizar.addActionListener(barraTituloComponent);
        bMinimizar.addMouseListener(barraTituloComponent);
        this.add(bMinimizar);


        bCerrar = sObjGraficos.construirJButton(
                "X",
                905, 0, 45, 50,
                sRecursos.getcMano(),
                null,
                sRecursos.getFuenteCerrar(), null, null, null,
                "c",
                true
        );
        bCerrar.addActionListener(barraTituloComponent);
        bCerrar.addMouseListener(barraTituloComponent);
        this.add(bCerrar);
    }

    private void crearJLabels(){
        iDimAux = new ImageIcon(sRecursos.getiFavicon().getImage()
                        .getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING)
        );

        lFavicon = sObjGraficos.construirJLabel(
                null,
                20,5, 40, 40,
                null,
                iDimAux,
                null, null, null, null,
                "c");
        this.add(lFavicon);

        lNombre = sObjGraficos.construirJLabel(
                "GDoc",
                65, 15, 70,20,
                null,
                null,
                sRecursos.getFuenteNombre(),
                null,
                Color.BLACK
                ,null,
                "c");
        this.add(lNombre);
    }

    public JButton getbMinimizar() {
        return bMinimizar;
    }

    public JButton getbCerrar() {
        return bCerrar;
    }
}
