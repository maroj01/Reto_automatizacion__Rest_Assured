# language: es

Característica: consultar la informacion personal del usuario
  Yo como asesor
  Quiero consultar un asuario
  Para conocer sus datos personales


  Escenario: validar el formato del correo
    Dado que estoy en la seccion de busqueda de usuarios
    Cuando busco el usuario con ID "2"
    Y obtengo el correo del usuario
    Entonces el formato del correo es el correcto
