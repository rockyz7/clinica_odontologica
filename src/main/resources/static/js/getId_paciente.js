 function findById() {
                     const id = document.querySelector('#findById').value
                    const table = document.querySelector('#pacienteIdTable')

                    const url = '/pacientes'+"/"+id;

                    const settings = {
                        method: 'GET'
                    }
                    fetch(url,settings)
                    .then(response => response.json())
                    .then(data => {
                        console.log(data)
                        table.innerHTML =
                                            '<h3>' + 'Paciente #' + data.id + '</h3>' +
                                            '<p>' + '<b>Nombre: </b>' + data.nombre.toUpperCase() + '</p>' +
                                            '<p>' + '<b>Apellido: </b>' + data.apellido.toUpperCase() + '</p>' +
                                            '<p>' + '<b>Fecha de ingreso: </b>' + data.fechaIngreso + '</p>' +
                                            '<p>' + '<b>Documento: </b>' + data.dni + '</p>' +
                                            '<p>' + '<b>Email: </b>' + data.email + '</p>'


                        //el formulario por default esta oculto y al editar se habilita

                    }).catch(error => {
                        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                                             '<strong> No existe un paciente con ese ID </strong> </div>'

                                              document.querySelector('#pacienteIdTable').innerHTML = errorAlert;
                    })
                }