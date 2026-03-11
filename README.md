# AUTO_FRONT_POM_FACTORY: Automatización SofkianOS

## 📋 Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Flujo E2E Automatizado](...)
3. [Motivación](#motivación)
4. [Tecnologías Empleadas](#tecnologías-empleadas)
5. [Arquitectura y Patrones](#arquitectura-y-patrones)
6. [Estructura del Proyecto](#estructura-del-proyecto)
7. [Manual de Instalación y Ejecución](#manual-de-instalación-y-ejecución)
8. [Reportes](#reportes)

---

## 🚀 Descripción del Proyecto
Este repositorio contiene la automatización de pruebas Front-End E2E para la aplicación SofkianOS.

> "Transformamos la identidad Sofkiana en Kudos tangibles. El término Kudos proviene del griego kŷdos, que significa honor, reconocimiento y prestigio por un logro. Aquí representa la forma en que celebramos los aportes reales de cada persona."

El objetivo es validar que el ciclo de reconocimiento sea íntegro, asegurando que la arquitectura soporte cambios futuros sin romperse.

## 🔄 Flujo E2E Automatizado
La automatización cubre el proceso principal de identidad y mérito dentro de la plataforma:
1. Acceso: Ingreso a la sección de generación de reconocimientos.
2. Creación: Registro de un nuevo reconocimiento completando los datos requeridos por la identidad Sofkiana.
3. Verificación: Exploración de la sección de Kudos para confirmar que el reconocimiento se visualiza correctamente.

## 🏗️ Arquitectura y Patrones
Siguiendo los requisitos del reto, se implementa:
*   **POM:** Organización de elementos y acciones en clases dedicadas por página.
*   **Page Factory:** Uso de **`@FindBy`** para declarar elementos y **`initElements()`** para su inicialización, optimizando la legibilidad del código.
*   **Separación de Lógica:** Las validaciones (aserciones) y acciones se centralizan en las clases de página para proteger los tests ante cambios frecuentes en la UI.

## 📂 Estructura del Proyecto
Estructura de carpetas optimizada para el patrón POM:

```text
src/test
└── java
    └── com
        └── sofkianos
            ├── hooks           <-- Configuración previa y posterior a los tests
            ├── pages           <-- Page Objects (Uso de @FindBy + Acciones + Validaciones)
            ├── runners         <-- Clases ejecutoras de Cucumber
            ├── stepdefinitions <-- Implementación de los pasos Gherkin
            └── util            <-- Utilidades (Helpers, esperas, DriverFactory)
└── resources
    └── features
        └── reconocimiento_kudos.feature    <-- Escenarios de identidad y Kudos
```

## ⚙️ Manual de Instalación y Ejecución
1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/AUTO_FRONT_POM_FACTORY.git
    ```
2.  **Ejecutar las pruebas:**
    Utiliza Gradle para limpiar el entorno, ejecutar los escenarios y generar el reporte de Serenity:
    ```bash
    gradle clean test aggregate
    ```

## 📊 Reportes
Al finalizar, se puede consultar el reporte detallado con las evidencias de la ejecución en:
`target/site/serenity/index.html`.