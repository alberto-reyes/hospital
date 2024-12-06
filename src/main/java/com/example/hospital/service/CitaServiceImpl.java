package com.example.hospital.service;

import com.example.hospital.dao.CitaRepository;
import com.example.hospital.model.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Cita altaCita(Cita cita) {
        // Verificar disponibilidad del consultorio
        List<Cita> citasConsultorio = citaRepository.findByConsultorioIdAndHorarioConsulta(cita.getConsultorio().getId(), cita.getHorarioConsulta());
        if (!citasConsultorio.isEmpty()) {
            throw new IllegalArgumentException("El consultorio ya tiene una cita a esta hora.");
        }

        // Verificar disponibilidad del doctor
        List<Cita> citasDoctor = citaRepository.findByDoctorIdAndHorarioConsulta(cita.getDoctor().getId(), cita.getHorarioConsulta());
        if (!citasDoctor.isEmpty()) {
            throw new IllegalArgumentException("El doctor ya tiene una cita a esta hora.");
        }

        // Verificar disponibilidad del paciente
        LocalDateTime startTime = cita.getHorarioConsulta().minusHours(2);
        LocalDateTime endTime = cita.getHorarioConsulta().plusHours(2);
        List<Cita> citasPaciente = citaRepository.findByNombrePacienteAndHorarioConsultaBetween(cita.getNombrePaciente(), startTime, endTime);
        if (!citasPaciente.isEmpty()) {
            throw new IllegalArgumentException("El paciente ya tiene una cita en este rango de tiempo.");
        }

        // Verificar que el doctor no exceda el límite diario
        LocalDateTime startOfDay = cita.getHorarioConsulta().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        List<Cita> citasDelDia = citaRepository.findByDoctorIdAndHorarioConsultaBetween(cita.getDoctor().getId(), startOfDay, endOfDay);
        if (citasDelDia.size() >= 8) {
            throw new IllegalArgumentException("El doctor no puede tener más de 8 citas en el día.");
        }

        // Guardar la nueva cita
        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> consultaCita(LocalDateTime fecha, Integer consultorioId, Integer doctorId) {
        if (fecha != null) {
            LocalDateTime startOfDay = fecha.toLocalDate().atStartOfDay();
            LocalDateTime endOfDay = startOfDay.plusDays(1);

            if (consultorioId != null) {
                return citaRepository.findByConsultorioIdAndHorarioConsultaBetween(consultorioId, startOfDay, endOfDay);
            } else if (doctorId != null) {
                return citaRepository.findByDoctorIdAndHorarioConsultaBetween(doctorId, startOfDay, endOfDay);
            } else {
                return citaRepository.findByHorarioConsultaBetween(startOfDay, endOfDay);
            }
        } else if (consultorioId != null) {
            return citaRepository.findByConsultorioId(consultorioId);
        } else if (doctorId != null) {
            return citaRepository.findByDoctorId(doctorId);
        } else {
            throw new IllegalArgumentException("Debe proporcionar al menos un criterio de búsqueda.");
        }
    }

    @Override
    public void cancelarCita(Integer citaId) {
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));
        if (cita.getHorarioConsulta().isAfter(LocalDateTime.now())) {
            citaRepository.delete(cita);
        } else {
            throw new IllegalArgumentException("No se puede cancelar una cita que ya ha pasado.");
        }
    }

    @Override
    public Cita editarCita(Cita cita) {
        // Verificar las mismas reglas que en altaCita
        return altaCita(cita);
    }
}
