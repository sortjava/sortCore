package com.sort.sortcore.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "sort_user", name = "sort_content_view")
public class TxnContentVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String txnSrcId;
	private String txnCreatorId;
	private String txnDirector;
	private String txnTeamName;
	private String txnTitle;
	private String txnGenere;
	private String txnAudio;
	private String txnDesc;
	private String txnDuration;
	private String txnVerticalImage;
	private String txnSrcVideoLink;
	private String txnSrcSubtitleLink;
	private String txnGeoCity;
	private String txnGeoCountry;
	private String txnVideoLink;
	private String txnPlatform;
	private String audCreatedBy;
	private String audUpdatedBy;
	private String audCreatedDate;
	private String audUpdatedDate;
	private String audPriority;
	private String audActive;
	private String txnSubtitleLink;
	private String txnId;
	private String aud_wishlistFlag;
	private String audLikeFlag;
	private String audUnlikeFlag;
	private String txnSource;
	private String txnReleasedate;
	private String audHashId;
	private String txnVideoType;
	@Column(name = "txn_video_1_link")
	private String txnVideo1Link;
	@Column(name = "txn_video_2_link")
	private String txnVideo2Link;

	public String getTxnSrcId() {
		return txnSrcId;
	}

	public void setTxnSrcId(String txnSrcId) {
		this.txnSrcId = txnSrcId;
	}

	public String getTxnCreatorId() {
		return txnCreatorId;
	}

	public void setTxnCreatorId(String txnCreatorId) {
		this.txnCreatorId = txnCreatorId;
	}

	public String getTxnDirector() {
		return txnDirector;
	}

	public void setTxnDirector(String txnDirector) {
		this.txnDirector = txnDirector;
	}

	public String getTxnTeamName() {
		return txnTeamName;
	}

	public void setTxnTeamName(String txnTeamName) {
		this.txnTeamName = txnTeamName;
	}

	public String getTxnTitle() {
		return txnTitle;
	}

	public void setTxnTitle(String txnTitle) {
		this.txnTitle = txnTitle;
	}

	public String getTxnGenere() {
		return txnGenere;
	}

	public void setTxnGenere(String txnGenere) {
		this.txnGenere = txnGenere;
	}

	public String getTxnAudio() {
		return txnAudio;
	}

	public void setTxnAudio(String txnAudio) {
		this.txnAudio = txnAudio;
	}

	public String getTxnDesc() {
		return txnDesc;
	}

	public void setTxnDesc(String txnDesc) {
		this.txnDesc = txnDesc;
	}

	public String getTxnDuration() {
		return txnDuration;
	}

	public void setTxnDuration(String txnDuration) {
		this.txnDuration = txnDuration;
	}

	public String getTxnVerticalImage() {
		return txnVerticalImage;
	}

	public void setTxnVerticalImage(String txnVerticalImage) {
		this.txnVerticalImage = txnVerticalImage;
	}

	public String getTxnSrcVideoLink() {
		return txnSrcVideoLink;
	}

	public void setTxnSrcVideoLink(String txnSrcVideoLink) {
		this.txnSrcVideoLink = txnSrcVideoLink;
	}

	public String getTxnSrcSubtitleLink() {
		return txnSrcSubtitleLink;
	}

	public void setTxnSrcSubtitleLink(String txnSrcSubtitleLink) {
		this.txnSrcSubtitleLink = txnSrcSubtitleLink;
	}

	public String getTxnGeoCity() {
		return txnGeoCity;
	}

	public void setTxnGeoCity(String txnGeoCity) {
		this.txnGeoCity = txnGeoCity;
	}

	public String getTxnGeoCountry() {
		return txnGeoCountry;
	}

	public void setTxnGeoCountry(String txnGeoCountry) {
		this.txnGeoCountry = txnGeoCountry;
	}

	public String getTxnVideoLink() {
		return txnVideoLink;
	}

	public void setTxnVideoLink(String txnVideoLink) {
		this.txnVideoLink = txnVideoLink;
	}

	public String getTxnPlatform() {
		return txnPlatform;
	}

	public void setTxnPlatform(String txnPlatform) {
		this.txnPlatform = txnPlatform;
	}

	public String getAudCreatedBy() {
		return audCreatedBy;
	}

	public void setAudCreatedBy(String audCreatedBy) {
		this.audCreatedBy = audCreatedBy;
	}

	public String getAudUpdatedBy() {
		return audUpdatedBy;
	}

	public void setAudUpdatedBy(String audUpdatedBy) {
		this.audUpdatedBy = audUpdatedBy;
	}

	public String getAudCreatedDate() {
		return audCreatedDate;
	}

	public void setAudCreatedDate(String audCreatedDate) {
		this.audCreatedDate = audCreatedDate;
	}

	public String getAudUpdatedDate() {
		return audUpdatedDate;
	}

	public void setAudUpdatedDate(String audUpdatedDate) {
		this.audUpdatedDate = audUpdatedDate;
	}

	public String getAudPriority() {
		return audPriority;
	}

	public void setAudPriority(String audPriority) {
		this.audPriority = audPriority;
	}

	public String getAudActive() {
		return audActive;
	}

	public void setAudActive(String audActive) {
		this.audActive = audActive;
	}

	public String getTxnSubtitleLink() {
		return txnSubtitleLink;
	}

	public void setTxnSubtitleLink(String txnSubtitleLink) {
		this.txnSubtitleLink = txnSubtitleLink;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getAud_wishlistFlag() {
		return aud_wishlistFlag;
	}

	public void setAud_wishlistFlag(String aud_wishlistFlag) {
		this.aud_wishlistFlag = aud_wishlistFlag;
	}

	public String getAudLikeFlag() {
		return audLikeFlag;
	}

	public void setAudLikeFlag(String audLikeFlag) {
		this.audLikeFlag = audLikeFlag;
	}

	public String getAudUnlikeFlag() {
		return audUnlikeFlag;
	}

	public void setAudUnlikeFlag(String audUnlikeFlag) {
		this.audUnlikeFlag = audUnlikeFlag;
	}

	public String getTxnSource() {
		return txnSource;
	}

	public void setTxnSource(String txnSource) {
		this.txnSource = txnSource;
	}

	public String getTxnReleasedate() {
		return txnReleasedate;
	}

	public void setTxnReleasedate(String txnReleasedate) {
		this.txnReleasedate = txnReleasedate;
	}

	public String getAudHashId() {
		return audHashId;
	}

	public void setAudHashId(String audHashId) {
		this.audHashId = audHashId;
	}

	public String getTxnVideoType() {
		return txnVideoType;
	}

	public void setTxnVideoType(String txnVideoType) {
		this.txnVideoType = txnVideoType;
	}

	public String getTxnVideo1Link() {
		return txnVideo1Link;
	}

	public void setTxnVideo1Link(String txnVideo1Link) {
		this.txnVideo1Link = txnVideo1Link;
	}

	public String getTxnVideo2Link() {
		return txnVideo2Link;
	}

	public void setTxnVideo2Link(String txnVideo2Link) {
		this.txnVideo2Link = txnVideo2Link;
	}

}