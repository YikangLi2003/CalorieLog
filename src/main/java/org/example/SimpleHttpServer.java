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
 * Database:
 * Define a interface for entities: user, diet diary......
 * add to database, delete, change, retrieve......
 *
 */

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // 1. 创建 HttpServer 实例，绑定到指定的 IP 和端口
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 2. 创建一个上下文（context），定义请求路径和处理器
        server.createContext("/", new RootHandler());
        server.createContext("/hello", new HelloHandler());
        server.createContext("/test", new TestHandler());

        // 3. 启动服务器
        server.setExecutor(null); // 使用默认的 Executor
        server.start();
        System.out.println("Server started on http://localhost:8080");
    }

    // 处理根路径的请求
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Welcome to the Root Path!";
            exchange.sendResponseHeaders(200, response.getBytes().length); // 设置响应头
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes()); // 写入响应体
            }
        }
    }

    // 处理 "/hello" 路径的请求
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

    // 处理 "/test" 路径的请求
    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Request method: " + exchange.getRequestMethod();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
