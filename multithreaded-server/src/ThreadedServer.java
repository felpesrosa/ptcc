import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ThreadedServer {
    static CSVBuilder csvBuilder;

    static HttpServer platformThreadsServer;
    static HttpServer singleThreadServer;
    static HttpServer cachedThreadsServer;
    static HttpServer virtualThreadsServer;

    public static void main(String[] args) throws Exception {
        csvBuilder = new CSVBuilder(ProcessHandle.current().pid());

        platformThreadsServer = HttpServer.create(new InetSocketAddress(8000), 0);
        platformThreadsServer.createContext("/withPlatformThreads", new PlatformThreadHandler());
        platformThreadsServer.setExecutor(java.util.concurrent.Executors.newThreadPerTaskExecutor(new SimpleThreadFactory()));
        platformThreadsServer.start();

        singleThreadServer = HttpServer.create(new InetSocketAddress(8001), 0);
        singleThreadServer.createContext("/withSingleThread", new SingleThreadHandler());
        singleThreadServer.setExecutor(java.util.concurrent.Executors.newSingleThreadExecutor());
        singleThreadServer.start();

        cachedThreadsServer = HttpServer.create(new InetSocketAddress(8002), 0);
        cachedThreadsServer.createContext("/withCachedThreads", new CachedThreadHandler());
        cachedThreadsServer.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
        cachedThreadsServer.start();

        virtualThreadsServer = HttpServer.create(new InetSocketAddress(8003), 0);
        virtualThreadsServer.createContext("/withVirtualThreads", new VirtualThreadHandler());
        virtualThreadsServer.setExecutor(java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor());
        virtualThreadsServer.start();

        virtualThreadsServer.createContext("/close", new CloseServerHandler());
    }

    static class CloseServerHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Closing the server!";
            System.out.println("------------- closing the server -------------");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

            cachedThreadsServer.stop(0);
            platformThreadsServer.stop(0);
            singleThreadServer.stop(0);
            virtualThreadsServer.stop(0);
        }
    }

    static class PlatformThreadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            String[] requestURI = t.getRequestURI().toString().split("/");

            /* exemplo de URI: /withPlatformThreads/1&Felipe%20Rosa&1111009302&7,8,9,9 */
            String[] infos = requestURI[2].split("&");

            Student student = new Student( /* Setar as informações do Estudante com dados da URI */
                    Long.parseLong(infos[0]),
                    infos[1].replace("%20", " "),
                    Long.valueOf(infos[2]),
                    infos[3].split(","));

            String response = "The student " + student.name + " is ";
            String result = student.isStudentApproved() ? "approved." : "disapproved.";
            response = response + result;

            csvBuilder.writeCSV(student.toString());

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class SingleThreadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            String[] requestURI = t.getRequestURI().toString().split("/");

            /* exemplo de URI: /withPlatformThreads/1&Felipe%20Rosa&1111009302&7,8,9,9 */
            String[] infos = requestURI[2].split("&");

            Student student = new Student( /* Setar as informações do Estudante com dados da URI */
                    Long.parseLong(infos[0]),
                    infos[1].replace("%20", " "),
                    Long.valueOf(infos[2]),
                    infos[3].split(","));

            String response = "The student " + student.name + " is ";
            String result = student.isStudentApproved() ? "approved." : "disapproved.";
            response = response + result;

            csvBuilder.writeCSV(student.toString());

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class CachedThreadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            String[] requestURI = t.getRequestURI().toString().split("/");

            /* exemplo de URI: /withPlatformThreads/1&Felipe%20Rosa&1111009302&7,8,9,9 */
            String[] infos = requestURI[2].split("&");

            Student student = new Student( /* Setar as informações do Estudante com dados da URI */
                    Long.parseLong(infos[0]),
                    infos[1].replace("%20", " "),
                    Long.valueOf(infos[2]),
                    infos[3].split(","));

            String response = "The student " + student.name + " is ";
            String result = student.isStudentApproved() ? "approved." : "disapproved.";
            response = response + result;

            csvBuilder.writeCSV(student.toString());

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class VirtualThreadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI());
            String[] requestURI = t.getRequestURI().toString().split("/");

            /* exemplo de URI: /withPlatformThreads/1&Felipe%20Rosa&1111009302&7,8,9,9 */
            String[] infos = requestURI[2].split("&");

            Student student = new Student( /* Setar as informações do Estudante com dados da URI */
                    Long.parseLong(infos[0]),
                    infos[1].replace("%20", " "),
                    Long.valueOf(infos[2]),
                    infos[3].split(","));

            String response = "The student " + student.name + " is ";
            String result = student.isStudentApproved() ? "approved." : "disapproved.";
            response = response + result;

            csvBuilder.writeCSV(student.toString());

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class SimpleThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    }

}