package br.com.biblioteca.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RiscoEnum {
	
	ALTO,
	
	MEDIO,
	
	BAIXO;
	
	public String getDesc() {
		String str = this.toString().toLowerCase().replace("medio", "médio");
		return str.substring(0,1).toUpperCase().concat(str.substring(1));
	}

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<>();
		List<RiscoEnum> list = Arrays.asList(values());
		list.forEach(item -> map.put(item.toString(), item.getDesc()));
		
		return map;
	}
}
