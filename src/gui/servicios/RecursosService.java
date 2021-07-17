package gui.servicios;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RecursosService {
    private Color colorPrincipal, colorGrisOscuro;
    private Font fuentePrincipal, fuenteCerrar, fuenteBotones, fuenteMediana, fuenteTextFields;
    private Cursor cMano;
    private Border bordeTextField;
    static private RecursosService servicio;

    private RecursosService(){
        this.generarFuentes();
        this.crearBordes();
        this.crearColores();
        this.crearCursores();
        this.crearFuentes();
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

    public Font getFuenteTextFields() {
        return fuenteTextFields;
    }

    public Color getColorGrisOscuro() {
        return colorGrisOscuro;
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

    public Cursor getcMano() {
        return cMano;
    }

    public Border getBordeTextField() {
        return bordeTextField;
    }

    private void crearColores(){
        colorPrincipal = new Color(0, 0, 0);
        colorGrisOscuro = new Color(80, 80, 80);
    }

    private void crearFuentes(){
        fuentePrincipal = new Font("Roboto", Font.PLAIN,20);
        fuenteCerrar = new Font("Roboto condensed",Font.PLAIN,20);
        fuenteBotones = new Font("Roboto condensed",Font.BOLD,14);
        fuenteMediana = new Font("Roboto light",Font.BOLD,14);
        fuenteTextFields = new Font("Roboto",Font.PLAIN,12);
    }

    private void crearCursores(){
        cMano = new Cursor(Cursor.HAND_CURSOR);
    }

    private void crearBordes(){
        bordeTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
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


}
