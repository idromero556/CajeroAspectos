package ejemplo.cajero.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import ejemplo.cajero.modelo.Banco;


public class ComandoListarOperaciones implements Comando {
	
	public String getNombre() {
		return "Listar operaciones";
	}

	public void ejecutar(Banco contexto, String nc) throws Exception {
		
		System.out.println("Listar operaciones");
		System.out.println();			
		
		
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("Operaciones"+nc+".txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	
	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            System.out.println(linea);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      
	      FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("Operaciones"+nc+".txt", true);
	            pw = new PrintWriter(fichero);

	            
	            pw.println("Consulta Lista de Operaciones");

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
