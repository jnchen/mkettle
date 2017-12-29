package com.jnchen.mkettle.utils.security;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by caojingchen on 2017/12/29.
 */
public class MD5Util {
        /**利用MD5进行加密
     　　* @param str  待加密的字符串
     　　* @return  加密后的字符串
     　　* @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     　　 * @throws UnsupportedEncodingException
     　　*/
        public static String encode(String str) {
            //确定计算方法
            String newstr = null;
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                BASE64Encoder base64en = new BASE64Encoder();
                //加密后的字符串
                newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return newstr;
        }
}
