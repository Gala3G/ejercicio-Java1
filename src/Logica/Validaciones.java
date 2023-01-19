package Logica;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import Usuario.Consola;

public class Validaciones {

	public static LocalDate validarFechaPRUEBA(String buscar) {
		//despues de mejorar este codigo para la validacion de fecha, me doy cuenta que si pudiera volver 
		//a empezar, hubiera fraccionado esta validacion en 3 partes
		//1. bucle 10 digitos
		//2. bucle guiones y dígitos donde tocan 
		//3. dias correspondientes a meses correctos
		//4. que no sea superior a la fecha de hoy, es decir, pasarlo a LocalDate para usar metodo After 
		//no se si hubierea sido correcto dividirlo en 2 metodos por ejemplo, pero sin duda me hubiera resultado mucho mas facil manejar esta validacion
		
		boolean falso;
		int mes, debugMes; 
		int dia, debugDia;
		boolean debug;
		LocalDate fecha = null;

		do {
			falso = false;

			// VALIDACION DE LENGTH 9 CARACTERES

			do { 
				falso = false;
				do {
					do {
						falso = false;
						if (buscar.length() != 10) {

							System.err.println("Error, la fecha necesita 10 caracteres");
							System.out.println("Introducir AÑO-MES-DIA");
							System.out.println("Ejemplo: 1992-01-31");
							buscar = Consola.scannerString();
							falso = true;
						}
						

					} while (falso);

					falso = false;

					// VALIDACION DE USO DE GUIONES
					if (buscar.charAt(4) != '-') {

						System.err.println("Error, introduzca guiones");
						falso = true;

					} else if (buscar.charAt(7) != '-') {

						System.err.println("Error, falta el segundo guion");
						falso = true;
					}

					// VALIDAR QUE (año, mes y dia) SEAN DIGITOS
					// Año, en bucle y que sean digitos
					/*
					 * for (int i = 3; i > buscar.length(); i--) { if ((int) buscar.charAt(i) < 48
					 * || (int) buscar.charAt(i) > 57) {
					 * System.err.println("Error, debe introducir numeros y guiones"); falso = true;
					 * } }
					 */

					for (int i = 3; i > buscar.length(); i--) {
						if (!Character.isDigit(buscar.charAt(i))) {
							System.err.println("Error, la posicion " + buscar.charAt(i) + 1 + " no es numero");
							falso = true;
						}
					}
					
					// Mes, que sean digitos y entre parentesis, con metodo

					if (!Character.isDigit(buscar.charAt(5)) || !Character.isDigit(buscar.charAt(6))) {

						System.err.println("Error en el mes, introduzca digitos");
						falso = true;
					}

					// Dias, que sean digitos y entre paréntesis, con metodo
					if (!Character.isDigit(buscar.charAt(8)) || !Character.isDigit(buscar.charAt(9))) {

						System.err.println("Error en los dias, introduzca digitos");
						falso = true;
					}

					if (falso == true) {

						System.out.println("Introducir AÑO-MES-DIA");
						System.out.println("Ejemplo: 1992-01-31");
						buscar = Consola.scannerString();
						falso = true;
					}

				} while (falso);

				// VALIDAR QUE EL ES MES Y DIA SEAN DE RANGO CORRECTO
				mes = Integer.parseInt(buscar.substring(5, 7));// (4,7)
				debugMes = mes;
				dia = Integer.parseInt(buscar.substring(8, buscar.length()));
				debugDia = dia;
				if (mes > 12 || mes < 1) {

					System.err.println("Error en el mes introducido");
					falso = true;

				} else if (dia > 31 || dia < 1) {
					System.err.println("Error en el dia introducido");
					falso = true;
				}

				// QUE CORRESPONDAN LOS DIAS A LOS MESES
				// (ojo agosto)
				if (mes == 01 || mes == 03 || mes == 05 || mes == 07 || mes == 10 || mes == 12) {
					if (dia > 31) {

						System.err.println("Error en el dia, el mes introducido tiene maximo 31 dias");
						falso = true;
					}
				} else if (mes == 04 || mes == 06 || mes == 11) {// ojo septiembre)
					if (dia > 30) {

						System.err.println("Error en el dia, el mes introducido tiene maximo 30 dias");
						falso = true;
					}
				} else if (mes == 02) {
					if (dia > 28) {

						System.err.println("Error en el dia, el mes introducido tiene maximo 28 dias");
						falso = true;
					}
				}

				if (!falso) {
					System.out.println("Fecha cumple los caracteres!");
				} else {
					System.out.println("Introducir AÑO-MES-DIA");
					System.out.println("Ejemplo: 1992-01-31");
					buscar = Consola.scannerString();
					falso = true;
				}

			} while (falso);

			// VALIDAMOS QUE FECHA NO SEA SUPERIOR A HOY
			try {
				if (falso != true) {

					// lo pasamos a local date
					fecha = LocalDate.parse(buscar);

					// si la nueva fecha LocalDate es inforior a la hoy, nos confirmara que la fecha
					// es falsa
					if (fecha.isBefore(LocalDate.now())) {
						System.out.println("Fecha cumple que sea anterior a hoy!");
					} else {
						System.err.println("Pero la fecha no puede ser superior a la actual");
						System.out.println("Introducir AÑO-MES-DIA");
						buscar = Consola.scannerString();
						falso = true;
					}
				}

			} catch (DateTimeParseException e) {
				System.err.println("ERROR FATAL, al convertir la fecha");
			} catch (DateTimeException e) {
				System.err.println("ERROR FATAL, del sistema para registrar la fecha");
			}

			// permanecemos en el bucle hasta que la validacion mientras que falso sea true
		} while (falso);

		return fecha;
	}

