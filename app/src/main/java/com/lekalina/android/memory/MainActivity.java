package com.lekalina.android.memory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lekalina.android.memory.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Observer {

    public HashMap<String, String> images = new HashMap<>();

    public Memory game;

    public GameTheme theme;

    public ActivityMainBinding binding;

    private boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        game = new Memory(16/2);

        game.addObserver(this);

        theme = new GameTheme();

        binding.cardContainer.setLayoutManager(new GridLayoutManager(this, 4));

        CardAdapter adapter = new CardAdapter(this, game.cards);

        binding.cardContainer.setAdapter(adapter);

        binding.newGame.setOnClickListener(v -> {
            game.newGame();
            theme.setTheme(theme.selectedTheme);
            adapter.updateCardList(game.cards);
            toggleButtons();
        });

        binding.changeTheme.setOnClickListener(v -> {
            theme.setRandomTheme();
            game.flipAllCardsDown();
            adapter.updateCardList(game.cards);
        });

        setFlipCount();

        toggleButtons();
    }

    public void toggleButtons() {
        binding.newGame.setVisibility(game.allMatched ? View.VISIBLE : View.GONE);
        binding.changeTheme.setVisibility(game.allMatched ? View.VISIBLE : View.GONE);
        if(isLandscape) {
            binding.newGame.setBackgroundColor(Color.TRANSPARENT);
            binding.buttonContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_black));
        }
        else {
            binding.newGame.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
            binding.buttonContainer.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public String imageForCard(Card card){
        if (!images.containsKey(card.identifier) && (theme.imageArray != null && theme.imageArray.size() > 0)) {
            int randomIndex = new Random().nextInt(theme.imageArray.size());
            images.put(card.identifier, theme.imageArray.get(randomIndex));
            theme.imageArray.remove(randomIndex);
        }
        return images.get(card.identifier);
    }

    @Override
    public void update(Observable observable, Object o) {
        setFlipCount();
        toggleButtons();
    }

    public void setFlipCount() {
        String flips = "Flips: "+game.flipCount;
        binding.flipCount.setText(flips);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            isLandscape = false;
        }
        toggleButtons();
    }

    class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

        List<Card> cardList;
        Context context;

        CardAdapter(Context context, List<Card> cardList) {
            this.context = context;
            this.cardList = cardList;
        }

        void updateCardList(List<Card> cardList) {
            this.cardList = cardList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
            Card card = cardList.get(position);
            if(card.isMatched || card.isFaceUp) {
                String cardImage = imageForCard(card);
                holder.textView.setText(cardImage);
                holder.textView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
            else {
                holder.textView.setText(theme.cardBackgroundImage);
                holder.textView.setBackgroundColor(ContextCompat.getColor(context, theme.cardColor));
            }
            holder.textView.setOnClickListener(View -> {
                if(!game.allMatched) {
                    game.chooseCard(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardList != null ? cardList.size() : 0;
        }
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
