package com.dxc.paws.service;

public interface UtilityService {

	String EMPTY = "";

	static final int PAD_LIMIT = 8192;

	void requestLogging(Object request);

	public static boolean isInteger(String s) {
		return isInteger(s, 15);
	}

	public static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}


	public static String rightPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (padLen == 1 && pads <= PAD_LIMIT) {
			return rightPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen) {
			return str.concat(padStr);
		} else if (pads < padLen) {
			return str.concat(padStr.substring(0, pads));
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();
			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}
			return str.concat(new String(padding));
		}
	}

	public static String rightPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (pads > PAD_LIMIT) {
			return rightPad(str, size, String.valueOf(padChar));
		}
		return str.concat(repeat(padChar, pads));
	}

	public static String rightPad(String str, int size) {
		return rightPad(str, size, ' ');
	}

	public static String repeat(char ch, int repeat) {
		char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
			buf[i] = ch;
		}
		return new String(buf);
	}

}
