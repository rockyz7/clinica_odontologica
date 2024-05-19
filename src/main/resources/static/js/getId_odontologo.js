

 function findById() {
                     const id = document.querySelector('#findById').value
                    const table = document.querySelector('#odontologoIdTable')

                    const url = '/odontologos'+"/"+id;

                    const settings = {
                        method: 'GET'
                    }
                    fetch(url,settings)
                    .then(response => response.json())
                    .then(data => {
                        console.log(data)
                        table.innerHTML =
                                            '<h3>' + 'Odontologo #' + data.id + '</h3>' +
                                            '<p>' + '<b>Matricula: </b>' + data.matricula + '</p>' +
                                            '<p>' + '<b>Nombre: </b>' + data.nombre.toUpperCase() + '</p>' +
                                            '<p>' + '<b>Apellido: </b>' + data.apellido.toUpperCase() + '</p>'



                        //el formulario por default esta oculto y al editar se habilita

                    }).catch(error => {
                        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                                             '<strong> No existe un odontólogo con ese ID </strong> </div>'

                                              document.querySelector('#odontologoIdTable').innerHTML = errorAlert;
                    })
                }