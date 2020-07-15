package org.classicsmc.cmcquests.data;

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

    public void setCompletedQuests(ArrayList<UUID> completedQuests) {
        this.completedQuests = completedQuests;
    }
}
