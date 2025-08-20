<p align="center">
  <img src="https://github.com/user-attachments/assets/f3be0599-9651-45cd-81d1-b958b81eb63f" 
       alt="logoClinic" 
       width="200" 
       height="200" />
</p>
<br>
<p align="center">
<b>SISTEMA DE CITAS MEDICAS ( ClinicaApp )</b>
</p>
<br>
<p align="center">
<b>INTRODUCCION</b>
</p>

El presente documento describe en detalle el diseño, funcionamiento y organización del Sistema de Citas Médicas en Línea, una plataforma digital que permite gestionar de manera eficiente la atención sanitaria entre pacientes, médicos y administradores.
Este sistema centraliza la información de los usuarios, agiliza la programación de citas y facilita la comunicación directa entre las partes involucradas.
A través de sus diferentes módulos —paciente, médico y administrador— se busca ofrecer una experiencia de uso fluida, segura y organizada, optimizando el tiempo y los recursos de todos los participantes.

<p align="center">
<b>OBJETIVOS DEL SISTEMA</b>
</p>

Objetivo General:
Brindar una plataforma digital que permita el registro, gestión y control de citas médicas, optimizando la interacción entre pacientes, médicos y administradores.

Objetivos Específicos:
- Permitir el registro y autenticación de pacientes y médicos con validaciones seguras.
- Facilitar la programación, modificación y cancelación de citas.
- Garantizar el almacenamiento seguro de la información personal y médica.
- Proporcionar al médico un perfil completo del paciente antes de la consulta.
- Otorgar a los administradores control total sobre la plataforma para su correcto funcionamiento.
- Centralizar toda la información de citas y participantes en un sistema único y accesible.
  
<p align="center">
<b>FASES Y FUNCIONALIDADES DEL SISTEMA</b>
  </p>
  
<b>Fase 1:</b> Registro de Pacientes
Propósito: Permitir que los pacientes creen una cuenta para acceder a las funciones del sistema.
Datos solicitados: Nombre completo, Edad y fecha de nacimiento, Género, Dirección y teléfono, Correo electrónico, Contraseña, Información médica básica.
Características: Verificación de campos obligatorios, prevención de registros duplicados, confirmación por correo electrónico.

<b>Fase 2:</b> Registro de Médicos
Propósito: Crear perfiles médicos verificados para que los profesionales puedan atender pacientes.
Datos solicitados: Nombre completo, Especialidad médica, Número de Colegiatura, Experiencia y formación académica, Datos de contacto.
Validaciones: Verificación de credenciales médicas, activación tras aprobación.

<b>Fase 3:</b> Inicio de Sesión
Proceso: Pacientes, médicos y Administrador ingresan correo y contraseña, validación y acceso a su panel correspondiente.
Seguridad: Contraseñas cifradas, sistema antiacceso no autorizado.
<b>Fase 4:</b> Panel del Paciente
Funciones: Actualizar información personal, solicitar citas, seleccionar especialidad y médico, describir síntomas, revisar historial.

<b>Fase 5:</b> Panel del Médico
Funciones: Acceder a lista de citas, consultar información de pacientes, confirmar.
<b>Fase 6:</b> Gestión de especialidades
Funciones: Luego de tu poner la especialidad en la que te quieres consultar, seleccionas el medico con el que deseo consultarte, luego accedo a agendar mi cita. ( El admi también crea, actualiza, edita y tiene el manejo total de las selecciones de las especialidades).
<b>Fase 7:</b> Solicitud y Gestión de Citas
Proceso paciente: Seleccionar especialidad, médico disponible, indicar síntomas, escoger fecha/hora, confirmar cita.
Proceso médico: Revisar solicitudes, confirmar o modificar, agregar notas previas.
<b>Fase 8:</b> Módulo del Administrador
Rol: Supervisar funcionamiento del sistema, validar registro de médicos, gestionar datos. 
Permisos: Acceso a todos los perfiles, modificación de configuraciones.

<p align="center">
<b>ORGANIZACION DEL SISTEMA</b>
</p>

El sistema se estructura en tres módulos principales:

<b>1. Módulo de Pacientes:</b>

- Registro e inicio de sesión.
- (Puede editar datos personales)
- Solicitud de citas. 
- Historial médico
- Ver solicitudes de citas
- Ver recetas
  
<b>2. Módulo de Médicos:</b>

- Registro con validación.
- Gestión pacientes.
- Gestion de citas
- Consultas
- (Puede editar datos personales)
- Ve historial medico del paciente al que está atendiendo.
  
<b>3. Modulo de especialidades:</b>

-Se añade la especialidad
-Selección de medico 
-Agenda de citas
-Horarios de citas con su fecha

<b>4. Módulo de Administrador:</b>

- Gestión y control total.
- Validación de médicos.
- Gestion de usuarios en general, o por separado.
  
<p align="center">
 <b>CONCLUSION</b>
</p>

Este sistema de citas médicas digital es una solución integral que optimiza el proceso de atención sanitaria desde el registro hasta la consulta.
La inclusión de un módulo administrativo robusto asegura el control y la seguridad de los datos, mientras que la validación de médicos garantiza la calidad de la atención.

Gracias a su diseño modular y funciones específicas para cada tipo de usuario, la plataforma mejora la experiencia del paciente, organiza el trabajo del médico y facilita la supervisión por parte de los administradores.
Este enfoque garantiza un flujo de trabajo eficiente, seguro y adaptable a diferentes especialidades y entornos clínicos.

