package kg.geektech.newsapp39.ui.board;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.newsapp39.R;
import kg.geektech.newsapp39.databinding.ItemBoardVpBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ItemBoardVpBinding binding;
    private Integer[] listImage = new Integer[]{R.raw.kaguya, R.raw.kakashi, R.raw.russia};
    private String listTitle[] = {"Title1", "Title2", "Title3"};
    private String listSubTitle[] = {"SubTitle1", "SubTitle2", "SubTitle3"};
    private OnClickItem onClickItem;

    public BoardAdapter(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardVpBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
        if (position == 2) binding.button.setVisibility(View.VISIBLE);
        else binding.button.setVisibility(View.INVISIBLE);
        binding.button.setOnClickListener(view -> onClickItem.onClick(binding.button));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemBoardVpBinding itemView) {
            super(binding.getRoot());
        }

        public void onBind(int pos) {
            binding.boardIv.setAnimation(listImage[pos]);
            binding.titleTv.setText(listTitle[pos]);
            binding.subTitleTv.setText(listSubTitle[pos]);
            new Handler().postDelayed(() -> {}, 5000);
        }
    }

    public interface OnClickItem {
        void onClick(Button button);
    }
}
