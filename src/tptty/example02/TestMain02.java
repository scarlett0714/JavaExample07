package tptty.example02;

import java.util.InputMismatchException;
import java.util.Scanner;

//2. 사용자 정의 예외
@SuppressWarnings("serial")
class MenuRangeCheckException extends RuntimeException { // RuntimeException으로부터 속성을 상속받아 새로운 사용자 정의 예외 클래스 생성

	public MenuRangeCheckException(String message) { // 예외 발생한 메세지정보가 매개변수로 들어옴
		super(message); // 들어온 메세지를 RuntimeException에서 해결

	}

}

public class TestMain02 {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			try {
				System.out.print("1)아메리카노 2)카페라떼 3)결재 4)종료\n메뉴를 선택하세요: ");
				int choice = scan.nextInt();
				// 만약, 숫자가 아니라 문자를 입력한다면? 새로운 예외 발생 : InputMismatchException (입력한 타입이 맞지 않음)
				
				// 예외 처리 방법
				// 1. 조건문이용
				if (!(choice >= 1 && choice <= 4)) {
					System.out.println("메뉴 범위 체크 요힘");
					// 2. 사용자 정의 예외
					throw new MenuRangeCheckException("메뉴 범위 체크 요함");
				}
			}
			// 2-1. try-catch구문으로 사용자 정의 예외 처리
			catch (MenuRangeCheckException e) {
				System.out.println(e.getMessage());
			}
			
			catch (InputMismatchException e) { 
				System.out.println("숫자로 입력해 주세요.");
				scan.nextLine(); //S:입력버퍼에 있는 것을 모두 읽어오기
			} //P:종료되지 않고 계속 실행됨 (새로운 값을 입력받지 못함)
			//why?이미 입력버퍼에 숫자가 아닌게 입력된 상황->계속 이렇게 잘못된 값을 읽어내는 것을 반복
		}

	}

}