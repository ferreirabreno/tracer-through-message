package dev.breno.tracerthroughmessage.messaging.models;

import java.util.UUID;

public record SimpleMessage(UUID identifier, String text, Integer value) { }
