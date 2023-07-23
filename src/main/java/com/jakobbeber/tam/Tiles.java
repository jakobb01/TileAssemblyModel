package com.jakobbeber.tam;

public class Tiles {

    public Tile[] createBinary() {
        //TO DO: two set of tiles for computation
        //
        //create tiles
        // INT TO CHARS COLORS:
        // -5 = anything, except border
        // -1 = BORDER
        // 0 = 0
        // 1 = 1
        // 2 = B
        // 3 = R
        Tile s = new Tile();
        //N, S, W, E
        s.setColors(new int[]{-1, 3, -1, 2});
        s.setGlue(new int[]{1, 1, 1, 1});
        s.setName("S");
        s.setColor("#FC21BE");

        Tile r = new Tile();
        //N, S, W, E
        r.setColors(new int[]{3, 3, -1, 1});
        r.setGlue(new int[]{1, 1, 1, 1});
        r.setName("R");
        r.setColor("#E5E029");

        Tile b = new Tile();
        //N, S, W, E
        b.setColors(new int[]{-1, 0, 2, 2});
        b.setGlue(new int[]{1, 1, 1, 1});
        b.setName("B");
        b.setColor("#1464FE");

        Tile nic0 = new Tile();
        //N, S, W, E
        nic0.setColors(new int[]{1, 0, 1, 1});
        nic0.setGlue(new int[]{1, 1, 1, 1});
        nic0.setName("0");
        nic0.setColor("#FE9F14");

        Tile nic1 = new Tile();
        //N, S, W, E
        nic1.setColors(new int[]{0, 0, 0, 0});
        nic1.setGlue(new int[]{1, 1, 1, 1});
        nic1.setName("0");
        nic1.setColor("#FE9F14");

        Tile ena1 = new Tile();
        //N, S, W, E
        ena1.setColors(new int[]{1, 1, 0, 0});
        ena1.setGlue(new int[]{1, 1, 1, 1});
        ena1.setName("1");
        ena1.setColor("#00E697");

        Tile ena2 = new Tile();
        //N, S, W, E
        ena2.setColors(new int[]{0, 1, 1, 0});
        ena2.setGlue(new int[]{1, 1, 1, 1});
        ena2.setName("1");
        ena2.setColor("#00E697");


        //create set of tiles
        Tile[] tiles = new Tile[8];
        tiles[0] = s;
        tiles[1] = r;
        tiles[2] = b;
        tiles[3] = nic0;
        tiles[4] = nic1;
        tiles[5] = ena1;
        tiles[6] = ena2;
        tiles[7] = null;


        // ----------------------------------------
        // INITIALIZATION
        Assembly assembly = new Assembly();
        assembly.setTiles(tiles);

        return tiles;
    }

    public Tile[] createSierpinski() {
        //TO DO: two set of tiles for computation
        //
        //create tiles
        // INT TO CHARS COLORS:
        // -5 = anything, except border
        // -1 = BORDER (null)
        // 0 = 0
        // 1 = 1
        // 2 = N
        // 3 = W
        Tile s = new Tile();
                            //N, S, W, E
        s.setColors(new int[]{-1, 2, -1, 3});
        s.setGlue(new int[]{1, 1, 1, 1});
        s.setName("S");
        s.setColor("#C1D0DB");

        Tile r = new Tile();
        //N, S, W, E
        r.setColors(new int[]{2, 2, -1, 1});
        r.setGlue(new int[]{1, 1, 1, 1});
        r.setName("R");
        r.setColor("#C1D0DB");

        Tile b = new Tile();
        //N, S, W, E
        b.setColors(new int[]{-1, 0, 3, 3});
        b.setGlue(new int[]{1, 1, 1, 1});
        b.setName("B");
        b.setColor("#C1D0DB");

        Tile nic0 = new Tile();
        //N, S, W, E
        nic0.setColors(new int[]{0, 0, 0, 0});
        nic0.setGlue(new int[]{1, 1, 1, 1});
        nic0.setName("0");
        nic0.setColor("#FE9F14");

        Tile nic1 = new Tile();
        //N, S, W, E
        nic1.setColors(new int[]{1, 0, 1, 0});
        nic1.setGlue(new int[]{1, 1, 1, 1});
        nic1.setName("0");
        nic1.setColor("#FE9F14");

        Tile ena1 = new Tile();
        //N, S, W, E
        ena1.setColors(new int[]{1, 1, 0, 1});
        ena1.setGlue(new int[]{1, 1, 1, 1});
        ena1.setName("1");
        ena1.setColor("#015EFF");

        Tile ena2 = new Tile();
        //N, S, W, E
        ena2.setColors(new int[]{0, 1, 1, 1});
        ena2.setGlue(new int[]{1, 1, 1, 1});
        ena2.setName("1");
        ena2.setColor("#015EFF");


        //create set of tiles
        Tile[] tiles = new Tile[8];
        tiles[0] = s;
        tiles[1] = r;
        tiles[2] = b;
        tiles[3] = nic0;
        tiles[4] = nic1;
        tiles[5] = ena1;
        tiles[6] = ena2;
        tiles[7] = null;

        return tiles;
    }


}
