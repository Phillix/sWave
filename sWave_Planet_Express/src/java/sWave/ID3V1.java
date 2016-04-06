package sWave;

import Dtos.Song;

/**
 *
 * @author Brian Millar
 */
public class ID3V1 {
    
    private static final boolean DEBUG = sWave.Server.DEBUGGING;
    
    /*
        This will run first as a fallback if ID3V2 cannot find data, we will 
        load data from here first if we can find it then any ID3V2 data found 
        will overwrite what was found here.
    */
    
    public static void extractTags(Song song) {
        //The ID3V1 Spec is so beautiful and simple, it was perfection, sadly 
        //most files now use the monstrosity that is ID3V2.x however I've found 
        //a lot of files actually have both as V2 resides at the start of a file 
        //and V1 at the end.
        
        byte buffer[]   = song.getSongdata();
        int currentByte = buffer.length - 128;
        String hasTag   = "";
        
        for (int i = 0; i < 3; i++) {
            hasTag += (char)buffer[currentByte];
            currentByte++;
        }
        
        if (hasTag.equals("TAG")) {
            System.out.print("ID3V1 Tag Found, Extracting Data... ");
            song.setTitle(extract30ByteText(currentByte, buffer));
            currentByte += 30;
            song.setArtist(extract30ByteText(currentByte, buffer));
            currentByte += 30;
            song.setAlbum(extract30ByteText(currentByte, buffer));
            currentByte += 30;
            String year = "" + (char)buffer[currentByte]     + 
                               (char)buffer[currentByte + 1] +
                               (char)buffer[currentByte + 2] +
                               (char)buffer[currentByte + 3];
            try {
                song.setYear(Integer.parseInt(year));
            } catch (NumberFormatException e) {
                System.out.println("Not a Year");
                if (DEBUG)
                    e.printStackTrace();
            }
            currentByte += 4;
            //We ignore the comment, we don't need it
            currentByte += 30;
            song.setGenre(lookupGenre(buffer[currentByte]));
            if (currentByte == buffer.length - 1)
                System.out.println("Success");
        }
    }
    
    private static String extract30ByteText(int currentByte, byte buffer[]) {
        String text = "";
        for (int i = 0; i < 30; i++)
            text += (char)buffer[currentByte + i];
        return text;
    }
    
    private static String lookupGenre(int genre) {
        String lookupTable[] = {"Blues", "Classic Rock", "Country", "Dance", 
                                "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", 
                                "Metal", "New Age", "Oldies", "Other", "Pop", 
                                "Rhythm and Blues", "Rap", "Reggae", "Rock", 
                                "Techno", "Industrial", "Alternative", "Ska", 
                                "Death Metal", "Pranks", "Soundtrack", 
                                "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", 
                                "Jazz & Funk", "Fusion", "Trance", "Classical", 
                                "Instrumental", "Acid", "House", "Game", 
                                "Sound Clip", "Gospel", "Noise", 
                                "Alternative Rock", "Bass", "Soul", "Punk", 
                                "Space", "Meditative", "Instrumental Pop", 
                                "Instrumental Rock", "Ethnic", "Gothic", 
                                "Darkwave", "Techno-Industrial", "Electronic", 
                                "Pop-Folk", "Eurodance", "Dream", 
                                "Southern Rock", "Comedy", "Cult", "Gangsta", 
                                "Top ", "Christian Rap", "Pop/Funk", "Jungle", 
                                "Native US", "Cabaret", "New Wave", 
                                "Psychedelic", "Rave", "Showtunes", "Trailer", 
                                "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", 
                                "Polka", "Retro", "Musical", "Rock ’n’ Roll", 
                                "Hard Rock", 
                                //Including the Winamp extensions because why not
                                "Folk", "Folk-Rock", "National Folk", "Swing", 
                                "Fast Fusion", "Bebop", "Latin", "Revival", 
                                "Celtic", "Bluegrass", "Avantgarde", 
                                "Gothic Rock", "Progressive Rock", 
                                "Psychedelic Rock", "Symphonic Rock", 
                                "Slow Rock", "Big Band", "Chorus", 
                                "Easy Listening", "Acoustic", "Humour", 
                                "Speech", "Chanson", "Opera", "Chamber Music", 
                                "Sonata", "Symphony", "Booty Bass", "Primus", 
                                "Porn Groove", "Satire", "Slow Jam", "Club", 
                                "Tango", "Samba", "Folklore", "Ballad", 
                                "Power Ballad", "Rhythmic Soul", "Freestyle", 
                                "Duet", "Punk Rock", "Drum Solo", "A cappella", 
                                "Euro-House", "Dance Hall", "Goa", 
                                "Drum & Bass", "Club-House", "Hardcore Techno", 
                                "Terror", "Indie", "BritPop", "Negerpunk", 
                                "Polsk Punk", "Beat", "Christian Gangsta Rap", 
                                "Heavy Metal", "Black Metal", "Crossover", 
                                "Contemporary Christian", "Christian Rock", 
                                "Merengue", "Salsa", "Thrash Metal", "Anime", 
                                "Jpop", "Synthpop", "Abstract", "Art Rock", 
                                "Baroque", "Bhangra", "Big Beat", "Breakbeat", 
                                "Chillout", "Downtempo", "Dub", "EBM", 
                                "Eclectic", "Electro", "Electroclash", "Emo", 
                                "Experimental", "Garage", "Global", "IDM", 
                                "Illbient", "Industro-Goth", "Jam Band", 
                                "Krautrock", "Leftfield", "Lounge", "Math Rock", 
                                "New Romantic", "Nu-Breakz", "Post-Punk", 
                                "Post-Rock", "Psytrance", "Shoegaze", 
                                "Space Rock", "Trop Rock", "World Music", 
                                "Neoclassical", "Audiobook", "Audio Theatre", 
                                "Neue Deutsche Welle", "Podcast", "Indie Rock", 
                                "G-Funk", "Dubstep", "Garage Rock", "Psybient"};
        return lookupTable[genre];
    }
    
}
