package br.com.kaique;

import br.com.kaique.dao.ClienteMapDAO;
import br.com.kaique.dao.IclienteDAO;
import br.com.kaique.domain.Cliente;

import javax.swing.*;
import java.util.Collection;

/**
 * @author Kaique
 */
public class App {

    private static IclienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        do {
            String opcao = JOptionPane.showInputDialog(null,
                    "Escolha uma opção (1 - 5): \n1 - Cadastro \n2 - Consulta \n3 - Exclusão \n4 - alteração \n5 - Sair",
                    "Cadastro de clientes", JOptionPane.INFORMATION_MESSAGE);

            switch (opcao) {
                case "1":
                    cadastrar();
                    break;
                case "2":
                    String tipoConsulta;
                    do {
                        tipoConsulta = JOptionPane.showInputDialog(null,
                                "Qual o tipo da consulta: \n1 - Consulta CPF\n2 - Consulta Tudo",
                                "Consulta", JOptionPane.INFORMATION_MESSAGE);
                    }while (!tipoConsulta.equals("1") && !tipoConsulta.equals("2"));
                    if ("1".equals(tipoConsulta)) {
                        consultar(pedeCpf());
                    } else {
                        consultarTudo();
                    }
                    break;
                case "3":
                    excluir(pedeCpf());
                    break;
                case "4":
                    alterar(pedeCpf());
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null,"Saindo ...",
                            "Sair", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                    break;
            }
        }while (true);
    }

    private static void consultarTudo() {
        Collection<Cliente> clientes = iClienteDAO.buscarTodos();
        StringBuilder menssage = new StringBuilder();

        for (Cliente cliente : clientes) {
            menssage.append(cliente.toString()).append("\n");
        }

        if (!clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Clientes encontrados: " + menssage,
                    "Consulta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"Não tem clientes cadastrados: ",
                    "Consulta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void alterar(long cpf) {
        Cliente clienteOriginal = iClienteDAO.consultar(cpf);
        if (clienteOriginal != null) {
            String nome = JOptionPane.showInputDialog(null,
                    "Digite o nome do cliente:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE,
                    null,null,clienteOriginal.getNome()).toString();
            String telString = JOptionPane.showInputDialog(null,
                            "Digite o telefone (somente números):", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE,
                            null,null,clienteOriginal.getTel().toString()).toString()
                    .replace("(","")
                    .replace(")","")
                    .replace("-", "")
                    .replace(" ", "");
            String end = JOptionPane.showInputDialog(null,
                    "Digite o endereço:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE,
                    null,null,clienteOriginal.getEnd()).toString();
            String numeroString = JOptionPane.showInputDialog(null,
                    "Digite o número da residência:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE,
                    null,null,clienteOriginal.getNumero().toString()).toString();
            String cidade = JOptionPane.showInputDialog(null,
                    "Digite a cidade:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE,
                    null,null,clienteOriginal.getCidade()).toString();
            String estado = JOptionPane.showInputDialog(null,
                    "Digite o estado:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE,
                    null,null,clienteOriginal.getEstado()).toString();

            Long tel = Long.parseLong(telString);
            Integer numero = Integer.parseInt(numeroString);

            Cliente clienteAlterado = new Cliente(nome,cpf,tel,end,numero,cidade,estado);
            iClienteDAO.alterar(clienteAlterado);
        } else {
            JOptionPane.showMessageDialog(null,"Cliente não encontrado",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void excluir(long cpf) {
        boolean isDelet = iClienteDAO.excluir(cpf);
        if (isDelet) {
            JOptionPane.showMessageDialog(null,"Cliente " + cpf + " deletado",
                    "Excluir", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"Cliente não encontrado",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(long cpf) {
        Cliente cliente = iClienteDAO.consultar(cpf);
        if (cliente != null) {
            JOptionPane.showMessageDialog(null,"Cliente Encontrado: " + cliente,
                    "Consulta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"Cliente não encontrado: ",
                    "Consulta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static long pedeCpf() {
        String cpfString = JOptionPane.showInputDialog(null,
                        "Digite o CPF (somente números):", "Consulta", JOptionPane.INFORMATION_MESSAGE)
                .replace(".", "")
                .replace("-","")
                .replace(" ", "");

        return Long.parseLong(cpfString);
    }

    private static void cadastrar() {
        String nome = JOptionPane.showInputDialog(null,
                "Digite o nome do cliente:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
        String cpfString = JOptionPane.showInputDialog(null,
                "Digite o CPF (somente números):", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE)
                .replace(".", "")
                .replace("-","")
                .replace(" ", "");
        String telString = JOptionPane.showInputDialog(null,
                "Digite o telefone (somente números):", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE)
                .replace("(","")
                .replace(")","")
                .replace("-", "")
                .replace(" ", "");
        String end = JOptionPane.showInputDialog(null,
                "Digite o endereço:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
        String numeroString = JOptionPane.showInputDialog(null,
                "Digite o número da residência:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
        String cidade = JOptionPane.showInputDialog(null,
                "Digite a cidade:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
        String estado = JOptionPane.showInputDialog(null,
                "Digite o estado:", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);

        Long cpf = Long.parseLong(cpfString);
        Long tel = Long.parseLong(telString);
        Integer numero = Integer.parseInt(numeroString);

        Cliente cliente = new Cliente(nome,cpf,tel,end,numero,cidade,estado);
        boolean isCadastrado = iClienteDAO.cadastro(cliente);

        if(isCadastrado) {
            JOptionPane.showMessageDialog(null,"Cliente cadastrado", "Cadastro",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar", "Cadastro",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
