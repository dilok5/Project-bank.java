package br.com.fecaf.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conta {
    private int numeroConta;
    private String agencia = "2808-xx";
    String password, confirmPassword;
    private double saldo = 0;
    private Cliente cliente;

    List<Conta> listContas = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public void transferir(Cliente referenciaCliente, Conta contaCliente){
        System.out.println("Informe o CPF do destinatário: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();

        Cliente clienteDestinatario = referenciaCliente.pesquisarCliente(cpf);
        Conta contaDestinatario = pesquisarConta(clienteDestinatario);

        if(contaDestinatario != null){
            System.out.println("| Transferencia para: " + clienteDestinatario.getNome());
            System.out.print("Informe o valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if(contaCliente.saldo >= valor){
                System.out.println("Tranferencia realizda com sucesso !!!");
                System.out.println("----------------- Comprovante : ");
                System.out.println("Agencia: " + contaCliente.getAgencia());
                System.out.println("Conta: " + contaCliente.getNumeroConta());
                System.out.println("Remetente: " + contaCliente.cliente.getNome());
                System.out.println("CPF do remetente: " + contaCliente.cliente.getCpf());
                System.out.println("Valor: " + valor);
                System.out.println("Agencia: " + contaDestinatario.getAgencia());
                System.out.println("Conta: " + contaDestinatario.getNumeroConta());
                System.out.println("Destinatario: " + contaDestinatario.cliente.getNome());
                System.out.println("CPF do destinatario: " + contaDestinatario.cliente.getCpf());
                System.out.println("/-----------------------------------------------------/");

                contaCliente.saldo -= valor;
                contaDestinatario.saldo += valor;

            }else {
                System.out.println("Saldo para transferencia indisponivel !");
            }
        }else {
            System.out.println("CPF destinatario não encontrado !");
        }

    }

    public void criarConta(Cliente referenciaCliente){
        Cliente cliente = new Cliente();
        long cpfCliente = cliente.cadastrarCliente();
        referenciaCliente.adicionarClientes(cliente);

        Conta conta = new Conta();

        Cliente clienteConta = referenciaCliente.pesquisarCliente(cpfCliente);

        conta.gerarConta(clienteConta);
        adicionarList(conta);

    }

    public void gerarConta(Cliente cliente){
        System.out.println("/----------------  Gerando Conta  --------------------/ ");
        numeroConta = (int) (Math.random()*10000);
        System.out.print("Informe uma senha: ");
        password = scanner.nextLine();

        do {
            System.out.print("Confirme a senha: ");
            confirmPassword = scanner.nextLine();

            if(!password.equals(confirmPassword)){
                System.out.println("Revise a senha! ");
            }
        }while (!password.equals(confirmPassword));

        System.out.println("/------------------------------------------------------------/");
        this.cliente = cliente;

    }

    public void realizarSaque(double valor){
        boolean validaSaque = avaliarSaque(valor);

        if (validaSaque){
            this.saldo -= valor;
        }else {
            System.out.println("Impossivel realizar saque: !!!");
        }

        System.out.println("O saldo disponível na conta é: " + this.saldo);
    }

    public boolean avaliarSaque(double valor){
        if (this.saldo >= valor){
            return true;
        }
        return false;

    }

    public void realizarDeposito(double valor){
       this.saldo += valor;
        System.out.println("O saldo disponível na conta é: " + this.saldo);
    }

    public void consultarSaldo(){
        System.out.println("O saldo disponível na conta é: " + this.saldo);
    }

    public void adicionarList(Conta conta){
        listContas.add(conta);
    }

    public Conta pesquisarConta(Cliente cliente) {
        for(Conta conta : listContas) {
            if(cliente == conta.cliente){
               return conta;
            }
        }
        return null;
    }

    public void exibirPerfil(Conta conta){
        System.out.println("/------------------------------------------------------------/");
        System.out.println("Olá " + conta.cliente.getNome() + " !");
        System.out.println("Agência: " + conta.getAgencia());
        System.out.println("Conta: " + conta.getNumeroConta());
        System.out.println("/------------------------------------------------------------/");
    }

    public String getPassword() {
        return password;
    }

    public String getAgencia() {
        return agencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void transferir(){

    }

}


