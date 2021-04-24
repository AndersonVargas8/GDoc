package logica;
import java.util.*;
import java.io.*;
import estructuras.*;
/**
 *La clase Controlador permite gestionar las funcionalidades correspondientes a las carpetas y sus documentos.
 * @author Anderson
 */
public class Controlador {
    public static ListaEncadenadaSimple<Carpeta> sec = new ListaEncadenadaSimple<>();
    public static final String SEPARATOR=";";
   public static final String QUOTE="\"";
   public static void main(String[] args) throws IOException {

      BufferedReader br = null;
      
      try {
         
         br =new BufferedReader(new FileReader("agenda.csv"));
         String line = br.readLine();
         while (null!=line) {
            String [] fields = line.split(SEPARATOR);
            
            Carpeta car = new Carpeta(fields[1],Integer.parseInt(fields[2]),fields[0]);
            sec.insertarAlFinal(car);
            line = br.readLine();
         }
         
      } catch (Exception e) {
         
      } finally {
         if (null!=br) {
            br.close();
         }
      }
      int a = sec.getContador();
      for(int i = 0; i < a; i++){
          System.out.println(sec.leerDato(i).toString());
      } 
}
   
}
