package gui.cliente.login;

import gui.servicios.serviciosGraficos.ObjGraficosService;
import gui.servicios.serviciosGraficos.RecursosService;

import javax.swing.*;
import java.awt.*;

public class RegistrarTemplate extends JPanel {
    //Objetos gráficos
    private JLabel lTitulo,lUsuario,lClave;
    private JTextField tNombreUsuario;
    private JPasswordField tClaveUsuario;
    private JButton bEntrar,bRegistrar;

    //Servicios
    ObjGraficosService sObjGraficos;
    RecursosService sRecursos;
    RegistrarComponent registrarComponent;

    public RegistrarTemplate(RegistrarComponent registrarComponent){

        //Obtención de servicios
        this.registrarComponent = registrarComponent;
        sObjGraficos = ObjGraficosService.getServicio();
        sRecursos = RecursosService.getServicio();

        //Creación de objetos gráficos
        this.crearJLabels();
        this.crearJTextFields();
        this.crearJButtons();

        //Configuración de la pantalla
        setSize(550,390);
        setBackground(Color.white);
        setLayout(null);
        setVisible(true);
    }

    private void crearJLabels(){
        lTitulo = sObjGraficos.construirJLabel(
                "REGISTRAR NUEVO USUARIO",
                50, 30, 340,40,
                null,
                null,
                new Font("Roboto",Font.BOLD,24),
                null,
                Color.BLACK
                ,null,
                "l");
        this.add(lTitulo);

        lUsuario = sObjGraficos.construirJLabel(
                "NUEVO USUARIO",
                50,100, 120,20,
                null,
                null,
                sRecursos.getFuenteMediana(),
                null,
                Color.BLACK
                ,null,
                "l");
        this.add(lUsuario);

        lClave = sObjGraficos.construirJLabel(
                "NUEVA CONTRASEÑA",
                50,180, 160,20,
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
                "Ingrese un nuevo usuario",
                50,130,400,40,
                sRecursos.getFuenteTextFields(),
                Color.WHITE,
                sRecursos.getColorGrisOscuro(),
                Color.black,
                sRecursos.getBordeTextField(),
                "l");
        tNombreUsuario.addMouseListener(registrarComponent);
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
        tClaveUsuario.addMouseListener(registrarComponent);
        this.add(tClaveUsuario);
    }

    private void crearJButtons(){
        bRegistrar= sObjGraficos.construirJButton(
                "REGISTRAR",
                50,280,140,40,
                sRecursos.getcMano(),
                null,
                sRecursos.getFuenteBotones(),
                new Color(0,134,190),
                Color.white,
                null,
                "c",
                true);

        bRegistrar.addActionListener(registrarComponent);
        bRegistrar.addMouseListener(registrarComponent);
        this.add(bRegistrar);

    }


    public JTextField gettNombreUsuario() {
        return tNombreUsuario;
    }

    public JPasswordField gettClaveUsuario() {
        return tClaveUsuario;
    }

    public JButton getbRegistrar(){
        return bRegistrar;
    }
}
