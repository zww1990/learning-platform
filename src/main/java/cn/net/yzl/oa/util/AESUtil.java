package cn.net.yzl.oa.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AESUtil {

	private static final String IV_STRING = "16-Bytes--String";
	public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMAT = "yyyy-MM-dd";
	public static final String TIMEZONE = "GMT+8";
	private static final String key = "yzl_staff&oa#514";

	private AESUtil() {
		super();
	}

	public static String encryptAES(String content) {
		if (content == null) {
			return null;
		}
		String reslut = null;
		try {
			reslut = encryptAES(content, key);
		} catch (Exception e) {
			log.error("AES加密失败.{}", content, e);
			throw new RuntimeException("加密失败");
		}
		return reslut;
	}

	public static String decryptAES(String content) {
		if (!StringUtils.hasText(content)) {
			return null;
		}
		String reslut = null;
		try {
			reslut = decryptAES(content, key);
		} catch (Exception e) {
			log.error("解密失败:{}", content, e);
			throw new RuntimeException("解密失败");
		}
		return reslut;
	}

	/**
	 * 加密
	 *
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptAES(String content, String key) throws Exception {
		byte[] byteContent = content.getBytes("UTF-8");
		// 注意，为了能与 iOS 统一
		// 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
		byte[] enCodeFormat = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

		byte[] initParam = IV_STRING.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

		// 指定加密的算法、工作模式和填充方式
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encryptedBytes = cipher.doFinal(byteContent);
		// 同样对加密后数据进行 base64 编码
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(encryptedBytes);
	}

	/**
	 * 解密
	 *
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String content, String key) throws Exception {
		// base64 解码
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedBytes = decoder.decode(content);
		byte[] enCodeFormat = key.getBytes();
		SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV_STRING.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		byte[] result = cipher.doFinal(encryptedBytes);
		return new String(result, "UTF-8");
	}

}
