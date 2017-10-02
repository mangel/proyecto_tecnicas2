package library;

public abstract class ElementoBiblioteca {
    private static int Secuencia = 1;
    
    private int id;
    private final int totalUnidades;
    private int numeroCopiasDisponibles;
    
    public ElementoBiblioteca(int numeroCopiasDisponibles){
        this.id = Secuencia++;
        this.numeroCopiasDisponibles = numeroCopiasDisponibles;
        totalUnidades = numeroCopiasDisponibles;
    }
    
    public int darId(){
        return id;
    }
    
    public int darNumeroCopiasDisponibles(){
        return numeroCopiasDisponibles;
    }
    
    public boolean prestar(){
        boolean result = false;
        
        if (numeroCopiasDisponibles > 0){
            numeroCopiasDisponibles--;
            result = true;
        }
        
        return result;
    }
    
    public boolean recibirCopia(){
        boolean result = false;
        
        if (numeroCopiasDisponibles < totalUnidades){
            numeroCopiasDisponibles++;
            result = true;
        }
        
        return result;
    }
}
