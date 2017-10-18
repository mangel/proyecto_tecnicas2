package library;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {
    
    private final Biblioteca biblioteca;
    
    private final Scanner sc;
    private short option;
    
    public Menu(){
        biblioteca = new Biblioteca();
        sc = new Scanner(System.in);
    }
    
    public void desplegarMenuBiblioteca(){
        String temp = "";
        do{ 
            try {
                System.out.println("Menú biblioteca, digite alguna de las siguientes opciones: ");
                System.out.println("\t1. Ingresar autor.");
                System.out.println("\t2. Ingresar libro.");
                System.out.println("\t3. Buscar libro por título.");
                System.out.println("\t4. Buscar libro por autor.");
                System.out.println("\t5. Mostrar todos los libros.");
                System.out.println("\t6. Mostrar todos los autores.");
                System.out.println("\t7. Ingresar artículo.");
                System.out.println("\t8. Ingresar PC.");
                System.out.println("\t9. Mostrar elementos.");
                System.out.println("\t10. Registrar entrada.");
                System.out.println("\t11. Registrar salida.");
                System.out.println("\t12. Salir.");
                
                temp = sc.nextLine();
                
                if (!temp.matches("\\A(1|2|3|4|5|6|7|8|9|10|11|12){1}\\z"))
                    throw new BadInputFormatException("seleccionar opción", "opción", "número-opción");
               
                option = Short.parseShort(temp);
                
                switch(option){
                    case 1:
                        manejarOpcionIngresarAutor();
                        break;
                    case 2:
                        manejarOpcionIngresarLibro();
                        break;
                    case 3:
                        manejarOpcionBuscarLibrosPorTitulo();
                        break;
                    case 4:
                        manejarOpcionBuscarLibrosPorAutor();
                        break;
                    case 5:
                        manejarOpcionMostrarLibros();
                        break;
                    case 6:
                        manejarOpcionMostrarAutores();
                        break;
                    case 7:
                        manejarOpcionIngresarArticulo();
                        break;
                    case 8:
                        manejarOpcionIngresarPC();
                        break;
                    case 9:
                        manejarOpcionMostrarElementos();
                        break;
                    case 10:
                        manejarOpcionRegistrarEntrada();
                        break;
                    case 11:
                        manejarOpcionRegistrarSalida();
                        break;
                }
            } catch (BadNumberOfArgumentsException ex) {
                System.out.println("ERROR: " + ex);
            } catch (BadInputFormatException ex) {
                System.out.println("ERROR: " + ex);
            } catch (UnavailableItemUnitsException ex) {
                System.out.println("ERROR: " + ex);
            } catch (UnavailableItemException ex) {
                System.out.println("ERROR: " + ex);
            }
        } while(option != 12);
    }
    
    private void manejarOpcionIngresarAutor() throws BadNumberOfArgumentsException{
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 1, agregar autor.");
        
        System.out.println("Ingrese la información del autor:");
        
        String input = innerSc.nextLine();
        String output = "El autor ha sido agregado";
        
        String [] tokens = input.split("&");
        
        if (tokens.length == 3)
            biblioteca.agregarAutor(tokens[0], tokens[1], tokens[2]);
        else
            throw new BadNumberOfArgumentsException("agregar un autor", 3, tokens.length, false, "");
        
        System.out.println("Salida: ");
        System.out.println(output);
    }
    
    private void manejarOpcionIngresarLibro() throws BadNumberOfArgumentsException, BadInputFormatException{
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 2, agregar libro.");
        
        System.out.println("Ingrese la información del nuevo libro:");
        
        String input = innerSc.nextLine();
        String output = "El libro ha sido agregado.";
        
        String [] tokens = input.split("&");
        
        if (tokens.length == 5){
            if(!tokens[3].matches("\\A\\d+\\z"))
                throw new BadInputFormatException("agregar libro", "cantidad", "numero");
            
            if(!tokens[4].matches("\\A\\d+\\z"))
                throw new BadInputFormatException("agregar libro", "copias", "numero");
            
            biblioteca.agregarLibro(tokens[0], tokens[1], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), tokens[2].equalsIgnoreCase("si"));
        }
        else
            throw new BadNumberOfArgumentsException("agregar un libro", 5, tokens.length, false, "");
        
        System.out.println("Salida: ");
        System.out.println(output);
    }
    
    private void manejarOpcionBuscarLibrosPorTitulo(){
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opción 3, buscar libro por título.");
        
        System.out.println("Ingresar las palabras clave:");
        
        String input = innerSc.nextLine();
        
        ArrayList<Libro> result = biblioteca.buscarLibrosPorTitulo(input);
        
        mostrarLibros(result);
    }
    
    private void manejarOpcionBuscarLibrosPorAutor(){
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opción 4, buscar libros por autor.");
        
        System.out.println("Ingrese el nombre del autor:");
        
        String input = innerSc.nextLine();
        
        ArrayList<Libro> result = biblioteca.buscarLibrosPorAutor(input);
        
        mostrarLibros(result);
    }
    
    private void manejarOpcionMostrarAutores(){
        System.out.println("Usted ha seleccionado la opción 4, mostrar autores.");
        for (Autor a : biblioteca.darAutores())
            System.out.println(a.toString());
    }
    
    private void manejarOpcionMostrarLibros(){
        System.out.println("Usted ha seleccionado la opción 5, mostrar libros.");
        mostrarLibros(biblioteca.darLibros());
    }
       
    private void manejarOpcionIngresarArticulo() throws BadNumberOfArgumentsException{
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 7, agregar artículo.");
        
        System.out.println("Ingrese los datos del nuevo artículo:");
        
        String input = innerSc.nextLine();
        String output = "El artículo ha sido agregado.";
        
        String [] tokens = input.split("&");
        
        if (tokens.length == 6){
            if (tokens[1].split("_").length == 0)
                throw new BadNumberOfArgumentsException("agregar artículo", 1, 0, true, "para el parámetro de autores");
            if (tokens[2].split("_").length == 0)
                throw new BadNumberOfArgumentsException("agregar un artículo", 1, tokens.length, true, "para el parámetro de keywords");
            biblioteca.agregarArticulo(tokens[0], tokens[1], tokens[2].split("_"), tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
        }
        else
            throw new BadNumberOfArgumentsException("agregar un artículo", 6, tokens.length, false, "");
        
        System.out.println("Salida: ");
        System.out.println(output);
    }
    
    private void manejarOpcionIngresarPC() throws BadNumberOfArgumentsException, BadInputFormatException{
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 8, agregar PC.");
        
        System.out.println("Ingrese los datos del nuevo PC:");
        
        String input = innerSc.nextLine();
        String output = "El PC ha sido agregado.";
        
        String [] tokens = input.split("&");
        
        if (tokens.length == 3){
            if (!tokens[0].matches("\\A\\d+\\z"))
                throw new BadInputFormatException("agregar PC", "número de serie", "número");
            
            if (!tokens[2].matches("\\A\\d+\\z"))
                throw new BadInputFormatException("agregar PC", "número de serie", "número");
            
            biblioteca.agregarPC(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]));
        }
        else
            throw new BadNumberOfArgumentsException("agregar un PC", 3, tokens.length, false, "");
        
        System.out.println("Salida: ");
        System.out.println(output);
    }
    
    private void manejarOpcionMostrarElementos(){
        System.out.println("Usted ha seleccionado la opción 9, mostrar elementos.");
        mostrarElementos(biblioteca.darElementos());
    }
    
    private void manejarOpcionRegistrarEntrada() throws BadInputFormatException{
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 10, registrar entrada.");
        
        System.out.print("Ingrese el id del elemento: ");
        
        String input = innerSc.nextLine();
        
        String output = "La disponibilidad del elemento ha sido incrementada.";
        
        if (!input.matches("\\A\\d+\\z"))
            throw new BadInputFormatException("Registrar entrada", "identificador", "número");
        
        boolean result = biblioteca.registrarEntrada(Integer.parseInt(input));
        
        if (!result)
            output = "Ha ocurrido un error durante el proceso de devolución.";
        
        System.out.println("Salida: ");
        System.out.println(output);
    }
    
    private void manejarOpcionRegistrarSalida() throws BadInputFormatException, UnavailableItemUnitsException, UnavailableItemException{
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 11, registrar salida.");
        
        System.out.print("Ingrese el id del elemento: ");
        
        String input = innerSc.nextLine();
        
        String output = "La disponibilidad del elemento ha sido decrementada.";
        
        if (!input.matches("\\A\\d+\\z"))
            throw new BadInputFormatException("regsitrar salida", "identificador", "número");
        
        boolean result = biblioteca.registrarSalida(Integer.parseInt(input));
        
        if (!result)
            output = "Ha ocurrido un error durante el proceso de salida.";
        
        System.out.println("Salida: ");
        System.out.println(output);
    }
    
    private void mostrarLibros(ArrayList<Libro> libros){
        for (Libro l : libros)
            System.out.println(l.toString());
    }
    
    private void mostrarElementos(ArrayList<ElementoBiblioteca> elementos){
        for(ElementoBiblioteca e:elementos)
            System.out.println(e);
    }
}
