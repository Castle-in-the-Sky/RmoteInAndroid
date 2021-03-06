package com.audiomobile.data;

import java.util.HashMap;

public class Globals
{
  public static String ADD_DEVICE;
  public static String ASSOCIATE_BUTTON;
  public static String ASSOCIATE_MODEL;
  public static String BACKUP;
  public static String BASE_URL = "http://api.appnimator.com/staging/ircodes";
 // public static final HashMap<String, Integer> BG_COLORS;
  public static String BRANDS;
  public static String CACHE_FOLDER;
  public static String CODES;
  public static String DEVICE_SEARCH;
  public static String EMAIL;
  public static String EXPAND_FOUND_BUTTON;
  public static String REMOTE_CUSTOM;
  public static String REMOTE_CUSTOMS;
  public static String REMOTE_FOLDER;
  public static String REMOTE_MUTE_CALL;
  public static String REMOTE_NO_REMOTES;
  public static String REMOTE_PREFF_CUSTOM_SHOWAGAIN;
  public static String REMOTE_PREFF_LASTUSED;
  public static String REMOTE_PREFF_LASTUSED_PATH;
  public static String REMOTE_SHARED_PREFF;
  
  public static String FCT_BUT1;
  public static String FCT_BUT2;
  public static String FCT_BUT3;
  public static String FCT_CHDO;
  public static String FCT_CHUP;
  public static String FCT_CURDO;
  public static String FCT_CURL;
  public static String FCT_CURR;
  public static String FCT_CURUP;
  public static String FCT_EXTRA0;
  public static String FCT_EXTRA1;
  public static String FCT_EXTRA2;
  public static String FCT_EXTRA3;
  public static String FCT_GREEN;
  public static String FCT_MUTE;
  public static String FCT_NONEXISTENT;
  public static String FCT_POWER;
  public static String FCT_RED;
  public static String FCT_VOLDO;
  public static String FCT_VOLUP;
  public static String FCT_YELLOW;
  public static String FIND_BUTTON;
  public static String GCM_SENDER_ID;
  public static String LICENSE_CHECK;
  public static String LVL_KEY;
  public static String LVL_PING;
  
  public static String MODELS = BASE_URL + "/models_gen.php";
  public static String PING;
  public static String REGISTER_GCM;
  public static String REPORT;
  public static String RESTORE;
  public static int RESULT_ALLCODES;
  public static int RESULT_CHANGE_MACRO;
  public static int RESULT_REMOTE;
  public static int RESULT_SELECT_CUSTOM_FCT;
  public static int RESULT_SELECT_FCT;
  public static int RESULT_SELECT_MACRO;
  public static String STAT_REVIEW_ACCEPT;
  public static String STAT_REVIEW_DISCARD;
  public static int TAG_STRING;
  public static String UPLOADREMOTE;
  public static String VERSION;
  public static Boolean VIBRATION;
  public static Boolean LEARN;
  public static Boolean POWER;
  public static String TEST_MODE;
  public static String NEW_REMOTE;
  
