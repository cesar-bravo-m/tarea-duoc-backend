package com.example.hospitalscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalscheduler.model.Cita;
import com.example.hospitalscheduler.model.Paciente;
import com.example.hospitalscheduler.model.SegmentoHorario;
import com.example.hospitalscheduler.repository.CitaRepository;
import com.example.hospitalscheduler.repository.PacienteRepository;
import com.example.hospitalscheduler.repository.SegmentoHorarioRepository;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private SegmentoHorarioRepository segmentoHorarioRepository;

    @PostMapping("/assign")
    public ResponseEntity<?> assignSegmentoToPaciente(
            @RequestParam Long pacienteId,
            @RequestParam Long segmentoHorarioId) {
        
        // Find paciente
        Paciente paciente = pacienteRepository.findById(pacienteId)
            .orElse(null);
        if (paciente == null) {
            return ResponseEntity.badRequest()
                .body("Paciente not found with id: " + pacienteId);
        }

        // Find segmento horario
        SegmentoHorario segmentoHorario = segmentoHorarioRepository.findById(segmentoHorarioId)
            .orElse(null);
        if (segmentoHorario == null) {
            return ResponseEntity.badRequest()
                .body("SegmentoHorario not found with id: " + segmentoHorarioId);
        }

        // Check if segmento is free
        if (!segmentoHorario.isFree()) {
            return ResponseEntity.badRequest()
                .body("SegmentoHorario is not available");
        }

        // Create new cita
        Cita cita = new Cita(paciente, segmentoHorario);
        
        // Mark segmento as not free
        segmentoHorario.setFree(false);
        segmentoHorarioRepository.save(segmentoHorario);
        
        // Save cita
        Cita savedCita = citaRepository.save(cita);
        
        return ResponseEntity.ok(savedCita);
    }
} 