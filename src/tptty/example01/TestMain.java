package tptty.example01;

import java.util.Random;

public class TestMain {
	public static void method(String[] args) throws ClassNotFoundException { //Class.forName()에 대한 예외던지기
//		String str = args[0];
		//main에서 try-catch구문을 쓸 수도 있지만, method내에서 예외처리하는 것도 가능
		
		//thorws구문 사용예시
		Class clazz = Class.forName("java.lang.String2"); //String2라고하는 class는 없음
	}
	
	public static void main(String[] args) {
		// 예외 발생시키는 방법: throw
//		throw new Exception(); //일반 예외 : 예외처리를 하지 않으면 컴파일자체가 되지 않음
//		throw new RuntimeException(); //런타임 예외 : 예외처리를 하지 않아도 컴파일 가능, but 실행하면 프로그램 종료

		int num = 100;
		Random rand = new Random();
//		while (true) {
			try {
				//ArrayIndexOutOfBoundsException발생
//				String str = args[0]; //main함수의 매개변수인 args배열 -> 배열에 참조할 것이 없음, 빈 방
				method(args); //위 구문을 try구문에 직접 넣지 않고, method이용해서 호출
				//main에서 method호출한 부분에서 예외발생-> method의 빈 배열을 사용한 곳에서 예외발생
				
				int result = num / rand.nextInt(100); // 0~99까지의 난수로 num을 나눔
				if (result > 2)
					return; // finally구문을 수행한 뒤에 반복문을 벗어남 (break만나자마자 바로 while문을 벗어나지 않음)
				// 만약 break가 아니라 return구문으로 메소드를 종료한다면? 그래도 finally까지 실행되고 메소드 종료

				System.out.println(result);
				// 정상적으로 실행이 되다가 난수로 0이 발생할 경우, 분모에 0이 대입되므로 ArithmeticException발생
			}
			
			//다중 catch구문
			catch (ArithmeticException e) { // 위에서 발생한 ArithmeticException 예외처리 : try-catch구문
				// ArithmeticException이 발생하지 않으면, catch구문 건너뛰고 finally구문실행
				System.out.println("0으로 나눌 수 없습니다."); // ArithmeticException이 발생하면 실행될 코드
			}
			
			catch (ArrayIndexOutOfBoundsException e) {
				//ArrayIndexOutOfBoundsException 예외 처리코드
				System.out.println(e.getMessage());
				e.printStackTrace(); //Stack에 있는 것을 추적해서 Exception발생한 곳까지 추적
			}
			
			//method에서 throws로 던진 오류 예외처리
			//1. catch구문으로 예외처리
			//2. 또다시 throws : main에서 throws로 던졌으므로, JVM이 받아 프로그램을 비정상적으로 종료시킴
			catch (ClassNotFoundException e) {
				System.out.println("클래스를 찾을 수 없습니다.");
				e.printStackTrace();
			}
			
			//catch (Exception e){} : Exception은 모든 예외의 부모클래스이므로, 다른 catch예외처리구문보다 아래에 위치

			finally { // 예외 발생 여부, 메소드, 구문의 종료여부에 상관없이 항상 실행
				System.out.println("다음 연산을 수행합니다.");
			}
//		}
	}

}
