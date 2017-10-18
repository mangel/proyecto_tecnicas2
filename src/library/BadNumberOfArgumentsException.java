package library;

import java.io.IOException;

public class BadNumberOfArgumentsException extends IOException {
    private String action;
    private int expected;
    private int received;
    private boolean many;
    private String field;
    
    public BadNumberOfArgumentsException(String action, int expected, int received, boolean many, String field) {
        this.action = action;
        this.expected = expected;
        this.received = received;
        this.many = many;
        this.field = field;
    }
    
    @Override
    public String toString(){
        return "Número incorrecto de parámetros al intentar " + action + ". Se esperaban " + (many? "al menos ": "") + expected + " parámetro(s) y se recibieron" + received + (!field.isEmpty()? " " + field : "") + ".";
    }
}
