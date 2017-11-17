package com.twan.zuyou.adapter;

import com.twan.mylibary.recyclerViewHelper.BaseItemDraggableAdapter;
import com.twan.mylibary.recyclerViewHelper.BaseViewHolder;
import com.twan.zuyou.R;
import com.twan.zuyou.entity.Room;

import java.util.List;

/**
 * Created by luoxw on 2016/6/20.
 */
public class ItemDragAdapter extends BaseItemDraggableAdapter<Room, BaseViewHolder> {
    public ItemDragAdapter(List data) {
        super(R.layout.item_draggable_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Room item) {
        helper.setText(R.id.tv, item.getRoomname());
        helper.setText(R.id.desc, "(空闲) 0/"+item.getBedcnt());
    }
}
