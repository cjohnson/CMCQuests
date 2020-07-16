package org.classicsmc.cmcquests.data;

import org.classicsmc.cmcquests.quest.Quest;

import java.util.ArrayList;
import java.util.UUID;

public class QuestDataManager {
    private ArrayList<Quest> availableQuests;

    public QuestDataManager() {
        this.availableQuests = new ArrayList<>();
    }

    public void registerQuest(Quest quest) {
        for(Quest containQuest : availableQuests) {
            if (containQuest.getQuestUUID().equals(quest.getQuestUUID())) {
                throw new IllegalArgumentException("Quest UUID is already in use!");
            }
            if(containQuest.getQuestName().equalsIgnoreCase(quest.getQuestName())) {
                throw new IllegalArgumentException("Quest Name is already in use!");
            }
        }

        availableQuests.add(quest);
    }

    public boolean containsQuest(Quest quest) {
        for(Quest containQuest : availableQuests)
            if(containQuest.getQuestUUID().equals(quest.getQuestUUID()))
                return true;

        return false;
    }

    public boolean containsQuest(UUID questUUID) {
        for(Quest containQuest : availableQuests)
            if(containQuest.getQuestUUID().equals(questUUID))
                return true;

        return false;
    }

    public boolean containsQuest(String questName) {
        for(Quest containQuest : availableQuests)
            if(containQuest.getQuestName().equalsIgnoreCase(questName))
                return true;

        return false;
    }

    public Quest getQuest(String questName) {
        for(Quest quest : availableQuests) {
            if(quest.getQuestName().equalsIgnoreCase(questName))
                return quest;
        }

        return null;
    }

    public Quest getQuest(UUID questUUID) {
        for(Quest quest : availableQuests) {
            if(quest.getQuestUUID().equals(questUUID))
                return quest;
        }

        return null;
    }

    public ArrayList<Quest> getAvailableQuests() {
        return availableQuests;
    }

    public void setAvailableQuests(ArrayList<Quest> availableQuests) {
        this.availableQuests = availableQuests;
    }
}
