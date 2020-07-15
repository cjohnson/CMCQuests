package org.classicsmc.cmcquests;

import co.aikar.commands.PaperCommandManager;
import com.google.gson.GsonBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.classicsmc.cmcquests.data.PlayerDataManager;
import org.classicsmc.cmcquests.data.PlayerQuestData;
import org.classicsmc.cmcquests.data.QuestDataManager;
import org.classicsmc.cmcquests.data.gson.exclusion.IgnoreExclusionStrategy;
import org.classicsmc.cmcquests.utility.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class CMCQuests extends JavaPlugin implements Listener {
    public static final com.google.gson.Gson GSON =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .addSerializationExclusionStrategy(new IgnoreExclusionStrategy())
                    .addDeserializationExclusionStrategy(new IgnoreExclusionStrategy())
                    .create();

    private PaperCommandManager paperCommandManager;

    private PlayerDataManager playerDataManager;
    private QuestDataManager questDataManager;

    private File playerDataFile;
    private File questDataFile;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        paperCommandManager = new PaperCommandManager(this);

        // Player data
        StringBuilder contentBuilder = new StringBuilder();

        playerDataFile = new File(getDataFolder(), "playerData.json");
        questDataFile = new File(getDataFolder(), "questData.json");

        String playerDataRawJson = IOUtils.getStringFromFile(playerDataFile);
        String questDataRawJson = IOUtils.getStringFromFile(questDataFile);

        if(playerDataRawJson.isEmpty()) {
            playerDataManager = new PlayerDataManager();
        } else {
            playerDataManager = GSON.fromJson(playerDataRawJson, PlayerDataManager.class);
        }

        if(playerDataManager == null)
            getLogger().severe("Player Data Manager is null....");

        if(questDataRawJson.isEmpty()) {
            questDataManager = new QuestDataManager();
        } else {
            questDataManager = GSON.fromJson(questDataRawJson, QuestDataManager.class);
        }
    }

    @Override
    public void onDisable() {
        String playerRawJson = GSON.toJson(playerDataManager);
        String questRawJson = GSON.toJson(questDataManager);

        //TODO Remove Debug Statements
        getLogger().info("PLAYERS: " + playerRawJson);
        getLogger().info("QUESTS: " + questRawJson);

        // Save File.
        IOUtils.saveStringToFile(playerRawJson, playerDataFile);
        IOUtils.saveStringToFile(questRawJson, questDataFile);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (playerDataManager.containsKey(event.getPlayer().getUniqueId()))
            return;

        playerDataManager.put(event.getPlayer().getUniqueId(), new PlayerQuestData());
    }
}
