package com.sort.sortcore.data;

public class OmdbRequest$OmdbRequestBuilder {
	private String title;
	private String year;
	private String uri;

	OmdbRequest$OmdbRequestBuilder() {
	}

	public OmdbRequest$OmdbRequestBuilder title(final String title) {
		this.title = title;
		return this;
	}

	public OmdbRequest$OmdbRequestBuilder year(final String year) {
		this.year = year;
		return this;
	}

	public OmdbRequest$OmdbRequestBuilder uri(final String uri) {
		this.uri = uri;
		return this;
	}

	public OmdbRequest build() {
		return new OmdbRequest(this.title, this.year, this.uri);
	}

	@Override
	public String toString() {
		return "OmdbRequest.OmdbRequestBuilder(title=" + this.title + ", year=" + this.year + ", uri=" + this.uri + ")";
	}
}