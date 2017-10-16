package library;

import java.io.IOException;

public class BadInputFormatException extends IOException {
    private String action;
    private String parameterName;
    private String expectedType;
    
    public BadInputFormatException(String action, String parameterName, String expectedType) {
        this.action = action;
        this.parameterName = parameterName;
        this.expectedType = expectedType;
    }
    
    @Override
    public String toString(){
        return "Formato de parámetro no válido. Al " + action + " para el parámetro '" + parameterName + "' se esperaba el tipo '" + expectedType + "'.";
    }
}
