package org.classicsmc.cmcquests.data;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {
    private HashMap<UUID, PlayerQuestData> playerQuestDataHashMap;

    public PlayerDataManager() {
        this.playerQuestDataHashMap = new HashMap<>();
    }

    public HashMap<UUID, PlayerQuestData> getPlayerQuestDataHashMap() {
        return playerQuestDataHashMap;
    }

    public void setPlayerQuestDataHashMap(HashMap<UUID, PlayerQuestData> playerQuestDataHashMap) {
        this.playerQuestDataHashMap = playerQuestDataHashMap;
    }

    public PlayerQuestData get(Object key) {
        return playerQuestDataHashMap.get(key);
    }

    public PlayerQuestData put(UUID key, PlayerQuestData value) {
        return playerQuestDataHashMap.put(key, value);
    }

    public boolean containsKey(Object key) {
        return playerQuestDataHashMap.containsKey(key);
    }
}
