package library;

import java.util.ArrayList;

public class Libro {
    
    private String titulo;
    private int cantidad;
    private int paginas;
    
    private ArrayList<Autor> autores;
    
    public Libro (String titulo, int cantidad, int paginas){
        this.autores = new ArrayList<>();
        
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.paginas = paginas;
    }
    
    public Libro (String titulo, int cantidad, int paginas, String[] autores){
        this(titulo, cantidad, paginas);
        for(String a : autores)
            this.autores.add(new Autor(a));
    }
    
    public String darTitulo(){
        return titulo;
    }
    
    public ArrayList<Autor> darAutores(){
        return autores;
    }
    
    @Override
    public String toString(){
        String as = "";
        boolean hasItems = false;
        
        for(Autor a: autores){
            if (hasItems)
                as += ",";
            
            as += "{ nombre: " + a.darNombre() +"}";
            
            hasItems = true;
        }
        
        return "{ título: " + titulo + ", autores:[" + as + "], cantidad: " + cantidad + ", páginas: " + paginas + "}";
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
}
