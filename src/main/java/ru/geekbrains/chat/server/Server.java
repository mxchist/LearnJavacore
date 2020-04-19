package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Vector;

public class Server {
	private Vector<ClientHandler> clients;
	public Connection connection;
	private Statement stmt;
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
			stmt = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void logNewSessionId() {
		try {
			this.stmt.executeUpdate(
					"insert into main.server_session\n" +
							"default values;\n" +
							"\n");
			ResultSet rs = this.stmt.executeQuery("select last_insert_rowid();");
			if (rs.next()) {
				this.sessionId = rs.getInt(1);
			}
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public void logNewClientSessionId(String nickname, int clientSessionId) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"insert into main.user_session(user_session_id, server_session_id, nickname)\n" +
							"values(?, ?, ?);"
			);
			ps.setInt(1, clientSessionId);
			ps.setInt(2, this.sessionId);
			ps.setString(3, nickname);
			ps.executeUpdate();
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
		for (ClientHandler cl : clients) {
			if (cl.getNick().equals(nickTo)) {
				cl.sendMsg("from " + from.getNick() + ": " + msg);
				from.sendMsg("to " + nickTo + ": " + msg);
				return;
			}
		}
		from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
	}

	public void broadcastMsg(ClientHandler from, String msg) {
		for (ClientHandler cl : clients) {
			if (!cl.checkBlackList(from.getNick())) {
				cl.sendMsg(msg);
			}
		}
	}

	public boolean isNickBusy(String nick) {
		for (ClientHandler cl : clients) {
			if (cl.getNick().equals(nick)) {
				return true;
			}
		}
		return false;
	}

	public void broadcastClientsList() {
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

	public void subscribe(ClientHandler client) {
		clients.add(client);
		broadcastClientsList();
	}

	public void unsubscribe(ClientHandler client) {
		clients.remove(client);
		broadcastClientsList();
	}
}
