package com.bmo.backend;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class Chat {

	public static void sendMessage(XMPPConnection connect, String Jid,
			String messageBody) {

		Message msg = new Message(Jid, Message.Type.groupchat);
		msg.setFrom(connect.getUser());
		msg.setBody(messageBody);
		msg.addBody("image_or_txt", "image");
		connect.sendPacket(msg);

		MultiUserChat.addInvitationListener(connect, new InvitationListener() {

			@Override
			public void invitationReceived(Connection arg0, String arg1,
					String arg2, String arg3, String arg4, Message arg5) {

			}

		});
	}

	public static void receiveMessages(XMPPConnection connect) {

		PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
		connect.addPacketListener(new PacketListener() {
			public void processPacket(Packet packet) {
				// Message message = (Message) packet;
				// String body = message.getBody();
				// String from = message.getFrom();
			}
		}, filter);
	}

}
