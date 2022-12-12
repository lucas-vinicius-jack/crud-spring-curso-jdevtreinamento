package br.com.springboot.cursojdevtreinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.cursojdevtreinamento.model.Usuario;
import br.com.springboot.cursojdevtreinamento.repository.UsuarioRepository;

@RestController
public class GretingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/mostranome/{name}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable  String name) {
		return name;
	}
	
	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuarioRepository.save(usuario);
		
		return "Ola Mundo " + greetingText(nome);
	}
	
	@GetMapping(value = "listatodos")
	@ResponseBody 
	public ResponseEntity<List<Usuario>> listaUsuario(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		
		Usuario user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long iduser){
		
		usuarioRepository.deleteById(iduser);
		
		return new ResponseEntity<String>("User deletado com sucesso",HttpStatus.OK);
	}
	
	@GetMapping(value = "buscauserid")
	@ResponseBody
	public ResponseEntity<Usuario> buscaUserId(@RequestParam(name = "iduser") Long iduser){
		
		Usuario usuario = usuarioRepository.findById(iduser).get();
		
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}
	
	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
		
		
		if(usuario.getId() == null) {
			return new ResponseEntity<String>("Id não informado para atualização.",HttpStatus.OK);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
	
		
		return new ResponseEntity<Usuario>(user,HttpStatus.OK);
		
		
	}
	
	@GetMapping(value = "buscarpornome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarpornome(@RequestParam(name = "name")String name){
		List<Usuario> usuario = usuarioRepository.buscarProNome(name.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario,HttpStatus.OK);
	}
	
	
}
