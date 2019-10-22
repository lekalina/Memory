package com.lekalina.android.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class GameTheme {

    private final String none = "default";
    private final String emoji = "emoji";
    private final String halloween = "halloween";
    private final String thanksgiving = "thanksgiving";
    private final String christmas = "christmas";
    private final String valentines = "valentines";
    private final String animals = "animals";
    private final String ocean = "ocean";
    private final String plants = "plants";
    private final String birds = "birds";
    private final String fruit = "fruit";
    private final String food = "food";
    private final String weather = "weather";


    int cardColor = R.color.white;
    String selectedTheme = "default";
    String cardBackgroundImage = "";
    List<String> imageArray = new ArrayList<>();

    GameTheme() {
        setTheme(none);
    }

    void setRandomTheme() {
        String[] themes = {none, emoji, halloween, thanksgiving, christmas, valentines, animals, ocean, plants, birds, fruit, food, weather};
        int randomIndex = new Random().nextInt(themes.length);
        String cardTheme = themes[randomIndex];
        selectedTheme = cardTheme;
        setTheme(cardTheme);
    }

    void setTheme(String theme) {
        imageArray.clear();
        imageArray = new ArrayList<>();
        selectedTheme = theme;
        switch(theme) {
            case emoji: {
                imageArray.addAll(Arrays.asList(emojiArray));
                cardColor = R.color.yellow;
                cardBackgroundImage = ":)";
                break;
            }
            case halloween: {
                imageArray.addAll(Arrays.asList(halloweenArray));
                cardColor = R.color.orange;
                cardBackgroundImage = "☠";
                break;
            }
            case thanksgiving: {
                imageArray.addAll(Arrays.asList(thanksgivingArray));
                cardColor = R.color.maroon;
                cardBackgroundImage = "★";
                break;
            }
            case christmas: {
                imageArray.addAll(Arrays.asList(christmasArray));
                cardColor = R.color.red;
                cardBackgroundImage = "☃︎";
                break;
            }
            case valentines: {
                imageArray.addAll(Arrays.asList(valentinesArray));
                cardColor = R.color.pink;
                cardBackgroundImage = "♥︎";
                break;
            }
            case animals: {
                imageArray.addAll(Arrays.asList(animalsArray));
                cardColor = R.color.dark_green;
                cardBackgroundImage = "ఠ";
                break;
            }
            case ocean: {
                imageArray.addAll(Arrays.asList(oceanArray));
                cardColor = R.color.blue;
                cardBackgroundImage = "༄";
                break;
            }
            case plants: {
                imageArray.addAll(Arrays.asList(plantsArray));
                cardColor = R.color.green;
                cardBackgroundImage = "⚘";
                break;
            }
            case birds: {
                imageArray.addAll(Arrays.asList(birdsArray));
                cardColor = R.color.light_green;
                cardBackgroundImage = "▻";
                break;
            }
            case fruit: {
                imageArray.addAll(Arrays.asList(fruitArray));
                cardColor = R.color.purple;
                cardBackgroundImage = "❧";
                break;
            }
            case food: {
                imageArray.addAll(Arrays.asList(foodArray));
                cardColor = R.color.light_blue;
                cardBackgroundImage = "♨︎";
                break;
            }
            case weather: {
                imageArray.addAll(Arrays.asList(weatherArray));
                cardColor = R.color.gray;
                cardBackgroundImage = "☂︎";
                break;
            }
            default: {
                imageArray.addAll(Arrays.asList(emojiArray));
                cardColor = R.color.yellow;
                cardBackgroundImage = ":)";
            }
        }
    }

    private String[] emojiArray = new String[] {"😁","🤪","🤩","🥰","🤢","🤕","🤣","😡","😇","🥳"};

    private String[] halloweenArray = new String[] {"🎃","👻","☠️","🕸","🍭","🕷","🍬","🧟‍️","😈","🦇"};

    private String[] thanksgivingArray = new String[] {"🦃","🍁","🍂","🌽","🥖","👨‍🌾","🍽","🥧","🍗","🥕"};

    private String[] christmasArray = new String[] {"🎅","🎄","⭐️","⛄️","❄️","🎁","🤶","🍪","🕯","🛍","🧸","🔔"};

    private String[] valentinesArray = new String[] {"💋","🌹","💐","🍫","🍓","💝","🌺","😍","♥️","💘","💌"};

    private String[] animalsArray = new String[] {"🐅","🐆","🦓","🐘","🐫","🦒","🦘","🐖","🐏","🦙","🐇","🦝","🐿","🦔"};

    private String[] oceanArray = new String[] {"🐙","🦑","🐡","🦈","🐳","🐬","🐠","🦞","🦀","🌊","🐚","🏝","⛵️"};

    private String[] plantsArray = new String[] {"🌵","🍀","🌻","🌴","🌼","🌸","🌺","🌿","🌲","♻️"};

    private String[] birdsArray = new String[] {"🦆","🦅","🦉","🦋","🦚","🦢","🦜","🕊","🐓","🐣"};

    private String[] fruitArray = new String[] {"🍐","🍊","🍎","🍋","🍌","🍉","🍇","🍒","🍑","🍍","🥥","🥝","🍓","🥭"};

    private String[] foodArray = new String[] {"🥨","🥐","🥯","🥞","🥓","🌭","🍔","🍟","🍕","🌮","🍣","🥟","🍤","🥧","🍦","🍩","🍪","🥪","🍿"};

    private String[] weatherArray = new String[] {"🌪","🌈","🌤","⛈","🌨","☔️","❄️","☀️","🔥","🌦"};
}
