package com.security.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.dto.AuthRequest;
import com.security.dto.DemoRequest;
import com.security.dto.DemoResponse;
import com.security.entity.DemoEntity;
import com.security.repository.DemoRepository;

@Service
public class DemoService {

	@Autowired
	private DemoRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authManager;

	public ResponseEntity<?> addUser(DemoRequest req) {

		if (repo.existsByName(req.getName())) {
			HashMap<String, String> map = new HashMap<>();
			map.put("response", "user already exists in db.");
			return new ResponseEntity<>(map, HttpStatus.CONFLICT);
		}

		else {
			HashMap<String, Object> map1 = new HashMap<>();
			DemoEntity entity = new DemoEntity();
			entity.setName(req.getName());
			entity.setPassword(passwordEncoder.encode(req.getPassword()));
			entity.setEmail(req.getEmail());
			entity.setRoles(req.getRoles());

			DemoEntity $entity = repo.save(entity);

			DemoResponse response = new DemoResponse($entity.getId(), $entity.getName(), $entity.getPassword(),
					$entity.getEmail(), $entity.getRoles());

			map1.put("response", response);

			return new ResponseEntity<>(map1, HttpStatus.ACCEPTED);
		}
	}

	public ResponseEntity<?> getAllUsers() {
		HashMap<String, List<?>> map = new HashMap<>();
		List<DemoEntity> entity = repo.findAll();
		List<DemoResponse> response = entity.stream()
				.map(x -> new DemoResponse(x.getId(), x.getName(), x.getPassword(), x.getEmail(), x.getRoles()))
				.toList();
		map.put("response", response);
		return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> authUser(AuthRequest request) {

		HashMap<String, String> map = new HashMap<>();

		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (authentication.isAuthenticated()) {

			String token = jwtService.generateTokenFromUsername(request.getUsername());

			map.put("response", token);

			return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

		}

		else {

			HashMap<String, String> map1 = new HashMap<>();

			map1.put("response", "invalid request");

			return new ResponseEntity<>(map1, HttpStatus.BAD_REQUEST);

		}

	}
}
