package gui.cliente.vistaPrincipal;

import gui.cliente.componentes.barraTitulo.BarraTituloComponent;
import gui.cliente.componentes.movimientos.MovimientosComponent;
import gui.cliente.componentes.navegacionUsuario.NavegacionUsuarioComponent;
import gui.cliente.componentes.registros.RegistrosComponent;
import gui.cliente.componentes.revision.RevisionComponent;
import gui.cliente.login.LoginComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipalComponent implements ActionListener {
    //componentes gráficos generales
    private VistaPrincipalTemplate vistaPrincipalTemplate;
    private BarraTituloComponent barraTituloComponent;
    private NavegacionUsuarioComponent navegacionUsuarioComponent;
    private LoginComponent loginComponent;

    //componentes gráficos secundarios
    private RevisionComponent revisionComponent;
    private RegistrosComponent registrosComponent;
    private MovimientosComponent movimientosComponent;

    public VistaPrincipalComponent(LoginComponent loginComponent){
        //componentes generales
        vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
        barraTituloComponent = new BarraTituloComponent(this);
        navegacionUsuarioComponent = new NavegacionUsuarioComponent(this);
        this.loginComponent = loginComponent;

        //Hace que el componente revisión se muestre de inicio
        this.revisionComponent = new RevisionComponent(this);
        vistaPrincipalTemplate.getpPrincipal().add(
                revisionComponent.getRevisionTemplate()
        );

        //adición de la barra superior y el panel de navegación
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

    public void mostrarComponente(String comando){
        vistaPrincipalTemplate.getpPrincipal().removeAll();
        switch (comando){
            case "Revisión":
                vistaPrincipalTemplate.getpPrincipal().add(
                        revisionComponent.getRevisionTemplate()
                );
                this.revisionComponent.getRevisionTemplate().revalidate();
                break;
            case "Registros":
                if(registrosComponent == null)
                    this.registrosComponent = new RegistrosComponent(this);
                vistaPrincipalTemplate.getpPrincipal().add(
                        registrosComponent.getRegistrosTemplate()
                );
                registrosComponent.getRegistrosTemplate().revalidate();
                break;
            case "Movimientos":
                if(movimientosComponent == null)
                    this.movimientosComponent = new MovimientosComponent();
                vistaPrincipalTemplate.getpPrincipal().add(
                        movimientosComponent.getMovimientosTemplate()
                );
                movimientosComponent.getMovimientosTemplate().revalidate();
                break;
            case "Cerrar Sesión":
                this.loginComponent.restaurarValores();
                this.vistaPrincipalTemplate.setVisible(false);
                this.loginComponent.getLoginTemplate().setVisible(true);
                break;
        }
        vistaPrincipalTemplate.repaint();
    }

    public VistaPrincipalTemplate getVistaPrincipalTemplate() {
        return vistaPrincipalTemplate;
    }

    public void restaurarValores(){
        this.vistaPrincipalTemplate.getpPrincipal().add(revisionComponent.getRevisionTemplate());
        actualizarValores();
    }

    public void actualizarValores(){
        this.revisionComponent.restarurarValores();
        this.navegacionUsuarioComponent.actualizarValores();
        if(this.movimientosComponent != null)
            this.movimientosComponent.actualizarValores();
        if(this.registrosComponent != null)
            this.registrosComponent.mostrarRegistrosTabla();
    }

    public void moverVentana(int posicionX, int posicionY){
        this.vistaPrincipalTemplate.setLocation(posicionX,posicionY);
    }
}
