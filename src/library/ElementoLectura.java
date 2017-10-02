package library;

import java.util.ArrayList;

public class ElementoLectura extends ElementoBiblioteca {

    private String titulo;
    private int numeroDePaginas;
    private ArrayList<Autor> autores;

    public ElementoLectura(int numeroCopiasDisponibles, String titulo, int numeroDePaginas, ArrayList<Autor> autores) {
        super(numeroCopiasDisponibles);
        
        this.autores = new ArrayList<>();
        
        for(Autor autor:autores){
            this.autores.add(autor);
            autor.agregarEscrito(this);
        }
        
        this.titulo = titulo;
        this.numeroDePaginas = numeroDePaginas;
    }

    public ArrayList<Autor> darAutores(){
        return autores;
    }
    
    public String darTitulo(){
        return titulo;
    }
    
    public int darNumeroDePaginas(){
        return numeroDePaginas;
    }
    
    public void agregarAutor(Autor a){
        boolean existe = false;
        
        for (Autor ta: autores){
            if(ta.darNombre().equals(a.darNombre())){
                existe = true;
                break;
            }
        }
        
        if (!existe){
            autores.add(a);
        }
    }
    
}
