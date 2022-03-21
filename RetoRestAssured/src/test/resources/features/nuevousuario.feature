# language: es

Característica: Creacion de usuario
  Yo como asesor
  Quiero crear un nuevo usuario
  Para ofrecerle servicios


  Escenario: Crear nuevo usuario
    Dado que me encuentro en la seccion de creacion de usuarios
    Cuando ingreso los datos del usuario: nombre "Jairo Roldan" trabajo "Analista Comercial"
    Entonces se creara el nuevo usuario con su respectivo ID.

