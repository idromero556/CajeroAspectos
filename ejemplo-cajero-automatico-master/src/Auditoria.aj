import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public aspect Auditoria {
	String num="";
	FileWriter fichero = null;
    PrintWriter pw = null;
	pointcut metodoLogin():execution(private static String login(..));
	after() returning(String nc): metodoLogin(){
		num=nc;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
        try
        {
            fichero = new FileWriter("Auditoria.txt", true);
            pw = new PrintWriter(fichero);

            pw.println("Número de Cuenta: "+nc);
            pw.println("Fecha ingreso: "+dateFormat.format(date));

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
	
	pointcut metodoEjecutar():execution(public void ejecutar(..));
	void around(): metodoEjecutar(){
		proceed();
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	
	      try {
			// Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("Operaciones"+num+".txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	
	         // Lectura del fichero
	         
	         String last = br.readLine();
	         String lastLine = "";
	         while(last!=null) {
	        	 lastLine = last;
	        	 last = br.readLine();
	         }
	         
	         try
	         {
	             fichero = new FileWriter("Auditoria.txt", true);
	             pw = new PrintWriter(fichero);

	             pw.println(lastLine);

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
		
		
	}

}
