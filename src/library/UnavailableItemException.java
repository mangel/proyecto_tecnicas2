package library;

public class UnavailableItemException extends Exception {
    private int id;
    
    public UnavailableItemException(int id) {
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    @Override
    public String toString()
    {
        return "El elmento con id " + id + " no existe en la bilblioteca.";
    }
}
