
package logica;

import datos.*;
import estructuras.ListaEncadenadaDoble;
import java.io.*;
import java.time.ZonedDateTime;
/**
 *Esta clase permite manejar los archivos para mantener la persistencia de la información.
 * @author Anderson
 */
public class Archivo{
    public static void ExportarTXT(ListaEncadenadaDoble<Documento> publicaciones){
        File archivo = new File("src/logica/documentos.txt").getAbsoluteFile();
        FileOutputStream escritor = null;
        ObjectOutputStream encriptador = null;
        try {
            archivo.createNewFile();
            escritor = new FileOutputStream(archivo);
            encriptador = new ObjectOutputStream(escritor);
            encriptador.writeObject(publicaciones);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        finally{
            if(escritor != null){
                try {
                    escritor.close();
                    if(escritor != null){
                        escritor.close();
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    public static ListaEncadenadaDoble<Documento> ImportarTXT(){
        File archivo = new File ("src/logica/documentos.txt").getAbsoluteFile();
        FileInputStream lector = null;
        ObjectInputStream decodificador = null;
        ListaEncadenadaDoble<Documento> publicaciones = new ListaEncadenadaDoble<>();
        try {
            lector = new FileInputStream(archivo);
            decodificador = new ObjectInputStream(lector);
            publicaciones = (ListaEncadenadaDoble<Documento>) decodificador.readObject();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return publicaciones;
    }
    
    public static ListaEncadenadaDoble<Documento> cargarDocumentos()throws IOException{
       ListaEncadenadaDoble<Documento> documentos = ImportarTXT();
       String SEPARATOR=";";
       String QUOTE="\"";

      BufferedReader br = null;

      try {

         br =new BufferedReader(new FileReader("src/logica/docs.csv"));
         String line = br.readLine();
         while (null!=line) {
            String [] fields = line.split(SEPARATOR);
            Ubicacion ub = new Ubicacion(fields[3], fields[4]);
            Historial hi = new Historial(fields[0], fields[6],fields[7]);
            Integer id = Integer.parseInt(fields[1]);
            String nombre = fields[2];
            ZonedDateTime fecha_ingreso = ZonedDateTime.now();
            ZonedDateTime fecha_expiracion = fecha_ingreso.plusYears(Integer.parseInt(fields[5]));
            
            Documento doc = new Documento(id, nombre, ub, fecha_ingreso, fecha_expiracion, new ListaEncadenadaDoble<Historial>(hi));
            documentos.insertarAlFinal(doc);
            line = br.readLine();
         }

      } catch (Exception e) {

      } finally {
         if (null!=br) {
            br.close();
         }
      }
        System.out.println(documentos.cantidadDeElementos());
        documentos.eliminarDuplicados();
        
        System.out.println(documentos.cantidadDeElementos());
      return documentos;
    }
}
