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

    }
}
