Feature: Crear y obtener cuenta

  Background:
    Given url baseUrl
    * def accountUrl = '/cuentas'
    * def uuid = function(){ return java.util.UUID.randomUUID() + '' }
    * def start = () => karate.start({ mock: 'client-mock.feature', port: 8080 })
    * def port = callonce start

  Scenario: Crear cuenta

    Given path accountUrl
    And request { "clientId": "#(uuid())", "number": "00122", "type": "SAVING", "initialBalance": 100.00, "status": "TRUE" }
    And header Accept = 'application/json'
    When method post
    Then status 200

