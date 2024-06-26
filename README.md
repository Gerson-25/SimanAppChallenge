﻿# SimanAppChallenge
## Descripcion del proyecto
#### Esta es una aplicacion desarrollada en Kotlin, utilizando las arquitecturas y librerias mas recientes para su elaboracion, explico un poco sobre la estructura y librearias usadas a continuacion:

## ARQUITECTURA DEL PROYECTO

#### La arquitectura empleada para este proyecto es ARQUITECTURA LIMPIA basada en las capas DATOS, DOMINIO y UI.

#### Capa de datos: En la capa de datos estan todas las especificaciones e implementaciones de las reglas de negocio sobre los servcios que se estan consumiendo y la estructura para el almacenamiento local

#### Capa de dominio: En la capa de dominio estan todas las definiciones y abstracciones de las reglas de negocios, las cuales seran usadas en la vista

#### Capa de UI: Aqui se encuentran todas las definiciones de la vista: Modelos de UI, funciones componibles para la creacion de las vistas, viewmodels, etc, esto con el fin de crear una separacion de las responsabilidades de cada capa


## PATRON DE DISEÑO EN LA VISTA

#### Se ha usado el patron vista-modelo-viewmodel, el cual es el recomendado por la documentacion de Google.

#### Vista: Aqui estan todos los componentes que usaran para componer la vista, debido a que se esta usando Jetpack Compose, esta carpeta contiene funciones componibles para la creacion de la misma

#### Modelos: Los modelos de datos son clases que especifican y abstraen solamente la informacion que será necesaria para construir la vista

#### Viewmodel: Estos son los encargados de conectar la capa de datos con la vista, realizando las consultas a los servicios y administrando los tipos de respuesta mediante Flows.

## Librerias usadas

#### Aqui hay un listado de las principales librearias usadas:

#### Room: Almacenamiento local
#### Hilt: Injeccion de dependencias
#### Paging: Administra la carga de datos y la paginacion
#### FIrebase: Uso de la libreria de Auth para el inicio de sesion y el registro
#### Coil: Manejo y dibujo de Imagenes por URLs


## CAPTURAS DE PANTALLA
![Captura de pantalla 2024-03-23 215428](https://github.com/Gerson-25/SimanAppChallenge/assets/45273906/d12e45b0-e6a2-4aa1-8e43-bbdc8f23e917)
![Captura de pantalla 2024-03-23 215446](https://github.com/Gerson-25/SimanAppChallenge/assets/45273906/c4adef57-057d-423f-bcae-4a1f3fdaf947)
![Captura de pantalla 2024-03-23 215513](https://github.com/Gerson-25/SimanAppChallenge/assets/45273906/8c2771d1-8af8-413b-a2a4-ff3384eb212f)
![Captura de pantalla 2024-03-23 215536](https://github.com/Gerson-25/SimanAppChallenge/assets/45273906/af70fe59-6f68-451d-aa30-3f179de06cc5)
![Captura de pantalla 2024-03-23 215306](https://github.com/Gerson-25/SimanAppChallenge/assets/45273906/c636e902-2192-4480-ae84-c5fc8a992beb)
![Captura de pantalla 2024-03-23 21533412](https://github.com/Gerson-25/SimanAppChallenge/assets/45273906/28149970-7261-42bb-9440-7a076fb8a77c)













