$(document).ready(function () {
    const imagenGuardada = $('#imagenSalida').attr('src');
    const dataContent= $('input[type=file]').next(".custom-file-label").attr('data-content');
    const field= $('input[type=file]').next(".custom-file-label").text();
    //Eliminar con modal con datos de producto
    $('[name="btnEliminar"]').click(function () {
        var array = $(this).val().split(".");
        var id = array[0];
        var nombre = array[1];
        $('#lblNombre').text(nombre);
        $('#idProdu').val(id);
    });
    $('#btnConfirmar').click(function () {
        $('#exampleModal').modal('hide');
        $('#modalCargando').modal('show');
    })
    $("#btnBuscar").click(function () {
       let nombreProducto = $('#txtBuscar').val();
       if (nombreProducto != "")
       {
           sessionStorage.setItem("nombreProducto",nombreProducto);
           console.log("Guardado");
       }
       else
       {
           sessionStorage.removeItem("nombreProducto");
           console.log("Eliminado");
       }
    });

    $(".page-item").click(function () {

            $('#txtBuscar').val(sessionStorage.getItem("nombreProducto"));

    });

    //Estilos para en input file con cambio de texto
    $("input[type=file]").change(function () {

        const urlActual = window.location.pathname;
        const inputFile = this.files[0];
        const fileType = inputFile["type"];
        const validImageTypes = ['image/jpg', 'image/jpeg', 'image/png'];
        if (this.files && this.files[0] && $.inArray(fileType,validImageTypes) > 0) {
            const reader = new FileReader();
            reader.onload = function(e) {
                $('#imagenSalida').attr('src', e.target.result);
                $('#imagenSalida').addClass("img-thumbnail h-10 w-10");
            }
            let fieldVal = $(this).val();
            // Change the node's value by removing the fake path (Chrome)
            fieldVal = fieldVal.replace("C:\\fakepath\\", "");
            if (fieldVal != undefined || fieldVal != "") {
                $(this).next(".custom-file-label").attr('data-content', fieldVal);
                $(this).next(".custom-file-label").text(fieldVal);
            }
            reader.readAsDataURL(this.files[0]);
        }
        else
        {
            if(!urlActual.includes('productos'))
            {
                $('#imagenSalida').attr('src','');
                $('#imagenSalida').removeClass("img-thumbnail h-10 w-10");
                let $el = $('#fileImagen');
                $el.wrap('<form>').closest(
                    'form').get(0).reset();
                $el.unwrap();
                $(this).next(".custom-file-label").attr('data-content', '');
                $(this).next(".custom-file-label").text('Seleccione Archivo');
            }
            else
            {
                $('#imagenSalida').attr('src',imagenGuardada);
                $(this).next(".custom-file-label").attr('data-content', dataContent);
                $(this).next(".custom-file-label").text('Seleccione Archivo',field);
            }
        }

    });
    //Validacion de nombre con REGEXP
    $('#txtNombre').keypress(function (e) {
        var regex = new RegExp("^[a-zA-ZÁ-ÿ \s]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        else
        {
            e.preventDefault();
            return false;
        }
    });
});


(function() {
    'use strict';
    window.addEventListener('load', function() {
// Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
// Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                else
                {
                    $('#modalCargando').modal('show');
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();