package logica;

import java.io.*;
import static logica.crud.*;
import estructuras.*;
import datos.*;

import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *La clase Controlador permite gestionar las funcionalidades correspondientes a las carpetas y sus documentos.
 * @author Anderson
 */
public class Controlador{
    
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    static ListaEncadenadaDoble<Documento> lista_documentos = new ListaEncadenadaDoble<>();
    

    public static void main(String[] args) throws IOException {
        ListaEncadenadaDoble<String> hola = new ListaEncadenadaDoble<>();
        //lista_documentos = Archivo.cargarDocumentos();
        lista_documentos = Archivo.ImportarTXT();

        String usuario = IngresarUsuario();
        int menu_opcion = 0;
        while (menu_opcion != 6) {
            menu_opcion = Menu();
            switch (menu_opcion) {
                case 1:
                    InsertarDocumento(lista_documentos, reader, usuario);
                    break;
                case 2:
                    EliminarDocumento(lista_documentos, reader, usuario);
                    break;
                case 3:
                    ModificarDocumento(lista_documentos, reader, usuario);
                    break;
                case 4:
                    buscaDocumentos(lista_documentos);
                    break;
                case 5:
                    Lista(lista_documentos);
                    break;
                default: Archivo.ExportarTXT(lista_documentos);
            }
        }
    }
    
    public static String Cpline(String line){
        while (line.length()<69){
            line += " ";
        }
        return line + "*";
    }
    
    static String IngresarUsuario() throws IOException {
        System.out.println("**********************************************************************");
        System.out.println(Cpline("* Ingrese su nombre de usuario: "));
        System.out.print("* ");
        String usuario = reader.readLine();
        System.out.println("**********************************************************************\n\n");
        return usuario;
    }
    
    static void escribirArchivo(){
        ListaEncadenada<String> hola = new ListaEncadenadaSimple<String>();
    }
    static int Menu () throws IOException {
        System.out.println("******************************** MENU ********************************");
        System.out.println(Cpline("* 1. Archivar un nuevo documento "));
        System.out.println(Cpline("* 2. Eliminar uno o varios documentos existente"));
        System.out.println(Cpline("* 3. Modificar un elemento existente"));
        System.out.println(Cpline("* 4. Buscar uno o varios documentos existentes"));
        System.out.println(Cpline("* 5. Mostrar la lista de los documentos existentes ("+lista_documentos.cantidadDeElementos() + ")"));
        System.out.println(Cpline("* 6. Salir del programa"));
        System.out.println(Cpline("* "));
        System.out.println(Cpline("* Ingrese Elección: "));
        System.out.print("* ");
        int menu_opcion = Integer.parseInt(reader.readLine());
        System.out.println("**********************************************************************\n\n");
        return menu_opcion;
    }
    
}
