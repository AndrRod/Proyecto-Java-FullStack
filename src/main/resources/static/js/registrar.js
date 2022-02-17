// Call the dataTables jQuery plugin
$(document).ready(function() {
});
async function registrar(){
    let datos = {};
    datos.nombre = document.getElementById("txtNombre").value;
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;
    // datos.repetirPassword = document.getElementById("txtRepetirPassword").value;

    let repetirPassword = document.getElementById("txtRepetirPassword").value;

    if(repetirPassword!==datos.password){
        alert("Las contraseñas escritas son diferentes");
        return;
    }

  //para llamar al servidor hay que usuar la funcion fetch
  const request = await fetch("api/usuarios/",
      {
        method: "POST",
        headers:{
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
          // agarra cualquier objeto y lo convierte en stirng json
        body: JSON.stringify(datos)
      });
    alert("la Cuenta " + datos.email + ", fue creada con exito")
    window.location.href = "login.html";

}

