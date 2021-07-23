package datos;

public class Movimiento {
    private int idDocumento;
    private String usuario;
    private String tipoMovimiento;
    private Fecha fecha;
    private Documento documento;

    public Movimiento(){

    }

    public Movimiento(int idDocumento, String usuario, String tipoMovimiento, Fecha fecha, Documento documento) {
        this.idDocumento = idDocumento;
        this.usuario = usuario;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.documento = documento;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
