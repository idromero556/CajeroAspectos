import java.util.List;

import ejemplo.cajero.control.Comando;
import ejemplo.cajero.control.ComandoConsignar;

public aspect Consignar {
	pointcut metodoComandos():execution(private static List<Comando>*(..));
	List<Comando> around(): metodoComandos(){
		List<Comando> comandos = proceed();
		comandos.add(new ComandoConsignar());
		return comandos;
		
	}

}
