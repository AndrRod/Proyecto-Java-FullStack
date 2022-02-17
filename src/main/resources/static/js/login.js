// Call the dataTables jQuery plugin
$(document).ready(function() {
  iniciarSesion();
});
async function iniciarSesion(){
    let datos = {};

    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;


  //para llamar al servidor hay que usuar la funcion fetch
  const request = await fetch("api/login/",
      {
        method: "POST",
        headers:{
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
          // agarra cualquier objeto y lo convierte en stirng json
        body: JSON.stringify(datos)
      });

     const response = await request.text();
     if (response==="ok"){
         window.location.href = "usuario.html"
     }
     alert("Usuario o Contraseña incorrecta, intente de nuevo")
}

