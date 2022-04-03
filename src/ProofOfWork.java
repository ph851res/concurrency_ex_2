import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ProofOfWork {
    int nonce;
    String block;
    BigInteger target;
    public ProofOfWork(int nonce, String block, BigInteger target) {
        this.nonce = nonce;
        this.block = block;
        this.target = target;
    }

    public int proveWork() throws NoSuchAlgorithmException{
        int nonceInner = this.nonce;
        while (true) {
            boolean result = checkHash(this.block, nonceInner, this.target);
            if (result==true) {
                break;
            } else nonceInner++;
        }
        return nonceInner;
    }

    public boolean checkHash(String block, int nonce, BigInteger target) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(digest.digest(block.concat(Integer.toString(nonce)).getBytes(StandardCharsets.UTF_8)));
        BigInteger number = new BigInteger(1, hash);
        if (number.compareTo(target) == -1) {
            return true;
        } else return false;
    }

}
