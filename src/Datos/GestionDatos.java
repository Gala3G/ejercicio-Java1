package Datos;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import Logica.Operaciones;
import Logica.Validaciones;
import Usuario.Consola;



public class GestionDatos {
	
	
	public static Datos guardarObjeto(Datos noticia5) {
		String dato;
		boolean falso = false;
		LocalDate fecha;

		//Añadir se caputará aqui. Damos valor al objeto de MAIN noticia5
		try {
			// FECHA
			System.out.println("Dime la fecha en este formato: YYYY-MM-DD");
			dato = Consola.scannerString();
			// Valido todas las restricciones de la fecha en bucle
			// Meto String y devuelve localDate
			fecha = Validaciones.validarFechaPRUEBA(dato);
			noticia5.setFecha(fecha);

			// TITULAR
			System.out.println("\nDime el titulo"); //Mismo patron para todos
			dato = Consola.scannerString(); //Recojo dato 
			dato = Validaciones.validarString(dato); //valido
			dato = Operaciones.formatoTexto(dato); //Adaptamos formato
			noticia5.setTitulo(dato); //Añadimos 

			// TEXTO
			System.out.println("\nDime la noticia");
			dato = Consola.scannerString();
			dato = Validaciones.validarString(dato);
			dato = Operaciones.formatoTexto(dato);
			noticia5.setTexto(dato);

			// AUTOR
			System.out.println("\nDime el autor");
			dato = Consola.scannerString();
			dato = Validaciones.validarAutor(dato);
			dato = Operaciones.formatoAutor(dato); //Excep. formato propio de autor
			noticia5.setAutor(dato);

		} catch (StringIndexOutOfBoundsException e) {
			System.err.println("ERROR FATAL, algun dato esta fuera de rango");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR FATAL, dato fuera de rango");
		} 

		return noticia5;
	}
	
	
	
	
	
	
	//....................................................................//
	//---------------------PRUEBAS QUE HICE QUE QUIERO GUARDAR-----------//
	
	public static Datos fichero = new Datos(null, null, null, null);
	
	static ArrayList <Datos> arrayList = new ArrayList<Datos>();

	

	public Datos getFichero() {
		return fichero;
	}

	public void setFichero(Datos fichero) {
		this.fichero = fichero;
	}

	public ArrayList<Datos> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<Datos> arrayList) {
		this.arrayList = arrayList;
	}

	public String todo() {
		return "Fichero= " + fichero + "\nTemporal= " + arrayList;
	}
	
	public  Datos todoFICHERO(Datos fichero2) {
		return  fichero ;
	}
	
	


	public void agregarTemporal(Datos noticia) {
		arrayList.add(noticia);
	}

	
	public static void mostrarTodo(ArrayList <Datos> listaNoticias) {
		for (Datos noticia : arrayList) {
			System.out.printf("Fecha: %s \nTitulo: %s \nTexto: %s \nAutor: %s \n", noticia.getFecha(),
					noticia.getTitulo(), noticia.getTexto(), noticia.getAutor());
			

		}
	}
	
	

}


	

	
	
	

	


