package Oyun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static ArrayList<Integer> oyuncuKonumları=new ArrayList<Integer>();
	static ArrayList<Integer> pcKonumları=new ArrayList<Integer>();

	public static void main(String[] args) {
		
		char[][] oyunTahtasi= {{'1','|','2','|','3'},
					{'-','-','-','-','-'},
					{'4','|','5','|','6'},
					{'-','-','-','-','-'},
					{'7','|','8','|','9'}};
		
	
	
		
		
		oyunTahtasiniYazdir(oyunTahtasi);
	
		
		
		
		while(true) {
			Scanner scr=new Scanner(System.in);
		System.out.println("Lutfen yerlestirmek istediginiz konumu giriniz");
		int konum=scr.nextInt();
		
		while(oyuncuKonumları.contains(konum)|| pcKonumları.contains(konum)) {
			System.out.println("Konum dolu! Bos bir konum seciniz!");
			konum=scr.nextInt();
		}
		sembolYerlestir(oyunTahtasi, konum, "oyuncu");
		
		String sonuc=kazananKontrolu();
		if(sonuc.length()>0) {
			System.out.println(sonuc);
			break;
		} 	
		
		
		
		Random r=new Random();
		int pcKonum=r.nextInt(9)+1;
		while(oyuncuKonumları.contains(pcKonum)|| pcKonumları.contains(pcKonum)) {
			
			pcKonum=r.nextInt(9)+1;
		}
		sembolYerlestir(oyunTahtasi, pcKonum, "pc");
		
		
		
		
		oyunTahtasiniYazdir(oyunTahtasi);
		
		if(sonuc.length()>0) {
			System.out.println(sonuc);
			break;
		}
		
	
		}
		
		
		
	}
	
	public static void oyunTahtasiniYazdir(char[][] dizi) {
		for(char[] row:dizi) {
			for(char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void sembolYerlestir(char[][] oyunTahtasi,int konum,String kullanici) {
		char sembol=' ';
		
		if(kullanici.equals("oyuncu")) {
			sembol='X';
			oyuncuKonumları.add(konum);
		}
		else if(kullanici.equals("pc")) {
			sembol='O';
			pcKonumları.add(konum);

		}
		

		switch(konum) {
		case 1: 
			oyunTahtasi[0][0]=sembol;
			break;
		case 2: 
			oyunTahtasi[0][2]=sembol;
			break;
		case 3: 
			oyunTahtasi[0][4]=sembol;
			break;
		case 4: 
			oyunTahtasi[2][0]=sembol;
			break;
		case 5: 
			oyunTahtasi[2][2]=sembol;
			break;
		case 6: 
			oyunTahtasi[2][4]=sembol;
			break;
		case 7: 
			oyunTahtasi[4][0]=sembol;
			break;
		case 8: 
			oyunTahtasi[4][2]=sembol;
			break;
		case 9: 
			oyunTahtasi[4][4]=sembol;
			break;
			default:
				break;
		}
		
	}
	
	public static String kazananKontrolu() {
		
		List ustSatır=Arrays.asList(1,2,3);
		List ortaSatır=Arrays.asList(4,5,6);
		List altSatır=Arrays.asList(7,8,9);
		
		List sagSutun=Arrays.asList(1,4,7);
		List ortaSutun=Arrays.asList(2,5,8);
		List solSutun=Arrays.asList(3,6,9);
		
		List carpraz1=Arrays.asList(1,5,9);
		List carpraz2=Arrays.asList(7,5,3);
		
		List<List> kazanmaYolları=new ArrayList<List>();
		kazanmaYolları.add(ustSatır);
		kazanmaYolları.add(ortaSatır);
		kazanmaYolları.add(altSatır);
		kazanmaYolları.add(sagSutun);
		kazanmaYolları.add(ortaSutun);
		kazanmaYolları.add(solSutun);
		kazanmaYolları.add(carpraz1);
		kazanmaYolları.add(carpraz2);
		
		
		
		
		if(oyuncuKonumları.size()+pcKonumları.size()==9) {
			return "Berabere";
		}
		for(List a:kazanmaYolları) {
			if(oyuncuKonumları.containsAll(a)) {
				return "Tebrikler kazandiniz!";
				
			}
			else if(pcKonumları.containsAll(a)) {
				return "Kaybettiniz! Bilgisayar kazandi!";
				
			}
			
		}
		
		
		return "";
	}
	
}
