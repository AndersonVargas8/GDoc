package logica;

import datos.Documento;
import datos.Fecha;
import estructuras.arboles.AVL;
import estructuras.listas.ListaEncadenadaDoble;
import gui.servicios.serviciosLogicos.DocumentosService;

import java.io.*;

public class ControlDocumentos {
    private AVL<Documento> documentos;
    private int numeroRegistro;

    public ControlDocumentos(){
        this.documentos = new AVL<>();
        cargarDatos();
    }

    public void cargarDatos() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("src/archivos/documentos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
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
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void guardarDatos(){
        ListaEncadenadaDoble<Documento> documentos = DocumentosService.getServicio().imprimirTodo();
        FileWriter fw = null;
        try{
            fw = new FileWriter("src/archivos/documentos.txt");
            BufferedWriter bw = new BufferedWriter(fw);
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
            if(fw != null){
                try {
                    fw.close();
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
