package gui.cliente.login;

import gui.servicios.ObjGraficosService;
import gui.servicios.RecursosService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Anderson Vargas
 */
public class LoginTemplate extends JFrame{
    
    //Objetos gráficos
    private JPanel pDerecha,pIzquierda;
    private JLabel lNombreEmpresa,lNombreProyecto, lTitulo,lUsuario,lClave;
    private JLabel lFondo,lLogo,lFavicon;
    private JTextField tNombreUsuario;
    private JPasswordField tClaveUsuario;
    private JButton bEntrar,bCerrar;
    
    //Objetos decoradores
    private ImageIcon iFondo,iLogo,iFavicon,iDimAux;
    
    //Servicios
    ObjGraficosService sObjGraficos;
    RecursosService sRecursos;
    
    
    public LoginTemplate(){

        //Obtención de servicios
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();

        //Creación de objetos gráficos
        this.crearObjetosDecoradores();
        this.crearJPanels();
        this.crearJLabels();
        this.crearJTextFields();
        this.crearJButtons();

        //Configuración de la pantalla
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(870,500);
        setLocationRelativeTo(this);
        setUndecorated(true);
        setVisible(true);
    }
    
    private void crearObjetosDecoradores(){
        iFondo = new ImageIcon("recursos/imagenes/fondo.jpg");
        iLogo = new ImageIcon("recursos/imagenes/logo.png");
        iFavicon = new ImageIcon("recursos/imagenes/favicon.png");
    }
    
    private void crearJPanels(){
        pIzquierda = sObjGraficos.construirJPanel(0, 0, 550, 500, Color.WHITE, null);
        this.add(pIzquierda);
        
        pDerecha = sObjGraficos.construirJPanel(550,0,320,500,Color.GRAY,null);
        this.add(pDerecha);
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
        
        iDimAux = new ImageIcon(iFavicon.getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING));
        
        lFavicon = sObjGraficos.construirJLabel(
                null,
                50,50, 50, 50,
                null,
                iDimAux,
                null, null, null, null,
                "c");
        pIzquierda.add(lFavicon);
        
        lNombreProyecto = sObjGraficos.construirJLabel(
                "GDoc",
                105, 65, 70,20,
                null,
                null,
                new Font("Roboto",Font.BOLD,24),
                null,
                Color.BLACK
                ,null,
                "c");
        pIzquierda.add(lNombreProyecto);
        
        lTitulo = sObjGraficos.construirJLabel(
                "INICIAR SESIÓN",
                50, 130, 190,40,
                null,
                null,
                new Font("Roboto",Font.BOLD,24),
                null,
                Color.BLACK
                ,null,
                "l");
        pIzquierda.add(lTitulo);
        
        lUsuario = sObjGraficos.construirJLabel(
                "USUARIO",
                50,200, 90,20,
                null,
                null,
                sRecursos.getFuenteMediana(),
                null,
                Color.BLACK
                ,null,
                "l");
        pIzquierda.add(lUsuario);
        
        lClave = sObjGraficos.construirJLabel(
                "CONTRASEÑA",
                50,280, 100,20,
                null,
                null,
                sRecursos.getFuenteMediana(),
                null,
                Color.BLACK
                ,null,
                "l");
        pIzquierda.add(lClave);
    }
    
    private void crearJTextFields(){
        
        tNombreUsuario = sObjGraficos.construirJTextField(
                "Ingrese su nombre de usuario",
                50,230,400,40,
                sRecursos.getFuenteTextFields(),
                Color.WHITE,
                sRecursos.getColorGrisOscuro(),
                Color.black,
                sRecursos.getBordeTextField(),
                "l");
        pIzquierda.add(tNombreUsuario);
        
        tClaveUsuario = sObjGraficos.construirJPasswordField(
                "Contraseña",
                50,310,400,40,
                sRecursos.getFuenteTextFields(),
                Color.WHITE,
                sRecursos.getColorGrisOscuro(),
                Color.BLACK,
                sRecursos.getBordeTextField(),
                "l");
        
        pIzquierda.add(tClaveUsuario);
    }
    
    private void crearJButtons(){
        bEntrar = sObjGraficos.construirJButton(
                "ENTRAR",
                50,380,140,40,
                sRecursos.getcMano(),
                null,
                sRecursos.getFuenteBotones(),
                new Color(0,134,190),
                Color.white,
                null,
                "c",
                true);
        
        pIzquierda.add(bEntrar);

        
        bCerrar = sObjGraficos.construirJButton(
                "X",
                0, 5, 45, 30,
                sRecursos.getcMano(),
                null,
                sRecursos.getFuenteCerrar(), null, null, null,
                "c",
                false
              );
              pIzquierda.add(bCerrar);
    }
    

}
