package rest.assured.sample.project.utils;

import io.restassured.response.Response;

import java.util.function.Supplier;

public class ApiWaitUtils {

    /**
     * Waits until a resource exists (HTTP 200 response) or the timeout is reached.
     *
     * @param request       the supplier providing the HTTP response
     * @param timeoutMillis the maximum time to wait in milliseconds
     * @throws RuntimeException if the resource does not exist within the given time
     */
    public static void waitUntilResourceExists(Supplier<Response> request, int timeoutMillis) {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < timeoutMillis) {
            Response response = request.get();
            if (response.getStatusCode() == 200) {
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for resource creation", e);
            }
        }

        throw new RuntimeException("Timeout: Resource was not created within " + timeoutMillis + "ms");
    }

    /**
     * Waits until a resource is deleted (HTTP 404 response) or the timeout is reached.
     *
     * @param request       the supplier providing the HTTP response
     * @param timeoutMillis the maximum time to wait in milliseconds
     * @throws RuntimeException if the resource is not deleted within the given time
     */
    public static void waitUntilResourceDeleted(Supplier<Response> request, int timeoutMillis) {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < timeoutMillis) {
            Response response = request.get();
            if (response.getStatusCode() == 404) {
                return;
            }
            try {
                Thread.sleep(100); // 100ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for resource deletion", e);
            }
        }

        throw new RuntimeException("Timeout: Resource was not deleted within " + timeoutMillis + "ms");
    }
}