package library;

public class PC extends ElementoBiblioteca {
    
    public int numeroDeSerie;
    public String sistemaOperativo;

    public PC(int numeroCopiasDisponibles, int numeroDeSerie, String sistemaOperativo) {
        super(numeroCopiasDisponibles);
        
        this.numeroDeSerie = numeroDeSerie;
        this.sistemaOperativo = sistemaOperativo;
    }
    
    public int darNumeroDeSerie(){
        return numeroDeSerie;
    }
    
    @Override
    public String toString(){
        return "{ id:" + darId() + " serial: " + numeroDeSerie + ", sistema_operativo:" + sistemaOperativo + ", cantidad: " + darNumeroCopiasDisponibles() + "}";
    }
}
