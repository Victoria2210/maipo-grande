package com.duoc.maipogrande.paginador;

public class Pagina {

    private short totalPaginas;
    private short paginaActual;
    private short desde;
    private short hasta;

    public Pagina(short totalPaginas,short paginaActual){
        this.totalPaginas = totalPaginas;
        this.desde = 1;
        this.hasta = (short)(desde+3);
        this.paginaActual = paginaActual;

        //Total de pagina menor o igual al numero de elementos por pagina
        if(totalPaginas <= 4) {
            desde = 1;
            hasta = totalPaginas;
        } else {
            //Pagina al inicio
            if(paginaActual <= 4/2) {
                desde = 1;
                hasta = 4;
                //Pagina al medio
            } else if(paginaActual >= totalPaginas - 4/2 ) {
                desde = (short) (totalPaginas - 4 + 1);
                hasta = (short) (desde+3);
                //Pagina al final
            } else {
                desde = (short) (paginaActual -4/2);
                hasta = (short) (desde+3);;
            }
        }
    }

    public short getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(short paginaActual) {
        this.paginaActual = paginaActual;
    }

    public short getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(short totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public short getDesde() {
        return desde;
    }

    public void setDesde(short desde) {
        this.desde = desde;
    }

    public short getHasta() {
        return hasta;
    }

    public void setHasta(short hasta) {
        this.hasta = hasta;
    }
}
