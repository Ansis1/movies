package ru.igor.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailersViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();
    private OnTrailerClickListener onTrailerClickListener;

    public void setOnTrailerClickListener(OnTrailerClickListener onTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.trailer_item,
                viewGroup,
                false
        );
        final TrailerAdapter.TrailersViewHolder holder = new TrailerAdapter.TrailersViewHolder(view,
                view.findViewById(R.id.trailer_title_tv));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                onTrailerClickListener.onTrailerClick(trailers.get(position).getUrl());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrailersViewHolder holder, int i) {

        Trailer trailer = trailers.get(i);
        holder.trailer_title_tv.setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    interface OnTrailerClickListener {
        void onTrailerClick(String url);
    }

    static class TrailersViewHolder extends RecyclerView.ViewHolder {

        private final TextView trailer_title_tv;


        public TrailersViewHolder(@NonNull @NotNull View itemView, TextView textViewTrailerName) {
            super(itemView);
            this.trailer_title_tv = itemView.findViewById(R.id.trailer_title_tv);
        }
    }
}
