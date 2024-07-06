Feature: stateful mock server of client

  Background:
    * def uuid = function(){ return java.util.UUID.randomUUID() + '' }
    * def account = {"documentNumber": "100","name": "Client 100","gender": "MALE","bornDate": "2000-01-01T00:00:00Z","address": "address client 100","phoneNumber": "+100","id": "","password": "100","status": "TRUE"}

  Scenario: pathMatches('/clientes/{id}')
    * account.id = pathParams.id
    * def responseStatus = 200
    * def response = account

  Scenario: pathMatches('/clientes/search') && methodIs('post')
    * def idRequest = request.ids[0]
    * account.id = pathParams.id
    * def responseStatus = 200
    * def response = [ account ]

  Scenario:
    * def responseStatus = 404