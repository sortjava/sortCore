package com.sort.sortcore.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbData {
	@JsonProperty("Title")
	public String title;
	@JsonProperty("Year")
	public String year;
	@JsonProperty("Rated")
	public String rated;
	@JsonProperty("Released")
	public String released;
	@JsonProperty("Runtime")
	public String runtime;
	@JsonProperty("Genre")
	public String genre;
	@JsonProperty("Director")
	public String director;
	@JsonProperty("Writer")
	public String writer;
	@JsonProperty("Actors")
	public String actors;
	@JsonProperty("Plot")
	public String plot;
	@JsonProperty("Language")
	public String language;
	@JsonProperty("Country")
	public String country;
	@JsonProperty("Awards")
	public String awards;
	@JsonProperty("Poster")
	public String poster;
	@JsonProperty("Ratings")
	public List<Rating> ratings;
	@JsonProperty("Metascore")
	public String metascore;
	public String imdbRating;
	public String imdbVotes;
	public String imdbID;
	@JsonProperty("Type")
	public String type;
	@JsonProperty("DVD")
	public String dvd;
	@JsonProperty("BoxOffice")
	public String boxOffice;
	@JsonProperty("Production")
	public String production;
	@JsonProperty("Website")
	public String website;
	@JsonProperty("Response")
	public String response;

	public static OmdbData$OmdbDataBuilder builder() {
		return new OmdbData$OmdbDataBuilder();
	}

	public OmdbData$OmdbDataBuilder toBuilder() {
		return (new OmdbData$OmdbDataBuilder()).title(this.title).year(this.year).rated(this.rated)
				.released(this.released).runtime(this.runtime).genre(this.genre).director(this.director)
				.writer(this.writer).actors(this.actors).plot(this.plot).language(this.language).country(this.country)
				.awards(this.awards).poster(this.poster).ratings(this.ratings).metascore(this.metascore)
				.imdbRating(this.imdbRating).imdbVotes(this.imdbVotes).imdbID(this.imdbID).type(this.type).dvd(this.dvd)
				.boxOffice(this.boxOffice).production(this.production).website(this.website).response(this.response);
	}

	public String getTitle() {
		return this.title;
	}

	public String getYear() {
		return this.year;
	}

	public String getRated() {
		return this.rated;
	}

	public String getReleased() {
		return this.released;
	}

	public String getRuntime() {
		return this.runtime;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getDirector() {
		return this.director;
	}

	public String getWriter() {
		return this.writer;
	}

	public String getActors() {
		return this.actors;
	}

	public String getPlot() {
		return this.plot;
	}

	public String getLanguage() {
		return this.language;
	}

	public String getCountry() {
		return this.country;
	}

	public String getAwards() {
		return this.awards;
	}

	public String getPoster() {
		return this.poster;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public String getMetascore() {
		return this.metascore;
	}

	public String getImdbRating() {
		return this.imdbRating;
	}

	public String getImdbVotes() {
		return this.imdbVotes;
	}

	public String getImdbID() {
		return this.imdbID;
	}

	public String getType() {
		return this.type;
	}

	public String getDvd() {
		return this.dvd;
	}

	public String getBoxOffice() {
		return this.boxOffice;
	}

	public String getProduction() {
		return this.production;
	}

	public String getWebsite() {
		return this.website;
	}

	public String getResponse() {
		return this.response;
	}

	@JsonProperty("Title")
	public void setTitle(final String title) {
		this.title = title;
	}

	@JsonProperty("Year")
	public void setYear(final String year) {
		this.year = year;
	}

	@JsonProperty("Rated")
	public void setRated(final String rated) {
		this.rated = rated;
	}

	@JsonProperty("Released")
	public void setReleased(final String released) {
		this.released = released;
	}

	@JsonProperty("Runtime")
	public void setRuntime(final String runtime) {
		this.runtime = runtime;
	}

	@JsonProperty("Genre")
	public void setGenre(final String genre) {
		this.genre = genre;
	}

	@JsonProperty("Director")
	public void setDirector(final String director) {
		this.director = director;
	}

	@JsonProperty("Writer")
	public void setWriter(final String writer) {
		this.writer = writer;
	}

	@JsonProperty("Actors")
	public void setActors(final String actors) {
		this.actors = actors;
	}

	@JsonProperty("Plot")
	public void setPlot(final String plot) {
		this.plot = plot;
	}

	@JsonProperty("Language")
	public void setLanguage(final String language) {
		this.language = language;
	}

	@JsonProperty("Country")
	public void setCountry(final String country) {
		this.country = country;
	}

	@JsonProperty("Awards")
	public void setAwards(final String awards) {
		this.awards = awards;
	}

	@JsonProperty("Poster")
	public void setPoster(final String poster) {
		this.poster = poster;
	}

	@JsonProperty("Ratings")
	public void setRatings(final List<Rating> ratings) {
		this.ratings = ratings;
	}

	@JsonProperty("Metascore")
	public void setMetascore(final String metascore) {
		this.metascore = metascore;
	}

	public void setImdbRating(final String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public void setImdbVotes(final String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public void setImdbID(final String imdbID) {
		this.imdbID = imdbID;
	}

	@JsonProperty("Type")
	public void setType(final String type) {
		this.type = type;
	}

	@JsonProperty("DVD")
	public void setDvd(final String dvd) {
		this.dvd = dvd;
	}

	@JsonProperty("BoxOffice")
	public void setBoxOffice(final String boxOffice) {
		this.boxOffice = boxOffice;
	}

	@JsonProperty("Production")
	public void setProduction(final String production) {
		this.production = production;
	}

	@JsonProperty("Website")
	public void setWebsite(final String website) {
		this.website = website;
	}

	@JsonProperty("Response")
	public void setResponse(final String response) {
		this.response = response;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof OmdbData)) {
			return false;
		} else {
			OmdbData other = (OmdbData) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label311: {
					Object this$title = this.getTitle();
					Object other$title = other.getTitle();
					if (this$title == null) {
						if (other$title == null) {
							break label311;
						}
					} else if (this$title.equals(other$title)) {
						break label311;
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

				label297: {
					Object this$rated = this.getRated();
					Object other$rated = other.getRated();
					if (this$rated == null) {
						if (other$rated == null) {
							break label297;
						}
					} else if (this$rated.equals(other$rated)) {
						break label297;
					}

					return false;
				}

				Object this$released = this.getReleased();
				Object other$released = other.getReleased();
				if (this$released == null) {
					if (other$released != null) {
						return false;
					}
				} else if (!this$released.equals(other$released)) {
					return false;
				}

				label283: {
					Object this$runtime = this.getRuntime();
					Object other$runtime = other.getRuntime();
					if (this$runtime == null) {
						if (other$runtime == null) {
							break label283;
						}
					} else if (this$runtime.equals(other$runtime)) {
						break label283;
					}

					return false;
				}

				Object this$genre = this.getGenre();
				Object other$genre = other.getGenre();
				if (this$genre == null) {
					if (other$genre != null) {
						return false;
					}
				} else if (!this$genre.equals(other$genre)) {
					return false;
				}

				label269: {
					Object this$director = this.getDirector();
					Object other$director = other.getDirector();
					if (this$director == null) {
						if (other$director == null) {
							break label269;
						}
					} else if (this$director.equals(other$director)) {
						break label269;
					}

					return false;
				}

				label262: {
					Object this$writer = this.getWriter();
					Object other$writer = other.getWriter();
					if (this$writer == null) {
						if (other$writer == null) {
							break label262;
						}
					} else if (this$writer.equals(other$writer)) {
						break label262;
					}

					return false;
				}

				Object this$actors = this.getActors();
				Object other$actors = other.getActors();
				if (this$actors == null) {
					if (other$actors != null) {
						return false;
					}
				} else if (!this$actors.equals(other$actors)) {
					return false;
				}

				label248: {
					Object this$plot = this.getPlot();
					Object other$plot = other.getPlot();
					if (this$plot == null) {
						if (other$plot == null) {
							break label248;
						}
					} else if (this$plot.equals(other$plot)) {
						break label248;
					}

					return false;
				}

				label241: {
					Object this$language = this.getLanguage();
					Object other$language = other.getLanguage();
					if (this$language == null) {
						if (other$language == null) {
							break label241;
						}
					} else if (this$language.equals(other$language)) {
						break label241;
					}

					return false;
				}

				Object this$country = this.getCountry();
				Object other$country = other.getCountry();
				if (this$country == null) {
					if (other$country != null) {
						return false;
					}
				} else if (!this$country.equals(other$country)) {
					return false;
				}

				Object this$awards = this.getAwards();
				Object other$awards = other.getAwards();
				if (this$awards == null) {
					if (other$awards != null) {
						return false;
					}
				} else if (!this$awards.equals(other$awards)) {
					return false;
				}

				label220: {
					Object this$poster = this.getPoster();
					Object other$poster = other.getPoster();
					if (this$poster == null) {
						if (other$poster == null) {
							break label220;
						}
					} else if (this$poster.equals(other$poster)) {
						break label220;
					}

					return false;
				}

				Object this$ratings = this.getRatings();
				Object other$ratings = other.getRatings();
				if (this$ratings == null) {
					if (other$ratings != null) {
						return false;
					}
				} else if (!this$ratings.equals(other$ratings)) {
					return false;
				}

				Object this$metascore = this.getMetascore();
				Object other$metascore = other.getMetascore();
				if (this$metascore == null) {
					if (other$metascore != null) {
						return false;
					}
				} else if (!this$metascore.equals(other$metascore)) {
					return false;
				}

				label199: {
					Object this$imdbRating = this.getImdbRating();
					Object other$imdbRating = other.getImdbRating();
					if (this$imdbRating == null) {
						if (other$imdbRating == null) {
							break label199;
						}
					} else if (this$imdbRating.equals(other$imdbRating)) {
						break label199;
					}

					return false;
				}

				Object this$imdbVotes = this.getImdbVotes();
				Object other$imdbVotes = other.getImdbVotes();
				if (this$imdbVotes == null) {
					if (other$imdbVotes != null) {
						return false;
					}
				} else if (!this$imdbVotes.equals(other$imdbVotes)) {
					return false;
				}

				label185: {
					Object this$imdbID = this.getImdbID();
					Object other$imdbID = other.getImdbID();
					if (this$imdbID == null) {
						if (other$imdbID == null) {
							break label185;
						}
					} else if (this$imdbID.equals(other$imdbID)) {
						break label185;
					}

					return false;
				}

				Object this$type = this.getType();
				Object other$type = other.getType();
				if (this$type == null) {
					if (other$type != null) {
						return false;
					}
				} else if (!this$type.equals(other$type)) {
					return false;
				}

				label171: {
					Object this$dvd = this.getDvd();
					Object other$dvd = other.getDvd();
					if (this$dvd == null) {
						if (other$dvd == null) {
							break label171;
						}
					} else if (this$dvd.equals(other$dvd)) {
						break label171;
					}

					return false;
				}

				Object this$boxOffice = this.getBoxOffice();
				Object other$boxOffice = other.getBoxOffice();
				if (this$boxOffice == null) {
					if (other$boxOffice != null) {
						return false;
					}
				} else if (!this$boxOffice.equals(other$boxOffice)) {
					return false;
				}

				label157: {
					Object this$production = this.getProduction();
					Object other$production = other.getProduction();
					if (this$production == null) {
						if (other$production == null) {
							break label157;
						}
					} else if (this$production.equals(other$production)) {
						break label157;
					}

					return false;
				}

				label150: {
					Object this$website = this.getWebsite();
					Object other$website = other.getWebsite();
					if (this$website == null) {
						if (other$website == null) {
							break label150;
						}
					} else if (this$website.equals(other$website)) {
						break label150;
					}

					return false;
				}

				Object this$response = this.getResponse();
				Object other$response = other.getResponse();
				if (this$response == null) {
					if (other$response != null) {
						return false;
					}
				} else if (!this$response.equals(other$response)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof OmdbData;
	}

	@Override
	public int hashCode() {
		int PRIME = 0;
		int result = 1;
		Object $title = this.getTitle();
		result = result * 59 + ($title == null ? 43 : $title.hashCode());
		Object $year = this.getYear();
		result = result * 59 + ($year == null ? 43 : $year.hashCode());
		Object $rated = this.getRated();
		result = result * 59 + ($rated == null ? 43 : $rated.hashCode());
		Object $released = this.getReleased();
		result = result * 59 + ($released == null ? 43 : $released.hashCode());
		Object $runtime = this.getRuntime();
		result = result * 59 + ($runtime == null ? 43 : $runtime.hashCode());
		Object $genre = this.getGenre();
		result = result * 59 + ($genre == null ? 43 : $genre.hashCode());
		Object $director = this.getDirector();
		result = result * 59 + ($director == null ? 43 : $director.hashCode());
		Object $writer = this.getWriter();
		result = result * 59 + ($writer == null ? 43 : $writer.hashCode());
		Object $actors = this.getActors();
		result = result * 59 + ($actors == null ? 43 : $actors.hashCode());
		Object $plot = this.getPlot();
		result = result * 59 + ($plot == null ? 43 : $plot.hashCode());
		Object $language = this.getLanguage();
		result = result * 59 + ($language == null ? 43 : $language.hashCode());
		Object $country = this.getCountry();
		result = result * 59 + ($country == null ? 43 : $country.hashCode());
		Object $awards = this.getAwards();
		result = result * 59 + ($awards == null ? 43 : $awards.hashCode());
		Object $poster = this.getPoster();
		result = result * 59 + ($poster == null ? 43 : $poster.hashCode());
		Object $ratings = this.getRatings();
		result = result * 59 + ($ratings == null ? 43 : $ratings.hashCode());
		Object $metascore = this.getMetascore();
		result = result * 59 + ($metascore == null ? 43 : $metascore.hashCode());
		Object $imdbRating = this.getImdbRating();
		result = result * 59 + ($imdbRating == null ? 43 : $imdbRating.hashCode());
		Object $imdbVotes = this.getImdbVotes();
		result = result * 59 + ($imdbVotes == null ? 43 : $imdbVotes.hashCode());
		Object $imdbID = this.getImdbID();
		result = result * 59 + ($imdbID == null ? 43 : $imdbID.hashCode());
		Object $type = this.getType();
		result = result * 59 + ($type == null ? 43 : $type.hashCode());
		Object $dvd = this.getDvd();
		result = result * 59 + ($dvd == null ? 43 : $dvd.hashCode());
		Object $boxOffice = this.getBoxOffice();
		result = result * 59 + ($boxOffice == null ? 43 : $boxOffice.hashCode());
		Object $production = this.getProduction();
		result = result * 59 + ($production == null ? 43 : $production.hashCode());
		Object $website = this.getWebsite();
		result = result * 59 + ($website == null ? 43 : $website.hashCode());
		Object $response = this.getResponse();
		result = result * 59 + ($response == null ? 43 : $response.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String var10000 = this.getTitle();
		return "OmdbData(title=" + var10000 + ", year=" + this.getYear() + ", rated=" + this.getRated() + ", released="
				+ this.getReleased() + ", runtime=" + this.getRuntime() + ", genre=" + this.getGenre() + ", director="
				+ this.getDirector() + ", writer=" + this.getWriter() + ", actors=" + this.getActors() + ", plot="
				+ this.getPlot() + ", language=" + this.getLanguage() + ", country=" + this.getCountry() + ", awards="
				+ this.getAwards() + ", poster=" + this.getPoster() + ", ratings=" + this.getRatings() + ", metascore="
				+ this.getMetascore() + ", imdbRating=" + this.getImdbRating() + ", imdbVotes=" + this.getImdbVotes()
				+ ", imdbID=" + this.getImdbID() + ", type=" + this.getType() + ", dvd=" + this.getDvd()
				+ ", boxOffice=" + this.getBoxOffice() + ", production=" + this.getProduction() + ", website="
				+ this.getWebsite() + ", response=" + this.getResponse() + ")";
	}

	public OmdbData() {
	}

	public OmdbData(final String title, final String year, final String rated, final String released,
			final String runtime, final String genre, final String director, final String writer, final String actors,
			final String plot, final String language, final String country, final String awards, final String poster,
			final List<Rating> ratings, final String metascore, final String imdbRating, final String imdbVotes,
			final String imdbID, final String type, final String dvd, final String boxOffice, final String production,
			final String website, final String response) {
		this.title = title;
		this.year = year;
		this.rated = rated;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.plot = plot;
		this.language = language;
		this.country = country;
		this.awards = awards;
		this.poster = poster;
		this.ratings = ratings;
		this.metascore = metascore;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
		this.imdbID = imdbID;
		this.type = type;
		this.dvd = dvd;
		this.boxOffice = boxOffice;
		this.production = production;
		this.website = website;
		this.response = response;
	}
}