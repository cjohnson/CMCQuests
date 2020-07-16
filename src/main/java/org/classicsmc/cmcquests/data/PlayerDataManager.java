package org.classicsmc.cmcquests.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {
    private HashMap<UUID, PlayerQuestData> playerQuestDataHashMap;

    public PlayerDataManager() {
        this.playerQuestDataHashMap = new HashMap<>();
    }

    public PlayerQuestData getPlayer(Player player) {
        if(!playerQuestDataHashMap.containsKey(player.getUniqueId()))
            return null;

        return playerQuestDataHashMap.get(player.getUniqueId());
    }

    public HashMap<UUID, PlayerQuestData> getPlayerQuestDataHashMap() {
        return playerQuestDataHashMap;
    }

    public void setPlayerQuestDataHashMap(HashMap<UUID, PlayerQuestData> playerQuestDataHashMap) {
        this.playerQuestDataHashMap = playerQuestDataHashMap;
    }

    public PlayerQuestData get(UUID key) {
        return playerQuestDataHashMap.get(key);
    }

    public PlayerQuestData put(UUID key, PlayerQuestData value) {
        return playerQuestDataHashMap.put(key, value);
    }

    public boolean containsKey(UUID key) {
        return playerQuestDataHashMap.containsKey(key);
    }
}
