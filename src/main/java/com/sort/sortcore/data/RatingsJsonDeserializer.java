package com.sort.sortcore.data;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class RatingsJsonDeserializer extends JsonDeserializer<List<Rating>> {
	@Override
	public List<Rating> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		RatingsJsonDeserializer$InnerItems innerItems = jp.readValueAs(RatingsJsonDeserializer$InnerItems.class);
		return innerItems.elements;
	}
}