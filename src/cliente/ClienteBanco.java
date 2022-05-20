package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import servidor.BancoRemoto;
import servidor.Conta;

public class ClienteBanco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);
    	
       // String host = teclado.nextLine();
        String host = "localhost";
        try {
        	
        	
            Registry registro = LocateRegistry.getRegistry(host, 20001);
            
            BancoRemoto stubCliente = (BancoRemoto) registro.lookup("Calc");

            System.out.println("Bem vindo ao Banco");
            

            
            // inicio do menu
            
                
                short opcao = 0;
                do{
                    System.out.println("Informe o numero da sua conta: ");
                    String login = teclado.nextLine();
                    System.out.println("Informe sua senha: ");
                    String senha = teclado.nextLine();
                    Conta conta = stubCliente.logar(login, senha);
                    if(conta != null){

                        do{
                            System.out.println("Bem vindo "+ conta.getNome_cliente());
                            System.out.println("Tipo de Usuario: ");
                            System.out.println( conta.getisFunc() ? "Funcionario" : "Comum");
                            exibirMenu();
                            System.out.print("Opção: ");
                            opcao = teclado.nextShort();

                            switch(opcao){
                                case 1:
                                    System.out.println("--Gerenciar--");
                                    ArrayList<Conta> lista = new ArrayList<Conta>(stubCliente.listaContas());
                                    System.out.println(lista);
                                    break;
                                    
                                case 2:
                                    System.out.println("--Saque--");
                                    System.out.println("Quanto deseja sacar? ");
                                    double valorS = teclado.nextDouble();
                                    double valS = stubCliente.sacar(conta.getN_conta(), valorS);
                                    if(valS != 0.0){
                                        System.out.println("Sacado: "+ valorS);
                                        System.out.println("Saldo atual: "+valS);
                                    }
                                    else{
                                        System.out.println("Você não possui limite suficiente");
                                    }
                                    break;
                                    
                                case 3:
                                    System.out.println("--Depósito--");
                                    System.out.println("Quanto deseja depositar? ");
                                    double valorD = teclado.nextDouble();
                                    double valD = stubCliente.depositar(conta.getN_conta(), valorD);
                                    if(valD != 0.0){
                                        System.out.println("Valor depositado: "+ valorD);
                                        System.out.println("Saldo atual: "+ valD);
                                    }
                                    else{
                                        System.out.println("Valor negativo não pode ser depositado");
                                    }
                                    
                                    break;
                                    
                                case 4:
                                    System.out.println("--Transferência--");
                                    System.out.println("Quanto deseja Transferir? ");
                                    double valorT = teclado.nextDouble();
                                    System.out.println("Qual a conta de destino? ");
    
                                    String contaD = teclado.next();
                                    double valT = stubCliente.transferir(conta.getN_conta(), contaD, valorT);
                                    if(valT != 0.0){
                                        System.out.println("Valor transferido: "+ valorT);
                                        System.out.println("Saldo atual: "+ valT);
                                    }
                                    else{
                                        System.out.println("Conta inexistente ou saldo insuficiente");
                                    }
                                    break;
                                    
                                case 5:
                                    System.out.println("--Saldo da Conta--");
                                    double saldo = stubCliente.saldo(conta.getN_conta());

                                    System.out.println("Saldo atual: " + saldo);
                                    break;
                                
                                case 6:
                                    System.out.println("--Investimentos--");
                                    break;
                                
                                case 99:
                                    break;
                                
                                default:
                                    System.out.println("Opção indisponível");
                            }

                        }while(opcao != 99);
                        //teclado.close();
                    }
                    else{
                        System.out.println("Usuario ou senha invalidos");
                    }
                }while(true);
           
            
            
            //System.out.println("login com sucesso?  " + stubCliente.logar("0123456", "1515"));
            
            //System.out.println("A subtração entre 20 e 10 é: " + stubCliente.subtrair(20, 10));
            
            //System.out.println("A multiplicação entre 20 e 10 é: " + stubCliente.multiplicar(20, 10));
           
            
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}

    public static void exibirMenu(){
		System.out.println("\n");
		System.out.println("+---------------------------------------+");
		System.out.println("|                 Banco                 |");
		System.out.println("+---------------------------------------+");
		System.out.println(
			"| 01 - Gerenciar                        |\n" +
			"| 02 - Saque                            |\n" +
			"| 03 - Depósito                         |\n" +
			"| 04 - Transferência                    |\n" +
			"| 05 - Saldo da Conta                   |\n" +
			"| 06 - Investimentos                    |\n" +
			"| 99 - Sair                             |"
		);
		System.out.println("+---------------------------------------+\n\n");
	}

}
