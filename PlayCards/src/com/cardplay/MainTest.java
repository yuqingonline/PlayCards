package com.cardplay;

import java.util.Scanner;

public class MainTest {
	private static Scanner mIn;

	public static void main(String[] args) {
		GameManager gameMgr = new GameManager();
		gameMgr.initGame();
		
		mIn = new Scanner(System.in);
		int num = 1;
		long time = System.currentTimeMillis();
		while(true) {
			//System.out.println("按任意键继续发牌");
			//mIn.next();
			gameMgr.playGame();
			if(num++ % 1000 == 0) {
				System.out.println("耗时：" + (System.currentTimeMillis() - time) + "ms");
				gameMgr.random();
				mIn.next();
				time = System.currentTimeMillis();
			}
		}
	}
}
