package com.example.hospital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    @NotNull(message = "El consultorio es obligatorio.")
    private Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "El doctor es obligatorio.")
    private Doctor doctor;

    @Column(name = "horario_consulta", nullable = false)
    @NotNull(message = "El horario de consulta es obligatorio.")
    private LocalDateTime horarioConsulta;

    @Column(name = "nombre_paciente", nullable = false)
    @NotBlank(message = "El nombre del paciente es obligatorio.")
    private String nombrePaciente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consultorio getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDateTime getHorarioConsulta() {
		return horarioConsulta;
	}

	public void setHorarioConsulta(LocalDateTime horarioConsulta) {
		this.horarioConsulta = horarioConsulta;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

    
}
