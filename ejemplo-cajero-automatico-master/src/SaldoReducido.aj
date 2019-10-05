import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.Cuenta;

public aspect SaldoReducido {
	Cuenta num=null;
	pointcut metodoLogin():execution(private static String login(..));
	after() returning(String nc): metodoLogin(){
		
		Banco b=(Banco) thisJoinPoint.getArgs()[0];
		num=b.buscarCuenta(nc);
	}
	pointcut metodoRetirar():execution(public void retirar(..));
	
	before() throws Exception: metodoRetirar() {
		long valor=Long.parseLong(String.valueOf(thisJoinPoint.getArgs()[0]));
		if (valor > num.getSaldo()-200) {
			throw new Exception("El saldo no puede ser menor a 200");
		}
		
	}
	
	void around() throws Exception:metodoRetirar(){
		
			proceed();


		
	}
	

}
