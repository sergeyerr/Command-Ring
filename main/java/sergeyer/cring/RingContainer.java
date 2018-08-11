package sergeyer.cring;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class RingContainer {
    public static List<RingCooldownStats> RingList = new ArrayList<>();
    public static int RingCount = 3;
    public static long CoolDownOfUsageMS = 6 * 60 * 60 * 1000;

    public static void Init() throws IOException {
        String tmp = "";
        File saveDir = new File("config/commandringsave", "save.json");
        if (saveDir.exists()) {
            try (FileInputStream inputStream = new FileInputStream("config/commandringsave/save.json")) {
                tmp = IOUtils.toString(inputStream);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                RingContainer.RingList = gson.fromJson(tmp, RingContainer.RingList.getClass());
            }

        } else {
            new File("config/commandringsave").mkdirs();
            saveDir.createNewFile();
            for (int i = 0; i < RingContainer.RingCount; i++)
                RingContainer.RingList.add(new RingCooldownStats());
            Save();
        }
    }

    public static void Save() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String tmp = gson.toJson(RingList);
        BufferedWriter writer = new BufferedWriter(new FileWriter("config/commandringsave/save.json"));
        writer.write(tmp);
        writer.close();
    }
}
