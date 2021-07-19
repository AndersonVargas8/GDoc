package gui.cliente.componentes.navegacionUsuario;

import gui.cliente.vistaPrincipal.VistaPrincipalComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavegacionUsuarioComponent implements ActionListener {
    private NavegacionUsuarioTemplate navegacionUsuarioTemplate;
    private VistaPrincipalComponent vistaPrincipalComponent;

    public NavegacionUsuarioComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.navegacionUsuarioTemplate = new NavegacionUsuarioTemplate(this);
        this.vistaPrincipalComponent = vistaPrincipalComponent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand().trim());
    }

    public NavegacionUsuarioTemplate getNavegacionUsuarioTemplate() {
        return navegacionUsuarioTemplate;
    }


}
