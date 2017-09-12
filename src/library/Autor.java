package library;

import java.util.ArrayList;

public class Autor {
    
    private String nombre;
    private String mail;
    private String genero;
    
    private ArrayList<Libro> libros;
    
    public Autor(String nombre){
        libros = new ArrayList<>();
        this.nombre = nombre;
    }
    
    public Autor(String nombre, String mail, String genero){
        this(nombre);
        this.mail = mail;
        this.genero = genero;
    }
    
    public String darNombre(){
        return nombre;
    }
    
    public void agregarLibro(Libro l){
        boolean existe = false;
        
        for(Libro tl: libros)
            if (tl.darTitulo().equals(l.darTitulo()))
            {
                existe = true;
                break;
            }
        
        if (!existe)
            libros.add(l);
    }
    
    public void agregarLibros(ArrayList<Libro> libros){
        for(Libro l:libros)
            agregarLibro(l);
    }
    
    public ArrayList<Libro> darLibros(){
        return libros;
    }
    
    public int contarLibros(){
        return libros.size();
    }
    
    @Override
    public String toString(){
        return "{ nombre: " + nombre + ", mail: "+ mail + ", genero: " + genero + "}";
    }
}
