package library;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    
    private Biblioteca biblioteca;
    
    private Scanner sc;
    private short option;
    
    public Menu(){
        biblioteca = new Biblioteca();
        sc = new Scanner(System.in);
    }
    
    public void desplegarMenuBiblioteca(){
        do{
            System.out.println("Menú biblioteca, digite alguna de las siguientes opciones: ");
            System.out.println("\t1. Ingresar autor.");
            System.out.println("\t2. Ingresar libro.");
            System.out.println("\t3. Buscar libro por título.");
            System.out.println("\t4. Buscar libro por autor.");
            System.out.println("\t5. Mostrar todos los libros.");
            System.out.println("\t6. Mostrar todos los autores.");
            System.out.println("\t7. Salir.");
            
            option = sc.nextShort();

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
            }
        } while(option != 7);
    }
    
    private void manejarOpcionIngresarAutor(){
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 1, agregar autor.");
        
        System.out.println("Ingrese la información del autor:");
        
        String input = innerSc.nextLine();
        String output = "El autor ha sido agregado";
        
        String [] tokens = input.split("&");
        
        
        if (tokens.length == 3)
            biblioteca.agregarAutor(tokens[0], tokens[1], tokens[2]);
        else
            output = "Error de formato de entrada";
        
        System.out.println("Salida: ");
        System.out.println(output);
    }
    
    private void manejarOpcionIngresarLibro(){
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 2, agregar libro.");
        
        System.out.println("Ingrese la información del nuevo libro:");
        
        String input = innerSc.nextLine();
        String output = "El libro ha sido agregado.";
        
        String [] tokens = input.split("&");
        
        if (tokens.length == 4){
            biblioteca.agregarLibro(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        }
        else
            output = "Error de formato de entrada";
        
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
        for (Autor a : biblioteca.darAutores())
            System.out.println(a.toString());
    }
    
    private void manejarOpcionMostrarLibros(){
        mostrarLibros(biblioteca.darLibros());
    }
    
    private void mostrarLibros(ArrayList<Libro> libros){
        for (Libro l : libros)
            System.out.println(l.toString());
    }
    
//    private void mostrarAutores(ArrayList<Autor> autores){
//        for (Autor a : autores)
//            System.out.println(a.toString());
//    }
}
