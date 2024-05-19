 function findById() {
                     const id = document.querySelector('#findById').value
                    const table = document.querySelector('#turnoIdTable')

                    const url = '/turnos'+"/"+id;

                    const settings = {
                        method: 'GET'
                    }
                    fetch(url,settings)
                    .then(response => response.json())
                    .then(data => {
                        console.log(data)
                        table.innerHTML =
                                            '<h3>' + 'Turno #' + data.id + '</h3>' +
                                            '<p>' + '<b>Id paciente: </b>' + data.pacienteId + '</p>' +
                                            '<p>' + '<b>Id odontólogo: </b>' + data.odontologoId + '</p>' +
                                            '<p>' + '<b>Fecha: </b>' + data.fecha + '</p>'



                        //el formulario por default esta oculto y al editar se habilita

                    }).catch(error => {
                        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                                             '<strong> No existe un turno con ese ID </strong> </div>'

                                              document.querySelector('#turnoIdTable').innerHTML = errorAlert;
                    })
                }