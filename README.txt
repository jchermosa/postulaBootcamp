CREATE TABLE "Postulante" (
  "id" int PRIMARY KEY,
  "nombre" varchar,
  "apellido" varchar,
  "nro_cedula" int,
  "correo" varchar,
  "telefono" varchar,
  "direccion" varchar,
  "experiencia_laboral" boolean DEFAULT false,
  "estudio_universitario" boolean DEFAULT false,
  "notebook" boolean DEFAULT false,
  "bootcamp_id" int,
  "aceptado" boolean DEFAULT false
);

CREATE TABLE "Postulante_Lenguaje" (
  "id" int PRIMARY KEY,
  "id_postulante" int,
  "id_lenguaje" int
);

CREATE TABLE "Lenguaje" (
  "id" int PRIMARY KEY,
  "nombre_lenguaje" varchar
);

CREATE TABLE "Bootcamp" (
  "id" int PRIMARY KEY,
  "id_lenguaje" int,
  "id_profesor" int,
  "fecha_inicio" date,
  "fecha_fin" date,
  "descripcion" varchar,
  "imagen" varchar,
  "titulo" varchar,
  "activo" boolean DEFAULT true
);

CREATE TABLE "Profesor" (
  "id" int PRIMARY KEY,
  "nombre" varchar,
  "apellido" varchar,
  "nro_cedula" int,
  "correo" varchar
);

CREATE TABLE "Usuario" (
  "id" int PRIMARY KEY,
  "nombre" varchar,
  "apellido" varchar,
  "correo" varchar,
  "password" varchar
);

ALTER TABLE "Bootcamp" ADD FOREIGN KEY ("id_lenguaje") REFERENCES "Lenguaje" ("id");

ALTER TABLE "Postulante_Lenguaje" ADD FOREIGN KEY ("id_postulante") REFERENCES "Postulante" ("id");

ALTER TABLE "Postulante_Lenguaje" ADD FOREIGN KEY ("id_lenguaje") REFERENCES "Lenguaje" ("id");

ALTER TABLE "Bootcamp" ADD FOREIGN KEY ("id_profesor") REFERENCES "Profesor" ("id");

ALTER TABLE "Postulante" ADD FOREIGN KEY ("bootcamp_id") REFERENCES "Bootcamp" ("id");