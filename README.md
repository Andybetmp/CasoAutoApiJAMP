# CasoAutoApiJAMP - Automatización de Pruebas API con Serenity BDD
# Autor: Jolaus Andy Meza Perez

Proyecto de automatización de pruebas sobre servicios REST desarrollado con **Java 17**, **Maven**, **Cucumber** y **Serenity BDD**, implementando el patrón **Screenplay** sobre la API pública [Fake Store API](https://fakestoreapi.com/).

## Entregables y Evidencias
- **Video demostrativo (ejecución, código y reporte Serenity):** [Ver video en Google Drive](https://drive.google.com/file/d/1_e27BOCp1ITjbJi_Xkj3gwfAJYcAxGbK/view?usp=sharing)
- **Reporte de ejecución en PDF:** Descargado desde el reporte HTML de Serenity y cargado directamente en la carpeta [`docs/`](./docs/) del repositorio.
- **Reporte HTML dinámico:** Se genera localmente tras cada ejecución en `target/site/serenity/index.html`.

## Requisitos previos
- **Java JDK 17**
- **Apache Maven 3.8+**
- Conexión a internet para consumir `https://fakestoreapi.com`

## Estructura del proyecto
```text
CasoAutoApiJAMP/
├── docs/                                  # Reporte de resultados de Serenity exportado en PDF
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/java/org/example/
    │   ├── Questions/
    │   │   ├── ResponseCode.java          # Valida el código de estado HTTP
    │   │   ├── ResponseField.java         # Valida el valor de un campo específico del JSON
    │   │   └── ResponseListNotEmpty.java  # Verifica que la lista devuelta contenga elementos
    │   └── Tasks/
    │       ├── GetResource.java           # Tarea reutilizable para peticiones HTTP GET
    │       └── PostResource.java          # Tarea reutilizable para peticiones HTTP POST
    └── test/
        ├── java/org/example/
        │   ├── RunnerTest.java            # Ejecutor principal de Cucumber con Serenity
        │   └── StepDefinition/
        │       ├── FakeStoreStepDefinition.java # Definición de pasos Gherkin y manejo de Stage
        │       └── ParameterDefinition.java     # Conversión dinámica del parámetro {actor}
        └── resources/features/
            └── FakeApi.feature            # Escenarios BDD en lenguaje Gherkin
