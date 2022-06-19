package Questions.code_5;

import Questions.code_1.AOE;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class Hash {
    private MessageDigest hash;

    public Hash(String algo) {
        try {
            hash = MessageDigest.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String input) {
        return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
    }

    public static void main(String[] args) {

        for (String str : Security.getAlgorithms("MessageDigest")) {
            System.out.println(str);
        }
        String algo = "SHA-256";
        Hash hash = new Hash(algo);
        String input = "ZimingGongGoogle";
        System.out.println(hash.hashCode(input));
    }
}
