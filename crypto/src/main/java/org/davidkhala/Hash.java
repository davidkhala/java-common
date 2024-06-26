package org.davidkhala;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	public static byte[] MD5(byte[] message) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		return md.digest(message);
	}

	public static byte[] SHA2_256(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest e = MessageDigest.getInstance("SHA-256");
		return e.digest(data);
	}
}
