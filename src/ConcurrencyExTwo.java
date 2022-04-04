import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class ConcurrencyExTwo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        ProofOfWork pof = new ProofOfWork();
        long startTime = System.currentTimeMillis();
        System.out.println(pof.proveWork("new block", new BigInteger("711990999998853575395644202399761444748041817389874403947722788198528885")));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
