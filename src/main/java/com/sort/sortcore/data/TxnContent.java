package com.sort.sortcore.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@SuppressWarnings("deprecation")
@Document(indexName = "txn_content")
@JsonNaming(SnakeCaseStrategy.class)
public class TxnContent {
	@Id
	String txnId;
	String txnSource;
	String txnType;
	String txnTitle;
	String txnUrl;
	String txnGenres;
	String txnAudio;
	String txnCast;
	String txnDuration;
	String txnYear;
	String txnMaturityRatings;
	String txnDescription;
	String txnReleaseDate;
	String txnSeasonNumber;
	String txnEpisodeNumber;
	String txnEpisodeTitle;
	String txnEpisodeDescription;
	String txnEventLocation;
	String audPriority;
	String audActive;
	String audCreatedBy;
	String audUpdatedBy;
	String audCreatedDate;
	String audUpdatedDate;
	String audImdbId;
	String audOpendbId;

	TxnContent(final String txnId, final String txnSource, final String txnType, final String txnTitle,
			final String txnUrl, final String txnGenres, final String txnAudio, final String txnCast,
			final String txnDuration, final String txnYear, final String txnMaturityRatings,
			final String txnDescription, final String txnReleaseDate, final String txnSeasonNumber,
			final String txnEpisodeNumber, final String txnEpisodeTitle, final String txnEpisodeDescription,
			final String txnEventLocation, final String audPriority, final String audActive, final String audCreatedBy,
			final String audUpdatedBy, final String audCreatedDate, final String audUpdatedDate, final String audImdbId,
			final String audOpendbId) {
		this.txnId = txnId;
		this.txnSource = txnSource;
		this.txnType = txnType;
		this.txnTitle = txnTitle;
		this.txnUrl = txnUrl;
		this.txnGenres = txnGenres;
		this.txnAudio = txnAudio;
		this.txnCast = txnCast;
		this.txnDuration = txnDuration;
		this.txnYear = txnYear;
		this.txnMaturityRatings = txnMaturityRatings;
		this.txnDescription = txnDescription;
		this.txnReleaseDate = txnReleaseDate;
		this.txnSeasonNumber = txnSeasonNumber;
		this.txnEpisodeNumber = txnEpisodeNumber;
		this.txnEpisodeTitle = txnEpisodeTitle;
		this.txnEpisodeDescription = txnEpisodeDescription;
		this.txnEventLocation = txnEventLocation;
		this.audPriority = audPriority;
		this.audActive = audActive;
		this.audCreatedBy = audCreatedBy;
		this.audUpdatedBy = audUpdatedBy;
		this.audCreatedDate = audCreatedDate;
		this.audUpdatedDate = audUpdatedDate;
		this.audImdbId = audImdbId;
		this.audOpendbId = audOpendbId;
	}

	public static TxnContent$TxnContentBuilder builder() {
		return new TxnContent$TxnContentBuilder();
	}

	public TxnContent$TxnContentBuilder toBuilder() {
		return (new TxnContent$TxnContentBuilder()).txnId(this.txnId).txnSource(this.txnSource).txnType(this.txnType)
				.txnTitle(this.txnTitle).txnUrl(this.txnUrl).txnGenres(this.txnGenres).txnAudio(this.txnAudio)
				.txnCast(this.txnCast).txnDuration(this.txnDuration).txnYear(this.txnYear)
				.txnMaturityRatings(this.txnMaturityRatings).txnDescription(this.txnDescription)
				.txnReleaseDate(this.txnReleaseDate).txnSeasonNumber(this.txnSeasonNumber)
				.txnEpisodeNumber(this.txnEpisodeNumber).txnEpisodeTitle(this.txnEpisodeTitle)
				.txnEpisodeDescription(this.txnEpisodeDescription).txnEventLocation(this.txnEventLocation)
				.audPriority(this.audPriority).audActive(this.audActive).audCreatedBy(this.audCreatedBy)
				.audUpdatedBy(this.audUpdatedBy).audCreatedDate(this.audCreatedDate).audUpdatedDate(this.audUpdatedDate)
				.audImdbId(this.audImdbId).audOpendbId(this.audOpendbId);
	}

