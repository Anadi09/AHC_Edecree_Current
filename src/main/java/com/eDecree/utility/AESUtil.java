package com.eDecree.utility;


	import javax.crypto.Cipher;
	import javax.crypto.IllegalBlockSizeException;
	import javax.crypto.NoSuchPaddingException;
	import javax.crypto.SecretKey;
	import javax.crypto.BadPaddingException;
	import javax.crypto.KeyGenerator;
	import javax.crypto.SecretKeyFactory;
	import javax.crypto.SealedObject;
	import javax.crypto.spec.IvParameterSpec;
	import javax.crypto.spec.PBEKeySpec;
	import javax.crypto.spec.SecretKeySpec;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.Serializable;
	import java.security.InvalidAlgorithmParameterException;
	import java.security.InvalidKeyException;
	import java.security.NoSuchAlgorithmException;
	import java.security.SecureRandom;
	import java.security.spec.InvalidKeySpecException;
	import java.security.spec.KeySpec;
	import java.util.Base64;

	public class AESUtil {

	    public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv)
	        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance(algorithm);
	        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	        byte[] cipherText = cipher.doFinal(input.getBytes());
	        return Base64.getEncoder()
	            .encodeToString(cipherText);
	    }

	    public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv)
	        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance(algorithm);
	        cipher.init(Cipher.DECRYPT_MODE, key, iv);
	        byte[] plainText = cipher.doFinal(Base64.getDecoder()
	            .decode(cipherText));
	        return new String(plainText);
	    }

	    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
	        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	        keyGenerator.init(n);
	        SecretKey key = keyGenerator.generateKey();
	        return key;
	    }

	    public static SecretKey getKeyFromPassword(String password, String salt)
	        throws NoSuchAlgorithmException, InvalidKeySpecException {
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
	            .getEncoded(), "AES");
	        return secret;
	    }

	    public static IvParameterSpec generateIv() {
	        byte[] iv = new byte[16];
	        new SecureRandom().nextBytes(iv);
	        return new IvParameterSpec(iv);
	    }

	    public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
	        File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
	        NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
	        BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance(algorithm);
	        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	        FileInputStream inputStream = new FileInputStream(inputFile);
	        FileOutputStream outputStream = new FileOutputStream(outputFile);
	        byte[] buffer = new byte[64];
	        int bytesRead;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            byte[] output = cipher.update(buffer, 0, bytesRead);
	            if (output != null) {
	                outputStream.write(output);
	            }
	        }
	        byte[] outputBytes = cipher.doFinal();
	        if (outputBytes != null) {
	            outputStream.write(outputBytes);
	        }
	        inputStream.close();
	        outputStream.close();
	    }

	    public static void decryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
	        File encryptedFile, File decryptedFile) throws IOException, NoSuchPaddingException,
	        NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
	        BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance(algorithm);
	        cipher.init(Cipher.DECRYPT_MODE, key, iv);
	        FileInputStream inputStream = new FileInputStream(encryptedFile);
	        FileOutputStream outputStream = new FileOutputStream(decryptedFile);
	        byte[] buffer = new byte[64];
	        int bytesRead;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            byte[] output = cipher.update(buffer, 0, bytesRead);
	            if (output != null) {
	                outputStream.write(output);
	            }
	        }
	        byte[] output = cipher.doFinal();
	        if (output != null) {
	            outputStream.write(output);
	        }
	        inputStream.close();
	        outputStream.close();
	    }

	    public static SealedObject encryptObject(String algorithm, Serializable object, SecretKey key,
	        IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
	        InvalidAlgorithmParameterException, InvalidKeyException, IOException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance(algorithm);
	        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	        SealedObject sealedObject = new SealedObject(object, cipher);
	        return sealedObject;
	    }

	    public static Serializable decryptObject(String algorithm, SealedObject sealedObject, SecretKey key,
	        IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
	        InvalidAlgorithmParameterException, InvalidKeyException, ClassNotFoundException,
	        BadPaddingException, IllegalBlockSizeException, IOException {
	        Cipher cipher = Cipher.getInstance(algorithm);
	        cipher.init(Cipher.DECRYPT_MODE, key, iv);
	        Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
	        return unsealObject;
	    }

	    public static String encryptPasswordBased(String plainText, SecretKey key, IvParameterSpec iv)
	        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	        return Base64.getEncoder()
	            .encodeToString(cipher.doFinal(plainText.getBytes()));
	    }

	    public static String decryptPasswordBased(String cipherText, SecretKey key, IvParameterSpec iv)
	        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, key, iv);
	        return new String(cipher.doFinal(Base64.getDecoder()
	            .decode(cipherText)));
	    }
	    
	    
	    public static void main(String [] args)  throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException,
        InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        // given
        String plainText = "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";
        String password = "test@123";
        String salt = "0";
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        SecretKey key = AESUtil.getKeyFromPassword(password, salt);

        // when
        String cipherText = AESUtil.encryptPasswordBased(plainText, key, ivParameterSpec);
        String decryptedCipherText = AESUtil.decryptPasswordBased(plainText, key, ivParameterSpec);

        // then
       // Assertions.assertEquals(plainText, decryptedCipherText);
    }

	}

