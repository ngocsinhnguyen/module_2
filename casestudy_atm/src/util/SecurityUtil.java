package util;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtil {

    // Mã hóa password hoặc PIN
    public static String hash(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt());
    }

    // Kiểm tra input có trùng hash hay không
    public static boolean verify(String input, String hash) {
        if (hash == null || hash.isEmpty()) return false;
        return BCrypt.checkpw(input, hash);
    }
}
