#### Traer paciente por id

```http
  GET /pacientes/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |

No se le pasan parametros.

#### Registrar Paciente

```http
  POST /pacientes/registrar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `nombre`  | `string` | **Required**. |
| `apellido`| `string` | **Required**. |
| `dni`     | `int` | **Required**. |
| `fechaIngreso`  | `LocalDate` | **Required**. | 
| `calle`| `string` | **Required**. |
| `numero`     | `int` | **Required**. |
| `localidad`     | `string` | **Required**. |
| `provincia`     | `string` | **Required**. |

La fecha de ingreso no puede ser anterior al día de hoy.


ejemplo:
```json
  {
  "nombre": "Martin",
  "apellido": "Di Salvo",
  "dni": 12345679,
  "fechaIngreso": "2024-06-16",
  "domicilioEntradaDto": {
    "calle": "Lacalle",
    "numero": 1612,
    "localidad": "Campana",
    "provincia": "Buenos Aires"
  }
}
```

### Modificar Paciente y su domicilio

```http
  PUT /pacientes/actualizar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`  | `long` | **Required**. |
| `nombre`  | `string` | **Required**. |
| `apellido`| `string` | **Required**. |
| `dni`     | `int` | **Required**. |
| `fechaIngreso`  | `LocalDate` | **Required**. | 
| `calle`| `string` | **Required**. |
| `numero`     | `int` | **Required**. |
| `localidad`     | `string` | **Required**. |
| `provincia`     | `string` | **Required**. |

La fecha de ingreso no puede ser anterior al día de hoy.


ejemplo:
```json
  {
  "id":1,
  "nombre": "Martin",
  "apellido": "Perez",
  "dni": 12345679,
  "fechaIngreso": "2024-01-01",
  "domicilioModificacionEntradaDto": {
    "id": 1,
    "calle": "Lacalle",
    "numero": 1612,
    "localidad": "Escobar",
    "provincia": "Buenos Aires"
  }
}



## Odontologos


#### Traer odontologo por id

```http
  GET /odontologos/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |


#### Listar Odontologos
```http
  GET /odontologos/listar
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |



#### Registrar odontologo

```http
  POST /odontologos/registrar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `matricula` | `int` | **Required**. |
| `nombre`  | `string` | **Required**. |
| `apellido`| `string` | **Required**. |


ejemplo:
```json
{
  "matricula":"YYYYYBBBB15636788",
  "nombre":"Geronimo",
  "apellido":"Benavidez"
} 
```
#### Modificar odontologo

```http
  PUT /odontologos/actualizar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id` | `long` | **Required**. |
| `matricula` | `int` | **Required**. |
| `nombre`  | `string` | **Required**. |
| `apellido`| `string` | **Required**. |


ejemplo:
```json
{
  "id":1,
  "matricula":"ASSSSSSSASA14",
  "nombre":"Momo",
  "apellido":"Benavidez"
} 

```


#### Eliminar Odontologo

```http
  DELETE /odontologos/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |


## Turnos

#### Listar turnos

```http
  GET /turnos/listar
```

#### Traer turno por id

```http
  GET /turno/{id}
```

#### Registrar turno
```http
  POST /turnos/registrar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fechaYHora` | `DateTime` | **Required**. |
| `pacienteId`| `long` | **Required**. |
| `odontologoId`| `long` | **Required**. |

ejemplo:
```json
{
  "fechaYhora": "2023-11-28T10:00:00",
  "odontologoId": 1,
  "pacienteId": 1
}

#### Eliminar Turno

```http
  DELETE /turnos/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |

