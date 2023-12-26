package com.aijiahao.blog.server.oauth2;

import com.aijiahao.blog.common.exception.RRException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author aijiahao
 * @create 2023/12/13  22:32
 * @description
 */
public class TokenGenerator {
    
    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }
    
    private static final char[] hexCode = "0123456789abcdef".toCharArray();
    
    public static String toHexString(byte[] data) {
        if(data == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(data.length * 2);
        for(byte b : data) {
            sb.append(hexCode[(b >> 4) & 0xF]);
            sb.append(hexCode[(b & 0xF)]);
        }
        return sb.toString();
    }
    
    
    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new RRException("生成Token失败", e);
        }
    }
}
