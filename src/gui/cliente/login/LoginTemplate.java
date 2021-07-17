package gui.cliente.login;

import gui.servicios.ObjGraficosService;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
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
    private ImageIcon iFondo,iLogo,iFavicon,iDimAux,iCerrar;
    private Border border;
    
    //Servicios
    ObjGraficosService sObjGraficos;
    
    
    public LoginTemplate(){
        
        sObjGraficos = ObjGraficosService.getServicio();
        
        this.crearObjetosDecoradores();
        this.crearJPanels();
        this.crearJLabels();
        this.crearJTextFields();
        this.crearJButtons();
        
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(870,500);
        setLocationRelativeTo(this);
        setUndecorated(true);
        setVisible(true);
        
        
    }
    
    public void crearObjetosDecoradores(){
        iFondo = new ImageIcon("recursos/imagenes/fondo.jpg");
        iLogo = new ImageIcon("recursos/imagenes/logo.png");
        iFavicon = new ImageIcon("recursos/imagenes/favicon.png");
        iCerrar = new ImageIcon("recursos/imagenes/cerrar.png");
        border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
    }
    
    public void crearJPanels(){
        pIzquierda = sObjGraficos.construirJPanel(0, 0, 550, 500, Color.WHITE, null);
        this.add(pIzquierda);
        
        pDerecha = sObjGraficos.construirJPanel(550,0,320,500,Color.GRAY,null);
        this.add(pDerecha);
    }
    
    public void crearJLabels(){
        
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
                new Font("Roboto light",Font.BOLD,14),
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
                new Font("Roboto light",Font.BOLD,14),
                null,
                Color.BLACK
                ,null,
                "l");
        pIzquierda.add(lClave);
    }
    
    public void crearJTextFields(){
        
        tNombreUsuario = sObjGraficos.construirJTextField(
                "Ingrese su nombre de usuario",
                50,230,400,40,
                new Font("Roboto",Font.PLAIN,12),
                Color.WHITE,
                new Color(80,80,80),
                Color.black,
                border,
                "l");
        pIzquierda.add(tNombreUsuario);
        
        tClaveUsuario = sObjGraficos.construirJPasswordField(
                "Contraseña",
                50,310,400,40,
                new Font("Roboto",Font.PLAIN,12),
                Color.WHITE,
                new Color(80,80,80),
                Color.BLACK,
                border,
                "l");
        
        pIzquierda.add(tClaveUsuario);
    }
    
    public void crearJButtons(){
        bEntrar = sObjGraficos.construirJButton(
                "ENTRAR",
                50,380,140,40,
                new Cursor(Cursor.HAND_CURSOR),
                null,
                new Font("Roboto condensed",Font.BOLD,14),
                new Color(0,134,190),
                Color.white,
                null,
                "c",
                true);
        
        pIzquierda.add(bEntrar);
        
        iDimAux = new ImageIcon(
            iCerrar.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING));
        
        bCerrar = sObjGraficos.construirJButton(
                "X",
                0, 5, 45, 30,
                new Cursor(Cursor.HAND_CURSOR),
                null,
                new Font("Roboto condensed",Font.PLAIN,20), null, null, null,
                "c",
                false
              );
              pIzquierda.add(bCerrar);
    }
}
