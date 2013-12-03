package utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created with IntelliJ IDEA.
 * User: dragos.serghie
 */
public class EncryptionUtil {

    private static final byte[] KEY = "ADBSJHJS12547896".getBytes();
    private static Key aesKey;
    private static Cipher cipher;
    private static byte[] textBytes, decrypted, decoded;

    /**
     * Order for decrypt: getBytes, decode, decrypt, toString
     *
     * @param text
     * @return
     */
    public static String decrypt(String text) {

        try {
            // Create key and cipher
            aesKey = new SecretKeySpec(KEY, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);

            // get bytes
            textBytes = text.getBytes();

            //decode
            decoded = new Base64().decode(textBytes);

            // decrypt
            decrypted = cipher.doFinal(decoded);
        } catch (Exception e) {
            return null;
        }
        // return as String
        return new String(decrypted);
    }
}
