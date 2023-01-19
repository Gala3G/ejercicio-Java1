package Logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.cert.TrustAnchor;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import Usuario.*;
import Datos.Datos;
import Datos.GestionDatos;
import Usuario.Consola;

public class Operaciones {

	// ....................METODOS FORMATO DE TEXTO..........................//

	public static String formatoTexto(String dato) {
		String bloque1;
		String bloque2;

		// para que la primera letra sea en May al inicio y despues del punto
		dato.trim();// quito espacios y luego se los doy

		// CASO 1. recibo string con "."
		if (dato.contains(".") && dato.length() > dato.indexOf('.') + 1) {

			// tengo que dividirlo para ver mejor la estructura
			// bloque1: Primer caracter en May, resto Min

			dato = dato.substring(0, 1).toUpperCase() + dato.substring(1, dato.indexOf('.')).toLowerCase() + ". "

			// bloque2: Despues del punto y espacio, primer caract May y resto en Min
					+ dato.substring(dato.indexOf('.') + 2, dato.indexOf('.') + 3).toUpperCase()
					+ dato.substring(dato.indexOf('.') + 3, dato.length()).toLowerCase();

			// No esta preparado para largos textos con varios puntos, tendría que buscar
			// otra forma más productiva

			// CASO 2. recibo string sin "."
		} else {
			dato = dato.substring(0, 1).toUpperCase() + dato.substring(1, dato.length()).toLowerCase();
		}

		return dato;

	}

	public static String formatoAutor(String dato) {
		boolean falso;

		// Validar 1 nombre y 1 apellido para transformar
		// Problema a resolver:
		// si me ponene solo nombre y un espacio (sin apellido) recibo Exception

		try {

			do {
				// Solucion: metodo endsWith = maravilla
				if (dato.endsWith(" ")) {
					System.err.println("Ojo, has puesto un espacio al final y dará error");
					dato = Consola.scannerString();
				}

				// si no hay espacios no hay 1 apellido
				if (!dato.contains(" ")) {
					System.err.println("Dime nombre y primer apellido");
					dato = Consola.scannerString();
				}

			} while (!dato.contains(" "));

			// pasadas todas las validaciones, transformo caracteres a May y Min
			dato = dato.substring(0, 1).toUpperCase() + dato.substring(1, dato.indexOf(' ') + 1).toLowerCase()
					+ dato.substring(dato.indexOf(' ') + 1, dato.indexOf(' ') + 2).toUpperCase()
					+ dato.substring(dato.indexOf(' ') + 2, dato.length()).toLowerCase();

		} catch (Exception e) {
			System.err.println("Error esta en formato");
		}

		return dato;
	}

	// ----------------METODOS GUARDAR----------------------------------//

	

	
	// ----------------------METODOS BUSCAR EN-------------------------------------//
	// -------------------------FICHERO------------------------------------//

