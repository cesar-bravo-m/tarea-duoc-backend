package com.example.hospitalscheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalscheduler.dto.LoginRequest;
import com.example.hospitalscheduler.dto.LoginResponse;
import com.example.hospitalscheduler.model.Funcionario;
import com.example.hospitalscheduler.repository.EspecialidadRepository;
import com.example.hospitalscheduler.repository.FuncionarioRepository;
import com.example.hospitalscheduler.repository.RolRepository;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private RolRepository rolRepository;

    // Create
    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
        funcionario.setId(null);  // Ensure we're creating a new entity
        
        // Handle especialidad if provided
        if (funcionario.getEspecialidad() != null && funcionario.getEspecialidad().getId() != null) {
            return especialidadRepository.findById(funcionario.getEspecialidad().getId())
                .map(especialidad -> {
                    funcionario.setEspecialidad(especialidad);
                    Funcionario savedFuncionario = funcionarioRepository.save(funcionario);
                    return ResponseEntity.ok(savedFuncionario);
                })
                .orElse(ResponseEntity.badRequest().build());
        }
        
        Funcionario savedFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(savedFuncionario);
    }

    // Read all
    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        return funcionarioRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetails) {
        return funcionarioRepository.findById(id)
            .map(funcionario -> {
                funcionario.setRut(funcionarioDetails.getRut());
                funcionario.setNombres(funcionarioDetails.getNombres());
                funcionario.setApellidos(funcionarioDetails.getApellidos());
                funcionario.setTelefono(funcionarioDetails.getTelefono());
                funcionario.setEmail(funcionarioDetails.getEmail());
                funcionario.setPassword(funcionarioDetails.getPassword());
                
                // Update especialidad if provided
                if (funcionarioDetails.getEspecialidad() != null && funcionarioDetails.getEspecialidad().getId() != null) {
                    return especialidadRepository.findById(funcionarioDetails.getEspecialidad().getId())
                        .map(especialidad -> {
                            funcionario.setEspecialidad(especialidad);
                            return ResponseEntity.ok(funcionarioRepository.save(funcionario));
                        })
                        .orElse(ResponseEntity.badRequest().build());
                }
                
                return ResponseEntity.ok(funcionarioRepository.save(funcionario));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable Long id) {
        return funcionarioRepository.findById(id)
            .map(funcionario -> {
                funcionarioRepository.delete(funcionario);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Add this method to the existing controller class
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Funcionario> getFuncionarioByRut(@PathVariable String rut) {
        Funcionario funcionario = funcionarioRepository.findByRut(rut);
        if (funcionario == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(funcionario);
    }

    // Add this method to the existing controller class
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Funcionario funcionario = funcionarioRepository.findByRut(loginRequest.getRut());
        
        if (funcionario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid credentials");
        }
        
        if (!funcionario.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid credentials");
        }
        
        LoginResponse response = new LoginResponse(
            funcionario.getId(),
            funcionario.getRut(),
            funcionario.getNombres(),
            funcionario.getApellidos(),
            funcionario.getRoles()
        );
        
        return ResponseEntity.ok(response);
    }
} 