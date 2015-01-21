package com.eli0te.helpers;

import com.eli0te.ihm.ApplicationLayout;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by eLi0tE on 16/01/15.
 * Utilisation du programme youtube-dl en python
 */
public class YoutubeHelper implements HelperInterface {

    private static String OS = System.getProperty("os.name").toLowerCase();

    String cmd, cmd2;


    public YoutubeHelper() {
        // Uses of the right library depending on OS
        if (isWindowsOS()) {
            cmd = System.getProperty("user.dir") + "\\lib\\youtube-dl.exe";
        } else {
            cmd = System.getProperty("user.dir") + "/lib/youtube-dl";
        }
    }

    @Override
    public void getVideo(String videoURL, String outputPath, ApplicationLayout al) throws Exception {
    }

<<<<<<< HEAD
    private ArrayList<String> getinformations(String url) throws Exception {
=======
    public ArrayList<HashMap<String, String>> getInformation(String url) throws Exception{


>>>>>>> origin/master

        Process[] p = new Process[1];

        p[0] = new ProcessBuilder(cmd, "-j", url).start();

        InputStreamReader is = new InputStreamReader(p[0].getInputStream());

        BufferedReader in = new BufferedReader(is);
        String cmdOutput;

<<<<<<< HEAD
        //Chaque ligne retourné est égale aux infos d'une ou plusieurs vidéos (si playlist)
        while ((cmdOutput = in.readLine()) != null) {
=======
        ArrayList<HashMap<String, String>> infoMapList = new ArrayList<>();


        int i = 0;
        HashMap<String, String> infoMap;

        //Chaque ligne retourné est égale aux infos d'une vidéos (si playlist, plusieurs lignes)
        while ( (cmdOutput = in.readLine() ) != null ) {
>>>>>>> origin/master
            // Traiter cmdOutput (Json)
            //in.
            JSONObject line = new JSONObject(cmdOutput);

            infoMap = new HashMap<>();

<<<<<<< HEAD
            // ...

=======
            infoMap.put("title", line.getString("_filename"));
            infoMap.put("description", line.getString("description"));
            infoMap.put("thumbnail", line.getString("thumbnail"));

            infoMapList.add(i, infoMap);
            i++;
>>>>>>> origin/master
        }
        return infoMapList;
    }

    @Override
    public void getAudio(String videoURL, String outputPath, ApplicationLayout al) throws Exception {
        Process[] p = new Process[2];

        p[0] = new ProcessBuilder(cmd, "--get-filename", videoURL).start();

        BufferedReader in;//new BufferedReader( new InputStreamReader(p[0].getInputStream()) );


<<<<<<< HEAD
        for (String s : infos) {
            al.updateEventList(s);
        }
=======
        // Récupérer le tableau créé avec le bouton checkUrl

    //    ArrayList<HashMap<String,String>> infoMapList = getInformation(videoURL);
>>>>>>> origin/master

      /*
        for (HashMap<String, String> hashMap: infoMapList){
            al.updateEventList("Nom de la vidéo : " + hashMap.get("title"));
            al.updateEventList("Description : " + hashMap.get("description"));
        }
*/
        // Dynamic construction of the outputPath depending on operating system
        cmd2 = outputPath;
        if (isWindowsOS()) {
            cmd2 += "\\";
        } else {
            cmd2 += "/";
        }
        cmd2 += "%(title)s.%(ext)s";

        p[1] = new ProcessBuilder(cmd,
                "-v",                   // => Afficher l'output complet de youtube-dl
                videoURL,
                "-x",                   // => = --extract-audio
                "--audio-format",
                "\"mp3\"",              // => Pris en compte de manière aléatoire (guillemets ou non)
                "--audio-quality",
                "0",                    // => Qualité à évaluer
                // "--embed-thumbnail",    // => Attribuer l'image de la vidéo au fichier audio
//                "--prefer-avconv",      // => A tester si meilleur que ffmpeg ou pas (plus récent)
                // "--prefer-ffmpeg",
                "--ignore-errors",      // => En cas de vidéo supprimée dans une playlist
                "-o",
                cmd2
        ).start();


        //youtube-dl.exe https://www.youtube.com/watch?v=2F6d6crjRyU -x --audio-format "mp3" --audio-quality 0 -o C:\Users\Marius\Music\Youtube\%(title)s.%(ext)s


<<<<<<< HEAD
        in = new BufferedReader(new InputStreamReader(p[1].getInputStream()));
        String cmdOutput;
        String s;

        while ((cmdOutput = in.readLine()) != null) {
=======
        in = new BufferedReader( new InputStreamReader(p[1].getInputStream()) );
        String cmdOutput;
        String s;

        while ( (cmdOutput = in.readLine()) != null ) {
>>>>>>> origin/master

            System.out.println(cmdOutput);
            if (cmdOutput.contains("[download] ") && cmdOutput.contains("%")) {
                s = cmdOutput.substring("[download] ".length(), cmdOutput.indexOf('%'));
                System.out.println(Float.parseFloat(s));
                if (s.contains("."))
                    al.updateProgressBar(Math.round(Float.parseFloat(s)));
<<<<<<< HEAD

=======
>>>>>>> origin/master
            }
        }
        in.close();
    }

    private static String RemoveIllegalPathCharacters(String path) {
        return path.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    private static String RemoveIllegalPathCharactersForRename(String path) {
        return path.replaceAll("[^a-zA-Z0-9.-]", " ");
    }

    private static boolean isWindowsOS() {
        if (OS.indexOf("win") >= 0)
            return true;
        return false;
    }
}
