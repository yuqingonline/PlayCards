package com.cardplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager_old {
	private List<Card> mCardList = new ArrayList<Card>();
	private List<Player> mPlayerList = new ArrayList<Player>();
	private static final int PLAYERCNT = 2;
	private static final int CARD_CNTIN_PLAYER = 3;
	private Random mRand = new Random(52);
	
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
		System.out.println("洗牌后的牌为：");
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
			index++;
		}
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
					} else if(player.getCard(i).getNumber() == player.getCard(j).getNumber()) {
						if(player.getCard(i).getColor() > player.getCard(j).getColor());
						Card temp = player.getCard(i);
						player.setCard(i, player.getCard(j));
						player.setCard(j, temp);
					}
				}
			}
		}
	}
	
	private void threeCardsRules() {
		int[] arr = new int[2];
		sortCards();
		for(int i = 0; i < PLAYERCNT; i++) {
//			System.out.print(mPlayerList.get(i) + "的牌");
			arr[i] = Card_old.threeCardRule(mPlayerList.get(i).getCard(0), 
					mPlayerList.get(i).getCard(1), mPlayerList.get(i).getCard(2));
		}
		
		if(arr[0] > arr[1]) {
			print0();
		} else if(arr[0] < arr[1]) {
			print1();
		} else {
			if(arr[0] == 5) {
//				System.out.println("都是豹子");
				if(mPlayerList.get(0).getCard(0).getNumber() > 
				mPlayerList.get(1).getCard(0).getNumber()) {
					print0();
				} else {
					print1();
				}
			} else if(arr[0] == 4) {
//				System.out.println("都是同花顺");
				if(mPlayerList.get(0).getCard(0).getNumber() > 
				mPlayerList.get(1).getCard(0).getNumber()) {
					print0();
				} else if(mPlayerList.get(0).getCard(0).getNumber() < 
						mPlayerList.get(1).getCard(0).getNumber()) {
					print1();
				} else {
					if(mPlayerList.get(0).getCard(0).getColor() > 
					mPlayerList.get(1).getCard(0).getColor()) {
						print0();
					} else
						print1();
				}
			} else if(arr[0] == 3) {
//				System.out.println("都是同花");
				if(mPlayerList.get(0).getCard(2).getNumber() > 
				mPlayerList.get(1).getCard(2).getNumber()) {
					print0();
				} else if(mPlayerList.get(0).getCard(2).getNumber() < 
						mPlayerList.get(1).getCard(2).getNumber()) {
					print1();
				} else {
					if(mPlayerList.get(0).getCard(1).getNumber() > 
					mPlayerList.get(1).getCard(1).getNumber()) {
						print0();
					} else if(mPlayerList.get(0).getCard(1).getNumber() < 
							mPlayerList.get(1).getCard(1).getNumber()) {
						print1();
					} else {
						if(mPlayerList.get(0).getCard(0).getNumber() > 
						mPlayerList.get(1).getCard(0).getNumber()) {
							print0();
						} else if(mPlayerList.get(0).getCard(0).getNumber() < 
								mPlayerList.get(1).getCard(0).getNumber()) {
							print1();
						} else {
							if(mPlayerList.get(0).getCard(2).getColor() > 
							mPlayerList.get(1).getCard(2).getColor()) {
								print0();
							} else
								print1();
						}
					} 
				}
			} else if(arr[0] == 2) {
//				System.out.println("都是拖拉机");
				if(mPlayerList.get(0).getCard(0).getNumber() > 
				mPlayerList.get(1).getCard(0).getNumber()) {
					print0();
				} else if(mPlayerList.get(0).getCard(0).getNumber() < 
						mPlayerList.get(1).getCard(0).getNumber()) {
					print1();
				} else {
					if(mPlayerList.get(0).getCard(0).getColor() > 
					mPlayerList.get(1).getCard(0).getColor()) {
						print0();
					} else
						print1();
				}
			} else if(arr[0] == 1) {
//				System.out.println("都有一个对牌");
				if(mPlayerList.get(0).getCard(1).getNumber() > 
				mPlayerList.get(1).getCard(1).getNumber()) {
					print0();
				} else if (mPlayerList.get(0).getCard(1).getNumber() < 
						mPlayerList.get(1).getCard(1).getNumber()) {
					print1();
				} else {
					if((mPlayerList.get(0).getCard(0).getNumber() > 
					mPlayerList.get(1).getCard(0).getNumber()) || 
					(mPlayerList.get(0).getCard(2).getNumber() > 
					mPlayerList.get(1).getCard(2).getNumber())) {
						print0();
					} else if((mPlayerList.get(0).getCard(0).getNumber() < 
							mPlayerList.get(1).getCard(0).getNumber()) || 
							(mPlayerList.get(0).getCard(2).getNumber() < 
									mPlayerList.get(1).getCard(2).getNumber())) {
						print1();
					} else {
						if(mPlayerList.get(0).getCard(2).getColor() > 
						mPlayerList.get(1).getCard(2).getColor()) {
							print0();
						} else
							print1();
					}
				}
			} else {
//				System.out.println("都是普通牌");
				if(mPlayerList.get(0).getCard(2).getNumber() > 
				mPlayerList.get(1).getCard(2).getNumber()) {
					print0();
				} else if(mPlayerList.get(0).getCard(2).getNumber() < 
						mPlayerList.get(1).getCard(2).getNumber()) {
					print1();
				} else {
					if(mPlayerList.get(0).getCard(1).getNumber() > 
					mPlayerList.get(1).getCard(1).getNumber()) {
						print0();
					} else if(mPlayerList.get(0).getCard(1).getNumber() < 
							mPlayerList.get(1).getCard(1).getNumber()) {
						print1();
					} else {
						if(mPlayerList.get(0).getCard(0).getNumber() > 
						mPlayerList.get(1).getCard(0).getNumber()) {
							print0();
						} else if(mPlayerList.get(0).getCard(0).getNumber() < 
								mPlayerList.get(1).getCard(0).getNumber()) {
							print1();
						} else {
							if(mPlayerList.get(0).getCard(2).getColor() > 
							mPlayerList.get(1).getCard(2).getColor()) {
								print0();
							} else
								print1();
						}
					} 
				}
			}
		}
	}
	
	private void print() {
//		System.out.println("玩家" + mPlayerList.get(0) + "的牌为：" + 
//		mPlayerList.get(0).getCard(0) + ", " +  
//				mPlayerList.get(0).getCard(1) + ", " + 
//		mPlayerList.get(0).getCard(2));
//		System.out.println("玩家" + mPlayerList.get(1) + "的牌为：" + 
//		mPlayerList.get(1).getCard(0) + ", " +  
//		mPlayerList.get(1).getCard(1) + ", " + 
//		mPlayerList.get(1).getCard(2));
	}
	
	private void print0() {
		print();
		//System.out.println("玩家" + mPlayerList.get(0) + "获得胜利");
	}
	
	private void print1() {
		print();
		//System.out.println("玩家" + mPlayerList.get(1) + "获得胜利");
	}
}
