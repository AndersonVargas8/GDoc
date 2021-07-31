package gui.cliente.login;

import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import java.awt.*;

public class IniciarSesionTemplate extends JPanel{
    //Objetos gráficos
    private JLabel lTitulo,lUsuario,lClave;
    private JTextField tNombreUsuario;
    private JPasswordField tClaveUsuario;
    private JButton bEntrar;

    //Servicios
    ObjGraficosService sObjGraficos;
    RecursosService sRecursos;
    IniciarSesionComponent iniciarSesionComponent;

    public IniciarSesionTemplate(IniciarSesionComponent iniciarSesionComponent){

        //Obtención de servicios
        this.iniciarSesionComponent = iniciarSesionComponent;
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();

        //Creación de objetos gráficos
        this.crearJLabels();
        this.crearJTextFields();
        this.crearJButtons();

        //Configuración de la pantalla
        setLayout(null);
        setSize(550,390);
        setBackground(null);
        setVisible(true);
    }




    private void crearJLabels(){
        lTitulo = sObjGraficos.construirJLabel(
                "INICIAR SESIÓN",
                50, 30, 190,40,
                null,
                null,
                new Font("Roboto",Font.BOLD,24),
                null,
                Color.BLACK
                ,null,
                "l");
        this.add(lTitulo);

        lUsuario = sObjGraficos.construirJLabel(
                "USUARIO",
                50,100, 90,20,
                null,
                null,
                sRecursos.getFuenteMediana(),
                null,
                Color.BLACK
                ,null,
                "l");
        this.add(lUsuario);

        lClave = sObjGraficos.construirJLabel(
                "CONTRASEÑA",
                50,180, 100,20,
                null,
                null,
                sRecursos.getFuenteMediana(),
                null,
                Color.BLACK
                ,null,
                "l");
        this.add(lClave);
    }

    private void crearJTextFields(){

        tNombreUsuario = sObjGraficos.construirJTextField(
                "Ingrese su nombre de usuario",
                50,130,400,40,
                sRecursos.getFuenteTextFields(),
                Color.WHITE,
                sRecursos.getColorGrisOscuro(),
                Color.black,
                sRecursos.getBordeTextField(),
                "l");
        tNombreUsuario.addMouseListener(iniciarSesionComponent);
        this.add(tNombreUsuario);

        tClaveUsuario = sObjGraficos.construirJPasswordField(
                "Contraseña",
                50,210,400,40,
                sRecursos.getFuenteTextFields(),
                Color.WHITE,
                sRecursos.getColorGrisOscuro(),
                Color.BLACK,
                sRecursos.getBordeTextField(),
                "l");
        tClaveUsuario.addMouseListener(iniciarSesionComponent);
        this.add(tClaveUsuario);
    }

    private void crearJButtons(){
        bEntrar = sObjGraficos.construirJButton(
                "ENTRAR",
                50,280,140,40,
                sRecursos.getcMano(),
                null,
                sRecursos.getFuenteBotones(),
                new Color(0,134,190),
                Color.white,
                null,
                "c",
                true);

        bEntrar.addActionListener(iniciarSesionComponent);
        bEntrar.addMouseListener(iniciarSesionComponent);
        this.add(bEntrar);

    }


    public JTextField gettNombreUsuario() {
        return tNombreUsuario;
    }

    public JPasswordField gettClaveUsuario() {
        return tClaveUsuario;
    }

    public JButton getbEntrar() {
        return bEntrar;
    }

}
