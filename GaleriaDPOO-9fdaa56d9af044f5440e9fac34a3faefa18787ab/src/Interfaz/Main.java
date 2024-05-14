package Interfaz;

import java.util.Scanner;
import Model.GaleriaDeArte;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GaleriaDeArte galeria = new GaleriaDeArte();
        galeria.AgregarAdministrador("Qwer1234", "admin23", "Camilo");
        galeria.AgregarCajero("Qwer1234", "cajero23", "Ernesto");
        galeria.AgregarOperador("Qwer1234", "operador23", "Arturo");
        boolean salir = false;
        
        System.out.println("Bienvenido a la galería de arte.");

        while (!salir) {
            System.out.println("Por favor, seleccione su tipo de usuario:");
            System.out.println("1. Administrador");
            System.out.println("2. Operador");
            System.out.println("3. Cliente");
            System.out.println("4. Cerrar Aplicación");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt()

            switch (opcion) {
                case 1:
                    AdminMain.main(galeria);
                    break;
                case 2:
                    OperadorMain.main(galeria);
                    break;
                case 3:
                    ClienteMain.main(galeria);
                    break;
                case 4:
                    salir = true; // Salir del bucle y finalizar la aplicación
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close(); // Cerrar el escáner al salir del bucle
        System.out.println("Gracias por usar la aplicación. ¡Hasta luego!");
    }
}
