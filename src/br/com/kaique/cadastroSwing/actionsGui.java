package br.com.kaique.cadastroSwing;

import br.com.kaique.dao.ClienteMapDAO;
import br.com.kaique.dao.IclienteDAO;
import br.com.kaique.domain.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class actionsGui {
    public static IclienteDAO clinteDAO = new ClienteMapDAO();

    public static DefaultTableModel model = new DefaultTableModel();

    public static void limpaCampo(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    public static void erro(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static boolean cadastraCliente(
            String nome,
            String cpf,
            String numero,
            String endereco,
            String telefone,
            String cidade,
            String estado
    ) {
        Long cpfLong = Long.parseLong(allRepace(cpf));
        Long telLong = Long.parseLong(allRepace(telefone));
        Integer numeroInt = Integer.parseInt(numero);

        Cliente cliente = new Cliente(nome, cpfLong, telLong, endereco, numeroInt, cidade, estado);
        boolean jaExiste = clinteDAO.cadastro(cliente);
        if (jaExiste) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cliente\n" + cliente + "\n cadastrado",
                    "cadastro",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        } else {
            erro("Cliente ja existe");
            return false;
        }
    }

    public static boolean isValid(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void initTabel(JTable tabela) {
        model.addColumn("Nome");
        model.addColumn("CPF");

        tabela.setModel(model);
    }

    public static void atualizaTabel() {
        model.setRowCount(0);
        Collection<Cliente> clientes = clinteDAO.buscarTodos();

        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getNome(), cliente.getCpf()});
        }
    }

    public static void buscaCliente(JTable tabela, Long cpf) {
        model.setRowCount(0);

        Cliente cliente = clinteDAO.consultar(cpf);
        if (cliente != null) {
            model.addRow(new Object[]{cliente.getNome(), cliente.getCpf()});
        } else {
            erro("Cliente não encontrado");
            atualizaTabel();
        }
    }

    public static String allRepace(String text) {
        return text.replace(" ", "")
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace(".", "");
    }

    public static void visualizaCliente(int row) {
        Collection<Cliente> clientes = clinteDAO.buscarTodos();
        List<Cliente> listaClientes = new ArrayList<>(clientes);
        Cliente cliente = listaClientes.get(row);

        String menssage = "Nome: " + cliente.getNome() +
                "\nCPF: " + cliente.getCpf() +
                "\nTelefone: " + cliente.getTel() +
                "\nEndereço: " + cliente.getEnd() +
                "\nNumero: " + cliente.getNumero() +
                "\nCidade: " + cliente.getCidade() +
                "\nEstado: " + cliente.getEstado();

        JOptionPane.showMessageDialog(
                null,
                menssage,
                "Cliente",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void buscarEdit(
            JTextField nomeInput,
            JTextField telInput,
            JTextField endInput,
            JTextField numeroInput,
            JTextField cidadeInput,
            JTextField estadoInput,
            Long cpf
    ) {
        Cliente cliente = clinteDAO.consultar(cpf);
        if (cliente != null) {
            nomeInput.setText(cliente.getNome());
            telInput.setText(cliente.getTel().toString());
            endInput.setText(cliente.getEnd());
            numeroInput.setText(cliente.getNumero().toString());
            cidadeInput.setText(cliente.getCidade());
            estadoInput.setText(cliente.getEstado());
        } else {
            erro("Cliente não encontrado");
        }
    }

    public static void saveEdit(
            String nome,
            String tel,
            String end,
            String numero,
            String cidade,
            String estado,
            Long cpf
    ) {
        Long telLong = Long.parseLong(tel);
        Integer numeroInt = Integer.parseInt(numero);

        Cliente cliente = new Cliente(nome, cpf, telLong, end, numeroInt, cidade, estado);
        clinteDAO.alterar(cliente);
        atualizaTabel();
    }

    public static void deletaCliente(Long cpf) {
        Cliente cliente = clinteDAO.consultar(cpf);

        int option = JOptionPane.showConfirmDialog(
                null,
                "Deseja excluir o cliente ?" + cliente.toString(),
                "Exclusão",
                JOptionPane.YES_NO_OPTION
        );
        if (option == JOptionPane.YES_OPTION) {
            clinteDAO.excluir(cpf);
            atualizaTabel();
        }
    }
}
