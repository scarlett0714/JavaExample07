package tptty.example03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//자바 디버깅하는법
//디버깅할 해당 라인의 숫자옆에 마우스->Toggle Breakpoint선택->Debug선택(RunX)
//디버깅화면으로 전환
//Step Into(F5) : 메소드 안으로 진입해서 수행
//Step Over(F6) : 전체 함수를 실행하고 다음으로 넘어감
//Step Return(F7) : 들어간 메소드 나오기
//Resume(F8) : Breakpoint를 여러개 걸어둘 경우, 다음 Breakpoint로 넘어감
public class TestMain03 {

	public static void main(String[] args) {
		//파일위치는 반드시 프로젝트내에 생성 (srcX, pacakgeX)
		fileMerge("words1.txt", "words2.txt", "words.txt"); //파일을 합치는 메소드
		VocManager voc = new VocManager("홍길동");
		voc.makeVoc("words.txt");

	}

	//filename1 + filename2 = filename3
	public static void fileMerge(String filename1, String filename2, String filename3) {
		
//		Scanner file1 = null;
//		Scanner file2 = null;
//		PrintWriter outfile = null;
		
		//찾는 파일이 없을 수 있으므로, 예외 처리 필요 : FileNotFoundException
		try (
				//try()안쪽에 객체를 생성해주면, finally에서 따로 close할 필요가 없음
				Scanner file1 = new Scanner(new File(filename1));
				Scanner file2 = new Scanner(new File(filename2));
				PrintWriter outfile = new PrintWriter(new File(filename3));) {
			
//			file1 = new Scanner(new File(filename1)); //키보드로부터 입력받는 Scanner객체가 아니라 file을 읽어냄
//			file2 = new Scanner(new File(filename2));
//			outfile = new PrintWriter(new File(filename3)); //file을 가져와서 출력함
			
			fileWriter(file1, outfile); //메소드 호출 : file1을 outfile에 출력
			fileWriter(file2, outfile); //메소드 호출 : file2를 outfile에 출력
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		
//		finally { //예외 여부에 상관없이, file은 close필요 : finally문 이용
//			//try구문안에 변수를 선언하면, try구문내에서만 변수사용가능 -> finally에서는 변수 사용 불가능
//			//File은 반드시 close해주어야함 -> 변수선언만 밖에다가 하기
//			if(file1!=null)
//				file1.close();
//			if(file2!=null)
//				file2.close();
//			if(outfile!=null)
//				outfile.close();
//		}
		
	}

	public static void fileWriter(Scanner file1, PrintWriter outfile) {
		while(file1.hasNextLine()) { //파일에 읽어올 다음 라인이 있는가?
//			String str = file1.nextLine(); //file1에서 한 줄 읽음
//			outfile.println(str); //print계열 메소드 사용 : Console창이 아닌 파일에 출력
			
			outfile.println(file1.nextLine());
		}
		
	}

}
