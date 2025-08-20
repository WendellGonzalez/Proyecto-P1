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
 



-	La interfaz de registro
 

-	Este es el inicio de sesión. Aquí accedo con la cuenta del admin
 

-	El 1er panel de administrador
 
 
 
 




-	Gestion de usuarios en general (Todos los usuarios juntos) y también por separado, pacientes y medicos.
 
 
 


 



-	Menú con Jmenu items para cerrar sesion, volver al modulo anterior y salir del sistema.
  
-	Aquí es un apartado para editar datos personales.
 







-	Panel de los pacientes
 
-	El apartado de informaciones
 
-	El apartado para realizar citas
 
-	El apartado para ver todas las citas que he tenido, con qué doctor, por cual motivo y en qué fecha. También si está en espera, realizada o rechazada. Puede funcionar como un apartado de notificaciones.
 

-	Apartado para ver todas las recetas que me han puesto los doctores en las consultas
 

-	Panel de medicos 
 
-	Aquí aparecen todos los pacientes que han sido pacientes especificamente de este doctor en algun momento.
 
 
 
-	Este apartado aparece cuando se le da al boton rojo que permite ver el historial del paciente actual.
<img width="468" height="470" alt="image" src="https://github.com/user-attachments/assets/769dc6e3-b8a1-4c40-a923-599de3bc4fee" />

