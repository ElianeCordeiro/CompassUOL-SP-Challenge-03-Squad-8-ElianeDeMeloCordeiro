package br.com.compassuol.pb.challenge.msusers.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.msusers.entities.Role;
import br.com.compassuol.pb.challenge.msusers.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msusers.payload.RoleDto;
import br.com.compassuol.pb.challenge.msusers.repositories.RoleRepository;
import br.com.compassuol.pb.challenge.msusers.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	
	private ModelMapper mapper;
	
	public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
		this.roleRepository = roleRepository;
		this.mapper = mapper;
	}

	@Override
	public RoleDto createRole(RoleDto roleDto) {
		
		Role role = roleRepository.save(mapToEntity(roleDto));
		RoleDto savedRole = mapToDto(role);
		
		return savedRole;
	}

	@Override
	public RoleDto getRoleById(long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
		
		return mapToDto(role);
	}

	@Override
	public RoleDto updateRole(RoleDto roleDto, long id) {
		
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
		
		Role newRole = mapToEntity(roleDto);
		
		role.setName(newRole.getName());
		
		Role updatedRole = roleRepository.save(role);
		
		return mapToDto(updatedRole);
	}

	private RoleDto mapToDto(Role role) {
		RoleDto roleDto = mapper.map(role, RoleDto.class);
		return roleDto;
	}
	
	private Role mapToEntity(RoleDto roleDto) {
		Role role = mapper.map(roleDto, Role.class);
		return role;
	}
}
