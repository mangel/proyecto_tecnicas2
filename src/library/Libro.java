package library;

import java.util.ArrayList;

public class Libro extends ElementoLectura {
    
    private final boolean tapaDura;
    
    public Libro (int numeroCopiasDisponibles, String titulo, int paginas, ArrayList<Autor> autores, boolean tapaDura){
        super(numeroCopiasDisponibles, titulo, paginas, autores);
        
        this.tapaDura = tapaDura;
    }
    
    public boolean esDeTapaDura(){
        return tapaDura;
    }
    
    public boolean tituloContienePalabrasClave(String [] keywords){
        boolean result = false;
        
        for (String k : keywords)
            if (darTitulo().contains(k)){
                result = true;
                break;
            }
        
        return result;
    }
    
    @Override
    public String toString(){
        String as = "";
        boolean hasItems = false;
        
        for(Autor a: darAutores()){
            if (hasItems)
                as += ",";
            
            as += "{ nombre: " + a.darNombre() +"}";
            
            hasItems = true;
        }
        
        return "{ id:" + darId() + " titulo: " + darTitulo() + ", autores:[" + as + "], tapa_dura: " + (tapaDura ? "si" : "no") + ", cantidad: " + darNumeroCopiasDisponibles() + ", paginas: " + darNumeroDePaginas() + "}";
    }
    
}
