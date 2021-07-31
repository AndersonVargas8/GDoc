package gui.cliente.login;

import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Anderson Vargas
 */
public class LoginTemplate extends JFrame{
    
    //Objetos gráficos
    private JPanel pDerecha, pTitulo,pContenido;
    private JLabel lNombreEmpresa,lNombreProyecto;
    private JLabel lFondo,lLogo,lFavicon;
    private JButton bCerrar;
    
    //Objetos decoradores
    private ImageIcon iFondo,iLogo,iLogoP,iDimAux;
    
    //Servicios
    ObjGraficosService sObjGraficos;
    RecursosService sRecursos;
    LoginComponent loginComponent;
    
    public LoginTemplate(LoginComponent loginComponent){

        //Obtención de servicios
        this.loginComponent = loginComponent;
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();

        //Creación de objetos gráficos
        this.crearObjetosDecoradores();
        this.crearJPanels();
        this.crearJLabels();
        this.crearJButtons();

        //Configuración de la pantalla
        iDimAux = new ImageIcon(iLogoP.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING));
        this.setIconImage(iDimAux.getImage());
        setLayout(null);
        this.addWindowListener(loginComponent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(870,500);
        setLocationRelativeTo(this);
        setUndecorated(true);
        setVisible(true);
    }
    
    private void crearObjetosDecoradores(){
        iFondo = new ImageIcon("recursos/imagenes/fondo.jpg");
        iLogo = new ImageIcon("recursos/imagenes/logo.png");
        iLogoP = new ImageIcon("recursos/imagenes/GDoc.png");
    }
    
    private void crearJPanels(){
        pTitulo = sObjGraficos.construirJPanel(0, 0, 550, 110, Color.WHITE, null);
        this.add(pTitulo);
        
        pDerecha = sObjGraficos.construirJPanel(550,0,320,500,Color.GRAY,null);
        this.add(pDerecha);

        pContenido = sObjGraficos.construirJPanel(0,110,550,390,Color.WHITE,null);
        this.add(pContenido);
    }
    
    private void crearJLabels(){
        
        lNombreEmpresa = sObjGraficos.construirJLabel(
                "Gestión Documental",
                0, 240, 320,20,
                null,
                null,
                new Font("Roboto Medium",Font.PLAIN,18),
                null,
                Color.BLACK
                ,null,
                "c");
        pDerecha.add(lNombreEmpresa);
        
        iDimAux = new ImageIcon(iLogo.getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING));
        lLogo = sObjGraficos.construirJLabel(
                null,
                85,80,150,150,
                null,
                iDimAux,
                null,null,null,null,
                "c");
        pDerecha.add(lLogo);
        
        
        iDimAux = new ImageIcon(iFondo.getImage().getScaledInstance(pDerecha.getWidth(), 
                pDerecha.getHeight(),
                Image.SCALE_AREA_AVERAGING));
        
        lFondo = sObjGraficos.construirJLabel(
                null,
                0, 0, pDerecha.getWidth(), pDerecha.getHeight(),
                null,
                iDimAux,
                null, null, null, null,
                "c");
        pDerecha.add(lFondo);
        
        iDimAux = new ImageIcon(sRecursos.getiFavicon().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING));
        
        lFavicon = sObjGraficos.construirJLabel(
                null,
                50,50, 50, 50,
                null,
                iDimAux,
                null, null, null, null,
                "c");
        pTitulo.add(lFavicon);
        
        lNombreProyecto = sObjGraficos.construirJLabel(
                "GDoc",
                105, 65, 70,20,
                null,
                null,
                sRecursos.getFuenteNombre(),
                null,
                Color.BLACK
                ,null,
                "c");
        pTitulo.add(lNombreProyecto);

    }

    private void crearJButtons(){


        
        bCerrar = sObjGraficos.construirJButton(
                "X",
                0, 0, 45, 40,
                sRecursos.getcMano(),
                null,
                sRecursos.getFuenteCerrar(), null, null, null,
                "c",
                true
              );
              bCerrar.addActionListener(loginComponent);
              bCerrar.addMouseListener(loginComponent);
              pTitulo.add(bCerrar);
    }


    public JButton getbCerrar() {
        return bCerrar;
    }

    public JPanel getpContenido() {
        return pContenido;
    }
}
