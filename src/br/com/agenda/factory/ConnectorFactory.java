package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectorFactory {

	//nome do usuário no banco de dados
	private static final String USERNAME = "root";

	private static final String PASSWORD = "";

	//Caminho do banco de dados, porta, nome do banco
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	/*
		Conexão com Banco de Dados
	*/

	public static Connection createConnectionToMySQL() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Cria a conexão com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;

	}

	public static void main(String[] args) throws Exception {
		//Recuperar conexão com Banco de Dados
		
		Connection con = createConnectionToMySQL();
		
		//Testando se a conexão é nula
		if(con!=null) {
			System.out.println("Conexão obtida com Sucesso");
			con.close();
		}
	}
}
