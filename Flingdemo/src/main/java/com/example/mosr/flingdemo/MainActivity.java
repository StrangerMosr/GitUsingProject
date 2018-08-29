package com.example.mosr.flingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnViewPagerListener {
    private RecyclerView mRecyclerView;
    List<Integer> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mData.add(R.mipmap.one);
        mData.add(R.mipmap.two);
        mData.add(R.mipmap.three);
        mData.add(R.mipmap.four);
        mData.add(R.mipmap.five);
        mData.add(R.mipmap.six);
        mData.add(R.mipmap.seven);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new FlingLayoutManager(this, LinearLayoutManager.VERTICAL, false).setmOnViewPagerListener(this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
                ViewHolder mViewHolder = new ViewHolder(view);
                return mViewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((ViewHolder) holder).mImageView.setImageResource(mData.get(position));

            }

            @Override
            public int getItemCount() {
                return mData.size();
            }
        });
    }

    @Override
    public void onPageSelected(int postion, boolean isNext) {
        int index = isNext ? 0 : 1;
    }

    @Override
    public void onPageRelease(int postion, boolean isNext) {
        int index = isNext ? 0 : 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.mImageView);
        }
    }
}
