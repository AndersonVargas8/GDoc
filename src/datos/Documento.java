package datos;

import java.time.*;
import java.io.BufferedReader;
import java.io.IOException;

import estructuras.listas.ListaEncadenadaDoble;
import estructuras.listas.Nodo;

import java.io.Serializable;
import java.util.Objects;
import static logica.Controlador.Cpline;

/**
 *
 * @author andres
 */
public class Documento implements Serializable{
    private Integer id;
    private String nombre;
    private Ubicacion ubicacion;
    private ZonedDateTime fecha_ingreso;
    private ZonedDateTime fecha_expiracion;
    private ListaEncadenadaDoble<Historial> historia;
    
    public Documento(Integer id, String nombre, Ubicacion ubicacion, ZonedDateTime fecha_ingreso, ZonedDateTime fecha_expiracion, ListaEncadenadaDoble<Historial> historia) {
        this.id = id;
        this.nombre = nombre;                                                           //Constructor
        this.ubicacion = ubicacion;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_expiracion = fecha_expiracion;
        this.historia = historia;
    }
    
    public Integer getId () {                                                           //    |                          
        return this.id;                                                                 //    |
    }                                                                                   //    |
                                                                                        //    |
    public ListaEncadenadaDoble<Historial> getHistoria () {                             //    |
        return this.historia;                                                           //    |
    }                                                                                   //    |
                                                                                        //    |
    public void setUbicacionSeccion (BufferedReader reader) throws IOException {        //    |
        this.ubicacion.setSeccion(reader);                                              //    |
    }                                                                                   //    |
                                                                                        //    |
    public void setUbicacionEstante (BufferedReader reader) throws IOException {        //    |     Gets correspondientes a todas las variables de la clase
        this.ubicacion.setEstante(reader);                                              //    |
    }                                                                                   //    |
                                                                                        //    |
    public void setNombre(BufferedReader reader) throws IOException {                   //    |
        System.out.print("* Nuevo nombre:  ");                                          //    |
        String nuevo_nombre = reader.readLine();                                        //    |
        this.nombre = nuevo_nombre;                                                     //    |
    }                                                                                   //    |
                                                                                        //    |
    public void setHistoria (ListaEncadenadaDoble<Historial> historia) {                //    |
        this.historia = historia;                                                       //    |
    }                                                                                   //    |
    
    public void print() {                                                               // imprime en consola las variables adjuntas al documentro subido
        Nodo<Historial> nodo_buscador = this.historia.getPrimero();                     // Inizializa un nodo de una lita doblemente encadena
        System.out.println("**********************************************************************");
        System.out.println(Cpline("* ID: " + this.id));
        System.out.println(Cpline("* NOMBRE: " + this.nombre));
        System.out.println(Cpline("* UBICACION: "));
        this.ubicacion.print();
        System.out.println(Cpline("* FECHA DE INGRESO: " + this.fecha_ingreso));
        System.out.println(Cpline("* FECHA DE EXPIRACION: " + this.fecha_expiracion));
        System.out.println(Cpline("* HISTORIAL: "));
        while (nodo_buscador != null) {                                                  //Busca e imprime la informacion del hisotrial correspondiente a el documento adjunto
          nodo_buscador.getDato().print();
          nodo_buscador = nodo_buscador.getSiguiente();
        }
        System.out.println("**********************************************************************");
    }
    
    @Override
    public boolean equals(Object dato){                                                 //Comprueba que el id ingresado sea el mismo que el de un id ya existente
        Documento comparar = (Documento) dato;
        return Objects.equals(this.id, comparar.getId());
    }

    public String toString(){                                                           //Convierte id int a String
        return String.valueOf(this.id);
    }
}
