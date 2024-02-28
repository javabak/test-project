ClientController
## Features

- Add a client
- Get all clients
- Get a client by ID

## Endpoints

- **POST /addClient**
  - Description: Save the client
  - Request Body: Client object
  - Responses:
    - 200 - Successfully saved the client
    - 400 - Bad request

- **GET /getAllClients**
  - Description: Get all clients
  - Responses:
    - 200 - Successfully got all clients
    - 404 - Clients not found

- **GET /getClientById/{clientId}**
  - Description: Get client by ID
  - Path Variable: clientId (Long)
  - Responses:
    - 200 - Successfully got client
    - 404 - Client with ID not found

ContactController
## Features

- Add a contact
- Get all contacts by client and contact type
- Get all contacts by client

    ## Endpoints

- **POST /addContact**
  - Description: Save the contact
  - Request Body: Contact object
  - Responses:
    - 200 - Successfully saved the contact
    - 400 - Bad request

- **GET /getAllContactsByClientAndType**
  - Description: Get all contacts by client and type
  - Responses:
    - 200 - Successfully got all contacts 
    - 404 - Contacts not found

- **GET /getAllContactsByClient**
  - Description: Get all contacts by client
  - Responses:
    - 200 - Successfully got all contacts
    - 404 - Contacts not found
