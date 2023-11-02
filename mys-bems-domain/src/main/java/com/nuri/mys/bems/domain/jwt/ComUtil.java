package com.nuri.mys.bems.domain.jwt;

import com.nuri.mys.bems.domain.jwt.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ComUtil {

	public static byte[] _ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

	// 소수점 4자리 이하 절삭.
	public static BigDecimal setDecimalScale(BigDecimal src) {
		return src.setScale(4, BigDecimal.ROUND_DOWN);
	}

	public static HashMap<String, Object> getSystemError(int errorCode, String errorMessage) {
		HashMap<String, Object> systemError = new HashMap<>();
		systemError.put("code", errorCode);
		if (!StringUtils.isEmpty(errorMessage)) {
			systemError.put("message", errorMessage);
		}

		return systemError;
	}

	public static int StringToInt(String string, int defaultValue) {
		try {
			if (string == null || string.trim().equals(""))
				return defaultValue;

			int value = Integer.parseInt(string);

			return value;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static BigDecimal StringToBigDecimal(String string) {
		try {
			if (string == null || string.trim().equals(""))
				return new BigDecimal("0.0");

			BigDecimal value = new BigDecimal(string);
			return value;
		} catch (NumberFormatException e) {
			return new BigDecimal("0.0");
		} catch (Exception e) {
			return new BigDecimal("0.0");
		}
	}

	/**
	 * 이메일주소 정규식 검사
	 * @param email
	 * @return boolean
	 */
	public static boolean validateEmail(String email) {
		Matcher matcher = Constant.REGEX_EMAIL_ADDRESS.matcher(email);
		return matcher.find();
	}

	/**
	 * 파일명 등을 url로 읽을 수 있도록 변환
	 * @param name
	 * @return String
	 */
	public static String makeReadableUrl(String name) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < name.length(); i++) {
			if (Character.isLetterOrDigit(name.charAt(i)) || name.charAt(i) == '.') {
				sb.append(name.charAt(i));
			} else {
				sb.append("_");
			}
		}
		return sb.toString();
	}

	/**
	 * 초 단위의 재생시간을 분과 초로 변환
	 * @param seconds
	 * @return time
	 */
	public static String secondsToTime(int seconds) {
		return String.format("%02d:%02d", seconds / 60, seconds % 60);
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat();

	/**
	 * 일시를 일 형식으로 반환
	 * @param time
	 * @return string
	 */
	public static String formattedTimeDay(Date time) {
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(time);
	}

	public static String formattedTimeStartDay(Date time) {
		sdf.applyPattern("yyyyMMdd000000");
		return sdf.format(time);
	}

	public static String formattedTimeEndDay(Date time) {
		sdf.applyPattern("yyyyMMdd235959");
		return sdf.format(time);
	}

	public static String formattedTimeDotDay(Date time) {
		sdf.applyPattern("yyyy.MM.dd");
		return sdf.format(time);
	}

	/**
	 * 일시를 일 시:분 형식으로 반환
	 * @param time
	 * @return string
	 */
	public static String formattedTimeHourMinSec(Date time) {
		sdf.applyPattern("dd/MM/yyyy HH:mm:ss");
		return sdf.format(time);
	}

	/**
	 * 이번년을 반환
	 * @return int 이번달
	 */
	public static int getThisYear() {
		Date dt = Calendar.getInstance().getTime();
		sdf.applyPattern("yyyy");
		return StringToInt(sdf.format(dt), 0);
	}
	
	/**
	 * 이번달을 반환
	 * @return int 이번달
	 */
	public static int getThisMonth() {
		Date dt = Calendar.getInstance().getTime();
		sdf.applyPattern("MM");
		return StringToInt(sdf.format(dt), 0);
	}

	/**
	 * 현재 타임스탬프를 반환
	 * @return time stamp
	 */
	public static String getTimeStamp() {
		Date dt = Calendar.getInstance().getTime();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}

	/**
	 * 오늘 0시 0분 0초
	 * @return Date
	 */
	public static Date getTodayStart() {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}

	private static final DecimalFormat df = new DecimalFormat("###,###");

	/**
	 * 가격 입력값을 콤마를 포함한 형식으로 반환
	 * @param price
	 * @return price
	 */
	public static String intToPrice(int price) {
		df.applyPattern("###,###");
		return df.format(price);
	}

	public static String longToPrice(long price) {
		df.applyPattern("###,###");
		return df.format(price);
	}

	public static String floatToPrice(float price) {
		df.applyPattern("###,###.###");
		return df.format(price);
	}

	public static String decimalToPrice(BigDecimal price) {
		df.applyPattern("###,###.###");
		return df.format(price);
	}

	/**
	 * AES 암호화된 텍스트를 반환
	 * @param txt
	 * @param key : 길이에 따라 128 / 192 / 256bit 처리
	 * @return encrypted string
	 * @throws java.io.UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encryptAES(String txt, String key) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] txtBytes = txt.getBytes(StandardCharsets.UTF_8);
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(_ivBytes);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		return new String(Base64.getEncoder().encode(cipher.doFinal(txtBytes)));
	}

	/**
	 * AES 복호화된 텍스트를 반환
	 * @param txt
	 * @param key : 길이에 따라 128 / 192 / 256bit 처리
	 * @return decrypted string
	 * @throws java.io.UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptAES(String txt, String key) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] txtBytes = Base64.getDecoder().decode(txt.getBytes(StandardCharsets.UTF_8));
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(_ivBytes);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		return new String(cipher.doFinal(txtBytes), "UTF-8");
	}
}
