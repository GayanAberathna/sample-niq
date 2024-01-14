package lk.sample.application.security;

import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

public class CustomPasswordEncoder implements PasswordEncoder {
    @SneakyThrows
    @Override
    public String encode(CharSequence charSequence) {
        return encript(charSequence.toString());
    }

    @SneakyThrows
    @Override
    public boolean matches(CharSequence charSequence, String passwordInDatabase) {
        return encript(charSequence.toString()).equalsIgnoreCase(passwordInDatabase);
    }

    public static String encript(String passwrd) throws Exception {
        String name = "name";
        String passwd = passwrd;
        String salts = "07,15,44,83,76";
        String salttmp[] = salts.split(",");
        byte salt[] = new byte[salttmp.length];
        for (int i = 0; i < salt.length; i++) {
            salt[i] = Byte.parseByte(salttmp[i]);
        }
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(salt);
        m.update(passwd.getBytes("UTF8"));
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
                    .substring(6);
        }
        return result;
    }
}
