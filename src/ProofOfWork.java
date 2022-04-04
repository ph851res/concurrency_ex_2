import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicLong;


public class ProofOfWork {
    AtomicLong winnerNonce;

    public ProofOfWork() {
        this.winnerNonce = new AtomicLong(-1);;
    }
    public long proveWork(String block, BigInteger target) {
        int currentNonce = 0;
        while (winnerNonce.get() == -1) {
            new Thread(new Runnable() {
                private String block;
                private BigInteger target;
                private int nonceInner;
                MessageDigest digest;
                public Runnable init(String block, BigInteger target, int nonceInner) {
                    this.block = block;
                    this.target = target;
                    this.nonceInner = nonceInner;
                    try {
                        this.digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {

                    }
                    return this;
                }
                @Override
                public void run() {
                    try {
                        checkHash(this.block, nonceInner, this.target, this.digest);
                    }
                    catch (NoSuchAlgorithmException e) {
                        System.out.println("NoSuchAlgorithmException");
                    }
                }
            }.init (block, target, currentNonce)).start();
            currentNonce++;
        }
        return winnerNonce.get();
    }

    public void checkHash(String block, int nonce, BigInteger target, MessageDigest digest) throws NoSuchAlgorithmException {
        byte[] hash = digest.digest(digest.digest(block.concat(Integer.toString(nonce)).getBytes(StandardCharsets.UTF_8)));
        BigInteger number = new BigInteger(1, hash);
        System.out.println(number);
        if (number.compareTo(target) == -1) {
            System.out.println("in here");
            this.winnerNonce.set(nonce);
        }
    }

}
