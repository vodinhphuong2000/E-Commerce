package com.r2s.javabackend09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.model.Identification;
import com.r2s.javabackend09.repository.IdentificationRepository;

@Service
public class IdentificationService {
	@Autowired
	private IdentificationRepository identificationRepository;

	public List<Identification> getAll() {
		return this.identificationRepository.findAll();
	}
}
