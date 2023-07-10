package br.com.compassuol.pb.challenge.msusers.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.compassuol.pb.challenge.msusers.entities.Role;
import br.com.compassuol.pb.challenge.msusers.entities.User;
import br.com.compassuol.pb.challenge.msusers.payload.RoleDto;
import br.com.compassuol.pb.challenge.msusers.payload.UserDto;
import br.com.compassuol.pb.challenge.msusers.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	
	
	@Mock
	private UserRepository userRepository;
	
	@Spy
	private ModelMapper mapper;
	
	@InjectMocks
	private UserService userService;
	
	
	@Test
	public void findUserByIdSuccessTest() {
		
		User user = createUserDefault();
		UserDto expectedUserDto = createExpectedUserDefault();
		
		when(userRepository.findById(any())).thenReturn(Optional.of(user));
		
		UserDto request = userService.getUserById(1);
		
		assertAll("response",
						() -> assertEquals(expectedUserDto.getFirstName(), request.getFirstName()),
						() -> assertEquals(expectedUserDto.getLastName(), request.getLastName()),
						() -> assertEquals(expectedUserDto.getEmail(), request.getEmail()),
						() -> assertEquals(expectedUserDto.getPassword(), request.getPassword())
				);
		
		int i =0;
		
		while(i<expectedUserDto.getRoles().size()) {
			for (RoleDto roleDto : expectedUserDto.getRoles()) {
				
			}
		}
		
	}
	
	
	private User createUserDefault() {
		Role role = new Role((long) 1, "OPERATOR");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		User userDefault = new User((long)1, "Eliane", "Cordeiro", "eliane@gmail.com", "eliane", roles);
		
		return userDefault;
	}
	
	private UserDto createExpectedUserDefault() {
		RoleDto roleDto = new RoleDto((long) 1, "OPERATOR");
		Set<RoleDto> rolesDto = new HashSet<>();
		rolesDto.add(roleDto);
		
		UserDto userDto = new UserDto("Eliane", "Cordeiro", "eliane@gmail.com", "eliane", rolesDto);
		
		return userDto;
		
	}	
	
	
}
