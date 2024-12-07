INSERT INTO especialidad (nombre) VALUES ('Cardiologia');
INSERT INTO especialidad (nombre) VALUES ('Dermatologia');
INSERT INTO especialidad (nombre) VALUES ('Pediatria');
INSERT INTO especialidad (nombre) VALUES ('Neurologia');
INSERT INTO especialidad (nombre) VALUES ('Oftalmologia');
INSERT INTO especialidad (nombre) VALUES ('Traumatologia');
INSERT INTO especialidad (nombre) VALUES ('Psiquiatria');
INSERT INTO especialidad (nombre) VALUES ('Ginecologia');

-- Seed data for Funcionarios
INSERT INTO funcionario (rut, nombres, apellidos, telefono, email, password, especialidad_id) 
VALUES ('123456789', 'Juan', 'Perez', '+56912345678', 'juan.perez@hospital.cl', 'password123', 1);

INSERT INTO funcionario (rut, nombres, apellidos, telefono, email, password, especialidad_id) 
VALUES ('987654321', 'Maria', 'Gonzalez', '+56987654321', 'maria.gonzalez@hospital.cl', 'password456', 2);

INSERT INTO funcionario (rut, nombres, apellidos, telefono, email, password, especialidad_id) 
VALUES ('111111111', 'Pedro', 'Rodriguez', '+56911111111', 'pedro.rodriguez@hospital.cl', 'password789', 3);

INSERT INTO funcionario (rut, nombres, apellidos, telefono, email, password, especialidad_id) 
VALUES ('222222222', 'Ana', 'Martinez', '+56922222222', 'ana.martinez@hospital.cl', 'passwordabc', 4);

INSERT INTO funcionario (rut, nombres, apellidos, telefono, email, password, especialidad_id) 
VALUES ('333333333', 'Carlos', 'Sanchez', '+56933333333', 'carlos.sanchez@hospital.cl', 'passworddef', 5); 

INSERT INTO paciente (rut, nombres, apellidos, telefono, email, fecha_nacimiento, genero, direccion) 
VALUES ('444444444', 'Laura', 'Silva', '+56944444444', 'laura.silva@email.com', '1990-05-15', 'F', 'Av. Principal 123');

INSERT INTO paciente (rut, nombres, apellidos, telefono, email, fecha_nacimiento, genero, direccion) 
VALUES ('555555555', 'Diego', 'Torres', '+56955555555', 'diego.torres@email.com', '1985-08-22', 'M', 'Calle Central 456');

INSERT INTO paciente (rut, nombres, apellidos, telefono, email, fecha_nacimiento, genero, direccion) 
VALUES ('666666666', 'Carmen', 'Lopez', '+56966666666', 'carmen.lopez@email.com', '1995-03-10', 'F', 'Pasaje Los Pinos 789');

INSERT INTO paciente (rut, nombres, apellidos, telefono, email, fecha_nacimiento, genero, direccion) 
VALUES ('777777777', 'Roberto', 'Muñoz', '+56977777777', 'roberto.munoz@email.com', '1982-11-30', 'M', 'Av. Los Robles 321'); 

-- Seed data for SegmentoHorario
INSERT INTO segmento_horario (nombre, fecha_hora_inicio, fecha_hora_fin, free, funcionario_id) 
VALUES ('Consulta 1', '2024-03-20 09:00:00', '2024-03-20 09:30:00', true, 1);

INSERT INTO segmento_horario (nombre, fecha_hora_inicio, fecha_hora_fin, free, funcionario_id) 
VALUES ('Consulta 2', '2024-03-20 09:30:00', '2024-03-20 10:00:00', true, 1);

INSERT INTO segmento_horario (nombre, fecha_hora_inicio, fecha_hora_fin, free, funcionario_id) 
VALUES ('Consulta Tarde', '2024-03-20 14:00:00', '2024-03-20 14:30:00', true, 2);

INSERT INTO segmento_horario (nombre, fecha_hora_inicio, fecha_hora_fin, free, funcionario_id) 
VALUES ('Consulta Tarde', '2024-03-20 14:30:00', '2024-03-20 15:00:00', true, 2); 

-- Seed data for Rol
INSERT INTO rol (nombre) VALUES ('USA_CITAS');
INSERT INTO rol (nombre) VALUES ('USA_AGENDA');
INSERT INTO rol (nombre) VALUES ('USA_INSCRIPCION');

-- Assign roles to funcionarios
INSERT INTO funcionario_rol (funcionario_id, rol_id) VALUES (1, 1);
INSERT INTO funcionario_rol (funcionario_id, rol_id) VALUES (1, 2);
INSERT INTO funcionario_rol (funcionario_id, rol_id) VALUES (2, 2);
INSERT INTO funcionario_rol (funcionario_id, rol_id) VALUES (3, 3);
INSERT INTO funcionario_rol (funcionario_id, rol_id) VALUES (4, 1);
INSERT INTO funcionario_rol (funcionario_id, rol_id) VALUES (5, 2);

-- Seed data for Cita
INSERT INTO cita (paciente_id, segmento_horario_id) VALUES (1, 1);
INSERT INTO cita (paciente_id, segmento_horario_id) VALUES (2, 3);