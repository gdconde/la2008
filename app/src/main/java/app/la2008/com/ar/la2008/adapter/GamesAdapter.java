package app.la2008.com.ar.la2008.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.interfaces.ObjectSelected;
import app.la2008.com.ar.la2008.models.GameSignature;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gdconde on 7/18/17.
 */

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GameSignature> games;
    private ObjectSelected listener;

    public GamesAdapter(Context context, ArrayList<GameSignature> games, ObjectSelected listener) {
        this.context = context;
        this.games = games;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.games.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.gameTextView.setText(this.games.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.select(games.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gameTextView) TextView gameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