	public String getTxnId() {
		return this.txnId;
	}

	public String getTxnSource() {
		return this.txnSource;
	}

	public String getTxnType() {
		return this.txnType;
	}

	public String getTxnTitle() {
		return this.txnTitle;
	}

	public String getTxnUrl() {
		return this.txnUrl;
	}

	public String getTxnGenres() {
		return this.txnGenres;
	}

	public String getTxnAudio() {
		return this.txnAudio;
	}

	public String getTxnCast() {
		return this.txnCast;
	}

	public String getTxnDuration() {
		return this.txnDuration;
	}

	public String getTxnYear() {
		return this.txnYear;
	}

	public String getTxnMaturityRatings() {
		return this.txnMaturityRatings;
	}

	public String getTxnDescription() {
		return this.txnDescription;
	}

	public String getTxnReleaseDate() {
		return this.txnReleaseDate;
	}

	public String getTxnSeasonNumber() {
		return this.txnSeasonNumber;
	}

	public String getTxnEpisodeNumber() {
		return this.txnEpisodeNumber;
	}

	public String getTxnEpisodeTitle() {
		return this.txnEpisodeTitle;
	}

	public String getTxnEpisodeDescription() {
		return this.txnEpisodeDescription;
	}

	public String getTxnEventLocation() {
		return this.txnEventLocation;
	}

	public String getAudPriority() {
		return this.audPriority;
	}

	public String getAudActive() {
		return this.audActive;
	}

	public String getAudCreatedBy() {
		return this.audCreatedBy;
	}

	public String getAudUpdatedBy() {
		return this.audUpdatedBy;
	}

	public String getAudCreatedDate() {
		return this.audCreatedDate;
	}

	public String getAudUpdatedDate() {
		return this.audUpdatedDate;
	}

	public String getAudImdbId() {
		return this.audImdbId;
	}

	public String getAudOpendbId() {
		return this.audOpendbId;
	}

	public void setTxnId(final String txnId) {
		this.txnId = txnId;
	}

	public void setTxnSource(final String txnSource) {
		this.txnSource = txnSource;
	}

	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	public void setTxnTitle(final String txnTitle) {
		this.txnTitle = txnTitle;
	}

	public void setTxnUrl(final String txnUrl) {
		this.txnUrl = txnUrl;
	}

	public void setTxnGenres(final String txnGenres) {
		this.txnGenres = txnGenres;
	}

	public void setTxnAudio(final String txnAudio) {
		this.txnAudio = txnAudio;
	}

	public void setTxnCast(final String txnCast) {
		this.txnCast = txnCast;
	}

	public void setTxnDuration(final String txnDuration) {
		this.txnDuration = txnDuration;
	}

	public void setTxnYear(final String txnYear) {
		this.txnYear = txnYear;
	}

	public void setTxnMaturityRatings(final String txnMaturityRatings) {
		this.txnMaturityRatings = txnMaturityRatings;
	}

	public void setTxnDescription(final String txnDescription) {
		this.txnDescription = txnDescription;
	}

	public void setTxnReleaseDate(final String txnReleaseDate) {
		this.txnReleaseDate = txnReleaseDate;
	}

	public void setTxnSeasonNumber(final String txnSeasonNumber) {
		this.txnSeasonNumber = txnSeasonNumber;
	}

	public void setTxnEpisodeNumber(final String txnEpisodeNumber) {
		this.txnEpisodeNumber = txnEpisodeNumber;
	}

	public void setTxnEpisodeTitle(final String txnEpisodeTitle) {
		this.txnEpisodeTitle = txnEpisodeTitle;
	}

