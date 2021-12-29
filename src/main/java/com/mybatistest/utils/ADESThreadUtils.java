package com.mybatistest.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * AES算法多线程工具类
 */
@Slf4j
public class ADESThreadUtils {
    @Test
    public void test(){
        //yinzzzz
        System.out.println( "openid："+ADESThreadUtils.decrypt("$ty$61j/NRnIecoHeydKirzfptdj7rWIW4HWEZYzNQ47WMw="));
        System.out.println( "unionid："+ADESThreadUtils.decrypt("$ty$rnIiB2Z7vAA0wgUmu6ppyTk++1t5jcFEDsbpX0V9EN8="));
        //note9




        //note 9
        System.out.println("加密openid "+ADESThreadUtils.encrypt("ojeFE0ooFC4uUR3uqKrekPLcNiM8"));
        System.out.println("加密unionid "+ADESThreadUtils.encrypt("ok9fW1DNbDRzM1rcxNH1nubplTSg"));
    }
    // 密盐
    private static String slatKey="snra6h1yki7fvgzo";
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_ALGORITHM_BC = "BC";
    // 加解密开关
    private static String encryptionMobileSwitch="1";



    /**
     * 从配置获取加解密开关：1开 0关
     * @param val
     */


    /**
     * 多线程初始化Cipher对象
     */
    private static final ThreadLocal<Cipher> decryptCiphers = new ThreadLocal<Cipher>() {
        @Override
        protected Cipher initialValue() {
            try {
                if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                    Security.addProvider(new BouncyCastleProvider());
                }
                byte[] raw = slatKey.getBytes(StandardCharsets.UTF_8);
                SecretKey secretKey = new SecretKeySpec(raw, AES_ALGORITHM);
                Cipher cipher = Cipher.getInstance(AES_ALGORITHM, AES_ALGORITHM_BC);  // 根据密钥，生成一个AES专用密钥
                cipher.init(Cipher.DECRYPT_MODE, secretKey);  // 初始化为解密模式的密码器
                return cipher;
            } catch (Exception e) {
                log.error("decryptCiphers()初始化有误：",e);
            }
            return null;
        }
    };

    /**
     * 多线程初始化Cipher对象
     */
    private static final ThreadLocal<Cipher> encryptCiphers = new ThreadLocal<Cipher>() {
        @Override
        protected Cipher initialValue() {
            try {
                if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                    Security.addProvider(new BouncyCastleProvider());
                }
                byte[] raw = slatKey.getBytes(StandardCharsets.UTF_8);
                SecretKeySpec secretKey = new SecretKeySpec(raw, AES_ALGORITHM);
                Cipher cipher = Cipher.getInstance(AES_ALGORITHM, AES_ALGORITHM_BC);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return cipher;
            } catch (Exception e) {
                log.error("encryptCiphers()初始化有误：",e);
            }
            return null;
        }
    };

    /**
     * @param content 加密内容
     * @return
     */
    public static String encrypt(String content) {
        try {
            if("0".equals(encryptionMobileSwitch) ||   content.startsWith(ConstantValue.ENCRYPT_PREFIX)){
                return content;
            }
            Cipher cipher = encryptCiphers.get();
            byte[] data = content.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = cipher.doFinal(data);
            String encryptStr = Base64.encode(encrypted);
            return ConstantValue.ENCRYPT_PREFIX+encryptStr;
        } catch (Exception e) {
            log.error("encrypt fail! str = {}", content, e);
            return content;
        }
    }
    /**
     * @param base64Encrypted 解密内容(base64编码格式)
     * @return java.lang.String
     */
    public static String decrypt(String base64Encrypted) {
        try {
            if("0".equals(encryptionMobileSwitch) ||  !base64Encrypted.startsWith(ConstantValue.ENCRYPT_PREFIX)){
                return base64Encrypted;
            }
            Cipher cipher = decryptCiphers.get();
            //截取加密后的密文
            String newBase64Encrypted = base64Encrypted.substring(4,base64Encrypted.length());
            byte[] content =  Base64.decode(newBase64Encrypted);
            byte[] encrypted = cipher.doFinal(content);
            String decryptMobile = new String(encrypted,StandardCharsets.UTF_8);
            if(null!=(decryptMobile)){
                return decryptMobile;
            }
        } catch (Exception e) {
            log.error("decrypt fail! str = {}", base64Encrypted, e);
            return base64Encrypted;
        }
        return base64Encrypted;
    }






}
