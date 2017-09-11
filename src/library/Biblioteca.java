package library;

import java.util.ArrayList;

public class Biblioteca {
    
    private ArrayList<Autor> autores;
    private ArrayList<Libro> libros;
    
    public Biblioteca(){
        autores = new ArrayList<>();
        libros = new ArrayList<>();
    }
    
    public void agregarAutor(String nombre, String mail, String genero){
        if (!autorExiste(nombre)){
            autores.add(new Autor(nombre, mail, genero));
        }
    }
    
    public void agregarLibro(String titulo, String autores, int cantidad, int paginas){
        if (!libroExiste(titulo)){
            String[] tokens = autores.split("_");
        
            for(String s: tokens)
                if(!autorExiste(s))
                    agregarAutor(s, "", "");

            libros.add(new Libro(titulo, cantidad, paginas, tokens));
        }
    }
    
    public ArrayList<Libro> buscarLibrosPorTitulo(String titulo){
        ArrayList<Libro> result = new ArrayList<>();
        
        String [] keywords = titulo.split("&");
        
        for(Libro l: libros)
            if (l.tituloContienePalabrasClave(keywords))
                result.add(l);
        
        return result;
    }
    
    public ArrayList<Libro> buscarLibrosPorAutor(String nombreAutor){
        ArrayList<Libro> result = new ArrayList<>();
        
        for(Libro l: libros)
            for (Autor a: l.darAutores())
                if (a.darNombre().equalsIgnoreCase(nombreAutor)){
                    result.add(l);
                    break;
                }
        
        return result;
    }
    
    public ArrayList<Autor> darAutores(){
        return autores;
    }
    
    public ArrayList<Libro> darLibros(){
        return libros;
    }
    
    private boolean autorExiste(String nombre){
        boolean result = false;
        for(Autor a : autores)
            if(a.darNombre().equals(nombre)){
                result = true;
                break;
            }
        return result;
    }
    
    private boolean libroExiste(String titulo){
        boolean result = false;
        for(Libro l : libros)
            if(l.darTitulo().equals(titulo)){
                result = true;
                break;
            }
        return result;
    }
}
