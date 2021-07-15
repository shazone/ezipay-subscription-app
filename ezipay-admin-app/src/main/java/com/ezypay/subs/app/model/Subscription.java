package com.ezypay.subs.app.model;

public enum Subscription {
	    WEEKLY,
	    MONTHLY,
	    DAILY;
	
	public String toString() {
		switch (this) {
			case WEEKLY:
				return "WEEKLY";
			case MONTHLY:
				return "MONTHLY";
			case DAILY:
				return "DAILY";
		}
		return "";
	}

}
