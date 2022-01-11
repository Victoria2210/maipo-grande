function checkearEstado(estadoVenta) {
    const estado = estadoVenta;
    switch (estado) {
        case "P":
            $('#list-home-list').tab('show');
            break;
        case "T":
            $('#list-profile-list').tab('show');
            break;
        case "E":
           $('#list-messages-list').tab('show');
           break;

    }
}