	public void setTxnEpisodeDescription(final String txnEpisodeDescription) {
		this.txnEpisodeDescription = txnEpisodeDescription;
	}

	public void setTxnEventLocation(final String txnEventLocation) {
		this.txnEventLocation = txnEventLocation;
	}

	public void setAudPriority(final String audPriority) {
		this.audPriority = audPriority;
	}

	public void setAudActive(final String audActive) {
		this.audActive = audActive;
	}

	public void setAudCreatedBy(final String audCreatedBy) {
		this.audCreatedBy = audCreatedBy;
	}

	public void setAudUpdatedBy(final String audUpdatedBy) {
		this.audUpdatedBy = audUpdatedBy;
	}

	public void setAudCreatedDate(final String audCreatedDate) {
		this.audCreatedDate = audCreatedDate;
	}

	public void setAudUpdatedDate(final String audUpdatedDate) {
		this.audUpdatedDate = audUpdatedDate;
	}

	public void setAudImdbId(final String audImdbId) {
		this.audImdbId = audImdbId;
	}

	public void setAudOpendbId(final String audOpendbId) {
		this.audOpendbId = audOpendbId;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof TxnContent)) {
			return false;
		} else {
			TxnContent other = (TxnContent) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$txnId = this.getTxnId();
				Object other$txnId = other.getTxnId();
				if (this$txnId == null) {
					if (other$txnId != null) {
						return false;
					}
				} else if (!this$txnId.equals(other$txnId)) {
					return false;
				}

				Object this$txnSource = this.getTxnSource();
				Object other$txnSource = other.getTxnSource();
				if (this$txnSource == null) {
					if (other$txnSource != null) {
						return false;
					}
				} else if (!this$txnSource.equals(other$txnSource)) {
					return false;
				}

				Object this$txnType = this.getTxnType();
				Object other$txnType = other.getTxnType();
				if (this$txnType == null) {
					if (other$txnType != null) {
						return false;
					}
				} else if (!this$txnType.equals(other$txnType)) {
					return false;
				}

				label302: {
					Object this$txnTitle = this.getTxnTitle();
					Object other$txnTitle = other.getTxnTitle();
					if (this$txnTitle == null) {
						if (other$txnTitle == null) {
							break label302;
						}
					} else if (this$txnTitle.equals(other$txnTitle)) {
						break label302;
					}

					return false;
				}

				label295: {
					Object this$txnUrl = this.getTxnUrl();
					Object other$txnUrl = other.getTxnUrl();
					if (this$txnUrl == null) {
						if (other$txnUrl == null) {
							break label295;
						}
					} else if (this$txnUrl.equals(other$txnUrl)) {
						break label295;
					}

					return false;
				}

				Object this$txnGenres = this.getTxnGenres();
				Object other$txnGenres = other.getTxnGenres();
				if (this$txnGenres == null) {
					if (other$txnGenres != null) {
						return false;
					}
				} else if (!this$txnGenres.equals(other$txnGenres)) {
					return false;
				}

				label281: {
					Object this$txnAudio = this.getTxnAudio();
					Object other$txnAudio = other.getTxnAudio();
					if (this$txnAudio == null) {
						if (other$txnAudio == null) {
							break label281;
						}
					} else if (this$txnAudio.equals(other$txnAudio)) {
						break label281;
					}

					return false;
				}

				label274: {
					Object this$txnCast = this.getTxnCast();
					Object other$txnCast = other.getTxnCast();
					if (this$txnCast == null) {
						if (other$txnCast == null) {
							break label274;
						}
					} else if (this$txnCast.equals(other$txnCast)) {
						break label274;
					}

					return false;
				}

				Object this$txnDuration = this.getTxnDuration();
				Object other$txnDuration = other.getTxnDuration();
				if (this$txnDuration == null) {
					if (other$txnDuration != null) {
						return false;
					}
				} else if (!this$txnDuration.equals(other$txnDuration)) {
					return false;
				}

				Object this$txnYear = this.getTxnYear();
				Object other$txnYear = other.getTxnYear();
				if (this$txnYear == null) {
					if (other$txnYear != null) {
						return false;
					}
				} else if (!this$txnYear.equals(other$txnYear)) {
					return false;
				}

				label253: {
					Object this$txnMaturityRatings = this.getTxnMaturityRatings();
					Object other$txnMaturityRatings = other.getTxnMaturityRatings();
					if (this$txnMaturityRatings == null) {
						if (other$txnMaturityRatings == null) {
							break label253;
						}
					} else if (this$txnMaturityRatings.equals(other$txnMaturityRatings)) {
						break label253;
					}

					return false;
				}

				label246: {
					Object this$txnDescription = this.getTxnDescription();
					Object other$txnDescription = other.getTxnDescription();
					if (this$txnDescription == null) {
						if (other$txnDescription == null) {
							break label246;
						}
					} else if (this$txnDescription.equals(other$txnDescription)) {
						break label246;
					}

					return false;
				}

				Object this$txnReleaseDate = this.getTxnReleaseDate();
				Object other$txnReleaseDate = other.getTxnReleaseDate();
				if (this$txnReleaseDate == null) {
					if (other$txnReleaseDate != null) {
						return false;
					}
				} else if (!this$txnReleaseDate.equals(other$txnReleaseDate)) {
					return false;
				}

				label232: {
					Object this$txnSeasonNumber = this.getTxnSeasonNumber();
					Object other$txnSeasonNumber = other.getTxnSeasonNumber();
					if (this$txnSeasonNumber == null) {
						if (other$txnSeasonNumber == null) {
							break label232;
						}
					} else if (this$txnSeasonNumber.equals(other$txnSeasonNumber)) {
						break label232;
					}

					return false;
				}

				Object this$txnEpisodeNumber = this.getTxnEpisodeNumber();
				Object other$txnEpisodeNumber = other.getTxnEpisodeNumber();
				if (this$txnEpisodeNumber == null) {
					if (other$txnEpisodeNumber != null) {
						return false;
					}
				} else if (!this$txnEpisodeNumber.equals(other$txnEpisodeNumber)) {
					return false;
				}

				label218: {
					Object this$txnEpisodeTitle = this.getTxnEpisodeTitle();
					Object other$txnEpisodeTitle = other.getTxnEpisodeTitle();
					if (this$txnEpisodeTitle == null) {
						if (other$txnEpisodeTitle == null) {
							break label218;
						}
					} else if (this$txnEpisodeTitle.equals(other$txnEpisodeTitle)) {
						break label218;
					}

					return false;
				}

				Object this$txnEpisodeDescription = this.getTxnEpisodeDescription();
				Object other$txnEpisodeDescription = other.getTxnEpisodeDescription();
				if (this$txnEpisodeDescription == null) {
					if (other$txnEpisodeDescription != null) {
						return false;
					}
				} else if (!this$txnEpisodeDescription.equals(other$txnEpisodeDescription)) {
					return false;
				}

				Object this$txnEventLocation = this.getTxnEventLocation();
				Object other$txnEventLocation = other.getTxnEventLocation();
				if (this$txnEventLocation == null) {
					if (other$txnEventLocation != null) {
						return false;
					}
				} else if (!this$txnEventLocation.equals(other$txnEventLocation)) {
					return false;
				}

				Object this$audPriority = this.getAudPriority();
				Object other$audPriority = other.getAudPriority();
				if (this$audPriority == null) {
					if (other$audPriority != null) {
						return false;
					}
				} else if (!this$audPriority.equals(other$audPriority)) {
					return false;
				}

				label190: {
					Object this$audActive = this.getAudActive();
					Object other$audActive = other.getAudActive();
					if (this$audActive == null) {
						if (other$audActive == null) {
							break label190;
						}
					} else if (this$audActive.equals(other$audActive)) {
						break label190;
					}

					return false;
				}

				label183: {
					Object this$audCreatedBy = this.getAudCreatedBy();
					Object other$audCreatedBy = other.getAudCreatedBy();
					if (this$audCreatedBy == null) {
						if (other$audCreatedBy == null) {
							break label183;
						}
					} else if (this$audCreatedBy.equals(other$audCreatedBy)) {
						break label183;
					}

					return false;
				}

				Object this$audUpdatedBy = this.getAudUpdatedBy();
				Object other$audUpdatedBy = other.getAudUpdatedBy();
				if (this$audUpdatedBy == null) {
					if (other$audUpdatedBy != null) {
						return false;
					}
				} else if (!this$audUpdatedBy.equals(other$audUpdatedBy)) {
					return false;
				}

				label169: {
					Object this$audCreatedDate = this.getAudCreatedDate();
					Object other$audCreatedDate = other.getAudCreatedDate();
					if (this$audCreatedDate == null) {
						if (other$audCreatedDate == null) {
							break label169;
						}
					} else if (this$audCreatedDate.equals(other$audCreatedDate)) {
						break label169;
					}

					return false;
				}

				label162: {
					Object this$audUpdatedDate = this.getAudUpdatedDate();
					Object other$audUpdatedDate = other.getAudUpdatedDate();
					if (this$audUpdatedDate == null) {
						if (other$audUpdatedDate == null) {
							break label162;
						}
					} else if (this$audUpdatedDate.equals(other$audUpdatedDate)) {
						break label162;
					}

					return false;
				}

				Object this$audImdbId = this.getAudImdbId();
				Object other$audImdbId = other.getAudImdbId();
				if (this$audImdbId == null) {
					if (other$audImdbId != null) {
						return false;
					}
				} else if (!this$audImdbId.equals(other$audImdbId)) {
					return false;
				}

				Object this$audOpendbId = this.getAudOpendbId();
				Object other$audOpendbId = other.getAudOpendbId();
				if (this$audOpendbId == null) {
					if (other$audOpendbId != null) {
						return false;
					}
				} else if (!this$audOpendbId.equals(other$audOpendbId)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof TxnContent;
	}

	@Override
	public int hashCode() {
		var PRIME = true;
		int result = 1;
		Object $txnId = this.getTxnId();
		result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
		Object $txnSource = this.getTxnSource();
		result = result * 59 + ($txnSource == null ? 43 : $txnSource.hashCode());
		Object $txnType = this.getTxnType();
		result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
		Object $txnTitle = this.getTxnTitle();
		result = result * 59 + ($txnTitle == null ? 43 : $txnTitle.hashCode());
		Object $txnUrl = this.getTxnUrl();
		result = result * 59 + ($txnUrl == null ? 43 : $txnUrl.hashCode());
		Object $txnGenres = this.getTxnGenres();
		result = result * 59 + ($txnGenres == null ? 43 : $txnGenres.hashCode());
		Object $txnAudio = this.getTxnAudio();
		result = result * 59 + ($txnAudio == null ? 43 : $txnAudio.hashCode());
		Object $txnCast = this.getTxnCast();
		result = result * 59 + ($txnCast == null ? 43 : $txnCast.hashCode());
		Object $txnDuration = this.getTxnDuration();
		result = result * 59 + ($txnDuration == null ? 43 : $txnDuration.hashCode());
		Object $txnYear = this.getTxnYear();
		result = result * 59 + ($txnYear == null ? 43 : $txnYear.hashCode());
		Object $txnMaturityRatings = this.getTxnMaturityRatings();
		result = result * 59 + ($txnMaturityRatings == null ? 43 : $txnMaturityRatings.hashCode());
		Object $txnDescription = this.getTxnDescription();
		result = result * 59 + ($txnDescription == null ? 43 : $txnDescription.hashCode());
		Object $txnReleaseDate = this.getTxnReleaseDate();
		result = result * 59 + ($txnReleaseDate == null ? 43 : $txnReleaseDate.hashCode());
		Object $txnSeasonNumber = this.getTxnSeasonNumber();
		result = result * 59 + ($txnSeasonNumber == null ? 43 : $txnSeasonNumber.hashCode());
		Object $txnEpisodeNumber = this.getTxnEpisodeNumber();
		result = result * 59 + ($txnEpisodeNumber == null ? 43 : $txnEpisodeNumber.hashCode());
		Object $txnEpisodeTitle = this.getTxnEpisodeTitle();
		result = result * 59 + ($txnEpisodeTitle == null ? 43 : $txnEpisodeTitle.hashCode());
		Object $txnEpisodeDescription = this.getTxnEpisodeDescription();
		result = result * 59 + ($txnEpisodeDescription == null ? 43 : $txnEpisodeDescription.hashCode());
		Object $txnEventLocation = this.getTxnEventLocation();
		result = result * 59 + ($txnEventLocation == null ? 43 : $txnEventLocation.hashCode());
		Object $audPriority = this.getAudPriority();
		result = result * 59 + ($audPriority == null ? 43 : $audPriority.hashCode());
		Object $audActive = this.getAudActive();
		result = result * 59 + ($audActive == null ? 43 : $audActive.hashCode());
		Object $audCreatedBy = this.getAudCreatedBy();
		result = result * 59 + ($audCreatedBy == null ? 43 : $audCreatedBy.hashCode());
		Object $audUpdatedBy = this.getAudUpdatedBy();
		result = result * 59 + ($audUpdatedBy == null ? 43 : $audUpdatedBy.hashCode());
		Object $audCreatedDate = this.getAudCreatedDate();
		result = result * 59 + ($audCreatedDate == null ? 43 : $audCreatedDate.hashCode());
		Object $audUpdatedDate = this.getAudUpdatedDate();
		result = result * 59 + ($audUpdatedDate == null ? 43 : $audUpdatedDate.hashCode());
		Object $audImdbId = this.getAudImdbId();
		result = result * 59 + ($audImdbId == null ? 43 : $audImdbId.hashCode());
		Object $audOpendbId = this.getAudOpendbId();
		result = result * 59 + ($audOpendbId == null ? 43 : $audOpendbId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String var10000 = this.getTxnId();
		return "TxnContent(txnId=" + var10000 + ", txnSource=" + this.getTxnSource() + ", txnType=" + this.getTxnType()
				+ ", txnTitle=" + this.getTxnTitle() + ", txnUrl=" + this.getTxnUrl() + ", txnGenres="
				+ this.getTxnGenres() + ", txnAudio=" + this.getTxnAudio() + ", txnCast=" + this.getTxnCast()
				+ ", txnDuration=" + this.getTxnDuration() + ", txnYear=" + this.getTxnYear() + ", txnMaturityRatings="
				+ this.getTxnMaturityRatings() + ", txnDescription=" + this.getTxnDescription() + ", txnReleaseDate="
				+ this.getTxnReleaseDate() + ", txnSeasonNumber=" + this.getTxnSeasonNumber() + ", txnEpisodeNumber="
				+ this.getTxnEpisodeNumber() + ", txnEpisodeTitle=" + this.getTxnEpisodeTitle()
				+ ", txnEpisodeDescription=" + this.getTxnEpisodeDescription() + ", txnEventLocation="
				+ this.getTxnEventLocation() + ", audPriority=" + this.getAudPriority() + ", audActive="
				+ this.getAudActive() + ", audCreatedBy=" + this.getAudCreatedBy() + ", audUpdatedBy="
				+ this.getAudUpdatedBy() + ", audCreatedDate=" + this.getAudCreatedDate() + ", audUpdatedDate="
				+ this.getAudUpdatedDate() + ", audImdbId=" + this.getAudImdbId() + ", audOpendbId="
				+ this.getAudOpendbId() + ")";
	}
}