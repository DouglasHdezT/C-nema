package com.kdc.cnema.utils;

import java.util.Base64;

public class ConstantsAPI {
	public final static String JWT_SECRET = "PVT031QV310134";
	
	public static String getEncodedSecret() {
		return Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
	}
}
