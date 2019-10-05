package ejemplo.cajero;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ejemplo.cajero.control.Comando;
import ejemplo.cajero.control.ComandoListarCuentas;
import ejemplo.cajero.control.ComandoListarOperaciones;
import ejemplo.cajero.control.ComandoRetirar;
import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.Cuenta;

/**
 * Simulador de un Cajero de Banco
 */
public class Cajero {
	
	/**
	 * Programa principal
	 * @param args parámetros de línea de comandos. Son ignorados por el programa.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		// crea el banco
		Banco banco = new Banco();
		
		// crea unas cuentas, para la prueba
		banco.agregarCuenta(new Cuenta("1", "clave", 1000));
		banco.agregarCuenta(new Cuenta("2", "clave", 2000));
		banco.agregarCuenta(new Cuenta("3", "clave", 3000));
		
		
		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = cargaComandos();
		
		
		// Ciclo del Programa
		// ==================

		System.out.println("Cajero Automático");
		System.out.println("=================");
		System.out.println();
		String nc ="a";
		boolean fin = false;
		boolean f = false;
		boolean conectado=false;
			
		do {
			if(conectado!=true) {
			try {
				nc = login(banco);
			}catch (Exception e1) {
				System.err.println(e1.getMessage());
				f = true;
			}
			}
			if(f != true) {
				conectado = true;
				// muestra los nombres de los comandos
				muestraMenuConComandos(comandos);
				System.out.println("X.- Salir");
				
				// la clase Console no funciona bien en Eclipse
				Scanner console = new Scanner(System.in);			
				String valorIngresado = console.nextLine();
				
				// obtiene el comando a ejecutar
				Comando comandoSeleccionado = retornaComandoSeleccionado(comandos, valorIngresado);
				if (comandoSeleccionado != null) {
					
					// intenta ejecutar el comando
					try {
						comandoSeleccionado.ejecutar(banco,nc);
						
					} catch (Exception e) {
						// si hay una excepción, muestra el mensaje
						System.err.println(e.getMessage());
					}
					
				} 
				// si no se obtuvo un comando, puede ser la opción de salir
				else if (valorIngresado.equalsIgnoreCase("X")) {
					fin = true;
				}
				
				System.out.println();
				} f=false;
			} while ( !fin );
			System.out.println("Gracias por usar el programa.");
	}
	// Manejo de cuenta
	@SuppressWarnings("resource")
	private static String login(Banco contexto) throws Exception {
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);			
		
		// Ingresa los datos
		System.out.println("Ingrese el número de cuenta");
		String numeroDeCuenta = console.nextLine();
		
		Cuenta cuenta = contexto.buscarCuenta(numeroDeCuenta);
		if (cuenta == null) {
			throw new Exception("No existe cuenta con el número " + numeroDeCuenta);
		}
			
		System.out.println("Ingrese la clave de la cuenta");
		String claveDeCuenta = console.nextLine();
		if (claveDeCuenta.equals(cuenta.getClave())== false ){
			throw new Exception("La clave no es correcta");
		}
		return numeroDeCuenta;
	}
	
	// Manejo de los comandos de la aplicación
	// =======================================
	
	// carga los comandos usados en el programa
	private static List<Comando> cargaComandos() {
		
		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = new ArrayList<>();
		
		comandos.add(new ComandoListarCuentas());
		comandos.add(new ComandoRetirar());
		comandos.add(new ComandoListarOperaciones());
		
		return comandos;
	}
	
	
	// Muestra el listado de comandos, para mostrar un menú al usuario
	// muestra el índice en el arreglo de comandos y el nombre del comando
	private static void muestraMenuConComandos(List<Comando> comandos) {
		
		// muestra los nombres de los comandos 
		for (int i=0; i < comandos.size(); i++) {
			System.out.println(i + ".-" + comandos.get(i).getNombre());
		}
	}
	
	
	// dado el texto ingresado por el usuario, retorna el comando correspondiente
	// retorna null si el texto no es un número o no existe ningún comando con ese índice
	private static Comando retornaComandoSeleccionado(List<Comando> comandos, String valorIngresado) {
		
		Comando comandoSeleccionado = null;
		
		// el valorIngresado es un número ?
		if (valorIngresado.matches("[0-9]")) {			
			int valorSeleccionado = Integer.valueOf(valorIngresado);
			// es un índice válido para la lista de comandos
			if (valorSeleccionado < comandos.size()) {
				comandoSeleccionado = comandos.get(valorSeleccionado);
			}
		}
		
		return comandoSeleccionado;
	}
	
}
