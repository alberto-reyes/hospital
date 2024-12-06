package com.example.hospital.dao;

import com.example.hospital.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByConsultorioIdAndHorarioConsulta(Integer consultorioId, LocalDateTime horarioConsulta);
    List<Cita> findByDoctorIdAndHorarioConsulta(Integer doctorId, LocalDateTime horarioConsulta);
    List<Cita> findByNombrePacienteAndHorarioConsultaBetween(String nombrePaciente, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Cita> findByDoctorIdAndHorarioConsultaBetween(Integer doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Cita> findByHorarioConsultaBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Cita> findByDoctorId(Integer doctorId);
    List<Cita> findByConsultorioId(Integer consultorioId);
    List<Cita> findByConsultorioIdAndHorarioConsultaBetween(Integer consultorioId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
