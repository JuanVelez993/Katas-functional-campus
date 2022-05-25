package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();
        String kataSix=movies.stream().flatMap(movie->movie.getBoxarts()
                .stream()).reduce((bArt1,bArt2)-> bArt1.getWidth() > bArt2.getWidth()? bArt1 : bArt2)
                .map(BoxArt::getUrl).get();
        System.out.println(kataSix);
        return kataSix;
    }
}
