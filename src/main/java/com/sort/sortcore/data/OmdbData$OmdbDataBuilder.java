package com.sort.sortcore.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbData$OmdbDataBuilder {
	private String title;
	private String year;
	private String rated;
	private String released;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String language;
	private String country;
	private String awards;
	private String poster;
	private List<Rating> ratings;
	private String metascore;
	private String imdbRating;
	private String imdbVotes;
	private String imdbID;
	private String type;
	private String dvd;
	private String boxOffice;
	private String production;
	private String website;
	private String response;

	OmdbData$OmdbDataBuilder() {
	}

	@JsonProperty("Title")
	public OmdbData$OmdbDataBuilder title(final String title) {
		this.title = title;
		return this;
	}

	@JsonProperty("Year")
	public OmdbData$OmdbDataBuilder year(final String year) {
		this.year = year;
		return this;
	}

	@JsonProperty("Rated")
	public OmdbData$OmdbDataBuilder rated(final String rated) {
		this.rated = rated;
		return this;
	}

	@JsonProperty("Released")
	public OmdbData$OmdbDataBuilder released(final String released) {
		this.released = released;
		return this;
	}

	@JsonProperty("Runtime")
	public OmdbData$OmdbDataBuilder runtime(final String runtime) {
		this.runtime = runtime;
		return this;
	}

	@JsonProperty("Genre")
	public OmdbData$OmdbDataBuilder genre(final String genre) {
		this.genre = genre;
		return this;
	}

	@JsonProperty("Director")
	public OmdbData$OmdbDataBuilder director(final String director) {
		this.director = director;
		return this;
	}

	@JsonProperty("Writer")
	public OmdbData$OmdbDataBuilder writer(final String writer) {
		this.writer = writer;
		return this;
	}

	@JsonProperty("Actors")
	public OmdbData$OmdbDataBuilder actors(final String actors) {
		this.actors = actors;
		return this;
	}

	@JsonProperty("Plot")
	public OmdbData$OmdbDataBuilder plot(final String plot) {
		this.plot = plot;
		return this;
	}

	@JsonProperty("Language")
	public OmdbData$OmdbDataBuilder language(final String language) {
		this.language = language;
		return this;
	}

	@JsonProperty("Country")
	public OmdbData$OmdbDataBuilder country(final String country) {
		this.country = country;
		return this;
	}

	@JsonProperty("Awards")
	public OmdbData$OmdbDataBuilder awards(final String awards) {
		this.awards = awards;
		return this;
	}

	@JsonProperty("Poster")
	public OmdbData$OmdbDataBuilder poster(final String poster) {
		this.poster = poster;
		return this;
	}

	@JsonProperty("Ratings")
	public OmdbData$OmdbDataBuilder ratings(final List<Rating> ratings) {
		this.ratings = ratings;
		return this;
	}

	@JsonProperty("Metascore")
	public OmdbData$OmdbDataBuilder metascore(final String metascore) {
		this.metascore = metascore;
		return this;
	}

	public OmdbData$OmdbDataBuilder imdbRating(final String imdbRating) {
		this.imdbRating = imdbRating;
		return this;
	}

	public OmdbData$OmdbDataBuilder imdbVotes(final String imdbVotes) {
		this.imdbVotes = imdbVotes;
		return this;
	}

	public OmdbData$OmdbDataBuilder imdbID(final String imdbID) {
		this.imdbID = imdbID;
		return this;
	}

	@JsonProperty("Type")
	public OmdbData$OmdbDataBuilder type(final String type) {
		this.type = type;
		return this;
	}

	@JsonProperty("DVD")
	public OmdbData$OmdbDataBuilder dvd(final String dvd) {
		this.dvd = dvd;
		return this;
	}

	@JsonProperty("BoxOffice")
	public OmdbData$OmdbDataBuilder boxOffice(final String boxOffice) {
		this.boxOffice = boxOffice;
		return this;
	}

	@JsonProperty("Production")
	public OmdbData$OmdbDataBuilder production(final String production) {
		this.production = production;
		return this;
	}

	@JsonProperty("Website")
	public OmdbData$OmdbDataBuilder website(final String website) {
		this.website = website;
		return this;
	}

	@JsonProperty("Response")
	public OmdbData$OmdbDataBuilder response(final String response) {
		this.response = response;
		return this;
	}

	public OmdbData build() {
		return new OmdbData(this.title, this.year, this.rated, this.released, this.runtime, this.genre, this.director,
				this.writer, this.actors, this.plot, this.language, this.country, this.awards, this.poster,
				this.ratings, this.metascore, this.imdbRating, this.imdbVotes, this.imdbID, this.type, this.dvd,
				this.boxOffice, this.production, this.website, this.response);
	}

	@Override
	public String toString() {
		return "OmdbData.OmdbDataBuilder(title=" + this.title + ", year=" + this.year + ", rated=" + this.rated
				+ ", released=" + this.released + ", runtime=" + this.runtime + ", genre=" + this.genre + ", director="
				+ this.director + ", writer=" + this.writer + ", actors=" + this.actors + ", plot=" + this.plot
				+ ", language=" + this.language + ", country=" + this.country + ", awards=" + this.awards + ", poster="
				+ this.poster + ", ratings=" + this.ratings + ", metascore=" + this.metascore + ", imdbRating="
				+ this.imdbRating + ", imdbVotes=" + this.imdbVotes + ", imdbID=" + this.imdbID + ", type=" + this.type
				+ ", dvd=" + this.dvd + ", boxOffice=" + this.boxOffice + ", production=" + this.production
				+ ", website=" + this.website + ", response=" + this.response + ")";
	}
}