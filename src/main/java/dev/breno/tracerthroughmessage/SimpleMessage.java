package dev.breno.tracerthroughmessage;

import java.util.UUID;

public record SimpleMessage(UUID identifier, String text, Integer value) { }
