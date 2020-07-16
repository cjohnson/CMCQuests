package org.classicsmc.cmcquests.data;

import org.bukkit.entity.Player;
import org.classicsmc.cmcquests.quest.Quest;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerQuestData {
    private ArrayList<UUID> completedQuests;

    public PlayerQuestData() {
        this.completedQuests = new ArrayList<>();
    }

    public ArrayList<UUID> getCompletedQuests() {
        return completedQuests;
    }

    public boolean hasCompletedQuest(Quest quest) {
        for(UUID uuid : completedQuests) {
            if(uuid.equals(quest.getQuestUUID()))
                return true;
        }

        return false;
    }

    public void completeQuest(Player player, Quest quest) {
        for(UUID uuid : completedQuests) {
            if(uuid.equals(quest.getQuestUUID()))
                return;
        }

        completedQuests.add(quest.getQuestUUID());
        quest.getQuestCompletable().onComplete(player);
    }

    public void setCompletedQuests(ArrayList<UUID> completedQuests) {
        this.completedQuests = completedQuests;
    }
}
