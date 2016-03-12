package Security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


/**
 *
 * @author Phillix
 */
public class UserSecurity {

    private static final boolean DEBUG = sWave.Server.debugging;

    /**
     *
     * @param   password
     * @return = String combining hashed password and salt 
     */
    public String hash(char[] password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[24];
            random.nextBytes(salt);

            byte[] hash = pbkdf2(password, salt);

            return toHex(salt) + "$" +  toHex(hash);
        }
        catch(Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
        return "nope";
    }
    /**
     * 
     * @param password     = array of user entered password to check
     * @param correctHash  = String of the stored database hashed password
     * @return             = true : passwords match, false : no match
     */
    public boolean checkPassword(char[] password, String correctHash){
        String[] splitHash = correctHash.split("\\$");
        byte[] salt = fromHex(splitHash[0]);
        String hash = splitHash[1];

        try {
            byte[] compareHash = pbkdf2(password, salt);
            return hash.equals(toHex(compareHash));
        }
        catch(Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
        return false;
    }

    /**
     * 
     * @param  = password
     * @param  = salt
     * @return = hashed password
     */
    private byte[] pbkdf2(char[] password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, 124000, 192);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return skf.generateSecret(spec).getEncoded();
        }
        catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            if (DEBUG)
                e.printStackTrace();
        }
        return null;
    }

    //http://stackoverflow.com/questions/25697151/what-is-the-purpose-to-add-padding-to-hex
    private String toHex(byte[] array) {
        StringBuilder s = new StringBuilder();
        for (byte b : array)
            s.append(String.format("%02x", b));
        return s.toString();
    }
    
    //https://crackstation.net/hashing-security.htm#salt
    private byte[] fromHex(String hex){
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
            binary[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        return binary;
    }
}
