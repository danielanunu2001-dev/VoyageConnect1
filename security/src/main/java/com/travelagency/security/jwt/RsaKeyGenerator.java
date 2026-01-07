package com.travelagency.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class RsaKeyGenerator {

    private static final String KEYS_DIRECTORY = "target/keys";
    private static final String PUBLIC_KEY_FILE = KEYS_DIRECTORY + "/app.rsa.pub";
    private static final String PRIVATE_KEY_FILE = KEYS_DIRECTORY + "/app.rsa";

    @Value("${jwt.keystore.password}")
    private String keystorePassword;

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() {
        try {
            File keysDir = new File(KEYS_DIRECTORY);
            if (!keysDir.exists()) {
                keysDir.mkdirs();
            }

            File publicKeyFile = new File(PUBLIC_KEY_FILE);
            if (!publicKeyFile.exists()) {
                generateAndSaveKeys();
            } else {
                loadKeys();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize RSA keys", e);
        }
    }

    private void generateAndSaveKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();

        try (FileOutputStream fos = new FileOutputStream(PUBLIC_KEY_FILE)) {
            fos.write(publicKey.getEncoded());
        }

        // Encrypt and save private key
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKey secretKey = new SecretKeySpec(keystorePassword.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();
        byte[] encryptedPrivateKey = cipher.doFinal(privateKey.getEncoded());

        try (FileOutputStream fos = new FileOutputStream(PRIVATE_KEY_FILE)) {
            fos.write(iv);
            fos.write(encryptedPrivateKey);
        }
    }

    private void loadKeys() throws Exception {
        byte[] publicKeyBytes = Files.readAllBytes(new File(PUBLIC_KEY_FILE).toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

        // Load and decrypt private key
        byte[] privateKeyFileBytes = Files.readAllBytes(new File(PRIVATE_KEY_FILE).toPath());
        byte[] iv = new byte[12];
        System.arraycopy(privateKeyFileBytes, 0, iv, 0, 12);
        byte[] encryptedPrivateKey = new byte[privateKeyFileBytes.length - 12];
        System.arraycopy(privateKeyFileBytes, 12, encryptedPrivateKey, 0, encryptedPrivateKey.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKey secretKey = new SecretKeySpec(keystorePassword.getBytes(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] decryptedPrivateKey = cipher.doFinal(encryptedPrivateKey);

        this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decryptedPrivateKey));
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }
}
