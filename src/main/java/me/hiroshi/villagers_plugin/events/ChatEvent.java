package me.hiroshi.villagers_plugin.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class chat implements Listener {
    public static final String CHANNEL_ID = "1249464796475297886";
    public static final String SERVER_URL = "http://127.0.0.1:3000/send_message";

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();

        try(HttpClient client = HttpClient.newHttpClient()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> payload = Map.of(
                    "channel_id", CHANNEL_ID,
                    "content", message,
                    "user", playerName
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERVER_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(payload)))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();

        } catch (Exception e) {
            System.out.println("Error when trying to request Discord Bot: " + e);
        }
    }
}
