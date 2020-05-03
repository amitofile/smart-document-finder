/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

/**
 *
 * @author amit
 */
public class MyOS {

    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    };

    private static OS os = null;

    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            } else if (operSys.contains("sunos")) {
                os = OS.SOLARIS;
            }
        }
        return os;
    }

    public String[] getSystemDirectories() {
        String[] dirs = null;
        switch (MyOS.getOS()) {
            case WINDOWS:
                break;
            case LINUX:
                break;
        }
        return dirs;
    }
}
