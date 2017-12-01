package net.unit8.genid;

import java.security.SecureRandom;

public class JettySessionId {
    private SecureRandom secureRandom = new SecureRandom();

    public String generateSessionId() {
        long r0 = secureRandom.nextLong();
        long r1 = secureRandom.nextLong();
        return Long.toString(r0,36)+Long.toString(r1,36);

    }
}
