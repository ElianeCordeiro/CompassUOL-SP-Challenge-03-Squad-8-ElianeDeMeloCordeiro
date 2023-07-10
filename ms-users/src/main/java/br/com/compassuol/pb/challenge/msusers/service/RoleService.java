package br.com.compassuol.pb.challenge.msusers.service;

import br.com.compassuol.pb.challenge.msusers.payload.RoleDto;

public interface RoleService {
	
	RoleDto createRole(RoleDto roleDto);
	
	RoleDto getRoleById(long id);
	
	RoleDto updateRole(RoleDto roleDto, long id);
}
