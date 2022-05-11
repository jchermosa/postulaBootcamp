CREATE TABLE Postulante (
  id serial PRIMARY KEY,
  nombre varchar,
  apellido varchar(50),
  nro_cedula int,
  correo varchar(50),
  telefono varchar(50),
  direccion varchar(50),
  experiencia_laboral BOOLEAN NOT NULL DEFAULT FALSE,
  estudio_universitario BOOLEAN NOT NULL DEFAULT FALSE,
  notebook BOOLEAN NOT NULL DEFAULT FALSE,
  bootcamp_id int,
  aceptado BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Postulante_Lenguaje (
  id serial PRIMARY KEY,
  id_postulante int,
  id_lenguaje int
);

CREATE TABLE Lenguaje (
  id serial PRIMARY KEY,
  nombre_lenguaje varchar(50)
);

CREATE TABLE Bootcamp (
  id serial PRIMARY KEY,
  id_lenguaje int,
  id_profesor int,
  fecha_inicio date,
  fecha_fin date,
  descripcion varchar(200),
  imagen varchar(50),
  titulo varchar(50),
  activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Profesor (
  id serial PRIMARY KEY,
  nombre varchar(50),
  apellido varchar(50),
  nro_cedula int,
  correo varchar(50)
);

CREATE TABLE Usuario (
  id serial PRIMARY KEY,
  nombre varchar(50),
  apellido varchar(50),
  correo varchar(50),
  contrasena varchar(50)
);

ALTER TABLE Bootcamp ADD FOREIGN KEY (id_lenguaje) REFERENCES Lenguaje(id);

ALTER TABLE Postulante_Lenguaje ADD FOREIGN KEY (id_postulante) REFERENCES Postulante (id);

ALTER TABLE Postulante_Lenguaje ADD FOREIGN KEY (id_lenguaje) REFERENCES Lenguaje (id);

ALTER TABLE Bootcamp ADD FOREIGN KEY (id_profesor) REFERENCES Profesor (id);

ALTER TABLE Postulante ADD FOREIGN KEY (bootcamp_id) REFERENCES Bootcamp(id);