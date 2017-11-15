package com.twan.zuyou.adapter;

import com.twan.mylibary.recyclerViewHelper.BaseItemDraggableAdapter;
import com.twan.mylibary.recyclerViewHelper.BaseViewHolder;
import com.twan.zuyou.R;

import java.util.List;

/**
 * Created by luoxw on 2016/6/20.
 */
public class ItemDragAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {
    public ItemDragAdapter(List data) {
        super(R.layout.item_draggable_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        switch (helper.getLayoutPosition() %
                3) {
            case 0:
                helper.setImageResource(R.id.iv_head, R.mipmap.ic_launcher_round);
                break;
            case 1:
                helper.setImageResource(R.id.iv_head, R.mipmap.ic_launcher_round);
                break;
            case 2:
                helper.setImageResource(R.id.iv_head, R.mipmap.ic_launcher_round);
                break;
        }
        helper.setText(R.id.tv, item);
    }
}
