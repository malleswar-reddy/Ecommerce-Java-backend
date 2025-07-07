package com.ecommerce.userservice.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component  // Optional
public class JwtKeyGenerator {
        public static void main(String[] args) {
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 256-bit key
            String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
            System.out.println("Your JWT secret key:\n" + base64Key);
        }
    }

