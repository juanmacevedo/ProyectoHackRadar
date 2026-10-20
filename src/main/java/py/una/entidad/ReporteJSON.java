package py.una.entidad;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReporteJSON {

	public static String objetoString(Reporte r) {

		JSONObject obj = new JSONObject();
		if (r.getReportId() != null) obj.put("report_id", r.getReportId());
		if (r.getHunterId() != null) obj.put("hunter_id", r.getHunterId());
		if (r.getProgramId() != null) obj.put("program_id", r.getProgramId());
		if (r.getTitulo() != null) obj.put("titulo", r.getTitulo());
		if (r.getDescripcion() != null) obj.put("descripcion", r.getDescripcion());
		if (r.getSeveridad() != null) obj.put("severidad", r.getSeveridad());
		if (r.getEstado() != null) obj.put("estado", r.getEstado());
		if (r.getFechaReporte() != null) obj.put("fecha_reporte", r.getFechaReporte());
		if (r.getUltimaActualizacion() != null) obj.put("ultima_actualizacion", r.getUltimaActualizacion());
		if (r.getComentarioRevisor() != null) obj.put("comentario_revisor", r.getComentarioRevisor());

		return obj.toJSONString();
	}

	public static Reporte stringObjeto(String str) throws Exception {
		Reporte r = new Reporte();
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(str.trim());
		JSONObject jsonObject = (JSONObject) obj;

		if (jsonObject.containsKey("report_id")) r.setReportId((String) jsonObject.get("report_id"));
		if (jsonObject.containsKey("hunter_id")) r.setHunterId((String) jsonObject.get("hunter_id"));
		if (jsonObject.containsKey("program_id")) r.setProgramId((String) jsonObject.get("program_id"));
		if (jsonObject.containsKey("titulo")) r.setTitulo((String) jsonObject.get("titulo"));
		if (jsonObject.containsKey("descripcion")) r.setDescripcion((String) jsonObject.get("descripcion"));
		if (jsonObject.containsKey("severidad")) r.setSeveridad((String) jsonObject.get("severidad"));
		if (jsonObject.containsKey("estado")) r.setEstado((String) jsonObject.get("estado"));
		if (jsonObject.containsKey("fecha_reporte")) r.setFechaReporte((String) jsonObject.get("fecha_reporte"));
		if (jsonObject.containsKey("ultima_actualizacion")) r.setUltimaActualizacion((String) jsonObject.get("ultima_actualizacion"));
		if (jsonObject.containsKey("comentario_revisor")) r.setComentarioRevisor((String) jsonObject.get("comentario_revisor"));

		return r;
	}

}