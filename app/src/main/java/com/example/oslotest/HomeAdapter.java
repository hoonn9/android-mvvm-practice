package com.example.oslotest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding3.view.RxView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.observers.DefaultObserver;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private static ViewHolder.OpenProfileListener mProfileListener;
    private List<User> mUsers = new ArrayList<>();
    private final Context context;

    public HomeAdapter(Context context, List<User> users) {
        this.context = context;
        mUsers = users;
    }

    public void updateUsers(List<User> users) {
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stack_user_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position < mUsers.size()) {
            User user = mUsers.get(position);
            holder.setUser(user);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    public void setOpenProfileListener(ViewHolder.OpenProfileListener listener) {
        mProfileListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View mView;
        private TextView name;
        private TextView city;
        private TextView reputation;
        private ImageView user_image;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            name = (TextView) view.findViewById(R.id.stack_user_name);
            city = (TextView) view.findViewById(R.id.stack_user_city);
            reputation = (TextView) view.findViewById(R.id.stack_user_reputation);
            user_image = (ImageView) view.findViewById(R.id.stack_user_image);
        }

        public void setUser(final User user){
            name.setText(user.getDisplayName());
            city.setText(user.getLocation());
            reputation.setText(String.valueOf(user.getReputation()));

            ImageLoader.getInstance().displayImage(user.getProfileImage(), user_image);

            RxView.clicks(mView).subscribe(new DefaultObserver<Object>(){
                @Override
                public void onNext(@NonNull Object o) {
                    Preconditions.checkNotNull(mProfileListener, "Empty Listener");
                    String url = user.getWebsiteUrl();
                    if(url != null)
                        mProfileListener.open(url);
                    else
                        mProfileListener.open(user.getLink());
                }

                @Override
                public void onError(@NonNull Throwable e) {}

                @Override
                public void onComplete() {

                }
            });
        }

        public interface OpenProfileListener {
            public void open(String url);
        }
    }
}
