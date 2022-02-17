// Call the dataTables jQuery plugin
$(document).ready(function() {

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

     const response =  await request.text();
     // aca se debería devolver un tipo de error (401) y no un fail
     if (response !="fail"){
         //guardamos el token del lado del browser junto el email
         localStorage.email = datos.email;
         localStorage.token = response;
         window.location.href = "usuarios.html"
     }
     else {
         alert("Usuario o Contraseña incorrecta, intente de nuevo")
     }

}

