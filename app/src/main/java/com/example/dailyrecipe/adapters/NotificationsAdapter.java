package com.example.dailyrecipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.models.Notification;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    private List<Notification> notificationList;

    public NotificationsAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_notifications, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.bindItem(notification);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        private TextView text_notification_date, text_notification_title, text_notification_description;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            text_notification_date = itemView.findViewById(R.id.text_notifications_date);
            text_notification_title = itemView.findViewById(R.id.text_notifications_title);
            text_notification_description = itemView.findViewById(R.id.text_notifications_description);

        }

        private void bindItem(Notification notification) {
            text_notification_date.setText(notification.getDate());
            text_notification_title.setText(notification.getTitle());
            text_notification_description.setText(notification.getDescription());
        }
    }
}
