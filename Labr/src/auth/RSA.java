package auth;

public class RSA {
//	/**
//	 * ��ȡ˽Կ ����PrivateKey
//	 * 
//	 * @param path
//	 *            ����˽Կ��֤��·��
//	 * @param password
//	 *            ˽Կ֤������
//	 * @return ����˽ԿPrivateKey
//	 * @throws KeyStoreException
//	 * @throws NoSuchAlgorithmException
//	 * @throws CertificateException
//	 * @throws IOException
//	 * @throws UnrecoverableKeyException
//	 */
//	private static PrivateKey getPrivateKey(String path, String password)
//			throws KeyStoreException, NoSuchAlgorithmException,
//			CertificateException, IOException, UnrecoverableKeyException {
//		KeyStore ks = KeyStore.getInstance("PKCS12");
//		FileInputStream fis = new FileInputStream(path);
//		char[] nPassword = null;
//		if ((password == null) || password.trim().equals("")) {
//			nPassword = null;
//		} else {
//			nPassword = password.toCharArray();
//		}
//		ks.load(fis, nPassword);
//		fis.close();
//		Enumeration<String> en = ks.aliases();
//		String keyAlias = null;
//		if (en.hasMoreElements()) {
//			keyAlias = (String) en.nextElement();
//		}
//
//		return (PrivateKey) ks.getKey(keyAlias, nPassword);
//	}
//
//	/**
//	 * ˽Կǩ���� ǩ���������£�BASE64(RSA(MD5(src),privatekey))������srcΪ��Ҫǩ�����ַ�����
//	 * privatekey���̻���CFCA֤��˽Կ��
//	 * 
//	 * @param plainText
//	 *            ��ǩ���ַ���
//	 * @param path
//	 *            ǩ��˽Կ·��
//	 * @param password
//	 *            ǩ��˽Կ����
//	 * @return ����ǩ������ַ���
//	 * @throws Exception
//	 */
//	public static String sign(String plainText, String path, String password)
//			throws Exception {
//		/*
//		 * MD5����
//		 */
//		MessageDigest md5 = MessageDigest.getInstance("MD5");
//		md5.update(plainText.getBytes("utf-8"));
//		byte[] digestBytes = md5.digest();
//		/*
//		 * ��˽Կ����ǩ�� RSA Cipher������ɼ��ܻ���ܹ���������RSA
//		 */
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		// ENCRYPT_MODE��ʾΪ����ģʽ
//		cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(path, password));
//		// ����
//		byte[] rsaBytes = cipher.doFinal(digestBytes);
//		// Base64����
//		//return new sun.misc.BASE64Encoder().byteArrayToBase64(rsaBytes);
//		
//	}
//
//	/**
//	 * ���ݹ�Կn��e���ɹ�Կ
//	 * 
//	 * @param modulus
//	 *            ��Կn��
//	 * @param publicExponent
//	 *            ��Կe��
//	 * @return ���ع�ԿPublicKey
//	 * @throws Exception
//	 */
//	public static PublicKey getPublickKey(String modulus, String publicExponent)
//			throws Exception {
//		KeySpec publicKeySpec = new RSAPublicKeySpec(
//				new BigInteger(modulus, 16), new BigInteger(publicExponent, 16));
//		KeyFactory factory = KeyFactory.getInstance("RSA");
//		PublicKey publicKey = factory.generatePublic(publicKeySpec);
//		return publicKey;
//	}
//
//	/**
//	 * �ù�Կ֤�������ǩ
//	 * 
//	 * @param message
//	 *            ǩ��֮ǰ��ԭ��
//	 * @param cipherText
//	 *            ǩ��
//	 * @param pubKeyn
//	 *            ��Կn��
//	 * @param pubKeye
//	 *            ��Կe��
//	 * @return boolean ��ǩ�ɹ�Ϊtrue,ʧ��Ϊfalse
//	 * @throws Exception
//	 */
//	public static boolean verify(String message, String cipherText,
//			String pubKeyn, String pubKeye) throws Exception {
//		Cipher c4 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		// ������Կ����Cipher������г�ʼ��,DECRYPT_MODE��ʾ����ģʽ
//		c4.init(Cipher.DECRYPT_MODE, getPublickKey(pubKeyn, pubKeye));
//		// ����
//		byte[] desDecTextBytes = c4.doFinal(Base64
//				.base64ToByteArray(cipherText));
//		// �õ�ǰ�ö�ԭ�Ľ��е�MD5
//		String md5Digest1 = Base64.byteArrayToBase64(desDecTextBytes);
//		MessageDigest md5 = MessageDigest.getInstance("MD5");
//		md5.update(message.getBytes("utf-8"));
//		byte[] digestBytes = md5.digest();
//		// �õ��̻���ԭ�Ľ��е�MD5
//		String md5Digest2 = Base64.byteArrayToBase64(digestBytes);
//		// ��֤ǩ��
//		if (md5Digest1.equals(md5Digest2)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * ��ȡ��Կcer
//	 * 
//	 * @param path
//	 *            .cer�ļ���·�� �磺c:/abc.cer
//	 * @return base64��Ĺ�Կ��
//	 * @throws IOException
//	 * @throws CertificateException
//	 */
//	public static String getPublicKey(String path) throws IOException,
//			CertificateException {
//		InputStream inStream = new FileInputStream(path);
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		int ch;
//		String res = "";
//		while ((ch = inStream.read()) != -1) {
//			out.write(ch);
//		}
//		byte[] result = out.toByteArray();
//		res = Base64.byteArrayToBase64(result);
//		return res;
//	}
//
}
