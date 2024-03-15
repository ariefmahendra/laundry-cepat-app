# Aplikasi Laundy Cepat

## API Spec

### Customer API

#### Create Customer

Request :

- Method : `POST`
- Endpoint : `/api/customers`
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "name": "string",
  "phoneNumber": "string",
  "address": "string"
}
```

Response :

- Status : 201 Created
- Body :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phoneNumber": "string",
    "address": "string"
  },
  "error": "string"
}
```

#### Get Customer

Request :

- Method : GET
- Endpoint : `/api/customers/{id}`
- Header :
    - Accept : application/json

Response :

- Status : 200 OK
- Body :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phoneNumber": "string",
    "address": "string"
  },
  "errors": "string"
}
```

#### Update Customer

Request :

- Method : PUT
- Endpoint : `/api/customers/{id}`
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "name": "string",
  "phoneNumber": "string",
  "address": "string"
}
```

Response :

- Status : 200 OK
- Body :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phoneNumber": "string",
    "address": "string"
  },
  "errors": "string"
}
```

#### Delete Customer

Request :

- Method : DELETE
- Endpoint : `/api/customers/{id}`
- Header :
    - Accept : application/json
- Body :

Response :

- Status : 200 OK
- Body :

```json
{
  "message": "string",
  "data": "OK",
  "errors": "string"
}
```

### Employee API

#### Create Employee

Request :

- Method : `POST`
- Endpoint : `/api/employees`
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "name": "string",
  "phoneNumber": "string",
  "address": "string"
}
```

Response :

- Status : 201 Created
- Body :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phoneNumber": "string",
    "address": "string"
  },
  "error": "string"
}
```

#### Get Employee

Request :

- Method : GET
- Endpoint : `/api/employees/{id}`
- Header :
    - Accept : application/json

Response :

- Status : 200 OK
- Body :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phoneNumber": "string",
    "address": "string"
  },
  "errors": "string"
}
```

#### Update Employee

Request :

- Method : PUT
- Endpoint : `/api/employee/{id}`
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "name": "string",
  "phoneNumber": "string",
  "address": "string"
}
```

Response :

- Status : 200 OK
- Body :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phoneNumber": "string",
    "address": "string"
  },
  "errors": "string"
}
```

#### Delete Employee

Request :

- Method : DELETE
- Endpoint : `/api/employee/{id}`
- Header :
    - Accept : application/json
- Body :

Response :

- Status : 200 OK
- Body :

```json
{
  "message": "string",
  "data": "OK",
  "errors": "string"
}
```


### Product API

#### Create Product

Request :

- Method : POST
- Endpoint : `/api/products`
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "name": "string",
  "price": BigDecimal,
  "unit": "string" (satuan product,cth: Buah atau Kg)
}
```

Response :

- Status Code: 201 Created
- Body:

```json
{
	"message": "string",
	"data": {
		"id": "string",
		"name": "string",
		"price": BigDecimal,
		"unit": "string" (satuan product,cth: Buah atau Kg)
	}
}
```

#### List Product

Request :

- Method : GET
- Endpoint : `/api/products`
    - Header :
    - Accept : application/json

Response :

- Status Code : 200 OK
- Body:

```json
{
	"message": "string",
	"data": [
		{
			"id": "string",
			"name": "string",
			"price": int,
			"unit": "string" (satuan product,cth: Buah atau Kg)
		},
		{
			"id": "string",
			"name": "string",
			"price": BigDecimal,
			"unit": "string" (satuan product,cth: Buah atau Kg)
		}
	],
  "error" : "string"
}
```

#### Product By Id

Request :

- Method : GET
- Endpoint : `/products/:id`
- Header :
    - Accept : application/json

Response :

- Status Code: 200 OK
- Body :

```json
{
	"message": "string",
	"data": {
		"id": "string",
		"name": "string",
		"price": BigDecimal,
		"unit": "string"
	},
  "errors": "string"
}
```

#### Update Product

Request :

- Method : PUT
- Endpoint : `/products/:id`
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
	"name": "string",
	"price": BigDecimal,
	"unit": "string"
}
```

Response :

- Status Code: 200 OK
- Body :

```json
{
  "message": "string",
  "data": {
	"id": "string",
	"name": "string",
	"price": BigDecimal, 
        "unit": "string"
  },
  "errors" : "string"
}
```

#### Delete Product

Request :

- Method : DELETE
- Endpoint : `/products/:id`
- Header :
    - Accept : application/json
- Body :

Response :

- Status : 200 OK
- Body :

```json
{
  "message": "product deleted",
  "data": null,
  "errors": null
}
```

### Transaction API

#### Create Transaction

Request :

- Method : POST
- Endpoint : `api/transactions`
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
	"billDate": "string",
	"entryDate": "string",
	"finishDate": "string",
	"employeeId": "string",
	"customerId": "string",
	"billDetails": [
		{
			"productId": "string",
			"qty": int
		}
	]
}
```

Request :

- Status Code: 201 Created
- Body :

```json
{
	"message": "string",
	"data":  {
		"id":  "string",
		"billDate":  "string",
		"entryDate":  "string",
		"finishDate":  "string",
		"employeeId":  "string",
		"customerId":  "string",
		"billDetails":  [
			{
				"id":	"string",
				"billId":  "string",
				"productId":  "string",
				"productPrice": int,
				"qty": int
			}
		]
	}
}
```

#### List Transaction

Request :

- Method : GET
- Endpoint : `/api/transactions`
- Header :
    - Accept : application/json
- Body :

Response :

- Status Code: 200 OK
- Body :

```json
{
	"message": "string",
  "data": [
    {
      "id": "string",
      "billDate": "string",
      "entryDate": "string",
      "finishDate": "string",
      "employee": {
        "id": "string",
        "name": "string",
        "phoneNumber": "string",
        "address": "string"
      },
      "customer": {
        "id": "string",
        "name": "string",
        "phoneNumber": "string",
        "address": "string"
      },
      "billDetails": [
        {
          "id": "string",
          "billId": "string",
          "product": {
            "id": "string",
            "name": "string",
            "price": int,
            "unit": "string" (satuan product,cth: Buah atau Kg)
          },
          "productPrice": int,
          "qty": int
        }
      ],
      "totalBill": int
    }
  ],
  "errors": "string"
}
```
