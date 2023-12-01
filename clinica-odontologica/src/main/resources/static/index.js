//const listaOdontologos = []

const divOdontologos = document.getElementById("verOdontologos")



function capturarInfo(){
    objetoOdontologo.matricula = matriculaOdontologo;
    objetoOdontologo.nombre = nameOdontologo;
    objetoOdontologo.apellido = lastNameOdontologo;
    
    return objetoOdontologo;
}
//CAPTURAR LOS INPUT DEL FORM
const form = document.getElementById("registrarOdontologo")
const inputMatricula = document.getElementById("matricula")
const inputNombre = document.getElementById("nombre")
const inputApellido = document.getElementById("apellido")
const divAclaracion = document.querySelector("div.aclaracion")


let settings = {
    "method":"POST",
    "headers": {
        "content-type": "application/json"
    },
    "body": {
        "matricula":"",
        "nombre":"",
        "apellido":""
    }
}

//METODO LISTAR ODONTOLOGOS
    const template = document.createElement("ul")
    fetch("http://localhost:8081/odontologos/listar")
        .then((response) => {
            return response.json()
        })
        .then(function(información){
            console.log(información)
            const templ = información.map(información => template.innerHTML +=`<li>${información.nombre} ${información.apellido}</li>`)
            divOdontologos.appendChild(template)
            
        })

/* //CAPTURAR FORMULARIO PARA ELIMINAR

const formEliminarOdontologo = document.getElementById("eliminarOdontologo")

let settings2 = {
    "method":"DELETE",
    "headers": {
        "content-type": "application/json"  
    }
} */

/* const idOdontologo = document.getElementById("idOdontologo").value
formEliminarOdontologo.addEventListener("submit", function(e){
    e.preventDefault()
    //No nos salio pero lo intentamos
    fetch(`http://localhost:8081/odontologos/eliminar/${idOdontologo}`, settings2)
    .then(response => {
        if (!response.ok) {
            throw new Error(`Error HTTP, estado: ${response.status}`);
        }
        console.log(response);
        return response.json();
    })
    .then(información => {
        console.log('Éxito:', información);
    })
    .catch(e => {
        console.error('Error:', e);
    })  
}) */


//PACIENTES

const verPacientes = document.getElementById("verPacientes")
const template2 = document.createElement("ul")
fetch("http://localhost:8081/pacientes/listar")
        .then((response) => {
            return response.json()
        })
        .then(function(información){
            console.log(información)
            const templ = información.map(información => template2.innerHTML +=`<li>${información.nombre} ${información.apellido}</li>`)
            verPacientes.appendChild(template2)
            
    })

//TURNOS

const verTurnos = document.getElementById("verTurnos")
const template3 = document.createElement("ul")
fetch("http://localhost:8081/turnos/listar")
        .then((response) => {
            return response.json()
        })
        .then(function(información){
            console.log(información)
            const templ = información.map(información => template3.innerHTML +=`
            <li>${información.pacienteTurnoSalidaDto.nombre} ${información.pacienteTurnoSalidaDto.apellido}
                ${información.odontologoTurnoSalidaDto.nombre} ${información.odontologoTurnoSalidaDto.apellido}
                ${información.fechaYHora}
            </li>`)
            verTurnos.appendChild(template3)
            
    })