	public static void busquedas(String ruta, String id, String busqueda) { // id será fecha, autor y noticia, segun el
																			// caso

		FileReader fichero = null;
		BufferedReader lector = null;
		String buscarEnFichero;
		boolean falso;
		String buscar;
		String copiaOriginal;
		String ficheroOriginal;
		int contador=0; 

		try {
			fichero = new FileReader(ruta);
			lector = new BufferedReader(fichero);
			falso = true;

			// antes de la busqueda, quiero asegurarme que no habra conflictos con MAY y
			// MIN.

			// Almaceno en copias para ajustar 

			while ((buscarEnFichero = lector.readLine()) != null) {

				ficheroOriginal = buscarEnFichero; // 1. almaceno la linea que vaya leyendo
				copiaOriginal = buscarEnFichero.toLowerCase(); // 2. almaceno todo fichero en MIN

				if (copiaOriginal.contains(busqueda.toLowerCase())) { // 3. si en MIN está, busco el length del string

					if (copiaOriginal.contains(id.toLowerCase()) && copiaOriginal.contains(busqueda.toLowerCase())) { // si
						contador++;
						System.out.println(ficheroOriginal);
						
						falso = false;
					}

				}
			}

			if (falso) {
				System.out.println("NO, no esta");
			}else {
				System.out.println("Y SI, si esta! Se ha encontrado estas veces: " + contador);
				
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error inesperado");
			e.printStackTrace();
		} finally {
			try {
				if (lector != null) {
					lector.close();
				}
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.err.println("Error al cerrar el fichero");
				e.printStackTrace();
			}

		}

	}

	// -----------------------------ARRAY------------------------------------//



	public static void buscarArray(String palabra, ArrayList<Datos> fichero) {
		boolean falso = true;
		String autor;
		String fecha;
		String texto;

		try {

			for (int i = 0; i < fichero.size(); i++) {
				fecha = fichero.get(i).getFecha().toString();
				autor = fichero.get(i).getAutor().toLowerCase();
				texto = fichero.get(i).getTexto().toLowerCase();
				if (fecha.contains(palabra)) {
					System.out.println("SI, esta");
					System.out.println(fichero.get(i).getFecha() + " fue publicado por: " + fichero.get(i).getAutor());
					falso = false;
				} else if (autor.contains(palabra.toLowerCase())) {
					System.out.println("SI, esta");
					System.out.println(fichero.get(i).getAutor() + " publico en: " + fichero.get(i).getFecha());
					falso = false;

				} else if (texto.contains(palabra.toLowerCase())) {
					System.out.println("SI, esta");
					System.out.println(palabra + " esta en la noticia:  " + fichero.get(i).getTexto());
					falso = false;

				}
			}
			if (falso) {
				System.out.println("NO, no esta");
			}

		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}
	}

	public static void buscarFecha2(LocalDate fecha, ArrayList<Datos> fichero) {
		boolean falso = true;
		LocalDate prueba;
		try {

			for (int i = 0; i < fichero.size(); i++) {

				if (fichero.get(i).getFecha().equals(fecha)) {
					System.out.println("SI, esta");
					System.out
							.println(fichero.get(i).getFecha() + " publico por el autor " + fichero.get(i).getAutor());
					falso = false;
				}
			}
			if (falso) {
				System.out.println("....PERO NO, esta fecha no esta en la lista de noticias");
			}

		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}
	}

	// -------------------------------------ESTOS METODOS FUNCIONAN--------------------------------//
	// ..............................pero no han sido usados para el ejercicio.....................//
	public static void leerFicheroFECHA(String ruta, String fecha) {

		FileReader fichero = null;
		BufferedReader lector = null;
		String cadena;
		boolean falso;

		try {
			fichero = new FileReader(ruta);
			lector = new BufferedReader(fichero);
			falso = true;
			while ((cadena = lector.readLine()) != null) {
				if (cadena.contains(fecha)) {
					System.out.println("Y SI, si esta! Hay una noticia con esa fecha");
					falso = false;
				}
			}
			if (falso) {
				System.out.println("....PERO NO, no hay noticia guardada con esa fecha");
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error inesperado");
			e.printStackTrace();
		} finally {
			try {
				if (lector != null) {
					lector.close();
				}
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.err.println("Error al cerrar el fichero");
				e.printStackTrace();
			}

		}

	}
	public static void buscarAutor(String palabra, ArrayList<Datos> fichero) {
		boolean falso = true;
		String prueba;
		try {

			for (int i = 0; i < fichero.size(); i++) {
				prueba = fichero.get(i).getAutor();
				if (prueba.contains(palabra)) {
					System.out.println("SI, esta");
					System.out.println(fichero.get(i).getAutor() + " publico en " + fichero.get(i).getFecha());
					falso = false;
				}
			}
			if (falso) {
				System.out.println("NO, no esta");
			}

		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}
	}

	public static void buscarFecha(String fecha, ArrayList<Datos> fichero) {
		boolean falso = true;
		LocalDate prueba;
		try {

			for (int i = 0; i < fichero.size(); i++) {
				prueba = fichero.get(i).getFecha();
				if (prueba.toString().contains(fecha)) {
					System.out.println("SI, esta");
					System.out
							.println(fichero.get(i).getFecha() + " publico por el autor " + fichero.get(i).getAutor());
					falso = false;
				}
			}
			if (falso) {
				System.out.println("NO, no esta");
			}

		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}
	}

	public static void buscarMes(LocalDate fecha, GestionDatos permanente) {

		if (!GestionDatos.fichero.getFecha().getMonth().equals(fecha)) {
			System.out.println(fecha + " : NO, esta");
		} else {
			System.out.println(fecha + " : SI, esta");
		}

	}

}
