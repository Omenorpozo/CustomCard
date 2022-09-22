<h1 align="center">Custom Card</h1>

## Descripción
Se ha desarrollado una vista customizada "CustomCard", la cual tiene casi todos sus atributos modificables.
Se ha simulado una llamada con un delay de 2s para mostrar un loading, transcurridos los 2s el loading se quita y se muestra el listado.
El listado que se muestra es mockeado, ya que no se realizan llamadas a servicios.
La app incorpora eventos para saber si debe cargar los datos o ha fallado, como no se hace ninguna llamada al servidor siempre se muestra el listado,
se ha dejado la estructura como si fuera un caso real.
Se ha incorporado un estado de la vista a través de StateFlow.



## Modulos
* **app** - Módulo de la aplicación con acceso a todos los módulos.
* **ui-components** - Módulo que incorpora todas las custom views.
* **string-manager** - Módulo que se centra de la carga de string customizados.
