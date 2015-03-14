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

	public static Message msg;

	public static void sendMessage(String event, XMPPConnection connect,
			String Jid, String phoneNumber, String messagetoSend) {

		switch (event) {

		case "sendsms":

			msg = new Message(Jid, Message.Type.normal);
			msg.setFrom(connect.getUser());
			msg.setBody(messagetoSend);
			msg.addBody("phoneNumber", phoneNumber);
			msg.addBody("event", "sendText");
			connect.sendPacket(msg);

			break;

		case "retrieveContacts":

			msg = new Message(Jid, Message.Type.normal);
			msg.setFrom(connect.getUser());
			msg.setBody(messagetoSend);
			msg.addBody("event", "retrieveContacts");
			connect.sendPacket(msg);

			break;
		case "readTexts":

			Message msg = new Message(Jid, Message.Type.normal);
			msg.setFrom(connect.getUser());
			msg.setBody(messagetoSend);
			msg.addBody("event", "readTexts");
			connect.sendPacket(msg);
			break;

		}

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
