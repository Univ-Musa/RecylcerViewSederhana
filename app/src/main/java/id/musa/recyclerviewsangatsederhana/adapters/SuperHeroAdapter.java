package id.musa.recyclerviewsangatsederhana.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.musa.recyclerviewsangatsederhana.R;
import id.musa.recyclerviewsangatsederhana.models.SuperHero;

public class SuperHeroAdapter extends RecyclerView.Adapter<SuperHeroAdapter.MyViewHolder> {

    List<SuperHero> heroList;
    private OnHeroActionListener actionListener;

    public interface OnHeroActionListener {
        void onEditClick(int position, SuperHero hero);
        void onDeleteClick(int position);
    }

    public SuperHeroAdapter(List<SuperHero> heroList, OnHeroActionListener actionListener) {
        this.heroList = heroList;
        this.actionListener = actionListener;
    }

    // 1. Function Tambah Data (Create)
    public void addHero(SuperHero hero) {
        heroList.add(hero);
        notifyItemInserted(heroList.size() - 1);
    }

    // 2. Function Ubah Data (Update)
    public void updateHero(int position, SuperHero hero) {
        heroList.set(position, hero);
        notifyItemChanged(position);
    }

    // 3. Function Hapus Data (Delete)
    public void removeHero(int position) {
        heroList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View superHeroView = layoutInflater.inflate(R.layout.item_super_hero, parent, false);
        return new MyViewHolder(superHeroView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SuperHero hero = heroList.get(position);
        holder.heroName.setText(hero.getHeroName());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView heroName;
        public Button btnEdit, btnHapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            heroName = itemView.findViewById(R.id.heroName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnHapus = itemView.findViewById(R.id.btnHapus);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && actionListener != null) {
                        actionListener.onEditClick(pos, heroList.get(pos));
                    }
                }
            });

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && actionListener != null) {
                        actionListener.onDeleteClick(pos);
                    }
                }
            });
        }
    }
}