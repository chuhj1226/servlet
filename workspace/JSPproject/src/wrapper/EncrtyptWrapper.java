package wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

// HttpServletRequestWrapper를 상속받는 클래스로 작성
public class EncrtyptWrapper extends HttpServletRequestWrapper{

	// 반드시 명시적으로 HttpServletRequest를 매개변수로 하는 생성자를 작성해야 함
	public EncrtyptWrapper(HttpServletRequest request) {
		
		super(request);
	}
	
	// ServletRequestWrapper의 getParameter 메소드 오버라이딩
	@Override
	public String getParameter(String key) {
		String value = "";
				
		if(key != null && (key.equals("userPwd")||key.equals("newPwd"))) {
			// request 객체에 담긴 파라미터 키 값이 userPwd 또는 newPwd인 경우 암호화
			value = getSha512(super.getParameter(key));
		}else {
			// request 객체에 담긴 파라미터 키 값이 userPwd, newPwd가 아닌 경우 기존 값 그대로 사용
			value = super.getParameter(key);
		}
		
		return value;
		
	}
	// sha512 해쉬함수를 통한 처리
	public String getSha512(String userPwd) {
		
		String encPwd = null;
		
		try {
			// sha512 방식의 암호화 객체 
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			// 전달 받은 String 타입의 userPwd를 바이트 배열로 변경
			byte[] bytes = userPwd.getBytes(Charset.forName("UTF-8"));
			
			// md 객체에 userPwd 바이트 배열을 전달해서 update 호출
			md.update(bytes);
			
			// md.digest() -> 해쉬함수 처리 된 결과를 byte 배열로 리턴
			// 해당 바이트 배열을 다시 String 타입으로 인코딩
			encPwd = Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			// -> 매개변수로 전달한 알고리즘명이 틀렸을 경우
			e.printStackTrace();
		}

		return encPwd;
	}
}
