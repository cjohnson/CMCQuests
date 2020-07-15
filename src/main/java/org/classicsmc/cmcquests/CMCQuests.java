package org.classicsmc.cmcquests;

import co.aikar.commands.PaperCommandManager;
import com.google.gson.GsonBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.classicsmc.cmcquests.data.PlayerDataManager;
import org.classicsmc.cmcquests.data.PlayerQuestData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class CMCQuests extends JavaPlugin implements Listener {
    public static final com.google.gson.Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private PaperCommandManager paperCommandManager;

    private PlayerDataManager playerDataManager;

    private File dataFile;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        paperCommandManager = new PaperCommandManager(this);

        StringBuilder contentBuilder = new StringBuilder();

        dataFile = new File(getDataFolder(), "playerData.json");

        if(!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            playerDataManager = new PlayerDataManager();

            return;
        }

        try (Stream<String> stream = Files.lines(Paths.get(dataFile.getPath()),
                StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append('\n'));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String rawJson = contentBuilder.toString();
        if(rawJson.isEmpty())
            playerDataManager = new PlayerDataManager();

        playerDataManager = GSON.fromJson(rawJson, PlayerDataManager.class);

        if(playerDataManager == null)
            getLogger().severe("Player Data Manager is null....");
    }

    @Override
    public void onDisable() {
        String rawJson = GSON.toJson(playerDataManager);
        getLogger().info(rawJson);

        // Save File.
        try {
            PrintWriter printWriter = new PrintWriter(dataFile.getPath());

            printWriter.println(rawJson);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(playerDataManager.containsKey(event.getPlayer().getUniqueId()))
            return;

        playerDataManager.put(event.getPlayer().getUniqueId(), new PlayerQuestData());
    }
}
