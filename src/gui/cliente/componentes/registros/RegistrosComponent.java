package gui.cliente.componentes.registros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrosComponent implements ActionListener {
    private RegistrosTemplate registrosTemplate;

    public RegistrosComponent(){
        this.registrosTemplate = new RegistrosTemplate(this);
    }

    public RegistrosTemplate getRegistrosTemplate() {
        return registrosTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
