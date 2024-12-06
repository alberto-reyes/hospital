package com.example.hospital.service;

import com.example.hospital.model.Cita;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
	Cita altaCita(Cita cita);

	List<Cita> consultaCita(LocalDateTime fecha, Integer consultorioId, Integer doctorId);

	void cancelarCita(Integer citaId);

	Cita editarCita(Cita cita);
}
