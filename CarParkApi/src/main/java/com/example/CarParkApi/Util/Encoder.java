package com.example.CarParkApi.Util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Encoder {

    public String encrypt(String data){
        String encrypt_data = applyMd5(data);
        encrypt_data = BCrypt.hashpw(encrypt_data,BCrypt.gensalt(11));
        return encrypt_data;
    }

    public boolean validateHash(String src,String des){
        return BCrypt.checkpw(src,des);
    }

    public String applyMd5(String data){
        try{
            MessageDigest o_messageDigest =MessageDigest.getInstance("MD5");
            byte[] arr_messageDigest = o_messageDigest.digest(data.getBytes());
            BigInteger no = new BigInteger(1, arr_messageDigest);

            // Convert message digest into hex value
            String str_hashText = no.toString(16);
            while (str_hashText.length() < 32) {
                str_hashText = "0" + str_hashText;
            }
            return str_hashText;

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            throw new RuntimeException("Error on create message Digest instance");
        }
    }
//
//    public String applyBcrypt(String data){
//        return BC
//    }
}
