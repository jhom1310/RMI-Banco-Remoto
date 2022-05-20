package servidor;

import java.io.Serializable;
import java.util.Objects;

public class Conta implements Serializable{
    private String n_conta;
    private String cpf;
    private String nome_cliente;
    private String endereco;
    private String data_nasc; // Como será somente para exbição simplifiquei pra uma String
    private String telefone;
    private String senha;
    private double saldo;
    private boolean isFunc;


    public Conta(String n_conta, String cpf, String nome_cliente, String endereco, String data_nasc, String telefone, String senha, double saldo, boolean isFunc) {
        this.n_conta = n_conta;
        this.cpf = cpf;
        this.nome_cliente = nome_cliente;
        this.endereco = endereco;
        this.data_nasc = data_nasc;
        this.telefone = telefone;
        this.senha = senha;
        this.saldo = saldo;
        this.isFunc = isFunc;
    }

    public double depositar(double valor){
        this.saldo += valor;
        return this.saldo;
    }

    public double sacar(double valor){
        if(this.saldo >= valor){
            this.saldo -= valor;
        }
        return this.saldo;
    }

    void transferir(Conta destino, double valor){
        if(this.saldo >= valor){
            this.saldo -= valor;
            destino.depositar(valor);
        }
    }

    public boolean getisFunc() {
        return this.isFunc;
    }
    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getN_conta() {
        return this.n_conta;
    }

    public void setN_conta(String n_conta) {
        this.n_conta = n_conta;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome_cliente() {
        return this.nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData_nasc() {
        return this.data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "{" +
            " n_conta='" + getN_conta() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", nome_cliente='" + getNome_cliente() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", data_nasc='" + getData_nasc() + "'" +
            ", telefone='" + getTelefone() + "'" +
            "}";
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Conta)) {
            return false;
        }
        Conta conta = (Conta) o;
        return Objects.equals(n_conta, conta.n_conta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n_conta);
    }

    
}
