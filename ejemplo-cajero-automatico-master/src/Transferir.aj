import java.util.List;

import ejemplo.cajero.control.Comando;
import ejemplo.cajero.control.ComandoTransferir;

public aspect Transferir {
	pointcut metodoComandos():execution(private static List<Comando>*(..));
	List<Comando> around(): metodoComandos(){
		List<Comando> comandos = proceed();
		comandos.add(new ComandoTransferir());
		return comandos;
		
	}

}
