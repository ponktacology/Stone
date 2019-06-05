package me.ponktacology.stone.data;

import me.ponktacology.stone.Stone;
import me.ponktacology.stone.generator.Generator;
import me.ponktacology.stone.manager.StoneManager;
import me.ponktacology.stone.util.LocationUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

@Getter
public class StoneData {

  private final MongoClient mongoClient;
  private final MongoDatabase mongoDatabase;
  private final MongoCollection<Document> generators;

  public StoneData() {
    this.mongoClient = new MongoClient(
        new MongoClientURI(Stone.getInstance().getStoneConfig().getMongoLink()));
    this.mongoDatabase = this.mongoClient.getDatabase("stone");
    this.generators = this.mongoDatabase.getCollection("generators");
  }

  public static void loadAll() {
    for (Document doc : Stone.getInstance().getStoneData().getGenerators().find()) {
      new Generator(LocationUtil.deserialize(doc.getString("location")),
          doc.getInteger("durability"));
    }
  }

  public static void saveAll() {
    final Stone stone = Stone.getInstance();
    for (Document doc : stone.getStoneData().getGenerators().find()) {
      stone.getStoneData().getGenerators().deleteOne(doc);
    }

    for (Generator generator : StoneManager.getGenerators().values()) {
      Document doc = new Document("location", LocationUtil.serialize(generator.getBlockLocation()));
      doc.append("durability", generator.getDurability());
      stone.getStoneData().getGenerators().insertOne(doc);
    }
  }

}
