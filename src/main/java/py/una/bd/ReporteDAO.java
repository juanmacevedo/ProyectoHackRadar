package py.una.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import py.una.entidad.Reporte;

public class ReporteDAO {

	public Reporte seleccionarPorId(String reportId) {
		String SQL = "SELECT report_id, estado, ultima_actualizacion, comentario_revisor FROM reporte WHERE report_id = ? ";

		Reporte r = null;

		Connection conn = null;
		try
		{
			conn = Bd.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, reportId);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				r = new Reporte();
				r.setReportId(rs.getString(1));
				r.setEstado(rs.getString(2));
				r.setUltimaActualizacion(rs.getString(3));
				r.setComentarioRevisor(rs.getString(4));
			}

		} catch (SQLException ex) {
			System.out.println("Error en la seleccion: " + ex.getMessage());
		}
		finally  {
			try{
				conn.close();
			}catch(Exception ef){
				System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
			}
		}
		return r;

	}

	public long insertar(Reporte r) throws SQLException {

		String SQL = "INSERT INTO reporte(report_id, hunter_id, program_id, titulo, descripcion, severidad, estado, fecha_reporte, ultima_actualizacion) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";

		long id = 0;
		Connection conn = null;

		try
		{
			conn = Bd.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, r.getReportId());
			pstmt.setString(2, r.getHunterId());
			pstmt.setString(3, r.getProgramId());
			pstmt.setString(4, r.getTitulo());
			pstmt.setString(5, r.getDescripcion());
			pstmt.setString(6, r.getSeveridad());
			pstmt.setString(7, r.getEstado());
			pstmt.setString(8, r.getFechaReporte());
			pstmt.setString(9, r.getUltimaActualizacion());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {
				id = affectedRows;
			}

		} catch (SQLException ex) {
			System.out.println("Error en la insercion: " + ex.getMessage());
		}
		finally  {
			try{
				conn.close();
			}catch(Exception ef){
				System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
			}
		}

		return id;
	}

}