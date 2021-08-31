package service.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
    public static String encryptPassword(String password, String salt){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(salt);
        stringBuilder.append(password);

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            final byte[] hash = messageDigest.digest(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptedPassword = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                encryptedPassword.append(String.valueOf(hash[i]));
            }
            return encryptedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        LoginService service = new LoginService();

        String pass = "test";
        String salt = "pGstHcXF7lEHqjbNoeTSdpIR1mgFblLkpWo9T3Tl7RlsbbcOUc5MrTTg2M2MdhAH";
        String genSalt = service.generateRandomSalt();
        String memRes = "60638295-11-46-4121-101-102-117-65-11546-57-80";

        String res = PasswordEncryptor.encryptPassword(pass, salt);
        System.out.println("pass: " + pass);
        System.out.println("salt: " + salt);
        System.out.println("result: " + res);
        System.out.println(memRes.equals(res));
        System.out.println("salt length: " + salt.length());
        System.out.println(genSalt);
    }


}
