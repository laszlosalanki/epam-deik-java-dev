package com.epam.training.ticketservice.movie;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String movieTitle;

    private String movieGenre;

    private Integer moviePlayTime;


    public Movie() { }

    public Movie (String movieTitle, String movieGenre, Integer moviePlayTime) {
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.moviePlayTime = moviePlayTime;
    }

    public Movie(final MovieBuilder movieBuilder) {
        this.movieTitle = movieBuilder.title;
        this.movieGenre = movieBuilder.genre;
        this.moviePlayTime = movieBuilder.playTime;
    }

    public static MovieBuilder builder() { return new MovieBuilder(); }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public int getMoviePlayTime() {
        return moviePlayTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTitle, movieGenre, moviePlayTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie that = (Movie) o;
        return Objects.equals(moviePlayTime, that.moviePlayTime) &&
                Objects.equals(movieTitle, that.movieTitle);
    }

    @Override
    public String toString() {
        return new StringBuilder(movieTitle)
                .append(" (")
                .append(movieGenre)
                .append(", ")
                .append(moviePlayTime)
                .append(" minutes)")
                .toString();
    }

    public static final class MovieBuilder {
        private String title;
        private String genre;
        private Integer playTime;

        private MovieBuilder() {}

        public MovieBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public MovieBuilder withPlayTime(Integer playTime) {
            this.playTime = playTime;
            return this;
        }

        public Movie build() { return new Movie(this); }
    }
}