<p align="center">
<b>RESUMEN DEL FLUJO</b>
</p>

1.	Registro de paciente/médico → Creación de cuenta.
2.	Aprobación por administrador (solo médicos).
3.	Inicio de sesión → Acceso al panel.
4.	Solicitud de cita → Selección de especialidad, médico, fecha, hora y síntomas.
5.	Confirmación → Notificación a paciente y médico.
6.	Administrador supervisa y mantiene orden de datos.


IMÁGENES DEL SISTEMA
-	Esta es la interfaz principal donde se elige si iniciar sesión o registrarse y también un pequeño “acerca de” de la empresa.
 

<img width="443" height="257" alt="image" src="https://github.com/user-attachments/assets/0633bd61-7827-4e80-a433-7ba656cc7fb8" />


-	La interfaz de registro

 <img width="355" height="328" alt="image" src="https://github.com/user-attachments/assets/36f06c24-d52f-4bf4-acc0-5a92a44269fd" />


-	Este es el inicio de sesión. Aquí accedo con la cuenta del admin

 <img width="470" height="269" alt="image" src="https://github.com/user-attachments/assets/a0b0bf23-1ee1-433c-8590-7dfa37f7fa61" />


-	El 1er panel de administrador

<img width="482" height="373" alt="image" src="https://github.com/user-attachments/assets/e8ba7238-9f08-4e59-9e04-04da5f35bd29" />


-	Gestion de usuarios en general (Todos los usuarios juntos) y también por separado, pacientes y medicos.
 
 <img width="540" height="291" alt="image" src="https://github.com/user-attachments/assets/23e94a58-7d95-46ca-96a6-a857eb819dd5" />

 <img width="532" height="289" alt="image" src="https://github.com/user-attachments/assets/849c6c1e-6320-490f-abe4-d190596f4024" />


- Gestion de solicitudes medicas

  <img width="540" height="306" alt="image" src="https://github.com/user-attachments/assets/f79a58d0-bd69-4b9c-92da-abaa19ca5d93" />

<img width="540" height="297" alt="image" src="https://github.com/user-attachments/assets/d82d70bd-0976-4368-86f8-05b3a5c58c62" />

<img width="496" height="270" alt="image" src="https://github.com/user-attachments/assets/b0d209e9-6305-43a0-90a7-632007394f46" />

<img width="540" height="294" alt="image" src="https://github.com/user-attachments/assets/6c1b3407-225d-4806-8430-f79164932f43" />


-	Menú con Jmenu items para cerrar sesion, volver al modulo anterior y salir del sistema.

  <img width="200" height="139" alt="image" src="https://github.com/user-attachments/assets/cb5fca2d-ff17-48ba-96b8-446e5bc0a141" />

  
-	Aquí es un apartado para editar datos personales.
 
<img width="316" height="315" alt="image" src="https://github.com/user-attachments/assets/f3f927df-58c9-4197-8ecc-32c278679b99" />


-	Panel de los pacientes

  <img width="378" height="319" alt="image" src="https://github.com/user-attachments/assets/aa59f167-2121-4bbb-87f1-c7a7026c7436" />

 
-	El apartado de informaciones

  <img width="362" height="321" alt="image" src="https://github.com/user-attachments/assets/871fb0b4-bfe8-4cc5-8c28-9483f6ca6cc5" />

 
-	El apartado para realizar citas

  <img width="479" height="269" alt="image" src="https://github.com/user-attachments/assets/9940228f-fed2-4bcf-a4a7-a16f3931f436" />

 
-	El apartado para ver todas las citas que he tenido, con qué doctor, por cual motivo y en qué fecha. También si está en espera, realizada o rechazada. Puede funcionar como un apartado de notificaciones.

  <img width="384" height="328" alt="image" src="https://github.com/user-attachments/assets/7dc19631-4ee0-4745-ba8b-ab7d6cab6ab6" />


-	Apartado para ver todas las recetas que me han puesto los doctores en las consultas
 
<img width="413" height="306" alt="image" src="https://github.com/user-attachments/assets/1075526b-42fd-4ecd-830c-dc671777b014" />


-	Panel de medicos

  <img width="475" height="310" alt="image" src="https://github.com/user-attachments/assets/69c73ecb-8522-4252-af48-40246ae83c58" />

 
-	Aquí aparecen todos los pacientes que han sido pacientes especificamente de este doctor en algun momento.
 
<img width="540" height="297" alt="image" src="https://github.com/user-attachments/assets/416a6cc4-b680-4bd6-a17e-de528a0e6749" />

- Aquí la gestion de citas (aceptar o rechazar cita)

  <img width="540" height="337" alt="image" src="https://github.com/user-attachments/assets/55873a6e-afbd-4ac8-b0f0-00ec2001f12b" />

- Aqui el modulo de consultas, donde se realizan las recetas y se atiende al paciente

  <img width="456" height="355" alt="image" src="https://github.com/user-attachments/assets/ef90a3c9-37b9-43a9-821c-31d54b1c74da" />

 
-	Este apartado aparece cuando se le da al boton rojo que permite ver el historial del paciente actual.
  
<img width="465" height="310" alt="image" src="https://github.com/user-attachments/assets/cd2033b6-81ab-4cc2-87d0-3579eb71ca2a" />



