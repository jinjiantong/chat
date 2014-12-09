/**
 * wechatdonal
 */
package bean;

import java.io.Serializable;

import org.jivesoftware.smack.packet.RosterPacket;

import tools.AppException;
import tools.Logger;

import com.google.gson.Gson;

/**
 * wechat
 *
 * @author donal
 *
 */
public class UserInfo implements Serializable {
	public String userId;
	public String nickName;
	public String description;
	public String registerDate;
	public String userHead;
	
	//add by jinchunhaon 多人聊天的时候用
	private String roomName;
	private String roomId;
	private String roomMemberCount;
	private String roomCreatedData;
	private String name;
	private String user;
	private RosterPacket.ItemType type;
	private int size;
	private String status;
	private String from;
	
	/**
	 * @param string
	 * @return
	 * @throws AppException 
	 */
	public static UserInfo parse(String string) throws AppException {
		UserInfo data = new UserInfo();
		try {
			Gson gson = new Gson();
			data = gson.fromJson(string, UserInfo.class);
		} catch (Exception e) {
			Logger.i(e);
			throw AppException.json(e);
		}
		return data;
	}
	
	//add by jinchunhaon 多人聊天的时候用
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomCreatedData() {
		return roomCreatedData;
	}

	public void setRoomCreatedData(String roomCreatedData) {
		this.roomCreatedData = roomCreatedData;
	}

	public String getRoomMemberCount() {
		return roomMemberCount;
	}

	public void setRoomMemberCount(String roomMemberCount) {
		this.roomMemberCount = roomMemberCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public RosterPacket.ItemType getType() {
		return type;
	}

	public void setType(RosterPacket.ItemType type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
