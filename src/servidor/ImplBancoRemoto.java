package servidor;

import java.rmi.RemoteException;
import java.util.*;

public class ImplBancoRemoto implements BancoRemoto{
	
	ArrayList<Conta> listcontas = new ArrayList<Conta>() {
		{
			add(new Conta("0123456", "016.662.244-30", 
			"Aleff Souza", "endereco", "13/10/1997",
		 	"84919194191", "1234",1000.00, false));

			add(new Conta("1111111", "222.662.244-30", 
			 "Maria Souza", "endereco", "20/10/2000",
			  "84919194191", "1234",1000.00, true));
		}
		
	};


	@Override
	public Conta logar(String login, String senha) throws RemoteException {
		//Conta conta = contas.stream().filter(conta -> login.equals(conta.getN_conta())).findAny();
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(login)).findAny();
		if (conta.isPresent() && senha.equals(conta.get().getSenha())){
			System.out.println("LOGANDO: "+conta.get().getNome_cliente());
			return conta.get();
		}
		else{
			return null;
		}
	}

	@Override
	public double sacar(String n_conta, double valor) throws RemoteException {
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		if(conta.get().getSaldo() >= valor){
			System.out.println("USUARIO: " + conta.get().getNome_cliente() + " SAQUE: "+ valor);
			return conta.get().sacar(valor);
		}
		return 0.0;
	}

	@Override
	public double depositar(String n_conta, double valor) throws RemoteException {
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		if (valor > 0) {
			System.out.println("USUARIO: " + conta.get().getNome_cliente() + " DEPOSITO: "+ valor);
			return conta.get().depositar(valor);
		}
		return 0.0;
	}

	@Override
	public double transferir(String n_conta, String n_contaD ,double valor) throws RemoteException {
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		Optional<Conta> contaD = listcontas.stream().filter(c -> c.getN_conta().equals(n_contaD)).findAny();
		if(contaD.isPresent()){
			if(conta.get().getSaldo() >= valor){
				System.out.println("USUARIO: " + conta.get().getNome_cliente() + " TRANSFERINDO: "+ valor + "DESTINO: "+ contaD.get().getNome_cliente());
				double saldo = conta.get().sacar(valor);
				contaD.get().depositar(valor);
				return saldo;
			}
		}	
		
		return 0.0;
	}

	@Override
	public double saldo(String n_conta) throws RemoteException {
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		System.out.println("USUARIO: " + conta.get().getNome_cliente() + " CHECK_SALDO: ");	
		return conta.get().getSaldo();
	}

	@Override
	public double saldoP(String n_conta) throws RemoteException {
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		System.out.println("USUARIO: " + conta.get().getNome_cliente() + " CHECK_SALDO_P: ");	
		return conta.get().getPoupanca();
	}

	@Override
	public double saldoR(String n_conta) throws RemoteException {
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		System.out.println("USUARIO: " + conta.get().getNome_cliente() + " CHECK_SALDO_R: ");	
		return conta.get().getRenda_fixa();
	}
	
	@Override
	public ArrayList<Conta> listaContas() throws RemoteException{
		return listcontas;
	}

	@Override
	public String getDados(String n_conta) throws RemoteException{
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		if(conta.isPresent()){
			return conta.get().toString();
		}
		return null;

	}

	@Override
	public Conta updateDados(String n_conta, String end, String tel) throws RemoteException{
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		if(conta.isPresent()){
			conta.get().setTelefone(tel);
			conta.get().setEndereco(end);
			System.out.println("USUARIO: " + conta.get().getNome_cliente() + " EDIT_DADOS: ");
			return conta.get();
		}
		else{

			return null;
		}
	}

	@Override
	public void removerConta(String n_conta) throws RemoteException{
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		System.out.println("USUARIO: " + conta.get().getNome_cliente() + " DELETE_CONTA: ");
		listcontas.remove(conta.get());

	}

	@Override
	public double depositarP(String n_conta, double valor) throws RemoteException{
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		if(conta.isPresent()){
			if(conta.get().getSaldo() >= valor){
				System.out.println("USUARIO: " + conta.get().getNome_cliente() + " AP_POUPANÇA: "+ valor );
				double saldoP = conta.get().depositarP(valor);
				
				return saldoP;
			}
		}	
		return 0.0;
	}

	@Override
    public double depositarR(String n_conta, double valor) throws RemoteException{
		Optional<Conta> conta = listcontas.stream().filter(c -> c.getN_conta().equals(n_conta)).findAny();
		if(conta.isPresent()){
			if(conta.get().getSaldo() >= valor){
				System.out.println("USUARIO: " + conta.get().getNome_cliente() + " AP_POUPANÇA: "+ valor );
				double saldoR = conta.get().depositarR(valor);
				
				return saldoR;
			}
		}	
		return 0.0;
	}


}
