package br.com.compassuol.pb.challenge.msusers.service;

import br.com.compassuol.pb.challenge.msusers.payload.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	
	UserDto getUserById(long id);
	
	UserDto updateUser(UserDto userDto, long id);
}
