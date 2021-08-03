package com.sort.sortcore.data;

public class MainBannerContent$MainBannerContentBuilder {
	private String id;
	private String type;
	private String title;
	private String year;
	private String Url;
	private String imageUrl;

	MainBannerContent$MainBannerContentBuilder() {
	}

	public MainBannerContent$MainBannerContentBuilder id(final String id) {
		this.id = id;
		return this;
	}

	public MainBannerContent$MainBannerContentBuilder type(final String type) {
		this.type = type;
		return this;
	}

	public MainBannerContent$MainBannerContentBuilder title(final String title) {
		this.title = title;
		return this;
	}

	public MainBannerContent$MainBannerContentBuilder year(final String year) {
		this.year = year;
		return this;
	}

	public MainBannerContent$MainBannerContentBuilder Url(final String Url) {
		this.Url = Url;
		return this;
	}

	public MainBannerContent$MainBannerContentBuilder imageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public MainBannerContent build() {
		return new MainBannerContent(this.id, this.type, this.title, this.year, this.Url, this.imageUrl);
	}

	@Override
	public String toString() {
		return "MainBannerContent.MainBannerContentBuilder(id=" + this.id + ", type=" + this.type + ", title="
				+ this.title + ", year=" + this.year + ", Url=" + this.Url + ", imageUrl=" + this.imageUrl + ")";
	}
}