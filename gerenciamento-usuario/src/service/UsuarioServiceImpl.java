package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Usuario;

public class UsuarioServiceImpl implements UsuarioService {
	
	//Simular que isso aqui eh um bando de dados
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@Override
	public void salvarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	@Override
	public Usuario buscaUsuarioPorId(Long id) {
		// Aplicar streams aqui ....
		for (Usuario u : usuarios) {
			if(u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return this.usuarios;
	}

	@Override
	public boolean deletarUsuarioPorId(Long id) {
		// streams e lambdas
		for (Usuario u : usuarios) {
			if(u.getId().equals(id)) {
				this.usuarios.remove(u);
				return  true;
			}
		}
		
		return false;
	}

	@Override
	public boolean atualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
