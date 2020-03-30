package com.slam.dunk.day08.demo02.util;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:12
 * @Description:
 */
public class EncryptUtils {
    /**
     * @param strSrc  str need to be encrypted
     * @param encName algorithm
     * @return
     */
    private static String EncryptStr(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            // to HexString
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    /**
     * use the default encrypt algorithm to encrypt the str
     *
     * @param str
     * @return
     */
    public static String EncryptByMD5(String str) {
        return EncryptStr(str, "MD5");
    }

    /**
     * @param s
     * @return
     */
    public static String to_MD5(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param str
     * @return
     */
    public static String EncryptBySHA1(String str) {
        return EncryptStr(str, "SHA-1");
    }

    /**
     * @param str
     * @return
     */
    public static String EncryptBySHA256(String str) {
        return EncryptStr(str, "SHA-256");
    }

    /**
     * @param bts
     * @return
     */
    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * @param str
     * @param key
     * @return
     */
    public static String union(String str, String key) {
        int strLen = str.length();
        int keyLen = key.length();
        Character[] s = new Character[strLen + keyLen];

        boolean flag = true;
        int strIdx = 0;
        int keyIdx = 0;
        for (int i = 0; i < s.length; i++) {
            if (flag) {
                if (strIdx < strLen) {
                    s[i] = str.charAt(strIdx);
                    strIdx++;
                }
                if (keyIdx < keyLen) {
                    flag = false;
                }

            } else {
                if (keyIdx < keyLen) {
                    s[i] = key.charAt(keyIdx);
                    keyIdx++;
                }
                if (strIdx < strLen) {
                    flag = true;
                }

            }
        }
        return StringUtils.join(s);
    }

    /**
     * @param str
     * @param key
     * @return
     */
    public static String encrypt(String str, String key) {

        if (str == null || str.length() == 0 || StringUtils.isBlank(key)) {
            return encrypt(str);
        }

        return encrypt(union(str, key));

    }


    /**
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        String encryptStr = EncryptByMD5(str);
        if (encryptStr != null) {
            encryptStr = encryptStr + encryptStr.charAt(0) + encryptStr.charAt(2) + encryptStr.charAt(4);
            encryptStr = EncryptByMD5(encryptStr);
        }
        return encryptStr;
    }
}
