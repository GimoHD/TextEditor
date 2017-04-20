/**
 * Debugs to the console
 */
class Debugger {

    private static boolean on = false;

    /**
     * @param s prints a debug string s in the command line
     */
    public static void print(String s) {
        if (Debugger.on) {
            System.out.println("DEBUG: " + s);
        }
    }

    /**
     * Turns the debugger OFF
     */
    public static void turnOff() {
        Debugger.on = false;
    }

    /**
     * Turns the debugger ON
     */
    public static void turnOn() {
        Debugger.on = true;
    }

}
