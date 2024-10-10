package br.com.kaique.dao;

import br.com.kaique.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kaique
 */
public class ClienteMapDAO implements IclienteDAO{

    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }

    @Override
    public Boolean cadastro(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())){
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public Boolean excluir(Long cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);

        if (clienteCadastrado != null) {
            this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
            return true;
        }
        return false;

    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf());

        if (clienteCadastrado != null) {
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setEstado(cliente.getEstado());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setTel(cliente.getTel());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
