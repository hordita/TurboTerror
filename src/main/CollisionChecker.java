package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;

        }
    }

    public void checkObject(Entity entity,int[] x) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY  + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.objectM.mapObjectNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.objectM.mapObjectNum[gp.currentMap][entityRightCol][entityTopRow];
                if(tileNum1!=0&&tileNum2!=0)
                if (gp.objectM.object[tileNum1].collision == true || gp.objectM.object[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    x[0]=entityLeftCol;x[1]=entityTopRow;
                    x[2]=tileNum1;


                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.objectM.mapObjectNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.objectM.mapObjectNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(tileNum1!=0&&tileNum2!=0)
                if (gp.objectM.object[tileNum1].collision == true || gp.objectM.object[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    x[0]=entityLeftCol;x[1]=entityBottomRow;
                    x[2]=tileNum1;

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.objectM.mapObjectNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.objectM.mapObjectNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(tileNum1!=0&&tileNum2!=0)
                if (gp.objectM.object[tileNum1].collision == true || gp.objectM.object[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    x[0]=entityLeftCol;x[1]=entityBottomRow;
                    x[2]=tileNum1;


                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.objectM.mapObjectNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.objectM.mapObjectNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(tileNum1!=0&&tileNum2!=0)
                if (gp.objectM.object[tileNum1].collision == true || gp.objectM.object[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    x[0]=entityRightCol;x[1]=entityBottomRow;
                    x[2]=tileNum1;
                }
                break;

        }

    }
    public boolean checkEntity(Entity entity,Entity target){
        entity.solidArea.x= entity.worldX+entity.solidArea.x;
        entity.solidArea.y= entity.worldY+entity.solidArea.y;

        target.solidArea.x= target.worldX+target.solidArea.x;
        target.solidArea.y= target.worldY+target.solidArea.y;

        switch (entity.direction){
            case "up":
                entity.solidArea.y-=entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){
                    //entity.collisionOn=true;
                    return true;
                }break;
            case "down":
                entity.solidArea.y+=entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){
                  //  entity.collisionOn=true;
                    return true;
                }break;
            case "left":
                entity.solidArea.x-=entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){
                  //  entity.collisionOn=true;
                   return true;
                }break;
            case "right":
                entity.solidArea.x+=entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){
                   // entity.collisionOn=true;
                    return true;
                }break;

        }
        entity.solidArea.x=entity.solidAreaDefaultX;
        entity.solidArea.y=entity.solidAreaDefaultY;

        target.solidArea.x=target.solidAreaDefaultX;
        target.solidArea.y=target.solidAreaDefaultY;
        return false;

    }

}
