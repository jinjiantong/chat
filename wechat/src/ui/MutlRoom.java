/**
 * wechatdonal
 */
package ui;

import im.MutlChating;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverItems;

import ui.adapter.FriendCardAdapter;
import ui.adapter.MultyChatHomeAdapter;
import utils.Constants;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import bean.UserInfo;

import com.donal.wechat.R;
import com.google.analytics.tracking.android.Log;


import config.AppActivity;

/**
 * 多人聊天室
 *
 * @author donal
 *
 */
public class MutlRoom extends AppActivity implements OnScrollListener, OnRefreshListener{
	
	private ListView xlistView;
	private List<UserInfo> datas;
	private MultyChatHomeAdapter mAdapter;
	private SwipeRefreshLayout swipeLayout;

	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatingroom);
		initUI();
		
		
		
	}
	
	@SuppressLint("InlinedApi")
	private void initUI() {
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.xrefresh);
		swipeLayout.setOnRefreshListener(this);
	    swipeLayout.setColorScheme(android.R.color.holo_blue_bright, 
	            android.R.color.holo_green_light, 
	            android.R.color.holo_orange_light, 
	            android.R.color.holo_red_light);
		xlistView = (ListView)findViewById(R.id.xlistview);
		xlistView.setOnScrollListener(this);
        xlistView.setDividerHeight(0);
        datas = new ArrayList<UserInfo>();
		mAdapter = new MultyChatHomeAdapter(this, datas);
		xlistView.setAdapter(mAdapter);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
			
				init();
				Message msg = new Message();
				msg.what = 10;
				handler.sendMessage(msg);
			}
			}).start();
		
		
		
			
	}

	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {

				switch (msg.what) {
				case 10:
				
					if(mAdapter != null){
					
						mAdapter.notifyDataSetChanged();
						xlistView.invalidate();
					}
					
					break;
				}
			}
		};
	
		/**
		 * 初始化房间列表
		 */
		public void init() {
			
			// 获得与XMPPConnection相关的ServiceDiscoveryManager
			ServiceDiscoveryManager discoManager = ServiceDiscoveryManager
					.getInstanceFor(Constants.conn);

			// 获得指定XMPP实体的项目
			// 这个例子获得与在线目录服务相关的项目
			DiscoverItems discoItems;
			try {
				discoItems = discoManager
						.discoverItems("conference.testchat");
				// 获得被查询的XMPP实体的要查看的项目
				Iterator<?> it = discoItems.getItems();
			    datas.clear();
				// 显示远端XMPP实体的项目
				while (it.hasNext()) {
					DiscoverItems.Item item = (DiscoverItems.Item) it.next();
					String roomName=item.getName();
					Log.e(roomName+"==");
					UserInfo user=new UserInfo(); 
					user.setRoomName(roomName);
					datas.add(user);
					//listDiscoverItems.add(item);
				}
				
			} catch (XMPPException e) {
				e.printStackTrace();
				
			}
		}
		
	
		
        	
	    //创建房间		
	public void createMultRoom(View v){
	
		
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(R.layout.showcreateroom,
				(ViewGroup) findViewById(R.id.dialog));

		AlertDialog.Builder builder = new Builder(
				MutlRoom.this);
		builder.setTitle("请输入房间名");
		builder.setView(layout);
		builder.setPositiveButton("确认", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText et = (EditText) layout
						.findViewById(R.id.et_roomName);
				String	roomName = et.getText().toString();
				createRoom(roomName);
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
			
		}
	
	//创建房间
	private void createRoom(String roomName) {

		Intent intent = new Intent(MutlRoom.this, MutlChating.class);
		intent.putExtra("jid", roomName + "@conference."+"testchat");
		intent.putExtra("action", "create");
		startActivity(intent);
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		swipeLayout.setRefreshing(false);
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	

	
	
	
}
