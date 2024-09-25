package br.com.agenda.view;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {
	
	public static void main(String[] args) {

		ContatoDAO contatoDao = new ContatoDAO();
		
		//Cadastro de Usuario
		Contato contato = new Contato();
		contato.setNome("João");
		contato.setIdade(24);
		contato.setDataCadastro(new Date());
		
		contatoDao.save(contato);
		
		
		//Atualizacao do contato
		
		Contato contato01 = new Contato();
		contato01.setNome("Maria Gabriela");
		contato01.setIdade(26);
		contato01.setDataCadastro(new Date());
		//Update de acordo com o valor do Id
		contato01.setId(1);
		
		contatoDao.update(contato01);
		
		
		//Visualização dos registros
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: "+c.getNome());
			System.out.println("Idade: "+c.getIdade());
			
		}
		
		//Deletar o contato pelo seu número de ID
		contatoDao.deleteByID(3);
		
	}
}
