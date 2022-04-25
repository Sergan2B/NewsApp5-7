package kg.geektech.newsapp39.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.newsapp39.databinding.ItemNewsBinding;
import kg.geektech.newsapp39.models.News;
@SuppressLint("NotifyDataSetChanged")
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsArrayList = new ArrayList<>();
    private ItemNewsBinding binding;
    private OnLongClickItem onLongClickItem;


    public void setNewsArrayList(News news) {
        newsArrayList.add(news);
        notifyItemInserted(newsArrayList.size());
    }

    public void setNewsArrayList(List<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
        notifyDataSetChanged();
    }

    public void setOnLongClickItem(OnLongClickItem onLongClickItem) {
        this.onLongClickItem = onLongClickItem;
    }

    public void remove(int pos) {
        newsArrayList.remove(pos);
        notifyItemRemoved(pos);
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
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public News getItem(int pos) {
        return newsArrayList.get(pos);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItems(List<News> list) {
        newsArrayList.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
        }

        public void onBind(News news) {
            binding.titleTv.setText(news.getTitle());
            binding.dataTv.setText(String.valueOf(news.getCreatedAt()));

            binding.getRoot().setOnLongClickListener(view -> {
                onLongClickItem.onLongClick(getAdapterPosition());
                return true;
            });
            binding.getRoot().setOnClickListener(view -> {
                onLongClickItem.onClick(getAdapterPosition());
            });
        }
    }

    public interface OnLongClickItem {
        void onClick(int pos);

        void onLongClick(int pos);
    }
}
