package ui.adapter;

import java.util.List;

import ui.Friend;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import bean.UserInfo;

import com.donal.wechat.R;
import com.google.analytics.tracking.android.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

import config.CommonValue;
import config.XmppConnectionManager;

public class MultyChatHomeAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<UserInfo> cards;
	
	static class CellHolder {
		TextView alpha;
		ImageView avatarImageView;
		TextView titleView;
		TextView desView;
	}
	
	public MultyChatHomeAdapter(Context context, List<UserInfo> cards) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.cards = cards;
	}
	
	@Override
	public int getCount() {
		return cards.size();
	}

	@Override
	public Object getItem(int arg0) {
		return cards.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		CellHolder cell = null;
		if (convertView == null) {
			cell = new CellHolder();
			convertView = inflater.inflate(R.layout.friend_card_cell, null);
			cell.alpha = (TextView) convertView.findViewById(R.id.alpha);
			cell.avatarImageView = (ImageView) convertView.findViewById(R.id.avatarImageView);
			cell.titleView = (TextView) convertView.findViewById(R.id.title);
			cell.desView = (TextView) convertView.findViewById(R.id.des);
			convertView.setTag(cell);
		}
		else {
			cell = (CellHolder) convertView.getTag();
		}
		final UserInfo user = cards.get(position);
		//ImageLoader.getInstance().displayImage(CommonValue.BASE_URL+model.userHead, cell.avatarImageView, CommonValue.DisplayOptions.default_options);
		cell.titleView.setText(user.getRoomName());
		cell.desView.setText("");
		cell.alpha.setVisibility(View.GONE);
		convertView.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				//进入房间
				
      //	Log.e("create chate");
      //	((Friend)context).createChat(model.userId+XmppConnectionManager.BASE_XMPP_SERVER_NAME);
			}
		});
		convertView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				//((Friend)context).show2OptionsDialog(new String[]{"删除好友"}, model);
				//删除房间
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("确定删除房间吗?")
				       .setCancelable(false)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				               
				           }
				       })
				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				 AlertDialog alert = builder.create();
				 alert.show();
				 
				return true;
			}
		});
		return convertView;
	}
	
}
