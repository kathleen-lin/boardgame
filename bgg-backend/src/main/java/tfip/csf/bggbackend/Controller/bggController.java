package tfip.csf.bggbackend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import tfip.csf.bggbackend.Comment;
import tfip.csf.bggbackend.Repository.bggRepo;

@RestController
@RequestMapping ("/api")
public class bggController {
    
    @Autowired
    bggRepo repo;

    @GetMapping(path = "/{gameName}")
    public ResponseEntity<String> getComments(@PathVariable String gameName){
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        Optional<List<Comment>> OptC = repo.findComments(gameName);

        if (OptC.isPresent()) {
            List<Comment> comments = OptC.get();
            if (!comments.isEmpty()){
                for (Comment c: comments){
                    // System.out.printf("%d. %s", i+1, comments.get(i).getcText());
                    JsonObject itemJson = Json.createObjectBuilder()
                                            .add("user", c.getUser())
                                            .add("rating", c.getRating())
                                            .add("text", c.getcText())
                                            .build();

                    arrBuilder.add(itemJson);
                }

                JsonArray body = arrBuilder.build();

                return new ResponseEntity<String>(body.toString(), HttpStatus.OK);
            }
            else {
                // System.out.println("ERROR");
                return new ResponseEntity<String>("list empty", HttpStatus.NOT_FOUND);
            }
    } else {
        // System.out.println("UH-OH");
        return new ResponseEntity<String>("uh-oh", HttpStatus.BAD_REQUEST);

    }

    }
}

// 

// for (Item i: items){
// JsonObject itemJson = Json.createObjectBuilder()
// 								.add("name", getName())
// 								.add("quantity", getQuantity())
// 								.add("price: getPrice())
// 								.build()
// arrBuilder.add(itemJson);
// }

// JsonOAray itmArr = arrBuilder.build();