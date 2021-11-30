
import java.sql.SQLException;
import java.util.List;
import dao.UsuarioDAOImpl;
import model.Usuario;

public class Test {

	public static void main(String[] args) {
		
		/*----- Criando os usuários -----*/
		Usuario usuario = new Usuario();
		//usuario.setNome("Maria Santos");
		//usuario.setEmail("Maria@gmail.com");
		//usuario.setTelefone("839917845129");
		
		Usuario usuario2 = new Usuario();
				//usuario2.setNome("Sablina");
				//usuario2.setEmail("Sablina@gmail.com");
				//usuario2.setTelefone("83991267778");
				
		Usuario usuario3 = new Usuario();
				//usuario3.setNome("Julia Cruz");
				//usuario3.setEmail("Julia@gmail.com");
				//usuario3.setTelefone("83978521469");
				
		
		/*------ Fazendo a conexão com o banco de dados ------*/
		
		UsuarioDAOImpl dao = new UsuarioDAOImpl(
				"jdbc:mysql://localhost:3306/usuario","root", "root");
		
		/*----- salvando os usuarios ----*/
		dao.salvar(usuario);
		dao.salvar(usuario2);
		dao.salvar(usuario3);
		
		
		/*----- Listando os usuários -----*/
		List<Usuario> usuarios = dao.listarUsuarios();
		for (Usuario u : usuarios) {
			System.out.println(u.toString());}

		
		/*----- fazendo a remoção do usuário ----*/
		dao.removerUsuarioPorId(5);
		
		
		/*----- Listando os usuários -----*/
		for (Usuario u : usuarios) {
			System.out.println(u.toString());}
		
		
		/* ---- Buscando usuario ----*/
			
				try {
					dao.buscaPorNomeUsuario("Sablina");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	

}
	}
		
		
