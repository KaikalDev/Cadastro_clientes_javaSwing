package br.com.kaique.dao;

import br.com.kaique.domain.Cliente;

import java.util.Collection;

/**
 * @author Kaique
 */
public interface IclienteDAO {
    public Boolean cadastro(Cliente cliente);

    public Boolean excluir(Long cpf);

    public void alterar(Cliente cliente);

    public Cliente consultar(Long cpf);

    public Collection<Cliente> buscarTodos();
}
