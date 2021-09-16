package cn.net.yzl.oa.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.net.yzl.oa.entity.pojo.AppStaffClockLogPo;
import cn.net.yzl.oa.entity.vo.AppStaffClockVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AESUtil {

	private static final String IV_STRING = "16-Bytes--String";
	public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIMEZONE = "GMT+8";
	private static final Pattern pattern = Pattern.compile("&");
	private static final String key = "yzl_staff&oa#514";

	private AESUtil() {
		super();
	}

	/**
	 * 加密时间date
	 *
	 * @param content
	 * @return
	 */
	public static String encryptAES(Date content) {
		if (content == null) {
			return null;
		}
		String reslut = null;
		try {
			reslut = encryptAES(DateUtil.format(content, FORMAT), key);
		} catch (Exception e) {
			log.error("AES加密失败.{}", content, e);
		}
		return reslut;
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

	/**
	 * 解密打卡对象（staffNo=aes(staffNo&date)）
	 *
	 * @param appStaffClockVO
	 * @return
	 */
	public static AppStaffClockLogPo decryptAES(AppStaffClockVO appStaffClockVO) {
		AppStaffClockLogPo po = null;
		String staffNo = appStaffClockVO.getStaffNo();
		if (StrUtil.isNotBlank(staffNo)) {
			String result = decryptAES(staffNo);
			String[] ss = pattern.split(result);
			if (ss.length != 2) {
				return po;
			}
			staffNo = ss[0];
			Date clockTime = DateUtil.parse(ss[1], FORMAT);
			po = new AppStaffClockLogPo();
			BeanUtils.copyProperties(appStaffClockVO, po);
			po.setStaffNo(staffNo);
			po.setClockTime(clockTime);
		}
		return po;
	}

	public static String decryptAES(String content) {
		if (StrUtil.isBlank(content)) {
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

	public static void main(String[] args) {
		AppStaffClockVO vo = new AppStaffClockVO();
		vo.setAddress("北京市海淀区花园路街道泰兴大厦泰兴大厦(花园东路)");
		vo.setLatitude(BigDecimal.valueOf(39.9803540));
		vo.setLongitude(BigDecimal.valueOf(116.3689370));
		LocalDateTime now = LocalDateTime.now().minusMinutes(1);
		vo.setStaffNo(AESUtil.encryptAES(String.join("&", "6666", now.format(DateTimeFormatter.ofPattern(FORMAT)))));
		vo.setClockTime(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
		try {
			String json = new ObjectMapper().writeValueAsString(vo);
			System.err.println(json);
			System.err.println(AESUtil.encryptAES(json));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		AppStaffClockLogPo po = AESUtil.decryptAES(vo);
		System.out.println(po);
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
