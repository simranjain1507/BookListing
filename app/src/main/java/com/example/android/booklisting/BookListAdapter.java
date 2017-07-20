package com.example.android.booklisting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by simranjain1507 on 20/07/17.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {

    private List<BookList> couponLists;
    Context context;

    public BookListAdapter(List<BookList> couponLists, Context context) {
        this.couponLists = couponLists;
        this.context = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.title.setText(couponLists.get(position).getTitle());
        holder.author.setText(couponLists.get(position).getAuthor());
        Glide.with(context).load(couponLists.get(position).getId()).placeholder(R.drawable.ic_book_black_24dp).into(holder.bookImage);
        final String link=couponLists.get(position).getPreview();
        holder.booklistsitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previewIntent=new Intent(context, BookPreview.class);
                previewIntent.putExtra("link",link);
                context.startActivity(previewIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return couponLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, author;
        ImageView bookImage;
        RelativeLayout booklistsitem;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_book_title);
            author = (TextView) itemView.findViewById(R.id.tv_book_auhtor);
            bookImage=(ImageView) itemView.findViewById(R.id.img_book);
            booklistsitem=(RelativeLayout) itemView.findViewById(R.id.book_list);
        }
    }


}

