package org.classicsmc.cmcquests.quest;

import org.bukkit.entity.Player;
import org.classicsmc.cmcquests.data.PlayerQuestData;
import org.classicsmc.cmcquests.data.gson.exclusion.Ignore;

import java.util.UUID;

public class Quest {
    private final UUID questUUID;

    private String questName;

    @Ignore
    private IQuestCompletable questCompletable;

    public Quest(String questName, IQuestCompletable questCompletable) {
        this.questUUID = UUID.randomUUID();
        this.questName = questName;
        this.questCompletable = questCompletable;
    }

    public Quest(UUID questUUID, String questName, IQuestCompletable questCompletable) {
        this.questUUID = questUUID;
        this.questName = questName;
        this.questCompletable = questCompletable;
    }

    public UUID getQuestUUID() {
        return questUUID;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public IQuestCompletable getQuestCompletable() {
        return questCompletable;
    }

    public void setQuestCompletable(IQuestCompletable questCompletable) {
        this.questCompletable = questCompletable;
    }
}
