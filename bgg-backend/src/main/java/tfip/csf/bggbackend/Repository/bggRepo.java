package tfip.csf.bggbackend.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import tfip.csf.bggbackend.Comment;

@Repository
public class bggRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String FIND_COMMENTS_SQL = "select * from comment where gid = (select gid from game where name like ?) order by rating desc limit 5";
    
    public Optional<List<Comment>> findComments (String gameName){
  
        List<Comment> comments = new ArrayList<Comment>();

        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(FIND_COMMENTS_SQL, gameName);

            while(rs.next()){
                Comment c = new Comment();
                c.setUser(rs.getString("user"));
                c.setRating(rs.getInt("rating"));
                c.setcText(rs.getString("c_text"));
                comments.add(c);
            }

        } catch (Exception e) {
            System.out.println("error");
        }
        
        return Optional.ofNullable(comments);
    }
}
