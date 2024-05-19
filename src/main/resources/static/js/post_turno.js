window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_turno');

    formulario.addEventListener('submit', function (event) {
    event.preventDefault()
        const formData = {

            pacienteId: document.querySelector('#paciente_id').value,
            odontologoId: document.querySelector('#odontologo_id').value,
            fecha: document.querySelector('#fecha').value,

            }


        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno agregado </div>'

                 resetUploadForm();
                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";


            })
            .catch(error => {

                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> No se puede registrar turno, corrobore que el ID del odontólogo o paciente sea correcto </strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";

                     ;})

           function resetUploadForm(){

                    document.querySelector('#paciente_id').value = "";
                    document.querySelector('#odontologo_id').value = "";
                    document.querySelector('#fecha').value = "";

                    }
    });

  });
