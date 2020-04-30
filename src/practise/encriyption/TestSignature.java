/**
 * 
 */
package practise.encriyption;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月25日 下午11:23:18
* Description:
*/
public class TestSignature {

	public static void main(String[] args) throws Exception{
		KeyPairGenerator token = KeyPairGenerator.getInstance("RSA");
		token.initialize(1024);
		KeyPair kp = token.generateKeyPair();
		PrivateKey sk = kp.getPrivate();
		PublicKey pk = kp.getPublic();
		
		byte[] message = "Hello, I am Yimin".getBytes(StandardCharsets.UTF_8);
		
		// use private key
		Signature s = Signature.getInstance("SHA1withRSA");
		s.initSign(sk);
		s.update(message);
		byte[] signed = s.sign();
		System.out.println(String.format("signature: %x", new BigInteger(1, signed)));
		
		// validate with public key
		Signature v = Signature.getInstance("SHA1withRSA");
		v.initVerify(pk);
		v.update(message);
		
		boolean valid = v.verify(signed);
		System.out.println("Valid ? : " + valid);
	}
}
