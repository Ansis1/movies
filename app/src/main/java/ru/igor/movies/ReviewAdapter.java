package ru.igor.movies;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewsViewHolder> {

    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEGATIVE = "Негативный";
    private static final String TYPE_NEITRAL = "Нейтральный";

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.review_item,
                viewGroup,
                false
        );
        final ReviewAdapter.ReviewsViewHolder holder = new ReviewAdapter.ReviewsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ReviewsViewHolder reviewsViewHolder, int i) {

        Review review = reviews.get(i);
        reviewsViewHolder.review_title_tv.setText(review.getAuthor());
        reviewsViewHolder.review_text_tv.setText(review.getText());

        Context context = reviewsViewHolder.itemView.getContext();

        switch (review.getType()) {

            case TYPE_NEGATIVE:
                reviewsViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
                break;
            case TYPE_NEITRAL:
                reviewsViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
                break;
            case TYPE_POSITIVE:
                reviewsViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
                break;

        }

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private final TextView review_title_tv;
        private final TextView review_text_tv;


        public ReviewsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.review_title_tv = itemView.findViewById(R.id.review_title_tv);
            this.review_text_tv = itemView.findViewById(R.id.review_text_tv);
        }
    }
}
