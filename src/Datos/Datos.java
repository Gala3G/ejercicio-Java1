package Datos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class Datos {


	private LocalDate fecha;
	private String titulo;
	private String texto;
	private String autor;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Datos(LocalDate fecha, String titulo, String texto, String autor) {
		super();
		this.fecha = fecha;
		this.titulo = titulo;
		this.texto = texto;
		this.autor = autor;
	}

	public Datos(ArrayList<Datos> listaNoticias) {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "\nFecha: " + fecha + "\nTitulo: " + titulo + "\nTexto: " + texto + "\nAutor: " + autor + "\n";
	}

	// -------------------AÑADIMOS METODOS ESCRIBIR Y LEER----------------------------//

	
	public static void escribirFichero(Datos noticia) {

		FileWriter guardar = null;

		try {
			guardar = new FileWriter("datos/datos.txt", true);// true = append
		
			guardar.write((noticia.toString()) + (char) 13);

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("Un error incontrolado");
		} finally {
			try {
				if (guardar != null) {
					guardar.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}


	public static void leerFichero(String ruta) {

		FileReader fichero = null;
		BufferedReader lector = null;
		String cadena;

		try {
			fichero = new FileReader(ruta);
			lector = new BufferedReader(fichero);

			while ((cadena = lector.readLine()) != null) {
				System.out.println(cadena + "\n");
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
	
	
	
	//-..............................................................................//
	//----------------ESTOS NO ESTAN ACTIVOS, PERO FUNCIONAN-------------------------//

	public static void escribirFicheroPRUEBAAarrays(ArrayList<String> datos) {
		FileWriter guardar = null;

		try {
			guardar = new FileWriter("datos/datos2.txt", true);// true = append

			for (String dato : datos) {
				guardar.write(dato + (char) 13);
			}
			// for(int i=0;i<datos.length;i++) {}

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("Un error incontrolado");
		} finally {
			try {
				if (guardar != null) {
					guardar.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public static void escribirFicheroPRUEBAarrays(ArrayList<Datos> listaNoticias) {
		FileWriter guardar = null;

		try {
			guardar = new FileWriter("datos/datos.txt", true);// true = append

			for (int i = 0; i < listaNoticias.size(); i++) {
				listaNoticias.get(i);
				guardar.write(i + (char) 13);
			}

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("Un error incontrolado");
		} finally {
			try {
				if (guardar != null) {
					guardar.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
	
	//-----------------------PRUEBAS------------------//
		public static void buscarAutorPRUEBA(LocalDate fecha, Datos fichero) {
			boolean falso = true;
			String prueba;
			try {
				if (fichero.getFecha().equals(fecha)) {
					System.out.println("SI, si esta");
					
				}else {
					System.out.println("NO, no esta");
				}
				

			} catch (Exception e) {
				System.err.println("ERROR FATAL");
			}
		}

		public static void leerFicheroPRUEBA2(String ruta) {

			FileReader fichero = null;
			BufferedReader lector = null;
			String cadena;

			try {
				fichero = new FileReader(ruta);
				lector = new BufferedReader(fichero);

				while ((cadena = lector.readLine()) != null) {
					System.out.println(cadena + "\n");
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
		
		

}