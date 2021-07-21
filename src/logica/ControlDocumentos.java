package logica;

import datos.Documento;
import estructuras.arboles.AVL;

import java.io.*;

public class ControlDocumentos {
    private AVL<Documento> documentos;

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
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",");

                Documento documento = new Documento();
                documento.setId(Integer.parseInt(atributos[0]));
                documento.setTipo(atributos[1]);
                documento.setNombre(atributos[2]);
                documento.setEstante(atributos[3]);
                documento.setCarpeta(atributos[4]);
                documento.setIngreso(atributos[5]);
                documento.setExpiración(atributos[6]);

                documentos.insertar(documento);
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AVL<Documento> getDocumentos() {
        return documentos;
    }
}
