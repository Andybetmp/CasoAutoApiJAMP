@FakeStoreAll
Feature: APIS de Fake Store

  Como usuario de Fake Store
  Quiero gestionar productos y consultar usuarios
  Para poder verificar el correcto funcionamiento de los servicios

  @CP01_Products @Products @Positivo
  Scenario: Obtener todos los productos exitosamente
    Given el actor establece el endpoint "/products"
    When el actor realiza una solicitud GET
    Then el codigo de respuesta debe ser 200
    And la lista de resultados no debe estar vacia

  @CP02_Products @Post_Products @Products @Positivo
  Scenario Outline: Crear un producto exitosamente
    Given el actor establece el endpoint "/products"
    When el actor crea un producto con "<title>" "<price>" "<description>" "<category>" "<image>"
    Then el codigo de respuesta debe ser 201
    And el campo "title" de la respuesta debe ser "<title>"

    Examples:
      | title             | price | description                 | category    | image                 |
      | Laptop Gamer Asus | 1200  | Laptop de medio rendimiento | electronics | https://i.pravatar.cc |

  @CP03_Users @Users @Positivo
  Scenario: Obtener todos los usuarios exitosamente
    Given el actor establece el endpoint "/users"
    When el actor realiza una solicitud GET
    Then el codigo de respuesta debe ser 200
    And la lista de resultados no debe estar vacia

  @CP04_Products @Products @Negativo
  Scenario: Consultar un recurso inexistente
    Given el actor establece el endpoint "/products/inexistente/99999"
    When el actor realiza una solicitud GET
    Then el codigo de respuesta debe ser 404