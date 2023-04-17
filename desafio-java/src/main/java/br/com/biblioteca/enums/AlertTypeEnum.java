package br.com.biblioteca.enums;

public enum AlertTypeEnum {
	SUCCESS, DANGER, WARNING;

	public String getType() {
		switch (this) {
		case SUCCESS: {
			return "alert-success";
		}
		case DANGER: {
			return "alert-danger";
		}
		case WARNING: {
			return "alert-warning";
		}
		default:
			return "alert-info";
		}
	}
}
