package gui.cliente.componentes.revision;

public class ProximosComponent {
    ProximosTemplate proximosTemplate;
    RevisionComponent revisionComponent;

    public ProximosComponent(RevisionComponent revisionComponent){
        proximosTemplate = new ProximosTemplate(this);
        this.revisionComponent = revisionComponent;
    }

    public ProximosTemplate getProximosTemplate(){
        return proximosTemplate;
    }
}
