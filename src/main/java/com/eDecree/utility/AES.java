package com.eDecree.utility;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AES {
	 private SecretKey key;
	    private final int KEY_SIZE = 128;
	    private final int DATA_LENGTH = 128;
	    private Cipher encryptionCipher;

	    public void init() throws Exception {
	        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	        keyGenerator.init(KEY_SIZE);
	        key = keyGenerator.generateKey();
	    }
	    
	    
	    public String encrypt(String data) throws Exception {
	    	
	        byte[] dataInBytes = data.getBytes();
	        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
	        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
	        return encode(encryptedBytes);
	    }
	    
	    public String decrypt(String encryptedData) throws Exception {
	        byte[] dataInBytes = decode(encryptedData);
	        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
	        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
	        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
	        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
	        return new String(decryptedBytes);
	    }
	    
	    
	    public static SecretKey getKeyFromPassword(String password, String salt)
	    	    throws NoSuchAlgorithmException, InvalidKeySpecException {
	    	    
	    	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	    	    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
	    	    SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
	    	        .getEncoded(), "AES");
	    	    return secret;
	    	}
	    
	    
	    private String encode(byte[] data) {
	        return Base64.getEncoder().encodeToString(data);
	    }

	    private byte[] decode(String data) {
	        return Base64.getDecoder().decode(data);
	    }
	    
	    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, 
	    IllegalBlockSizeException, InvalidKeyException, BadPaddingException, 
	    InvalidAlgorithmParameterException, NoSuchPaddingException {
	    
	    String plainText = "www.baeldung.com";
	    String password = "baeldung";
	    String salt = "12345678";
	    IvParameterSpec ivParameterSpec = AESUtil.generateIv();
	    SecretKey key = AESUtil.getKeyFromPassword(password,salt);
	    String cipherText = AESUtil.encryptPasswordBased(plainText, key, ivParameterSpec);
	    String decryptedCipherText = AESUtil.decryptPasswordBased(
	      cipherText, key, ivParameterSpec);
	  //  Assertions.assertEquals(plainText, decryptedCipherText);
	}
	}

