package me.hiroshi.villagers_plugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class chat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player pl = event.getPlayer();
        String message = event.getMessage();

        try {

            HttpClient client = HttpClient.newHttpClient();

            String json = String.format("""
                                       {
                                       "channel_id": 1249464796475297886,
                                       "content": "%s",
                                       "user": "%s"
                                       }
                            """,
                    message, pl.getName());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:3000/send_message"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
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
