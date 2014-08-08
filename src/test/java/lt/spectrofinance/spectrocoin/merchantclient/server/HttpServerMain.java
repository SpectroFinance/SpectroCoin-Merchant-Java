package lt.spectrofinance.spectrocoin.merchantclient.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Simple executable to start HttpServer for HTTP request/response interaction.
 */
public class HttpServerMain {

	private static int PORT = 8000;
	private static final int BACKLOG = 0;   // none
	private static String URL_CONTEXT = "/httphandler";

	private HttpHandler httpHandler;

	/**
	 * Server initialization constructor with parameters
	 *
	 * @param httpHandler http handler
	 */
	public HttpServerMain(HttpHandler httpHandler) {
		this.httpHandler = httpHandler;
	}

	/**
	 * Server initialization constructor with parameters
	 *
	 * @param port server port
	 * @param path server path
	 * @param httpHandler http handler
	 */
	public HttpServerMain(int port, String path, HttpHandler httpHandler) {

		this.PORT = port;
		this.URL_CONTEXT = path;
		this.httpHandler = httpHandler;
	}

	/**
	 * HttpServerMain executable to run Sun's built-in JVM HTTP server.
	 *
	 */
	private void startServer() throws IOException {

		final String hostName = InetAddress.getLocalHost().getCanonicalHostName();

		final HttpServer server = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);
		server.createContext(URL_CONTEXT, httpHandler);
		server.setExecutor(null); // allow default executor to be created
		server.start();

		System.out.println("Server started and listening on http://" + hostName + ":" + PORT + URL_CONTEXT);
	}

	public void start() throws IOException {
		startServer();
	}

}