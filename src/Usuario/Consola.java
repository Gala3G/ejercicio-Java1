package Usuario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Datos.Datos;
import Logica.Validaciones;

public class Consola {

	public static void MenuPrincipalPRUEBA1() {

		System.out.println("\nBienvenido a su web de noticias \n");
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. AÑADIR (fichero)");
		System.out.println("2. AÑADIR (temporal)");
		System.out.println("3. BUSCAR");
		System.out.println("4. SALIR");
		System.out.println("Que desea hacer?\n");

	} 

	public static void MenuBuscar1() {

		System.out.println("\nMENU DE BUSQUEDAS");
		System.out.println("1. Fecha");
		System.out.println("2. Autor");
		System.out.println("3. Noticia(texto)");
		System.out.println("4. Mostrar todo");
		System.out.println("5. En todos los atributos");
		System.out.println("6. REGRESAR A MENU\n");
		System.out.println("Que desea hacer?\n");

	}

	public static void resultadoFichero() {
		System.out.println("\nRESULTADO DE BUSQUEDA EN FICHERO");
	}

	public static void resultadoArray() {
		System.out.println("\nRESULTADO DE BUSQUEDA EN ARRAYS");
	}

	public static String scannerString() {
		Scanner lector = new Scanner(System.in);
		String respuesta = " ";

		try {

			respuesta = lector.nextLine();

		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}

		return respuesta;
	}

	public static char scannerChar() {
		Scanner lector = new Scanner(System.in);
		char respuesta = ' ';

		try {
			respuesta = lector.next().charAt(0);
			// respuesta = Character.toUpperCase(respuesta);

		} catch (InputMismatchException ex) {
			System.err.println("ERROR FATAL, no se ha introducido una letra");
			respuesta = lector.next().charAt(0);
		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}

		return respuesta;
	}

	// --------------------SIN USAR, PERO FUNCIONA--------------------//
	public static String absorverScanner() {
		Scanner lector = new Scanner(System.in);
		return lector.next();
	}

}
