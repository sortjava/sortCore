package com.sort.sortcore.data;

public final class OmdbRequest {
	private final String title;
	private final String year;
	private final String uri;

	OmdbRequest(final String title, final String year, final String uri) {
		this.title = title;
		this.year = year;
		this.uri = uri;
	}

	public static OmdbRequest$OmdbRequestBuilder builder() {
		return new OmdbRequest$OmdbRequestBuilder();
	}

	public OmdbRequest$OmdbRequestBuilder toBuilder() {
		return (new OmdbRequest$OmdbRequestBuilder()).title(this.title).year(this.year).uri(this.uri);
	}

	public String getTitle() {
		return this.title;
	}

	public String getYear() {
		return this.year;
	}

	public String getUri() {
		return this.uri;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof OmdbRequest)) {
			return false;
		} else {
			OmdbRequest other;
			label44: {
				other = (OmdbRequest) o;
				Object this$title = this.getTitle();
				Object other$title = other.getTitle();
				if (this$title == null) {
					if (other$title == null) {
						break label44;
					}
				} else if (this$title.equals(other$title)) {
					break label44;
				}

				return false;
			}

			Object this$year = this.getYear();
			Object other$year = other.getYear();
			if (this$year == null) {
				if (other$year != null) {
					return false;
				}
			} else if (!this$year.equals(other$year)) {
				return false;
			}

			Object this$uri = this.getUri();
			Object other$uri = other.getUri();
			if (this$uri == null) {
				if (other$uri != null) {
					return false;
				}
			} else if (!this$uri.equals(other$uri)) {
				return false;
			}

			return true;
		}
	}

	@Override
	public int hashCode() {
		int PRIME = 1;
		int result = 1;
		Object $title = this.getTitle();
		result = result * 59 + ($title == null ? 43 : $title.hashCode());
		Object $year = this.getYear();
		result = result * 59 + ($year == null ? 43 : $year.hashCode());
		Object $uri = this.getUri();
		result = result * 59 + ($uri == null ? 43 : $uri.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String var10000 = this.getTitle();
		return "OmdbRequest(title=" + var10000 + ", year=" + this.getYear() + ", uri=" + this.getUri() + ")";
	}
}