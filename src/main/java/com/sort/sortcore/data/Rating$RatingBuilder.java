package com.sort.sortcore.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating$RatingBuilder {
	private String source;
	private String value;

	Rating$RatingBuilder() {
	}

	@JsonProperty("Source")
	public Rating$RatingBuilder source(final String source) {
		this.source = source;
		return this;
	}

	@JsonProperty("Value")
	public Rating$RatingBuilder value(final String value) {
		this.value = value;
		return this;
	}

	public Rating build() {
		return new Rating(this.source, this.value);
	}

	@Override
	public String toString() {
		return "Rating.RatingBuilder(source=" + this.source + ", value=" + this.value + ")";
	}
}