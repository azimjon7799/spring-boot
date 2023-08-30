import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

public class OTPGenerator {

    private static final int OTP_LENGTH = 6;
    private static final Duration TOTP_PERIOD = Duration.ofSeconds(30);

    public static String generateOTP(String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        TimeBasedOneTimePasswordGenerator totpGenerator = new TimeBasedOneTimePasswordGenerator(TOTP_PERIOD, OTP_LENGTH, HashFunction.SHA1);
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        totpGenerator.setSecretKey(ByteBuffer.wrap(keyBytes));

        Instant now = Instant.now();
        long counter = now.getEpochSecond() / TOTP_PERIOD.getSeconds();

        return String.format("%06d", totpGenerator.generateOneTimePassword(counter));
    }
}
