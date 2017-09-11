package library;

import java.util.ArrayList;

public class Biblioteca {
    
    private final ArrayList<Autor> autores;
    private final ArrayList<Libro> libros;
    
    public Biblioteca(){
        autores = new ArrayList<>();
        libros = new ArrayList<>();
    }
    
    public void agregarAutor(String nombre, String mail, String genero){
        if (!autorExiste(nombre))
            autores.add(new Autor(nombre, mail, genero));
    }
    
    public void agregarLibro(String titulo, String autores, int cantidad, int paginas){
        Libro l = new Libro(titulo, cantidad, paginas);
            
        String[] tokens = autores.split("_");
            
        Autor ta = null;
            
        for(String s: tokens) {
            ta = obtenerAutor(s);
            if (ta == null){
                ta = new Autor(s);
                ta.agregarLibro(l);
                l.agregarAutor(ta);
            } else {
                l.agregarAutor(ta);
                ta.agregarLibro(l);
            }
        }
            
        agregarLibro(l);
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
    
    public int contarAutores(){
        return autores.size();
    }
    
    public Libro obtenerLibroConMasPaginas(){
        Libro result = null;
        
        for(Libro l : libros)
            if (result != null){
                if(l.darPaginas() > result.darPaginas())
                    result = l;
            } else
                result = l;
        
        return result;
    }
    
    public Autor obtenerAutorConMasLibros(){
        Autor result = null;
        
        for(Autor a:autores)
            if(result != null){
                if(a.contarLibros() > result.contarLibros())
                    result = a;
            } else
                result = a;
                
        return result;
    }
    
    public boolean editarPaginasLibro(String titulo, int nuevoValorPaginas){
        boolean result = false;
        Libro l = obtenerLibro(titulo);
        
        if (l != null){
            l.editarPaginas(nuevoValorPaginas);
            result = true;
        }
        
        return result;
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
    
    private Autor obtenerAutor(String nombre){
        Autor a = null;
        
        for(Autor ta:autores)
            if(ta.darNombre().equals(nombre)){
                a = ta;
                break;
            }
        
        return a;
    }
    
    private Libro obtenerLibro (String titulo){
        Libro l = null;
        
        for (Libro tl:libros)
            if(tl.darTitulo().equals(titulo)){
                l = tl;
                break;
            }
        
        return l;
    }
    
    private void agregarAutor(Autor autor) {
        if(!autorExiste(autor.darNombre()))
            autores.add(autor);
        else {
            Autor ta = obtenerAutor(autor.darNombre());
            ta.agregarLibros(autor.darLibros());
        }
    }
    
    private void agregarLibro(Libro l){
        if (!libroExiste(l.darTitulo())) {
            libros.add(l);
            
            l.darAutores().forEach((a) -> {
                agregarAutor(a);
            });
        }
    }
}
