package com.sort.sortcore.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {
	@JsonProperty("Source")
	public String source;
	@JsonProperty("Value")
	public String value;

	public static com.sort.sortcore.data.Rating$RatingBuilder builder() {
		return new com.sort.sortcore.data.Rating$RatingBuilder();
	}

	public Rating$RatingBuilder toBuilder() {
		return (new Rating$RatingBuilder()).source(this.source).value(this.value);
	}

	public String getSource() {
		return this.source;
	}

	public String getValue() {
		return this.value;
	}

	@JsonProperty("Source")
	public void setSource(final String source) {
		this.source = source;
	}

	@JsonProperty("Value")
	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Rating)) {
			return false;
		} else {
			Rating other = (Rating) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$source = this.getSource();
				Object other$source = other.getSource();
				if (this$source == null) {
					if (other$source != null) {
						return false;
					}
				} else if (!this$source.equals(other$source)) {
					return false;
				}

				Object this$value = this.getValue();
				Object other$value = other.getValue();
				if (this$value == null) {
					if (other$value != null) {
						return false;
					}
				} else if (!this$value.equals(other$value)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof Rating;
	}

	@Override
	public int hashCode() {
		int PRIME = 1;
		int result = 1;
		Object $source = this.getSource();
		result = result * 59 + ($source == null ? 43 : $source.hashCode());
		Object $value = this.getValue();
		result = result * 59 + ($value == null ? 43 : $value.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String var10000 = this.getSource();
		return "Rating(source=" + var10000 + ", value=" + this.getValue() + ")";
	}

	public Rating() {
	}

	public Rating(final String source, final String value) {
		this.source = source;
		this.value = value;
	}
}