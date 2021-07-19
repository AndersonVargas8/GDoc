package logica;

import datos.Usuario;
import estructuras.listas.ListaEncadenadaSimple;

import java.io.*;

public class ControlUsuarios {
    private ListaEncadenadaSimple<Usuario> usuarios;

    public ControlUsuarios(){
        this.usuarios = new ListaEncadenadaSimple<>();
        cargarDatos();
    }

    public void cargarDatos(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try{
            archivo =  new File("src/archivos/usuarios.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea = br.readLine()) != null){
                String[] atributos = linea.split(",");

                Usuario usuario = new Usuario(atributos[0],atributos[1]);
                usuarios.insertarAlFinal(usuario);
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario devolverUsuario(String nombreUsuario){
        for(int i = 0; i < usuarios.cantidadDeElementos(); i++){
            Usuario aux = usuarios.leerDato(i++);
            if(aux.getNombreUsuario().equals(nombreUsuario))
                return aux;
        }
        return null;
    }

    public boolean verificarUsuario(String nombreUsuario, String claveUsuario){
        for(int i = 0; i < usuarios.cantidadDeElementos(); i++){
            Usuario aux = usuarios.leerDato(i);
            if(aux.getNombreUsuario().equals(nombreUsuario))
                if(aux.getClaveUsuario().equals(claveUsuario))
                    return true;
        }

        return false;
    }
}
