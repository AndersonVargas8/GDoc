package gui.servicios.serviciosLogicos;

import datos.Documento;
import estructuras.grafos.Grafo;

public class SolicitudesService {
    private static SolicitudesService servicio;

    private Grafo rutas;

    public SolicitudesService(){
        rutas = new Grafo();

        //Creación de vertices
        rutas.agregarVertice("Bodega 1");
        rutas.agregarVertice("Bodega 2");
        rutas.agregarVertice("Bodega 3");
        rutas.agregarVertice("Cafetería");
        rutas.agregarVertice("Cartera");
        rutas.agregarVertice("Dirección General");
        rutas.agregarVertice("Mercadeo");
        rutas.agregarVertice("Recepción");
        rutas.agregarVertice("Recursos humanos");
        rutas.agregarVertice("Sistemas");

        //Creación de aristas
        rutas.adicionarAristaDoble("Bodega 1","Mercadeo",3);
        rutas.adicionarAristaDoble("Bodega 1", "Bodega 2", 2);
        rutas.adicionarAristaDoble("Bodega 2", "Recursos humanos",5);
        rutas.adicionarAristaDoble("Bodega 3","Recursos humanos",3);
        rutas.adicionarAristaDoble("Cafetería","Cartera",5);
        rutas.adicionarAristaDoble("Cafetería", "Recepción",8);
        rutas.adicionarAristaDoble("Cafetería", "Recursos humanos",8);
        rutas.adicionarAristaDoble("Cartera","Mercadeo",2);
        rutas.adicionarArista("Cartera","Recepción",6);
        rutas.adicionarArista("Dirección General", "Cafetería",1);
        rutas.adicionarArista("Recepción","Sistemas",5);
        rutas.adicionarArista("Recursos humanos","Dirección General",2);
        rutas.adicionarArista("Sistemas","Cafetería",1);

    }

    public static SolicitudesService getServicio(){
        if(servicio == null)
            servicio = new SolicitudesService();

        return servicio;
    }

    public int getTiempoSolicitud(Documento doc, String dependencia){
        String sOrigen = "Bodega " + DocumentosService.getServicio().retornarBodega(doc.getTipo());
        rutas.getCaminoMasCorto(sOrigen,dependencia);
        return rutas.getPesoMinimo(sOrigen, dependencia);
    }

    public int getTiempoSolicitud(String dependencia,Documento doc){
        String sDestino = "Bodega " + DocumentosService.getServicio().retornarBodega(doc.getTipo());
        rutas.getCaminoMasCorto(dependencia,sDestino);
        return rutas.getPesoMinimo(dependencia,sDestino);
    }
}
