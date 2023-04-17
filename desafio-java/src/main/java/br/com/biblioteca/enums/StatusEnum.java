package br.com.biblioteca.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum StatusEnum {
	
	EM_ANALISE,
	
	ANALISE_REALIZADA,

	ANALISE_APROVADA,
	
	PLANEJADO,
	
	INICIADO,
	
	EM_ANDAMENTO,
	
	ENCERRADO,
	
	CANCELADO;
	
	public String getDesc() {
		String str = this.toString().replace("_", " ").toLowerCase().replace("analise", "análise");
		return str.substring(0,1).toUpperCase().concat(str.substring(1));
	}
	
	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<>();
		List<StatusEnum> list = Arrays.asList(values());
		list.forEach(item -> map.put(item.toString(), item.getDesc()));
		
		return map;
	}
}
