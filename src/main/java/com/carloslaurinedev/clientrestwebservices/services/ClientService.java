package com.carloslaurinedev.clientrestwebservices.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloslaurinedev.clientrestwebservices.dto.ClientDTO;
import com.carloslaurinedev.clientrestwebservices.entities.Client;
import com.carloslaurinedev.clientrestwebservices.repositories.ClientRepository;
import com.carloslaurinedev.clientrestwebservices.services.exceptions.DBException;
import com.carloslaurinedev.clientrestwebservices.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<ClientDTO> findAll() {

		List<Client> entityList = repository.findAll();

		List<ClientDTO> dtoList = entityList.stream().map(entity -> new ClientDTO(entity)).collect(Collectors.toList());

		/*
		 * List<CategoryDTO> dtoList = new ArrayList<>(); for(Category entity:
		 * entityList) { dtoList.add(new CategoryDTO(entity)); }
		 */

		return dtoList;

	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageable) {

		Page<Client> entityPage = repository.findAll(pageable);

		Page<ClientDTO> dtoPage = entityPage.map(entity -> new ClientDTO(entity));

		return dtoPage;

	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {

		Optional<Client> obj = repository.findById(id);

		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));

		ClientDTO dto = new ClientDTO(entity);

		return dto;

	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {

		Client entity = new Client();

		setEntityAttributesWithDTO(entity, dto);

		dto = new ClientDTO(repository.save(entity));

		return dto;
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {

		try {

			Client entity = repository.getOne(id);

			setEntityAttributesWithDTO(entity, dto);

			entity = repository.save(entity);
			dto = new ClientDTO(entity);
			return dto;

		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Inexistent Id => " + id);

		}

	}

	public void delete(Long id) {

		try {

			repository.deleteById(id);

		}

		catch (EmptyResultDataAccessException e1) {

			throw new ResourceNotFoundException("Inexistent Id for Deletion => " + id);

		}

		catch (DataIntegrityViolationException e2) {

			throw new DBException("Data Integrity Violation");

		}
	}

	private void setEntityAttributesWithDTO(Client entity, ClientDTO dto) {

		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());

	}

}
