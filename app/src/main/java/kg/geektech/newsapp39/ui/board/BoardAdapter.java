package kg.geektech.newsapp39.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.newsapp39.R;
import kg.geektech.newsapp39.databinding.ItemBoardVpBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ItemBoardVpBinding binding;
    private Integer[] listImage = new Integer[]{R.drawable.x2x, R.drawable.x5x, R.drawable.x8x};
    private String listTitle[] = {"Title1", "Title2", "Title3"};
    private String listSubTitle[] = {"SubTitle1", "SubTitle2", "SubTitle3"};
    private int[] listPager = {View.VISIBLE, View.VISIBLE, View.INVISIBLE};
    private int[] listPager2 = {View.INVISIBLE, View.INVISIBLE, View.VISIBLE};
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
        holder.onBind(listImage[position], listTitle[position], listSubTitle[position], listPager[position], listPager2[position]);
    }

    @Override
    public int getItemCount() {
        return listTitle.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemBoardVpBinding itemView) {
            super(binding.getRoot());
        }

        public void onBind(Integer integer, String s, String s1, int i1, int i) {
            binding.button4.setVisibility(i1);
            binding.button.setVisibility(i);
            binding.boardIv.setImageResource(integer);
            binding.titleTv.setText(s);
            binding.subTitleTv.setText(s1);
            initListenerOnClick(binding.button);
            initListenerOnClick(binding.button4);
        }

        public void initListenerOnClick(Button button) {
            binding.getRoot().setOnClickListener(v -> onClickItem.onClick(button));
        }
    }

    public interface OnClickItem {
        void onClick(Button button);
    }
}
