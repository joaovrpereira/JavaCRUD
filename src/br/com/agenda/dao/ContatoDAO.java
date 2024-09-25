package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;



import br.com.agenda.factory.ConnectorFactory;
import br.com.agenda.model.*;

public class ContatoDAO {
	
	public void save (Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?,?,?)";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			
			//Cria a conexão com o Banco de Dados
			conn = ConnectorFactory.createConnectionToMySQL();
			
			
			//Criamos uma PreparedStatement para executar query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores da Query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn!= null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contatos;";
				
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		//Classe para recuperar os dados do banco 
		ResultSet rset = null;
		
		try {
			conn = ConnectorFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
				//Recuperar ID
				contato.setId(rset.getInt("id"));
				
				contato.setNome(rset.getString("nome"));
				
				contato.setIdade(rset.getInt("idade"));
				
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				contatos.add(contato);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset !=null) {
						rset.close();
					}
					if(pstm != null) {
						pstm.close();
					}
					if(conn != null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return contatos;
		
	}
	
	public void update(Contato contato) {
		
		String sql = " UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ?"+
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectorFactory.createConnectionToMySQL();
			
			//Criar classe para executar query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			
			pstm.setInt(2,contato.getIdade());
			
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			//Qual o ID do registro que deseja atualizar?
			pstm.setInt(4, contato.getId());
			
			pstm.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	public void deleteByID(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectorFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement)conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn!= null) {
					conn.close();
				}	
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

