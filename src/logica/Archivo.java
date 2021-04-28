
package logica;

import datos.*;
import estructuras.ListaEncadenadaDoble;
import estructuras.Cola;
import java.io.*;
import java.time.ZonedDateTime;
/**
 *Esta clase permite manejar los archivos para mantener la persistencia de la información.
 * @author Anderson
 */
public class Archivo{
    /**
     * Eporta elementos de Documento guardados como una lista doblemente encadenada
     * @param publicaciones
     */
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

    /**
     * Retorna los elementos de Documento gurdados en una lista
     * @return
     */
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

    /**
     * Inicializa el docuemento docs.cvs
     * @return
     * @throws IOException
     */
    public static ListaEncadenadaDoble<Documento> cargarDocumentos()throws IOException{
        return  cargarDocumentos("src/logica/docs.csv");
    }

    /**
     * Inicializa y ordena la información respectiva a cada elemento subido por Ubicacón, Historial, Id, Fecha, etc y lo guarda en documento
     * @param ruta
     * @return
     * @throws IOException
     */
    private static ListaEncadenadaDoble<Documento> cargarDocumentos(String ruta)throws IOException{
        ListaEncadenadaDoble<Documento> documentos = new ListaEncadenadaDoble<>();
        String SEPARATOR=";";
        String QUOTE="\"";
        Cola<String> nom = new Cola<>();
        BufferedReader br = null;
        try {
            br =new BufferedReader(new FileReader(ruta));
            String line = br.readLine();
             while (null!=line) {
                String [] fields = line.split(SEPARATOR);
                Ubicacion ub = new Ubicacion(fields[3], fields[4]);
                Historial hi = new Historial(fields[0], fields[6],fields[7]);
                Integer id = Integer.parseInt(fields[1]);
                String nomdoc = fields[2];
                ZonedDateTime fecha_ingreso = ZonedDateTime.now();
                ZonedDateTime fecha_expiracion = fecha_ingreso.plusYears(Integer.parseInt(fields[5]));

                Documento doc = new Documento(id, nomdoc, ub, fecha_ingreso, fecha_expiracion, new ListaEncadenadaDoble<Historial>(hi));
                documentos.insertarAlFinal(doc);

                line = br.readLine();
             }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
         if (null!=br) {
            br.close();
         }
        }

        documentos.eliminarDuplicados();
      return documentos;
    }
}
