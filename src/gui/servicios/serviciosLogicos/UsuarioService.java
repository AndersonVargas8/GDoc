package gui.servicios.serviciosLogicos;

import datos.Usuario;
import logica.ControlUsuarios;

public class UsuarioService {
    private static UsuarioService servicio;
    private ControlUsuarios cUsuario;
    private Usuario usuarioConectado;

    public UsuarioService(){
        cUsuario = new ControlUsuarios();
    }
    public static UsuarioService getServicio(){
        if(servicio == null)
            servicio = new UsuarioService();

        return servicio;
    }

    public boolean verificarDatosUsuario(String nombreUsuario, String claveUsuario){
        if(cUsuario.verificarUsuario(nombreUsuario,claveUsuario)){
            this.usuarioConectado = cUsuario.devolverUsuario(nombreUsuario);
            return true;
        }
        return false;
    }

    public boolean verificarUsuarioExistente(String nombreUsuario){
        return cUsuario.verificarUsuario(nombreUsuario);
    }

    public void crearUsuario(String nombreUsuario, String claveUsuario){
        cUsuario.agregarUsuario(new Usuario(nombreUsuario, claveUsuario));
    }
    public Usuario getUsuarioConectado() {
        return this.usuarioConectado;
    }
}
