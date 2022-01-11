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

function format(item, state) {
    if (!item.id) {
        return item.text;
    }
    var countryUrl = "https://lipis.github.io/flag-icon-css/flags/4x3/";
    var stateUrl = "https://oxguy3.github.io/flags/svg/us/";
    var url = state ? stateUrl : countryUrl;
    var img = $("<img>", {
        class: "img-flag",
        width: 26,
        src: url + item.element.value.toLowerCase() + ".svg"
    });
    var span = $("<span>", {
        text: " " + item.text
    });
    span.prepend(img);
    return span;
}

$(document).ready(function () {
    fechaMinima();
    fechaLimite();
    $('#btnAgregar').click(() => {
        if ($('.media').length >= 15) {
            return;
        }
        let numeroDiv = $('.media').last().attr('id').split('divProductos');
        const frutas = $("#frutas").val().split('.');
        const frutasOptions = traerFrutasEnOption(frutas);
        numeroDiv = numeroDiv[1];
        numeroDiv++;
        const html = "    <div class=\"media\" id=divProductos" + numeroDiv + ">\n" +
            "                                                <div class=\"media-body\">\n" +
            "                                                    <div class=\"form-row\">\n" +
            "                                                        <div class=\"form-group  col-md-6\">\n" +
            "                                                            <label>Nombre del producto</label>\n" +
            "                                                           <select class=\"selectpicker form-control\" data-live-search=\"true\" required=\"true\" name=\"nombreproducto[]\">" +
            "                                                           "+frutasOptions+"                     "+
            "                                                            </select>" +
            "                                                            <div class=\"invalid-feedback\">Nombre obligatorio</div>\n" +
            "                                                        </div>\n" +
            "                                                        <div class=\"form-group  col-md-6\">\n" +
            "                                                            <label>Cantidad</label>\n" +
            "                                                            <div class=\"input-group\">\n" +
            "                                                                <input class=\"form-control\" required=\"true\" onkeypress=\"return event.charCode >= 48 && event.charCode <= 57\" name=\"cantidadproducto[]\"/>\n" +
            "                                                                <div class=\"input-group-prepend\">\n" +
            "                                                                    <select name=\"unidadMasa[]\" required=\"true\" class=\"form-control\">\n" +
            "                                                                        <option value=\"\">Tipo</option>\n" +
            "                                                                        <option value=\"KG\">Kilogramos</option>\n" +
            "                                                                        <option value=\"T\">Toneladas</option>\n" +
            "                                                                    </select>"+
        "                                                                </div>\n" +
        "                                                            <div class=\"invalid-feedback\">Cantidad obligatoria</div>\n" +
        "                                                            </div>\n" +
        "                                                        </div>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <a class=\"align-self-center ml-3 text-secondary\">\n" +
        "                                                    <i class=\"fas fa-trash-alt fa-2x mt-2\" id='" + numeroDiv + "' name=\"trash\"\n" +
        "                                                       style=\"cursor: pointer\"></i>\n" +
        "                                                </a>\n" +
        "                                            </div>"

        $(html).insertBefore($('#btnAgregar'));
        $("html, body").animate({scrollTop: $(document).height()}, 500);
        $('.selectpicker').selectpicker('refresh');

    });
    function traerFrutasEnOption(frutas)
    {
        let options = "";
        frutas.forEach(f => {
            options += `<option value="${f}">${f}</option>\n`;
        })
        return options;
    }
    function fechaMinima() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!

        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = yyyy + '-' + mm + '-' + dd;
        $('#fechaEmision').attr('value', today);
        $('#fechaEmision').prop('readonly', true);
    }

    function fechaLimite() {
        var today = new Date();
        var today2 = new Date();

        today.setDate(today.getDate() + 15);
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = yyyy + '-' + mm + '-' + dd;

        today2.setDate(today2.getDate() + 50);
        var dd = today2.getDate();
        var mm = today2.getMonth() + 1; //January is 0!
        var yyyy = today2.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today2 = yyyy + '-' + mm + '-' + dd;
        $('#fechaLimite').attr('value', today);
        $('#fechaLimite').attr('min', today);
        $('#fechaLimite').attr('max', today2);
    }

    $(document).on('click', '[name="trash"]', (e) => {
        if ($('.media').length == 1) {
            return;
        }
        const id = $(e.target).attr('id');
        $('#divProductos' + id + '').remove();

    });
});