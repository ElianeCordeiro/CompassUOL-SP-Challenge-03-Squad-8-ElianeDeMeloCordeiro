package br.com.compassuol.pb.challenge.msusers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.pb.challenge.msusers.payload.RoleDto;
import br.com.compassuol.pb.challenge.msusers.service.RoleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/role")
public class RoleController {
	
	private RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PostMapping
	public ResponseEntity<RoleDto> createRole(@RequestBody @Valid RoleDto roleDto){
		return new ResponseEntity<>(roleService.createRole(roleDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleDto> getRoleById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(roleService.getRoleById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleDto roleDto, 
												@PathVariable(name = "id") long id){
		RoleDto roleResponse = roleService.updateRole(roleDto, id);
		
		return new ResponseEntity<>(roleResponse, HttpStatus.OK);
	}
	
}
