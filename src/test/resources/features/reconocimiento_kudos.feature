Feature: Gestion de reconocimientos en SofkianOS

  Scenario Outline: Crear y validar un reconocimiento en Kudos
    Given que el usuario ingresa a la pagina de generacion de reconocimientos
    When crea un reconocimiento seleccionando el remitente "<remitente>"
    And selecciona al destinatario "<destinatario>"
    And selecciona la categoria "<categoria>"
    And escribe un mensaje de felicitacion "<mensaje>"
    And envia el reconocimiento
    Then explora la seccion de Kudos y verifica que el reconocimiento fue creado
    Examples:
      | remitente  | destinatario  | categoria   | mensaje                    |
      | Santiago   | Backend Team  | Innovation  | Gracias por tu gran aporte |