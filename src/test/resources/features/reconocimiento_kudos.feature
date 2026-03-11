Feature: Gestion de reconocimientos en SofkianOS

  Scenario: Crear y validar un reconocimiento en Kudos
    Given que el usuario ingresa a la pagina de generacion de reconocimientos
    When crea un reconocimiento con los datos requeridos
    Then explora la seccion de Kudos y verifica que el reconocimiento fue creado