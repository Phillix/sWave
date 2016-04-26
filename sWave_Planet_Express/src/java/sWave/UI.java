package sWave;

/**
 *
 * @author Brian Millar
 */
public class UI {
    public static String footer = 
    "<footer class=\"panel\" id=\"base\">" +
        sWave.Graphics.s_play + 
        "\n<span id=\"songInfoDisplay\"></span>" + 
        "\n<span id=\"volControls\">" +
        sWave.Graphics.s_vol + 
        "\n<input id=\"volSlider\" oninput=\"updateVol()\" type=\"range\" min=\"0\" max=\"10\"/></span>" +
        "<span id=\"currTimeDisplay\">--:--</span>" + 
        "<span onclick=\"jumpTo(event)\" id=\"progressBG\"></span>" + 
        "<span onclick=\"jumpTo(event)\" id=\"progress\"></span>" + 
        "<span id=\"durationDisplay\">--:--</span>" + 
    "</footer>";
}
