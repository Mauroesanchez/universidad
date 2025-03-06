# universidad
Proyecto en Java, gestión universitaria


Aclaraciones, el sistema corre con un DataSet precargado de la carrera de sistemas hasta 3er año, materias con sus correlativas como indica la pagina de UNTDF
por razones de testeo el alumno (99887766, "Pedro", "Ramírez") se precarga con todas las materias finalizadas para poder verificar si esta graduado.
a continuacion adjunto el DataSet inicial

// Crear carrera con **0 optativas necesarias**
        Carrera sistemas = new Carrera("Ingeniería en Sistemas", 0, new PlanA("Plan A"));

        // Crear materias obligatorias
        Materia info = new Materia("Elementos de Informática", true, false, 1);
        Materia prog = new Materia("Expresión de Problemas y Algoritmos", true, false, 1);
        Materia algebra = new Materia("Álgebra", true, false, 1);
        Materia algo1 = new Materia("Algorítmica y Programación I", true, false, 2);
        Materia logica = new Materia("Elementos de Lógica y Matemática Discreta", true, false, 2);
        Materia analisis = new Materia("Análisis Matemático", true, false, 2);
        Materia sistOrg = new Materia("Sistemas y Organizaciones", true, false, 3);
        Materia arqComp = new Materia("Arquitectura de Computadoras", true, false, 3);
        Materia algo2 = new Materia("Algorítmica y Programación II", true, false, 3);
        Materia estadistica = new Materia("Estadística", true, false, 3);
        Materia basesDatos = new Materia("Bases de Datos I", true, false, 4);
        Materia poo = new Materia("Programación y Diseño Orientado a Objetos", true, false, 4);
        Materia ingSoftware1 = new Materia("Ingeniería de Software I", true, false, 4);
        Materia labProg = new Materia("Laboratorio de Programación y Lenguajes", true, false, 5);
        Materia ingSoftware2 = new Materia("Ingeniería de Software II", true, false, 5);
        Materia concurrencia = new Materia("Introducción a la Concurrencia", true, false, 5);
        Materia sistOp = new Materia("Sistemas Operativos", true, false, 6);
        Materia labSoft = new Materia("Laboratorio de Software", true, false, 6);

        // Agregar correlativas
        algo1.setCorrelativas(List.of(prog));
        algo2.setCorrelativas(List.of(algo1, logica));
        arqComp.setCorrelativas(List.of(info));
        estadistica.setCorrelativas(List.of(algebra, analisis));
        basesDatos.setCorrelativas(List.of(algo2));
        poo.setCorrelativas(List.of(algo1));
        ingSoftware1.setCorrelativas(List.of(algo1));
        labProg.setCorrelativas(List.of(algo2));
        ingSoftware2.setCorrelativas(List.of(ingSoftware1, estadistica));
        concurrencia.setCorrelativas(List.of(arqComp, algo2));
        sistOp.setCorrelativas(List.of(concurrencia));
        labSoft.setCorrelativas(List.of(basesDatos, poo, ingSoftware1));

        // Agregar materias a la carrera
        sistemas.agregarMateriaObligatoria(info);
        sistemas.agregarMateriaObligatoria(prog);
        sistemas.agregarMateriaObligatoria(algebra);
        sistemas.agregarMateriaObligatoria(algo1);
        sistemas.agregarMateriaObligatoria(logica);
        sistemas.agregarMateriaObligatoria(analisis);
        sistemas.agregarMateriaObligatoria(sistOrg);
        sistemas.agregarMateriaObligatoria(arqComp);
        sistemas.agregarMateriaObligatoria(algo2);
        sistemas.agregarMateriaObligatoria(estadistica);
        sistemas.agregarMateriaObligatoria(basesDatos);
        sistemas.agregarMateriaObligatoria(poo);
        sistemas.agregarMateriaObligatoria(ingSoftware1);
        sistemas.agregarMateriaObligatoria(labProg);
        sistemas.agregarMateriaObligatoria(ingSoftware2);
        sistemas.agregarMateriaObligatoria(concurrencia);
        sistemas.agregarMateriaObligatoria(sistOp);
        sistemas.agregarMateriaObligatoria(labSoft);

        // Agregar carrera a la facultad
        facultad.agregarCarrera(sistemas);

        // Crear alumnos
        Alumno alumno1 = new Alumno(12345678, "Juan", "Pérez");
        Alumno alumno2 = new Alumno(87654321, "María", "Gómez");
        Alumno alumno3 = new Alumno(11223344, "Carlos", "López");
        Alumno alumno4 = new Alumno(55667788, "Ana", "Fernández");
        Alumno alumno5 = new Alumno(99887766, "Pedro", "Ramírez"); // Alumno que se debe graduar

        // Agregar alumnos a la facultad
        facultad.agregarEstudiante(alumno1);
        facultad.agregarEstudiante(alumno2);
        facultad.agregarEstudiante(alumno3);
        facultad.agregarEstudiante(alumno4);
        facultad.agregarEstudiante(alumno5);

        // Inscribir alumnos en la carrera
        facultad.inscribirAlumnoEnCarrera(12345678, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(87654321, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(11223344, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(55667788, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(99887766, "Ingeniería en Sistemas"); // Pedro
