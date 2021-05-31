package com.tienda.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.dtos.Combinada;
import com.tienda.dtos.IProdPedCat;
import com.tienda.dtos.Icombinada;
import com.tienda.dtos.Ipedidos;
import com.tienda.dtos.UsuarioDTO;
import com.tienda.entities.UsuarioEntity;
import com.tienda.repositories.UsuarioDao;
import com.tienda.services.UsuarioService;

import ch.qos.logback.core.status.Status;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@GetMapping({"/listar","/",""})
	public List <UsuarioEntity> listarUsuarios(){
		
		return usuarioService.encontraUsuarios();
	}
	
	@GetMapping("/com")
	public List <Icombinada> combinada(){
		
		return usuarioService.combinado();
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody UsuarioDTO usuario, HttpServletResponse response){
		Map<String, Object> msj = new HashMap<String,Object>();
		
		try {
			
			
			if(usuario !=null) {
				UsuarioEntity user = new UsuarioEntity();
				user.setNombre(usuario.getNombre());
				user.setApellido(usuario.getApellido());
				user.setEmail(usuario.getEmail());
				user.setFechaNacimiento(usuario.getFechaNacimiento());
				user.setPassword(usuario.getPassword());
				usuarioDao.save(user);
				
				//return el map
				msj.put("error", false);
				msj.put("msj","Se guardo exitosamente");
				msj.put("objeto", usuario);
				return new ResponseEntity<>(msj, HttpStatus.OK);
			}else {
				
				//return el map
				msj.put("error", true);
				msj.put("msj","No hubo usuario");
				msj.put("objeto", usuario);
				return new ResponseEntity<>(msj, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			
			msj.put("error", true);
			msj.put("msj",e.getMessage());
			msj.put("objeto", null);
			return new ResponseEntity<>(msj,HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping("/email/{email}")
	public boolean existeEmail(@PathVariable(name="email") String email) {
		return usuarioService.existeUsuario(email);
	}
	@GetMapping("/email2/{email}")
	public List<String> existeE(@PathVariable(name="email") String email) {
		return usuarioService.existeUsu(email);
	}
	
	@GetMapping("/user/{id}")
	public UsuarioEntity encontrarUsario(@PathVariable(name="id") Long id) {
		return usuarioService.encontrarUsuario(id);
	}
	
	@GetMapping("/pedidos/{id}")
	public List<Ipedidos> encontrarPedidos(@PathVariable(name="id") Long id) {
		return usuarioService.encontrarPedidos(id);
	}

	@GetMapping("/productos/{idPedido}")
	public List<IProdPedCat> encontrarProductos(@PathVariable(name="idPedido") Long idPedido) {
		return usuarioService.encontrarProductos(idPedido);
	}
	
	@GetMapping("/token/{email}/{pass}")
	public UsuarioDTO getToken(@PathVariable(value = "email" ) String email,@PathVariable(value = "pass") String pass) {
		
		
		return usuarioService.getToken(email, pass);
	}
}
