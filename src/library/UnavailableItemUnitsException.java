package library;

public class UnavailableItemUnitsException extends UnavailableItemException {
    
    public UnavailableItemUnitsException(int id) {
        super(id);
    }
    
    @Override
    public String toString()
    {
        return "No hay unidades disponibles para el elemento " + getId() + ".";
    }
}
