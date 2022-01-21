package kg.geektech.newsapp39.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.geektech.newsapp39.OnClickItem;
import kg.geektech.newsapp39.databinding.ItemNewsBinding;
import kg.geektech.newsapp39.ui.news.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> newsArrayList;
    private ItemNewsBinding binding;
    private OnClickItem onClickItem;


    public NewsAdapter(ArrayList<News> newsArrayList, OnClickItem onClickItem) {
        this.newsArrayList = newsArrayList;
        this.onClickItem = onClickItem;
        notifyDataSetChanged();
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(newsArrayList.get(position));
        holder.itemView.setOnLongClickListener(view -> {onClickItem.onClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public News getItem(int pos) {
        return newsArrayList.get(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
        }

        public void onBind(News news) {
            binding.titleTv.setText(news.getTitle());
            binding.dataTv.setText(String.valueOf(news.getCreatedAt()));
        }
    }
}
