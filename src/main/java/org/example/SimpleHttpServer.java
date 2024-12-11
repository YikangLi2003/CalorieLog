package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * What do I want to do:
 * A server that can store data provided by user,
 * retrieve data required by user and send them to the client.
 *
 *
 */

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        // 设置服务器端口
        int port = 8080;

        // 创建HttpServer实例并绑定到指定端口
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // 创建一个上下文，指定路径和处理器
        server.createContext("/", new RootHandler());

        // 创建一个其他路径示例
        server.createContext("/hello", new HelloHandler());

        // 启动服务器
        server.setExecutor(null); // 使用默认的线程池
        System.out.println("HTTP Server started on port " + port);
        server.start();
    }

    // 根路径处理器
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Welcome to the Java HTTP Server!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    // /hello 路径的处理器
    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, World!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}

