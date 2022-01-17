package tptty.example03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VocManager {
	private String userName;
	ArrayList<Word> list = new ArrayList<>();
	Word[] voc;
	
	
	static Scanner scan = new Scanner(System.in);

	VocManager(String userName) {
		super();
		this.userName = userName;
	}
	
	
	
	void addWord(Word word) {
		list.add(word);
	}
	
	void makeVoc(String fileName) { //VocManager객체를 만들고나서 입력된 파일을 읽어다가 단어 각각 word객체 만들어서 addWord호출해서 배열에 저장
		
		try(Scanner scan = new Scanner(new File(fileName))) { //일반예외이므로 반드시 예외처리
			while(scan.hasNextLine()) { //scan에서 다음에 읽어낼 것이 있는 동안에
				String str = scan.nextLine(); //words파일에서 한줄 읽어와서 str에 저장 (영어, 뜻 모두포함)
				String[] temp = str.split("\t"); //split으로 구분된 문자열은 모두 배열로 반환됨->temp에는 영어, 뜻 2개의 데이터가 저장됨
				this.addWord(new Word(temp[0].trim(), temp[1].trim())); //혹시모를 단어의 앞뒤 공백 제거
				
			}
			voc = list.toArray(new Word[list.size()]);
			System.out.println(userName+"의 단어장이 생성되었습니다.");
			this.menu(); //단어를 찾아야하니 메뉴 호출
		}catch(FileNotFoundException e) {
			System.out.println(userName+"의 단어장이 생성되지 않았습니다. 파일명을 확인하세요.");
		}
	}
	
	void menu() {
		int choice = 0;
		while(choice != 3) {
			System.out.println("\n------"+userName+"의 단어장 ------");
			System.out.println("1) 단어검색 2) 단어검색2 3) 종료");
			System.out.print("메뉴를 선택하세요 : ");
			choice = scan.nextInt();
			scan.nextLine(); //이상한 문자열 들어오면 읽어서 없애기
			System.out.println();
			
			switch (choice) {
			case 1:
				searchVoc(); //단어검색메소드 호출
				break;
			case 2:
				searchVoc2();
				break;
			case 3: 
				System.out.println(userName+"의 단어장 프로그램을 종료합니다.");
				break;
			}
		}
		
	}

	

	public void searchVoc() {
		System.out.println("------단어 검색------");
		System.out.print("검색할 단어를 입력하세요 (영단어) : ");
		String sWord = scan.nextLine();
		sWord = sWord.trim(); //입력한 단어에 있을 공백제외
		for(Word word : voc) { //배열에 있는 단어들과 입력받은 단어비교
			if(word!=null) { //등록된 단어가 있을 경우
				if(word.eng.equals(sWord)) { //equals : 문자열 비교
					System.out.println("단어의 뜻 : "+word.kor);
					break;
				}
			}else {
				System.out.println("단어장에 등록되어 있지 않습니다.");
				break;
			}
		}
		
	}
	
	public void searchVoc2() { //완벽한 단어스페링을 모르고 일부분만 아는경우
		System.out.println("------단어 검색------");
		System.out.print("검색할 부분단어를 입력하세요 (영단어) : ");
		String sWord = scan.nextLine();
		sWord = sWord.trim(); //입력한 단어에 있을 공백제외
		for(Word word : voc) { //배열에 있는 단어들과 입력받은 단어비교
			if(word!=null) { //등록된 단어가 있을 경우
				if(word.eng.contains(sWord)) { //contains : 입력된 문자열을 포함하고 있는가?
					System.out.println(word); //포함된 단어가 여러개있을 수 있으므로 바로 break;(x)
				}
			}else {
				break;
			}
		}
		
	}
	
}
