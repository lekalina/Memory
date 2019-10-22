package com.lekalina.android.memory;

import java.util.UUID;

class Card {

    boolean isFaceUp = false;
    boolean isMatched = false;
    String identifier = UUID.randomUUID().toString();
}
