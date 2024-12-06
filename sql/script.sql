CREATE DATABASE hospital;

CREATE USER 'hospital'@'%' IDENTIFIED BY '111111';
GRANT ALL PRIVILEGES ON hospital.* TO 'hospital'@'%';
FLUSH PRIVILEGES;

use hospital;

CREATE TABLE doctores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    especialidad VARCHAR(50) NOT NULL
);

CREATE TABLE consultorios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_consultorio INT NOT NULL,
    piso INT NOT NULL
);

CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consultorio_id INT NOT NULL,
    doctor_id INT NOT NULL,
    horario_consulta DATETIME NOT NULL,
    nombre_paciente VARCHAR(50) NOT NULL,
    FOREIGN KEY (consultorio_id) REFERENCES consultorios(id),
    FOREIGN KEY (doctor_id) REFERENCES doctores(id)
);

INSERT INTO doctores (nombre, apellido_paterno, apellido_materno, especialidad) VALUES
('Carlos', 'García', 'López', 'Medicina General'),
('Alberto', 'Pérez', 'Martínez', 'Medicina General'),
('Cristian', 'Fernández', 'Sánchez', 'Medicina General');

INSERT INTO consultorios (numero_consultorio, piso) VALUES
(101, 1),
(102, 1),
(103, 1);

select * from doctores;
select * from consultorios;

DELETE from citas