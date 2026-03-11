# AUTO_FRONT_POM_FACTORY: Automatización SofkianOS

## 📋 Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Motivación](#motivación)
3. [Tecnologías Empleadas](#tecnologías-empleadas)
4. [Arquitectura y Patrones](#arquitectura-y-patrones)
5. [Estructura del Proyecto](#estructura-del-proyecto)
6. [Manual de Instalación y Ejecución](#manual-de-instalación-y-ejecución)
7. [Reportes](#reportes)

---

## 🚀 Descripción del Proyecto
Este repositorio contiene la automatización de pruebas **Front-End E2E** para la aplicación **SofkianOS**.

> "Transformamos la identidad Sofkiana en Kudos tangibles. El término Kudos proviene del griego _kŷdos_, que significa honor, reconocimiento y prestigio por un logro. Aquí representa la forma en que celebramos los aportes reales de cada persona."

El objetivo es validar los flujos críticos de la plataforma asegurando que la arquitectura soporte cambios futuros sin romperse.

## 🛠️ Tecnologías Empleadas
*   **Lenguaje:** Java.
*   **Gestión de Dependencias:** Gradle.
*   **Framework:** Serenity BDD.
*   **Patrón de Diseño:** **Page Object Model (POM)** con **Page Factory**.
*   **Pruebas de Comportamiento:** Cucumber (Gherkin).

## 🏗️ Arquitectura y Patrones
Siguiendo los requisitos del reto, se implementa:
*   **POM (Page Object Model):** Organización de elementos de la interfaz y acciones en clases dedicadas por cada página de SofkianOS.
*   **Page Factory:** Uso de la anotación **`@FindBy`** para declarar elementos y **`initElements()`** para su inicialización, optimizando el rendimiento y la legibilidad del código.
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
        └── registro.feature    <-- Escenarios de negocio en Gherkin (identidad y Kudos)
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