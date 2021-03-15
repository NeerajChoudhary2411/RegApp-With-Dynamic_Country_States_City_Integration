package in.org.neeraj.utility;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface AppUtil {

	static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	static final String NUMBER = "0123456789";

	static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	static SecureRandom random = new SecureRandom();

	public static Map<Integer, String> convertToMap(List<Object[]> list) {
		return list.stream().collect(Collectors.toMap(ob -> (Integer) ob[0], ob -> (String) ob[1]));
	}

	public static String generateRandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			// debug
			System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

			sb.append(rndChar);

		}

		return sb.toString();

	}

}
