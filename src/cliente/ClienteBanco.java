package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.lang.Math;

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
            
            BancoRemoto stubCliente = (BancoRemoto) registro.lookup("Bank");

            System.out.println("Bem vindo ao Banco");
            

            
            // inicio do menu
            
                
                short opcao = 0;
                do{
                    teclado.nextLine();
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
                                    short opcaoG = 0;
                                    System.out.println("--Gerenciar--");
                                    if(conta.getisFunc()){
                                        var lista = stubCliente.listaContas();
                                        System.out.println(lista);
                                        break;
                                    }
                                    else{
                                        System.out.println("Seus dados: ");
                                        System.out.println( stubCliente.getDados(conta.getN_conta()));
                                        System.out.println("01 - Editar dados");
                                        System.out.println("02 - Excluir Conta");
                                        System.out.println("03 - Voltar");
                                        System.out.println("opção: ");
                                        opcaoG = teclado.nextShort();
                                        switch (opcaoG) {
                                            case 1:
                                                System.out.println("Novo endereço:");
                                                teclado.nextLine();
                                                String end = teclado.nextLine();
                                                System.out.println("Novo numero de telefone:");
                                                String tel = teclado.nextLine();
                                                conta = stubCliente.updateDados(conta.getN_conta(), end, tel);
                                                break;
                                                
                                            case 2:
                                                stubCliente.removerConta(conta.getN_conta());
                                                System.out.println("Conta removida");
                                                opcao = 99;
                                                
                                                
                                                break;
                                            case 3:
                                                
                                                break;
                                        
                                            default:
                                                System.out.println("Opção indisponível");
                                        }

                                        break;
                                    }
                                    
                                    
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
                                short opcaoI = 0;
                                    System.out.println("--Investimentos--");
                                    double p = stubCliente.saldoP(conta.getN_conta());
                                    double r = stubCliente.saldoR(conta.getN_conta());
                                    System.out.println("Valor Aplivado na Poupança: "+ p);
                                    System.out.println("Valor Aplivado na Renda Fixa: "+ r);

                                    System.out.println("Simulação renda fixa atual :");
                                    System.out.println("3 Meses - " + (r * Math.pow(1 + 0.015 ,3) ) );
                                    System.out.println("6 Meses - " + (r * Math.pow(1 + 0.015 ,6) ) );
                                    System.out.println("12 Meses - " + (r * Math.pow(1 + 0.015 ,12) ) );

                                    System.out.println("-----------------------------------------------");

                                    System.out.println(" 1 - Aplicar na poupança");
                                    System.out.println(" 2 - Aplicar na renda fixa");
                                    System.out.println(" 3 - Voltar");
                                    opcaoI = teclado.nextShort();
                                    switch (opcaoI) {
                                        case 1:
                                        double saldoI = stubCliente.saldo(conta.getN_conta());
                                        System.out.println("Saldo atual: " + saldoI);
                                        System.out.println("Informe quanto deseja aplicar: ");
                                        double valorI = teclado.nextDouble();

                                        System.out.println("Valor aplicado. Saldo atual na poupança: " + stubCliente.depositarP(conta.getN_conta(), valorI));

                                            
                                            break;
                                        case 2:
                                            double saldoR = stubCliente.saldo(conta.getN_conta());
                                            System.out.println("Saldo atual: " + saldoR);
                                            System.out.println("Informe quanto deseja aplicar: ");
                                            double valorR = teclado.nextDouble();

                                            System.out.println("Valor aplicado. Saldo atual na Remda Fixa: " + stubCliente.depositarR(conta.getN_conta(), valorR));
                                            break;

                                        case 3:
                                            break;
                                    
                                        default:
                                            break;
                                    }

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
