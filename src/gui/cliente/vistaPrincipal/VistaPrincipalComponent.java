package gui.cliente.vistaPrincipal;

import gui.cliente.componentes.barraTitulo.BarraTituloComponent;
import gui.cliente.componentes.navegacionUsuario.NavegacionUsuarioComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipalComponent implements ActionListener {
    private VistaPrincipalTemplate vistaPrincipalTemplate;
    private BarraTituloComponent barraTituloComponent;
    private NavegacionUsuarioComponent navegacionUsuarioComponent;

    public VistaPrincipalComponent(){
        vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
        barraTituloComponent = new BarraTituloComponent(this);
        navegacionUsuarioComponent = new NavegacionUsuarioComponent();

        vistaPrincipalTemplate.getpBarra().add(
                barraTituloComponent.getBarraTituloTemplate()
        );

        vistaPrincipalTemplate.getpNavegacion().add(
                navegacionUsuarioComponent.getNavegacionUsuarioTemplate()
        );
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public void cerrar(){
        System.exit(0);
    }

    public void minimizar(){
        this.vistaPrincipalTemplate.setExtendedState(Frame.ICONIFIED);
    }
}
