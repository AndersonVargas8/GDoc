package logica;

import datos.Usuario;
import estructuras.listas.ListaEncadenadaSimple;
import estructuras.tablasHash.TablaHash;

import java.io.*;

public class ControlUsuarios {
    private static TablaHash<String,Usuario> usuarios;

    public ControlUsuarios(){
        if(usuarios == null){
            this.usuarios = new TablaHash<>();
            cargarDatos();
        }
    }



    public void cargarDatos(){
        File archivo = new File ("src/archivos/usuarios.txt").getAbsoluteFile();
        FileInputStream lector = null;
        ObjectInputStream decodificador = null;
        try {
            lector = new FileInputStream(archivo);
            decodificador = new ObjectInputStream(lector);
            usuarios = (TablaHash<String, Usuario>) decodificador.readObject();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void guardarDatos(){
        File archivo = new File("src/archivos/usuarios.txt").getAbsoluteFile();
        FileOutputStream escritor = null;
        ObjectOutputStream encriptador = null;
        try {
            archivo.createNewFile();
            escritor = new FileOutputStream(archivo);
            encriptador = new ObjectOutputStream(escritor);
            encriptador.writeObject(usuarios);
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

    public Usuario devolverUsuario(String nombreUsuario){
        if(usuarios.contiene(nombreUsuario))
            return usuarios.leer(nombreUsuario);
        return null;
    }

    public boolean verificarUsuario(String nombreUsuario, String claveUsuario){
        if(usuarios.contiene(nombreUsuario)){
            if(usuarios.leer(nombreUsuario).getClaveUsuario().equals(claveUsuario))
                return true;
        }
        return false;
    }

    public boolean verificarUsuario(String nombreUsuario){
        return usuarios.contiene(nombreUsuario);
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.insertar(usuario.getNombreUsuario(),usuario);
    }
}
