package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	public static void main(String [] args) {
		
		// XML (eXtensible Markup Language) : 단순화된 데이터 기술 형식 		
		
		// XML에 저장되는 데이터 형식은 Key : Value 형식(Map)이다.
		// Key, Value 모두 String(문자열) 형식
		// Map<String, String> 
		
		// XML 파일을 읽고, 쓰기 위한 IO 관련 클래스 필요
		
		// * Properties 컬렉션 객체 *
		// - Map의 후손 클래스
		// - Key, Value 모두 String(문자열)형식
		// - XML 파일을 읽고, 쓰는데 특화된 메서드를 제공
		
		
	
		try {
			Scanner sc = new Scanner(System.in);
			
			// 1. Properties 객체 생성
			Properties prop = new Properties();
			
			System.out.print("생성할 파일 이름 : ");
			String fileName = sc.nextLine();
			
			// 2.FileOutputStream 생성  (입력한 명칭으로 파일을 내보내겠다....)
			// 파일명.xml
			FileOutputStream fos = new FileOutputStream( fileName + ".xml" );  // 오류확인 후 SURROUND with try-catch 해주기
			
			// 3. Properties 객체를 이용해서 XML 파일 생성
			prop.storeToXML(fos, fileName + ".xml file");
			
			// 4. 파일 잘 만들어졌는지 확인
			System.out.println(fileName + ".xml 파일 생성 완료");
						
		} catch (IOException e) {
			// 그냥 Exception 해도 되고 조상인 IOException해도 상관없음
			e.printStackTrace();
		}
		
	}
}
