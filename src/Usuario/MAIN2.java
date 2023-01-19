package Usuario;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthScrollPaneUI;

import Datos.Datos;
import Datos.GestionDatos;
import Logica.Operaciones;
import Logica.Validaciones;

public class MAIN2 {

	public static void main(String[] args) {

		int menu = 0;
		char respuesta;
		String dato;
		boolean falso = false;
		LocalDate fecha;
		int prueba = 0;
		String palabraBuscada = "";
		
		

		ArrayList<Datos> listaNoticias = new ArrayList<Datos>(); //creo un ArrayList con los atributos de DATOS

		Datos noticia1 = new Datos(LocalDate.parse("2022-03-01"), "titulo 1", "texto 1", "autor 1"); //Creamos objeto y le damos valor
		Datos noticia2 = new Datos(LocalDate.parse("2022-03-02"), "hola que tal", "todo bien", "genial");
		Datos noticia3 = new Datos(LocalDate.parse("2022-03-03"), "titulo 3", "texto 3", "Lola Santos");
		Datos noticia4 = new Datos(LocalDate.parse("2022-03-04"), "titulo 4", "texto 4", "autor 4");
		
		//Noticia5 la dejo NULL 
		//La usaré para introducir las noticias temporales (ArrayList) 
		//O para guardar permanentemente (FILE) 
		Datos noticia5 = new Datos(null, null, null, null);

		
		//Añadimos los 4 primero objetos los añado al ArrayList porque haré búsquedas tanto de Fichero como de ArrayList. 
		//Y así me dará más juego para hacer las comprobaciones de las búsquedas en ArrayList
		listaNoticias.add(noticia1); 
		listaNoticias.add(noticia2);
		listaNoticias.add(noticia3);
		listaNoticias.add(noticia4);

		do {
			Consola.MenuPrincipalPRUEBA1();
			menu=Validaciones.validarRango(1, 4);
			switch (menu) {
			case 1:  
				// AÑADIR A FICHERO
				//Almacenamos los atributos en nuevo objeto noticia5
				//Y dependiente de lo que quiera el usuario
				//objeto noticia5 se almacenará en Fichero o en ArrayList
		
				try {
					System.out.println("MENU/AÑADIR/FICHERO \n");	
					//1.Creamos objeto
					GestionDatos.guardarObjeto(noticia5);
					
					//2.Almacenamos en Fichero
					Datos.escribirFichero(noticia4);
					
					//3.Confirmamos por pantalla el objeto creado
					System.out.println("Esto es la noticia guardada en el fichero:" + noticia5.toString());
				   
					
				} catch (Exception e) {
					System.err.println("ERROR FATAL");
				}

				break;
			case 2:
				// AÑADIR A TEMPORAL
				// Se repite el patron solo que en vez invocar metodo escribir fichero
				// Hago un add al del array list y añador el objeto
				try {
					System.out.println("MENU/AÑADIR/TEMPORALMENTE \n");
					GestionDatos.guardarObjeto(noticia5);
					listaNoticias.add(noticia5);
					System.out.println("Esta es la noticia introducida:" + listaNoticias.get(4).toString());
					// ojo, hay que imprimir la posicion 4, la posicion 5 no existe
				} catch (IndexOutOfBoundsException e) {
					System.err.println("ERROR FATAL, dato fuera de rango");
				}catch (Exception e) {
					System.err.println("ERROR FATAL");
				}
				break;
			case 3:
				// BUSQUEDAS 
				
				do {
					Consola.MenuBuscar1();
					menu=Validaciones.validarRango(1, 6);
					
					switch (menu) {
					case 1:
						//BUSQUEDA FECHA
			 
						 
						try {

							System.out.println("Dime la fecha en este formato: YYYY-MM-DD");
							dato = Consola.scannerString();
							fecha = Validaciones.validarFechaPRUEBA(dato);
							dato = fecha.toString();
							//porque paso la fecha a string para guardar en dato? 
							//Por como he construido la validacion de FECHA, que es:
							//1. por restricciones: num digitos, num guiones, relacion mes/dia, etc, etc
							//2. pasarla de string a localDate para asegurarme que fecha es inferior a la fecha de hoy

							//pero al haberle añadido la condicion numero 2, tenia que pasarlo a localDate
							//Por tanto, la validacion de fecha me devuelve LocalDate y lo paso a string
							//Y asi puedo usar el mismo metodo de busqueda para todas las consultas 
							
							
							Consola.resultadoFichero();
							Operaciones.busquedas("datos/datos.txt", "Fecha:", dato); //usare el mismo metodo en todas las busquedas, 
																					  //solo que cambiando la id, es decir, el nombre del atributo
							Consola.resultadoArray();
							Operaciones.buscarArray(dato, listaNoticias);
							// nota, los mensajes de la valicion del localDate causan confusion
							// USAR fecha 2022-03-01 para encontrar
							

						} catch (Exception e) {
							System.err.println("ERROR FATAL");
						}


						break;
					case 2:
						//BUSQUEDA AUTOR
						
						try {
							//sin comentarios, mismo patron que en todas las busquedas
							System.out.println("Que autor busca?");
							dato = Consola.scannerString();
							dato=Validaciones.validarString(dato);
							dato = Operaciones.formatoAutor(dato);//Exc. que si añado el formato/validacion estricto de autor
																  //forzar que haya nombre y apellido + cambiarle formato a May
																  //buscando que haya un espacio en el string 
							Consola.resultadoFichero();
							Operaciones.busquedas("datos/datos.txt", "Autor:", dato);
							
							Consola.resultadoArray();
							Operaciones.buscarArray(dato, listaNoticias);
							//poner palabra autor para que salga algo


						} catch (Exception e) {
							System.err.println("ERROR FATAL");
						}
						

						break;
					case 3:
						//BUSQUEDA NOTICIA
						
						try {
							//sin comentarios, mismo patron que en todas las busquedas
							System.out.println("Que palabra quiere encontrar en la noticia?");
							dato = Consola.scannerString();
							dato=Validaciones.validarString(dato);
							
							Consola.resultadoFichero();
							Operaciones.busquedas("datos/datos.txt", "Texto:", dato);
							
							Consola.resultadoArray();
							Operaciones.buscarArray(dato, listaNoticias);

						} catch (Exception e) {
							System.err.println("ERROR FATAL");
						}

						break;
					case 4:
						//MOSTRAR TODO
						

						try {
							Consola.resultadoFichero();			
							Datos.leerFichero("datos/datos.txt");
							
							Consola.resultadoArray();
							System.out.println(listaNoticias);

						} catch (Exception e) {
							System.err.println("ERROR FATAL");
						}

						break;
					case 5:
						//RASTREAR EN TODOS LOS ATRIBUTOS
						
						try {
							//sin comentarios, mismo patron que en todas las busquedas
							System.out.println("Que palabra buscas?");
							dato = Consola.scannerString();
							dato=Validaciones.validarString(dato);
							
							Consola.resultadoFichero();							
							Operaciones.busquedas("datos/datos.txt", "", dato);//Exc. que al eliminar el id (nombre atributo) de parametro
																			   //puede buscar con el mismo metodo en todo el fichero
							Consola.resultadoArray();
							Operaciones.buscarArray(dato, listaNoticias);

						} catch (Exception e) {
							System.err.println("ERROR FATAL");
						}

						break;

					default:
						break;
					}

				} while (menu != 6);
				break;

			default:
				break;
			}

		} while (menu != 4);

// -------------------------------------------------------------------------------//
	}
}