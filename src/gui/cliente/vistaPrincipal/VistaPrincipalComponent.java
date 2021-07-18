package gui.cliente.vistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipalComponent implements ActionListener {
    private VistaPrincipalTemplate vistaPrincipalTemplate;

    public VistaPrincipalComponent(){
        vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
