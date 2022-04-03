import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class ConcurrencyExTwo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        ProofOfWork pof = new ProofOfWork(0, "new block", new BigInteger("7711990999998853575395644202399761444748041817389874403947722788198528885"));
        long startTime = System.currentTimeMillis();
        System.out.println(pof.proveWork());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
