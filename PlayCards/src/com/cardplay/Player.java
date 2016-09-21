package com.cardplay;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String mName;
	List<Card> mCardList;

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public Card getCard( int index) {
		return mCardList.get(index);
	}

	public void addCard(Card card) {
		if(card != null) {
			mCardList.add(card);
		}
	}

	public void setCard(int index, Card card) {
		mCardList.set(index, card);
	}
	
	public void dropCard() {
		if(mCardList != null) {
			mCardList.clear();
		}
	}

	public Player(String name) {
		this.mName = name;
		this.mCardList = new ArrayList<Card>();
	}
	
	@Override
	public String toString() {
		return mName;
	}
}
