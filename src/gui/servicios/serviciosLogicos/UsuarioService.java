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

    public Usuario getUsuarioConectado() {
        return this.usuarioConectado;
    }
}
