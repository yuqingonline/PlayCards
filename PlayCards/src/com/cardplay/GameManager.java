package com.cardplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
	private List<Card> mCardList = new ArrayList<Card>();
	private List<Player> mPlayerList = new ArrayList<Player>();
	private static final int PLAYERCNT = 2;
	private static final int CARD_CNTIN_PLAYER = 3;
	private Random mRand = new Random(System.currentTimeMillis());
	
	public void initGame() {
		initCards();
		randomCards();
		initPlayers();
	}
	
	public void playGame() {
		dealCards();
//		compareCards();
		threeCardsRules();
	}
	
	public void random() {
		randomCards();
	}
	
	private void initCards() {
		System.out.println("生成一幅扑克牌：");
		for(int i = 0; i < Card.colorCnt(); i++) {
			for(int j = 0; j < Card.numberCnt(); j++) {
				Card tmpCard = new Card(i, j);
				System.out.print(tmpCard + ", ");
				mCardList.add(tmpCard);
			}
		}
		System.out.println();
	}
	
	private void randomCards() {
//		System.out.println("洗牌后的牌为：");
		Random rand = new Random(System.currentTimeMillis());
		int cardsCnt = mCardList.size();
		for(int i = 0; i < cardsCnt; i++) {
			int m = rand.nextInt(cardsCnt);
			int n = rand.nextInt(cardsCnt);
			Card tmpCard = mCardList.get(m);
			mCardList.set(m, mCardList.get(n));
			mCardList.set(n, tmpCard);
		}
		
//		for(Card card : mCardList) {
//			System.out.print(card + ", ");
//		}
		System.out.println();
	}
	
	private void initPlayers() {	
		for(int i = 0; i < PLAYERCNT; i++) {
			mPlayerList.add(new Player("wyq" + i));
		}
	}
	
	private void dealCards() {
		for(Player player : mPlayerList) {
			player.dropCard();
		}
		
		int cardCnt = mCardList.size();
		int index = mRand.nextInt(cardCnt);
		//System.out.println("为玩家发牌。。。。");
		for(int i = 0; i < CARD_CNTIN_PLAYER * PLAYERCNT; i++) {
			index %= cardCnt;
			mPlayerList.get(i % PLAYERCNT).addCard(mCardList.get(index));
//			System.out.print(mCardList.get(index) + ", ");
			index++;
		}
//		System.out.println();
	}
	
/*	private void compareCards() {
		List<Card> maxCards = new ArrayList<Card>();
		for(Player player : mPlayerList) {
			maxCards.add(Card.compareCard(player.getCard(0), player.getCard(1)));
		}
		
		if(Card.compare(maxCards.get(0), maxCards.get(1))) {
			System.out.println("玩家" + mPlayerList.get(0) + "获得胜利，获胜的牌为：" + maxCards.get(0));
		} else {
			System.out.println("玩家" + mPlayerList.get(1) + "获得胜利，获胜的牌为：" + maxCards.get(1));
		}
		
		for(Player player : mPlayerList) {
			System.out.println("玩家" + player + "的牌为：" + player.getCard(0) + ", " + player.getCard(1));
		}
	}*/

	private void sortCards() {
		for(Player player : mPlayerList) {
			for(int i = 0; i < CARD_CNTIN_PLAYER; i++) {
				for(int j = i + 1; j < CARD_CNTIN_PLAYER; j++) {
					Card ca = player.getCard(i);
					Card cb = player.getCard(j);
					if(ca.getNumber() > cb.getNumber()) {
						Card temp = ca;
						player.setCard(i, cb);
						player.setCard(j, temp);
					} else if(ca.getNumber() == cb.getNumber()) {
						if(ca.getColor() > cb.getColor());
						Card temp = ca;
						player.setCard(i, cb);
						player.setCard(j, temp);
					}
				}
			}
		}
	}
	
	private void threeCardsRules() {
		int[][] arr = new int[2][5];
		sortCards();
		
		for(int i = 0; i < PLAYERCNT; i++) {
			Player player = mPlayerList.get(i);
//			System.out.print(mPlayerList.get(i) + "的牌");
			arr[i] = Card.threeCardRule(player.getCard(0), player.getCard(1), player.getCard(2));
		}
		
		for(int j = 0; j < 5; j++) {
			if(arr[0][j] > arr[1][j]) {
				print0();
				break;
			} else if(arr[0][j] < arr[1][j]) {
				print1();
				break;
			}
		}
	}
	
	private void print() {
//		Player player1 = mPlayerList.get(0);
//		Player player2 = mPlayerList.get(1);
		
//		System.out.println("玩家" + player1 + "的牌为：" + player1.getCard(0) + ", " +  
//				player1.getCard(1) + ", " + player1.getCard(2));
//		System.out.println("玩家" + player2 + "的牌为：" + player2.getCard(0) + ", " +  
//				player2.getCard(1) + ", " + player2.getCard(2));
	}
	
	private void print0() {
		print();
//		System.out.println("玩家" + mPlayerList.get(0) + "获得胜利");
	}
	
	private void print1() {
		print();
//		System.out.println("玩家" + mPlayerList.get(1) + "获得胜利");
	}
}
