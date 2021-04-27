package logica;

import datos.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.util.Objects;
import static logica.Controlador.Cpline;
import estructuras.*;

/**
 *
 * @author andres
 */
public class crud {

    static ListaEncadenadaDoble<Documento> InsertarDocumento (ListaEncadenadaDoble<Documento> lista, BufferedReader reader, String usuario) throws IOException {

        System.out.println ("************************ INGRESO DE DOCUMENTO ************************");
        System.out.println(Cpline("* Ingresa el ID del documento: "));
        System.out.print("* ");
        Integer id = Integer.parseInt(reader.readLine());
        System.out.println(Cpline("* Ingresa el Nombre del documento: "));
        System.out.print("* ");
        String nombre = reader.readLine();
        System.out.println(Cpline("* En que seccion se coloco el documento en fisico: "));
        System.out.print("* ");
        String seccion = reader.readLine();
        System.out.println(Cpline("* En que estante se coloco el documento en fisico: "));
        System.out.print("* ");
        String estante = reader.readLine();
        System.out.println(Cpline("* Cuanto tiempo de vigencia en años tiene este documento: "));
        System.out.print("* ");
        long tiempo_expiracion = Long.parseLong(reader.readLine());
        ZonedDateTime fecha_ingreso = ZonedDateTime.now();
        ZonedDateTime fecha_expiracion = fecha_ingreso.plusYears(tiempo_expiracion);
        Ubicacion ubicacion = new Ubicacion(seccion, estante);
        Historial hist = new Historial(usuario, ""+fecha_ingreso, "Creación del documento");
        ListaEncadenadaDoble<Historial> historia = new ListaEncadenadaDoble<>();
        historia.insertarAlFinal(hist);
        Documento doc = new Documento (id, nombre, ubicacion, fecha_ingreso, fecha_expiracion, historia);
        lista.insertarAlFinal(doc);

        int cont = lista.cantidadDeElementos();
        lista.eliminarDuplicados();
        if(lista.cantidadDeElementos() != cont)
            System.out.println("* Ya existe un elemento con ese id *");
        System.out.println("**********************************************************************\n\n");
        return lista;
    }

    static void EliminarDocumento (ListaEncadenadaDoble<Documento> lista, BufferedReader reader, String usuario) throws IOException {
        Pila<Documento> eliminacion = new Pila<>();
        boolean check = true;
        System.out.println("********************** ELIMINACION DE DOCUMENTO **********************");
        while(check) {
            System.out.println(Cpline("* Ingresa el ID del documento a eliminar: "));
            System.out.print("* ");
            Integer id = Integer.parseInt(reader.readLine());
            Documento doc = BuscarDocumentoPorId(lista, id);
            if (doc != null) {
                eliminacion.push(doc);
            }else {
                System.out.println("* No existe un documento con ese id *");
            }
            System.out.println("\n¿Desea ingresar otro elemento a la lista de eliminación?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("\nIngrese elección: ");
            int opc = Integer.parseInt(reader.readLine());
            if(opc != 1)
                check = false;

        }
        eliminacion.eliminarDuplicados();
        while (eliminacion.peek() != null){
            Documento doc = eliminacion.pop();
            System.out.println("ELIMINANDO EL DOCUMENTO " + doc.getId() + "...Ok");
            lista.eliminar(lista.buscar(doc));
            ListaEncadenadaDoble<Historial> historia = doc.getHistoria();
            ZonedDateTime fecha_ingreso = ZonedDateTime.now();
            Historial hist = new Historial(usuario, "" + fecha_ingreso, "Eliminacion del documento");
            historia.insertarAlFinal(hist);
            doc.setHistoria(historia);
        }



        System.out.println("**********************************************************************\n\n");
    }
    
    static void ModificarDocumento (ListaEncadenadaDoble<Documento> lista, BufferedReader reader, String usuario) throws IOException {
        System.out.println("********************** MODIFICACION DE DOCUMENTO *********************");
        System.out.println(Cpline("* Ingresa el ID del documento a modificar: "));
        System.out.print("* ");
        Integer id = Integer.parseInt(reader.readLine());
        Documento doc = BuscarDocumentoPorId(lista, id);
        if (doc != null) {
            ListaEncadenadaDoble<Historial> historia = doc.getHistoria();
            ZonedDateTime fecha_ingreso = ZonedDateTime.now();
            Historial hist;
            System.out.println(Cpline("* 1. Modificar Nombre"));
            System.out.println(Cpline("* 2. Ubicacion __ Seccion"));
            System.out.println(Cpline("* 3. Ubicacion __ Estante"));
            System.out.print("* ");
            Integer seleccion = Integer.parseInt(reader.readLine());
            switch (seleccion) {
                case 1:
                    doc.setNombre(reader);
                    hist = new Historial(usuario, ""+fecha_ingreso, "Modificacion de nombre del documento");
                    historia.insertarAlFinal(hist);
                    doc.setHistoria(historia);
                    break;
                case 2:
                    doc.setUbicacionSeccion(reader);
                    hist = new Historial(usuario, ""+fecha_ingreso, "Modificacion de la seccion/ubicacion del documento");
                    historia.insertarAlFinal(hist);
                    doc.setHistoria(historia);
                    break;
                case 3:
                    doc.setUbicacionEstante(reader);
                    hist = new Historial(usuario, ""+fecha_ingreso, "Modificacion del estante/ubicacion del documento");
                    historia.insertarAlFinal(hist);
                    doc.setHistoria(historia);
                    break;
            }
        }
        
        System.out.println("**********************************************************************\n\n");
    }

    static void buscaDocumentos(ListaEncadenadaDoble<Documento> lista) throws IOException {
        Cola<Documento> busqueda = new Cola<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean check = true;

        System.out.println("************************* BUSCAR DOCUMENTOS *************************");

        while(check){
            System.out.println(Cpline("* Ingrese el id del documento que desea buscar: "));
            System.out.print("* ");
            Integer opc = Integer.parseInt(reader.readLine());
            Documento bus = BuscarDocumentoPorId(lista,opc);
            if(bus != null) {
                busqueda.enqueue(bus);
            }else{
                System.out.println("* No existe un documento con ese id *");
            }
            System.out.println("\n¿Desea ingresar otro elemento a la búsqueda?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("\nIngrese elección: ");
            opc = Integer.parseInt(reader.readLine());
            if(opc != 1)
                check = false;
        }
        busqueda.eliminarDuplicados();
        while(busqueda.peek() != null)
            busqueda.dequeue().print();
        System.out.println("**********************************************************************\n\n");
    }
    static void Lista (ListaEncadenadaDoble<Documento> lista) {
        System.out.println("************************* LISTA DE DOCUMENTO *************************");
        Nodo<Documento> nodo_buscador = lista.getPrimero();
        int i =0;
        while(nodo_buscador != null) {
            nodo_buscador.getDato().print();
            nodo_buscador = nodo_buscador.getSiguiente();
        }
        System.out.println("**********************************************************************\n\n");
    }
    
    static Documento BuscarDocumentoPorId (ListaEncadenadaDoble<Documento> lista, Integer id) {
        Documento doc = null;
        Nodo<Documento> nodo_buscador = lista.getPrimero();
        for(int i = 0; i < lista.cantidadDeElementos(); i++){
            Documento aux = lista.leerDato(i);
            if (aux.getId().equals(id)) {
                doc = aux;
                break;
            }
        }
        return doc;
    }
}
