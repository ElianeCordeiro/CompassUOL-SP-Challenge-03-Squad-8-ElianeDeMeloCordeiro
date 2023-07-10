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

import br.com.compassuol.pb.challenge.msusers.payload.UserDto;
import br.com.compassuol.pb.challenge.msusers.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
											@PathVariable(name = "id") long id){
		UserDto userResponse = userService.updateUser(userDto, id);
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

}
