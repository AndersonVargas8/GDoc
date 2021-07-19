package gui.cliente.componentes.revision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevisionComponent implements ActionListener {
    private RevisionTemplate revisionTemplate;

    public RevisionComponent(){
        this.revisionTemplate = new RevisionTemplate(this);
    }

    public RevisionTemplate getRevisionTemplate() {
        return revisionTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
