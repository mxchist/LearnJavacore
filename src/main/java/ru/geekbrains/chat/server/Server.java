package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Vector;

public class Server {
	private Vector<ClientHandler> clients;
	public Connection connection;

	private int sessionId;
	private int clientSessionId;

	public Server() {
		clients = new Vector<>();
		ServerSocket server = null;
		Socket socket = null;
		try {
			connect();
//			AuthService authService = new AuthService(this.connection);
			server = new ServerSocket(8189);
			System.out.println("Сервер запущен. Ожидаем клиентов...");
			logNewSessionId();
			this.clientSessionId = 0;
			while (true) {
				socket = server.accept();
				this.clientSessionId++;
				System.out.println("Клиент подключился");
				new ClientHandler(this, socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			disconnect();
		}
	}

	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:users.db");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void logNewSessionId() {
		try {
			Statement stmt = this.connection.createStatement();
			stmt.executeUpdate(
					"insert into main.server_session\n" +
							"default values;\n" +
							"\n");
			ResultSet rs = stmt.executeQuery("select last_insert_rowid();");
			if (rs.next()) {
				this.sessionId = rs.getInt(1);
			}
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public void logNewClientSessionId(String nickname, int clientSessionId) throws SQLException {
		PreparedStatement ps;
		ps = this.connection.prepareStatement(
					"insert into main.user_session(user_session_id, server_session_id, nickname)\n" +
							"values(?, ?, ?);"
			);
		ps.setInt(1, clientSessionId);
		ps.setInt(2, this.sessionId);
		ps.setString(3, nickname);
		ps.executeUpdate();
	}

	public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) throws IOException {
		for (ClientHandler cl : clients) {
			if (cl.getNick().equals(nickTo)) {
				cl.sendMsg("from " + from.getNick() + ": " + msg);
				from.sendMsg("to " + nickTo + ": " + msg);
				return;
			}
		}
		from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
	}

	public void broadcastMsg(ClientHandler from, String msg) throws SQLException,  IOException {
		for (ClientHandler cl : clients) {
			if (!cl.checkBlackList(from.getNick())) {
				cl.sendMsg(msg);
			}
		}
		PreparedStatement ps;
		ps = this.connection.prepareStatement("insert into main.messages_broadcast(server_session_id, message)" +
					"values(?, ?)");
		ps.setInt(1, this.sessionId);
		ps.setString(2, msg);
		ps.executeUpdate();
	}

	public ResultSet getBroadcastMessagesHistory (String nickname) throws SQLException {
		PreparedStatement ps;
		ps = this.connection.prepareStatement("select\n" +
				"mb.message\n" +
				"from main.user_session  as us\n" +
				"inner join main.messages_broadcast as mb on mb.server_session_id = us.server_session_id\n" +
				"where us.nickname = ?" +
				"order by mb.server_session_id asc\n" +
				"    , us.creation_time asc ");
		ps.setString(1, nickname);
		return ps.executeQuery();
	}

	public boolean isNickBusy(String nick) {
		for (ClientHandler cl : clients) {
			if (cl.getNick().equals(nick)) {
				return true;
			}
		}
		return false;
	}

	public void broadcastClientsList() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("/clientslist ");
		for (ClientHandler cl : clients) {
			sb.append(cl.getNick() + " ");
		}
		String out = sb.toString();
		for (ClientHandler cl : clients) {
			cl.sendMsg(out);
		}
	}

	public void subscribe(ClientHandler client) throws IOException {
		clients.add(client);
		broadcastClientsList();
	}

	public void unsubscribe(ClientHandler client) throws IOException {
		clients.remove(client);
		broadcastClientsList();
	}
}
