package gui.cliente.componentes.navegacionUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavegacionUsuarioComponent implements ActionListener {
    private NavegacionUsuarioTemplate navegacionUsuarioTemplate;

    public NavegacionUsuarioComponent(){
        this.navegacionUsuarioTemplate = new NavegacionUsuarioTemplate(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public NavegacionUsuarioTemplate getNavegacionUsuarioTemplate() {
        return navegacionUsuarioTemplate;
    }
}
