package library;

import java.util.ArrayList;

public class Biblioteca {
    
    private final ArrayList<Autor> autores;
    private final ArrayList<ElementoBiblioteca> elementos;
    
    public Biblioteca(){
        autores = new ArrayList<>();
        elementos = new ArrayList<>();
    }
    
    public void agregarAutor(String nombre, String mail, String genero){
        if (!autorExiste(nombre))
            autores.add(new Autor(nombre, mail, genero));
    }
    
    public void agregarLibro(String titulo, String autores, int cantidad, int paginas, boolean tapaDura) throws BadNumberOfArgumentsException{
        
        String[] tokens = autores.split("_");
        
        if (tokens.length == 0)
            throw new BadNumberOfArgumentsException("agregar libro", 1, 0, true, "para el parámetro de autores.");
        
        ArrayList<Autor> listaAutores = new ArrayList<>();
        Autor ta = null;
            
        for(String s: tokens) {
            ta = obtenerAutor(s);
            if (ta == null)
                listaAutores.add(new Autor(s));
             else
                listaAutores.add(ta);
        }
        
        Libro l = new Libro(cantidad, titulo, paginas, listaAutores, tapaDura);
        
        agregarLibro(l);
    }
    
    public void agregarArticulo(String titulo, String autores, String [] keywords, String URL, int cantidad, int paginas) {
        
        String[] tokens = autores.split("_");
            
        ArrayList<Autor> listaAutores = new ArrayList<>();
        Autor ta = null;
            
        for(String s: tokens) {
            ta = obtenerAutor(s);
            if (ta == null)
                listaAutores.add(new Autor(s));
             else
                listaAutores.add(ta);
        }
        
        Articulo a = new Articulo(cantidad, titulo, paginas, listaAutores, URL, keywords);
        
        agregarArticulo(a);
    }
    
    public void agregarPC(int numeroDeSerie, String sistemaOperativo, int cantidadDisponible){
        PC pc = new PC(cantidadDisponible, numeroDeSerie, sistemaOperativo);
        
        agregarPC(pc);
    }
    
    public ArrayList<Libro> buscarLibrosPorTitulo(String titulo){
        ArrayList<Libro> result = new ArrayList<>();
        
        String [] keywords = titulo.split("&");
        
        for(Libro l: darLibros())
            if (l.tituloContienePalabrasClave(keywords))
                result.add(l);
        
        return result;
    }
    
    public ArrayList<Libro> buscarLibrosPorAutor(String nombreAutor){
        ArrayList<Libro> result = new ArrayList<>();
        
        for(Libro l: darLibros())
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
        ArrayList<Libro> result = new ArrayList<>();
        
        for(ElementoLectura e:darEscritos())
            if ((ElementoLectura)e instanceof Libro)
                result.add((Libro)e);
        
        return result;
    }
    
    public ArrayList<Articulo> darArticulos(){
        ArrayList<Articulo> result = new ArrayList<>();
        
        for(ElementoLectura e:darEscritos())
            if ((ElementoLectura)e instanceof Articulo)
                result.add((Articulo)e);
        
        return result;
    }
    
    public ArrayList<ElementoBiblioteca> darElementos(){
        return elementos;
    }
        
    public boolean registrarEntrada(int id){
        boolean result = false;
        
        ElementoBiblioteca e = buscarElemento(id);
        
        if(e != null)
            result = e.recibirCopia();
        
        return result;
    }
    
    public boolean registrarSalida(int id) throws UnavailableItemUnitsException, UnavailableItemException{
        boolean result = false;
        
        ElementoBiblioteca e = buscarElemento(id);
        
        if(e != null)
            result = e.prestar();
        else
            throw new UnavailableItemException(id);
        
        return result;
    }
    
    private ArrayList<ElementoLectura> darEscritos(){
        ArrayList<ElementoLectura> result = new ArrayList<>();
        
        for(ElementoBiblioteca e:elementos)
            if(e instanceof ElementoLectura)
                result.add((ElementoLectura)e);
        
        return result;
    }
    
    private ArrayList<PC> darPCs(){
        ArrayList<PC> result = new ArrayList<>();
        
        for(ElementoBiblioteca e:elementos)
            if(e instanceof PC)
                result.add((PC)e);
        
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
        for(Libro l : darLibros())
            if(l.darTitulo().equals(titulo)){
                result = true;
                break;
            }
        return result;
    }
    
    private boolean articuloExiste(String titulo){
        boolean result = false;
        for(Articulo a : darArticulos())
            if(a.darTitulo().equals(titulo)){
                result = true;
                break;
            }
        return result;
    }
    
    private boolean PCExiste(int numeroDeSerie){
        boolean result = false;
        for(PC pc : darPCs())
            if(pc.darNumeroDeSerie() == numeroDeSerie){
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
            elementos.add(l);
            
            l.darAutores().forEach((a) -> {
                agregarAutor(a);
            });
        }
    }
    
    private void agregarArticulo(Articulo a){
        if (!articuloExiste(a.darTitulo())) {
            elementos.add(a);
            
            a.darAutores().forEach((au) -> {
                agregarAutor(au);
            });
        }
    }
    
    private void agregarPC(PC pc){
        if (!PCExiste(pc.darNumeroDeSerie()))
            elementos.add(pc);
    }
    
    private ElementoBiblioteca buscarElemento(int id){
        ElementoBiblioteca result = null;
        
        for(ElementoBiblioteca eb:elementos)
            if (eb.darId() == id)
                result = eb;
        
        return result;
    }
}
