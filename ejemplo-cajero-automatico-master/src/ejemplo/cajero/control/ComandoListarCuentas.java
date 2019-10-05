package ejemplo.cajero.control;

import java.io.FileWriter;
import java.io.PrintWriter;

import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.Cuenta;

/**
 * Comando usado para listar las cuentas 
 */
public class ComandoListarCuentas implements Comando {

	@Override
	public String getNombre() {
		return "Consultar";
	}

	@Override
	public void ejecutar(Banco contexto, String nc) throws Exception {
		
		System.out.println("Consultar");
		System.out.println();
		Cuenta cuenta = contexto.buscarCuenta(nc);
	
		System.out.println(cuenta.getNumero() + " : $ " + cuenta.getSaldo());
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("Operaciones"+nc+".txt", true);
            pw = new PrintWriter(fichero);

            
            pw.println("Consulta de saldo "+cuenta.getSaldo());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }

	}

}
