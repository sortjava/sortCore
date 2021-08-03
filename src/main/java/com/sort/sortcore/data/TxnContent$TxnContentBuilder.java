package com.sort.sortcore.data;

public class TxnContent$TxnContentBuilder {
	private String txnId;
	private String txnSource;
	private String txnType;
	private String txnTitle;
	private String txnUrl;
	private String txnGenres;
	private String txnAudio;
	private String txnCast;
	private String txnDuration;
	private String txnYear;
	private String txnMaturityRatings;
	private String txnDescription;
	private String txnReleaseDate;
	private String txnSeasonNumber;
	private String txnEpisodeNumber;
	private String txnEpisodeTitle;
	private String txnEpisodeDescription;
	private String txnEventLocation;
	private String audPriority;
	private String audActive;
	private String audCreatedBy;
	private String audUpdatedBy;
	private String audCreatedDate;
	private String audUpdatedDate;
	private String audImdbId;
	private String audOpendbId;

	TxnContent$TxnContentBuilder() {
	}

	public TxnContent$TxnContentBuilder txnId(final String txnId) {
		this.txnId = txnId;
		return this;
	}

	public TxnContent$TxnContentBuilder txnSource(final String txnSource) {
		this.txnSource = txnSource;
		return this;
	}

	public TxnContent$TxnContentBuilder txnType(final String txnType) {
		this.txnType = txnType;
		return this;
	}

	public TxnContent$TxnContentBuilder txnTitle(final String txnTitle) {
		this.txnTitle = txnTitle;
		return this;
	}

	public TxnContent$TxnContentBuilder txnUrl(final String txnUrl) {
		this.txnUrl = txnUrl;
		return this;
	}

	public TxnContent$TxnContentBuilder txnGenres(final String txnGenres) {
		this.txnGenres = txnGenres;
		return this;
	}

	public TxnContent$TxnContentBuilder txnAudio(final String txnAudio) {
		this.txnAudio = txnAudio;
		return this;
	}

	public TxnContent$TxnContentBuilder txnCast(final String txnCast) {
		this.txnCast = txnCast;
		return this;
	}

	public TxnContent$TxnContentBuilder txnDuration(final String txnDuration) {
		this.txnDuration = txnDuration;
		return this;
	}

	public TxnContent$TxnContentBuilder txnYear(final String txnYear) {
		this.txnYear = txnYear;
		return this;
	}

	public TxnContent$TxnContentBuilder txnMaturityRatings(final String txnMaturityRatings) {
		this.txnMaturityRatings = txnMaturityRatings;
		return this;
	}

	public TxnContent$TxnContentBuilder txnDescription(final String txnDescription) {
		this.txnDescription = txnDescription;
		return this;
	}

	public TxnContent$TxnContentBuilder txnReleaseDate(final String txnReleaseDate) {
		this.txnReleaseDate = txnReleaseDate;
		return this;
	}

	public TxnContent$TxnContentBuilder txnSeasonNumber(final String txnSeasonNumber) {
		this.txnSeasonNumber = txnSeasonNumber;
		return this;
	}

	public TxnContent$TxnContentBuilder txnEpisodeNumber(final String txnEpisodeNumber) {
		this.txnEpisodeNumber = txnEpisodeNumber;
		return this;
	}

	public TxnContent$TxnContentBuilder txnEpisodeTitle(final String txnEpisodeTitle) {
		this.txnEpisodeTitle = txnEpisodeTitle;
		return this;
	}

	public TxnContent$TxnContentBuilder txnEpisodeDescription(final String txnEpisodeDescription) {
		this.txnEpisodeDescription = txnEpisodeDescription;
		return this;
	}

	public TxnContent$TxnContentBuilder txnEventLocation(final String txnEventLocation) {
		this.txnEventLocation = txnEventLocation;
		return this;
	}

	public TxnContent$TxnContentBuilder audPriority(final String audPriority) {
		this.audPriority = audPriority;
		return this;
	}

	public TxnContent$TxnContentBuilder audActive(final String audActive) {
		this.audActive = audActive;
		return this;
	}

	public TxnContent$TxnContentBuilder audCreatedBy(final String audCreatedBy) {
		this.audCreatedBy = audCreatedBy;
		return this;
	}

	public TxnContent$TxnContentBuilder audUpdatedBy(final String audUpdatedBy) {
		this.audUpdatedBy = audUpdatedBy;
		return this;
	}

	public TxnContent$TxnContentBuilder audCreatedDate(final String audCreatedDate) {
		this.audCreatedDate = audCreatedDate;
		return this;
	}

	public TxnContent$TxnContentBuilder audUpdatedDate(final String audUpdatedDate) {
		this.audUpdatedDate = audUpdatedDate;
		return this;
	}

	public TxnContent$TxnContentBuilder audImdbId(final String audImdbId) {
		this.audImdbId = audImdbId;
		return this;
	}

	public TxnContent$TxnContentBuilder audOpendbId(final String audOpendbId) {
		this.audOpendbId = audOpendbId;
		return this;
	}

	public TxnContent build() {
		return new TxnContent(this.txnId, this.txnSource, this.txnType, this.txnTitle, this.txnUrl, this.txnGenres,
				this.txnAudio, this.txnCast, this.txnDuration, this.txnYear, this.txnMaturityRatings,
				this.txnDescription, this.txnReleaseDate, this.txnSeasonNumber, this.txnEpisodeNumber,
				this.txnEpisodeTitle, this.txnEpisodeDescription, this.txnEventLocation, this.audPriority,
				this.audActive, this.audCreatedBy, this.audUpdatedBy, this.audCreatedDate, this.audUpdatedDate,
				this.audImdbId, this.audOpendbId);
	}

	@Override
	public String toString() {
		return "TxnContent.TxnContentBuilder(txnId=" + this.txnId + ", txnSource=" + this.txnSource + ", txnType="
				+ this.txnType + ", txnTitle=" + this.txnTitle + ", txnUrl=" + this.txnUrl + ", txnGenres="
				+ this.txnGenres + ", txnAudio=" + this.txnAudio + ", txnCast=" + this.txnCast + ", txnDuration="
				+ this.txnDuration + ", txnYear=" + this.txnYear + ", txnMaturityRatings=" + this.txnMaturityRatings
				+ ", txnDescription=" + this.txnDescription + ", txnReleaseDate=" + this.txnReleaseDate
				+ ", txnSeasonNumber=" + this.txnSeasonNumber + ", txnEpisodeNumber=" + this.txnEpisodeNumber
				+ ", txnEpisodeTitle=" + this.txnEpisodeTitle + ", txnEpisodeDescription=" + this.txnEpisodeDescription
				+ ", txnEventLocation=" + this.txnEventLocation + ", audPriority=" + this.audPriority + ", audActive="
				+ this.audActive + ", audCreatedBy=" + this.audCreatedBy + ", audUpdatedBy=" + this.audUpdatedBy
				+ ", audCreatedDate=" + this.audCreatedDate + ", audUpdatedDate=" + this.audUpdatedDate + ", audImdbId="
				+ this.audImdbId + ", audOpendbId=" + this.audOpendbId + ")";
	}
}