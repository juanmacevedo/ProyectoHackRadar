package py.una.entidad;

public class Reporte {

	String reportId;
	String hunterId;
	String programId;
	String titulo;
	String descripcion;
	String severidad;
	String estado;
	String fechaReporte;
	String ultimaActualizacion;
	String comentarioRevisor;

	public Reporte(){
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getHunterId() {
		return hunterId;
	}

	public void setHunterId(String hunterId) {
		this.hunterId = hunterId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSeveridad() {
		return severidad;
	}

	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(String fechaReporte) {
		this.fechaReporte = fechaReporte;
	}

	public String getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(String ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	public String getComentarioRevisor() {
		return comentarioRevisor;
	}

	public void setComentarioRevisor(String comentarioRevisor) {
		this.comentarioRevisor = comentarioRevisor;
	}
}