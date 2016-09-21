package com.cardplay;

public class Card {
	private int mNumber;
	private int mColor;
	private static final String[] NUMBERS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private static final String[] COLORS = {"方块", "梅花", "红心", "黑桃"};

	public int getNumber() {
		return mNumber;
	}

	public void setNumber(int number) {
		this.mNumber = number;
	}

	public int getColor() {
		return mColor;
	}

	public void setColor(int color) {
		this.mColor = color;
	}

	public Card(int color, int number) {
		this.mColor = color;
		this.mNumber = number;
	}
	
	@Override
	public String toString() {
		return COLORS[mColor] + NUMBERS[mNumber];
	}
	
	public static int colorCnt() {
		return COLORS.length;
	}

	public static int numberCnt() {
		return NUMBERS.length;
	}
	
	public static boolean compare(Card card1, Card card2) {
		return (card1.getNumber() * NUMBERS.length + card1.getColor()) > 
		(card2.getNumber() * NUMBERS.length + card2.getColor());
	}
	
	public static Card compareCard(Card card1, Card card2) {
		if(compare(card1, card2)) {
			return card1;
		}
		return card2;
	}

	public static int[] threeCardRule(Card card1, Card card2, Card card3) {
		int[] arr = new int [5];
		if((card1.getNumber() == card2.getNumber()) && 
				(card1.getNumber() == card3.getNumber())) {
//			System.out.println("是豹子");
			arr[0] = 5;
			arr[1] = card1.getNumber();
			return arr;
		} else if((card1.getColor() == card2.getColor()) && 
				(card1.getColor() == card3.getColor())) {
			if((card2.getNumber() == (card1.getNumber() + 1)) && 
					(card3.getNumber() == (card2.getNumber() + 1))) {
//				System.out.println("是同花顺");
				arr[0] = 4;
				arr[1] = card3.getNumber();
				arr[2] = card3.getColor();
				return arr;
			} else {
//				System.out.println("是同花");
				arr[0] = 3;
				arr[1] = card3.getNumber();
				arr[2] = card2.getNumber();
				arr[3] = card1.getNumber();
				arr[4] = card3.getColor();
				return arr;
			}
		} else if((card2.getNumber() == (card1.getNumber() + 1)) && 
				(card3.getNumber() == (card2.getNumber() + 1))) {
//			System.out.println("是拖拉机");
			arr[0] = 2;
			arr[1] = card3.getNumber();
			arr[2] = card3.getColor();
			return arr;
		}
		else if((card1.getNumber() == card2.getNumber()) || 
				(card2.getNumber() == card3.getNumber())) {
//			System.out.println("是对子");
			arr[0] = 1;
			arr[1] = card2.getNumber();
			arr[2] = card3.getNumber() + card1.getNumber();
			arr[3] = card3.getColor();
			return arr;
		}
//		System.out.println("是普通牌");
		arr[0] = 0;
		arr[1] = card3.getNumber();
		arr[2] = card2.getNumber();
		arr[3] = card1.getNumber();
		arr[4] = card3.getColor();
		return arr;
	}
}
