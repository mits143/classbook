package com.app.classbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.app.classbook.R;
import com.app.classbook.model.response.SubTopicResponseModel;
import com.app.classbook.model.response.TopicSubTopicResponseItem;
import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import java.util.List;

public class TopicSubTopicAdapter extends ExpandableRecyclerAdapter<TopicSubTopicResponseItem, SubTopicResponseModel, TopicSubTopicAdapter.TransactionParentViewHolder, TopicSubTopicAdapter.TransactionChildViewHolder> {

    private LayoutInflater mInflater;
    public Context context;

    public TopicSubTopicAdapter(Context context, @NonNull List<TopicSubTopicResponseItem> topicSubTopicResponseItems) {
        super(topicSubTopicResponseItems);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public TransactionParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView = mInflater.inflate(R.layout.cell_parent, parentViewGroup, false);
        expandAllParents();
        return new TransactionParentViewHolder(recipeView);
    }

    @Override
    public TransactionChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView = mInflater.inflate(R.layout.cell_parent, childViewGroup, false);
        return new TransactionChildViewHolder(ingredientView);
    }

    // onBind ...
    @Override
    public void onBindParentViewHolder(@NonNull TransactionParentViewHolder transactionParentViewHolder, int parentPosition, @NonNull TopicSubTopicResponseItem topicSubTopicResponseItem) {
        transactionParentViewHolder.bind(topicSubTopicResponseItem);
    }

    @Override
    public void onBindChildViewHolder(@NonNull TransactionChildViewHolder ingredientViewHolder, int parentPosition, int childPosition, @NonNull SubTopicResponseModel subTopicResponseModel) {
        ingredientViewHolder.bind(subTopicResponseModel);
    }

    public class TransactionParentViewHolder extends ParentViewHolder {

        private TextView txtParentName;

        public TransactionParentViewHolder(View itemView) {
            super(itemView);
            txtParentName = itemView.findViewById(R.id.txtParentName);
        }

        public void bind(TopicSubTopicResponseItem topicSubTopicResponseItem) {
            txtParentName.setText(topicSubTopicResponseItem.getName());
        }
    }

    public class TransactionChildViewHolder extends ChildViewHolder {

        private TextView txtParentName;

        public TransactionChildViewHolder(View itemView) {
            super(itemView);

            txtParentName = itemView.findViewById(R.id.txtParentName);
        }

        public void bind(SubTopicResponseModel subTopicResponseModel) {

            txtParentName.setText(subTopicResponseModel.getName());

//            itemView.setOnClickListener(v -> {
//                attributeDelete.onClick(subTopicResponseModel);
//            });
        }
    }

    interface onAttributeDelete {
        void onClick(SubTopicResponseModel SubTopicResponseModel);
    }
}