	public static String validarString(String dato) {
		boolean falso = false;

		try {

			do {
				falso = false;
				if (dato.length() < 4) {
					System.err.println("Debe escribir más de 3 caracteres");
					dato = Consola.scannerString();
					falso = true;
				}

			} while (falso);

		} catch (Exception e) {
			System.err.println("ERROR FATAL, del sistema");
		}

		return dato;
	}

	// ----------------------VALIDACIONES DE TOMA DE DATOS POR
	// SCANNER----------------------//

	public static int validarInt() {
		Scanner lector = new Scanner(System.in);
		int num = 0;

		try {
			num = lector.nextInt();

		} catch (InputMismatchException ex) {
			System.err.println("Error fatal, no se ha introducido un numero");

		} catch (Exception e) {
			System.err.println("Error, desconcido");
		}
		
		return num;
	}

	public static char validarChar(char dato) {

		// char charUpperCase = Character.toUpperCase(dato);

		try {

			do {

				if (dato != 'S' || dato == 'N') {
					System.err.println("Introduzca S o N");
					Consola.scannerChar();
				}

			} while (dato == 'S' || dato == 'N');

		} catch (InputMismatchException e) {
			System.err.println("Se ha producido un error");
		}

		return dato;

	}

	public static String validarAutor(String dato) {
		boolean falso;

		try {

			do {
				falso = false;

				// aqui tengo un problema. puedo localizr facil si hay 1 solo espacio
				// pero si hay dos espacios, no me lo convertirá bien en el formato
				// y desde formato a Autor se rompe, busco condiciones para evitarlo

				if (!dato.contains(" ")) { // si no hay 1 espacio, no hay nombre y apellido
					System.err.println("Debe escribir nombre y apellido");
					dato = Consola.scannerString();
					falso = true;
				}

			} while (falso);

		} catch (Exception e) {
			System.err.println("ERROR FATAL, del sistema");
		}

		return dato;
	}

	public static int validarInt(int num) {

		boolean falso = false;

		return num;
	}

	public static int validarRango(int uno, int dos) {
		int num = 0;

		num = Validaciones.validarInt();
		// valido rango
		do {
			try {

				if (num < uno || num > dos) {
					System.err.println("Dime un numero del menu");
					num = Validaciones.validarInt();
				}

			} catch (InputMismatchException ex) {
				System.err.println("ERROR FATAL, no se ha introducido un numero");

			} catch (Exception e) {
				System.err.println("ERROR FATAL");
			}
		} while (num < uno || num > dos);

		return num;
	}

	// ..................................NO ESTA EN
	// USO.............................................//
	// ---------------------pq la fusioné con validarFechaPRUEBA, pero
	// funciona-------------------//
	public static boolean validarFechaMenorNow(String buscar) {

		boolean falso = false;
		LocalDate fecha;

		try {

			// Si todo es correcto, quiero restringir que la fecha introducida no pueda ser
			// mayor a la fecha del sistema en NOW
			fecha = LocalDate.parse(buscar);

			if (fecha.isAfter(LocalDate.now())) {
				System.err.println("Error, la fecha es año-mes-dia y no puede ser superior a la actual");
				falso = true;
			}

		} catch (DateTimeParseException e) {
			System.err.println("ERROR FATAL, al convertir la fecha");
		} catch (DateTimeException e) {
			System.err.println("ERROR FATAL, del sistema para registrar la fecha");
		} catch (RuntimeException e) {
			System.err.println("ERROR FATAL, del sistema para registrar la fecha");
		}

		return falso;
	}

}
