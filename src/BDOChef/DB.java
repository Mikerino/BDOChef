package BDOChef;

import BDOChef.Models.*;
import io.jsondb.JsonDBTemplate;


public class DB {

    public static JsonDBTemplate initDB() {
        //Actual location on disk for database files, process should have read-write permissions to this folder
        String dbFilesLocation = "./Data";

        //Java package name where POJO's are present
        String baseScanPackage = "BDOChef.Models";

        JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);

        if(!jsonDBTemplate.collectionExists(DiscordMember.class))
            jsonDBTemplate.createCollection(DiscordMember.class);
        if(!jsonDBTemplate.collectionExists(GameCharacter.class))
            jsonDBTemplate.createCollection(GameCharacter.class);
        if(!jsonDBTemplate.collectionExists(Setting.class))
            jsonDBTemplate.createCollection(Setting.class);
        if(!jsonDBTemplate.collectionExists(NWEvent.class))
            jsonDBTemplate.createCollection(NWEvent.class);

        return jsonDBTemplate;

    }
}
