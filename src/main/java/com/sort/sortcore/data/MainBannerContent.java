package com.sort.sortcore.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MainBannerContent {
    @ApiModelProperty(required = true, example = "2070")
    String id;
    @ApiModelProperty(required = true, example = "MOVIE")
    String type;
    @ApiModelProperty(required = true, example = "Kanneeti Katthi")
    String title;
    @ApiModelProperty(required = true, example = "2020")
    String year;
    String genre;
    @ApiModelProperty(required = true, example = "https url to a service")
    String Url;
    @ApiModelProperty(required = false, example = "Not available")
    String imageUrl;

    MainBannerContent(final String id, final String type, final String title, final String year, final String genre, final String Url,
                      final String imageUrl) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.Url = Url;
        this.imageUrl = imageUrl;
    }

    public static MainBannerContent$MainBannerContentBuilder builder() {
        return new MainBannerContent$MainBannerContentBuilder();
    }

    public MainBannerContent$MainBannerContentBuilder toBuilder() {
        return (new MainBannerContent$MainBannerContentBuilder()).id(this.id).type(this.type).title(this.title)
                .year(this.year).genre(this.genre).Url(this.Url).imageUrl(this.imageUrl);
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getYear() {
        return this.year;
    }

    public String getUrl() {
        return this.Url;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setUrl(final String Url) {
        this.Url = Url;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MainBannerContent)) {
            return false;
        } else {
            MainBannerContent other = (MainBannerContent) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
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

                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
                    return false;
                }

                label62:
                {
                    Object this$year = this.getYear();
                    Object other$year = other.getYear();
                    if (this$year == null) {
                        if (other$year == null) {
                            break label62;
                        }
                    } else if (this$year.equals(other$year)) {
                        break label62;
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

                label55:
                {
                    Object this$Url = this.getUrl();
                    Object other$Url = other.getUrl();
                    if (this$Url == null) {
                        if (other$Url == null) {
                            break label55;
                        }
                    } else if (this$Url.equals(other$Url)) {
                        break label55;
                    }

                    return false;
                }

                Object this$imageUrl = this.getImageUrl();
                Object other$imageUrl = other.getImageUrl();
                if (this$imageUrl == null) {
                    if (other$imageUrl != null) {
                        return false;
                    }
                } else if (!this$imageUrl.equals(other$imageUrl)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MainBannerContent;
    }

    @Override
    public int hashCode() {
        int PRIME = 0;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $year = this.getYear();
        result = result * 59 + ($year == null ? 43 : $year.hashCode());
        Object $genre = this.getGenre();
        result = result * 59 + ($genre == null ? 43 : $genre.hashCode());
        Object $Url = this.getUrl();
        result = result * 59 + ($Url == null ? 43 : $Url.hashCode());
        Object $imageUrl = this.getImageUrl();
        result = result * 59 + ($imageUrl == null ? 43 : $imageUrl.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String var10000 = this.getId();
        return "MainBannerContent(id=" + var10000 + ", type=" + this.getType() + ", title=" + this.getTitle()
                + ", year=" + this.getYear() + ", Url=" + this.getUrl() + ", imageUrl=" + this.getImageUrl() + ")";
    }
}