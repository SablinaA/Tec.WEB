package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO {
	
	private String jdbcUrl = "jdbc:mysql://localhost:3306/usuario";
	private String jdbcUser = "root";
	private String jdbcSenha = "root";
	private Connection jsbcConnection;
	
	public UsuarioDAOImpl(String jdbcUrl, String jdbcUser, String jdbcSenha) {
		
		this.jdbcUrl = jdbcUrl;
		this.jdbcSenha = jdbcSenha;
		this.jdbcUser = jdbcUser;
	}
	
	public void conectar() throws SQLException {
		
		if(jsbcConnection == null || jsbcConnection.isClosed()) {
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				
			} catch (Exception e) {
				throw new SQLException();
			}
			
			this.jsbcConnection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcSenha);
		}
	}
	
	public void disconect() throws SQLException {
		if(jsbcConnection != null && !jsbcConnection.isClosed()) {
			this.jsbcConnection.close();
		}
	}
	
	

	@Override
	public boolean salvar(Usuario usuario) {
		
		boolean inseriu = false;
		String sql = "INSERT INTO usuario (nome, email, telefone) VALUES (?,?,?)";
		
		try {
			conectar();
			
			PreparedStatement statement = this.jsbcConnection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getTelefone());
			
			inseriu = statement.executeUpdate() > 0;
			statement.close();
			disconect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return inseriu;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuario";
		try {
			PreparedStatement statement = this.jsbcConnection.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Long id = (long) result.getInt("id");
				String nome = result.getString("nome");
				String email = result.getString("email");
				String telefone = result.getString("telefone");
				
				Usuario usuario = new Usuario();
				usuario.setId(id);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setTelefone(telefone);
				
				usuarios.add(usuario);
			}
			
			result.close();
			statement.close();
			disconect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return usuarios;

	}

	@Override
	public void removerUsuarioPorId(int id) {
		
		String deletar = "DELETE FROM usuario WHERE id LIKE ?";
		
		
		try {
			
			conectar();
			
			PreparedStatement statement = this.jsbcConnection.prepareStatement(deletar);
			statement.setLong(1, id);
			
			System.out.println(statement.executeUpdate());
			System.out.println("Usuário de Id " + id + " removido");
			
			statement.close();
			disconect();
			
		} catch  (SQLException e){
			
			e.printStackTrace();
			System.out.println("Usuario não localizado");
		}
		

	}
			 			
		
	
	
	@Override
	public Usuario buscaPorNomeUsuario(String nome) throws SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/usuario", "root", "root");
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario ");
		ResultSet rs = stmt.executeQuery();

		try{

		while(rs.next()) {

		          String usuario = rs.getString("nome");

		            if(usuario.equals(nome)){                    

		              String id = rs.getString("id");
		  			  String email = rs.getString("email");
		  			  String telefone = rs.getString("telefone");
		 	
		  			  System.out.println("-----Usuario encontrado:------");
		  			  System.out.println("ID: "+ id+
		  					            "\nNome: " + usuario +
		  					  			"\nEmail: " + email+
		  					  			"\nTelefone: "+telefone);		  			
		            } ;
		  			  }
					rs.close();
		  			stmt.close();
					disconect();
		} 
	
		catch (SQLException e) {
		          		System.out.println("Usuario não encontrado");

		}
		return null;
	
	
	}}
		

