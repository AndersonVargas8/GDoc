package gui.cliente.componentes.barraTitulo;

import gui.cliente.vistaPrincipal.VistaPrincipalComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraTituloComponent implements ActionListener {

    private BarraTituloTemplate barraTituloTemplate;
    private VistaPrincipalComponent vistaPrincipalComponent;

    public BarraTituloComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.barraTituloTemplate = new BarraTituloTemplate(this);
        this.vistaPrincipalComponent = vistaPrincipalComponent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == barraTituloTemplate.getbMinimizar())
            vistaPrincipalComponent.minimizar();
        if(e.getSource() == barraTituloTemplate.getbCerrar())
            vistaPrincipalComponent.cerrar();
    }

    public BarraTituloTemplate getBarraTituloTemplate() {
        return barraTituloTemplate;
    }
}
