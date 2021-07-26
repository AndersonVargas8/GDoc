package logica;

import datos.Documento;
import datos.Fecha;
import estructuras.arboles.AVL;
import estructuras.listas.ListaEncadenadaDoble;
import gui.servicios.serviciosLogicos.DocumentosService;

import java.io.*;
import java.nio.charset.Charset;

public class ControlDocumentos {
    private AVL<Documento> documentos;
    private int numeroRegistro;

    public ControlDocumentos(){
        this.documentos = new AVL<>();
        cargarDatos();
    }

    public void cargarDatos() {
        FileInputStream archivo = null;
        BufferedReader br = null;
        try {
            archivo = new FileInputStream("src/archivos/documentos.txt");
            br = new BufferedReader(
                    new InputStreamReader(archivo,Charset.forName("UTF-8").newDecoder())
            );

            String linea = br.readLine();
            numeroRegistro = Integer.parseInt(linea);

            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",");

                Documento documento = new Documento();
                documento.setId(Integer.parseInt(atributos[0]));
                documento.setTipo(atributos[1]);
                documento.setNombre(atributos[2]);
                documento.setEstante(atributos[3]);
                documento.setCarpeta(atributos[4]);
                documento.setIngreso(new Fecha(atributos[5].split("/")));
                documento.setExpiracion(new Fecha(atributos[6].split("/")));

                documentos.insertar(documento);
            }
            archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void guardarDatos(){
        ListaEncadenadaDoble<Documento> documentos = DocumentosService.getServicio().imprimirTodo();
        FileOutputStream archivo = null;
        try{
            archivo = new FileOutputStream("src/archivos/documentos.txt");
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(archivo, Charset.forName("UTF-8").newEncoder())
            );
            bw.write(DocumentosService.getServicio().getSiguienteId() + "\n");
            for(Documento doc: documentos)
                bw.write(doc.getId() + ","
                        + doc.getTipo() + ","
                        + doc.getNombre() + ","
                        + doc.getEstante() + ","
                        + doc.getCarpeta() + ","
                        + doc.getIngreso().toString() + ","
                        + doc.getExpiracion().toString() + "\n");

            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(archivo != null){
                try {
                    archivo.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public AVL<Documento> getDocumentos() {
        return documentos;
    }

    public int getNumeroRegistro(){
        return numeroRegistro;
    }
}
