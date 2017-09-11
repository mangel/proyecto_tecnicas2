package library;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    
    private final Biblioteca biblioteca;
    
    private final Scanner sc;
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
            System.out.println("\t8. Mostrar número de autores.");
            System.out.println("\t9. Mostrar libro con más páginas.");
            System.out.println("\t10. Mostrar autor con más libros.");
            System.out.println("\t11. Editar paginas de libro.");
            
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
                case 8:
                    manejarOpcionMostrarNumeroAutores();
                    break;
                case 9:
                    manejarOpcionMostrarLibroConMasPaginas();
                    break;
                case 10:
                    manejarOpcionMostrarAutorConMasLibros();
                    break;
                case 11:
                    manejarOpcionEditarPaginasLibro();
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
    
    private void manejarOpcionMostrarNumeroAutores(){
        System.out.println("Usted ha seleccionado la opción 8, contar autores.");
        System.out.println("Salida:");
        System.out.println("La biblioteca cuenta con " + biblioteca.contarAutores() + ".");
    }
    
    private void manejarOpcionMostrarLibroConMasPaginas(){
        System.out.println("Usted ha seleccionado la opción 9, libro con más páginas.");
        
        Libro l = biblioteca.obtenerLibroConMasPaginas();
        
        System.out.println("Salida:");
        System.out.println(l);
    }
    
    private void manejarOpcionMostrarAutorConMasLibros(){
        System.out.println("Usted ha seleccionado la opción 10, dar autor con más libros.");
        System.out.println("Salida:");
        
        Autor a = biblioteca.obtenerAutorConMasLibros();
        
        System.out.println("El autor con más libros en la biblioteca es " + a.darNombre() + " con un total de " + a.contarLibros());
    }
    
    private void manejarOpcionEditarPaginasLibro(){
        Scanner innerSc = new Scanner(System.in);
        System.out.println("Usted ha seleccionado la opcion 11 (bono), editar copias.");
        System.out.print("Ingrese los datos del libro: ");
        
        String input = innerSc.nextLine();
        
        String[] tokens = input.split("&");
        
        if (tokens.length == 2){
            boolean result = biblioteca.editarPaginasLibro(tokens[0], Integer.parseInt(tokens[1]));
            
            if (result){
                System.out.println("Los nuevos datos del libro son: ");
                Libro l = biblioteca.buscarLibrosPorTitulo(tokens[0]).get(0);
                System.out.println(l);
            } else
                System.out.println("El libro no existe.");
        }else
            System.out.println("Error de formato.");
        
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
