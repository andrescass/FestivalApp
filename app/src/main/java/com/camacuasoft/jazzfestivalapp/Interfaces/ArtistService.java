package com.camacuasoft.jazzfestivalapp.Interfaces;

import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.Show;
import com.camacuasoft.jazzfestivalapp.Models.TimeStamps;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andres on 23/07/17.
 */

public interface ArtistService {
    @GET("find_artist.php")
    Call<Artist> getArtist(@Query("ID") int id);

    @GET("get_all_artists.php")
    Call<List<Artist>> getAllArtist();

    @GET("get_stamps.php")
    Call<List<TimeStamps>> getStamps(@Query("name") String TSname);

    @GET("get_shows.php")
    Call<List<Show>> getShows(@Query("q") String q, @Query("value") String value);
}
