package com.lekalina.android.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

class Memory extends Observable {

    private int numberOfPairs;
    boolean allMatched = false;
    int flipCount = 0;

    List<Card> cards = new ArrayList<>();

    Memory(int numberOfPairsOfCards) {
        this.numberOfPairs = numberOfPairsOfCards;
        newGame();
    }

    void chooseCard(int index) {
        if(!cards.get(index).isMatched) {
            List<Integer> previousCardsInPlay = getIndicesOfCardsInPlay();
            flipCount++;
            cards.get(index).isFaceUp = true;
            List<Integer> cardsInPlay = getIndicesOfCardsInPlay();
            if(cardsInPlay.size() == 2) {
                // check for match
                int firstCardIndex = cardsInPlay.get(0);
                int secondCardIndex = cardsInPlay.get(1);
                if(cards.get(firstCardIndex).identifier.equals(cards.get(secondCardIndex).identifier)){
                    cards.get(firstCardIndex).isMatched = true;
                    cards.get(secondCardIndex).isMatched = true;
                    checkAllMatched();
                }
            }
            else if(cardsInPlay.size() > 2) {
                for(int cardIndex: previousCardsInPlay) {
                    cards.get(cardIndex).isFaceUp = false;
                }
            }
            triggerObservers();
        }
    }

    void newGame() {
        allMatched = false;
        flipCount = 0;
        cards.clear();
        cards = new ArrayList<>();
        for(int i=0; i<numberOfPairs; i++) {
            Card card = new Card();
            cards.add(card);
            Card cardMatch = new Card();
            cardMatch.identifier = card.identifier;
            cards.add(cardMatch);
        }
        Collections.shuffle(cards);
        triggerObservers();
    }

    void flipAllCardsDown() {
        for(Card card : cards) {
            card.isFaceUp = false;
            card.isMatched = false;
        }
    }

    private List<Integer> getIndicesOfCardsInPlay() {
        List<Integer> cardsInPlay = new ArrayList<>();
        for(Card card: cards) {
            if(card.isFaceUp && !card.isMatched) {
                cardsInPlay.add(cards.indexOf(card));
            }
        }
        return cardsInPlay;
    }

    private void checkAllMatched() {
        for(Card card : cards) {
            if(!card.isMatched) {
                return;
            }
        }
        allMatched = true;
    }

    private void triggerObservers() {
        setChanged();
        notifyObservers();
    }
}
