package br.com.fecaf.controller;

import br.com.fecaf.model.Cliente;
import br.com.fecaf.model.Conta;

import java.util.Scanner;

public class Login {

    Scanner scanner = new Scanner(System.in);

    public Conta realizarLogin(Conta referenciaConta, Cliente referenciaCLiente){
        System.out.print("Informe o CPF: ");
        long cpfUser = scanner.nextLong();
        scanner.nextLine();
        System.out.println("informe a senha: ");
        String passwordUser = scanner.nextLine();


        Conta contaCliente = validarLogin(cpfUser, passwordUser,referenciaConta, referenciaCLiente);

        if (contaCliente != null){
            return contaCliente;
        } else{
            System.out.println("Dados Incorretos ! Verifique novamente ou abra uma conta. ");
        }
        return null;
    }

    public Conta validarLogin (long cpfUser, String passwordUser, Conta referenciaConta,Cliente referenciaCLiente){
        Cliente dadosCliente = referenciaCLiente.pesquisarCliente(cpfUser);

        if (dadosCliente != null);{
            Conta contaCliente = referenciaConta.pesquisarConta(dadosCliente);
            String password = contaCliente.getPassword();

            if (password.equals(passwordUser)) {
                return contaCliente;
            }
        }
        return null;
    }
}
