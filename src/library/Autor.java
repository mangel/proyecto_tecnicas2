package library;

import java.util.ArrayList;

public class Autor {
    
    private String nombre;
    private String mail;
    private String genero;
    
    private ArrayList<ElementoLectura> escritos;
    
    public Autor(String nombre){
        escritos = new ArrayList<>();
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
    
    public void agregarEscrito(ElementoLectura l){
        boolean existe = false;
        
        for(ElementoLectura tl: escritos){
            if(tl.getClass().equals(l.getClass())){
                if (tl.darTitulo().equals(l.darTitulo()))
                {
                    existe = true;
                    break;
                }
            }
        }
        
        
        if (!existe)
            escritos.add(l);
    }
    
    public void agregarLibro(Libro l){
        boolean existe = false;
        
        for(Libro tl: darLibros())
            if (tl.darTitulo().equals(l.darTitulo()))
            {
                existe = true;
                break;
            }
        
        if (!existe)
            escritos.add(l);
}
    
    public void agregarLibros(ArrayList<Libro> libros){
        for(Libro l:libros)
            agregarLibro(l);
    }
    
    public ArrayList<ElementoLectura> darEscritos(){
        return escritos;
    }
    
    public ArrayList<Libro> darLibros(){
        ArrayList<Libro> result = new ArrayList<>();
        
        for(ElementoLectura e:darEscritos())
            if (e instanceof Libro)
                result.add((Libro)e);
        
        return result;
    }
    
    @Override
    public String toString(){
        return "{ nombre: " + nombre + ", mail: "+ mail + ", genero: " + genero + "}";
    }
}
