# AUTO_FRONT_POM_FACTORY: Automatización SofkianOS

## 📋 Índice
1. [Descripción del Proyecto](#-descripción-del-proyecto)
2. [Propuesta de Valor](#-propuesta-de-valor)
3. [Flujo E2E Automatizado](#-flujo-e2e-automatizado)
4. [Tecnologías y Stack](#-tecnologías-y-stack)
5. [Arquitectura y Patrones](#-arquitectura-y-patrones)
6. [Estructura del Proyecto](#-estructura-del-proyecto)
7. [Requisitos Previos](#-requisitos-previos)
8. [Manual de Instalación y Ejecución](#-manual-de-instalación-y-ejecución)
9. [Reportes](#-reportes)
10. [Equipo Original de Desarrollo](#-equipo-original-de-desarrollo)

---

## 🚀 Descripción del Proyecto
Este repositorio contiene la automatización de pruebas **Front-End E2E** para la aplicación **SofkianOS**, un sistema distribuido basado en microservicios.

> **Sofkian** (nuestra esencia) + **OS** (Sistema Operativo de Kudos) = **Cultura de Recompensa**.

El objetivo principal es validar que el ciclo de reconocimiento sea íntegro, transformando la identidad Sofkiana en **Kudos** tangibles (honor, reconocimiento y prestigio).

## 💎 Propuesta de Valor
*   **Reconocimiento Instantáneo:** Los Kudos se envían inmediatamente sin esperas visibles.
*   **Procesamiento Masivo:** Capacidad para procesar miles de reconocimientos mediante un pipeline asíncrono con RabbitMQ.
*   **Gamificación Justa:** Categorías con puntos y trazabilidad para fortalecer los vínculos en equipos distribuidos.

## 🔄 Flujo E2E Automatizado
La automatización cubre el camino crítico del usuario:
1.  **Acceso:** Ingreso a la landing page y navegación al formulario de reconocimientos.
2.  **Creación:** Registro de un nuevo reconocimiento (Remitente, Destinatario, Categoría y Mensaje).
3.  **Envío Especial:** Ejecución de la acción de envío mediante el **óvalo deslizante** (interacción personalizada).
4.  **Verificación:** Exploración de la sección de **Kudos** para confirmar la persistencia y visibilidad del mensaje.

## 🛠️ Tecnologías y Stack
### Automatización (QA)
*   **Lenguaje:** Java 17+.
*   **Framework de Automatización:** Serenity BDD.
*   **Test Runner:** Cucumber.
*   **Gestión de Dependencias:** Gradle.
*   **Patrón:** Page Object Model (POM) con **Page Factory**.

### Sistema bajo Prueba - SofkianOS
*   **Frontend:** React + Vite.
*   **Backend:** Spring Boot (Java 17).
*   **Mensajería:** RabbitMQ.
*   **Infraestructura:** Docker (Multi-stage).

## 🏗️ Arquitectura y Patrones

*   **Page Factory:** Uso de la anotación **`@FindBy`** para la localización de elementos y **`initElements()`** para su inicialización, reduciendo el código repetitivo.
*   **Separación de Lógica:** Las acciones y validaciones están encapsuladas en las clases de página para proteger los tests ante cambios en la UI.
*   **Configuración Centralizada:** Uso de `serenity.conf` para la gestión del WebDriver y capacidades del navegador.

## 📂 Estructura del Proyecto
```text
src/test
└── java
    └── com.sofkianos
        ├── pages           <-- Page Objects (@FindBy + Métodos de acción)
        ├── runners         <-- Ejecutores de Cucumber (JUnit)
        └── stepdefinitions <-- Implementación de pasos Gherkin
└── resources
    ├── features            <-- Escenarios declarativos en Gherkin
    └── serenity.conf       <-- Configuración del Driver y Navegador
```

## 📋 Requisitos Previos
Para ejecutar este proyecto, necesitas tener instalado:
1.  **Java JDK 17 o superior**.
2.  **Docker & Docker Compose** (para levantar la aplicación SofkianOS).
3.  **Git** (para clonar los repositorios).

## ⚙️ Manual de Instalación y Ejecución

### 1. Levantar el Sistema (SofkianOS)
Para que las pruebas tengan éxito, primero debes iniciar el ambiente local de la aplicación:
```bash
git clone https://github.com/ElyRiven/sofkianos-mvp.git

# Se recomienda descargar el archivo comprimido del proyecto o clonar el repo original
docker compose up --build
```
*   **Frontend Cliente:** `http://localhost:5173`.

### 2. Ejecutar la Automatización
Una vez que el entorno esté activo, clona este repositorio de pruebas y ejecuta:
```bash
# Clonar repositorio de automatización
git clone https://github.com/JavierLuisG/AUTO_FRONT_POM_FACTORY.git

# Ejecutar pruebas y generar reporte
gradle clean test aggregate
```

## 📊 Reportes
Serenity BDD genera un reporte interactivo y visual (con capturas de pantalla de cada acción). 
```bash
# Puedes encontrarlo en
target/site/serenity/index.html
```

---

## 👥 Equipo Original de Desarrollo
Este proyecto de automatización valida el trabajo realizado por:
> Christopher Pallo, Elian Condor, Leonel, Jean Pierre Villacis, Hans Ortiz.