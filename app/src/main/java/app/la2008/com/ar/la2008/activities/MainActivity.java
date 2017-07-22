package app.la2008.com.ar.la2008.activities;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.adapter.GamesAdapter;
import app.la2008.com.ar.la2008.interfaces.ObjectSelected;
import app.la2008.com.ar.la2008.models.Game;
import app.la2008.com.ar.la2008.models.GameSignature;
import app.la2008.com.ar.la2008.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.liveGamesRecyclerview) RecyclerView liveGamesRecyclerView;
    @BindView(R.id.finishedGamesRecyclerView) RecyclerView finishedGamesRecyclerView;

    private DatabaseReference mDatabase;
    private ArrayList<GameSignature> mLiveGames = new ArrayList<>();
    private ArrayList<GameSignature> mFinishedGames = new ArrayList<>();
    private GameSelected gameSelected = new GameSelected();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mDatabase = Utils.getDatabase().getReference();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManagerLive =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final GamesAdapter liveGamesAdapter = new GamesAdapter(this, this.mLiveGames, gameSelected);
        liveGamesRecyclerView.setLayoutManager(layoutManagerLive);
        liveGamesRecyclerView.setAdapter(liveGamesAdapter);

        LinearLayoutManager layoutManagerFinished =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final GamesAdapter finishedGamesAdapter = new GamesAdapter(this, this.mFinishedGames, gameSelected);
        finishedGamesRecyclerView.setLayoutManager(layoutManagerFinished);
        finishedGamesRecyclerView.setAdapter(finishedGamesAdapter);

        ChildEventListener liveGamesListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    String key = dataSnapshot.getKey();
                    String name = dataSnapshot.getValue(String.class);
                    mLiveGames.add(new GameSignature(key, name));
                    liveGamesAdapter.notifyDataSetChanged();
                }
                catch (DatabaseException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                String name = dataSnapshot.getValue(String.class);
                mLiveGames.remove(new GameSignature(key, name));
                liveGamesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ChildEventListener finishedGamesListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    String key = dataSnapshot.getKey();
                    String name = dataSnapshot.getValue(String.class);
                    mFinishedGames.add(new GameSignature(key, name));
                    finishedGamesAdapter.notifyDataSetChanged();
                }
                catch (DatabaseException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                String name = dataSnapshot.getValue(String.class);
                mFinishedGames.remove(new GameSignature(key, name));
                finishedGamesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        this.mDatabase
                .getRoot()
                .child("games_live")
                .addChildEventListener(liveGamesListener);
        this.mDatabase
                .getRoot()
                .child("games_finished")
                .addChildEventListener(finishedGamesListener);
    }

    @OnClick(R.id.newGameButton)
    public void newGame() {
        NewGameActivity.start(this);
    }

    private class GameSelected implements ObjectSelected {
        @Override
        public void select(Object object) {
            WatchGameActivity.start(MainActivity.this, (GameSignature)object);
        }
    }
}
