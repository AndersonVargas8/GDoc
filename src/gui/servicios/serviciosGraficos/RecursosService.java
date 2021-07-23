package gui.servicios.serviciosGraficos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RecursosService {
    private Color colorPrincipal, colorPrincipalOscuro, colorSecundario,
            colorGrisOscuro, colorGris, colorGrisClaro, colorGrisBoton1,colorGrisBotonOscuro1,
    colorGrisBoton2,colorGrisBotonOscuro2;
    private Font fuentePrincipal, fuenteCerrar, fuenteBotones, fuenteMediana,
            fuenteTextFields,fuenteNombre, fuenteTitulo;
    private Cursor cMano;
    private Border bordeTextField,bordePlano;
    private ImageIcon iFavicon;
    static private RecursosService servicio;

    private RecursosService(){
        this.generarFuentes();
        this.crearBordes();
        this.crearColores();
        this.crearCursores();
        this.crearFuentes();
        this.crearImagenes();
    }

    public static RecursosService getServicio(){
        if(servicio == null){
            servicio = new RecursosService();
        }

        return servicio;
    }

    public Color getColorPrincipal() {
        return colorPrincipal;
    }

    public Color getColorSecundario() {
        return colorSecundario;
    }

    public Font getFuenteTextFields() {
        return fuenteTextFields;
    }

    public Color getColorGrisOscuro() {
        return colorGrisOscuro;
    }

    public Color getColorPrincipalOscuro() {
        return colorPrincipalOscuro;
    }

    public Color getColorGrisClaro() {
        return colorGrisClaro;
    }

    public Color getColorGris() {
        return colorGris;
    }

    public Color getColorGrisBoton1() {
        return colorGrisBoton1;
    }

    public Color getColorGrisBotonOscuro1() {
        return colorGrisBotonOscuro1;
    }

    public Color getColorGrisBoton2() {
        return colorGrisBoton2;
    }

    public Color getColorGrisBotonOscuro2() {
        return colorGrisBotonOscuro2;
    }

    public Font getFuentePrincipal() {
        return fuentePrincipal;
    }

    public Font getFuenteCerrar() {
        return fuenteCerrar;
    }

    public Font getFuenteBotones() {
        return fuenteBotones;
    }

    public Font getFuenteMediana() {
        return fuenteMediana;
    }

    public Font getFuenteNombre() {
        return fuenteNombre;
    }

    public Font getFuenteTitulo() {
        return fuenteTitulo;
    }

    public Cursor getcMano() {
        return cMano;
    }

    public Border getBordeTextField() {
        return bordeTextField;
    }

    public Border getBordePlano() {
        return bordePlano;
    }

    public ImageIcon getiFavicon() {
        return iFavicon;
    }

    private void crearColores(){
        colorPrincipal = new Color(49, 99, 142);
        colorPrincipalOscuro = new Color(22, 74, 120);
        colorSecundario = new Color(215, 227, 232);
        colorGrisOscuro = new Color(80, 80, 80);
        colorGrisClaro = new Color(249, 246, 249);
        colorGris = new Color(235,235,235);
        colorGrisBoton1 = new Color(132, 135, 147);
        colorGrisBotonOscuro1 = new Color(93, 94, 99);
        colorGrisBoton2 = new Color(117, 119, 128);
        colorGrisBotonOscuro2 = new Color(78, 79, 82);
    }

    private void crearFuentes(){
        fuentePrincipal = new Font("Roboto", Font.PLAIN,14);
        fuenteCerrar = new Font("Roboto condensed",Font.PLAIN,20);
        fuenteBotones = new Font("Roboto condensed",Font.BOLD,14);
        fuenteMediana = new Font("Roboto light",Font.BOLD,14);
        fuenteTextFields = new Font("Roboto",Font.PLAIN,12);
        fuenteNombre = new Font("Roboto",Font.BOLD,24);
        fuenteTitulo = new Font("Roboto light", Font.BOLD, 17);
    }

    private void crearCursores(){
        cMano = new Cursor(Cursor.HAND_CURSOR);
    }

    private void crearBordes(){
        bordeTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
        bordePlano = BorderFactory.createLineBorder(Color.GRAY,1,true);
    }
    private void generarFuentes(){
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-Black.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-BlackItalic.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-Bold.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-BoldItalic.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-Italic.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-Light.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-LightItalic.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-Medium.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-MediumItalic.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-Regular.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-Thin.ttf")
            ));

            ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("recursos/fuentes/Roboto-ThinItalic.ttf")
            ));
        }catch(IOException | FontFormatException e){
            System.out.println(e);
        }
    }

    private void crearImagenes(){
        iFavicon = new ImageIcon("recursos/imagenes/favicon.png");
    }


}
