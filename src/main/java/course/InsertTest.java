package course;

import java.util.ArrayList;

import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class InsertTest { 
        public static void main(String[] args) {
            MongoClient c =  new MongoClient(new MongoClientURI("mongodb://localhost"));
            MongoDatabase db = c.getDatabase("test");
            MongoCollection<Document> albums = db.getCollection("albums");
            MongoCollection<Document> images = db.getCollection("images");
            ArrayList<Document> set = images.find().into(new ArrayList<Document>());
            for(Document image : set){
            	Object id = image.get("_id");
            	ArrayList<Document> x = albums.find(new Document("images",id)).into(new ArrayList<Document>());
            	if (x.size() == 0){
            		images.deleteOne(new Document("_id",id));
            	}
            }
            ArrayList<Document> response = images.find(new Document("tags","sunrises")).into(new ArrayList<Document>());
            System.out.println(response.size());
        }
}