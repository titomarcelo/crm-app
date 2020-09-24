package com.tmao.crm.commons.domain;

public enum MessageType {

	PRIMARY {
		@Override
		public String getStyle() {
			return "primary";
		}
	},

	SECONDARY {
		@Override
		public String getStyle() {
			return "secondary";
		}
	},

	SUCCESS {
		@Override
		public String getStyle() {
			return "success";
		}
	},

	DANGER {
		@Override
		public String getStyle() {
			return "danger";
		}
	},

	WARNING {
		@Override
		public String getStyle() {
			return "warning";
		}
	},

	INFO {
		@Override
		public String getStyle() {
			return "info";
		}
	},

	LIGHT {
		@Override
		public String getStyle() {
			return "light";
		}
	},

	DARK {
		@Override
		public String getStyle() {
			return "dark";
		}
	};

	public abstract String getStyle();

}
