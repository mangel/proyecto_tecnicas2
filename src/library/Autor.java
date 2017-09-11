package library;

public class Autor {
    
    private String nombre;
    private String mail;
    private String genero;
    
    public Autor(String nombre){
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
    
    @Override
    public String toString(){
        return "{ nombre: " + nombre + ", mail: "+ mail + ", genero: " + genero + "}";
    }
}
