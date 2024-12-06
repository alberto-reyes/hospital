package com.example.hospital.controller;

import com.example.hospital.model.Cita;
import com.example.hospital.service.CitaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping("/alta")
    public Cita altaCita(@Valid @RequestBody Cita cita) {
        return citaService.altaCita(cita);
    }

    @GetMapping("/consulta")
    public List<Cita> consultaCita(@RequestParam(required = false) LocalDateTime fecha,
                                   @RequestParam(required = false) Integer consultorioId,
                                   @RequestParam(required = false) Integer doctorId) {
        return citaService.consultaCita(fecha, consultorioId, doctorId);
    }

    @DeleteMapping("/cancelar/{id}")
    public void cancelarCita(@PathVariable Integer id) {
        citaService.cancelarCita(id);
    }

    @PutMapping("/editar")
    public Cita editarCita(@Valid @RequestBody Cita cita) {
        return citaService.editarCita(cita);
    }
}
