class Util {
    // Using a ternary operator to avoid NPE
    public static void printLength(String name) {
        System.out.println(name != null ? name.length() : "Name is null");
    }
}
