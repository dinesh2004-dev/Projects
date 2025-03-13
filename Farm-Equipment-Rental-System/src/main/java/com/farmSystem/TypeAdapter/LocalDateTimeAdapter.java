package com.farmSystem.TypeAdapter;

import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime>{
	
	@Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(value != null ? value.toString() : null);
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        return in.peek() == JsonToken.NULL ? null : LocalDateTime.parse(in.nextString());
    }

}
