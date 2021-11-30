package dao;


import java.sql.SQLException;
import java.util.List;
import model.Usuario;

public interface UsuarioDAO {

	public boolean salvar(Usuario usuario);
	public List<Usuario> listarUsuarios();
	void removerUsuarioPorId(int id);
	Usuario buscaPorNomeUsuario(String nome) throws SQLException;

}