  static
  {
    BRANDS = BASE_URL + "/brands.php";
    CODES = BASE_URL + "/codes.php";
    PING = BASE_URL + "/ping.php";
    LVL_PING = BASE_URL + "/lvlping.php";
    REGISTER_GCM = BASE_URL + "/gcm_register.php";
    REPORT = BASE_URL + "/report.php";
    VERSION = BASE_URL + "/version.php";
    ADD_DEVICE = BASE_URL + "/adddevice.php";
    UPLOADREMOTE = BASE_URL + "/uploadremote.php";
    ASSOCIATE_MODEL = BASE_URL + "/associate_model.php";
    ASSOCIATE_BUTTON = BASE_URL + "/associate_button.php";
    BACKUP = BASE_URL + "/remote_backup.php";
    RESTORE = BASE_URL + "/remote_restore.php";
    FIND_BUTTON = BASE_URL + "/find_button_lazy.php";
    EXPAND_FOUND_BUTTON = BASE_URL + "/expand_button_lazy.php";
    DEVICE_SEARCH = BASE_URL + "/device_search.php";
    LICENSE_CHECK = BASE_URL + "/license_check.php";
    STAT_REVIEW_ACCEPT = BASE_URL + "/stats/review_accept.php";
    STAT_REVIEW_DISCARD = BASE_URL + "/stats/review_discard.php";
    REMOTE_FOLDER = "REMOTE";
    CACHE_FOLDER = "cache";
    REMOTE_SHARED_PREFF = "SHARED_REMOTE";
    REMOTE_PREFF_LASTUSED = "";
    REMOTE_PREFF_LASTUSED_PATH = "last_used_remote_path";
    REMOTE_CUSTOM = "custom_";
    REMOTE_CUSTOMS = "customRemotes";
    REMOTE_PREFF_CUSTOM_SHOWAGAIN = "recorder_show_again";
    REMOTE_MUTE_CALL = "mute_call";
    REMOTE_NO_REMOTES = "noRemotes";
    FCT_POWER = "power";
    FCT_CHUP = "channel_up";
    FCT_CHDO = "channel_down";
    FCT_VOLUP = "volume_up";
    FCT_VOLDO = "volume_down";
    FCT_CURUP = "cursor_up";
    FCT_CURDO = "cursor_down";
    FCT_CURL = "cursor_left";
    FCT_CURR = "cursor_right";
    FCT_MUTE = "mute";
    FCT_BUT1 = "button1";
    FCT_BUT2 = "button2";
    FCT_BUT3 = "button3";
    FCT_EXTRA0 = "extra0";
    FCT_EXTRA1 = "extra1";
    FCT_EXTRA2 = "extra2";
    FCT_EXTRA3 = "extra3";
    FCT_NONEXISTENT = "non_existent";
    FCT_YELLOW = "yellow";
    FCT_GREEN = "green";
    TEST_MODE = "remote_test";
    FCT_RED = "red";
    RESULT_REMOTE = 1111;
    RESULT_SELECT_FCT = 1222;
    RESULT_ALLCODES = 1333;
    RESULT_SELECT_MACRO = 1444;
    RESULT_CHANGE_MACRO = 1555;
    RESULT_SELECT_CUSTOM_FCT = 1666;
    TAG_STRING = 0;
    EMAIL = "sommer.jiang78@gmail.com";
    
    VIBRATION = false;
    LEARN = false;
    POWER = false;
    NEW_REMOTE = "new_remote";
//    BG_COLORS = new HashMap();
//    BG_COLORS.put(FCT_YELLOW, Integer.valueOf(2130837836));
//    BG_COLORS.put(FCT_GREEN, Integer.valueOf(2130837710));
//    BG_COLORS.put(FCT_BLUE, Integer.valueOf(2130837517));
//    BG_COLORS.put(FCT_RED, Integer.valueOf(2130837752));
//    BG_COLORS.put("blue 2", Integer.valueOf(2130837629));
//    BG_COLORS.put("blue 3", Integer.valueOf(2130837630));
//    BG_COLORS.put("brown", Integer.valueOf(2130837631));
//    BG_COLORS.put("cyan", Integer.valueOf(2130837632));
//    BG_COLORS.put("cyan 2", Integer.valueOf(2130837633));
//    BG_COLORS.put("gray", Integer.valueOf(2130837634));
//    BG_COLORS.put("olive", Integer.valueOf(2130837636));
//    BG_COLORS.put("orange", Integer.valueOf(2130837637));
//    BG_COLORS.put("pink", Integer.valueOf(2130837638));
//    BG_COLORS.put("pink 2", Integer.valueOf(2130837639));
//    BG_COLORS.put("purple", Integer.valueOf(2130837640));
//    BG_COLORS.put("plain", Integer.valueOf(2130837643));
  }
}

