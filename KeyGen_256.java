package com.keygen;

import java.security.SecureRandom;

public class KeyGen_256 {

	public static void main(String[] args) {

		// Generate a 256-bit secret key
		SecureRandom random = new SecureRandom();
		byte[] keyBytes = new byte[32];
		random.nextBytes(keyBytes);
		String secretKey = bytesToHex(keyBytes);

		// Print the key
		System.out.print("256 bit key : ");
		System.out.println(secretKey);
	}

	private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xff;
			hexChars[i * 2] = HEX_ARRAY[v >>> 4];
			hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0f];
		}
		return new String(hexChars);
	}
}

// public class KeyGen_512 {

// public static void main(String[] args) {

// // Generate a 512-bit secret key
// SecureRandom random = new SecureRandom();
// byte[] keyBytes = new byte[64];
// random.nextBytes(keyBytes);
// String secretKey = bytesToHex(keyBytes);

// // Print the key
// System.out.print("512 bit key : ");
// System.out.println(secretKey);
// }

// private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

// public static String bytesToHex(byte[] bytes) {
// char[] hexChars = new char[bytes.length * 2];
// for (int i = 0; i < bytes.length; i++) {
// int v = bytes[i] & 0xff;
// hexChars[i * 2] = HEX_ARRAY[v >>> 4];
// hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0f];
// }
// return new String(hexChars);
// }

// }
