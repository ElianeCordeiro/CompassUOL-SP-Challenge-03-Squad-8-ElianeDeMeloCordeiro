package br.com.compassuol.pb.challenge.msusers.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.msusers.entities.Role;
import br.com.compassuol.pb.challenge.msusers.entities.User;
import br.com.compassuol.pb.challenge.msusers.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msusers.payload.RoleDto;
import br.com.compassuol.pb.challenge.msusers.payload.UserDto;
import br.com.compassuol.pb.challenge.msusers.repositories.RoleRepository;
import br.com.compassuol.pb.challenge.msusers.repositories.UserRepository;
import br.com.compassuol.pb.challenge.msusers.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	private ModelMapper mapper;
	
	private RoleRepository roleRepository;
	
	public UserServiceImpl(UserRepository userRepository,
						ModelMapper mapper,
						RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.mapper = mapper;
		this.roleRepository = roleRepository;
	}


	@Override
	public UserDto createUser(UserDto userDto) {

		Set<Role> roles = new HashSet<>();
		
		Set<RoleDto> rolesDto = userDto.getRoles();
		rolesDto.forEach(role -> {
			Role roleSearch = roleRepository.findById(role.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Role", "id", role.getId()));
		roles.add(roleSearch);
		});
		
		User user = mapToEntity(userDto);
		user.setRoles(roles);
		User savedUser = userRepository.save(user);

		UserDto userResponse = mapToDto(savedUser);
		return userResponse;
	}

	
	@Override
	public UserDto getUserById(long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		return mapToDto(user);
	}
	

	@Override
	public UserDto updateUser(UserDto userDto, long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

		User newUser = mapToEntity(userDto);
		
		user.setRoles(newUser.getRoles());
		user.setEmail(newUser.getEmail());
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setPassword(newUser.getPassword());
		
		User updatedUser = userRepository.save(user);
		
		return mapToDto(updatedUser);
	}
	

	private UserDto mapToDto(User user) {
		UserDto userDto = mapper.map(user, UserDto.class);
		return userDto;
	}
	
	private User mapToEntity(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		return user;
	}
	
}
