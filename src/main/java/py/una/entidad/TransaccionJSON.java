package py.una.entidad;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TransaccionJSON {

	public static String objetoString(Transaccion t) {

		JSONObject obj = new JSONObject();
		if (t.getTransaccionId() != null) obj.put("transaccion_id", t.getTransaccionId());
		if (t.getCuentaOrigen() != null) obj.put("cuenta_origen", t.getCuentaOrigen());
		if (t.getCuentaDestino() != null) obj.put("cuenta_destino", t.getCuentaDestino());
		if (t.getMonto() != 0) obj.put("monto", t.getMonto());
		if (t.getMoneda() != null) obj.put("moneda", t.getMoneda());
		if (t.getEstado() != null) obj.put("estado", t.getEstado());
		if (t.getFechaProceso() != null) obj.put("fecha_proceso", t.getFechaProceso());

		return obj.toJSONString();
	}

	public static Transaccion stringObjeto(String str) throws Exception {
		Transaccion t = new Transaccion();
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(str.trim());
		JSONObject jsonObject = (JSONObject) obj;

		if (jsonObject.containsKey("transaccion_id")) t.setTransaccionId((String) jsonObject.get("transaccion_id"));
		if (jsonObject.containsKey("cuenta_origen")) t.setCuentaOrigen((String) jsonObject.get("cuenta_origen"));
		if (jsonObject.containsKey("cuenta_destino")) t.setCuentaDestino((String) jsonObject.get("cuenta_destino"));
		if (jsonObject.containsKey("monto")) t.setMonto(((Number) jsonObject.get("monto")).doubleValue());
		if (jsonObject.containsKey("moneda")) t.setMoneda((String) jsonObject.get("moneda"));
		if (jsonObject.containsKey("estado")) t.setEstado((String) jsonObject.get("estado"));
		if (jsonObject.containsKey("fecha_proceso")) t.setFechaProceso((String) jsonObject.get("fecha_proceso"));

		return t;
	}

}