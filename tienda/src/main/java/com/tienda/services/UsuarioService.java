package com.tienda.services;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tienda.dtos.Combinada;
import com.tienda.dtos.IProdPedCat;
import com.tienda.dtos.Icombinada;
import com.tienda.dtos.Ipedidos;
import com.tienda.dtos.UsuarioDTO;
import com.tienda.entities.UsuarioEntity;


public interface UsuarioService {
	
	public List<UsuarioEntity> encontraUsuarios();
	
	public List<Icombinada> combinado();
	
	public UsuarioEntity registrar(UsuarioDTO usuario);
	
	public boolean existeUsuario(String email);
	
	public List<String> existeUsu(String email);
	
	public UsuarioEntity encontrarUsuario(Long id);
	
	public List<Ipedidos> encontrarPedidos(Long id);
	
	public List<IProdPedCat> encontrarProductos(Long idPedido);
	
	public UsuarioDTO getToken(String email, String pass);

}
