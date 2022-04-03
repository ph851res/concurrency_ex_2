import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ProofOfWork {
    int nonce;
    String block;
    BigInteger target;
    int winnerNonce;
    public ProofOfWork(int nonce, String block, BigInteger target) {
        this.nonce = nonce;
        this.block = block;
        this.target = target;
        this.winnerNonce = -1;
    }

    public int proveWork() throws NoSuchAlgorithmException{
        int nonceInner = this.nonce;
        while (true) {
            new Thread(new Runnable() {
                private String block;
                private BigInteger target;
                private int nonceInner;
                public Runnable init(String block, BigInteger target, int nonceInner) {
                    this.block = block;
                    this.target = target;
                    this.nonceInner = nonceInner;
                    return this;
                }
                @Override
                public void run() {
                    try {
                        checkHash(this.block, nonceInner, this.target);
                        //System.out.println(nonceInner);
                    }
                    catch (NoSuchAlgorithmException e) {
                        System.out.println("NoSuchAlgorithmException");
                    }
                }
            }.init(this.block, this.target, nonceInner)).start();
            if (winnerNonce != -1) {
                break;
            } else nonceInner++;
        }
        return winnerNonce;
    }

    public void checkHash(String block, int nonce, BigInteger target) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(digest.digest(block.concat(Integer.toString(nonce)).getBytes(StandardCharsets.UTF_8)));
        BigInteger number = new BigInteger(1, hash);
        System.out.println(number);
        if (number.compareTo(target) == -1) {
            System.out.println("hello");
            this.winnerNonce = nonce;
        }
    }

}
