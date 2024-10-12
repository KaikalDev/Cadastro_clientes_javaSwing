package br.com.kaique.cadastroSwing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class gui {

    private JPanel panel1;
    private JTabbedPane navBar;
    private JLabel nomeLabel;
    private JTextField nomeInput;
    private JLabel cpfLabel;
    private JTextField cpfInput;
    private JLabel telefoneLabel;
    private JTextField telefoneInput;
    private JLabel enderecoLabel;
    private JTextField enderecoInput;
    private JLabel numeroLabel;
    private JTextField numeroInput;
    private JLabel cidadeLabel;
    private JTextField cidadeInput;
    private JLabel estadoLabel;
    private JTextField estadoInput;
    private JButton cadastarButton;
    private JButton cancelarButton;
    private JTextField cpfBusca;
    private JButton buscarButton;
    private JTable table1;
    private JTextField cpfBuscaEdit;
    private JButton buscarButton1;
    private JTextField nomeEdit;
    private JTextField telEdit;
    private JTextField endEdit;
    private JTextField numeroEdit;
    private JTextField cidadeEdit;
    private JTextField estadoEdit;
    private JButton salvarEdicaoButton;
    private JButton cancelarButton1;
    private JButton deletarClienteButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Cliente");
        frame.setContentPane(new gui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public gui() {
        actionsGui.initTabel(table1);
        actionsGui.atualizaTabel();

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionsGui.limpaCampo(
                        nomeInput,
                        cpfInput,
                        numeroInput,
                        telefoneInput,
                        cidadeInput,
                        estadoInput,
                        enderecoInput
                );
            }
        });

        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionsGui.isValid(
                        nomeInput.getText(),
                        cpfInput.getText(),
                        numeroInput.getText(),
                        enderecoInput.getText(),
                        telefoneInput.getText(),
                        cidadeInput.getText(),
                        estadoInput.getText()
                )) {
                    if (actionsGui.cadastraCliente(
                            nomeInput.getText(),
                            cpfInput.getText(),
                            numeroInput.getText(),
                            enderecoInput.getText(),
                            telefoneInput.getText(),
                            cidadeInput.getText(),
                            estadoInput.getText()
                    )) {
                        actionsGui.atualizaTabel();
                        actionsGui.limpaCampo(
                                nomeInput,
                                cpfInput,
                                numeroInput,
                                telefoneInput,
                                cidadeInput,
                                estadoInput,
                                enderecoInput
                        );
                    }
                } else {
                    actionsGui.erro("Campo invalido");
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionsGui.isValid(cpfBusca.getText())) {
                    Long cpf = Long.parseLong(actionsGui.allRepace(cpfBusca.getText()));
                    actionsGui.buscaCliente(table1, cpf);
                } else {
                    actionsGui.atualizaTabel();
                }
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = table1.getSelectedRow();
                actionsGui.visualizaCliente(row);
            }
        });

        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionsGui.isValid(cpfBuscaEdit.getText())) {
                    Long cpf = Long.parseLong(actionsGui.allRepace(cpfBuscaEdit.getText()));
                    actionsGui.buscarEdit(
                            nomeEdit,
                            telEdit,
                            endEdit,
                            numeroEdit,
                            cidadeEdit,
                            estadoEdit,
                            cpf
                    );
                } else {
                    actionsGui.limpaCampo(
                            nomeEdit,
                            numeroEdit,
                            telEdit,
                            cidadeEdit,
                            estadoEdit,
                            endEdit,
                            cpfBuscaEdit
                    );
                }
            }
        });

        cancelarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionsGui.limpaCampo(
                        nomeEdit,
                        numeroEdit,
                        telEdit,
                        cidadeEdit,
                        estadoEdit,
                        endEdit,
                        cpfBuscaEdit
                );
            }
        });

        salvarEdicaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long cpf = Long.parseLong(actionsGui.allRepace(cpfBuscaEdit.getText()));
                actionsGui.saveEdit(
                        nomeEdit.getText(),
                        telEdit.getText(),
                        endEdit.getText(),
                        numeroEdit.getText(),
                        cidadeEdit.getText(),
                        estadoEdit.getText(),
                        cpf
                );
                actionsGui.limpaCampo(
                        nomeEdit,
                        numeroEdit,
                        telEdit,
                        cidadeEdit,
                        estadoEdit,
                        endEdit,
                        cpfBuscaEdit
                );
            }
        });

        deletarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionsGui.isValid(cpfBuscaEdit.getText())) {
                    Long cpf = Long.parseLong(actionsGui.allRepace(cpfBuscaEdit.getText()));
                    actionsGui.deletaCliente(cpf);
                }
                actionsGui.limpaCampo(
                        nomeEdit,
                        numeroEdit,
                        telEdit,
                        cidadeEdit,
                        estadoEdit,
                        endEdit,
                        cpfBuscaEdit
                );
            }
        });
    }
}
