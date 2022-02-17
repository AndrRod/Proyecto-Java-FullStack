// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuairos();
  emailUsuario();
  $('#usuarios').DataTable();
});
async function cargarUsuairos(){
  //para llamar al servidor hay que usuar la funcion fetch
  const request = await fetch("api/usuarios/",
      {
        method: "GET",
        headers:{
          'Accept': 'application/json',
          'Content-Type': 'application/json',
            //    aca hay que agregar la informacion del token (se toma la informacion del localStorage)
            'Authorization': localStorage.token
        }
      });


  // seguido convertimos a json
  const usuarios = await request.json();
  let listadoHtml = "";
    for (let usuario of usuarios){

    let nombre = usuario.nombre == null ? "-" : usuario.nombre;
    let usuarioHtml = "" +
        "<tr>\n" +
    "            <td>"+ usuario.id + "</td>\n" +
    "            <td>"+ nombre +"</td>\n" +
    "            <td>"+ usuario.email +"</td>\n" +
    "            <td>\n" +
    "                <a href=\"#\" class=\"btn btn-outline-success\" >\n" +
    "                       <i class=\"fa fa-plus-square\"> Ver más</i>\n" +
    "               </a>\n" +
    "               <a href=\"#\" class=\"btn btn-outline-secondary\">\n" +
    "                   <i class=\"fa fa-marker\"> Modificar</i>\n" +
    "               </a>\n" +
    "                <a href=\"#\" onclick='eliminarUsuario("+usuario.id+")' class=\"btn btn-outline-danger\">\n" +
    "                    <i class=\"fas fa-trash\"> Borrar</i>\n" +
    "                </a>\n" +
    "           </td>\n" +
    "</tr>"
    listadoHtml+=usuarioHtml;
}
    document.querySelector("#usuarios tbody").outerHTML = listadoHtml;
}
function emailUsuario(){
    document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}
function getHeaders(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        //    aca hay que agregar la informacion del token (se toma la informacion del localStorage)
        'Authorization': localStorage.token
    }

}

async function eliminarUsuario(id) {

    if(confirm("desea eliminar el usuario id " + id)){
    //para llamar al servidor hay que usuar la funcion fetch
    const request = await fetch("api/usuarios/" + id,
        {
            method: "DELETE",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            //    aca hay que agregar la informacion del token (se toma la informacion del localStorage)
                'Authorization': localStorage.token
            }
        });
    document.location.reload();
    }

}