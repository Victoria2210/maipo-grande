(function () {
    'use strict';
    window.addEventListener('load', function () {
// Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
// Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                } else {
                    $('#modalCargando').modal('show');
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();
$(document).ready(()=> {
    function exit(e)
    {
        const divSinProductos = "<div class='container text-center'> <h5>No posee productos necesarios</h5> </div>";
        $('#' + e.currentTarget.id + '').parent().parent().children('.card-header.text-uppercase').append(divSinProductos);
    }
    function cargarProductos(e,num,response)
    {
        const productos = response.productos;
        $('#' + e.currentTarget.id + '').parent().parent().find('.d-flex.justify-content-center.mt-3').remove();
        if(productos.length === 0)
        {
            exit(e);
        }
        else {
            localStorage.setItem("items".concat(num[1]), JSON.stringify(productos));
            let productosCombo = "";
            productos.forEach((p) => {
                productosCombo += "<option value='" + p.idProdu + "'>" + p.nombreProdu + "</option>";
                return productosCombo;
            });
            const divAgregar = "<div class=\"card-body\">\n" +
                "<div class=\"row\">\n" +
                "\t\t\t<input type=\"hidden\" name=\"idProds[]\" value='"+num[1]+"'/>"+
                "<div class=\"col\">\n" +
                "<div class=\"form-group\">\n" +
                "<label>Producto a ofrecer</label> <select\n" +
                "class=\"custom-select browser-default\" id='cmbCancelar" + num[1] + "' name='cmbCancelar[]' required>\n" +
                "<option value=\"\" disabled selected>-Mis productos-</option>\n" +
                productosCombo +
                "</select>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"col\">\n" +
                "<div class=\"form-group\">\n" +
                "<label>Disponible</label> <input name='txtDisponible' class=\"form-control\" \n" +
                "readonly='true' value=\"\" />\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"form-group\">\n" +
                "<label>Precio a ofertar X Kg</label> <input\n" +
                "class=\"form-control\" type='number' required name='precioOfertar[]' />\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"card-footer py-0 px-0\">\n" +
                "<button type=\"button\"\n" +
                "class=\"btn btn-secondary rounded-0 btn-block\" id='btnCancelar" + num[1] + "' name='btnCancelar'>Cancelar</button>\n" +
                "</div>";
            const divPrincipal = $('#' + e.currentTarget.id + '').parent().parent();
            const divOfertar = $('#' + e.currentTarget.id + '').parent();
            divOfertar.remove();
            $(divPrincipal).append(divAgregar);
        }
    }
    $('[name="btnOfertar"]').click((e)=>
    {
        const num = e.currentTarget.id.split(/(\d+)/);
        const divCargando = "<div class=\"d-flex justify-content-center mt-3\">\n" +
            "  <div class=\"spinner-border\" role=\"status\">\n" +
            "    <span class=\"sr-only\">Loading...</span>\n" +
            "  </div>\n" +
            "</div>";
        $('#' + e.currentTarget.id + '').parent().parent().children('.card-header.text-uppercase').append(divCargando);
        $('#' + e.currentTarget.id + '').attr('disabled','disabled');
        $.ajax({
            type : 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: '/productosDisponibles',
            data: JSON.stringify({
                idProductoSolicitado:num[1]
            }),
            success : function (response) {
                cargarProductos(e,num,response);
            },
            error : function (e) {
                console.log(e);
            }
        });
    });
    $('body').on('click', '[name="btnOfertar"]', (e) => {
        const num = e.currentTarget.id.split(/(\d+)/);
        const divCargando = "<div class=\"d-flex justify-content-center mt-3\">\n" +
            "  <div class=\"spinner-border\" role=\"status\">\n" +
            "    <span class=\"sr-only\">Loading...</span>\n" +
            "  </div>\n" +
            "</div>";
        $('#' + e.currentTarget.id + '').parent().parent().children('.card-header.text-uppercase').append(divCargando);
        $('#' + e.currentTarget.id + '').attr('disabled','disabled');
        $.ajax({
            type : 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: '/productosDisponibles',
            data: JSON.stringify({
                idProductoSolicitado:num[1]
            }),
            success : function (response) {
                const productos = response.productos;
                if(productos.length === 0)
                {
                    exit();
                }
                else {
                    cargarProductos(e,num,response)
                }
            },
            error : function (e) {
                console.log(e);
            }
        });
    });
    $(document).on('click', '[name="btnCancelar"]', (e) => {
        const num = e.currentTarget.id.split(/(\d+)/);
        const divAgregar = "<div class=\"card-body py-0 px-0\">\n" +
            "<button type=\"button\"  id='btnOfertar"+num[1]+"' name=\"btnOfertar\"  class=\"btn btn-success btn-block rounded-0\">O F E R T A R</button>\n" +
            "</div>\n"
        const divCancelar  = $('#'+e.currentTarget.id+'').parent();
        const divPadre = $('#'+e.currentTarget.id+'').parent().parent();
        const divBody  = $('#'+e.currentTarget.id+'').parent().parent().children('.card-body');
        divCancelar.remove();
        divBody.remove();
        $(divPadre).append(divAgregar);
    });

    $(document).on('change', '[name="cmbCancelar[]"]', (e) => {
        const cmb = e.currentTarget.id;
        const num = e.currentTarget.id.split(/(\d+)/);
        const idProd = $('#'.concat(cmb)).val();
        const productos = JSON.parse(localStorage.getItem('items'.concat(num[1])));
        const txtDisponible = $(e.currentTarget).parent().parent().parent().parent().find('[name="txtDisponible"]');

        console.log(txtDisponible);
        productos.forEach((producto) => {
            if(producto.idProdu == idProd)
            {
                txtDisponible.val(producto.stockProdu+" "+firstLetter(producto.unidadMasaProdu.toLowerCase()));
            }
        });

    });
    function firstLetter(s) {
        return s.replace(/^.{1}/g, s[0].toUpperCase());
    }
    $('#btnAgregar').click((e)=>{
        if(!$('[name="cmbCancelar[]"]').length)
        {
            e.preventDefault();
        }
    });
});
