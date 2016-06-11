package com.hbb20;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbb20 on 30/4/16.
 */
class GThumbSwatch {
    static List<GThumbSwatch> gThumbSwatchList = null;
    int colorBG, colorText;

    public GThumbSwatch(int colorBG, int colorText) {
        this.colorBG = colorBG;
        this.colorText = colorText;
    }

    public static GThumbSwatch getGThumbSwatchForEntropy(int id) {
        if (gThumbSwatchList == null) {
            prepareSwatchList();
        }
        int index = id % gThumbSwatchList.size();
        return gThumbSwatchList.get(index);
    }

    private static void prepareSwatchList() {
        gThumbSwatchList = new ArrayList<>();
        gThumbSwatchList.add(new GThumbSwatch(-10461040, -805306369));
        gThumbSwatchList.add(new GThumbSwatch(-10981248, -452984833));
        gThumbSwatchList.add(new GThumbSwatch(-10454912, -117440513));
        gThumbSwatchList.add(new GThumbSwatch(-5724064, -1560281088));
        gThumbSwatchList.add(new GThumbSwatch(-12556160, -838860801));
        gThumbSwatchList.add(new GThumbSwatch(-8353656, -1040187392));
        gThumbSwatchList.add(new GThumbSwatch(-8894392, -1325400065));
        gThumbSwatchList.add(new GThumbSwatch(-10981208, -268435457));
        gThumbSwatchList.add(new GThumbSwatch(-12021648, -922746880));
        gThumbSwatchList.add(new GThumbSwatch(-5193600, -1728053248));
        gThumbSwatchList.add(new GThumbSwatch(-6260568, -1023410176));
        gThumbSwatchList.add(new GThumbSwatch(-6252440, -1392508928));
        gThumbSwatchList.add(new GThumbSwatch(-10983256, -503316481));
        gThumbSwatchList.add(new GThumbSwatch(-12029800, -436207617));
        gThumbSwatchList.add(new GThumbSwatch(-7307176, -872415232));
        gThumbSwatchList.add(new GThumbSwatch(-11505536, -503316481));
        gThumbSwatchList.add(new GThumbSwatch(-8357792, -369098752));
        gThumbSwatchList.add(new GThumbSwatch(-9932664, -285212673));
        gThumbSwatchList.add(new GThumbSwatch(-5216128, -721420288));
        gThumbSwatchList.add(new GThumbSwatch(-9918328, -1459617792));
        gThumbSwatchList.add(new GThumbSwatch(-10452928, -1));
        gThumbSwatchList.add(new GThumbSwatch(-11491256, -1375731712));
        gThumbSwatchList.add(new GThumbSwatch(-9412448, -587202561));
        gThumbSwatchList.add(new GThumbSwatch(-13086608, -1509949441));
        gThumbSwatchList.add(new GThumbSwatch(-12025704, -436207616));
        gThumbSwatchList.add(new GThumbSwatch(-7303040, -1258291200));
        gThumbSwatchList.add(new GThumbSwatch(-10463160, -1224736769));
        gThumbSwatchList.add(new GThumbSwatch(-5205872, -1409286144));
        gThumbSwatchList.add(new GThumbSwatch(-11513704, -1241513985));
        gThumbSwatchList.add(new GThumbSwatch(-8345456, -1509949440));
        gThumbSwatchList.add(new GThumbSwatch(-8349544, -1325400064));
        gThumbSwatchList.add(new GThumbSwatch(-6254480, -1291845632));
        gThumbSwatchList.add(new GThumbSwatch(-10981232, -385875969));
        gThumbSwatchList.add(new GThumbSwatch(-5742496, -100663297));
        gThumbSwatchList.add(new GThumbSwatch(-3622760, -1744830464));
        gThumbSwatchList.add(new GThumbSwatch(-8875936, -1107296256));
        gThumbSwatchList.add(new GThumbSwatch(-9926488, -989855744));
        gThumbSwatchList.add(new GThumbSwatch(-8882064, -335544320));
        gThumbSwatchList.add(new GThumbSwatch(-10448744, -1107296256));
        gThumbSwatchList.add(new GThumbSwatch(-10973016, -1107296256));
        gThumbSwatchList.add(new GThumbSwatch(-12021624, -989855744));
        gThumbSwatchList.add(new GThumbSwatch(-6795176, -754974721));
        gThumbSwatchList.add(new GThumbSwatch(-11511704, -1241513985));

    }
}
