package library;

import java.util.ArrayList;

public class Articulo extends ElementoLectura {
    
    private String [] keywords;
    private String URL;
    
    public Articulo (int numeroCopiasDisponibles, String titulo, int paginas, ArrayList<Autor> autores, String URL, String [] keywords){
        super(numeroCopiasDisponibles, titulo, paginas, autores);
        
        this.keywords = keywords;
        this.URL = URL;
    }
    
    @Override
    public String toString(){
        String as = "";
        String ks = "";
        int increment = 1;
        
        boolean hasItems = false;
        
        for(Autor a: darAutores()){
            if (hasItems)
                as += ",";
            
            as += "{ nombre: " + a.darNombre() +"}";
            
            hasItems = true;
        }
        
        hasItems = false;
        
        for(String s: keywords){
            if (hasItems)
                ks += ",";
            
            ks += "{ kw" + (increment++) + ": " + s + "}";
            
            hasItems = true;
        }
        
        return "{ id:" + darId() + " titulo: " + darTitulo() + ", autores:[" + as + "], palabras_clave: [" + ks + "], doi:" + URL + ", cantidad: " + darNumeroCopiasDisponibles() + ", paginas: " + darNumeroDePaginas() + "}";
    }
}
