package com.asm.webConfig;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashUtil {
	public static String hash(String password) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(password, salt);
	}
	
	public static boolean verify(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}
}
