package net.minheur.keepFocus.save;

import com.google.gson.annotations.SerializedName;

public class Save {
    @SerializedName("session_amount")
    public int sessionAmount;
    @SerializedName("session_minutes")
    public int sessionDuration;
    @SerializedName("pause_minutes")
    public int pauseDuration;
    @SerializedName("end_duration")
    public int endDuration;

}
